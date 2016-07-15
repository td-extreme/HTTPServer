#!/bin/bash
set -e
./gradlew clean check
./src/blackboxTest/bash/BlackBoxTest.sh
