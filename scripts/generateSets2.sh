#!/bin/bash

#path='/home/cristinae/Dropbox/sts2017/task1'
path='/home/cristinae/pln/LumpSTS/task1'

prefix="all"
#trainFolders=("en_ar.train.1_fold")
trainFolders=("ar.train.1_fold"  "en_ar.train.1_fold" "es.train.1_fold" "en_es.train.1_fold" "en.train.1_fold")
internalTest=("en.test.1_fold") 
testFolder=("STS2017.eval")

sims=("cosine" "jaccard" "kl" "js")


## BASIC: Only cosine similarities without translation features
if [ $1 == "basic" ] ; then

   train=${prefix}".trainWallsims.dat"
   mv $train $train.back
   for x in "${trainFolders[@]}"; do
       echo "$x"
       cd $x/sim
       file1=f1$RANDOM
       file2=f2$RANDOM
       file3=f3$RANDOM
       file4=f4$RANDOM
       file5=f5$RANDOM
       paste -d, ../*.scores.0.txt *.0.1-* > $file1 
       for i in {2..5}; do
	   j=`expr $i - 1`
	   oldFile="file$j"
	   newFile="file$i"
           paste -d, ${!oldFile} *0.$i-*.* > ${!newFile}
           echo "paste -d, ${!oldFile} *0.$i-*.cosine > ${!newFile}"
 	   rm ${!oldFile}
       done
       cat $file5 >> ../../$train
       rm $file5
       cd ../..
   done
   grep -v '^,' $train > $train.tmp
   mv $train.tmp $train

   train=${prefix}".train.dat"
   mv $train $train.back
   for x in "${trainFolders[@]}"; do
       echo "$x"
       cd $x/sim
       file1=f1$RANDOM
       file2=f2$RANDOM
       file3=f3$RANDOM
       file4=f4$RANDOM
       file5=f5$RANDOM
       paste -d, ../*.scores.0.txt *.0.1-* > $file1 
       for i in {2..5}; do
	   j=`expr $i - 1`
	   oldFile="file$j"
	   newFile="file$i"
           paste -d, ${!oldFile} *0.$i-*.cosine > ${!newFile}
           echo "paste -d, ${!oldFile} *0.$i-*.cosine > ${!newFile}"
 	   rm ${!oldFile}
       done
       cat $file5 >> ../../$train
       rm $file5
       cd ../..
   done
   grep -v '^,' $train > $train.tmp
   mv $train.tmp $train

## BASICMT: Only cosine similarities with additional features from translation
elif [ $1 == "basicMT" ] ; then
   train=${prefix}".trainWmtWallsims.dat"
   mv $train $train.back
   for x in "${trainFolders[@]}"; do
       echo "$x"
       cd $x/sim
       file1=f1$RANDOM
       file2=f2$RANDOM
       file3=f3$RANDOM
       file4=f4$RANDOM
       file5=f5$RANDOM
       file6=f6$RANDOM
       paste -d, ../*.scores.0.txt *.0.1-* > $file1 
       for i in {2..6}; do
	   j=`expr $i - 1`
	   oldFile="file$j"
	   newFile="file$i"
           paste -d, ${!oldFile} *0.$i-*.* > ${!newFile}
           echo "paste -d, ${!oldFile} *0.$i-*.cosine > ${!newFile}"
 	   rm ${!oldFile}
       done
       cat $file6 >> ../../$train
       rm $file6
       cd ../..
   done
   grep -v '^,' $train > $train.tmp
   mv $train.tmp $train

   train=${prefix}".trainWmt.dat"
   mv $train $train.back
   for x in "${trainFolders[@]}"; do
       echo "$x"
       cd $x/sim
       file1=f1$RANDOM
       file2=f2$RANDOM
       file3=f3$RANDOM
       file4=f4$RANDOM
       file5=f5$RANDOM
       file6=f6$RANDOM
       paste -d, ../*.scores.0.txt *.0.1-* > $file1 
       for i in {2..6}; do
	   j=`expr $i - 1`
	   oldFile="file$j"
	   newFile="file$i"
           paste -d, ${!oldFile} *0.$i-*.cosine > ${!newFile}
           echo "paste -d, ${!oldFile} *0.$i-*.cosine > ${!newFile}"
 	   rm ${!oldFile}
       done
       cat $file6 >> ../../$train
       rm $file6
       cd ../..
   done
   grep -v '^,' $train > $train.tmp
   mv $train.tmp $train

fi




