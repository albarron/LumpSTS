package cat.lump.sts2017.ml;

import it.uniroma2.sag.kelp.data.dataset.CsvDatasetReader;
import it.uniroma2.sag.kelp.data.dataset.Dataset;
import it.uniroma2.sag.kelp.data.dataset.DatasetReader;
import it.uniroma2.sag.kelp.data.dataset.LibsvmDatasetReader;
import it.uniroma2.sag.kelp.data.dataset.SimpleDataset;
import it.uniroma2.sag.kelp.data.example.Example;
import it.uniroma2.sag.kelp.data.label.Label;
import it.uniroma2.sag.kelp.data.label.StringLabel;
import it.uniroma2.sag.kelp.kernel.Kernel;
import it.uniroma2.sag.kelp.kernel.cache.FixIndexKernelCache;
import it.uniroma2.sag.kelp.kernel.standard.RbfKernel;
import it.uniroma2.sag.kelp.kernel.vector.LinearKernel;
import it.uniroma2.sag.kelp.learningalgorithm.regression.libsvm.EpsilonSvmRegression;
import it.uniroma2.sag.kelp.predictionfunction.regressionfunction.RegressionFunction;
import it.uniroma2.sag.kelp.predictionfunction.regressionfunction.RegressionOutput;
import it.uniroma2.sag.kelp.utils.evaluation.RegressorEvaluator;
import it.uniroma2.sag.kelp.utils.exception.NoSuchPerformanceMeasureException;

import java.io.IOException;
import java.util.Locale;

import cat.lump.aq.basics.log.LumpLogger;

public class KelpRegressor {

  /** Logger */
  private static LumpLogger logger = 
      new LumpLogger (KelpRegressor.class.getSimpleName());
  
  private static final int nfold = 10;
  
//  private static final Label label = new StringLabel("r");
  
//  private static final float[] Cs = {0, 0.001f, 0.01f, 0.1f, 1, 10, 100};
  private static final float[] Cs = {0.001f};
  
  private static final float gammaMin =  0.0f;
  private static final float gammaMax =  0.5f;
  private static final float gammaDelta = 0.1f;
  
  private static final StringLabel label = new StringLabel("reg");
  
  private final float C;
  private final float GAMMA;
  
  public KelpRegressor(float c, float gamma) {
    C=c;
    GAMMA=gamma;l
  }
  
