#!/bin/bash

set -xe

docker run -e "CF_HOME=/pong_matcher_grails" -t camelpunch/pong-matcher-grails /bin/bash -c "\
    cd pong_matcher_grails
    cf api https://api.run.pivotal.io &&
    cf auth $CF_USERNAME $CF_PASSWORD &&
    cf target -o $CF_ORG -s $CF_SPACE &&
    cf push -n $HOSTNAME -d cfapps.io"
