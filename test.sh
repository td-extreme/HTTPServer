#!/bin/bash
./gradlew clean check
if [ $? -ne 0 ]
then
  exit 1
fi

./src/blackboxTest/bash/BlackBoxTest.sh
