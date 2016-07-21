#!/bin/bash

TESTPATH=$1

./getRootPath.sh | nc localhost 8080
echo non delayed thread end time
date +%s
date +%s > ${TESTPATH}/endTimeRequestWithOutDelay.txt
