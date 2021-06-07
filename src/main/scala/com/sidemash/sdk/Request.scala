package com.sidemash.sdk
import Request.{HeaderName, HeaderValue}

case class Request(nonce:Long,
                   signedHeaders : List[(HeaderName, HeaderValue)],
                   httpMethod : String,
                   path : String,
                   queryString : Option[String],
                   bodyHash : Option[String]) {
  def toMessage : String =
    nonce +
      signedHeaders.map{ case (key, value) => key + ":" + value }.mkString("") +
      httpMethod +
      path +
      queryString.getOrElse("") +
      bodyHash.getOrElse("")
}

object Request {

  type HeaderName = String
  type HeaderValue = String
  def apply(signedHeaders:List[(HeaderName, HeaderValue)],
            method:String,
            path:String,
            queryString:Option[String],
            bodyHash:Option[String]) : Request = {
    Request(
      nonce = System.currentTimeMillis(),
      signedHeaders,
      method,
      path,
      queryString = queryString,
      bodyHash
    )
  }
}