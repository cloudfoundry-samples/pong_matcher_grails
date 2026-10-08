package org.pongmatcher

class MatchRequest {
    String id
    String requesterId
    Date dateCreated

    static mapping = {
        id column: "uuid", generator: "assigned"
    }

    static List<MatchRequest> unfulfilled() {
        list(sort: "dateCreated", order: "asc").findAll { it.match() == null }
    }

    static MatchRequest firstOpen(String playerId) {
        def inappropriateOpponentIds = [playerId] + Result.previousOpponents(playerId)
        unfulfilled().find { matchRequest ->
            !inappropriateOpponentIds.contains(matchRequest.requesterId)
        }
    }

    Match match() {
        Match.findByMatchRequest1OrMatchRequest2(this, this)
    }

    Map toJson() {
        [
            id      : id,
            player  : requesterId,
            match_id: match()?.id
        ]
    }
}
