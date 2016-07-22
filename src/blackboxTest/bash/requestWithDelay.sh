#!/bin/bash

TESTPATH=$1

./getRootPathWithDelay.sh | nc localhost 8080
echo delayed thread end time
date +%s
date +%s > ${TESTPATH}/endTimeRequestWithDelay.txt
