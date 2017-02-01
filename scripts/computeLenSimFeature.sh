#!/bin/bash

JAR=/Users/albarron/workspace/LumpSTS/target/LumpSTS-0.0.1-SNAPSHOT.jar
DEPENDENCIES=/Users/albarron/workspace/LumpSTS/target/dependency/*
INPUT_PATH=/Users/albarron/workspace/LumpSTS/DATA


#################
# Length Factor #
#################

CLAZZ=cat.lump.sts2017.similarity.LenFactorSimilarity

######
# Test 2017
######

L1=ar
L2=ar
java -cp $JAR:$DEPENDENCIES $CLAZZ -f $INPUT_PATH/STS2017.eval/STS.input.track1.$L1-$L2.txt -o $INPUT_PATH/STS2017.eval/sim/track1.$L1-$L2.0 -l $L1 -m $L2

L1=en
L2=en
java -cp $JAR:$DEPENDENCIES $CLAZZ -f $INPUT_PATH/STS2017.eval/STS.input.track5.$L1-$L2.txt -o $INPUT_PATH/STS2017.eval/sim/track5.$L1-$L2.0 -l $L1 -m $L2

L1=es
L2=es
java -cp $JAR:$DEPENDENCIES $CLAZZ -f $INPUT_PATH/STS2017.eval/STS.input.track3.$L1-$L2.txt -o $INPUT_PATH/STS2017.eval/sim/track3.$L1-$L2.0 -l $L1 -m $L2


L1=en
L2=ar
java -cp $JAR:$DEPENDENCIES $CLAZZ -f $INPUT_PATH/STS2017.eval/STS.input.track2.$L2-$L1.txt -o $INPUT_PATH/STS2017.eval/sim/track2.$L2-$L1.0 -l $L1 -m $L2


L1=en
L2=es
java -cp $JAR:$DEPENDENCIES $CLAZZ -f $INPUT_PATH/STS2017.eval/STS.input.track4a.$L2-$L1.txt -o $INPUT_PATH/STS2017.eval/sim/track4a.$L2-$L1.0 -l $L1 -m $L2
java -cp $JAR:$DEPENDENCIES $CLAZZ -f $INPUT_PATH/STS2017.eval/STS.input.track4b.$L2-$L1.txt -o $INPUT_PATH/STS2017.eval/sim/track4b.$L2-$L1.0 -l $L1 -m $L2


L1=en
L2=tr
java -cp $JAR:$DEPENDENCIES $CLAZZ -f $INPUT_PATH/STS2017.eval/STS.input.track6.$L2-$L1.txt -o $INPUT_PATH/STS2017.eval/sim/track6.$L2-$L1.0 -l $L1 -m $L2

######
# Test 2016
######

L1=en
L2=en
java -cp $JAR:$DEPENDENCIES $CLAZZ -f $INPUT_PATH/$L1.test.1_fold/$L1.input.0.txt -o $INPUT_PATH/$L1.test.1_fold/sim/$L1-$L2.0 -l $L1 -m $L2



########
# SINGLE
########

# ENGLISH-ENGLISH
L1=en
L2=en
java -cp $JAR:$DEPENDENCIES $CLAZZ -f $INPUT_PATH/$L1.train.1_fold/$L1.input.0.txt -o $INPUT_PATH/$L1.train.1_fold/sim/$L1-$L2.0 -l $L1 -m $L2

# Spanish-Spanish
L1=es
L2=es
java -cp $JAR:$DEPENDENCIES $CLAZZ -f $INPUT_PATH/$L1.train.1_fold/$L1.input.0.txt -o $INPUT_PATH/$L1.train.1_fold/sim/$L1-$L2.0 -l $L1 -m $L2

# ARABIC-Arabic
L1=ar
L2=ar

#1-fold
java -cp $JAR:$DEPENDENCIES $CLAZZ -f $INPUT_PATH/$L1.train.1_fold/$L1.input.0.txt -o $INPUT_PATH/$L1.train.1_fold/sim/$L1-$L2.0 -l $L1 -m $L2

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
java -cp $JAR:$DEPENDENCIES $CLAZZ -f $INPUT_PATH/$L1\_$L2.train.1_fold/$L1\_$L2.input.0.txt -o $INPUT_PATH/$L1\_$L2.train.1_fold/sim/$L1-$L2.0 -l $L1 -m $L2

# English-Spanish
L1=en
L2=es
java -cp $JAR:$DEPENDENCIES $CLAZZ -f $INPUT_PATH/$L1\_$L2.train.1_fold/$L1\_$L2.input.0.txt -o $INPUT_PATH/$L1\_$L2.train.1_fold/sim/$L1-$L2.0 -l $L1 -m $L2

#-f /data/alt/corpora/semeval2017/task1/en.train.1_fold/MINI_ENGLISH_ARABIC.txt -o /data/alt/corpora/semeval2017/task1/en.train.1_fold/MINI_ENGLISH_ARABIC.txt.out -l en -m ar


#1-fold
#java -cp $JAR:$DEPENDENCIES $CLAZZ -f $INPUT_PATH/$L1\_$L2.train.1_fold/$L1\_$L2.input.0.txt -o $INPUT_PATH/$L1\_$L2.train.1_fold/sim/$L1-$L2.0 -l $L1 -m $L2


#10-folds

#for j in $(seq 0 9); do 
#  java -cp $JAR:$DEPENDENCIES $CLAZZ -f $INPUT_PATH/$L1\_$L2.train.10_fold/$L1\_$L2.input.$j.txt -o $INPUT_PATH/$L1\_$L2.train.10_fold/sim/$L1-$L2.$j -l $L1 -m $L2 
#done
