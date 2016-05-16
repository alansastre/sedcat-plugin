package es.unileon.sonarqube.sedcat.storage;

/**
 *  Reune las constantes que contienen los mensajes específicos que aparecerán
 *  en la pantalla drill-down de la métrica acciones en función del set que haya salido
 *  
 *	@author alan.sastre
 *	@version 1.0
 */
public class ActionsMessageConstants {

	//metrics
	public static final String UNIT_TEST_SUCCESS = "Unit Test Success";
	public static final String UNIT_TEST_COVERAGE= "Unit Test Coverage";
	public static final String MUTATIONS_COVERAGE = "Mutations Coverage";
	public static final String NUMBER_OF_TESTS = "Number Of Tests";
	public static final String COMPLEXITY = "Number Of Tests";
	
	//Common parts
	public static final String MENU_HEADER = "</br>Improve the following project parameters in the following order of priority:</br></br>";
	public static final String STRONG_1 = "<strong>1. ";
	public static final String STRONG_2 = "<strong>2. ";
	public static final String STRONG_3 = "<strong>3. ";
	public static final String STRONG_4 = "<strong>4. ";
	public static final String STRONG_5 = "<strong>5. ";
	public static final String STRONG_BR = "</strong></br>";
	public static final String STRONG_BR_BR = "</strong></br></br>";

	//messages
	public static final String MESSAGE_SET0 = MENU_HEADER
			+ STRONG_1 + UNIT_TEST_SUCCESS + STRONG_BR
			+ STRONG_2 + UNIT_TEST_COVERAGE + STRONG_BR
			+ STRONG_3 + MUTATIONS_COVERAGE + STRONG_BR
			+ STRONG_4 + NUMBER_OF_TESTS + STRONG_BR_BR
			+ "First priority is to fix those tests that are failing to raise the percentage of test cases that pass.</br>"
			+ "Second increase is coverage, will allow successful tests achieve greater amount of code.</br>"
			+ "Thirdly increase mutations coverage helps verify that the code coverage achieved has actually been tested.</br>"
			+ "Lastly it is recommendable add more test cases to finish cover the project code.</br>";

	public static final String MESSAGE_SET1 = MENU_HEADER
			+ STRONG_1 + UNIT_TEST_COVERAGE + STRONG_BR
			+ STRONG_2 + UNIT_TEST_SUCCESS + STRONG_BR
			+ STRONG_3 + MUTATIONS_COVERAGE + STRONG_BR
			+ STRONG_4 + NUMBER_OF_TESTS + STRONG_BR_BR
			+ "In this situation is more priority to fix coverage before success, this means that successful tests are covering "
			+ "little code or not enough.</br>"
			+ "Later, increase mutations coverage helps verify that the code coverage achieved has actually been tested.</br>"
			+ "Lastly it is recommendable add more test cases to finish cover the project code.</br>";
	
	
	public static final String MESSAGE_SET2 = MENU_HEADER
			+ STRONG_1 + UNIT_TEST_SUCCESS + STRONG_BR
			+ STRONG_2 + UNIT_TEST_COVERAGE + STRONG_BR
			+ STRONG_3 + NUMBER_OF_TESTS + STRONG_BR_BR
			+ "First priority is to fix those tests that are failing to raise the percentage of test cases that pass.</br>"
			+ "Second increase is coverage, will allow successful tests achieve greater amount of code.</br>"
			+ "Lastly it is recommendable add more test cases to finish cover the project code.</br>";
	
	
	public static final String MESSAGE_SET3 = MENU_HEADER
			+ STRONG_1 + UNIT_TEST_SUCCESS + STRONG_BR
			+ STRONG_2 + MUTATIONS_COVERAGE + STRONG_BR
			+ STRONG_3 + NUMBER_OF_TESTS + STRONG_BR
			+ STRONG_4 + UNIT_TEST_COVERAGE + STRONG_BR_BR
			+ "First priority is to fix those tests that are failing to raise the percentage of test cases that pass.</br>"
			+ "In this case the level of coverage is high, so it is recommended to fix after the other parameters.</br>"
			+ "Second increase mutations coverage helps verify that the code coverage achieved has actually been tested.</br>"
			+ "Thirdly it is recommendable add more test cases to finish cover the project code.</br>"
			+ "Lastly increase is coverage, will allow successful tests achieve greater amount of code.</br>";

