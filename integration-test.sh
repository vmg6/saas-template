#!/bin/bash

echo " | [+] Service ..."
pushd services/grizzly-jersey
./start-app.sh

popd
echo " | [-] Static"
