#!/bin/bash
TESTPATH=$1
EXIT_CODE=0
RED='\033[0;31m'
GREEN='\033[0;32m'
WHITE='\033[0m'


runThreads() {
  ./requestWithDelay.sh ${TESTPATH} > /dev/null 2>/dev/null &
  ./requestWithOutDelay.sh ${TESTPATH} > /dev/null 2>/dev/null &
}

checkThatEndTimeForRequestWithOutDelayIsEarlierThanRequestWithOutDelay() {
  endTimeThreadWithoutDelay="`head -1 ${TESTPATH}/endTimeRequestWithOutDelay.txt`"
  endTimeThreadWithDelay="`head -1 ${TESTPATH}/endTimeRequestWithDelay.txt`"
  testGreaterThan ThreadWithNoDelayFinishesBeforeThreadWithDelay ${endTimeThreadWithDelay} ${endTimeThreadWithoutDelay}
}

testGreaterThan() {
  printf "\n\n${WHITE}Black Box Test > $1"
  if [ $2 -gt $3 ]; then
    printf "${GREEN} PASSED ${WHITE}\n"
  else
    EXIT_CODE=1
    printf "${RED} FAILED ${WHITE}\n"
    printf "\n\n--------------\n"
    printf "Thread With Delay ended     : $2\n"
    printf "Thread With Out Delay ended : $3\n"
    printf "\n\n--------------\n"
  fi
}

cleanUp() {
  rm ${TESTPATH}/endTimeRequestWithOutDelay.txt
  rm ${TESTPATH}/endTimeRequestWithDelay.txt
}

# Main

runThreads
sleep 15
checkThatEndTimeForRequestWithOutDelayIsEarlierThanRequestWithOutDelay
cleanUp
exit $EXIT_CODE
