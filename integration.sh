#!/bin/bash

echo "[+] Start application ..."

echo " | [+] Service ..."
pushd services/grizzly-jersey

export $(cat ./../../.env | grep -v ^# | xargs)
java -jar target/org.swecourse.services.api-18.3.0-SNAPSHOT-jar-with-dependencies.jar

echo "git fetch ..."
git url: 'https://github.com/vmg6/saas-fremework-camp.git

echo "Start Integration test ..."
mvn clean test -Dservers=env1"

echo "Stop application ..."
fuser -k 80/tcp

popd
echo " | [-] Service"