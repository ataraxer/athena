package com.ataraxer.athena
package storage

import com.ataraxer.athena.data.{Note, Tag}
import com.mongodb.casbah.Imports._


trait MongoAdapter extends AthenaDatabaseComponent {
  val db = new AthenaDatabase {
    private val mongoClient = MongoClient()
    private val db = mongoClient("athena")
    private val notes = db("notes")

    def saveNote(note: Note) = {
      val builder = MongoDBObject.newBuilder
      builder += "id"   -> note.id.toString
      builder += "name" -> note.name
      builder += "body" -> note.body
      builder += "tags" -> note.tags.map(_.toString)
      notes.insert(builder.result)
    }

    def getNote(id: String) = {
      val filter = MongoDBObject("id" -> id)
      notes.find(filter).toList.map(_.toString)
    }
  }
}


// vim: set ts=2 sw=2 et:
