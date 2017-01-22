package cat.lump.sts2017.ml;

import java.io.File;
import java.util.HashMap;

import cat.lump.aq.basics.log.LumpLogger;

import ml.dmlc.xgboost4j.java.DMatrix;
import ml.dmlc.xgboost4j.java.XGBoost;
import ml.dmlc.xgboost4j.java.XGBoostError;

/**
 * Class to learn and apply a Gradient Boosted Machines on the STS data using the H2O library
 * 
 * @author cristina
 * @since Dec 30, 2016
 */

public class XGBoost4j {
	
	/** Logger */
	private static LumpLogger logger = 
			new LumpLogger (XGBoost4j.class.getSimpleName());

	
	/**
	 * Given a set of eXtreme Gradient Boosting parameters performs n-fold cross-validation on a given
	 * training set. Returns the score on test.

	 * 
	 * @param training
	 * @param nfold
	 * @param iters
	 * @param learningRate
	 * @param gamma
	 * @param max_depth
	 * @param min_child_weight
	 * @return
	 */
	private static float trainWithCV(String training, int nfold, int iters, float learningRate,
			float gamma, int max_depth, int min_child_weight) {
	
		DMatrix trainMat = null;
		try {
			String input = training + ".libsvm";
			trainMat = new DMatrix(input);
		} catch (XGBoostError e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	    HashMap<String, Object> params = new HashMap<String, Object>();
	    params.put("eta", learningRate);
	    params.put("max_depth", max_depth);
	    params.put("min_child_weight", min_child_weight);
	    params.put("silent", 1);   //non-verbose
	    params.put("objective", "reg:linear");
	    params.put("gamma", gamma);

	    //set additional eval_metrics
	    String[] metrics = null;
	    String[] evalHist = null;
		try {
	   			evalHist = XGBoost.crossValidation(trainMat, params, iters, nfold, metrics, null, null);
		} catch (XGBoostError e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String lastEv = evalHist[evalHist.length-1];
		
		System.out.println(lastEv);
		return 0.2f;
		
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
	 */
	private static void trainWithGridSearch(String training) {

		// Trains a model
		// Fixed parameters
		int nfold = 10;
		int iters = 1100;
		
		// Grid intervals
		float learningRateMin = 0.04f;
		float learningRateMax = 0.30f;
		float learningDelta = 0.02f;
		int learningInt = (int) ((learningRateMax-learningRateMin)/learningDelta);
		float gammaMin =  0.0f;
		float gammaMax =  0.5f;
		float gammaDelta = 0.1f;
		int gammaInt = (int) ((gammaMax-gammaMin)/gammaDelta);
		int max_depthMin = 3;
		int max_depthMax = 8;
		int min_child_weightMin = 1;
		int min_child_weightMax = 6;
		// 'max_depth':range(3,10,2),
		// 'min_child_weight':range(1,6,2)
		// 'gamma':[i/10.0 for i in range(0,5)]
		// 'learningRate' 0.05 to 0.3 
		
		// Grid Search
		for (int l=0; l<=learningInt; l++) {
		    float lr = learningRateMin + learningDelta*l;
			for (int g=0; g<=gammaInt; g++) {
			    float gp = gammaMin + gammaDelta*l;
				for (int md=max_depthMin; md<=max_depthMax; md++) {
					for (int mc=min_child_weightMin; mc<=min_child_weightMax; mc++) {
						// The higher the learning rate, the less the number of iterations we need
						int itersW = (int) (iters - 1000*lr);
						float kk = trainWithCV(training, nfold, itersW, lr, gp, md, mc);
						System.out.println("iters: "+ itersW +" lr: "+ lr +" gamma: "+ gp +" maxDepth: "+ md + " minCW: "+mc);
					}
				}
			}
		}
		
		
	}

	/**
	 * Main function to run the class, serves as example
	 * 
	 * @param args
	 * 		-f Input file with the training data features and label as first column
	 *      -t Input file with the test data
	 * 		-m Previously trained model
	 */
	public static void main(String[] args) {
		
		CliGBM cli = new CliGBM();
		cli.parseArguments(args);
		
		String training = null;
		//File scores = null;
		String test = null;
		File model = null;

		training = cli.getTraining();
		//scores = cliGBM.getScores();
		test = cli.getTest();
		model = cli.getModel();
		
		// Convert input into the input xgboost format
		Utils.csv2libsvm(training);
		// Begins the training
		trainWithGridSearch(training);
		

	}



}
