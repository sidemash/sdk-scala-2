package com.sidemash.sdk

import play.api.libs.json.{Json, OWrites, Reads, Writes}

case class RestCollection[A:Reads:Writes](url: String,
                                          pagination: Pagination,
                                          items: List[A]){
  def toJson : String =
    Json.toJson(this).toString
}

object RestCollection {
  implicit def reader[A:Reads:Writes] : Reads[RestCollection[A]] =
    Json.reads[RestCollection[A]]

  implicit def owriter[A:Reads:Writes] : OWrites[RestCollection[A]] =
    Json.writes[RestCollection[A]]

  implicit def writer[A:Reads:Writes] : Writes[RestCollection[A]] =
    Json.writes[RestCollection[A]]

  def fromJson(s: String) : Client =
    Json.parse(s).as[Client]
}