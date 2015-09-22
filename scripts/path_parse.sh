#!/bin/bash
S=$(echo ${0})
V=$(echo $S | awk -F/ '{print $(NF-1)}')
P_TO_LOG=${S%%/$V*}/lib/datamining.log
P_TO_OUTPUT=${S%%/$V*}/output/requests_statistics.txt
echo ${P_TO_LOG}
echo ${P_TO_OUTPUT}