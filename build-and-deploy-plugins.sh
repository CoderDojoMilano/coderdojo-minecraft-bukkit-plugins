#!/bin/sh

PLUGIN_FOLDER="$1/plugins"
LOCATION=$(dirname "$(readlink -fn "$0")")  

mkdir -p $PLUGIN_FOLDER

find $LOCATION -type d -name "*-plugin" -exec sh -c "cd \"{}\" && pwd && ./gradlew" \;


find $LOCATION -type f -name "*all*.jar" -exec sh -c "cp \"{}\" $PLUGIN_FOLDER" \;
