# LumpSTS
### Lump participation at SemEval 2017 STS
___
### Set-up and installation

1. Download and install WikiTailor source <br />
`git clone https://github.com/cristinae/WikiTailor.git` <br />
`mvn -DskipTests clean compile assembly:single install` <br />

If you need to annotate corpora: <br />

2. Download and install MADAMIRA jar <br />
[License for downloading](http://innovation.columbia.edu/technologies/cu14012_arabic-language-disambiguation-for-natural-language-processing-applications) <br />
`mvn install:install-file -Dfile={$PATH}/MADAMIRA-release-20160516-2.1/MADAMIRA-release-20160516-2.1.jar -DgroupId=edu.columbia.ccls.madamira  -DartifactId=MADAMIRA-release -Dversion=20160516-2.1 -Dpackaging=jar` <br />

If you need to work with BabelNet indices: <br />

4. Download and install the BabelNet API and its dependencies <br />
[API download] (http://babelnet.org/data/3.7/BabelNet-API-3.7.zip) <br />
`unzip BabelNet-API-3.7.zip` <br />
`mvn install:install-file -Dfile=lib/jltutils-2.2.jar -DgroupId=it.uniroma1.lcl.jlt -DartifactId=jltutils -Dversion=2.2 -Dpackaging=jar` <br />
`unzip -p babelnet-api-3.7.jar META-INF/maven/it.uniroma1.lcl.babelnet/babelnet-api/pom.xml | grep -vP '<(scope|systemPath)>' >babelnet-api-3.7.pom` <br />
(consider using homebrew's ggrep if on OsX)<br />
`mvn install:install-file -Dfile=babelnet-api-3.7.jar -DpomFile=babelnet-api-3.7.pom` <br />

5. Download BabelNet indices and make the API aware of them <br />
[Indices download] (http://babelnet.org/login) <br />
`tar xjvf babelnet-3.7-index.tar.bz2` <br />
- In `./BabelNet-API-3.7/config/babelnet.var.properties` include the path to the index:  <br />
 `babelnet.dir=/home/usr/BabelNet-3.7` <br />
- In `./BabelNet-API-3.7/config/jlt.var.properties` include the path to WordNet:  <br />
 `jlt.wordnetPrefix=/usr/local/share/wordnet` <br />
- Move the `./BabelNet-API-3.7/config` folder to your ${basedir}  <br />

If you need to use the machine learning module: <br />

6. Download and install xgboost <br />
`git clone https://github.com/dmlc/xgboost.git` <br />
which requires the dmlc-core and rabit packages. Download them into xgboost corresponding folders and `make` both of them <br />
`git clone https://github.com/dmlc/dmlc-core.git` <br />
`git clone https://github.com/dmlc/rabit.git` <br />
Now you are ready to compile in folder `./xgboost/jvm-packages`  <br />
`mvn package` <br />
`mvn install` <br />


Finally: <br />

7. Download and install this repository <br />
`git clone https://github.com/albarron/LumpSTS.git` <br />
`mvn clean dependency:copy-dependencies package` <br />

### External resources
1. Download the IXA pipes for tokenisation and lemmatisation. They are used as an external executable, no need for installation.<br />
[Download page](http://ixa2.si.ehu.es/ixa-pipes/download.html)<br />
Include their path in the configuration file lumpSTS.ini<br />

2. Download Moses tokeniser (or better, ask me for a modified version)<br />
Include its path in the configuration file lumpSTS.ini<br /><br />


```
### External software and models
# IXA pipe
ixaTok=/home/cristinae/soft/processors/ixa/ixa-pipe-tok-1.8.5-exec.jar
ixaLem=/home/cristinae/soft/processors/ixa/ixa-pipe-pos-1.5.1-exec.jar
posEs=/home/cristinae/soft/processors/ixa/morph-models-1.5.0/es/es-pos-perceptron-autodict01-ancora-2.0.bin
lemEs=/home/cristinae/soft/processors/ixa/morph-models-1.5.0/es/es-lemma-perceptron-ancora-2.0.bin
posEn=/home/cristinae/soft/processors/ixa/morph-models-1.5.0/en/en-pos-perceptron-autodict01-conll09.bin
lemEn=/home/cristinae/soft/processors/ixa/morph-models-1.5.0/en/en-lemma-perceptron-conll09.bin

# Moses
mosesTok=/home/cristinae/soft/processors/moses/tokenizerNO2html.perl
```

