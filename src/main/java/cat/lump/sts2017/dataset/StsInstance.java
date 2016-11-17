package cat.lump.sts2017.dataset;

import cat.lump.aq.basics.check.CHK;

/**
 * A container for a single STS instance. 
 * It checks that the elements ---sentences and score--- are acceptable
 * and provides getters to access them.
 *  
 * @author albarron
 *
 */
public class StsInstance {

  /** (Unique?) id for the instance */
  private final String id;
  
  /** Text in text 1 */
  private final String text1;
  
  /** Text in text 2 */
  private final String text2;
  
  /** score(text1, text2) */
  private double score;
  
  /** Minimum valid similarity for a pair of texts */
  private static final double SCORE_MIN = 0;
  
  /** Maximum valid similarity for a pair of texts */
  private static final double SCORE_MAX = 5;
  
  private static final double SCORE_DEFAULT_IF_NONE_AVAILABLE = -1;
  
  /**
   * This constructor checks that the texts are both valid (e.g., they
   * are not empty).
   * 
   * In this case, there is no score available (e.g., because the instance
   * belongs to a test set and this is unknown). The assigned score 
   * value is that of  {@code NO_SIMILARITY_AVAILABLE}
   * 
   * @param id 
   *                id for the current instance
   * @param text1
   *                first text of the instance
   * @param text2
   *                second text of the instance
   */
  public StsInstance(String id, String text1, String text2) {
    this(id, text1, text2, SCORE_DEFAULT_IF_NONE_AVAILABLE);
  }
  
  /**
   * This constructor checks that the texts and score are all valid 
   * (e.g., the texts are not empty, 0<= score <=5).
   * 
   * @param id 
   *                id for the current instance
   * @param text1
   *                first text of the instance
   * @param text2
   *                second text of the instance
   * @param score
   *                similarity between the two texts
   */
  public StsInstance(String id, String text1, String text2, double score) {
    checkTextIsValid(text1, "first");
    checkTextIsValid(text2, "second");
    checkScoreIsValid(score);
    this.id = id;
    this.text1 = text1;
    this.text2 = text2;
    this.score = score;
  }
  
  public String getId() {
    return id;
  }
  public String getText1() {
    return text1;
  }
  
  public String getText2() {
    return text2;
  }
  
  public double getScore() {
    return score;
  }
  
  @Override
  public String toString() {
    return String.format("%s\t%s\t%s\t%f", 
        getId(), getText1(), getText2(), getScore());
  }
  /**
   * Does the checks to guarantee the input string is valid. So far it just 
   * checks that the text is not empty. It crashes the code immediately
   * in that case.
   * 
   * @param text
   *            Input sentence.
   * @param side
   *            "left" or "right" as in the STS input file format.
   */
  private void checkTextIsValid(String text, String side) {
    CHK.CHECK(! text.isEmpty(), 
        String.format("The %s text is empty", side)
    );
  }
  
  /**Check if the score lies in a valid range. Otherwise, it crashes the
   * process.
   *   
   * @param score
   *            value in the range [SCORE_MIN,SCORE_MAX] or SCORE_DEFAULT_IF_NONE_AVAILABLE
   */
  private void checkScoreIsValid(double score) {
    if (score == SCORE_DEFAULT_IF_NONE_AVAILABLE) {
      return;
    }
    CHK.CHECK(score >= SCORE_MIN, "The score cannot be lower than zero");
    CHK.CHECK(score <= SCORE_MAX, "The score cannot be higher than five");
  }
}
