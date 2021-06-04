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

case class SecureAndNonSecure(secure: String,
                              nonSecureOn80: String,
                              nonSecure: String) {
  val _type: String = SecureAndNonSecure.Type

  def toJson : String =
    Json.toJson(this)(SecureAndNonSecure.Writer).toString
}

object SecureAndNonSecure {
  val Type: String = "SecureAndNonSecure"

  implicit val Reader : Reads[SecureAndNonSecure]   = Json.reads[SecureAndNonSecure]
  implicit val OWriter: OWrites[SecureAndNonSecure] = Json.writes[SecureAndNonSecure]
  implicit val Writer : Writes[SecureAndNonSecure]  = OWriter

  def fromJson(s: String) : SecureAndNonSecure =
    Json.parse(s).as[SecureAndNonSecure]
}