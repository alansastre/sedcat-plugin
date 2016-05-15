package es.unileon.sonarqube.sedcat.start;

/**
 * 
 * Clase que contiene las claves para cada tipo de variable usada en la aplicacion
 * estas claves son las que se referencian desde el widget para obtener tales metricas
 * @author alan.sastre
 * @version 1.0
 */


public class SedcatMetricsKeys {

	//variables entrada
	public static final String SUCCESS_UNIT_TESTS_KEY = "success_unit_tests";
	public static final String COVERAGE_UNIT_TESTS_KEY = "coverage_unit_tests";
	public static final String MUTANTS_KEY = "mutations_coverage_tests";
	public static final String NUMBERTESTS_KEY = "number_tests";
	public static final String CODE_LINES_KEY = "number_code_lines";
	
	public static final String COMPLEXITY_CLASS_KEY = "complexity_sedcat_average_class";
	public static final String COMPLEXITY_THRESHOLD_KEY = "complexity_sedcat_threshold";
	
	//variables salida
	public static final String QUALITY_MEASURE_KEY = "quality_measure";
	public static final String ACTIONS_TO_PERFORM_KEY = "actions_to_realize";	
	public static final String ACTION_MESSAGE_KEY = "action_to_realize_message";	
	
	//variables de prueba
	public static final String MESSAGE_KEY_1 = "message_key_1";
	public static final String MESSAGE_KEY_2 = "message_key_2";
	public static final String LINESOFCODE_COMPUTER = "computer_codelines";
	public static final String NUMBERTESTS_COMPUTER = "computer_numbertests";
	public static final String GENERAL_COMPUTER_RESULT = "computer_general_result";
	
	
	private SedcatMetricsKeys() {
		throw new AssertionError("Can not instantiate constants class");
	}
}