# DOCKER-VERSION 1.2.0

FROM        camelpunch/pong-matcher-base-groovy

# install app as unprivileged user
ADD         app pong_matcher_grails
RUN         chown -R web:web pong_matcher_grails

USER        web
ENV         GROOVY_HOME /groovy
ENV         PATH $GROOVY_HOME/bin:$PATH
ENV         JAVA_HOME /usr

RUN         cd pong_matcher_grails; ./grailsw refresh-dependencies
