package com.ataraxer.athena
package server

import storage._
import data._
import controller._

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
{ this: AthenaDatabaseComponent with AthenaControllerComponent =>

  import AthenaRouter._

  implicit val json4sFormats = Json.formats(NoTypeHints)


  val notePath = {
    path("add") {
      post {
        entity(as[Entry]) { entry =>
          complete {
            val tags = entry.tags.map( name => Tag(name, Nil) )

            val note = controller.createNote(
              entry.name,
              entry.text,
              tags)

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
        parameters('text.?, 'tag.?) { (text, tag) =>
          complete {
            if (text.isDefined && tag.isDefined) {
              ("error" -> "Illegal combination of parameters.")
            }

            text map { text =>
              db.findNote(text).map( _.toJson )
            }

            tag map { tag =>
              db.findNoteByTag(tag).map( _.toJson )
            }
          }
        }
      }
    }
  }

  val route = pathPrefix("note") { notePath }
}


// vim: set ts=2 sw=2 et:
