source=$1
target=$2
path='/home/cristinae/pln/LumpSTS/task1/'

simPath=${source%cris/*}
#simPath=${simPath}'sim/en-en.0.4-'
simPath=${simPath}'sim/'$3'.0.4-'
#tmp=${a#*_}   # remove prefix ending in "_"
#b=${tmp%_*}   # remove suffix starting with "_"

java -cp target/LumpSTS-0.0.1-SNAPSHOT-jar-with-dependencies.jar cat.lump.sts2017.similarity.VectorsSimCalculator -s $path$source -t $path$target -m cosine -o $path${simPath}wNMT.cosine
java -cp target/LumpSTS-0.0.1-SNAPSHOT-jar-with-dependencies.jar cat.lump.sts2017.similarity.VectorsSimCalculator -s $path$source -t $path$target -m jaccard -o $path${simPath}wNMT.jaccard
java -cp target/LumpSTS-0.0.1-SNAPSHOT-jar-with-dependencies.jar cat.lump.sts2017.similarity.VectorsSimCalculator -s $path$source -t $path$target -m KL -o $path${simPath}wNMT.kl
java -cp target/LumpSTS-0.0.1-SNAPSHOT-jar-with-dependencies.jar cat.lump.sts2017.similarity.VectorsSimCalculator -s $path$source -t $path$target -m JS -o $path${simPath}wNMT.js


source=${source/.w.ctx/.l.ctx}
target=${target/.w.ctx/.l.ctx}
java -cp target/LumpSTS-0.0.1-SNAPSHOT-jar-with-dependencies.jar cat.lump.sts2017.similarity.VectorsSimCalculator -s $path$source -t $path$target -m cosine -o $path${simPath}lNMT.cosine
java -cp target/LumpSTS-0.0.1-SNAPSHOT-jar-with-dependencies.jar cat.lump.sts2017.similarity.VectorsSimCalculator -s $path$source -t $path$target -m jaccard -o $path${simPath}lNMT.jaccard
java -cp target/LumpSTS-0.0.1-SNAPSHOT-jar-with-dependencies.jar cat.lump.sts2017.similarity.VectorsSimCalculator -s $path$source -t $path$target -m KL -o $path${simPath}lNMT.kl
java -cp target/LumpSTS-0.0.1-SNAPSHOT-jar-with-dependencies.jar cat.lump.sts2017.similarity.VectorsSimCalculator -s $path$source -t $path$target -m JS -o $path${simPath}lNMT.js

