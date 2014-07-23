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

    def saveNote(note: Note) = {
      notes.insert(grater[Note].asDBObject(note))
    }

    def getNote(id: String) = {
      val filter = MongoDBObject("id" -> id)
      notes.find(filter).toList.map(grater[Note].asObject(_))
    }
  }
}


// vim: set ts=2 sw=2 et:
