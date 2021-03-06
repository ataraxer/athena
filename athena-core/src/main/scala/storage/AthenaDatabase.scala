package com.ataraxer.athena
package storage

import com.ataraxer.athena.data.{Note, Tag}


trait AthenaDatabaseComponent {
  def db: AthenaDatabase

  trait AthenaDatabase {
    def saveNote(note: Note): Unit
    def getNote(name: String): List[Note]
    def findNote(text: String): List[Note]
    def findNoteByTag(tag: String): List[Note]
    def findNoteByText(text: String): List[Note]
  }
}


// vim: set ts=2 sw=2 et:
