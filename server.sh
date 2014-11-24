#!/bin/sh

#LOCATION=$(dirname "$(readlink -fn "$0")")  
#LOCATION=$(dirname "$0")
LOCATION=$HOME/crafbukkit

cd "$LOCATION"  

mkdir -p logs

MINECRAFT=`find . -maxdepth 1 -type f -name "*.jar"`
 
JAVA="java"
 
JAVAOPTS="-server -jar"
 
RUNNING=`screen -ls | grep minecraft`
 
case "$1" in
'start')
        cd $LOCATION
        RUNNING=`screen -ls | grep minecraft`
        if [ "$RUNNING" = "" ]
        then
                screen -dmS minecraft $JAVA $JAVAOPTS $MINECRAFT nogui
        fi
        ;;
'stop')
        screen -x minecraft -X stuff "`printf "kickall Restarting server!  Try again in 60 seconds!\r"`"
        sleep 2
        screen -x minecraft -X stuff `printf "stop\r"`
        ;;
 
'restart')
        screen -x minecraft -X stuff "`printf "kickall Restarting server!  Try again in 60 seconds!\r"`"
        sleep 2
        screen -x minecraft -X stuff `printf "stop\r"`
        RUNNING=`screen -ls | grep minecraft`
        cd $LOCATION
        until [ "$RUNNING" = "" ]
        do
                RUNNING=`screen -ls | grep minecraft`
        done
        screen -dmS minecraft $JAVA $JAVAOPTS $MINECRAFT nogui
        sleep 1
        screen -x minecraft
        ;;
 
'view')
        screen -x minecraft
        ;;
 
'sv')
        cd $LOCATION
        if [ "$RUNNING" = "" ]
        then
                screen -dmS minecraft $JAVA $JAVAOPTS $MINECRAFT nogui
        fi
        sleep 1
        screen -x minecraft
        ;;     
 
*)
        echo "Usage: $0 { start | stop | restart | view | sv (start & view) }"
        ;;
esac
exit 0
