#!/bin/sh

set -e

# Check if there are one argument
if [ $# -eq 1 ]; then
   # Check if the input file actually exists.
   if [ ! -d "$1" ]; then
     echo "The server folder $1 does not exist... check them"
     exit 1
   fi
else
   echo "Usage: $0 [server folder]"
   exit 1
fi


PLUGIN_FOLDER="$1/plugins"
LOCATION=$(dirname "$(readlink -fn "$0")")  

mkdir -p $PLUGIN_FOLDER

find $LOCATION -type d -name "*-plugin" -exec sh -c "cd \"{}\" && pwd && ./gradlew" \;


find $LOCATION -type f -name "*-bukkit-plugin*.jar" -exec sh -c "cp \"{}\" $PLUGIN_FOLDER" \;
