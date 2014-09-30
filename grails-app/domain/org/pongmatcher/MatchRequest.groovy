package org.pongmatcher

class MatchRequest {

    String id

    static mapping = {
      id column: "uuid", generator: "assigned"
    }

    static constraints = {
    }
}
