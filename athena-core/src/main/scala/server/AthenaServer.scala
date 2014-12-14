package com.ataraxer.athena
package server

import storage._
import data._

import akka.actor.ActorSystem
import spray.routing.SimpleRoutingApp
import java.util.UUID


class AthenaServer extends SimpleRoutingApp with MongoAdapter {
  implicit val system = ActorSystem("server-system")


  val notePath = {
    path("add") {
      post {
        entity(as[String]) { body =>
          parameters('name, 'tags) { (name, tags) =>
            complete {
              val id = UUID.randomUUID
              val tagsList = tags.split(',').toList map {
                name => Tag(name, Nil)
              }
              val note = Note(id, name, body, tagsList)
              db.saveNote(note)
              "OK"
            }
          }
        }
      }
    } ~
    path("get") {
      get {
        parameters('name) { name =>
          complete {
            db.getNote(name).toString
          }
        }
      }
    } ~
    path("find") {
      get {
        parameters('text) { text =>
          complete {
            db.findNote(text).toString
          }
        }
      }
    }
  }

  val route = pathPrefix("note") { notePath }

  startServer(interface = "localhost", port = 8998)(route)
}


// vim: set ts=2 sw=2 et:
