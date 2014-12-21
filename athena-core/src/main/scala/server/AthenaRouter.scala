package com.ataraxer.athena
package server

import storage._
import data._

import akka.actor.ActorSystem

import spray.routing.SimpleRoutingApp
import spray.httpx.Json4sSupport

import org.json4s.NoTypeHints
import org.json4s.native.{Serialization => Json}


import java.util.UUID


object AthenaRouter {
  case class Entry(name: String, text: String, tags: List[String])
}


trait AthenaRouter
  extends SimpleRoutingApp
  with Json4sSupport
{ this: AthenaDatabaseComponent =>

  import AthenaRouter._

  implicit val json4sFormats = Json.formats(NoTypeHints)


  val notePath = {
    path("add") {
      post {
        entity(as[Entry]) { entry =>
          complete {
            val id = UUID.randomUUID
            val tags = entry.tags.map( name => Tag(name, Nil) )
            val note = Note(id, entry.name, entry.text, tags)
            db.saveNote(note)
            note.toJson
          }
        }
      }
    } ~
    path("get") {
      get {
        parameters('name) { name =>
          complete {
            db.getNote(name).map( _.toJson )
          }
        }
      }
    } ~
    path("find") {
      get {
        parameters('text) { text =>
          complete {
            db.findNote(text).map( _.toJson )
          }
        }
      }
    }
  }

  val route = pathPrefix("note") { notePath }
}


// vim: set ts=2 sw=2 et:
