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

L1=ar
L2=ar
java -cp $JAR:$DEPENDENCIES $CLAZZ -f $INPUT_PATH/STS2017.eval/STS.input.track1.$L1-$L2.txt.trad2en -o $INPUT_PATH/STS2017.eval/sim/track1.$L1-$L2.trad2en.0.6-esa -l en -x $INDEX

L1=en
L2=en
java -cp $JAR:$DEPENDENCIES $CLAZZ -f $INPUT_PATH/STS2017.eval/STS.input.track5.$L1-$L2.txt.trad2es -o $INPUT_PATH/STS2017.eval/sim/track5.$L1-$L2.trad2es.0.6-esa -l es -x $INDEX

L1=es
L2=es
java -cp $JAR:$DEPENDENCIES $CLAZZ -f $INPUT_PATH/STS2017.eval/STS.input.track3.$L1-$L2.txt.trad2en -o $INPUT_PATH/STS2017.eval/sim/track3.$L1-$L2.trad2en.0.6-esa -l en -x $INDEX

L1=en
L2=ar
java -cp $JAR:$DEPENDENCIES $CLAZZ -f $INPUT_PATH/STS2017.eval/STS.input.track2.$L2-$L1.txt.trad2en -o $INPUT_PATH/STS2017.eval/sim/track2.$L2-$L1.trad2en.0.6-esa -l en  -x $INDEX


L1=en
L2=es
java -cp $JAR:$DEPENDENCIES $CLAZZ -f $INPUT_PATH/STS2017.eval/STS.input.track4a.$L2-$L1.txt.trad2en -o $INPUT_PATH/STS2017.eval/sim/track4a.$L2-$L1.trad2en.0.6-esa -l en -x $INDEX
java -cp $JAR:$DEPENDENCIES $CLAZZ -f $INPUT_PATH/STS2017.eval/STS.input.track4b.$L2-$L1.txt.trad2en -o $INPUT_PATH/STS2017.eval/sim/track4b.$L2-$L1.trad2en.0.6-esa -l en -x $INDEX



L1=en
L2=tr
java -cp $JAR:$DEPENDENCIES $CLAZZ -f $INPUT_PATH/STS2017.eval/STS.input.track6.tr-$L1.txt.trad2en -o $INPUT_PATH/STS2017.eval/sim/track6.tr-$L1.trad2en.0.6-esa -l en -x $INDEX


######
# Test 2016
######

L1=en
java -cp $JAR:$DEPENDENCIES $CLAZZ -f $INPUT_PATH/$L1.test.1_fold/$L1.input.0.txt.trad2es -o $INPUT_PATH/$L1.test.1_fold/sim/$L1-$L1.0.trad2es.0.6-esa. -l es -x $INDEX


########
# SINGLE
########

#ENGLISH-ENGLISH
L1=en
java -cp $JAR:$DEPENDENCIES $CLAZZ -f $INPUT_PATH/$L1.train.1_fold/$L1.input.0.txt.trad2es -o $INPUT_PATH/$L1.train.1_fold/sim/$L1-$L1.trad2es.0.6-esa -l es -x $INDEX

# Spanish-Spanish
L1=es
java -cp $JAR:$DEPENDENCIES $CLAZZ -f $INPUT_PATH/$L1.train.1_fold/$L1.input.0.txt.trad2en -o $INPUT_PATH/$L1.train.1_fold/sim/$L1-$L1.trad2en.0.6-esa -l en -x $INDEX

# ARABIC-Arabic
L1=ar
java -cp $JAR:$DEPENDENCIES $CLAZZ -f $INPUT_PATH/$L1.train.1_fold/$L1.input.0.txt.trad2en -o $INPUT_PATH/$L1.train.1_fold/sim/$L1-$L1.trad2en.0.6-esa -l en -x $INDEX



######
# PAIR 
######


# ENGLISH-ARABIC
L1=en
L2=ar
java -cp $JAR:$DEPENDENCIES $CLAZZ -f $INPUT_PATH/$L1\_$L2.train.1_fold/$L1\_$L2.input.0.txt.trad2en -o $INPUT_PATH/$L1\_$L2.train.1_fold/sim/$L1-$L2.trad2en.0.6-esa -l en -x $INDEX

# English-Spanish
L1=en
L2=es
java -cp $JAR:$DEPENDENCIES $CLAZZ -f $INPUT_PATH/$L1\_$L2.train.1_fold/$L1\_$L2.input.0.txt.trad2en -o $INPUT_PATH/$L1\_$L2.train.1_fold/sim/$L1-$L2.trad2en.0.6-esa -l en -x $INDEX


#-f /data/alt/corpora/semeval2017/task1/en.train.1_fold/MINI_ENGLISH_ARABIC.txt -o /data/alt/corpora/semeval2017/task1/en.train.1_fold/MINI_ENGLISH_ARABIC.txt.out -l en -m ar
