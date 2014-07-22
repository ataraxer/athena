package com.ataraxer.athena
package data

import java.util.UUID


case class Tag(name: String, tags: List[Tag])

case class Note(id: UUID, name: String, body: String, tags: List[Tag])


// vim: set ts=2 sw=2 et:
