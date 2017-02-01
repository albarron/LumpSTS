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
java -cp $JAR:$DEPENDENCIES $CLAZZ -f $INPUT_PATH/STS2017.eval/STS.input.track1.$L1-$L2.txt.trad2en -o $INPUT_PATH/STS2017.eval/sim/track1.$L1-$L2.0.trad2en -l en -m en

L1=en
L2=en
java -cp $JAR:$DEPENDENCIES $CLAZZ -f $INPUT_PATH/STS2017.eval/STS.input.track5.$L1-$L2.txt.trad2es -o $INPUT_PATH/STS2017.eval/sim/track5.$L1-$L2.0.trad2es -l es -m es

L1=es
L2=es
java -cp $JAR:$DEPENDENCIES $CLAZZ -f $INPUT_PATH/STS2017.eval/STS.input.track3.$L1-$L2.txt.trad2en -o $INPUT_PATH/STS2017.eval/sim/track3.$L1-$L2.0.trad2en -l en -m en


L1=en
L2=ar
java -cp $JAR:$DEPENDENCIES $CLAZZ -f $INPUT_PATH/STS2017.eval/STS.input.track2.$L2-$L1.txt.trad2en -o $INPUT_PATH/STS2017.eval/sim/track2.$L2-$L1.0.trad2en -l en -m en


L1=en
L2=es
java -cp $JAR:$DEPENDENCIES $CLAZZ -f $INPUT_PATH/STS2017.eval/STS.input.track4a.$L2-$L1.txt.trad2en -o $INPUT_PATH/STS2017.eval/sim/track4a.$L2-$L1.0.trad2en -l en -m en
java -cp $JAR:$DEPENDENCIES $CLAZZ -f $INPUT_PATH/STS2017.eval/STS.input.track4b.$L2-$L1.txt.trad2en -o $INPUT_PATH/STS2017.eval/sim/track4b.$L2-$L1.0.trad2en -l en -m en


L1=en
L2=tr
java -cp $JAR:$DEPENDENCIES $CLAZZ -f $INPUT_PATH/STS2017.eval/STS.input.track6.$L2-$L1.txt.trad2en -o $INPUT_PATH/STS2017.eval/sim/track6.$L2-$L1.0.trad2en -l en -m en

######
# Test 2016
######

L1=en
L2=en
java -cp $JAR:$DEPENDENCIES $CLAZZ -f $INPUT_PATH/$L1.test.1_fold/$L1.input.0.txt.trad2es -o $INPUT_PATH/$L1.test.1_fold/sim/$L1-$L2.0.trad2es -l es -m es



########
# SINGLE
########

# ENGLISH-ENGLISH
L1=en
L2=en
java -cp $JAR:$DEPENDENCIES $CLAZZ -f $INPUT_PATH/$L1.train.1_fold/$L1.input.0.txt.trad2es -o $INPUT_PATH/$L1.train.1_fold/sim/$L1-$L2.0.trad2es -l es -m es

# Spanish-Spanish
L1=es
L2=es
java -cp $JAR:$DEPENDENCIES $CLAZZ -f $INPUT_PATH/$L1.train.1_fold/$L1.input.0.txt.trad2en -o $INPUT_PATH/$L1.train.1_fold/sim/$L1-$L2.0.trad2en -l en -m en

# ARABIC-Arabic
L1=ar
L2=ar

#1-fold
java -cp $JAR:$DEPENDENCIES $CLAZZ -f $INPUT_PATH/$L1.train.1_fold/$L1.input.0.txt.trad2en -o $INPUT_PATH/$L1.train.1_fold/sim/$L1-$L2.0.trad2en -l en -m en




######
# PAIR 
######


# ENGLISH-ARABIC
L1=en
L2=ar
java -cp $JAR:$DEPENDENCIES $CLAZZ -f $INPUT_PATH/$L1\_$L2.train.1_fold/$L1\_$L2.input.0.txt.trad2en -o $INPUT_PATH/$L1\_$L2.train.1_fold/sim/$L1-$L2.0.trad2en -l en -m en


# English-Spanish
L1=en
L2=es

java -cp $JAR:$DEPENDENCIES $CLAZZ -f $INPUT_PATH/$L1\_$L2.train.1_fold/$L1\_$L2.input.0.txt.trad2en -o $INPUT_PATH/$L1\_$L2.train.1_fold/sim/$L1-$L2.0.trad2en -l en -m en

