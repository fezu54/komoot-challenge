#!/bin/sh
gradle -p ../../../. build
cp ../../../build/libs/challenge-0.0.1-SNAPSHOT.jar service.jar
docker build -t komoot-challenge .
docker tag komoot-challenge:latest public.ecr.aws/m1h9n5v7/komoot-challenge:latest
docker push public.ecr.aws/m1h9n5v7/komoot-challenge:latest
eb deploy
