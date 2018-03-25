#!/bin/bash

echo "[+] Start application ..."

echo " | [+] Service ..."
pushd services/grizzly-jersey

echo "Stop app ..."
fuser -k 80/tcp

popd
echo " | [-] Service"