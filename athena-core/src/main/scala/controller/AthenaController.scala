package com.ataraxer.athena
package controller

import storage._
import data._

import java.util.UUID


trait AthenaControllerService extends AthenaControllerComponent {
  this: AthenaDatabaseComponent =>

  val controller = new AthenaController {
    def createNote(name: String, text: String, tags: List[Tag]): Note = {
      val existingNotes = db.findNoteByText(text)

      if (existingNotes.isEmpty) {
        val id = UUID.randomUUID
        val note = Note(id, name, text, tags)
        db.saveNote(note)
        note
      } else {
        existingNotes.head
      }
    }
  }
}


// vim: set ts=2 sw=2 et:
