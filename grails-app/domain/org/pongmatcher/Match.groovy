package org.pongmatcher

class Match {
    MatchRequest matchRequest1
    MatchRequest matchRequest2

    static mapping = {
        // "match" is a reserved word in several databases
        table "pong_match"
    }

    Map toJson() {
        [
            id                : id,
            match_request_1_id: matchRequest1?.id,
            match_request_2_id: matchRequest2?.id
        ]
    }
}
