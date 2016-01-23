package es.unileon.sonarqube.sedcat.start;

/**
 * 
 * 
 * @author alan.sastre
 */


public class SedcatMetricsKeys {


	private SedcatMetricsKeys() {
		// Hide utility class constructor
	}

	//variables entrada
	public static final String EXITO_COVERAGE_KEY = "exito_tests";
	public static final String COBERTURA_COVERAGE_KEY = "cobertura_tests";
	public static final String MUTANTS_KEY = "mutantes_tests";
	public static final String NUMBERTESTS_KEY = "numero_tests";
	public static final String CODE_LINES_KEY = "numero_lineascodigo_tests";
	
	
	//variables salida
	public static final String QUALITY_MEASURE_KEY = "quality_measure";
	public static final String ACTIONS_TO_PERFORM_KEY = "actions_to_realize";	
	
	//variables de prueba
	public static final String MESSAGE_KEY_1 = "message_key_1";
	public static final String MESSAGE_KEY_2 = "message_key_2";
	
	
	
	
}