#!/bin/bash


# This script generates 6 binary features that reflect the language of the 
# source and target language in a file. Everything is hard coded and not magic is 
# carried out (such as language identification.
#
# The features are 
# src_ar
# src_en
# src_es
# trg_ar
# trg_en
# trg_es
#

INPUT_PATH=/Users/albarron/workspace/LumpSTS/DATA



######
# Test 2017
######

L1=ar
L2=ar
code=1,0,0,1,0,0

lines=`wc -l $INPUT_PATH/STS2017.eval/STS.input.track1.$L1-$L2.txt | awk {'print $1'}`
for i in $(seq 1 $lines); do
   echo $code
done > $INPUT_PATH/STS2017.eval/sim/track1.$L1-$L2.0.1-langs
wc -l $INPUT_PATH/STS2017.eval/STS.input.track1.$L1-$L2.txt
wc -l $INPUT_PATH/STS2017.eval/sim/track1.$L1-$L2.0.1-langs
echo


L1=en
L2=en
code=0,1,0,0,1,0

lines=`wc -l $INPUT_PATH/STS2017.eval/STS.input.track5.$L1-$L2.txt | awk {'print $1'}`
for i in $(seq 1 $lines); do
   echo $code
done > $INPUT_PATH/STS2017.eval/sim/track5.$L1-$L2.0.1-langs
wc -l $INPUT_PATH/STS2017.eval/STS.input.track5.$L1-$L2.txt
wc -l $INPUT_PATH/STS2017.eval/sim/track5.$L1-$L2.0.1-langs
echo


L1=es
L2=es
code=0,0,1,0,0,1

lines=`wc -l $INPUT_PATH/STS2017.eval/STS.input.track3.$L1-$L2.txt | awk {'print $1'}`
for i in $(seq 1 $lines); do
   echo $code
done > $INPUT_PATH/STS2017.eval/sim/track3.$L1-$L2.0.1-langs
wc -l $INPUT_PATH/STS2017.eval/STS.input.track3.$L1-$L2.txt
wc -l $INPUT_PATH/STS2017.eval/sim/track3.$L1-$L2.0.1-langs
echo


L1=en
L2=ar
code=0,1,0,1,0,0

lines=`wc -l $INPUT_PATH/STS2017.eval/STS.input.track2.$L2-$L1.txt | awk {'print $1'}`
for i in $(seq 1 $lines); do
   echo $code
done > $INPUT_PATH/STS2017.eval/sim/track2.$L2-$L1.0.1-langs
wc -l $INPUT_PATH/STS2017.eval/STS.input.track2.$L2-$L1.txt
wc -l $INPUT_PATH/STS2017.eval/sim/track2.$L2-$L1.0.1-langs
echo


L1=en
L2=es
code=0,1,0,0,0,1

lines=`wc -l $INPUT_PATH/STS2017.eval/STS.input.track4a.$L2-$L1.txt | awk {'print $1'}`
for i in $(seq 1 $lines); do
   echo $code
done > $INPUT_PATH/STS2017.eval/sim/track4a.$L2-$L1.0.1-langs
wc -l $INPUT_PATH/STS2017.eval/STS.input.track4a.$L2-$L1.txt
wc -l $INPUT_PATH/STS2017.eval/sim/track4a.$L2-$L1.0.1-langs
echo


lines=`wc -l $INPUT_PATH/STS2017.eval/STS.input.track4b.$L2-$L1.txt | awk {'print $1'}`
for i in $(seq 1 $lines); do
   echo $code
done > $INPUT_PATH/STS2017.eval/sim/track4b.$L2-$L1.0.1-langs
wc -l $INPUT_PATH/STS2017.eval/STS.input.track4b.$L2-$L1.txt
wc -l $INPUT_PATH/STS2017.eval/sim/track4b.$L2-$L1.0.1-langs
echo



#No turkish in these three features. Always 0 on the target
L1=en
L2=tr
code=0,1,0,0,0,0

lines=`wc -l $INPUT_PATH/STS2017.eval/STS.input.track6.$L2-$L1.txt | awk {'print $1'}`
for i in $(seq 1 $lines); do
   echo $code
