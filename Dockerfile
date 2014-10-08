# DOCKER-VERSION 1.2.0

# base
FROM    ubuntu:14.04
RUN     apt-get update
RUN     apt-get install -qy build-essential wget

# create user
RUN     adduser --disabled-password web

# cf
ADD     https://cli.run.pivotal.io/stable?release=debian64&source=github cf.deb
RUN     dpkg -i cf.deb

# install java and unzip
RUN     apt-get install -y default-jre
RUN     apt-get install -y unzip

# install groovy
ADD     http://dl.bintray.com/groovy/maven/groovy-binary-2.4.0-beta-3.zip groovy-binary.zip
RUN     unzip groovy-binary.zip
RUN     mv `find /groovy-* -maxdepth 0 -type d` /groovy
RUN     chown -R web:web /groovy

# install app as unprivileged user
ADD         app pong_matcher_grails
RUN         chown -R web:web pong_matcher_grails

USER        web
ENV         GROOVY_HOME /groovy
ENV         PATH $GROOVY_HOME/bin:$PATH
ENV         JAVA_HOME /usr

RUN         cd pong_matcher_grails; ./grailsw war
