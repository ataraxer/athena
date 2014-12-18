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


trait AthenaRouter
  extends SimpleRoutingApp
  with Json4sSupport
{
  this: AthenaDatabaseComponent =>

  implicit val json4sFormats = Json.formats(NoTypeHints)


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

              ("note" ->
                ("name" -> name) ~
                ("id" -> id) ~
                ("tags" -> tagsList) ~
                ("text" -> body))
            }
          }
        }
      }
    } ~
    path("get") {
      get {
        parameters('name) { name =>
          complete {
            db.getNote(name) map { note =>
              ("note" ->
                ("name" -> note.name) ~
                ("id" -> note.id) ~
                ("tags" -> note.tags) ~
                ("text" -> note.body))
            }
          }
        }
      }
    } ~
    path("find") {
      get {
        parameters('text) { text =>
          complete {
            db.findNote(text) map { note =>
              ("note" ->
                ("name" -> note.name) ~
                ("id" -> note.id) ~
                ("tags" -> note.tags) ~
                ("text" -> note.body))
            }
          }
        }
      }
    }
  }

  val route = pathPrefix("note") { notePath }
}


// vim: set ts=2 sw=2 et:
