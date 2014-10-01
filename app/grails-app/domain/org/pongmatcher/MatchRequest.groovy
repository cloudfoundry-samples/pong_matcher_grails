package org.pongmatcher

import org.pongmatcher.Match

class MatchRequest {
    String id
    String requesterId

    static mapping = {
        id column: "uuid", generator: "assigned"
    }

    static def unfulfilled() {
        list().findAll { it.match() == null }
    }

    static MatchRequest firstOpen(playerId) {
        def inappropriateOpponentIds =
            [playerId] + Result.previousOpponents(playerId)
        unfulfilled().find { matchRequest ->
            !inappropriateOpponentIds.contains(matchRequest.requesterId)
        }
    }

    Match match() {
        MatchRequest request = this
        def query = Match.where {
            matchRequest1 == request || matchRequest2 == request
        }
        query.find()
    }
}