	public static final String MESSAGE_SET4 = MENU_HEADER
			+ STRONG_1 + MUTATIONS_COVERAGE + STRONG_BR
			+ STRONG_2 + UNIT_TEST_COVERAGE + STRONG_BR
			+ STRONG_3 + UNIT_TEST_SUCCESS + STRONG_BR
			+ STRONG_4 + NUMBER_OF_TESTS + STRONG_BR_BR
			+ "In this case the level of coverage and success is high, so it is recommended to fix mutations coverage before. "
			+ "Mutations coverage helps verify that the code coverage achieved has actually been tested.</br>"
			+ "Second increase is coverage, will allow successful tests achieve greater amount of code.</br>"
			+ "Thirdly priority is to fix those tests that are failing to raise the percentage of test cases that pass.</br>"
			+ "Lastly it is recommendable add more test cases to finish cover the project code.</br>";

	
	public static final String MESSAGE_SET5 = MENU_HEADER
			+ STRONG_1 + MUTATIONS_COVERAGE + STRONG_BR
			+ STRONG_2 + NUMBER_OF_TESTS + STRONG_BR
			+ STRONG_3 + UNIT_TEST_SUCCESS + STRONG_BR
			+ STRONG_4 + UNIT_TEST_COVERAGE + STRONG_BR_BR
			+ "In this case the level of coverage and success is high, so it is recommended to fix mutations coverage before. "
			+ "Mutations coverage helps verify that the code coverage achieved has actually been tested.</br>"
			+ "Second it is recommendable add more test cases to finish cover the project code.</br>"
			+ "Thirdly priority is to fix those tests that are failing to raise the percentage of test cases that pass.</br>"
			+ "Lastly increase is coverage, will allow successful tests achieve greater amount of code.</br>";
	
	
	public static final String MESSAGE_SET6 = MENU_HEADER
			+ STRONG_1 + MUTATIONS_COVERAGE + STRONG_BR
			+ STRONG_2 + NUMBER_OF_TESTS + STRONG_BR
			+ "In this case the level of coverage and success is maximum. "
			+ "Increase mutations coverage helps verify that the code coverage achieved has actually been tested.</br>"
			+ "Lastly increase is coverage, will allow successful tests achieve greater amount of code.</br>";
	
	
	
	public static final String MESSAGE_SET7 = MENU_HEADER
			+ STRONG_1 + UNIT_TEST_SUCCESS + STRONG_BR
			+ STRONG_2 + UNIT_TEST_COVERAGE + STRONG_BR
			+ STRONG_3 + MUTATIONS_COVERAGE + STRONG_BR
			+ "First priority is to fix those tests that are failing to raise the percentage of test cases that pass.</br>"
			+ "Second increase is coverage, will allow successful tests achieve greater amount of code.</br>"
			+ "Thirdly increase mutations coverage helps verify that the code coverage achieved has actually been tested.</br>";
	
	

	public static final String MESSAGE_SET8 =  MENU_HEADER
			+ STRONG_1 + UNIT_TEST_COVERAGE + STRONG_BR
			+ STRONG_2 + UNIT_TEST_SUCCESS + STRONG_BR
			+ STRONG_3 + MUTATIONS_COVERAGE + STRONG_BR
			+ "In this situation is more priority to fix coverage before success, this means that successful tests are covering"
			+ " little code or not enough.</br>"
			+ "Second priority is to fix those tests that are failing to raise the percentage of test cases that pass.</br>"
			+ "Thirdly increase mutations coverage helps verify that the code coverage achieved has actually been tested.</br>";
	
	public static final String MESSAGE_SET9 = MENU_HEADER
			+ STRONG_1 + UNIT_TEST_COVERAGE + STRONG_BR
			+ STRONG_2 + MUTATIONS_COVERAGE + STRONG_BR
			+ STRONG_3 + UNIT_TEST_SUCCESS + STRONG_BR
			+ "In this situation is more priority to fix coverage before success, this means that successful tests are covering "
			+ "little code  or not enough.</br>"
			+ "Second increase mutations coverage helps verify that the code coverage achieved has actually been tested.</br>"
			+ "Thirdly priority is to fix those tests that are failing to raise the percentage of test cases that pass.</br>";
			

	public static final String MESSAGE_SET10 = MENU_HEADER
			+ STRONG_1 + UNIT_TEST_COVERAGE + STRONG_BR
			+ STRONG_2 + MUTATIONS_COVERAGE + STRONG_BR
			+ STRONG_3 + NUMBER_OF_TESTS + STRONG_BR
			+ "In this case the level of unit test success is maximum. "
			+ "So it's more priority to fix coverage, this means that successful tests are covering little code or not enough.</br>"
			+ "Second increase mutations coverage helps verify that the code coverage achieved has actually been tested.</br>"
			+ "Lastly it is recommendable add more test cases to finish cover the project code.</br>";

			
	
	
	public static final String MESSAGE_SET11 = MENU_HEADER
			+ STRONG_1 + UNIT_TEST_SUCCESS + STRONG_BR
			+ STRONG_2 + MUTATIONS_COVERAGE + STRONG_BR
			+ STRONG_3 + NUMBER_OF_TESTS + STRONG_BR
			+ "In this case the level of coverage percentage is maximum which means that all code in project has been reached. "
			+ "So it's more priority to fix success, this means there are test cases that are failing.</br>"
			+ "Second increase mutations coverage helps verify that the code coverage achieved has actually been tested.</br>"
			+ "Lastly it is recommendable add more test cases to finish cover the project code.</br>";
	
	
	public static final String MESSAGE_SET12 = "This is the optimal case in which project metrics cannot be improved "
			+ "by have the maximum values. It means that your project has:</br></br>"
			+ STRONG_1 + "Maximum" + UNIT_TEST_SUCCESS + STRONG_BR
			+ STRONG_2 + "Maximum" + UNIT_TEST_COVERAGE + STRONG_BR
			+ STRONG_3 + "Maximum" + MUTATIONS_COVERAGE + STRONG_BR
			+ STRONG_4 + "High level of" + NUMBER_OF_TESTS + STRONG_BR
			+ STRONG_5 + COMPLEXITY +"ideal or below the threshold of allowed complexity."+ STRONG_BR;
	
	
	public static final String MESSAGE_SET13 = MENU_HEADER
			+ STRONG_1 + UNIT_TEST_SUCCESS + STRONG_BR
			+ STRONG_2 + MUTATIONS_COVERAGE + STRONG_BR
			+ "In this case the level of coverage percentage is maximum which means that all code in project has been reached and "
			+ "in addition, the number of test is hight.</br>"
			+ "So it's more priority to fix success, this means there are test cases that are failing.</br>"
			+ "Second increase mutations coverage helps verify that the code coverage achieved has actually been tested.</br>";

	
	
