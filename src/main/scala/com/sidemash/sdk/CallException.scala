package com.sidemash.sdk

case class CallException(statusCode:Int, statusMessage:String, body:String) extends Exception(statusCode + " " + statusMessage + "\n" + body)
