# CF example app: ping-pong matching server

This is an app to match ping-pong players with each other. It's currently an
API only, so you have to use `curl` to interact with it.

It is an [Apache Grails][grails] 8 application (Spring Boot 4) and requires
JDK 21 or later. It has an [acceptance test suite][acceptance-test] you might
like to look at.

## Running on Cloud Foundry

Log in, replacing `API-ENDPOINT` with the API endpoint of your Cloud Foundry
installation.

```bash
cf login -a API-ENDPOINT
```

Target your org / space. An empty space is recommended, to avoid naming collisions.

```bash
cf target -o myorg -s myspace
```

Build the app. This creates `build/libs/pong_matcher_grails-0.1.war`.

```bash
./gradlew assemble
```

Push the app. The `manifest.yml` file describes the app. It sets two
environment variables for the Java buildpack:

- `BP_JAVA_VERSION: 21`, because Grails 8 requires Java 21 and the buildpack
  uses Java 17 by default.
- `JBP_CONFIG_TOMCAT: '{ tomcat: { version: 11.+ } }'`, because Grails 8 is
  based on Spring Boot 4, which requires Servlet 6.1 (Tomcat 11). The buildpack
  uses Tomcat 10.1 by default.

```bash
cf push
```

By default the app uses an in-memory H2 database, so no database service is
needed. To store data in MySQL or PostgreSQL, create a service instance from
one of the database services listed by `cf marketplace`, bind it to the app,
and restage:

```bash
cf marketplace
cf create-service SERVICE PLAN SERVICE-INSTANCE
cf bind-service grailspong SERVICE-INSTANCE
cf restage grailspong
```

For Spring Boot 3 and 4 based apps, including Grails 8, the Java buildpack
automatically adds [java-cfenv][java-cfenv] to the app. When a database
service is bound, java-cfenv sets the Spring Boot `spring.datasource.*`
properties from the service credentials.
`grails-app/conf/application.yml` maps those properties to the GORM
`dataSource` configuration, and the tables are created at startup.

Export the test host, using the route shown by `cf app grailspong`:

```bash
export HOST=https://grailspong.example.com
```

Now follow the [interaction instructions](#interaction-instructions).

## Running locally

Start the application server, which uses an in-memory H2 database:

```bash
./gradlew bootRun
```

Export the test host

```bash
export HOST=http://localhost:8080
```

Now follow the [interaction instructions](#interaction-instructions).

To run the tests:

```bash
./gradlew check
```

## Interaction instructions

Start by clearing the database from any previous tests.
You should get a 200.

```bash
curl -v -X DELETE $HOST/all
```

Then request a match, providing both a request ID and player ID. Again, you
should get a 200.

```bash
curl -v -H "Content-Type: application/json" -X PUT $HOST/match_requests/firstrequest -d '{"player": "andrew"}'
```

Now pretend to be someone else requesting a match:

```bash
curl -v -H "Content-Type: application/json" -X PUT $HOST/match_requests/secondrequest -d '{"player": "navratilova"}'
```

Let's check on the status of our first match request:

```bash
curl -v -X GET $HOST/match_requests/firstrequest
```

The bottom of the output should show you the match_id. You'll need this in the
next step.

Now pretend that you've got back to your desk and need to enter the result:

```bash
curl -v -H "Content-Type: application/json" -X POST $HOST/results -d '
{
    "match_id":"thematchidyoureceived",
    "winner":"andrew",
    "loser":"navratilova"
}'
```

You should get a 201 Created response.

Future requests with different player IDs should not cause a match with someone
who has already played. The program is not yet useful enough to
allow pairs who've already played to play again.

[acceptance-test]:https://github.com/cloudfoundry-samples/pong_matcher_acceptance
[grails]:https://grails.apache.org
[java-cfenv]:https://github.com/pivotal-cf/java-cfenv
