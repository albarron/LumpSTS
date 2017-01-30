# Similarity measures calculation

How to run the different similarity measures.


## Similarity among context vectors from an NMT system

Once the context vectors have been estimated for the two files of aligned sentences, calculate the similarity between those vectors using the cat.lump.sts2017.similarity.VectorsSimCalculator on the two files of vectors as follows:

```
java -cp target/LumpSTS-0.0.1-SNAPSHOT-jar-with-dependencies.jar cat.lump.sts2017.similarity.VectorsSimCalculator -h

usage: VectorsSimCalculator

 -h,--help            This help
 -m,--measure <arg>   Similarity measure to use
                      [cosine(default)/jaccard/KL/JS]
 -o,--output <arg>    Output file to store the similarities
 -s,--source <arg>    File with the vectors for the source sentences
 -t,--target <arg>    File with the vectors for the target sentences

ex:

java -cp target/LumpSTS-0.0.1-SNAPSHOT-jar-with-dependencies.jar cat.lump.sts2017.similarity.VectorsSimCalculator -s /home/cristinae/pln/LumpSTS/task1/ar.train.1_fold/cris/ar.src.input.0.txt.bpe.w.ctx -t /home/cristinae/pln/LumpSTS/task1/ar.train.1_fold/cris/ar.trg.input.0.txt.bpe.w.ctx -m cosine -o /home/cristinae/pln/LumpSTS/task1/ar.train.1_fold/sim/ar-ar.0.4-wNMTcosine

```


## Similarity among sentence-level word (BabelNet IDs) vector representations 

Given the embeddings calculated on a corpus of text made of BabelNet IDs, calculate the similarity for pairs of aligned sentences in two different files using the cat.lump.sts2017.similarity.SentenceW2VSimCalculator class as follows:

```
java -cp target/LumpSTS-0.0.1-SNAPSHOT-jar-with-dependencies.jar cat.lump.sts2017.similarity.SentenceW2VSimCalculator -h 

usage: SentenceW2VSimCalculator
 -e,--embeddings <arg>   File with the word embeddings in w2v format
 -h,--help               This help
 -m,--measure <arg>      Similarity measure to use
                         [cosine(default)/jaccard/KL/JS]
 -o,--output <arg>       Output file to store the similarities
 -s,--source <arg>       File with the vectors for the source sentences
 -t,--target <arg>       File with the vectors for the target sentences

ex:

java -cp target/LumpSTS-0.0.1-SNAPSHOT-jar-with-dependencies.jar cat.lump.sts2017.similarity.SentenceW2VSimCalculator -e /media/cristinae/DATA1/pln/semeval/w2v/corpusAll.all.b.300.w2v  -s /home/cristinae/pln/LumpSTS/task1/ar.train.1_fold/cris/ar.src.input.0.txt.b -t /home/cristinae/pln/LumpSTS/task1/ar.train.1_fold/cris/ar.trg.input.0.txt.b -m cosine -o /home/cristinae/pln/LumpSTS/task1/ar.train.1_fold/sim/ar-ar.0.5-BNallcosine

```

