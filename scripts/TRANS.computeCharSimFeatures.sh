#!/bin/bash

JAR=/Users/albarron/workspace/LumpSTS/target/LumpSTS-0.0.1-SNAPSHOT.jar
DEPENDENCIES=/Users/albarron/workspace/LumpSTS/target/dependency/*
INPUT_PATH=/Users/albarron/workspace/LumpSTS/DATA


################
# CHAR N-GRAMS #
################

CLAZZ=cat.lump.sts2017.similarity.CharNgramsSimilarity

######
# Test 2017
######

L1=ar
L2=ar
for i in $(seq 2 5); do 
  java -cp $JAR:$DEPENDENCIES $CLAZZ -f $INPUT_PATH/STS2017.eval/STS.input.track1.$L1-$L2.txt.trad2en -o $INPUT_PATH/STS2017.eval/sim/track1.$L1-$L2.0.trad2en -l en -n $i
done

L1=en
L2=en
for i in $(seq 2 5); do 
  java -cp $JAR:$DEPENDENCIES $CLAZZ -f $INPUT_PATH/STS2017.eval/STS.input.track5.$L1-$L2.txt.trad2es -o $INPUT_PATH/STS2017.eval/sim/track5.$L1-$L2.0.trad2es -l es -n $i
done

L1=es
L2=es
for i in $(seq 2 5); do 
  java -cp $JAR:$DEPENDENCIES $CLAZZ -f $INPUT_PATH/STS2017.eval/STS.input.track3.$L1-$L2.txt.trad2en -o $INPUT_PATH/STS2017.eval/sim/track3.$L1-$L2.0.trad2en -l en -n $i
done

L1=en
L2=ar
for i in $(seq 2 5); do 
  java -cp $JAR:$DEPENDENCIES $CLAZZ -f $INPUT_PATH/STS2017.eval/STS.input.track2.$L2-$L1.txt.trad2en -o $INPUT_PATH/STS2017.eval/sim/track2.$L2-$L1.0.trad2en -l $L1 -n $i
done

L1=en
L2=es
for i in $(seq 2 5); do 
  java -cp $JAR:$DEPENDENCIES $CLAZZ -f $INPUT_PATH/STS2017.eval/STS.input.track4a.$L2-$L1.txt.trad2en -o $INPUT_PATH/STS2017.eval/sim/track4a.$L2-$L1.0.trad2en -l $L1 -n $i
  java -cp $JAR:$DEPENDENCIES $CLAZZ -f $INPUT_PATH/STS2017.eval/STS.input.track4b.$L2-$L1.txt.trad2en -o $INPUT_PATH/STS2017.eval/sim/track4b.$L2-$L1.0.trad2en -l $L1 -n $i
done

L1=en
L2=tr
for i in $(seq 2 5); do 
  java -cp $JAR:$DEPENDENCIES $CLAZZ -f $INPUT_PATH/STS2017.eval/STS.input.track6.$L2-$L1.txt.trad2en -o $INPUT_PATH/STS2017.eval/sim/track6.$L2-$L1.0.trad2en -l $L1 -n $i
done


######
# Test 2016
######

L1=en
L2=en
for i in $(seq 2 5); do 
  java -cp $JAR:$DEPENDENCIES $CLAZZ -f $INPUT_PATH/$L1.test.1_fold/$L1.input.0.txt.trad2es -o $INPUT_PATH/$L1.test.1_fold/sim/$L1-$L2.0.trad2es -l es -n $i
done


#####
# SINGLE
#####


# ENGLISH
L1=en
L2=en

for i in $(seq 2 5); do 
  java -cp $JAR:$DEPENDENCIES $CLAZZ -f $INPUT_PATH/$L1.train.1_fold/$L1.input.0.txt.trad2es -o $INPUT_PATH/$L1.train.1_fold/sim/$L1-$L2.0.trad2es -l es -n $i
done


# ARABIC
L1=ar
L2=ar

for i in $(seq 2 5); do 
  java -cp $JAR:$DEPENDENCIES $CLAZZ -f $INPUT_PATH/$L1.train.1_fold/$L1.input.0.txt.trad2en -o $INPUT_PATH/$L1.train.1_fold/sim/$L1-$L2.0.trad2en -l en -n $i
done


# Spanish
L1=es
L2=es

for i in $(seq 2 5); do 
  java -cp $JAR:$DEPENDENCIES $CLAZZ -f $INPUT_PATH/$L1.train.1_fold/$L1.input.0.txt.trad2en -o $INPUT_PATH/$L1.train.1_fold/sim/$L1-$L2.0.trad2en -l en -n $i
done



#####
# PAIRS
#####

# ENGLISH-ARABIC
L1=en
L2=ar

for i in $(seq 2 5); do 
  java -cp $JAR:$DEPENDENCIES $CLAZZ -f $INPUT_PATH/$L1\_$L2.train.1_fold/$L1\_$L2.input.0.txt.trad2en -o $INPUT_PATH/$L1\_$L2.train.1_fold/sim/$L1-$L2.0.trad2en -l en -n $i
done


# English-Spanish
L1=en
L2=es

for i in $(seq 2 5); do 
  java -cp $JAR:$DEPENDENCIES $CLAZZ -f $INPUT_PATH/$L1\_$L2.train.1_fold/$L1\_$L2.input.0.txt.trad2en -o $INPUT_PATH/$L1\_$L2.train.1_fold/sim/$L1-$L2.0.trad2en -l en -n $i
done

#10-folds
#for i in $(seq 2 5); do 
#  for j in $(seq 0 9); do 
#    java -cp $JAR:$DEPENDENCIES $CLAZZ -f $INPUT_PATH/$L1\_$L2.train.10_fold/$L1\_$L2.input.$j.txt -o $INPUT_PATH/$L1\_$L2.train.10_fold/sim/$L1-$L2.$j.txt -l $L1 -m $L2 -n $i
#  done
#done