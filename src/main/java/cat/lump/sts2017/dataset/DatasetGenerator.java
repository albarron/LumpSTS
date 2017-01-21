package cat.lump.sts2017.dataset;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cat.lump.aq.basics.check.CHK;
import cat.lump.aq.basics.log.LumpLogger;

/**
 * @author albarron
 *
 */
public class DatasetGenerator {
  
  /** Number of folds generated by default is 1: one single file generated */
  private static final int DEFAULT_FOLDS = 1;
  
  private final int MAX_FOLDS = 20;
  
  /** Default behaviour regarding shufling data (it is set as false) */
//  private static final boolean DEFAULT_SHUFFLE = false;
  
  private static final String DEFAULT_BASE_PATH = "/data/alt/corpora/semeval2017/task1";
  
  private final String BASE_PATH;
  
  private static final String DEFAULT_INFIX = "train";
  
  private String INFIX;
  
  
  
  private  String LANGUAGE;
    
  private int FOLDS;
    
  /** Configuration file section */
//  private final Section section;

  /** Logger */
  private static final LumpLogger logger = 
      new LumpLogger (DatasetGenerator.class.getSimpleName());
  
  private final DatasetHandlerInterface dha;

  public DatasetGenerator(String language, int folds, String corpusPath) {
    //this(language, language, folds, shuffle, iniFile);
    
    FOLDS = validateFolds(folds);
    LANGUAGE = language;
    BASE_PATH = corpusPath;

    dha = DatasetHandlerFactory.getDatasetHandler(LANGUAGE, corpusPath);
    logger.info("Set language: " + LANGUAGE);
    logger.info("Number of folds: " + FOLDS);
    logger.info("Path to the corpus: " + BASE_PATH);
   
  }
  
  public DatasetGenerator(String lan1, String lan2, int folds, String corpusPath) {
    dha = DatasetHandlerFactory.getDatasetHandler(lan1, lan2, corpusPath); 
    
    LANGUAGE = dha.getLanguage();
//    LANGUAGE_2 = lan2;
    FOLDS = validateFolds(folds);
    BASE_PATH = corpusPath;
    
    logger.info("Set language pair: " + LANGUAGE);
    //TODO CHANGE THIS
//    logger.info("Set language 2:" + lan2 ); 
    logger.info("Number of folds: " + FOLDS);
    logger.info("Path to the corpus: " + BASE_PATH);
  }
  

//  public void generateFolds() throws IOException {
//    // generate the folder with the current timestamp
//    File dir = new File(getFolderName());
//    logger.info(String.format("Creating folder %s", dir));
//    dir.mkdir();
//    
//    //Setup the writers for all the folds.
//    List<BufferedWriter> writers = new ArrayList<BufferedWriter>();
//    int i;
//    for (i = 0; i < FOLDS; i++) {
//      writers.add(new BufferedWriter( 
//          new FileWriter(getFoldFileName(dir.toString(), i))));
//    }
//    
//    logger.info(String.format("Creating %d folds", i));
//    
//    i = 0;
//      List<String> lines = dha.getInstances();
//          //FileIO.fileToLines(new File(getInputFileFullPath(f)));
//      
//      for (String line : lines) {
//        writers.get(i).write(line); // we write line j into file i
//        writers.get(i++).write("\n");   // and add a line break
//        if (i==FOLDS) {   // reset the file counter; another option would be a module
//          i = 0;
//        }
//      }      
//    
//
//    // Now we close all the writers
//    for (i = 0; i < FOLDS; i++) {
//      writers.get(i).close();
//    }
//    logger.info("Have a nice day");
//  }
  
