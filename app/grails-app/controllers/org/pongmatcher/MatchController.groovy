package org.pongmatcher

import static org.springframework.http.HttpStatus.*
import grails.converters.JSON

class MatchController {

    static responseFormats = ['json']

    def show(Match matchInstance) {
        if (matchInstance == null) {
            System.out.println("NOT FOUND")
            render status: NOT_FOUND
            return
        } else {
            System.out.println("FOUND!")
            System.out.println(matchInstance as JSON)
            respond matchInstance, [status: OK]
            return
        }
    }
}
