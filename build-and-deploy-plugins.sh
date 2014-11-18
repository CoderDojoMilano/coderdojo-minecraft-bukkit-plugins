#!/bin/sh

LOCATION=$(dirname "$(readlink -fn "$0")")  

echo $LOCATION

find $LOCATION -type d -name "*-plugin" -exec sh -c "cd \"{}\" && pwd && ./gradlew" \;
