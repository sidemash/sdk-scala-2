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

case class Timestamp(seconds: Long) {
  val _type: String = Timestamp.Type

  def toJson : String =
    Json.toJson(this)(Timestamp.Writer).toString
}

object Timestamp {
  val Type: String = "Timestamp"

  implicit val Reader : Reads[Timestamp]   = Json.reads[Timestamp]
  implicit val OWriter: OWrites[Timestamp] = Json.writes[Timestamp]
  implicit val Writer : Writes[Timestamp]  = OWriter

  def fromJson(s: String) : Timestamp =
    Json.parse(s).as[Timestamp]
}