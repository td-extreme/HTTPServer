#!/bin/bash

TESTPATH=$1

./getRootPath.sh | telnet
echo non delayed thread end time
date +%s
date +%s > ${TESTPATH}/endTimeRequestWithOutDelay.txt
