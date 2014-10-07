package org.pongmatcher

class Match {
    MatchRequest matchRequest1
    MatchRequest matchRequest2
    Date proposedDate

    static constraints = {
        proposedDate blank: true, nullable: true
    }

    static mapping = {
        table "`match`"
    }
}
