package org.pongmatcher

import org.pongmatcher.Match

class MatchRequest {
    static hasOne = [match: Match]
    String id
    String requesterId

    static mapping = {
        id column: "uuid", generator: "assigned"
    }
}
