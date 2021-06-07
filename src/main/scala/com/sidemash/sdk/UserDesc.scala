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

case class UserDesc(todo: Int) {
  val _type: String = UserDesc.Type

  def toJson : String =
    Json.toJson(this)(UserDesc.Writer).toString
}

object UserDesc {
  val Type: String = "UserDesc"

  implicit val Reader : Reads[UserDesc]   = Json.reads[UserDesc]
  implicit val OWriter: OWrites[UserDesc] = Json.writes[UserDesc]
  implicit val Writer : Writes[UserDesc]  = OWriter

  def fromJson(s: String) : UserDesc =
    Json.parse(s).as[UserDesc]
}