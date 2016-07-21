#!/bin/bash

TESTPATH=$1

./getRootPathWithDelay.sh | telnet
date +%s > ${TESTPATH}/endTimeRequestWithDelay.txt