  public void generateFolds() throws IOException {
    // generate the folder with the current timestamp
    File dir = new File(getFolderName());
    if (dir.exists()) {
      logger.info(String.format("Backing up existing folder %s", dir));
      dir.renameTo(new File(getFolderNameTimestamp(dir)));
    }
    
    logger.info(String.format("Creating folder %s", dir));
    dir.mkdir();
    
    //Setup the writers for all the folds.
    // Scores
    List<BufferedWriter> writersScores = new ArrayList<BufferedWriter>();
    // Text pairs
    List<BufferedWriter> writersText = new ArrayList<BufferedWriter>();
    // Source text
    List<BufferedWriter> writersSrcText = new ArrayList<BufferedWriter>();
    // Target text
    List<BufferedWriter> writersTrgText = new ArrayList<BufferedWriter>();
    
    int f;
    for (f = 0; f < FOLDS; f++) {
      writersScores.add(new BufferedWriter(
          new FileWriter(getScoreFoldFileName(dir.toString(), f))));
      writersText.add(new BufferedWriter( 
          new FileWriter(getTextFoldFileName(dir.toString(), f))));
      
      writersSrcText.add(new BufferedWriter(
          new FileWriter(getSrcTextFoldFileName(dir.toString(), f))));
      writersTrgText.add(new BufferedWriter(
          new FileWriter(getTrgTextFoldFileName(dir.toString(), f))));
      
    }
    
    logger.info(String.format("Creating %d folds", f));
    
    f = 0;
      List<String> lines = dha.getTexts();
      List<String> scores = dha.getScores();
      CHK.CHECK(lines.size() == scores.size(), 
          "There is something wrong; the number of text and score lines is not the same");
          //FileIO.fileToLines(new File(getInputFileFullPath(f)));
      
      for (int i = 0; i < lines.size(); i++) {
        writersScores.get(f).write(scores.get(i));
        writersScores.get(f).write("\n");
        
        writersText.get(f).write(lines.get(i)); // we write line i into file f
        writersText.get(f).write("\n");   
        
        String[] single = lines.get(i).split("\t");
        writersSrcText.get(f).write(single[0]);
        writersSrcText.get(f).write("\n");
        
        writersTrgText.get(f).write(single[1]);
        writersTrgText.get(f++).write("\n"); // and add a line break
        if (f==FOLDS) {   // reset the file counter; another option would be a module
          f = 0;
        }
      }

    // Now we close all the writers
    for (f = 0; f < FOLDS; f++) {
      writersScores.get(f).close();
      writersText.get(f).close();
      writersSrcText.get(f).close();
      writersTrgText.get(f).close();
    }
    logger.info("Have a nice day");
  }
  

  
  public void selectDatasets() throws IOException {
    BufferedReader scanner = new BufferedReader(new InputStreamReader(System.in));
    
    System.out.println("Include every subcorpus available? [y,n] (y if empty)");
    if (yesOrEmpty(scanner.readLine().trim())) {
      dha.activateAllCorpus();
      return;
    }
    
    for (String availableCorpus : dha.getAvailableCorpora()) {
      System.out.format("Include corpus %s? [y,n] (y if empty)%n", availableCorpus);
      if (yesOrEmpty(scanner.readLine().trim())) {
        dha.activateCorpus(availableCorpus);
//        requiredCorpora.put(availableCorpus.getKey(), availableCorpus.getValue());
      }
    }
  }

  private int validateFolds(int folds) {
    CHK.CHECK(folds > 0 && folds <=MAX_FOLDS, 
        String.format("The number of folds must be 0 < folds <= %s", MAX_FOLDS));
    return folds;
  }
  
  private String getScoreFoldFileName(String path, int i) {
    return String.format("%s%s%s.scores.%d.txt", 
        path, 
        File.separator, 
        dha.getLanguage(), 
        i);
  }

  private String getTextFoldFileName(String path, int i) {
    return String.format("%s%s%s.input.%d.txt", 
        path, 
        File.separator, 
        dha.getLanguage(), 
        i);
  }
  
  private String getSrcTextFoldFileName(String path, int i) {
    return String.format("%s%s%s.src.input.%d.txt", 
        path, 
        File.separator, 
        dha.getLanguage(), 
        i);
  }
  
  private String getTrgTextFoldFileName(String path, int i) {
    return String.format("%s%s%s.trg.input.%d.txt", 
        path, 
        File.separator, 
        dha.getLanguage(), 
        i);
  }

  /**
   * Combines the base path to the corpus with the language (pair) and the 
   * timestamp in order to generate one unique folder with the newly generated 
   * dataset.
   * 
   * @return
   *        [BASE_PATH]/[lang].[FOLDS]_fold
   */     
  private String getFolderName() {
    String outPath = String.format("%s%s%s.%s.%d_fold", 
          BASE_PATH, File.separator, LANGUAGE, INFIX, FOLDS);
    return outPath;
  }
  
  private String getFolderNameTimestamp(File dir) {
    Date now = new Date();
    SimpleDateFormat dateFormat = new SimpleDateFormat("M_D_hh_mm_ss");
    String path = String.format("%s.%s", dir.toString(), dateFormat.format(now));
    return path;
  }
  
//  /**
//   * Combines the base path to the corpus with the language (pair) and the 
//   * timestamp in order to generate one unique folder with the newly generated 
//   * dataset.
//   * 
//   * @return
//   *        [BASE_PATH]/[lang].[timestamp]
//   */     
//  private String getFolderNameTimestamp() {
//    // TODO LANGUAGE WILL EVENTUALLY BECOME PAIR OR SINGLE
//    // DECIDE IF IT COMES FROM HANDLER OR FROM THIS CLASS
//    Date now = new Date();
//    SimpleDateFormat dateFormat = new SimpleDateFormat("M_D_hh_mm_ss");
//    String outPath = String.format("%s%s%s.%s", BASE_PATH, File.separator, LANGUAGE, dateFormat.format(now) );
//    return outPath;
//  }

//  public void getFolds() {
//    //TODO RETURN SOMETHING
//  }
//  
//  public void getFold(int i) {
//    //TODO return one single fold
//  }
  

  
 
  
  /**
   * Assumes that an empty answer is equivalent to y (yes).
   * 
   * @param option 
   *          a String containing y, n, or empty
   * @return
   *          true if empty or y
   */
  private boolean yesOrEmpty(String option) {
    if (option.isEmpty() || option.equals("y")) {
      return true;
    }
    return false;
  }
  
  
  /**
   * Getting the source language
   * @param scanner
   * @return
   *        the selected language; crashes if language is not available.
   * @throws IOException
   */
  private static String getSourceLanguage(BufferedReader scanner) throws IOException {
    System.out.println("Enter the first language [ar, en, es]: ");
    String lan = scanner.readLine();
    DatasetHandlerFactory.checkLanguageExists(lan);
    return lan;
  }
  
