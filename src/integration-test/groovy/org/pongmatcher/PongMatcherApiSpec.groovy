package org.pongmatcher

import grails.testing.mixin.integration.Integration
import groovy.json.JsonSlurper
import spock.lang.Shared
import spock.lang.Specification

import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse

@Integration
class PongMatcherApiSpec extends Specification {

    @Shared
    HttpClient client = HttpClient.newHttpClient()

    private HttpResponse<String> call(String method, String path, String body = null) {
        def builder = HttpRequest.newBuilder(URI.create("http://localhost:${serverPort}${path}"))
        if (body != null) {
            builder.header("Content-Type", "application/json")
            builder.method(method, HttpRequest.BodyPublishers.ofString(body))
        } else {
            builder.method(method, HttpRequest.BodyPublishers.noBody())
        }
        client.send(builder.build(), HttpResponse.BodyHandlers.ofString())
    }

    private static Map json(HttpResponse<String> response) {
        new JsonSlurper().parseText(response.body()) as Map
    }

    void "the README interaction flow works"() {
        expect:
        call("DELETE", "/all").statusCode() == 200
        call("PUT", "/match_requests/firstrequest", '{"player": "andrew"}').statusCode() == 200

        when: "only one player has asked for a match"
        def first = call("GET", "/match_requests/firstrequest")

        then:
        first.statusCode() == 200
        json(first) == [id: "firstrequest", player: "andrew", match_id: null]

        when: "a second player asks for a match"
        def put2 = call("PUT", "/match_requests/secondrequest", '{"player": "navratilova"}')
        def firstAgain = json(call("GET", "/match_requests/firstrequest"))
        def second = json(call("GET", "/match_requests/secondrequest"))

        then:
        put2.statusCode() == 200
        firstAgain.match_id != null
        second.match_id == firstAgain.match_id

        when:
        def match = call("GET", "/matches/${firstAgain.match_id}")

        then:
        match.statusCode() == 200
        json(match) == [id: firstAgain.match_id, match_request_1_id: "firstrequest", match_request_2_id: "secondrequest"]

        and: "the result can be entered"
        call("POST", "/results", """{"match_id": "${firstAgain.match_id}", "winner": "andrew", "loser": "navratilova"}""").statusCode() == 201

        when: "the same players ask again"
        call("PUT", "/match_requests/thirdrequest", '{"player": "andrew"}')
        call("PUT", "/match_requests/fourthrequest", '{"player": "navratilova"}')

        then: "they are not matched with each other again"
        json(call("GET", "/match_requests/fourthrequest")).match_id == null

        and: "unknown resources return 404"
        call("GET", "/match_requests/nosuchrequest").statusCode() == 404
        call("GET", "/matches/999999").statusCode() == 404
    }
}
