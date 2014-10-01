package org.pongmatcher

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class EveryResourceController {

    static allowedMethods = [delete: "DELETE"]

    @Transactional
    def delete() {
        Match.executeUpdate("delete Match m")
        MatchRequest.executeUpdate("delete MatchRequest r")
        Result.executeUpdate("delete Result r")
        render status: OK
        return
    }
}
