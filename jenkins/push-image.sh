#!/bin/bash

echo "*********************"
echo "*** Pushing Image ***"
echo "*********************"

AUTH_IMAGE="education_auth"
APP_IMAGE="education_app"

echo "*** Logging in ***"
docker login -u $1 -p $2

echo "*** Tagging AUTH Image ***"
docker tag $AUTH_IMAGE:$3 allsul/$AUTH_IMAGE:$3

echo "*** Pushing AUTH Image ***"
docker push allsul/$AUTH_IMAGE:$3


echo "*** Tagging APP Image ***"
docker tag $APP_IMAGE:$3 allsul/$APP_IMAGE:$3

echo "*** Pushing APP Image ***"
docker push allsul/$APP_IMAGE:$3

echo "*** Images has been pushed ***"
