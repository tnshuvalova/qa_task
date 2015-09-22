#!/bin/bash
S=$(echo ${0})
V=$(echo $S | awk -F/ '{print $(NF-1)}')
P_TO_LOG=${S%%/$V*}/data/datamining.log
P_TO_OUTPUT=${S%%/$V*}/output/success_statistics.txt
grep 'HTTP/1.1" 200' $P_TO_LOG | awk -F' ' '{split($4,a,":");print "successful requests in "a[2]}' |  uniq -c > $P_TO_OUTPUT
