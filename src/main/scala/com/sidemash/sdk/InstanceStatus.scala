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

import InstanceStatus._
import play.api.libs.json._

sealed abstract class InstanceStatus {
  def isNotInitializing : Boolean =
    InstanceStatus.isNotInitializing(this.toString)

  def isNotRunning : Boolean =
    InstanceStatus.isNotRunning(this.toString)

  def isNotRestarting : Boolean =
    InstanceStatus.isNotRestarting(this.toString)

  def isNotUpdating : Boolean =
    InstanceStatus.isNotUpdating(this.toString)

  def isNotMaintaining : Boolean =
    InstanceStatus.isNotMaintaining(this.toString)

  def isNotPartiallyAvailable : Boolean =
    InstanceStatus.isNotPartiallyAvailable(this.toString)

  def isNotNotAvailable : Boolean =
    InstanceStatus.isNotNotAvailable(this.toString)

  def isNotTerminating : Boolean =
    InstanceStatus.isNotTerminating(this.toString)

  def isNotTerminated : Boolean =
    InstanceStatus.isNotTerminated(this.toString)

  def ifNotInitializing(fn: InstanceStatus => Unit) : Unit =
    InstanceStatus.ifNotInitializing(this.toString, fn)

  def ifNotRunning(fn: InstanceStatus => Unit) : Unit =
    InstanceStatus.ifNotRunning(this.toString, fn)

  def ifNotRestarting(fn: InstanceStatus => Unit) : Unit =
    InstanceStatus.ifNotRestarting(this.toString, fn)

  def ifNotUpdating(fn: InstanceStatus => Unit) : Unit =
    InstanceStatus.ifNotUpdating(this.toString, fn)

  def ifNotMaintaining(fn: InstanceStatus => Unit) : Unit =
    InstanceStatus.ifNotMaintaining(this.toString, fn)

  def ifNotPartiallyAvailable(fn: InstanceStatus => Unit) : Unit =
    InstanceStatus.ifNotPartiallyAvailable(this.toString, fn)

  def ifNotNotAvailable(fn: InstanceStatus => Unit) : Unit =
    InstanceStatus.ifNotNotAvailable(this.toString, fn)

  def ifNotTerminating(fn: InstanceStatus => Unit) : Unit =
    InstanceStatus.ifNotTerminating(this.toString, fn)

  def ifNotTerminated(fn: InstanceStatus => Unit) : Unit =
    InstanceStatus.ifNotTerminated(this.toString, fn)

  def isInitializing : Boolean =
    InstanceStatus.isInitializing(this.toString)

  def isRunning : Boolean =
    InstanceStatus.isRunning(this.toString)

  def isRestarting : Boolean =
    InstanceStatus.isRestarting(this.toString)

  def isUpdating : Boolean =
    InstanceStatus.isUpdating(this.toString)

  def isMaintaining : Boolean =
    InstanceStatus.isMaintaining(this.toString)

  def isPartiallyAvailable : Boolean =
    InstanceStatus.isPartiallyAvailable(this.toString)

  def isNotAvailable : Boolean =
    InstanceStatus.isNotAvailable(this.toString)

  def isTerminating : Boolean =
    InstanceStatus.isTerminating(this.toString)

  def isTerminated : Boolean =
    InstanceStatus.isTerminated(this.toString)

  def asInitializingOption : Option[Initializing.type] =
     InstanceStatus.asInitializingOption(this.toString)

  def asRunningOption : Option[Running.type] =
     InstanceStatus.asRunningOption(this.toString)

  def asRestartingOption : Option[Restarting.type] =
     InstanceStatus.asRestartingOption(this.toString)

  def asUpdatingOption : Option[Updating.type] =
     InstanceStatus.asUpdatingOption(this.toString)

  def asMaintainingOption : Option[Maintaining.type] =
     InstanceStatus.asMaintainingOption(this.toString)

  def asPartiallyAvailableOption : Option[PartiallyAvailable.type] =
     InstanceStatus.asPartiallyAvailableOption(this.toString)

  def asNotAvailableOption : Option[NotAvailable.type] =
     InstanceStatus.asNotAvailableOption(this.toString)

  def asTerminatingOption : Option[Terminating.type] =
     InstanceStatus.asTerminatingOption(this.toString)

  def asTerminatedOption : Option[Terminated.type] =
     InstanceStatus.asTerminatedOption(this.toString)

  def ifInitializing(fn: Initializing.type => Unit) : Unit =
    InstanceStatus.ifInitializing(this.toString, fn)

