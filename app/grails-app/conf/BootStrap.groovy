import grails.converters.JSON
import org.pongmatcher.MatchRequest

class BootStrap {

    def init = { servletContext ->

        JSON.registerObjectMarshaller(MatchRequest) {
            return [
                id: it.id,
                match_id: it.match()?.id
            ]
        }

    }
    def destroy = {
    }
}
