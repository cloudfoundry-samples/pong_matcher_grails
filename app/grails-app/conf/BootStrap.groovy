import grails.converters.JSON
import org.pongmatcher.Match
import org.pongmatcher.MatchRequest

class BootStrap {

    def init = { servletContext ->

        JSON.registerObjectMarshaller(MatchRequest) {
            return [
                id: it.id,
                player: it.requesterId,
                match_id: it.match()?.id
            ]
        }

        JSON.registerObjectMarshaller(Match) {
            return [
                id: it.id,
                match_request_1_id: it.matchRequest1Id,
                match_request_2_id: it.matchRequest2Id
            ]
        }

    }

    def destroy = {
    }
}
