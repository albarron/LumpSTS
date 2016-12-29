# Annotation with BabelNet IDs

Include the BabelNet IDs into a file with already annotated data with cat.lump.sts2017.babelNet.DataIDAnnotator class:

```
java -cp LumpSTS-0.0.1-SNAPSHOT-jar-with-dependencies.jar cat.lump.sts2017.babelNet.DataIDAnnotator -h

usage: DataIDAnnotator
 -h,--help             This help
 -i,--input <arg>      Input file to annotate (in Annotator format wpl)
 -l,--language <arg>   Language of the input text (ar/en/es)

ex:
java -cp LumpSTS-0.0.1-SNAPSHOT-jar-with-dependencies.jar cat.lump.sts2017.babelNet.DataIDAnnotator -l en -i file.wpl
```

### Data format

Raw text with one sentence per line. For each token, factors word (w), PoS (p) and lemma (l) are separated by pipes as output by the prepro package. 

Input: w1|p1|l1 w2|p1|l2 ... wn|pn|ln

Output: w1|p1|l1|b1 w2|p1|l2|b2 ... wn|pn|ln|bn
