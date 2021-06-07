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

case class Pagination(selfUrl: String,
                      prevUrl: Option[String] = None,
                      nextUrl: Option[String] = None,
                      startedTime: UTCDateTime,
                      nbItemsOnThisPage: Int,
                      page: Int,
                      nbItemsPerPage: Int) {
  val _type: String = Pagination.Type

  def toJson : String =
    Json.toJson(this)(Pagination.Writer).toString
}

object Pagination {
  val Type: String = "Pagination"

  implicit val Reader : Reads[Pagination]   = Json.reads[Pagination]
  implicit val OWriter: OWrites[Pagination] = Json.writes[Pagination]
  implicit val Writer : Writes[Pagination]  = OWriter

  def fromJson(s: String) : Pagination =
    Json.parse(s).as[Pagination]
}