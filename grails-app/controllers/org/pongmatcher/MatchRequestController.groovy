package org.pongmatcher

import grails.converters.JSON
import grails.gorm.transactions.Transactional

import static org.springframework.http.HttpStatus.NOT_FOUND
import static org.springframework.http.HttpStatus.OK

class MatchRequestController {

    @Transactional(readOnly = true)
    def show(String id) {
        MatchRequest matchRequest = MatchRequest.get(id)
        if (matchRequest == null) {
            render status: NOT_FOUND
            return
        }
        render(matchRequest.toJson() as JSON)
    }

    @Transactional
    def save(String id) {
        def newMatchRequest = new MatchRequest(requesterId: request.JSON.player)
        newMatchRequest.id = id
        newMatchRequest.save(failOnError: true, flush: true)

        def openMatchRequest = MatchRequest.firstOpen(newMatchRequest.requesterId)
        if (openMatchRequest) {
            new Match(matchRequest1: openMatchRequest, matchRequest2: newMatchRequest)
                .save(failOnError: true)
        }

        render status: OK
    }
}