  def ifRunning(fn: Running.type => Unit) : Unit =
    InstanceStatus.ifRunning(this.toString, fn)

  def ifRestarting(fn: Restarting.type => Unit) : Unit =
    InstanceStatus.ifRestarting(this.toString, fn)

  def ifUpdating(fn: Updating.type => Unit) : Unit =
    InstanceStatus.ifUpdating(this.toString, fn)

  def ifMaintaining(fn: Maintaining.type => Unit) : Unit =
    InstanceStatus.ifMaintaining(this.toString, fn)

  def ifPartiallyAvailable(fn: PartiallyAvailable.type => Unit) : Unit =
    InstanceStatus.ifPartiallyAvailable(this.toString, fn)

  def ifNotAvailable(fn: NotAvailable.type => Unit) : Unit =
    InstanceStatus.ifNotAvailable(this.toString, fn)

  def ifTerminating(fn: Terminating.type => Unit) : Unit =
    InstanceStatus.ifTerminating(this.toString, fn)

  def ifTerminated(fn: Terminated.type => Unit) : Unit =
    InstanceStatus.ifTerminated(this.toString, fn)

  def asOneOf(selection: InstanceStatus*) : Option[InstanceStatus] =
    InstanceStatus.asOneOf(this.toString, selection:_*)

  def ifOneOf(selection: InstanceStatus*) : (InstanceStatus => Unit) => Unit =
    InstanceStatus.ifOneOf(this.toString, selection:_*)

  def toJson : String =
    Json.toJson(this)(InstanceStatus.Writer).toString
}

object InstanceStatus {
  case object Initializing extends InstanceStatus
  case object Running extends InstanceStatus
  case object Restarting extends InstanceStatus
  case object Updating extends InstanceStatus
  case object Maintaining extends InstanceStatus
  case object PartiallyAvailable extends InstanceStatus
  case object NotAvailable extends InstanceStatus
  case object Terminating extends InstanceStatus
  case object Terminated extends InstanceStatus

  val AllPossiblesValues: Set[String] =
    Set("Initializing",
        "Running",
        "Restarting",
        "Updating",
        "Maintaining",
        "PartiallyAvailable",
        "NotAvailable",
        "Terminating",
        "Terminated")

  def asInitializingOption(value: String) : Option[Initializing.type] =
    if(isInitializing(value)) Some(Initializing) else None

  def asRunningOption(value: String) : Option[Running.type] =
    if(isRunning(value)) Some(Running) else None

  def asRestartingOption(value: String) : Option[Restarting.type] =
    if(isRestarting(value)) Some(Restarting) else None

  def asUpdatingOption(value: String) : Option[Updating.type] =
    if(isUpdating(value)) Some(Updating) else None

  def asMaintainingOption(value: String) : Option[Maintaining.type] =
    if(isMaintaining(value)) Some(Maintaining) else None

  def asPartiallyAvailableOption(value: String) : Option[PartiallyAvailable.type] =
    if(isPartiallyAvailable(value)) Some(PartiallyAvailable) else None

  def asNotAvailableOption(value: String) : Option[NotAvailable.type] =
    if(isNotAvailable(value)) Some(NotAvailable) else None

  def asTerminatingOption(value: String) : Option[Terminating.type] =
    if(isTerminating(value)) Some(Terminating) else None

  def asTerminatedOption(value: String) : Option[Terminated.type] =
    if(isTerminated(value)) Some(Terminated) else None

  def ifInitializing(value: String, fn: Initializing.type => Unit) : Unit =
    if(isInitializing(value)) fn(Initializing)

  def ifRunning(value: String, fn: Running.type => Unit) : Unit =
    if(isRunning(value)) fn(Running)

  def ifRestarting(value: String, fn: Restarting.type => Unit) : Unit =
    if(isRestarting(value)) fn(Restarting)

  def ifUpdating(value: String, fn: Updating.type => Unit) : Unit =
    if(isUpdating(value)) fn(Updating)

  def ifMaintaining(value: String, fn: Maintaining.type => Unit) : Unit =
    if(isMaintaining(value)) fn(Maintaining)

  def ifPartiallyAvailable(value: String, fn: PartiallyAvailable.type => Unit) : Unit =
    if(isPartiallyAvailable(value)) fn(PartiallyAvailable)

  def ifNotAvailable(value: String, fn: NotAvailable.type => Unit) : Unit =
    if(isNotAvailable(value)) fn(NotAvailable)

  def ifTerminating(value: String, fn: Terminating.type => Unit) : Unit =
    if(isTerminating(value)) fn(Terminating)

