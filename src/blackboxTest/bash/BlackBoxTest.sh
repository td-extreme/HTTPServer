#!/bin/bash
TESTPATH=./temp
EXIT_CODE=0
RED='\033[0;31m'
GREEN='\033[0;32m'
WHITE='\033[0m'

setUp() {
  ./gradlew run -P args="-d ./src/blackboxTest/bash/${TESTPATH}" &
  SERVER_PID=$!
  printf "\n\n:Blackbox Tests:\n"
  cd ./src/blackboxTest/bash
  mkdir ${TESTPATH}
  printf "There is a 15 second wait to ensure the HttpServer is running before any tests execute.\n\n"
  sleep 15
}

tearDown() {
  rm -rf ${TESTPATH}
  kill $SERVER_PID
}

dispayResults() {
  if [ $EXIT_CODE -eq 0 ]; then
    printf "\n\nBLACKBOX TESTS PASSED\n\n"
  else
    printf "\n\nBLACKBOX TESTS FAILED\n\n"
  fi
}

updateExitCode() {
if [ $? -ne 0 ]; then
  EXIT_CODE=$?
fi
}
# Main 
bash --version
setUp
./TestGetRequest.sh ${TESTPATH}
updateExitCode
./TestMultiThreading.sh ${TESTPATH}
updateExitCode
tearDown
dispayResults
exit $EXIT_CODE
