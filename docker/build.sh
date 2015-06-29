#! /bin/bash -e

rm -fr build
mkdir build
cp ../build/libs/sensors-0.0.1-SNAPSHOT.jar build

docker build -t iot_weather_ms .
