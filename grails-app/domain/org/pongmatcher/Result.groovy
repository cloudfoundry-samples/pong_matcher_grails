package org.pongmatcher

class Result {
    String winnerId
    String loserId
    Match match

    static constraints = {
        match nullable: true
    }

    static List<Result> involvingPlayer(String playerId) {
        findAllByWinnerIdOrLoserId(playerId, playerId)
    }

    static List<String> previousOpponents(String playerId) {
        involvingPlayer(playerId).collect { result ->
            result.winnerId == playerId ? result.loserId : result.winnerId
        }
    }
}
