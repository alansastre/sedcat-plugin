package org.sonar.plugins.sedcat.start;

/**
 * Clase de constantes para el plugin Sedcat. 
 *	@author alan.sastre
 *	@version 1.0
 */
public class SedcatConstants {


  /*
   * variables utilizadas para calcular la cobertura de mutantes en un proyecto
   * no se pueden utilizar en el sensor porque se reinician en cada ejecución
   */
  public static double mutationsTotal = 0;
  public static double mutationsDetected = 0;
  
  
//settings screen constants keys:
  
  //modo activo
  public static final String ACTIVE_MODE_KEY = "sonar.sedcat.activeMode";
  
  //directorio reporte mutantes
  public static final String PITEST_REPORT_DIRECTORY_KEY = "sonar.sedcat.pitestReportsDirectory";
  public static final String PITEST_REPORT_DIRECTORY_DEFAULT =  "target/pit-reports";

  //umbral complejidad
  public static final String COMPLEXITY_THRESHOLD_KEY = "sonar.sedcat.complexityThreshold";
  public static final String COMPLEXITY_THRESHOLD_DEFAULT = "30";
 

  
  private SedcatConstants() {
	  throw new AssertionError("Can not instantiate constants class");
  }
  
}