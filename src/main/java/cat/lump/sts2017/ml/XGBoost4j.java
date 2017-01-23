package cat.lump.sts2017.ml;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cat.lump.aq.basics.log.LumpLogger;
import ml.dmlc.xgboost4j.java.Booster;
import ml.dmlc.xgboost4j.java.DMatrix;
import ml.dmlc.xgboost4j.java.XGBoost;
import ml.dmlc.xgboost4j.java.XGBoostError;

/**
 * Class to learn and apply eXtreme Gradient Boosting on the STS data
 * 
 * @author cristina
 * @since Jan 22, 2016
 */

public class XGBoost4j {
	
	/** Logger */
	private static LumpLogger logger = 
			new LumpLogger (XGBoost4j.class.getSimpleName());

	public static final float MAX_SCORE = 10000000f;
	
	
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

		if (evalHist.equals(null)){
			return MAX_SCORE;
		}
		String lastEv = evalHist[evalHist.length-1];

		// we extract the score on test
		Matcher m = Pattern.compile("cv-test-\\w+:(.+)$").matcher(lastEv);
		Float score =  null; 
		if (m.find()) {
		    score = Float.valueOf(m.group(1));
		}

		return score;
		
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
		//iters = 110;
		
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
		
		// Best point will be stored in
		int itersBest = iters;
		float learningBest = 0f;
		float gammaBest = 0f;
		int max_depthBest = 0;
		int min_child_weightBest = 0;
		
		// Grid Search
		float score = MAX_SCORE;
		float scoreBest = MAX_SCORE;
		for (int l=0; l<=learningInt; l++) {
		    float lr = learningRateMin + learningDelta*l;
			// The higher the learning rate, the less the number of iterations we need
			int itersW = (int) (iters - 1000*lr);
			for (int g=0; g<=gammaInt; g++) {
			    float gp = gammaMin + gammaDelta*g;
				for (int md=max_depthMin; md<=max_depthMax; md++) {
					for (int mc=min_child_weightMin; mc<=min_child_weightMax; mc++) {
						score = trainWithCV(training, nfold, itersW, lr, gp, md, mc);
						if (score<scoreBest) {
							itersBest = iters;
							learningBest = lr;
							gammaBest = gp;
							max_depthBest = md;
							min_child_weightBest = mc;
							scoreBest = score;
						}
						System.out.println("iters: "+ itersW +" lr: "+ lr +" gamma: "+ gp +" maxDepth: "+
						md + " minCW: "+mc+ " (score="+score+")" );
					}
				}
			}
		}
		
		logger.info("The best parameters after 10-fold CV are\n    lr: "+ 
				learningBest +" gamma: "+ gammaBest +" maxDepth: "+ max_depthBest + 
				" minCW: "+min_child_weightBest+"  (score="+scoreBest+")");

		try {
			itersBest=itersBest+100;
			trainSingleModel(training, itersBest, learningBest, gammaBest, max_depthBest, min_child_weightBest);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (XGBoostError e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	/**
	 * Trains and saves a model with the given fixed parameters for the boosting
	 * 
	 * @param training
	 * @param iters
	 * @param learningRate
	 * @param gamma
	 * @param max_depth
	 * @param min_child_weight
	 * @throws IOException
	 * @throws XGBoostError
	 */
	private static void trainSingleModel(String training, int iters, float learningRate, float gamma,
			int max_depth, int min_child_weight)  throws IOException, XGBoostError {

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

	    HashMap<String, DMatrix> watches = new HashMap<String, DMatrix>();
	    watches.put("train", trainMat);
	
	    //train a boost model
	    Booster booster = XGBoost.train(trainMat, params, iters, watches, null, null);

	    //save model to modelPath
	    File file = new File("./models");
	    if (!file.exists()) {
	      file.mkdirs();
	    }
	    String modelPath = "./models/xgb."+"LR"+learningRate+"G"+gamma+"MD"+max_depth+"CW"+min_child_weight+".model";
	    booster.saveModel(modelPath);

	    //dump model with feature map
	    //String[] modelInfos = booster.getModelDump("../../demo/data/featmap.txt", false);
	    //saveDumpModel("./model/dump.raw.txt", modelInfos);

	    //save dmatrix into binary buffer
	    //testMat.saveBinary("./model/dtest.buffer");
	}

	

	/**
	 * Given a previously trained model, generates and prints the predictions in a text
	 * file with extension .pred
	 * 
	 * @param test
	 * @param model
	 */
	private static void predictor(String test, String model) {
		
		DMatrix testMat = null;
	    Booster booster = null;
	    float[][] predictions = null;
		try {
			String input = test + ".libsvm";
			testMat = new DMatrix(input);
			booster = XGBoost.loadModel(model);
			predictions = booster.predict(testMat);
		} catch (XGBoostError e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			savePredictions(test, predictions);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	public static void saveDumpModel(String modelPath, String[] modelInfos) throws IOException {
		   try{
		      PrintWriter writer = new PrintWriter(modelPath, "UTF-8");
		      for(int i = 0; i < modelInfos.length; ++ i) {
		        writer.print("booster[" + i + "]:\n");
		        writer.print(modelInfos[i]);
		      }
		      writer.close();
		    } catch (Exception e) {
		      e.printStackTrace();
		    }
	}

	/**
	 * Prints the actual predictions into a file 
	 * 
	 * @param test
	 * @param predictions
	 * @throws IOException
	 */
	public static void savePredictions(String test, float[][] predictions) throws IOException {
		   String output = test + ".pred";
		   try{
		      PrintWriter writer = new PrintWriter(output, "UTF-8");
			  for (int i=0; i<predictions.length; i++) {
			        writer.print(predictions[i][0] +"\n");
			  }
		      writer.close();
		    } catch (Exception e) {
		      e.printStackTrace();
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
		String model = null;

		training = cli.getTraining();
		//scores = cliGBM.getScores();
		test = cli.getTest();
		model = cli.getModel();
		
		// To be sure decimals will be points and no commas:
		Locale.setDefault(new Locale("en"));
		
		if (training!=null && test==null && model==null){
			// Convert input into the input xgboost format
			Utils.csv2libsvm(training);
			// Begins the training
			trainWithGridSearch(training);
		}
		if (training==null && test!=null && model!=null){
			// Convert input into the input xgboost format
			Utils.csv2libsvm(test);
			predictor(test, model);
		}

	}


}
