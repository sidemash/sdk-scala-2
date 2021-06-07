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

import StreamSquare.Size._
import play.api.libs.json._

case class StreamSquare(id: String,
                        url: String,
                        status: InstanceStatus,
                        isElastic: Boolean,
                        size: StreamSquare.Size,
                        playDomainName: Option[String] = None,
                        publishDomainName: Option[String] = None,
                        publish: Publish,
                        hook: Hook,
                        description: Option[String] = None,
                        foreignData: Option[String] = None) {
  val _type: String = StreamSquare.Type

  def toJson : String =
    Json.toJson(this)(StreamSquare.Writer).toString
}

object StreamSquare {
  sealed abstract class Size {
    def isNotS : Boolean =
      Size.isNotS(this.toString)

    def isNotM : Boolean =
      Size.isNotM(this.toString)

    def isNotL : Boolean =
      Size.isNotL(this.toString)

    def isNotXl : Boolean =
      Size.isNotXl(this.toString)

    def isNotXxl : Boolean =
      Size.isNotXxl(this.toString)

    def ifNotS(fn: Size => Unit) : Unit =
      Size.ifNotS(this.toString, fn)

    def ifNotM(fn: Size => Unit) : Unit =
      Size.ifNotM(this.toString, fn)

    def ifNotL(fn: Size => Unit) : Unit =
      Size.ifNotL(this.toString, fn)

    def ifNotXl(fn: Size => Unit) : Unit =
      Size.ifNotXl(this.toString, fn)

    def ifNotXxl(fn: Size => Unit) : Unit =
      Size.ifNotXxl(this.toString, fn)

    def isS : Boolean =
      Size.isS(this.toString)

    def isM : Boolean =
      Size.isM(this.toString)

    def isL : Boolean =
      Size.isL(this.toString)

    def isXl : Boolean =
      Size.isXl(this.toString)

    def isXxl : Boolean =
      Size.isXxl(this.toString)

    def asSOption : Option[S.type] =
       Size.asSOption(this.toString)

    def asMOption : Option[M.type] =
       Size.asMOption(this.toString)

    def asLOption : Option[L.type] =
       Size.asLOption(this.toString)

    def asXlOption : Option[Xl.type] =
       Size.asXlOption(this.toString)

    def asXxlOption : Option[Xxl.type] =
       Size.asXxlOption(this.toString)

    def ifS(fn: S.type => Unit) : Unit =
      Size.ifS(this.toString, fn)

    def ifM(fn: M.type => Unit) : Unit =
      Size.ifM(this.toString, fn)

    def ifL(fn: L.type => Unit) : Unit =
      Size.ifL(this.toString, fn)

    def ifXl(fn: Xl.type => Unit) : Unit =
      Size.ifXl(this.toString, fn)

    def ifXxl(fn: Xxl.type => Unit) : Unit =
      Size.ifXxl(this.toString, fn)

    def asOneOf(selection: Size*) : Option[StreamSquare.Size] =
      Size.asOneOf(this.toString, selection:_*)

    def ifOneOf(selection: Size*) : (StreamSquare.Size => Unit) => Unit =
      Size.ifOneOf(this.toString, selection:_*)

    def toJson : String =
      Json.toJson(this)(StreamSquare.Size.Writer).toString
  }

  object Size {
    case object S extends Size
    case object M extends Size
    case object L extends Size
    case object Xl extends Size
    case object Xxl extends Size

    val AllPossiblesValues: Set[String] = Set("S", "M", "L", "XL", "XXL")

    def asSOption(value: String) : Option[S.type] =
      if(isS(value)) Some(S) else None

    def asMOption(value: String) : Option[M.type] =
      if(isM(value)) Some(M) else None

    def asLOption(value: String) : Option[L.type] =
      if(isL(value)) Some(L) else None

    def asXlOption(value: String) : Option[Xl.type] =
      if(isXl(value)) Some(Xl) else None

    def asXxlOption(value: String) : Option[Xxl.type] =
      if(isXxl(value)) Some(Xxl) else None

    def ifS(value: String, fn: S.type => Unit) : Unit =
      if(isS(value)) fn(S)

    def ifM(value: String, fn: M.type => Unit) : Unit =
      if(isM(value)) fn(M)

    def ifL(value: String, fn: L.type => Unit) : Unit =
      if(isL(value)) fn(L)

    def ifXl(value: String, fn: Xl.type => Unit) : Unit =
      if(isXl(value)) fn(Xl)

    def ifXxl(value: String, fn: Xxl.type => Unit) : Unit =
      if(isXxl(value)) fn(Xxl)

    def asOneOf(value:String, selection: Size*) : Option[StreamSquare.Size] =
      selection.find(_.toString == value)

    def ifOneOf(value: String, selection: Size*) : (StreamSquare.Size => Unit) => Unit = {
      selection.find(_.toString == value) match {
        case Some(v) => fn => fn(v)
        case None    => _  => ()
      }
    }

    def isValid(value: String) : Boolean =
      AllPossiblesValues.contains(value)

    def isNotS(value: String) : Boolean =
      value != S.toString

    def isNotM(value: String) : Boolean =
      value != M.toString

    def isNotL(value: String) : Boolean =
      value != L.toString

    def isNotXl(value: String) : Boolean =
      value != Xl.toString

    def isNotXxl(value: String) : Boolean =
      value != Xxl.toString

    def ifNotS(value: String, fn: Size => Unit) : Unit =
      if(isNotS(value)) fn(S)

    def ifNotM(value: String, fn: Size => Unit) : Unit =
      if(isNotM(value)) fn(M)

    def ifNotL(value: String, fn: Size => Unit) : Unit =
      if(isNotL(value)) fn(L)

    def ifNotXl(value: String, fn: Size => Unit) : Unit =
      if(isNotXl(value)) fn(Xl)

    def ifNotXxl(value: String, fn: Size => Unit) : Unit =
      if(isNotXxl(value)) fn(Xxl)

    def isS(value: String) : Boolean =
      value == S.toString

    def isM(value: String) : Boolean =
      value == M.toString

    def isL(value: String) : Boolean =
      value == L.toString

    def isXl(value: String) : Boolean =
      value == Xl.toString

    def isXxl(value: String) : Boolean =
      value == Xxl.toString

    def fromString(value: String) : Option[StreamSquare.Size] =
      value match {
        case "S" => Some(S)
        case "M" => Some(M)
        case "L" => Some(L)
        case "XL" => Some(Xl)
        case "XXL" => Some(Xxl)
        case _ => None
      }

    implicit object Reader extends Reads[StreamSquare.Size] {
      def reads(json: JsValue): JsResult[StreamSquare.Size] = json match {
        case JsString(value) => fromString(value) match {
          case Some(size) => JsSuccess(size)
          case None => JsError(s"Invalid value '$value' submitted for StreamSquare.Size")
        }
        case _ => JsError("JsString expected for ValueClass parameter")
      }
    }
    implicit object Writer extends Writes[StreamSquare.Size] {
      def writes(o:StreamSquare.Size): JsValue = JsString(o.toString) 
    }

    def fromJson(s: String) : StreamSquare.Size =
      Json.parse(s).as[StreamSquare.Size]
  }

  val Type: String = "StreamSquare"

  implicit val Reader : Reads[StreamSquare]   = Json.reads[StreamSquare]
  implicit val OWriter: OWrites[StreamSquare] = Json.writes[StreamSquare]
  implicit val Writer : Writes[StreamSquare]  = OWriter

  def fromJson(s: String) : StreamSquare =
    Json.parse(s).as[StreamSquare]
}