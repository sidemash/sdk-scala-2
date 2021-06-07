/*
   Copyright © 2020 Sidemash Cloud Services

   Licensed under the Apache  License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless  required  by  applicable  law  or  agreed to in writing,
   software  distributed  under  the  License  is distributed on an
   "AS IS"  BASIS, WITHOUT  WARRANTIES  OR CONDITIONS OF  ANY KIND,
   either  express  or  implied.  See the License for the  specific
   language governing permissions and limitations under the License.
*/


package com.sidemash.sdk

import HttpMethod._
import play.api.libs.json._

sealed abstract class HttpMethod {
  def isNotGet : Boolean =
    HttpMethod.isNotGet(this.toString)

  def isNotPost : Boolean =
    HttpMethod.isNotPost(this.toString)

  def isNotPut : Boolean =
    HttpMethod.isNotPut(this.toString)

  def isNotDelete : Boolean =
    HttpMethod.isNotDelete(this.toString)

  def isNotPatch : Boolean =
    HttpMethod.isNotPatch(this.toString)

  def ifNotGet(fn: HttpMethod => Unit) : Unit =
    HttpMethod.ifNotGet(this.toString, fn)

  def ifNotPost(fn: HttpMethod => Unit) : Unit =
    HttpMethod.ifNotPost(this.toString, fn)

  def ifNotPut(fn: HttpMethod => Unit) : Unit =
    HttpMethod.ifNotPut(this.toString, fn)

  def ifNotDelete(fn: HttpMethod => Unit) : Unit =
    HttpMethod.ifNotDelete(this.toString, fn)

  def ifNotPatch(fn: HttpMethod => Unit) : Unit =
    HttpMethod.ifNotPatch(this.toString, fn)

  def isGet : Boolean =
    HttpMethod.isGet(this.toString)

  def isPost : Boolean =
    HttpMethod.isPost(this.toString)

  def isPut : Boolean =
    HttpMethod.isPut(this.toString)

  def isDelete : Boolean =
    HttpMethod.isDelete(this.toString)

  def isPatch : Boolean =
    HttpMethod.isPatch(this.toString)

  def asGetOption : Option[Get.type] =
     HttpMethod.asGetOption(this.toString)

  def asPostOption : Option[Post.type] =
     HttpMethod.asPostOption(this.toString)

  def asPutOption : Option[Put.type] =
     HttpMethod.asPutOption(this.toString)

  def asDeleteOption : Option[Delete.type] =
     HttpMethod.asDeleteOption(this.toString)

  def asPatchOption : Option[Patch.type] =
     HttpMethod.asPatchOption(this.toString)

  def ifGet(fn: Get.type => Unit) : Unit =
    HttpMethod.ifGet(this.toString, fn)

  def ifPost(fn: Post.type => Unit) : Unit =
    HttpMethod.ifPost(this.toString, fn)

  def ifPut(fn: Put.type => Unit) : Unit =
    HttpMethod.ifPut(this.toString, fn)

  def ifDelete(fn: Delete.type => Unit) : Unit =
    HttpMethod.ifDelete(this.toString, fn)

  def ifPatch(fn: Patch.type => Unit) : Unit =
    HttpMethod.ifPatch(this.toString, fn)

  def asOneOf(selection: HttpMethod*) : Option[HttpMethod] =
    HttpMethod.asOneOf(this.toString, selection:_*)

  def ifOneOf(selection: HttpMethod*) : (HttpMethod => Unit) => Unit =
    HttpMethod.ifOneOf(this.toString, selection:_*)

  def toJson : String =
    Json.toJson(this)(HttpMethod.Writer).toString
}

object HttpMethod {
  case object Get extends HttpMethod
  case object Post extends HttpMethod
  case object Put extends HttpMethod
  case object Delete extends HttpMethod
  case object Patch extends HttpMethod

  val AllPossiblesValues: Set[String] = Set("GET", "POST", "PUT", "DELETE", "PATCH")

