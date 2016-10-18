package org.sonar.plugins.sedcat.start;

/**
 * 
 * Clase que contiene las claves para cada tipo de variable usada en la aplicacion
 * estas claves son las que se referencian desde el widget para obtener tales metricas
 * @author alan.sastre
 * @version 1.0
 */

/**
 * Constant class
 *	@author alan.sastre
 *	@version 1.0
 */
public class SedcatMetricsKeys {

	//variables entrada
	public static final String MUTANTS_KEY = "mutations_coverage_tests";
	public static final String COMPLEXITY_CLASS_KEY = "complexity_sedcat_average_class";
	public static final String COMPLEXITY_THRESHOLD_KEY = "complexity_sedcat_threshold";
	
	//variables salida
	public static final String QUALITY_MEASURE_KEY = "quality_measure";
	public static final String ACTIONS_TO_PERFORM_KEY = "actions_to_realize";	
	public static final String ACTIONS_MESSAGE_KEY = "actions_drilldown_message";	
	public static final String QUALITY_MESSAGE_KEY = "quality_drilldown_message";	
	
	private SedcatMetricsKeys() {
		throw new AssertionError("Can not instantiate constants class");
	}
}