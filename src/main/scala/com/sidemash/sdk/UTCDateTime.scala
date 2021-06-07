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

case class UTCDateTime(iso8601: String,
                       timestamp: Timestamp) {
  val _type: String = UTCDateTime.Type

  def toJson : String =
    Json.toJson(this)(UTCDateTime.Writer).toString
}

object UTCDateTime {
  val Type: String = "UTCDateTime"

  implicit val Reader : Reads[UTCDateTime]   = Json.reads[UTCDateTime]
  implicit val OWriter: OWrites[UTCDateTime] = Json.writes[UTCDateTime]
  implicit val Writer : Writes[UTCDateTime]  = OWriter

  def fromJson(s: String) : UTCDateTime =
    Json.parse(s).as[UTCDateTime]
}