package org.pongmatcher

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

import org.pongmatcher.Result

@Transactional(readOnly = true)
class MatchRequestController {

    static responseFormats = ['json']

    def show(MatchRequest matchRequestInstance) {
        if (matchRequestInstance == null) {
            render status: NOT_FOUND
            return
        } else {
            respond matchRequestInstance, [status: OK]
            return
        }
    }

    @Transactional
    def save() {
        def newMatchRequest = new MatchRequest(requesterId: request.JSON.get("player"))
        newMatchRequest.id = params.id
        newMatchRequest.save(failOnError: true)

        def openMatchRequest = MatchRequest.firstOpen(newMatchRequest.requesterId)
        if (openMatchRequest) {
            def match = new Match(matchRequest1: openMatchRequest,
                                  matchRequest2: newMatchRequest)
            match.save(failOnError: true)
        }

        render status: OK
        return
    }

}
