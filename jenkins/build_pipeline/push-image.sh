#!/bin/bash

echo "***************************"
echo "****** Pushing Image ******"
echo "***************************"

AUTH_IMAGE="education_auth"
APP_IMAGE="education_app"
TAG="$(head -n 1 version.log)"

{
  echo "****** Logging in ******"
  docker login -u $1 -p $2
} || {
  echo "****** Failed Logging in DockerHub ******"
  exit 1
}

{
  echo "****** Tagging AUTH Image ******"
  docker tag $AUTH_IMAGE:$TAG allsul/$AUTH_IMAGE:$TAG

  echo "****** Pushing AUTH Image ******"
  docker push allsul/$AUTH_IMAGE:$TAG
} || {
  echo "****** Failed Processing AUTH Image ******"
  exit 1
}

{
  echo "****** Tagging APP Image ******"
  docker tag $APP_IMAGE:$TAG allsul/$APP_IMAGE:$TAG

  echo "****** Pushing APP Image ******"
  docker push allsul/$APP_IMAGE:$TAG
} || {
  echo "****** Failed Processing APP Image ******"
  exit 1
}

echo "************************************"
echo "****** Images has been pushed ******"
echo "************************************"