done > $INPUT_PATH/STS2017.eval/sim/track6.$L2-$L1.0.1-langs
wc -l $INPUT_PATH/STS2017.eval/STS.input.track6.$L2-$L1.txt
wc -l $INPUT_PATH/STS2017.eval/sim/track6.$L2-$L1.0.1-langs
echo


######
# Test 2016
######

L1=en
L2=en
code=0,1,0,0,1,0

lines=`wc -l $INPUT_PATH/$L1.test.1_fold/$L1.input.0.txt | awk {'print $1'}`
for i in $(seq 1 $lines); do
   echo $code
done > $INPUT_PATH/$L1.test.1_fold/sim/$L1-$L2.0.1-langs
wc -l $INPUT_PATH/$L1.test.1_fold/$L1.input.0.txt
wc -l $INPUT_PATH/$L1.test.1_fold/sim/$L1-$L2.0.1-langs
echo


########
# SINGLE
########

# ENGLISH-ENGLISH
#L1=en
#L2=en
#code=0,1,0,0,1,0


# Spanish-Spanish
#L1=es
#L2=es
#code=0,0,1,0,0,1

# ARABIC-Arabic
L1=ar
L2=ar
code=1,0,0,1,0,0

lines=`wc -l $INPUT_PATH/$L1.train.1_fold/$L1.input.0.txt | awk {'print $1'}`
for i in $(seq 1 $lines); do
   echo $code
done > $INPUT_PATH/$L1.train.1_fold/sim/$L1-$L2.0.1-langs
wc -l $INPUT_PATH/$L1.train.1_fold/$L1.input.0.txt
wc -l $INPUT_PATH/$L1.train.1_fold/sim/$L1-$L2.0.1-langs
echo



#1-fold
#java -cp $JAR:$DEPENDENCIES $CLAZZ -f  -o  -l $L1 -m $L2

#10-folds
#for j in $(seq 0 9); do 
#  java -cp $JAR:$DEPENDENCIES $CLAZZ -f $INPUT_PATH/$L1.train.10_fold/$L1.input.$j.txt -o $INPUT_PATH/$L1.train.10_fold/sim/$L1-$L2.$j -l $L1 -m $L2 
#done



######
# PAIR 
######


# ENGLISH-ARABIC
L1=en
L2=ar
code=0,1,0,1,0,0

lines=`wc -l $INPUT_PATH/$L1\_$L2.train.1_fold/$L1\_$L2.input.0.txt | awk {'print $1'}`
for i in $(seq 1 $lines); do
   echo $code
done > $INPUT_PATH/$L1\_$L2.train.1_fold/sim/$L1-$L2.0.1-langs
wc -l $INPUT_PATH/$L1\_$L2.train.1_fold/$L1\_$L2.input.0.txt
wc -l $INPUT_PATH/$L1\_$L2.train.1_fold/sim/$L1-$L2.0.1-langs
echo

# English-Spanish
L1=en
L2=es
code=0,1,0,0,0,1

lines=`wc -l $INPUT_PATH/$L1\_$L2.train.1_fold/$L1\_$L2.input.0.txt | awk {'print $1'}`
for i in $(seq 1 $lines); do
   echo $code
done > $INPUT_PATH/$L1\_$L2.train.1_fold/sim/$L1-$L2.0.1-langs
wc -l $INPUT_PATH/$L1\_$L2.train.1_fold/$L1\_$L2.input.0.txt
wc -l $INPUT_PATH/$L1\_$L2.train.1_fold/sim/$L1-$L2.0.1-langs
echo

#-f /data/alt/corpora/semeval2017/task1/en.train.1_fold/MINI_ENGLISH_ARABIC.txt -o /data/alt/corpora/semeval2017/task1/en.train.1_fold/MINI_ENGLISH_ARABIC.txt.out -l en -m ar


#1-fold
#java -cp $JAR:$DEPENDENCIES $CLAZZ -f  -o $L1-$L2.0 -l $L1 -m $L2


#10-folds

#for j in $(seq 0 9); do 
#  java -cp $JAR:$DEPENDENCIES $CLAZZ -f $INPUT_PATH/$L1\_$L2.train.10_fold/$L1\_$L2.input.$j.txt -o $INPUT_PATH/$L1\_$L2.train.10_fold/sim/$L1-$L2.$j -l $L1 -m $L2 
#done
