package com.ataraxer.athena
package controller

import storage.AthenaDatabaseComponent
import data._


trait AthenaControllerComponent {
  this: AthenaDatabaseComponent =>

  def controller: AthenaController

  trait AthenaController {
    def createNote(name: String, text: String, tags: List[Tag]): Note
  }
}


// vim: set ts=2 sw=2 et:
