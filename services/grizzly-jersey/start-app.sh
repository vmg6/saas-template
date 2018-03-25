#!/bin/bash

export $(cat ./../../.env | grep -v ^# | xargs)
nohup java -jar target/org.swecourse.services.api-18.3.0-SNAPSHOT-jar-with-dependencies.jar > nohup.out 2> nohup.err < /dev/null &