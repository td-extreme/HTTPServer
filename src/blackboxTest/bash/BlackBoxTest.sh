#!/bin/bash
TESTPATH=./temp
EXIT_CODE=0
RED='\033[0;31m'
GREEN='\033[0;32m'
WHITE='\033[0m'

setUp() {
  mkdir ${TESTPATH}
  rm ${TESTPATH}/*
  touch ${TESTPATH}/file01.txt
  ./gradlew run -P args="-d ${TESTPATH}" &
  SERVER_PID=$!
  printf "\n\n:Blackbox Tests:\n"
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

testGetDirectoryContents() {
  curl localhost:8080 > ${TESTPATH}/testGet.txt 2>/dev/null
  echo -n "<!DOCTYPE html><html><body><a href=\"/file01.txt\">file01.txt</a><br><a href=\"/testGet.txt\">testGet.txt</a><br></body></html>" >> ${TESTPATH}/expected.txt
  printf "\n\n${WHITE}Black Box Test > testGetDirectoryContents"
  if cmp -s ${TESTPATH}/expected.txt ${TESTPATH}/testGet.txt ; then
    printf "${GREEN} PASSED ${WHITE}\n"
  else
    EXIT_CODE=1
    printf "${RED} FAILED ${WHITE}\n"
    printf "\n\n--------------\n"
    printf "\n pwd : "
    pwd
    printf "\n ls : "
    ls
    printf "\n ls temp : "
    ls ${TESTPATH} 
    printf "\n\n ----\n diff expected.txt testGet.txt \n"
    diff ${TESTPATH}/expected.txt ${TESTPATH}/testGet.txt
    printf "\n"
    printf "\n more expected.txt"
    more expected.txt
    printf "\n more testGet.txt \n"
    more testGet.txt
    printf "\n SERVER_PID "
    printf $SERVER_PID
    printf "\n curl localhost:8080 \n"
    curl localhost:8080 
    printf "\n\n--------------\n"
  fi
}

setUp
sleep 5
testGetDirectoryContents
tearDown
dispayResults
exit $EXIT_CODE
