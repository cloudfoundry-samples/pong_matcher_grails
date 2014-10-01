package org.pongmatcher

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class ResultController {

    static responseFormats = ['json']
    static allowedMethods = [update: "PUT"]

    @Transactional
    def save() {
        Match match = Match.get(request.JSON.match_id)
        def winnerId = request.JSON.winner
        def loserId = request.JSON.loser

        Result result = new Result(match: match, winnerId: winnerId, loserId: loserId)
        result.save(failOnError: true)

        render status: CREATED
        return
    }
}
