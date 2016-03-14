package es.unileon.sonarqube.sedcat.start;

/**
 * Constants for the PIT plugins
 * There is a constant for each configuration key.
 * Most of thoses configuration keys, and the javadoc comments are
 * strongly inspired by the maven PIT plugin
 *
 * @author alan.sastre
 */
public class SedcatConstants {


  private SedcatConstants() {

  }

  public static final String SUCCESS_KEY = "success";
  public static final String COVERAGE_KEY = "coverage";
  public static final String MUTANTS_KEY = "mutations";
  public static final String NUMBER_TESTS_KEY = "numbertests";
  public static final String NUMBER_CODE_LINES_KEY = "numbercodelines";
  
  public static final String REPORT_DIRECTORY_DEF = "mutationsPathReport";
  
  
  
 

}