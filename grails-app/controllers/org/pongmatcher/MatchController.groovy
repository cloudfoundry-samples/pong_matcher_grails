package org.pongmatcher

import grails.converters.JSON
import grails.gorm.transactions.Transactional

import static org.springframework.http.HttpStatus.NOT_FOUND

class MatchController {

    @Transactional(readOnly = true)
    def show(Long id) {
        Match match = id != null ? Match.get(id) : null
        if (match == null) {
            render status: NOT_FOUND
            return
        }
        render(match.toJson() as JSON)
    }
}
