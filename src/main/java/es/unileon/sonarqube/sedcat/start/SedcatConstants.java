package es.unileon.sonarqube.sedcat.start;

/**
 * Clase de constantes para el plugin Sedcat. 
 *	@author alan.sastre
 *	@version 1.0
 */
public class SedcatConstants {


  private SedcatConstants() {
//	  throw new AssertionError("Can not instantiate constants class");
  }
  /*
   * variables utilizadas para calcular la cobertura de mutantes en un proyecto
   * no se pueden utilizar en el sensor porque se reinician en cada ejecución
   */
  public static int mutationsTotal = 0;
  public static int mutationsDetected = 0;
  
  
//setting constants keys 
  //directorio reporte mutantes
  public static final String PITEST_REPORT_DIRECTORY_KEY = "sonar.sedcat.pitestReportsDirectory";
  public static final String PITEST_REPORT_DIRECTORY_DEFAULT =  "target/pit-reports";
 
  
  //umbral complejidad
  public static final String COMPLEXITY_THRESOLD_KEY = "sonar.sedcat.complexityThresold";
  public static final String COMPLEXITY_THRESOLD_DEFAULT = "30";
 

}