#!/bin/bash

echo " | [+] Service ..."
pushd services/grizzly-jersey

nohup java -jar target/org.swecourse.services.api-18.3.0-SNAPSHOT-jar-with-dependencies.jar > nohup.out 2> nohup.err < /dev/null &
