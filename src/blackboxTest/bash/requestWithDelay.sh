#!/bin/bash

TESTPATH=$1

./getRootPathWithDelay.sh | telnet
echo delayed thread end time
date +%s
date +%s > ${TESTPATH}/endTimeRequestWithDelay.txt
