package org.pongmatcher

class Result {
    String winnerId
    String loserId

    static def involvingPlayer(playerId) {
        where {
            [winnerId, loserId].contains(playerId)
        }
    }

    static def previousOpponents(playerId) {
        involvingPlayer(playerId).collect { result ->
            result.winnerId == playerId ? result.loserId : result.winnerId
        }
    }

}