  private static float test(SimpleDataset trainingSet, float c, float gamma, SimpleDataset testSet)
      throws NoSuchPerformanceMeasureException, IOException {
    
    // The epsilon in loss function of the regressor
    float pReg = 0.1f;
    // The regularization parameter of the regressor
//    float c = 2f;
    
    // Kernel for the first representation (0-index)
    Kernel linear = new LinearKernel("all");
    
    // Applying the RBF kernel
    Kernel rbf = new RbfKernel(gamma, linear);
    // Applying a cache
    FixIndexKernelCache kernelCache = new FixIndexKernelCache(
          trainingSet.getNumberOfExamples());
    rbf.setKernelCache(kernelCache);
    
    EpsilonSvmRegression regression = new EpsilonSvmRegression(rbf, label,
        c, pReg);
    
    regression.learn(trainingSet);
    
     RegressionFunction regressor = regression.getPredictionFunction();
 
    RegressorEvaluator evaluator = new RegressorEvaluator(trainingSet.getRegressionProperties());
 
    // For each example from the test set
    for (Example e : testSet.getExamples()) {
      // Predict the value
       RegressionOutput prediction = regressor.predict(e);
      // Print the original and the predicted values
      System.out.format("real value: %s\t-\tpredicted value: %f%n", e.getRegressionValue(label),
          prediction.getScore(label));
      // Update the evaluator
      evaluator.addCount(e, prediction);
    }

    // Get the Mean Squared Error for the targeted label
    float measSquareError = evaluator.getMeanSquaredError(label);

    System.out.println("\nMean Squared Error:\t" + measSquareError);

    return measSquareError;
} 
  
  
  /***
   * Given a training set, explores the parameter space for 4 parameters of the eXtreme Gradient Boosting:
   * 'max_depth':range(1,6,1),
   * 'min_child_weight':range(3,8,1)
   * 'gamma':range(0,0.5,0.1)]
   * 'learningRate':range(0.04,0.30,0.02) 
   * 
   * 10-fold cross-validation is done for every model
   * 
   * TODO: Read intervals from config or command line
   * 
   * @param training
   * @throws Exception 
   */
  private static void trainWithGridSearch(String training) throws Exception {
    
    SimpleDataset dataset = new SimpleDataset();
    // LOADING THE CSV dataset; label is in the first position
    
    
    
    DatasetReader reader = //new LibsvmDatasetReader(training, "all");
    new LibsvmDatasetReader(training, "all", label);
//    LibsvmDatasetReader(String filename, String representationName, StringLabel regressionPropertyName)
    
//    DatasetReader reader = new LibsvmDatasetReader(
//        training, "all", false, CsvDatasetReader.LabelPosition.FIRST_COLUMN, new StringLabel("gold"));

    dataset.populate(reader);
    
    float c;
    
    
    Dataset[] folds = dataset.nFolding(nfold);
    float[] msErrors = new float[folds.length];
    logger.info("Folds generated");
    
    float mean;
    float sd;
    for (int iC = 0 ; iC < Cs.length ; iC++ ) {
      c = Cs[iC];
      for (float gamma = gammaMin; gamma <= gammaMax ; gamma +=gammaDelta) {
        logger.info(String.format("Exploring with c=%f and gamma=%f", c, gamma));
        for (int i = 0; i < nfold; ++i) {
          SimpleDataset testSet = (SimpleDataset) folds[i];
          SimpleDataset trainingSet = getAllExcept(folds, i);
          msErrors[i] = test(trainingSet, c, gamma, testSet);
          
//          try {
//            System.out.println("start testing with C=" + c);
//            msErrors[i] = test(trainingSet, c, testSet);
//          } catch (FileNotFoundException e) {
//            e.printStackTrace();
//          } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//          } catch (NoSuchPerformanceMeasureException e) {
//            e.printStackTrace();
//          } catch (IOException e) {
//            e.printStackTrace();
//          }
//        }
          
        }
        mean = getMean(msErrors);
        sd = getSD(msErrors);
        System.out.println(msErrors.length);
        logger.info(String.format("c=%f\tgamma=%f\t MSE = %f+-%f", c, gamma, mean, sd));
      }
    }
 
        

    
//    // Trains a model
//    // Fixed parameters
//    int iters = 1100;
//    //iters = 110;
//    
//    // Grid intervals
//    float learningRateMin = 0.04f;
//    float learningRateMax = 0.30f;
//    float learningDelta = 0.02f;
//    int learningInt = (int) ((learningRateMax-learningRateMin)/learningDelta);
//    
//    int gammaInt = (int) ((gammaMax-gammaMin)/gammaDelta);
//    int max_depthMin = 3;
//    int max_depthMax = 8;
//    int min_child_weightMin = 1;
//    int min_child_weightMax = 6;
//    // 'max_depth':range(3,10,2),
//    // 'min_child_weight':range(1,6,2)
//    // 'gamma':range(0,0.5,0.1)
//    // 'learningRate':range(0.04,0.3,0.02) 
//    
//    // Best point will be stored in
//    int itersBest = iters;
//    float learningBest = 0f;
//    float gammaBest = 0f;
//    int max_depthBest = 0;
//    int min_child_weightBest = 0;
//    
//    // Grid Search
//    float score = MAX_SCORE;
//    float scoreBest = MAX_SCORE;
//    for (int l=0; l<=learningInt; l++) {
//        float lr = learningRateMin + learningDelta*l;
//      // The higher the learning rate, the less the number of iterations we need
//      int itersW = (int) (iters - 1000*lr);
//      for (int g=0; g<=gammaInt; g++) {
//          float gp = gammaMin + gammaDelta*g;
//        for (int md=max_depthMin; md<=max_depthMax; md++) {
//          for (int mc=min_child_weightMin; mc<=min_child_weightMax; mc++) {
//            score = trainWithCV(training, nfold, itersW, lr, gp, md, mc);
//            if (score<scoreBest) {
//              itersBest = iters;
//              learningBest = lr;
//              gammaBest = gp;
//              max_depthBest = md;
//              min_child_weightBest = mc;
//              scoreBest = score;
//            }
//            System.out.println("iters: "+ itersW +" lr: "+ lr +" gamma: "+ gp +" maxDepth: "+
//            md + " minCW: "+mc+ " (score="+score+")" );
//          }
//        }
//      }
//    }
//    
//    logger.info("The best parameters after 10-fold CV are\n    lr: "+ 
//        learningBest +" gamma: "+ gammaBest +" maxDepth: "+ max_depthBest + 
//        " minCW: "+min_child_weightBest+"  (score="+scoreBest+")");
//
//    try {
//      itersBest=itersBest+100;
//      trainSingleModel(training, itersBest, learningBest, gammaBest, max_depthBest, min_child_weightBest);
//    } catch (IOException e) {
//      // TODO Auto-generated catch block
//      e.printStackTrace();
//    } catch (XGBoostError e) {
//      // TODO Auto-generated catch block
//      e.printStackTrace();
//    }
  }
  
