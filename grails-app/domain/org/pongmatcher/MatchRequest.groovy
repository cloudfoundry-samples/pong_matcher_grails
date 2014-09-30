package org.pongmatcher

import org.pongmatcher.Match

class MatchRequest {
    String id
    String requesterId

    static mapping = {
        id column: "uuid", generator: "assigned"
    }

    Match match() {
        def request = this
        def query = Match.where {
            matchRequest1 == request || matchRequest2 == request
        }
        query.find()
    }
}
