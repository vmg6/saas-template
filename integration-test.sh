#!/bin/bash

echo "[+] Start application ..."

echo " | [+] Service ..."
pushd services/grizzly-jersey

export $(cat ./../../.env | grep -v ^# | xargs)
nohup java -jar target/org.swecourse.services.api-18.3.0-SNAPSHOT-jar-with-dependencies.jar > nohup.out 2> nohup.err < /dev/null &


echo "Git fetch"
git fetch --tags --progress https://github.com/vmg6/saas-fremework-camp.git +refs/heads/*:refs/remotes/origin/*

echo "Start test"
mvn clean test -Dservers=env1


echo "Stop app ..."
fuser -k 80/tcp

popd
echo " | [-] Service"