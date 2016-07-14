#!/bin/bash
TESTPATH=./temp
EXIT_CODE=0
RED='\033[0;31m'
GREEN='\033[0;32m'
WHITE='\033[0m'

setUp() {
  mkdir ${TESTPATH}
  ./gradlew run -P args="-d ${TESTPATH}" &
  SERVER_PID=$!
  printf "\n\n:Blackbox Tests:\n"
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

testResults() {
  printf "\n\n${WHITE}Black Box Test > $1"
  if cmp -s ${TESTPATH}/$2 ${TESTPATH}/$3 ; then
    printf "${GREEN} PASSED ${WHITE}\n"
  else
    EXIT_CODE=1
    printf "${RED} FAILED ${WHITE}\n"
    printf "\n\n--------------\n"
    diff ${TESTPATH}/$2 ${TESTPATH}/$3
    printf "\n\n--------------\n"
  fi
  rm ${TESTPATH}/$2
  rm ${TESTPATH}/$3
}

testGetDirectoryContents() {
  touch ${TESTPATH}/file01.txt
  curl localhost:8080 > ${TESTPATH}/dirActual.txt 2>/dev/null
  echo -n "<!DOCTYPE html><html><body><a href=\"/dirActual.txt\">dirActual.txt</a><br><a href=\"/file01.txt\">file01.txt</a><br></body></html>" >> ${TESTPATH}/dirExpected.txt
  testResults testGetDirectoryContents dirExpected.txt dirActual.txt
 }

testGetFileContents() {
  echo -n "This is a test" > ${TESTPATH}/fileContentsExpected.txt
  curl localhost:8080/fileContentsExpected.txt > ${TESTPATH}/fileContentsActual.txt 2>/dev/null

  testResults testGetFileContents fileContentsExpected.txt fileContentsActual.txt
}

# Main 

setUp
testGetDirectoryContents
testGetFileContents
tearDown
dispayResults
exit $EXIT_CODE
