package org.pongmatcher

class Result {
    String winnerId
    String loserId

    static constraints = {
    }

    static def involvingPlayer(playerId) {
        list().findAll { result ->
            [result.winnerId, result.loserId].contains(playerId)
        }
    }

    static def previousOpponents(playerId) {
        involvingPlayer(playerId).collect { result ->
            result.winnerId == playerId ? result.loserId : result.winnerId
        }
    }

}
