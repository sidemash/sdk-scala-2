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

import UpdateStreamSquareForm._
import play.api.libs.json._

case class UpdateStreamSquareForm(id: String,
                                  remove: Set[RemovableField] = Set.empty,
                                  set: Set[Edit] = Set.empty) {
  def toJson : String =
    Json.toJson(this)(UpdateStreamSquareForm.Writer).toString

  def toBody : Option[String] =
    Some(this.toJson)

  def removeDescription : UpdateStreamSquareForm = {
    val newRemove = this.remove ++ Set(RemovableField.Description)
    UpdateStreamSquareForm(id, newRemove, set)
  }

  def removeForeignData : UpdateStreamSquareForm = {
    val newRemove = this.remove ++ Set(RemovableField.ForeignData)
    UpdateStreamSquareForm(id, newRemove, set)
  }

  def setIsElastic(i: Boolean) : UpdateStreamSquareForm = {
    val newSet = this.set ++ Set(Edit.SetIsElastic(i))
    UpdateStreamSquareForm(id, remove, newSet)
  }

  def setIsElasticOption(i: Option[Boolean]) : UpdateStreamSquareForm =
    i match {
      case Some(newValue) => setIsElastic(newValue)
      case None => this
    }

  def setSize(s: StreamSquare.Size) : UpdateStreamSquareForm = {
    val newSet = this.set ++ Set(Edit.SetSize(s))
    UpdateStreamSquareForm(id, remove, newSet)
  }

  def setSizeOption(s: Option[StreamSquare.Size]) : UpdateStreamSquareForm =
    s match {
      case Some(newValue) => setSize(newValue)
      case None => this
    }

  def setHook(h: Hook) : UpdateStreamSquareForm = {
    val newSet = this.set ++ Set(Edit.SetHook(h))
    UpdateStreamSquareForm(id, remove, newSet)
  }

  def setHookOption(h: Option[Hook]) : UpdateStreamSquareForm =
    h match {
      case Some(newValue) => setHook(newValue)
      case None => this
    }

  def setDescription(d: String) : UpdateStreamSquareForm = {
    val newSet = this.set ++ Set(Edit.SetDescription(d))
    UpdateStreamSquareForm(id, remove, newSet)
  }

  def setDescriptionOption(d: Option[String]) : UpdateStreamSquareForm =
    d match {
      case Some(newValue) => setDescription(newValue)
      case None => this
    }

  def setForeignData(f: String) : UpdateStreamSquareForm = {
    val newSet = this.set ++ Set(Edit.SetForeignData(f))
    UpdateStreamSquareForm(id, remove, newSet)
  }

  def setForeignDataOption(f: Option[String]) : UpdateStreamSquareForm =
    f match {
      case Some(newValue) => setForeignData(newValue)
      case None => this
    }
}

object UpdateStreamSquareForm {
  sealed abstract class RemovableField
  object RemovableField {
    case object Description extends RemovableField { override def toString: String = "description" }
    case object ForeignData extends RemovableField { override def toString: String = "foreignData" }

    implicit object Writer extends Writes[RemovableField] {
      def writes(o:RemovableField): JsValue = JsString(o.toString)
    }
  }

  sealed abstract class EditableField
  object EditableField {
    case object IsElastic extends EditableField { override def toString: String = "isElastic" }
    case object Size extends EditableField { override def toString: String = "size" }
    case object Hook extends EditableField { override def toString: String = "hook" }
    case object Description extends EditableField { override def toString: String = "description" }
    case object ForeignData extends EditableField { override def toString: String = "foreignData" }

    implicit object Writer extends Writes[EditableField] {
      def writes(o:EditableField): JsValue = JsString(o.toString)
    }
  }

  sealed abstract class Edit(val field: EditableField)
  object Edit {
    case class SetIsElastic(newValue: Boolean) extends Edit(EditableField.IsElastic)
    case class SetSize(newValue: StreamSquare.Size) extends Edit(EditableField.Size)
    case class SetHook(newValue: Hook) extends Edit(EditableField.Hook)
    case class SetDescription(newValue: String) extends Edit(EditableField.Description)
    case class SetForeignData(newValue: String) extends Edit(EditableField.ForeignData)

    implicit object Writer extends Writes[Edit] {
      def writes(o:Edit): JsValue = o match {
        case SetIsElastic(newValue) => Json.obj(o.field.toString -> newValue)
        case SetSize(newValue) => Json.obj(o.field.toString -> newValue)
        case SetHook(newValue) => Json.obj(o.field.toString -> newValue)
        case SetDescription(newValue) => Json.obj(o.field.toString -> newValue)
        case SetForeignData(newValue) => Json.obj(o.field.toString -> newValue)
      }
    }
  }

  implicit val OWriter: OWrites[UpdateStreamSquareForm] = Json.writes[UpdateStreamSquareForm]
  implicit val Writer : Writes[UpdateStreamSquareForm]  = OWriter
}