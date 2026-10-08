package org.pongmatcher

import grails.gorm.transactions.Transactional

import static org.springframework.http.HttpStatus.OK

class EveryResourceController {

    static allowedMethods = [delete: "DELETE"]

    @Transactional
    def delete() {
        Result.executeUpdate("delete Result r")
        Match.executeUpdate("delete Match m")
        MatchRequest.executeUpdate("delete MatchRequest r")
        render status: OK
    }
}
