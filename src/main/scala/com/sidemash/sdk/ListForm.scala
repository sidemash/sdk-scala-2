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

case class ListForm(where: Option[String] = None,
                    limit: Option[Int] = None,
                    orderBy: Option[String] = None) {
def toBody : Option[String] =
  Some(this.toJson)

  def toJson : String =
    Json.toJson(this)(ListForm.Writer).toString

  def toQueryString: List[(String, String)] =
    this.where.map(w => ("where", w)).toList ++
      this.orderBy.map(o => ("orderBy", o)).toList ++
      this.limit.map(l => ("limit", l.toString)).toList
}

object ListForm {
  implicit val Reader : Reads[ListForm]   = Json.reads[ListForm]
  implicit val OWriter: OWrites[ListForm] = Json.writes[ListForm]
  implicit val Writer : Writes[ListForm]  = OWriter

  def fromJson(s: String) : ListForm =
    Json.parse(s).as[ListForm]
}