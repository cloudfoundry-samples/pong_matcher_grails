package org.pongmatcher

import grails.gorm.transactions.Transactional

import static org.springframework.http.HttpStatus.CREATED

class ResultController {

    static allowedMethods = [save: "POST"]

    @Transactional
    def save() {
        def json = request.JSON
        Match match = json.match_id != null ? Match.get(json.match_id as Long) : null

        new Result(match: match, winnerId: json.winner, loserId: json.loser)
            .save(failOnError: true)

        render status: CREATED
    }
}
