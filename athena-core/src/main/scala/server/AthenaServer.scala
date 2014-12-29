package com.ataraxer.athena
package server

import storage._
import data._
import controller._

import akka.actor.ActorSystem
import spray.routing.SimpleRoutingApp
import java.util.UUID


class AthenaServer
  extends AthenaRouter
  with AthenaControllerService
  with MongoAdapter
{
  implicit val system = ActorSystem("server-system")
  startServer(interface = "localhost", port = 8998)(route)
}


// vim: set ts=2 sw=2 et:
