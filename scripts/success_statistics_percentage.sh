#!/bin/bash
S=$(echo ${0})
V=$(echo $S | awk -F/ '{print $(NF-1)}')
P_TO_LOG=${S%%/$V*}/data/datamining.log
P_TO_OUTPUT=${S%%/$V*}/output/success_statistics_percentage.txt
P_TO_S=${S%%/$V*}/output/success_per_hour.txt
P_TO_A=${S%%/$V*}/output/all_per_hour.txt
grep 'HTTP/1.1" 200' $P_TO_LOG | awk -F' ' '{split($4,a,":");print a[2]}' |  uniq -c > $P_TO_S
grep 'HTTP/1.1' $P_TO_LOG | awk -F' ' '{split($4,a,":");print a[2]}' |  uniq -c > $P_TO_A

declare -a ARRAY_HOURS
declare -i success_container
declare -i all_container
count=0
echo SUCCESSFUL REQUESTS > $P_TO_OUTPUT

while read line ; do
  IFS=" "
  set -- $line
  ARRAY_HOURS[$count]=$2
  all_container=$1
  while read line_inner ; do
	  IFS=" "
	  set -- $line_inner
      if [[ ${ARRAY_HOURS[$count]} = $2 ]];
        then
           success_container=$1
      fi
  done < $P_TO_S
  echo at ${ARRAY_HOURS[$count]} $[$success_container*100/$all_container]% requests are successful >> $P_TO_OUTPUT
  ((count++)) 
done < $P_TO_A
rm $P_TO_S
rm $P_TO_A

