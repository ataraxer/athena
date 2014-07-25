package com.ataraxer.athena
package server

import akka.actor.ActorSystem
import spray.routing.SimpleRoutingApp


class AthenaServer extends SimpleRoutingApp {
  implicit val system = ActorSystem("server-system")

  val route = {
    path("add") {
      parameters('name, 'tags) { (name, tags) =>
        complete { "OK" }
      }
    } ~
    path("find") {
      parameters('text) { text =>
        complete { "OK" }
      }
    }
  }

  startServer(interface = "localhost", port = 8998)(route)
}


// vim: set ts=2 sw=2 et:
