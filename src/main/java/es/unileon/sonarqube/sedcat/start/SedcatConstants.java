package es.unileon.sonarqube.sedcat.start;

/**
 * Clase de constantes para el plugin Sedcat. 
 *	@author alan.sastre
 *	@version 1.0
 */
public class SedcatConstants {


  private SedcatConstants() {

  }

  //setting constants
  public static final String SUCCESS_KEY = "success";
  public static final String COVERAGE_KEY = "coverage";
  public static final String MUTANTS_KEY = "mutations";
  public static final String NUMBER_TESTS_KEY = "numbertests";
  public static final String NUMBER_CODE_LINES_KEY = "numbercodelines";
  
  /*
   * variables utilizadas para calcular la cobertura de mutantes en un proyecto
   * no se pueden utilizar en el sensor porque se reinician en cada ejecución
   */
  public static int mutationsTotal = 0;
  public static int mutationsDetected = 0;
  //directorio reporte mutantes
  public static final String REPORT_DIRECTORY_KEY = "sonar.pitest.reportsDirectory";
  public static final String REPORT_DIRECTORY_DEF = "target/pit-reports";
  
 

}