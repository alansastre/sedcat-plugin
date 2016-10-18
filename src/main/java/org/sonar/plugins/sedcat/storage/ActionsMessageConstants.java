package org.sonar.plugins.sedcat.storage;

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
	public static final String COMPLEXITY = "Complexity";
	
	//Common parts
	public static final String MENU_HEADER = "</br>Improve the following project parameters in the following order of priority:</br></br>";
	public static final String STRONG_1 = "<strong>1. ";
	public static final String STRONG_2 = "<strong>2. ";
	public static final String STRONG_3 = "<strong>3. ";
	public static final String STRONG_4 = "<strong>4. ";
	public static final String STRONG_5 = "<strong>5. ";
	public static final String STRONG_BR = "</strong></br>";
	public static final String STRONG_BR_BR = "</strong></br></br>";
	public static final String HTML_BR = "</br>";
	
	//common messages
	public static final String COMPLEXITY_MESSAGE = "The first thing is to fix the complexity average by class "
			+ "which affects the parameters of the test cases making tests more laborious and difficult to maintain."+ HTML_BR;
	public static final String UNIT_TEST_SUCCESS_FIRST = "First priority is to fix those tests that are failing to raise the percentage of test cases that pass."+ HTML_BR;
	public static final String UNIT_TEST_COVERAGE_SECOND = "Second increase is coverage, will allow successful tests achieve greater amount of code."+ HTML_BR;
	public static final String MUTATIONS_COVERAGE_THIRD = "Thirdly increase mutations coverage helps verify that the code coverage achieved has actually been tested."+ HTML_BR;
	public static final String NUMBER_OF_TESTS_LAST= "Lastly it is recommendable add more test cases to finish cover the project code." + HTML_BR;
	public static final String UNIT_TEST_COVERAGE_FIRST= "In this situation is more priority to fix coverage before success, this means that successful tests are covering "
			+ "little code or not enough." + HTML_BR;
	public static final String UNIT_TEST_COVERAGE_HIGH = "In this case the level of coverage is high, so it is recommended to fix after the other parameters." + HTML_BR;
	public static final String MUTATIONS_COVERAGE_SECOND = "Second increase mutations coverage helps verify that the code coverage achieved has actually been tested." + HTML_BR;
	public static final String UNIT_TEST_COVERAGE_LAST = "Lastly increase is coverage, will allow successful tests achieve greater amount of code." + HTML_BR;
	public static final String MUTATIONS_COVERAGE_FIRST = "In this case the level of coverage and success is high, so it is recommended to fix mutations coverage before."
			+ HTML_BR + "Mutations coverage helps verify that the code coverage achieved has actually been tested" + HTML_BR;
	public static final String UNIT_TEST_SUCCESS_THIRD = "Thirdly priority is to fix those tests that are failing to raise the percentage of test cases that pass." + HTML_BR;
	
	public static final String UNIT_TEST_SUCCESS_SECOND = "Second priority is to fix those tests that are failing to raise the percentage of test cases that pass." + HTML_BR;

	
	
	//messages
	public static final String MESSAGE_SET0 = MENU_HEADER
			
			+ STRONG_1 + UNIT_TEST_SUCCESS + STRONG_BR
			+ STRONG_2 + UNIT_TEST_COVERAGE + STRONG_BR
			+ STRONG_3 + MUTATIONS_COVERAGE + STRONG_BR
			+ STRONG_4 + NUMBER_OF_TESTS + STRONG_BR_BR
			+ UNIT_TEST_SUCCESS_FIRST 
			+ UNIT_TEST_COVERAGE_SECOND 
			+ MUTATIONS_COVERAGE_THIRD 
			+ NUMBER_OF_TESTS_LAST;

	public static final String MESSAGE_SET1 = MENU_HEADER
			+ STRONG_1 + UNIT_TEST_COVERAGE + STRONG_BR
			+ STRONG_2 + UNIT_TEST_SUCCESS + STRONG_BR
			+ STRONG_3 + MUTATIONS_COVERAGE + STRONG_BR
			+ STRONG_4 + NUMBER_OF_TESTS + STRONG_BR_BR
			+ UNIT_TEST_COVERAGE_FIRST
			+ "Later, increase mutations coverage helps verify that the code coverage achieved has actually been tested.</br>"
			+ NUMBER_OF_TESTS_LAST;
	
	
	public static final String MESSAGE_SET2 = MENU_HEADER
			+ STRONG_1 + UNIT_TEST_SUCCESS + STRONG_BR
			+ STRONG_2 + UNIT_TEST_COVERAGE + STRONG_BR
			+ STRONG_3 + NUMBER_OF_TESTS + STRONG_BR_BR
			+ UNIT_TEST_SUCCESS_FIRST 
			+ UNIT_TEST_COVERAGE_SECOND 
			+ NUMBER_OF_TESTS_LAST;
	
	
	public static final String MESSAGE_SET3 = MENU_HEADER
			+ STRONG_1 + UNIT_TEST_SUCCESS + STRONG_BR
			+ STRONG_2 + MUTATIONS_COVERAGE + STRONG_BR
			+ STRONG_3 + NUMBER_OF_TESTS + STRONG_BR
			+ STRONG_4 + UNIT_TEST_COVERAGE + STRONG_BR_BR
			+ UNIT_TEST_SUCCESS_FIRST 
			+ UNIT_TEST_COVERAGE_HIGH
			+ MUTATIONS_COVERAGE_SECOND
			+ "Thirdly it is recommendable add more test cases to finish cover the project code.</br>"
			+ UNIT_TEST_COVERAGE_LAST;

	public static final String MESSAGE_SET4 = MENU_HEADER
			+ STRONG_1 + MUTATIONS_COVERAGE + STRONG_BR
			+ STRONG_2 + UNIT_TEST_COVERAGE + STRONG_BR
			+ STRONG_3 + UNIT_TEST_SUCCESS + STRONG_BR
			+ STRONG_4 + NUMBER_OF_TESTS + STRONG_BR_BR
			+ MUTATIONS_COVERAGE_FIRST 
			+ UNIT_TEST_COVERAGE_SECOND
			+ UNIT_TEST_SUCCESS_THIRD
			+ NUMBER_OF_TESTS_LAST;

	
	public static final String MESSAGE_SET5 = MENU_HEADER
			+ STRONG_1 + MUTATIONS_COVERAGE + STRONG_BR
			+ STRONG_2 + NUMBER_OF_TESTS + STRONG_BR
			+ STRONG_3 + UNIT_TEST_SUCCESS + STRONG_BR
			+ STRONG_4 + UNIT_TEST_COVERAGE + STRONG_BR_BR
			+ MUTATIONS_COVERAGE_FIRST
			+ "Second it is recommendable add more test cases to finish cover the project code.</br>"
			+ UNIT_TEST_SUCCESS_THIRD
			+ UNIT_TEST_COVERAGE_LAST;
	
	
	public static final String MESSAGE_SET6 = MENU_HEADER
			+ STRONG_1 + MUTATIONS_COVERAGE + STRONG_BR
			+ STRONG_2 + NUMBER_OF_TESTS + STRONG_BR_BR
			+ "In this case the level of coverage and success is maximum. "
			+ "Increase mutations coverage helps verify that the code coverage achieved has actually been tested.</br>"
			+ UNIT_TEST_COVERAGE_LAST + HTML_BR;
	
	
	
	public static final String MESSAGE_SET7 = MENU_HEADER
			+ STRONG_1 + UNIT_TEST_SUCCESS + STRONG_BR
			+ STRONG_2 + UNIT_TEST_COVERAGE + STRONG_BR
			+ STRONG_3 + MUTATIONS_COVERAGE + STRONG_BR_BR
			+ UNIT_TEST_SUCCESS_FIRST
			+ UNIT_TEST_COVERAGE_SECOND 
			+ MUTATIONS_COVERAGE_THIRD;
	
	

	public static final String MESSAGE_SET8 =  MENU_HEADER
			+ STRONG_1 + UNIT_TEST_COVERAGE + STRONG_BR
			+ STRONG_2 + UNIT_TEST_SUCCESS + STRONG_BR
			+ STRONG_3 + MUTATIONS_COVERAGE + STRONG_BR_BR
			+ UNIT_TEST_COVERAGE_FIRST 
			+ UNIT_TEST_SUCCESS_SECOND
			+ MUTATIONS_COVERAGE_THIRD;
	
	public static final String MESSAGE_SET9 = MENU_HEADER
			+ STRONG_1 + UNIT_TEST_COVERAGE + STRONG_BR
			+ STRONG_2 + MUTATIONS_COVERAGE + STRONG_BR
			+ STRONG_3 + UNIT_TEST_SUCCESS + STRONG_BR_BR
			+ UNIT_TEST_COVERAGE_FIRST 
			+ MUTATIONS_COVERAGE_SECOND 
			+ UNIT_TEST_SUCCESS_THIRD;
			

	public static final String MESSAGE_SET10 = MENU_HEADER
			+ STRONG_1 + UNIT_TEST_COVERAGE + STRONG_BR
			+ STRONG_2 + MUTATIONS_COVERAGE + STRONG_BR
			+ STRONG_3 + NUMBER_OF_TESTS + STRONG_BR_BR
			+ "In this case the level of unit test success is maximum. "
			+ "So it's more priority to fix coverage, this means that successful tests are covering little code or not enough.</br>"
			+ MUTATIONS_COVERAGE_SECOND 
			+ NUMBER_OF_TESTS_LAST ;

			
	
	
	public static final String MESSAGE_SET11 = MENU_HEADER
			+ STRONG_1 + UNIT_TEST_SUCCESS + STRONG_BR
			+ STRONG_2 + MUTATIONS_COVERAGE + STRONG_BR
			+ STRONG_3 + NUMBER_OF_TESTS + STRONG_BR_BR
			+ "In this case the level of coverage percentage is maximum which means that all code in project has been reached. "
			+ "So it's more priority to fix success, this means there are test cases that are failing.</br>"
			+ MUTATIONS_COVERAGE_SECOND 
			+ NUMBER_OF_TESTS_LAST ;
	
	
	public static final String MESSAGE_SET12 = "This is the optimal case in which project metrics cannot be improved "
			+ "by have the maximum values. It means that your project has:</br></br>"
			+ STRONG_1 + "Maximum" + UNIT_TEST_SUCCESS + STRONG_BR
			+ STRONG_2 + "Maximum" + UNIT_TEST_COVERAGE + STRONG_BR
			+ STRONG_3 + "Maximum" + MUTATIONS_COVERAGE + STRONG_BR
			+ STRONG_4 + "High level of" + NUMBER_OF_TESTS + STRONG_BR
			+ STRONG_5 + COMPLEXITY +"ideal or below the threshold of allowed complexity."+ STRONG_BR;
	
	
	public static final String MESSAGE_SET13 = MENU_HEADER
			+ STRONG_1 + UNIT_TEST_SUCCESS + STRONG_BR
			+ STRONG_2 + MUTATIONS_COVERAGE + STRONG_BR_BR
			+ "In this case the level of coverage percentage is maximum which means that all code in project has been reached and "
			+ "in addition, the number of test is hight.</br>"
			+ "So it's more priority to fix success, this means there are test cases that are failing.</br>"
			+ MUTATIONS_COVERAGE_SECOND ;

	
	
	public static final String MESSAGE_SET14 = MENU_HEADER
			+ STRONG_1 + UNIT_TEST_COVERAGE + STRONG_BR
			+ STRONG_2 + MUTATIONS_COVERAGE + STRONG_BR_BR
			+ "In this case the level of unit test success is maximum and number of test is hight. "
			+ "In this situation is more priority to fix coverage this means that successful tests are covering "
			+ "little code  or not enough.</br>"
			+ MUTATIONS_COVERAGE_SECOND ;

	
	public static final String MESSAGE_SET15 = MENU_HEADER
			+ STRONG_1 + MUTATIONS_COVERAGE + STRONG_BR
			+ STRONG_2 + UNIT_TEST_COVERAGE + STRONG_BR_BR
			+ "In this case the level of unit test success is maximum and number of test is hight. "
			+ "Coverage value is high, so its more priority increase mutations coverage which helps verify"
			+ " that code coverage achieved has actually been tested.</br>"
			+ "Lastly the priority is fix coverage value, this means that successful tests are covering "
			+ "little code  or not enough.</br>";

	public static final String MESSAGE_SET16 = MENU_HEADER
			+ STRONG_1 + UNIT_TEST_SUCCESS + STRONG_BR
			+ STRONG_2 + MUTATIONS_COVERAGE + STRONG_BR
			+ STRONG_3 + UNIT_TEST_COVERAGE + STRONG_BR_BR
			+ "In this case the level of coverage percentage is high which means that has been reached much of code in project. "
			+ "So it's more priority to fix success, this means there are test cases that are failing.</br>"
			+ MUTATIONS_COVERAGE_SECOND 
			+ "Finally, you should improve coverage to finish covering all the code.</br>";
	
	
	public static final String MESSAGE_SET17 = MENU_HEADER
			+ STRONG_1 + MUTATIONS_COVERAGE + STRONG_BR
			+ STRONG_2 + UNIT_TEST_SUCCESS + STRONG_BR
			+ STRONG_3 + UNIT_TEST_COVERAGE + STRONG_BR_BR
			+ "In this case the level of coverage percentage is high which means that has been reached much of code in project. "
			+ "First increase mutations coverage helps verify that the code coverage achieved has actually been tested.</br>"
			+ "Second it's priority to fix success, this means there are test cases that are failing.</br>"
			+ "Finally, you should improve coverage to finish covering all the code.</br>";
	
	
	
	public static final String MESSAGE_SET18 = MENU_HEADER
			+ STRONG_1 + MUTATIONS_COVERAGE + STRONG_BR
			+ STRONG_2 + UNIT_TEST_SUCCESS + STRONG_BR_BR
			+ "In this case the level of coverage percentage is maximum which means that all code in project has been reached and "
			+ "in addition, the number of test is hight.</br>"
			+ "First increase mutations coverage helps verify that the code coverage achieved has actually been tested.</br>"
			+ "Second it's priority to fix success, this means there are test cases that are failing.</br>";

	
	public static final String MESSAGE_SET19 = MENU_HEADER
			+ STRONG_1 + MUTATIONS_COVERAGE + STRONG_BR_BR
			+ "In this case the level of coverage percentage and unit test success are maximum which means that all code in"
			+ "project has been reached and all test pass. In addition, the number of test is hight.</br>"
			+ "Only it needs to increase mutations coverage, this helps verify that the code coverage achieved has actually been tested.</br>";
	
	
	
	public static final String MESSAGE_SET20 = MENU_HEADER
			+ STRONG_1 + MUTATIONS_COVERAGE + STRONG_BR
			+ STRONG_2 + UNIT_TEST_COVERAGE + STRONG_BR
			+ STRONG_3 + NUMBER_OF_TESTS + STRONG_BR_BR
			+ "In this case the level of unit test success is maximum.</br>"
			+ "First increase mutations coverage helps verify that the code coverage achieved has actually been tested.</br>"
			+ "Second you should improve coverage to finish covering all the code.</br>"
			+ NUMBER_OF_TESTS_LAST ;
			
	
	
	
	public static final String MESSAGE_SET21 = MENU_HEADER
			+ STRONG_1 + MUTATIONS_COVERAGE + STRONG_BR
			+ STRONG_2 + UNIT_TEST_SUCCESS + STRONG_BR
			+ STRONG_3 + NUMBER_OF_TESTS + STRONG_BR_BR
			+ "In this case the level of coverage percentage is maximum which means that all code in project has been reached.</br>"
			+ "First increase mutations coverage helps verify that the code coverage achieved has actually been tested.</br>"
			+ "Second it's priority to fix success, this means there are test cases that are failing.</br>"
			+ NUMBER_OF_TESTS_LAST ;

	
	
	public static final String MESSAGE_SET22 = MENU_HEADER
			+ STRONG_1 + UNIT_TEST_SUCCESS + STRONG_BR
			+ STRONG_2 + MUTATIONS_COVERAGE + STRONG_BR
			+ STRONG_3 + UNIT_TEST_COVERAGE + STRONG_BR
			+ STRONG_4 + NUMBER_OF_TESTS + STRONG_BR_BR
			+ UNIT_TEST_SUCCESS_FIRST 
			+ UNIT_TEST_COVERAGE_HIGH 
			+ MUTATIONS_COVERAGE_SECOND 
			+ "Thirdly increase is coverage, will allow successful tests achieve greater amount of code.</br>"
			+ NUMBER_OF_TESTS_LAST ;
	
	public static final String MESSAGE_SET23 = MENU_HEADER
			+ STRONG_1 + MUTATIONS_COVERAGE + STRONG_BR
			+ STRONG_2 + UNIT_TEST_SUCCESS + STRONG_BR
			+ STRONG_3 + UNIT_TEST_COVERAGE + STRONG_BR
			+ STRONG_4 + NUMBER_OF_TESTS + STRONG_BR_BR
			+ "First increase mutations coverage helps verify that the code coverage achieved has actually been tested.</br>"
			+ "Second it's priority to fix success, this means there are test cases that are failing.</br>"
			+ "Thirdly increase is coverage, will allow successful tests achieve greater amount of code.</br>"
			+ NUMBER_OF_TESTS_LAST ;
	
	
	public static final String MESSAGE_SET24 = MENU_HEADER
			+ STRONG_1 + UNIT_TEST_COVERAGE + STRONG_BR
			+ STRONG_2 + UNIT_TEST_SUCCESS + STRONG_BR
			+ STRONG_3 + NUMBER_OF_TESTS + STRONG_BR_BR
			+ UNIT_TEST_COVERAGE_FIRST 
			+ UNIT_TEST_SUCCESS_SECOND
			+ NUMBER_OF_TESTS_LAST ;	
	
	
	
	public static final String MESSAGE_SET25 = MENU_HEADER
			+ STRONG_1 + UNIT_TEST_COVERAGE + STRONG_BR
			+ STRONG_2 + NUMBER_OF_TESTS + STRONG_BR_BR
			+ "In this case the level of unit test success is maximum.</br>"
			+ "First you should improve coverage to finish covering all the code.</br>"
			+ NUMBER_OF_TESTS_LAST ;	
	

	public static final String MESSAGE_SET26 = MENU_HEADER
			+ STRONG_1 + UNIT_TEST_SUCCESS + STRONG_BR
			+ STRONG_2 + NUMBER_OF_TESTS + STRONG_BR_BR
			+ UNIT_TEST_SUCCESS_FIRST 
			+ NUMBER_OF_TESTS_LAST ;	
	
	public static final String MESSAGE_SET27 = MENU_HEADER
			+ STRONG_1 + NUMBER_OF_TESTS + STRONG_BR_BR
			+ "In this case the level of coverage, success, mutations are maximum and complexity is in threshold allowed.</br>"
			+ "is only necessary to increase the number of test cases, so that there is balance with the size of the project.</br>";	
	
	
	public static final String MESSAGE_SET28 = MENU_HEADER
			+ STRONG_1 + UNIT_TEST_SUCCESS + STRONG_BR
			+ STRONG_2 + UNIT_TEST_COVERAGE + STRONG_BR_BR
			+ UNIT_TEST_SUCCESS_FIRST 
			+ UNIT_TEST_COVERAGE_SECOND  ;
	
	
	public static final String MESSAGE_SET29 = MENU_HEADER
			+ STRONG_1 + UNIT_TEST_COVERAGE + STRONG_BR
			+ STRONG_2 + UNIT_TEST_SUCCESS + STRONG_BR_BR
			+ "First increase is coverage, will allow successful tests achieve greater amount of code.</br>"
			+ UNIT_TEST_SUCCESS_SECOND;
	
	
	
	public static final String MESSAGE_SET30 = MENU_HEADER
			+ STRONG_1 + UNIT_TEST_COVERAGE + STRONG_BR_BR
			+ "In this case the level of success and mutations are maximum and complexity is in threshold allowed.</br>"
			+ "Only it needs to increase coverage, will allow successful tests achieve greater amount of code.</br>";

	public static final String MESSAGE_SET31 = MENU_HEADER
			+ STRONG_1 + UNIT_TEST_SUCCESS + STRONG_BR_BR
			+ "In this case the level of coverage and mutations are maximum and complexity is in threshold allowed.</br>"
			+ "Only it needs to fix those tests that are failing to raise the percentage of test cases that pass.</br>";
	
	
	public static final String MESSAGE_SET32 = MENU_HEADER
			+ STRONG_1 + UNIT_TEST_SUCCESS + STRONG_BR
			+ STRONG_2 + UNIT_TEST_COVERAGE + STRONG_BR
			+ STRONG_3 + NUMBER_OF_TESTS + STRONG_BR_BR
			+ UNIT_TEST_SUCCESS_FIRST 
			+ UNIT_TEST_COVERAGE_SECOND  
			+ NUMBER_OF_TESTS_LAST ;	
	
	
	public static final String MESSAGE_SET33 = MENU_HEADER
			+ STRONG_1 + COMPLEXITY + STRONG_BR
			+ STRONG_2 + UNIT_TEST_SUCCESS + STRONG_BR
			+ STRONG_3 + UNIT_TEST_COVERAGE + STRONG_BR
			+ STRONG_4 + MUTATIONS_COVERAGE + STRONG_BR
			+ STRONG_5 + NUMBER_OF_TESTS + STRONG_BR_BR
			+ COMPLEXITY_MESSAGE
			+ UNIT_TEST_SUCCESS_SECOND
			+ "Thirdly increase is coverage, will allow successful tests achieve greater amount of code.</br>"
			+ "Later increase mutations coverage helps verify that the code coverage achieved has actually been tested.</br>"
			+ NUMBER_OF_TESTS_LAST ;

	
	public static final String MESSAGE_SET34 = MENU_HEADER
			+ STRONG_1 + COMPLEXITY + STRONG_BR
			+ STRONG_2 + UNIT_TEST_COVERAGE + STRONG_BR
			+ STRONG_3 + UNIT_TEST_SUCCESS + STRONG_BR
			+ STRONG_4 + MUTATIONS_COVERAGE + STRONG_BR
			+ STRONG_5 + NUMBER_OF_TESTS + STRONG_BR_BR
			+ COMPLEXITY_MESSAGE
			+ "Second is more priority to fix coverage before success, this means that successful tests are covering "
			+ "little code or not enough.</br>"
			+ "Later, increase mutations coverage helps verify that the code coverage achieved has actually been tested.</br>"
			+ NUMBER_OF_TESTS_LAST ;


	public static final String MESSAGE_SET35 = MENU_HEADER
			+ STRONG_1 + COMPLEXITY + STRONG_BR
			+ STRONG_2 + UNIT_TEST_SUCCESS + STRONG_BR
			+ STRONG_3 + UNIT_TEST_COVERAGE + STRONG_BR
			+ STRONG_4 + NUMBER_OF_TESTS + STRONG_BR_BR
			+ COMPLEXITY_MESSAGE
			+ UNIT_TEST_SUCCESS_SECOND
			+ "Thirdly increase is coverage, will allow successful tests achieve greater amount of code.</br>"
			+ NUMBER_OF_TESTS_LAST ;
	
	
	public static final String MESSAGE_SET36 = MENU_HEADER
			+ STRONG_1 + COMPLEXITY + STRONG_BR
			+ STRONG_2 + UNIT_TEST_SUCCESS + STRONG_BR
			+ STRONG_3 + MUTATIONS_COVERAGE + STRONG_BR
			+ STRONG_4 + NUMBER_OF_TESTS + STRONG_BR
			+ STRONG_5 + UNIT_TEST_COVERAGE + STRONG_BR_BR
			+ COMPLEXITY_MESSAGE
			+ UNIT_TEST_SUCCESS_SECOND
			+ MUTATIONS_COVERAGE_THIRD 
			+ "Later, it is recommendable add more test cases to finish cover the project code.</br>"
			+ UNIT_TEST_COVERAGE_LAST ;


	public static final String MESSAGE_SET37 = MENU_HEADER
			+ STRONG_1 + COMPLEXITY + STRONG_BR
			+ STRONG_2 + MUTATIONS_COVERAGE + STRONG_BR
			+ STRONG_3 + UNIT_TEST_COVERAGE + STRONG_BR
			+ STRONG_4 + UNIT_TEST_SUCCESS + STRONG_BR
			+ STRONG_5 + NUMBER_OF_TESTS + STRONG_BR_BR
			+ COMPLEXITY_MESSAGE
			+ MUTATIONS_COVERAGE_FIRST 
			+ "Thirdly increase is coverage, will allow successful tests achieve greater amount of code.</br>"
			+ "Later, priority is to fix those tests that are failing to raise the percentage of test cases that pass.</br>"
			+ NUMBER_OF_TESTS_LAST ;
	
	
	public static final String MESSAGE_SET38 = MENU_HEADER
			+ STRONG_1 + COMPLEXITY + STRONG_BR
			+ STRONG_2 + MUTATIONS_COVERAGE + STRONG_BR
			+ STRONG_3 + NUMBER_OF_TESTS + STRONG_BR
			+ STRONG_4 + UNIT_TEST_SUCCESS + STRONG_BR
			+ STRONG_5 + UNIT_TEST_COVERAGE + STRONG_BR_BR
			+ COMPLEXITY_MESSAGE
			+ MUTATIONS_COVERAGE_FIRST 
			+ "Thirdly it is recommendable add more test cases to finish cover the project code.</br>"
			+ "Later, priority is to fix those tests that are failing to raise the percentage of test cases that pass.</br>"
			+ UNIT_TEST_COVERAGE_LAST ;
	
	public static final String MESSAGE_SET39 =  MENU_HEADER
			+ STRONG_1 + COMPLEXITY + STRONG_BR
			+ STRONG_2 + MUTATIONS_COVERAGE + STRONG_BR
			+ STRONG_3 + NUMBER_OF_TESTS + STRONG_BR_BR
			+ COMPLEXITY_MESSAGE
			+ "In this case the level of coverage and success is maximum. "
			+ "Increase mutations coverage helps verify that the code coverage achieved has actually been tested.</br>"
			+ UNIT_TEST_COVERAGE_LAST ;
	
	
	
	public static final String MESSAGE_SET40 = MENU_HEADER
			+ STRONG_1 + COMPLEXITY + STRONG_BR
			+ STRONG_2 + UNIT_TEST_SUCCESS + STRONG_BR
			+ STRONG_3 + UNIT_TEST_COVERAGE + STRONG_BR
			+ STRONG_4 + MUTATIONS_COVERAGE + STRONG_BR_BR
			+ COMPLEXITY_MESSAGE
			+ UNIT_TEST_SUCCESS_SECOND
			+ "Thirdly increase is coverage, will allow successful tests achieve greater amount of code.</br>"
			+ "Lastly increase mutations coverage helps verify that the code coverage achieved has actually been tested.</br>";
	
	
	public static final String MESSAGE_SET41 = MENU_HEADER
			+ STRONG_1 + COMPLEXITY + STRONG_BR
			+ STRONG_2 + UNIT_TEST_COVERAGE + STRONG_BR
			+ STRONG_3 + UNIT_TEST_SUCCESS + STRONG_BR
			+ STRONG_4 + MUTATIONS_COVERAGE + STRONG_BR_BR
			+ COMPLEXITY_MESSAGE
			+ UNIT_TEST_COVERAGE_FIRST 
			+ UNIT_TEST_SUCCESS_THIRD
			+ "Lastly increase mutations coverage helps verify that the code coverage achieved has actually been tested.</br>";
	
	
	
	public static final String MESSAGE_SET42 = MENU_HEADER
			+ STRONG_1 + COMPLEXITY + STRONG_BR
			+ STRONG_2 + UNIT_TEST_COVERAGE + STRONG_BR
			+ STRONG_3 + MUTATIONS_COVERAGE + STRONG_BR
			+ STRONG_4 + UNIT_TEST_SUCCESS + STRONG_BR_BR
			+ COMPLEXITY_MESSAGE
			+ UNIT_TEST_COVERAGE_FIRST 
			+ MUTATIONS_COVERAGE_THIRD 
			+ "Lastly priority is to fix those tests that are failing to raise the percentage of test cases that pass.</br>";
	
	
	public static final String MESSAGE_SET43 = MENU_HEADER
			+ STRONG_1 + COMPLEXITY + STRONG_BR
			+ STRONG_2 + UNIT_TEST_COVERAGE + STRONG_BR
			+ STRONG_3 + MUTATIONS_COVERAGE + STRONG_BR
			+ STRONG_4 + NUMBER_OF_TESTS + STRONG_BR_BR
			+ COMPLEXITY_MESSAGE
			+ "In this case the level of unit test success is maximum. "
			+ "So it's more priority to fix coverage, this means that successful tests are covering little code or not enough.</br>"
			+ MUTATIONS_COVERAGE_THIRD 
			+ NUMBER_OF_TESTS_LAST ;
	
	public static final String MESSAGE_SET44 = MENU_HEADER
			+ STRONG_1 + COMPLEXITY + STRONG_BR
			+ STRONG_2 + UNIT_TEST_SUCCESS + STRONG_BR
			+ STRONG_3 + MUTATIONS_COVERAGE + STRONG_BR
			+ STRONG_4 + NUMBER_OF_TESTS + STRONG_BR_BR
			+ COMPLEXITY_MESSAGE
			+ "In this case the level of coverage percentage is maximum which means that all code in project has been reached. "
			+ "So it's more priority to fix success, this means there are test cases that are failing.</br>"
			+ MUTATIONS_COVERAGE_THIRD 
			+ NUMBER_OF_TESTS_LAST ;

	
	public static final String MESSAGE_SET45 = "This is the optimal case in which project metrics cannot be improved "
			+ "except that complexity is high. Parameters to improve: </br></br>"
			+ STRONG_1 + COMPLEXITY + STRONG_BR_BR
			+ COMPLEXITY_MESSAGE;
	
	
	public static final String MESSAGE_SET46 = MENU_HEADER
			+ STRONG_1 + COMPLEXITY + STRONG_BR
			+ STRONG_2 + UNIT_TEST_SUCCESS + STRONG_BR
			+ STRONG_3 + MUTATIONS_COVERAGE + STRONG_BR_BR
			+ COMPLEXITY_MESSAGE
			+ "In this case the level of coverage percentage is maximum which means that all code in project has been reached and "
			+ "in addition, the number of test is hight.</br>"
			+ "So it's more priority to fix success, this means there are test cases that are failing.</br>"
			+ "Lastly increase mutations coverage helps verify that the code coverage achieved has actually been tested.</br>";
	
	
	
	public static final String MESSAGE_SET47 = MENU_HEADER
			+ STRONG_1 + COMPLEXITY + STRONG_BR
			+ STRONG_2 + UNIT_TEST_COVERAGE + STRONG_BR
			+ STRONG_3 + MUTATIONS_COVERAGE + STRONG_BR_BR
			+ COMPLEXITY_MESSAGE
			+ "In this case the level of unit test success is maximum and number of test is hight. "
			+ "In this situation is more priority to fix coverage this means that successful tests are covering "
			+ "little code  or not enough.</br>"
			+ "Lastly increase mutations coverage helps verify that the code coverage achieved has actually been tested.</br>";
	
	
	
	public static final String MESSAGE_SET48 = MENU_HEADER
			+ STRONG_1 + COMPLEXITY + STRONG_BR
			+ STRONG_2 + MUTATIONS_COVERAGE + STRONG_BR
			+ STRONG_3 + UNIT_TEST_COVERAGE + STRONG_BR_BR
			+ COMPLEXITY_MESSAGE
			+ "In this case the level of unit test success is maximum and number of test is hight. "
			+ "Coverage value is high, so its more priority increase mutations coverage which helps verify"
			+ " that code coverage achieved has actually been tested.</br>"
			+ "Lastly the priority is fix coverage value, this means that successful tests are covering "
			+ "little code  or not enough.</br>";
	
	
	
	public static final String MESSAGE_SET49 =  MENU_HEADER
			+ STRONG_1 + COMPLEXITY + STRONG_BR
			+ STRONG_2 + UNIT_TEST_SUCCESS + STRONG_BR
			+ STRONG_3 + MUTATIONS_COVERAGE + STRONG_BR
			+ STRONG_4 + UNIT_TEST_COVERAGE + STRONG_BR_BR
			+ COMPLEXITY_MESSAGE
			+ "In this case the level of coverage percentage is high which means that has been reached much of code in project. "
			+ "So it's more priority to fix success, this means there are test cases that are failing.</br>"
			+ MUTATIONS_COVERAGE_THIRD 
			+ "Finally, you should improve coverage to finish covering all the code.</br>";
	
	
	
	public static final String MESSAGE_SET50 = MENU_HEADER
			+ STRONG_1 + COMPLEXITY + STRONG_BR
			+ STRONG_2 + MUTATIONS_COVERAGE + STRONG_BR
			+ STRONG_3 + UNIT_TEST_SUCCESS + STRONG_BR
			+ STRONG_4 + UNIT_TEST_COVERAGE + STRONG_BR_BR
			+ COMPLEXITY_MESSAGE
			+ "In this case the level of coverage percentage is high which means that has been reached much of code in project. "
			+ "First increase mutations coverage helps verify that the code coverage achieved has actually been tested.</br>"
			+ "Thirdly it's priority to fix success, this means there are test cases that are failing.</br>"
			+ "Finally, you should improve coverage to finish covering all the code.</br>";
	
	
	public static final String MESSAGE_SET51 = MENU_HEADER
			+ STRONG_1 + COMPLEXITY + STRONG_BR
			+ STRONG_2 + MUTATIONS_COVERAGE + STRONG_BR
			+ STRONG_3 + UNIT_TEST_SUCCESS + STRONG_BR_BR
			+ COMPLEXITY_MESSAGE
			+ "In this case the level of coverage percentage is maximum which means that all code in project has been reached and "
			+ "in addition, the number of test is hight.</br>"
			+ MUTATIONS_COVERAGE_THIRD 
			+ "Lastly it's priority to fix success, this means there are test cases that are failing.</br>";
	
	
	
	public static final String MESSAGE_SET52 =  MENU_HEADER
			+ STRONG_1 + COMPLEXITY + STRONG_BR
			+ STRONG_2 + MUTATIONS_COVERAGE + STRONG_BR_BR
			+ COMPLEXITY_MESSAGE
			+ "In this case the level of coverage percentage and unit test success are maximum which means that all code in"
			+ "project has been reached and all test pass. In addition, the number of test is hight.</br>"
			+ "Second it needs to increase mutations coverage, this helps verify that the code coverage achieved has actually been tested.</br>";
	
	
	
	public static final String MESSAGE_SET53 = MENU_HEADER
			+ STRONG_1 + COMPLEXITY + STRONG_BR
			+ STRONG_2 + MUTATIONS_COVERAGE + STRONG_BR
			+ STRONG_3 + UNIT_TEST_COVERAGE + STRONG_BR
			+ STRONG_4 + NUMBER_OF_TESTS + STRONG_BR_BR
			+ COMPLEXITY_MESSAGE
			+ "In this case the level of unit test success is maximum.</br>"
			+ MUTATIONS_COVERAGE_SECOND 
			+ "Thirdly you should improve coverage to finish covering all the code.</br>"
			+ NUMBER_OF_TESTS_LAST ;
	
	
	public static final String MESSAGE_SET54 = MENU_HEADER
			+ STRONG_1 + COMPLEXITY + STRONG_BR
			+ STRONG_2 + MUTATIONS_COVERAGE + STRONG_BR
			+ STRONG_3 + UNIT_TEST_SUCCESS + STRONG_BR
			+ STRONG_4 + NUMBER_OF_TESTS + STRONG_BR_BR
			+ COMPLEXITY_MESSAGE
			+ "In this case the level of coverage percentage is maximum which means that all code in project has been reached.</br>"
			+ MUTATIONS_COVERAGE_SECOND 
			+ "Thirdly it's priority to fix success, this means there are test cases that are failing.</br>"
			+ NUMBER_OF_TESTS_LAST ;
	
	
	
	public static final String MESSAGE_SET55 = MENU_HEADER
			+ STRONG_1 + COMPLEXITY + STRONG_BR
			+ STRONG_2 + UNIT_TEST_SUCCESS + STRONG_BR
			+ STRONG_3 + MUTATIONS_COVERAGE + STRONG_BR
			+ STRONG_4 + UNIT_TEST_COVERAGE + STRONG_BR
			+ STRONG_5 + NUMBER_OF_TESTS + STRONG_BR_BR
			+ COMPLEXITY_MESSAGE
			+ UNIT_TEST_SUCCESS_SECOND
			+ UNIT_TEST_COVERAGE_HIGH 
			+ MUTATIONS_COVERAGE_THIRD 
			+ "Later increase is coverage, will allow successful tests achieve greater amount of code.</br>"
			+ NUMBER_OF_TESTS_LAST ;
	
	
	public static final String MESSAGE_SET56 = MENU_HEADER
			+ STRONG_1 + COMPLEXITY + STRONG_BR
			+ STRONG_2 + MUTATIONS_COVERAGE + STRONG_BR
			+ STRONG_3 + UNIT_TEST_SUCCESS + STRONG_BR
			+ STRONG_4 + UNIT_TEST_COVERAGE + STRONG_BR
			+ STRONG_5 + NUMBER_OF_TESTS + STRONG_BR_BR
			+ COMPLEXITY_MESSAGE
			+ MUTATIONS_COVERAGE_SECOND 
			+ "Thirdly it's priority to fix success, this means there are test cases that are failing.</br>"
			+ "Later increase is coverage, will allow successful tests achieve greater amount of code.</br>"
			+ NUMBER_OF_TESTS_LAST ;
	
	public static final String MESSAGE_SET57 = MENU_HEADER
			+ STRONG_1 + COMPLEXITY + STRONG_BR
			+ STRONG_2 + UNIT_TEST_COVERAGE + STRONG_BR
			+ STRONG_3 + UNIT_TEST_SUCCESS + STRONG_BR
			+ STRONG_4 + NUMBER_OF_TESTS + STRONG_BR_BR
			+ COMPLEXITY_MESSAGE
			+ UNIT_TEST_COVERAGE_FIRST 
			+ UNIT_TEST_SUCCESS_THIRD
			+ NUMBER_OF_TESTS_LAST ;	
	

	public static final String MESSAGE_SET58 = MENU_HEADER
			+ STRONG_1 + COMPLEXITY + STRONG_BR
			+ STRONG_2 + UNIT_TEST_COVERAGE + STRONG_BR
			+ STRONG_3 + NUMBER_OF_TESTS + STRONG_BR_BR
			+ COMPLEXITY_MESSAGE
			+ "In this case the level of unit test success is maximum.</br>"
			+ "Later you should improve coverage to finish covering all the code.</br>"
			+ NUMBER_OF_TESTS_LAST ;	
	
	
	
	public static final String MESSAGE_SET59 = MENU_HEADER
			+ STRONG_1 + COMPLEXITY + STRONG_BR
			+ STRONG_2 + UNIT_TEST_SUCCESS + STRONG_BR
			+ STRONG_3 + NUMBER_OF_TESTS + STRONG_BR_BR
			+ COMPLEXITY_MESSAGE
			+ UNIT_TEST_SUCCESS_SECOND
			+ NUMBER_OF_TESTS_LAST ;	
	

	public static final String MESSAGE_SET60 = MENU_HEADER
			+ STRONG_1 + COMPLEXITY + STRONG_BR
			+ STRONG_2 + NUMBER_OF_TESTS + STRONG_BR_BR
			+ COMPLEXITY_MESSAGE
			+ "In this case the level of coverage, success, mutations are maximum.</br>"
			+ "It needs to increase the number of test cases, so that there is balance with the size of the project.</br>";	
	
	public static final String MESSAGE_SET61 = MENU_HEADER
			+ STRONG_1 + COMPLEXITY + STRONG_BR
			+ STRONG_2 + UNIT_TEST_SUCCESS + STRONG_BR
			+ STRONG_3 + UNIT_TEST_COVERAGE + STRONG_BR_BR
			+ COMPLEXITY_MESSAGE
			+ UNIT_TEST_SUCCESS_SECOND
			+ UNIT_TEST_COVERAGE_LAST ;
	
	public static final String MESSAGE_SET62 = MENU_HEADER
			+ STRONG_1 + COMPLEXITY + STRONG_BR
			+ STRONG_2 + UNIT_TEST_COVERAGE + STRONG_BR
			+ STRONG_3 + UNIT_TEST_SUCCESS + STRONG_BR_BR
			+ COMPLEXITY_MESSAGE
			+ UNIT_TEST_COVERAGE_SECOND  
			+ "Lastly priority is to fix those tests that are failing to raise the percentage of test cases that pass.</br>";
	
	public static final String MESSAGE_SET63 = MENU_HEADER
			+ STRONG_1 + COMPLEXITY + STRONG_BR
			+ STRONG_2 + UNIT_TEST_COVERAGE + STRONG_BR_BR
			+ COMPLEXITY_MESSAGE
			+ "In this case the level of success and mutations are maximum.</br>"
			+ "It needs to increase coverage, will allow successful tests achieve greater amount of code.</br>";
	
	public static final String MESSAGE_SET64 =  MENU_HEADER
			+ STRONG_1 + COMPLEXITY + STRONG_BR
			+ STRONG_2 + UNIT_TEST_SUCCESS + STRONG_BR_BR
			+ COMPLEXITY_MESSAGE
			+ "In this case the level of coverage and mutations are maximum and complexity is in threshold allowed.</br>"
			+ "It needs to fix those tests that are failing to raise the percentage of test cases that pass.</br>";

	public static final String MESSAGE_SET65 =  MENU_HEADER
			+ STRONG_1 + COMPLEXITY + STRONG_BR
			+ STRONG_2 + UNIT_TEST_SUCCESS + STRONG_BR
			+ STRONG_3 + UNIT_TEST_COVERAGE + STRONG_BR
			+ STRONG_4 + NUMBER_OF_TESTS + STRONG_BR_BR
			+ UNIT_TEST_SUCCESS_SECOND
			+ "Thirdly increase is coverage, will allow successful tests achieve greater amount of code.</br>"
			+ NUMBER_OF_TESTS_LAST ;	

	private ActionsMessageConstants() {
		throw new AssertionError("Can not instantiate constants class");
	}
}
