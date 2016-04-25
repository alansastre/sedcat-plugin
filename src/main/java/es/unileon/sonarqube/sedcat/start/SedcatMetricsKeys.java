package es.unileon.sonarqube.sedcat.start;

/**
 * 
 * Clase que contiene las claves para cada tipo de variable usada en la aplicacion
 * estas claves son las que se referencian desde el widget para obtener tales metricas
 * @author alan.sastre
 * @version 1.0
 */


public class SedcatMetricsKeys {


	private SedcatMetricsKeys() {
		// Hide utility class constructor
	}

	//variables entrada
	public static final String SUCCESS_UNIT_TESTS_KEY = "success_unit_tests";
	public static final String COVERAGE_UNIT_TESTS_KEY = "coverage_unit_tests";
	public static final String MUTANTS_KEY = "mutations_coverage_tests";
	public static final String NUMBERTESTS_KEY = "number_tests";
	public static final String CODE_LINES_KEY = "number_code_lines";
	
	
	public static final String LINESOFCODE_COMPUTER = "computer_codelines";
	public static final String NUMBERTESTS_COMPUTER = "computer_numbertests";
	public static final String GENERAL_COMPUTER_RESULT = "computer_general_result";
	
	
	//variables salida
	public static final String QUALITY_MEASURE_KEY = "quality_measure";
	public static final String ACTIONS_TO_PERFORM_KEY = "actions_to_realize";	
	
	//variables de prueba
	public static final String MESSAGE_KEY_1 = "message_key_1";
	public static final String MESSAGE_KEY_2 = "message_key_2";

	
	
}