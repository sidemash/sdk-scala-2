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

case class PublishRtmp(streamKeyPrefix: String,
                       ip: SecureAndNonSecure,
                       domain: SecureAndNonSecure) {
  val _type: String = PublishRtmp.Type

  def toJson : String =
    Json.toJson(this)(PublishRtmp.Writer).toString
}

object PublishRtmp {
  val Type: String = "PublishRtmp"

  implicit val Reader : Reads[PublishRtmp]   = Json.reads[PublishRtmp]
  implicit val OWriter: OWrites[PublishRtmp] = Json.writes[PublishRtmp]
  implicit val Writer : Writes[PublishRtmp]  = OWriter

  def fromJson(s: String) : PublishRtmp =
    Json.parse(s).as[PublishRtmp]
}