  def asGetOption(value: String) : Option[Get.type] =
    if(isGet(value)) Some(Get) else None

  def asPostOption(value: String) : Option[Post.type] =
    if(isPost(value)) Some(Post) else None

  def asPutOption(value: String) : Option[Put.type] =
    if(isPut(value)) Some(Put) else None

  def asDeleteOption(value: String) : Option[Delete.type] =
    if(isDelete(value)) Some(Delete) else None

  def asPatchOption(value: String) : Option[Patch.type] =
    if(isPatch(value)) Some(Patch) else None

  def ifGet(value: String, fn: Get.type => Unit) : Unit =
    if(isGet(value)) fn(Get)

  def ifPost(value: String, fn: Post.type => Unit) : Unit =
    if(isPost(value)) fn(Post)

  def ifPut(value: String, fn: Put.type => Unit) : Unit =
    if(isPut(value)) fn(Put)

  def ifDelete(value: String, fn: Delete.type => Unit) : Unit =
    if(isDelete(value)) fn(Delete)

  def ifPatch(value: String, fn: Patch.type => Unit) : Unit =
    if(isPatch(value)) fn(Patch)

  def asOneOf(value:String, selection: HttpMethod*) : Option[HttpMethod] =
    selection.find(_.toString == value)

  def ifOneOf(value: String, selection: HttpMethod*) : (HttpMethod => Unit) => Unit = {
    selection.find(_.toString == value) match {
      case Some(v) => fn => fn(v)
      case None    => _  => ()
    }
  }

  def isValid(value: String) : Boolean =
    AllPossiblesValues.contains(value)

  def isNotGet(value: String) : Boolean =
    value != Get.toString

  def isNotPost(value: String) : Boolean =
    value != Post.toString

  def isNotPut(value: String) : Boolean =
    value != Put.toString

  def isNotDelete(value: String) : Boolean =
    value != Delete.toString

  def isNotPatch(value: String) : Boolean =
    value != Patch.toString

  def ifNotGet(value: String, fn: HttpMethod => Unit) : Unit =
    if(isNotGet(value)) fn(Get)

  def ifNotPost(value: String, fn: HttpMethod => Unit) : Unit =
    if(isNotPost(value)) fn(Post)

  def ifNotPut(value: String, fn: HttpMethod => Unit) : Unit =
    if(isNotPut(value)) fn(Put)

  def ifNotDelete(value: String, fn: HttpMethod => Unit) : Unit =
    if(isNotDelete(value)) fn(Delete)

  def ifNotPatch(value: String, fn: HttpMethod => Unit) : Unit =
    if(isNotPatch(value)) fn(Patch)

  def isGet(value: String) : Boolean =
    value == Get.toString

  def isPost(value: String) : Boolean =
    value == Post.toString

  def isPut(value: String) : Boolean =
    value == Put.toString

  def isDelete(value: String) : Boolean =
    value == Delete.toString

  def isPatch(value: String) : Boolean =
    value == Patch.toString

  def fromString(value: String) : Option[HttpMethod] =
    value match {
      case "GET" => Some(Get)
      case "POST" => Some(Post)
      case "PUT" => Some(Put)
      case "DELETE" => Some(Delete)
      case "PATCH" => Some(Patch)
      case _ => None
    }

  implicit object Reader extends Reads[HttpMethod] {
    def reads(json: JsValue): JsResult[HttpMethod] = json match {
      case JsString(value) => fromString(value) match {
        case Some(httpMethod) => JsSuccess(httpMethod)
        case None => JsError(s"Invalid value '$value' submitted for HttpMethod")
      }
      case _ => JsError("JsString expected for ValueClass parameter")
    }
  }
  implicit object Writer extends Writes[HttpMethod] {
    def writes(o:HttpMethod): JsValue = JsString(o.toString) 
  }

  def fromJson(s: String) : HttpMethod =
    Json.parse(s).as[HttpMethod]
}