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
        log.error "Request in: ${request.JSON}"
        def newMatchRequest = new MatchRequest(requesterId: request.JSON.get("player"))
        newMatchRequest.id = params.id
        newMatchRequest.save(failOnError: true)

        def openMatchRequest = firstOpenMatchRequest(newMatchRequest.requesterId)
        if (openMatchRequest) {
            def match = new Match(matchRequest1: openMatchRequest,
                                  matchRequest2: newMatchRequest)
            match.save(failOnError: true)
        }

        render status: OK
        return
    }

    def firstOpenMatchRequest(playerId) {
        log.error "Unfulfilled requests: ${unfulfilledMatchRequests().size()}"
        unfulfilledMatchRequests().find { matchRequest ->
            log.error "Assessing whether request is open: ${matchRequest}"
            def inappropriateOpponentIds = [playerId] + previousOpponents(playerId)
            log.error "Inappropriate opponents: ${inappropriateOpponentIds}"
            log.error "Requester: ${matchRequest.requesterId}"
            !inappropriateOpponentIds.contains(matchRequest.requesterId)
        }
    }

    def unfulfilledMatchRequests() {
        MatchRequest.list().findAll { matchRequest ->
            log.error "Assessing whether request is unfulfilled: ${matchRequest}"
            matchRequest.match() == null
        }
    }

    def previousOpponents(playerId) {
        resultsInvolvingPlayer(playerId).collect { result ->
            result.winner == playerId ? result.loser : result.winner
        }
    }

    def resultsInvolvingPlayer(playerId) {
        Result.list().findAll { result ->
            [result.winnerId, result.loserId].contains(playerId)
        }
    }
}
