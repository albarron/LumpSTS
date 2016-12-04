# Preprocessing for STS 2017

Run any preprocessing and annotation through the cat.lump.sts2017.prepro.Annotator class:

```
java -cp LumpSTS-0.0.1-SNAPSHOT-jar-with-dependencies.jar cat.lump.sts2017.prepro.Annotator -h

usage: Annotator
 -a,--annotation <arg>   Annotation layer (tok, lem)
 -c,--config <arg>       Configuration file for the lumpSTS project
 -h,--help               This help
 -i,--input <arg>        Input file to annotate -one sentence per line-
 -l,--language <arg>     Language of the input text (ar/en/es)

```
