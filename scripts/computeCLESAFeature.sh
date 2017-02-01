#!/bin/bash

JAR=/Users/albarron/workspace/WikiTailor/target/WikiTailor-1.0-SNAPSHOT.jar
DEPENDENCIES=/Users/albarron/workspace/WikiTailor/target/dependency/*
INPUT_PATH=/Users/albarron/workspace/LumpSTS/DATA
INDEX=/data/alt/corpora/semeval2017/task1/clesa/index

#################
# Length Factor #
#################

CLAZZ=cat.lump.ir.sts2017.CLEsaSimilarityComputer


######
# Test 2017
######

#L1=ar
#L2=ar
#java -cp $JAR:$DEPENDENCIES $CLAZZ -f $INPUT_PATH/STS2017.eval/STS.input.track1.$L1-$L2.txt -o $INPUT_PATH/STS2017.eval/sim/track1.$L1-$L2.0.3-esa.cosine -l $L1 -x $INDEX

#L1=en
#L2=en
#java -cp $JAR:$DEPENDENCIES $CLAZZ -f $INPUT_PATH/STS2017.eval/STS.input.track5.$L1-$L2.txt -o $INPUT_PATH/STS2017.eval/sim/track5.$L1-$L2.0.3-esa.cosine -l $L1 -x $INDEX

#L1=es
#L2=es
#java -cp $JAR:$DEPENDENCIES $CLAZZ -f $INPUT_PATH/STS2017.eval/STS.input.track3.$L1-$L2.txt -o $INPUT_PATH/STS2017.eval/sim/track3.$L1-$L2.0.3-esa.cosine -l $L1 -x $INDEX

#L1=en
#L2=ar
#java -cp $JAR:$DEPENDENCIES $CLAZZ -f $INPUT_PATH/STS2017.eval/STS.input.track2.$L2-$L1.txt -o $INPUT_PATH/STS2017.eval/sim/track2.$L2-$L1.0.3-esa.cosine -l $L1 -m $L2 -x $INDEX


#L1=en
#L2=es
#java -cp $JAR:$DEPENDENCIES $CLAZZ -f $INPUT_PATH/STS2017.eval/STS.input.track4a.$L2-$L1.txt -o $INPUT_PATH/STS2017.eval/sim/track4a.$L2-$L1.0.3-esa.cosine -l $L1 -m $L2 -x $INDEX
#java -cp $JAR:$DEPENDENCIES $CLAZZ -f $INPUT_PATH/STS2017.eval/STS.input.track4b.$L2-$L1.txt -o $INPUT_PATH/STS2017.eval/sim/track4b.$L2-$L1.0.3-esa.cosine -l $L1 -m $L2 -x $INDEX


#TEMPORAL FOR ENGLISH TURKISH
#L1=en
#L2=en
#java -cp $JAR:$DEPENDENCIES $CLAZZ -f $INPUT_PATH/STS2017.eval/STS.input.track6.tr-$L1.txt -o $INPUT_PATH/STS2017.eval/sim/track6.tr-$L1.0.3-esa.cosine -l $L1 -m $L2 -x $INDEX


######
# Test 2016
######

#L1=en
#java -cp $JAR:$DEPENDENCIES $CLAZZ -f $INPUT_PATH/$L1.test.1_fold/$L1.input.0.txt -o $INPUT_PATH/$L1.test.1_fold/sim/$L1-$L1.0.3-esa.cosine -l $L1 -x $INDEX


########
# SINGLE
########

#ENGLISH-ENGLISH
#L1=en

# Spanish-Spanish
#L1=es

# ARABIC-Arabic
#L1=ar

#1-fold
#java -cp $JAR:$DEPENDENCIES $CLAZZ -f $INPUT_PATH/$L1.train.1_fold/$L1.input.0.txt -o $INPUT_PATH/$L1.train.1_fold/sim/$L1-$L1.0.3-esa.cosine -l $L1 -x $INDEX

#10-folds
#for j in $(seq 0 9); do 
#  java -cp $JAR:$DEPENDENCIES $CLAZZ -f $INPUT_PATH/$L1.train.10_fold/$L1.input.$j.txt -o $INPUT_PATH/$L1.train.10_fold/sim/$L1-$L1.$j.3-esa.cosine -l $L1 -x $INDEX
#done


######
# PAIR 
######


# ENGLISH-ARABIC
#L1=en
#L2=ar

# English-Spanish
L1=en
L2=es


#-f /data/alt/corpora/semeval2017/task1/en.train.1_fold/MINI_ENGLISH_ARABIC.txt -o /data/alt/corpora/semeval2017/task1/en.train.1_fold/MINI_ENGLISH_ARABIC.txt.out -l en -m ar


#1-fold
java -cp $JAR:$DEPENDENCIES $CLAZZ -f $INPUT_PATH/$L1\_$L2.train.1_fold/$L1\_$L2.input.0.txt -o $INPUT_PATH/$L1\_$L2.train.1_fold/sim/$L1-$L2.0.3-esa.cosine -l $L1 -m $L2 -x $INDEX


#10-folds

for j in $(seq 0 0); do 
  java -cp $JAR:$DEPENDENCIES $CLAZZ -f $INPUT_PATH/$L1\_$L2.train.10_fold/$L1\_$L2.input.$j.txt -o $INPUT_PATH/$L1\_$L2.train.10_fold/sim/$L1-$L2.$j.3-esa.cosine -l $L1 -m $L2 -x $INDEX
done
