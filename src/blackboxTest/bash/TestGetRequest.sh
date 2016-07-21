#!/bin/bash
TESTPATH=$1
EXIT_CODE=0
RED='\033[0;31m'
GREEN='\033[0;32m'
WHITE='\033[0m'



printTestResults() {
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
  curl localhost:8080 > ${TESTPATH}/dirActual.txt 2>/dev/null
  echo -n "<!DOCTYPE html><html><body><a href=\"/dirActual.txt\">dirActual.txt</a><br></body></html>" >> ${TESTPATH}/dirExpected.txt
  printTestResults testGetDirectoryContents dirExpected.txt dirActual.txt
 }

testGetFileContents() {
  echo -n "This is a test" > ${TESTPATH}/fileContentsExpected.txt
  curl localhost:8080/fileContentsExpected.txt > ${TESTPATH}/fileContentsActual.txt 2>/dev/null

  printTestResults testGetFileContents fileContentsExpected.txt fileContentsActual.txt
}

# Main

testGetDirectoryContents
testGetFileContents
exit $EXIT_CODE
