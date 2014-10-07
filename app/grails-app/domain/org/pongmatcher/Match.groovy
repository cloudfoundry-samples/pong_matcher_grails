package org.pongmatcher

class Match {
    MatchRequest matchRequest1
    MatchRequest matchRequest2
    Date proposedDate

    static constraints = {
    }

    static mapping = {
        table "`match`"
    }
}