	public static final String MESSAGE_SET14 = MENU_HEADER
			+ STRONG_1 + UNIT_TEST_COVERAGE + STRONG_BR
			+ STRONG_2 + MUTATIONS_COVERAGE + STRONG_BR
			+ "In this case the level of unit test success is maximum and number of test is hight. "
			+ "In this situation is more priority to fix coverage this means that successful tests are covering "
			+ "little code  or not enough.</br>"
			+ "Second increase mutations coverage helps verify that the code coverage achieved has actually been tested.</br>";

	
	public static final String MESSAGE_SET15 = MENU_HEADER
			+ STRONG_1 + MUTATIONS_COVERAGE + STRONG_BR
			+ STRONG_2 + UNIT_TEST_COVERAGE + STRONG_BR
			+ "In this case the level of unit test success is maximum and number of test is hight. "
			+ "Coverage value is high, so its more priority increase mutations coverage which helps verify"
			+ " that code coverage achieved has actually been tested.</br>"
			+ "Lastly the priority is fix coverage value, this means that successful tests are covering "
			+ "little code  or not enough.</br>";

	
	
	
	public static final String MESSAGE_SET16 = "prueba";
	public static final String MESSAGE_SET17 = "prueba";
	public static final String MESSAGE_SET18 = "prueba";
	public static final String MESSAGE_SET19 = "prueba";
	public static final String MESSAGE_SET20 = "prueba";
	public static final String MESSAGE_SET21 = "prueba";
	public static final String MESSAGE_SET22 = "prueba";
	public static final String MESSAGE_SET23 = "prueba";
	public static final String MESSAGE_SET24 = "prueba";
	public static final String MESSAGE_SET25 = "prueba";
	public static final String MESSAGE_SET26 = "prueba";
	public static final String MESSAGE_SET27 = "prueba";
	public static final String MESSAGE_SET28 = "prueba";
	public static final String MESSAGE_SET29 = "prueba";
	public static final String MESSAGE_SET30 = "prueba";
	public static final String MESSAGE_SET31 = "prueba";
	public static final String MESSAGE_SET32 = "prueba";
	public static final String MESSAGE_SET33 = "prueba";
	public static final String MESSAGE_SET34 = "prueba";
	public static final String MESSAGE_SET35 = "prueba";
	public static final String MESSAGE_SET36 = "prueba";
	public static final String MESSAGE_SET37 = "prueba";
	public static final String MESSAGE_SET38 = "prueba";
	public static final String MESSAGE_SET39 = "prueba";
	public static final String MESSAGE_SET40 = "prueba";
	public static final String MESSAGE_SET41 = "prueba";
	public static final String MESSAGE_SET42 = "prueba";
	public static final String MESSAGE_SET43 = "prueba";
	public static final String MESSAGE_SET44 = "prueba";
	public static final String MESSAGE_SET45 = "prueba";
	public static final String MESSAGE_SET46 = "prueba";
	public static final String MESSAGE_SET47 = "prueba";
	public static final String MESSAGE_SET48 = "prueba";
	public static final String MESSAGE_SET49 = "prueba";
	public static final String MESSAGE_SET50 = "prueba";
	public static final String MESSAGE_SET51 = "prueba";
	public static final String MESSAGE_SET52 = "prueba";
	public static final String MESSAGE_SET53 = "prueba";
	public static final String MESSAGE_SET54 = "prueba";
	public static final String MESSAGE_SET55 = "prueba";
	public static final String MESSAGE_SET56 = "prueba";
	public static final String MESSAGE_SET57 = "prueba";
	public static final String MESSAGE_SET58 = "prueba";
	public static final String MESSAGE_SET59 = "prueba";
	public static final String MESSAGE_SET60 = "prueba";
	public static final String MESSAGE_SET61 = "prueba";
	public static final String MESSAGE_SET62 = "prueba";
	public static final String MESSAGE_SET63 = "prueba";
	public static final String MESSAGE_SET64 = "prueba";
	public static final String MESSAGE_SET65 = "prueba";


	private ActionsMessageConstants() {
		throw new AssertionError("Can not instantiate constants class");
	}
}