  private static float getMean(float[] values) {
    float m = 0;
    for (int i = 0; i< values.length; i++) {
      m += values[i];
    }
    
    return m/values.length;
  }
  
  
  private static float getSD(float[] values) {
    float d = 0;
    float m = getMean(values);
    for (int i = 0; i< values.length; i++) {
      d += Math.pow(values[i]-m, 2);
    }
    return (float) Math.sqrt(d/values.length);
    
  }
  
  private static SimpleDataset getAllExcept(Dataset[] folds, int i) {
    SimpleDataset ret = new SimpleDataset();
    for (int k = 0; k < folds.length; ++k) {
      if (i != k)
        ret.addExamples(folds[k]);
    }
    return ret;
  }
  
  
  /**
   * Main function to run the class, serves as example
   * 
   * @param args
   *    -f Input file with the training data features and label as first column
   *      -t Input file with the test data
   *    -m Previously trained model
   * @throws Exception 
   */
  public static void main(String[] args) throws Exception {
    
    System.setProperty("org.slf4j.simpleLogger.defaultLogLevel", "WARN");
    CliSVM cli = new CliSVM();
    cli.parseArguments(args);
    
    String training = null;
    //File scores = null;
    String test = null;
    String model = null;

    training = cli.getTraining();
    //scores = cliGBM.getScores();
    test = cli.getTest();
    model = cli.getModel();
    
    float c = cli.getC();
    float gamma = cli.getGamma(); 
    // To be sure decimals will be points and no commas:
    Locale.setDefault(new Locale("en"));
//    
    KelpRegressor reg = new KelpRegressor(c, gamma);
    
    if (training!=null && test==null && model==null){
      // Convert input into the input xgboost format
      String trainLibsvm = Utils.csv2libsvm(training);
      logger.info("Libsvm dataset generated");
      // Begins the training
      logger.info("Trying to do tuning");
      trainWithGridSearch(trainLibsvm);
    }
    
    
    //TODO from here
//    if (training!= null && model != null) {
//   // Convert input into the input xgboost format
//      Utils.csv2libsvm(training);
//      
//      // Begins the training
//    
//      trainWithGridSearch(training);
//    }
//    
//    if (training==null && test!=null && model!=null){
//      // Convert input into the input xgboost format
//      Utils.csv2libsvm(test);
//      System.out.println("STILL TO BE DONE. LOOK AT THE MAIN");
////      predictor(test, model);
//    }

  }

  
  
}
