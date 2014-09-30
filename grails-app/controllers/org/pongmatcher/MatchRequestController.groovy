package org.pongmatcher



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class MatchRequestController {

    static responseFormats = ['json', 'xml']
    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond MatchRequest.list(params), [status: OK]
    }

    def show(MatchRequest matchRequestInstance) {
        render status: OK
        return
    }

    @Transactional
    def save(MatchRequest matchRequestInstance) {
        if (matchRequestInstance == null) {
            render status: NOT_FOUND
            return
        }

        matchRequestInstance.validate()
        if (matchRequestInstance.hasErrors()) {
            render status: NOT_ACCEPTABLE
            return
        }

        matchRequestInstance.save flush:true
        respond matchRequestInstance, [status: CREATED]
    }

    @Transactional
    def update(MatchRequest matchRequestInstance) {
        if (matchRequestInstance == null) {
            def m = new MatchRequest()
            m.id = params.id
            m.save()
            render status: OK
            return
        }

        matchRequestInstance.validate()
        if (matchRequestInstance.hasErrors()) {
            render status: NOT_ACCEPTABLE
            return
        }

        matchRequestInstance.save flush:true
        respond matchRequestInstance, [status: OK]
    }

    @Transactional
    def delete(MatchRequest matchRequestInstance) {

        if (matchRequestInstance == null) {
            render status: NOT_FOUND
            return
        }

        matchRequestInstance.delete flush:true
        render status: NO_CONTENT
    }
}
