package com.ataraxer.athena
package data

import org.json4s._
import org.json4s.JsonDSL._

import java.util.UUID


case class Tag(name: String, tags: List[Tag]) {
  def toJson: JValue = {
    ("tag" ->
      ("name" -> name) ~
      ("tags" -> tags.map( _.toJson )))
  }
}


case class Note(id: UUID, name: String, body: String, tags: List[Tag]) {
  def toJson = {
    ("note" ->
      ("name" -> name) ~
      ("id" -> id.toString) ~
      ("tags" -> tags.map( _.toJson )) ~
      ("text" -> body))
  }
}


// vim: set ts=2 sw=2 et:
