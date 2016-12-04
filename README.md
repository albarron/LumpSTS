# LumpSTS
#### Lump participation at SemEval 2017 STS
___
### Set-up and installation

1. Download and install WikiTailor source

`git clone https://github.com/cristinae/WikiTailor.git`

`mvn -DskipTests clean compile assembly:single install`

2. Download and install MADAMIRA jar

[License for downloading](http://innovation.columbia.edu/technologies/cu14012_arabic-language-disambiguation-for-natural-language-processing-applications)

`mvn install:install-file -Dfile={$PATH}/MADAMIRA-release-20160516-2.1/MADAMIRA-release-20160516-2.1.jar -DgroupId=edu.columbia.ccls.madamira  -DartifactId=MADAMIRA-release -Dversion=20160516-2.1 -Dpackaging=jar`

3. Download and install this repository

`git clone https://github.com/albarron/LumpSTS.git`

### External resources
1. Download the IXA pipes for tokenisation and lemmatisation. They are used as an external executable, no need for installation.

[Download page](http://ixa2.si.ehu.es/ixa-pipes/download.html)

Include their path in the configuration file lumpSTS.ini

```
### External software and models
# IXA pipe
ixaTok=/home/cristinae/soft/processors/ixa/ixa-pipe-tok-1.8.5-exec.jar
ixaLem=/home/cristinae/soft/processors/ixa/ixa-pipe-pos-1.5.1-exec.jar
posEs=/home/cristinae/soft/processors/ixa/morph-models-1.5.0/es/es-pos-perceptron-autodict01-ancora-2.0.bin
lemEs=/home/cristinae/soft/processors/ixa/morph-models-1.5.0/es/es-lemma-perceptron-ancora-2.0.bin
posEn=/home/cristinae/soft/processors/ixa/morph-models-1.5.0/en/en-pos-perceptron-autodict01-conll09.bin
lemEn=/home/cristinae/soft/processors/ixa/morph-models-1.5.0/en/en-lemma-perceptron-conll09.bin

```

