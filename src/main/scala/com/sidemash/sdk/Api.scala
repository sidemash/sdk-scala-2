package com.sidemash.sdk

import java.io.{BufferedReader, InputStream, InputStreamReader}
import java.net.{HttpURLConnection, URL, URLEncoder}
import java.nio.charset.StandardCharsets
import java.security.MessageDigest
import java.util.{Base64, StringJoiner}

import javax.crypto.Mac
import javax.crypto.spec.SecretKeySpec
import play.api.libs.json.{JsResult, JsSuccess, JsValue, Json, Reads, Writes}


object Api {

  type From[T] = String => T
  type QueryString = List[(String, String)]

  def list[T:Writes:Reads](path:String, headers: Map[String, String] = Map(), queryString:QueryString = Nil, auth:Auth) : RestCollection[T] = {
    call(path, method = "GET", headers, queryString, body = None, auth)(RestCollection.reader[T])
  }
  
  def get[T:Reads](path:String, headers: Map[String, String] = Map(), queryString:QueryString = Nil, auth:Auth) : T  = {
    call(path, method = "GET", headers, queryString, body = None, auth)
  }

  def post[T:Reads](path:String, headers: Map[String, String] = Map(), queryString:QueryString = Nil, body:Option[String], auth:Auth) : T  = {
    call(path, method = "POST", headers, queryString, body =  body, auth)
  }

  def patch[T:Reads](path:String, headers: Map[String, String] = Map(), queryString:QueryString = Nil, body:Option[String], auth:Auth) : T  = {
    call(path, method = "PATCH", headers, queryString, body =  body, auth)
  }

  def put[T:Reads](path:String, headers: Map[String, String] = Map(), queryString:QueryString = Nil, body:Option[String], auth:Auth) : T  = {
    call(path, method = "PUT", headers, queryString, body =  body, auth)
  }

  def delete(path:String, headers: Map[String, String] = Map(), queryString:QueryString = Nil, auth:Auth): Unit = {
    call[Unit](path, method = "DELETE", headers, queryString, None, auth)(unitReader)
  }


  val unitReader : Reads[Unit] = new Reads[Unit] {
    override def reads(json: JsValue): JsResult[Unit] = JsSuccess(())
  }

  private val Host = "http://dev-api.sidemash.io"
  val Version = "v1.0"

  private def tuple2UrlEncodedKeyValuePairs(t: (String, String)) =
    URLEncoder.encode(t._1, StandardCharsets.UTF_8.name()) + "=" + URLEncoder.encode(t._1, StandardCharsets.UTF_8.name())

  private def call[T:Reads](path:String, method:String,  headers: Map[String, String], queryString:QueryString, body:Option[String], auth:Auth) : T = {
    val qsStr = queryString.map(tuple2UrlEncodedKeyValuePairs)
    val qsOption =
      if(qsStr.isEmpty) None
      else Some(qsStr.mkString("?", "&", ""))
    val queryUrl: URL = new URL(Host + path + qsOption.getOrElse(""))
    val connection : HttpURLConnection = queryUrl.openConnection().asInstanceOf[HttpURLConnection]
    if(method == "PATCH") connection.setRequestMethod("POST")
    else connection.setRequestMethod(method)

    val signedHeaders = computeSignedHeaders(method, body, headers, auth)
    if(method != "GET"){
      val request : Request = Request(
        method = method,
        path = path,
        bodyHash = body.map(sha256),
        signedHeaders = signedHeaders,
        queryString = qsOption
      )
      connection.setRequestProperty("X-Sdm-Nonce",  request.nonce.toString)
      connection.setRequestProperty("X-Sdm-SignedHeaders",  signedHeaders.map(_._1).mkString(", "))
      connection.setRequestProperty("X-Sdm-Signature",  s"SHA256 " + sign(request, auth.secretKey))
    }
    signedHeaders.foreach({
      case (key, value) =>
        connection.setRequestProperty(key, value)
    })

    body foreach { b =>
      connection.setDoOutput(true)
      connection.getOutputStream.write(b.getBytes(StandardCharsets.UTF_8))
    }

    val statusCode = connection.getResponseCode
    val statusMessage = connection.getResponseMessage
    def getResponseBody(in:InputStream) : String = {
      val rd : BufferedReader = new BufferedReader(new InputStreamReader(in))
      val sj: StringJoiner = new StringJoiner("\n")
      @scala.annotation.tailrec
      def getResponseBodyRec(line:String): String = {
        if(line == null) sj.toString
        else {
          sj.add(line)
          getResponseBodyRec(line =  rd.readLine())
        }
      }
      getResponseBodyRec(line = rd.readLine())
    }
    if(statusCode < 300) Json.parse(getResponseBody(connection.getInputStream)).as[T]
    else throw CallException(statusCode, statusMessage, getResponseBody(connection.getErrorStream))
  }

  private def computeSignedHeaders(method:String,
                                   body:Option[String],
                                   headers: Map[String, String],
                                   auth:Auth) : List[(String, String)] = {
    (if(method == "PATCH") List("X-HTTP-Method-Override" -> "PATCH") else Nil ) :::
      List("Accept" -> "application/json", "User-Agent" ->  s"Sdk Scala $Version", "Authorization" -> s"Bearer ${auth.token}") :::
      headers.toList :::
      body.toList.map(_ => "Content-Type" -> "application/json")
  }

  private def sha256(message:String) : String  = {
    val md : MessageDigest = MessageDigest.getInstance("SHA-256")
    md.update(message.getBytes(StandardCharsets.UTF_8))
    Base64.getEncoder.encodeToString(md.digest())
  }
  private val Algorithm = "HmacSHA256"
  private def sign(signedRequest: Request, privateKey: String) : String = {
    val message = signedRequest.toMessage
    val secretKeySpec = new SecretKeySpec(privateKey.getBytes(StandardCharsets.UTF_8), Algorithm)
    val mac : Mac = Mac.getInstance(Algorithm)
    mac.init(secretKeySpec)
    mac.update(message.getBytes(StandardCharsets.UTF_8))
    Base64.getEncoder.encodeToString(mac.doFinal())
  }
}
