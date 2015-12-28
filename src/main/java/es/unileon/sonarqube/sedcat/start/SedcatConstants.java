package es.unileon.sonarqube.sedcat.start;

/**
 * Constants for the PIT plugins
 * There is a constant for each configuration key.
 * Most of thoses configuration keys, and the javadoc comments are
 * strongly inspired by the maven PIT plugin
 *
 * @author alan.jesus
 */
public class SedcatConstants {


  private SedcatConstants() {

  }

  public static final String SUCCESS_KEY = "exito";
  public static final String COVERAGE_KEY = "coverage";
  
  
  public static final String REPOSITORY_KEY = "pitest";
  public static final String REPOSITORY_NAME = "Pitest";

  public static final String SURVIVED_MUTANT_RULE_KEY = "pitest.survived.mutant";

  public static final String INSUFFICIENT_MUTATION_COVERAGE_RULE_KEY = "pitest.insufficient.mutation.coverage";

  public static final String COVERAGE_RATIO_PARAM = "minimumMutationCoverageRatio";

  public static final String MODE_KEY = "sonar.pitest.mode";

  public static final String MODE_SKIP = "skip";

  public static final String MODE_REUSE_REPORT = "reuseReport";

  public static final String REPORT_DIRECTORY_KEY = "sonar.pitest.reportsDirectory";

  public static final String REPORT_DIRECTORY_DEF = "target/pit-reports";
  public static final String POM_PATH = "targetClasses";


}