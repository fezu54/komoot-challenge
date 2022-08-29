#!/bin/sh
gradle -p ../../../. build
cp ../../../build/libs/challenge-0.0.1-SNAPSHOT.jar service.jar
eb deploy
rm service.jar