  def ifTerminated(value: String, fn: Terminated.type => Unit) : Unit =
    if(isTerminated(value)) fn(Terminated)

  def asOneOf(value:String, selection: InstanceStatus*) : Option[InstanceStatus] =
    selection.find(_.toString == value)

  def ifOneOf(value: String, selection: InstanceStatus*) : (InstanceStatus => Unit) => Unit = {
    selection.find(_.toString == value) match {
      case Some(v) => fn => fn(v)
      case None    => _  => ()
    }
  }

  def isValid(value: String) : Boolean =
    AllPossiblesValues.contains(value)

  def isNotInitializing(value: String) : Boolean =
    value != Initializing.toString

  def isNotRunning(value: String) : Boolean =
    value != Running.toString

  def isNotRestarting(value: String) : Boolean =
    value != Restarting.toString

  def isNotUpdating(value: String) : Boolean =
    value != Updating.toString

  def isNotMaintaining(value: String) : Boolean =
    value != Maintaining.toString

  def isNotPartiallyAvailable(value: String) : Boolean =
    value != PartiallyAvailable.toString

  def isNotNotAvailable(value: String) : Boolean =
    value != NotAvailable.toString

  def isNotTerminating(value: String) : Boolean =
    value != Terminating.toString

  def isNotTerminated(value: String) : Boolean =
    value != Terminated.toString

  def ifNotInitializing(value: String, fn: InstanceStatus => Unit) : Unit =
    if(isNotInitializing(value)) fn(Initializing)

  def ifNotRunning(value: String, fn: InstanceStatus => Unit) : Unit =
    if(isNotRunning(value)) fn(Running)

  def ifNotRestarting(value: String, fn: InstanceStatus => Unit) : Unit =
    if(isNotRestarting(value)) fn(Restarting)

  def ifNotUpdating(value: String, fn: InstanceStatus => Unit) : Unit =
    if(isNotUpdating(value)) fn(Updating)

  def ifNotMaintaining(value: String, fn: InstanceStatus => Unit) : Unit =
    if(isNotMaintaining(value)) fn(Maintaining)

  def ifNotPartiallyAvailable(value: String, fn: InstanceStatus => Unit) : Unit =
    if(isNotPartiallyAvailable(value)) fn(PartiallyAvailable)

  def ifNotNotAvailable(value: String, fn: InstanceStatus => Unit) : Unit =
    if(isNotNotAvailable(value)) fn(NotAvailable)

  def ifNotTerminating(value: String, fn: InstanceStatus => Unit) : Unit =
    if(isNotTerminating(value)) fn(Terminating)

  def ifNotTerminated(value: String, fn: InstanceStatus => Unit) : Unit =
    if(isNotTerminated(value)) fn(Terminated)

  def isInitializing(value: String) : Boolean =
    value == Initializing.toString

  def isRunning(value: String) : Boolean =
    value == Running.toString

  def isRestarting(value: String) : Boolean =
    value == Restarting.toString

  def isUpdating(value: String) : Boolean =
    value == Updating.toString

  def isMaintaining(value: String) : Boolean =
    value == Maintaining.toString

  def isPartiallyAvailable(value: String) : Boolean =
    value == PartiallyAvailable.toString

  def isNotAvailable(value: String) : Boolean =
    value == NotAvailable.toString

  def isTerminating(value: String) : Boolean =
    value == Terminating.toString

  def isTerminated(value: String) : Boolean =
    value == Terminated.toString

  def fromString(value: String) : Option[InstanceStatus] =
    value match {
      case "Initializing" => Some(Initializing)
      case "Running" => Some(Running)
      case "Restarting" => Some(Restarting)
      case "Updating" => Some(Updating)
      case "Maintaining" => Some(Maintaining)
      case "PartiallyAvailable" => Some(PartiallyAvailable)
      case "NotAvailable" => Some(NotAvailable)
      case "Terminating" => Some(Terminating)
      case "Terminated" => Some(Terminated)
      case _ => None
    }

  implicit object Reader extends Reads[InstanceStatus] {
    def reads(json: JsValue): JsResult[InstanceStatus] = json match {
      case JsString(value) => fromString(value) match {
        case Some(instanceStatus) => JsSuccess(instanceStatus)
        case None => JsError(s"Invalid value '$value' submitted for InstanceStatus")
      }
      case _ => JsError("JsString expected for ValueClass parameter")
    }
  }
  implicit object Writer extends Writes[InstanceStatus] {
    def writes(o:InstanceStatus): JsValue = JsString(o.toString) 
  }

  def fromJson(s: String) : InstanceStatus =
    Json.parse(s).as[InstanceStatus]
}