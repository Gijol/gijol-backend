#!/bin/bash
PROJECT_NAME=graduation
PROFILE=dev

echo "> now ing app pid find!"

CURRENT_PID=$(pgrep -f $PROJECT_NAME)
echo "$CURRENT_PID"

if [ -z $CURRENT_PID ]; then
        echo "> no ing app."
else
        echo "> kill -9 $CURRENT_PID"
        kill -9 $CURRENT_PID
        sleep 3
fi
echo "> new app deploy"

cd /home/ubuntu/deploy
JAR_NAME=$(find ./* -name "*SNAPSHOT.jar" | head -1)
echo "> JAR Name: $JAR_NAME"

nohup java -jar -Dspring.profiles.active=$PROFILE $JAR_NAME 1>nohup/stdout.txt 2>nohup/stderr.txt &
sleep 2

