package com.ataraxer.athena
package storage

import com.ataraxer.athena.data.{Note, Tag}
import com.mongodb.casbah.Imports._
import com.novus.salat._
import com.novus.salat.global._


trait MongoAdapter extends AthenaDatabaseComponent {
  val db = new AthenaDatabase {
    private val mongoClient = MongoClient()
    private val db = mongoClient("athena")
    private val notes = db("notes")

    notes.ensureIndex(MongoDBObject("body" -> "text"))

    def saveNote(note: Note) = {
      notes.insert(grater[Note].asDBObject(note))
    }

    def getNote(name: String) = {
      val filter = MongoDBObject("name" -> name)
      notes.find(filter).toList.map(grater[Note].asObject(_))
    }

    def findNote(text: String) = {
      notes.find($text(text)).toList.map(grater[Note].asObject(_))
    }

    def findNoteByTag(tag: String) = {
      val filter = "tag" $all tag
      notes.find(filter).toList.map(grater[Note].asObject(_))
    }
  }
}


// vim: set ts=2 sw=2 et:
