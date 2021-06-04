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

import play.api.libs.json._

sealed abstract class Hook {
  val _type: String

  def toJson : String =
    Json.toJson(this)(Hook.Writer).toString
}

object Hook {
  case class HttpCall(method: HttpMethod,
                      url: String) extends Hook {
    override val _type: String = HttpCall.Type
  }

  object HttpCall {
    val Type: String = "Hook.HttpCall"

    implicit val Reader : Reads[HttpCall]   = Json.reads[HttpCall]
    implicit val OWriter: OWrites[HttpCall] = Json.writes[HttpCall]
    implicit val Writer : Writes[HttpCall]  = OWriter

    def fromJson(s: String) : HttpCall =
      Json.parse(s).as[HttpCall]
  }

  case class WsCall(method: HttpMethod,
                    url: String) extends Hook {
    override val _type: String = WsCall.Type
  }

  object WsCall {
    val Type: String = "Hook.WsCall"

    implicit val Reader : Reads[WsCall]   = Json.reads[WsCall]
    implicit val OWriter: OWrites[WsCall] = Json.writes[WsCall]
    implicit val Writer : Writes[WsCall]  = OWriter

    def fromJson(s: String) : WsCall =
      Json.parse(s).as[WsCall]
  }

  implicit object Reader extends Reads[Hook] {
    override def reads(json: JsValue): JsResult[Hook] = json \ "_type" match {
      case JsDefined(JsString(value)) if value == HttpCall.Type => HttpCall.Reader.reads(json)
      case JsDefined(JsString(value)) if value == WsCall.Type => WsCall.Reader.reads(json)
    }
  }
  implicit object OWriter extends OWrites[Hook]  {
    override def writes(o: Hook): JsObject = o match {
      case hook:HttpCall => HttpCall.OWriter.writes(hook) ++ Json.obj("_type" -> HttpCall.Type)
      case hook:WsCall => WsCall.OWriter.writes(hook) ++ Json.obj("_type" -> WsCall.Type)
    }
  }
  implicit val Writer : Writes[Hook]  = OWriter

  def fromJson(s: String) : Hook =
    Json.parse(s).as[Hook]
}