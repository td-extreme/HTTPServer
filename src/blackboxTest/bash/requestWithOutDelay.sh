#!/bin/bash

TESTPATH=$1

./getRootPath.sh | telnet
date +%s > ${TESTPATH}/endTimeRequestWithOutDelay.txt
