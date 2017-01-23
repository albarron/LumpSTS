# Training and testing a dataset with XGBools

Machine learning system with cat.lump.sts2017.ml.XGBoost4j class:

```
java -cp target/LumpSTS-0.0.1-SNAPSHOT-jar-with-dependencies.jar cat.lump.sts2017.ml.XGBoost4j -h

usage: XGBoost4j
 -c,--crossvalidation <arg>   number of cross-validation folds [0,...]
 -f,--training <arg>          Input file with the training features in csv
                              format. Labels are expected in the first column.
 -g,--gridsearch <arg>        Grid search parameter optimisation [0/1]
 -h,--help                    This help
 -m,--model <arg>             Previously trained model
 -t,--test <arg>              Input file with the test data.

ex. for learning:
java -cp target/LumpSTS-0.0.1-SNAPSHOT-jar-with-dependencies.jar cat.lump.sts2017.ml.XGBoost4j -f airfoilTrain.csv

ex. for predicting:
java -cp target/LumpSTS-0.0.1-SNAPSHOT-jar-with-dependencies.jar cat.lump.sts2017.ml.XGBoost4j -t airfoilTest.csv -m models/xgb.LR0.04G0.5MD8CW4.model
```

### Comments

* By default, 10-fold cross-validation and gridsearch are applied. The code does not recognise -c and -g at the moment.

* Grid explored in the parameter space:
```
    'learningRate':range(0.04,0.3,0.02) 
    'gamma':range(0,0.5,0.1)
    'max_depth':range(3,10,2),
    'min\_child\_weight':range(1,6,2)
```