  /**
   * Getting the target language. N
   * @param scanner
   * @param srcLan
   * @return
   *      
   * @throws IOException
   */
  private static String getTargetLanguage(BufferedReader scanner, String srcLan) 
  throws IOException {
    System.out.println("Enter the second language [ar, en, es] (monolingual if empty): ");
    String tLan = scanner.readLine().trim();
    String lan = (tLan.isEmpty()) 
        ? null 
        : tLan;
    if (lan != null) { 
      DatasetHandlerFactory.checkLanguagePairExists(srcLan, lan); 
      if (srcLan.equals(lan)) {
        logger.error("I cannot set up CL with the same language on both sides");
      }
    }
    return lan;
  }
  
//  /**
//   * Getting if we should generate the test partition rather than the training one
//   * @param scanner
//   * THIS DOESN'T WORK. THE TEST IS THE 2016 DATASET (WHEN AVAILABLE) IT HAS TO BE DEFINED MANUALLY
//   * @return
//   * @throws IOException
//   */
//  private static boolean chooseSetPartition(BufferedReader scanner) throws IOException {
//    System.out.println("Generate 2016 test partition? [y/n] (no if empty)");
//    String genTest = scanner.readLine().trim();
//    if (genTest.isEmpty() || genTest.startsWith("n")) {
//      return false;
//    }
//    return true;
//  }
  
  /**
   * Getting the number of folds
   * @param scanner
   * @return
   * @throws IOException
   */
  private static int getFolds(BufferedReader scanner) throws IOException {
    System.out.format("Enter the desired number of folds (%d if empty): %n", DEFAULT_FOLDS);
    String sFolds = scanner.readLine().trim();
    int folds = sFolds.isEmpty()
        ? DEFAULT_FOLDS
        : Integer.valueOf(sFolds);
    return folds;
  }

  private static String getCorpusPath(BufferedReader scanner) throws IOException {
    System.out.format("Parent path to the dataset (default: %s) %n", 
        DEFAULT_BASE_PATH);
    String corpusPath = scanner.readLine().trim();
    if (corpusPath.isEmpty()) {
      return DEFAULT_BASE_PATH;
    }
    String basePath = corpusPath.startsWith(File.separator) 
        ? corpusPath 
        : System.getProperty("user.dir") + File.separator + corpusPath;
    return basePath;
  }
  
  private static String getFolderInfix(BufferedReader scanner) throws IOException {
    System.out.format("Infix label [train/test] (default: %s) %n", 
        DEFAULT_INFIX);
    String readInfix = scanner.readLine().trim();
    if (readInfix.isEmpty()) {
      return DEFAULT_INFIX;
    }
    String infix = readInfix.equals(DEFAULT_INFIX) 
        ? DEFAULT_INFIX 
        : "test";
    return infix;
  }
  
  public void setOutputInfix(String infix) {
    INFIX = infix;
  }
  public static void main(String[] args) throws IOException {
    // A scanner allows us for reading from he command line
    BufferedReader scanner = new BufferedReader(new InputStreamReader(System.in));
    
    // Getting the language(s)
    String lan1 = getSourceLanguage(scanner);
    String lan2 = getTargetLanguage(scanner, lan1);

    String basePath = getCorpusPath(scanner);
    
    String folderInfix = getFolderInfix(scanner);
    
    int folds;
    
    //THIS DOESNT WORK
//    boolean genTest = chooseSetPartition(scanner);
    
//    if (genTest) {
//      folds = 1;
//    } else {
//      folds = getFolds(scanner);
//    }
    
    folds = getFolds(scanner);
    
    //    //Run
    DatasetGenerator dg;
    
    if (lan2 == null) {
      dg = new DatasetGenerator(lan1, folds, basePath);
    } else {
      dg = new DatasetGenerator(lan1, lan2, folds, basePath);
    }
    dg.setOutputInfix(folderInfix);
    dg.selectDatasets();
    dg.generateFolds();
    
  }

    
}
