package es.unileon.sonarqube.sedcat.suite;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import es.unileon.sonarqube.sedcat.scanners.CoverageUnitTestsComputerTests;
import es.unileon.sonarqube.sedcat.scanners.MutationsCoverageSensorTests;
import es.unileon.sonarqube.sedcat.scanners.MutationsReportFinderTests;
import es.unileon.sonarqube.sedcat.scanners.MutationsReportParserTests;
import es.unileon.sonarqube.sedcat.scanners.NumberCodeLinesComputerTests;
import es.unileon.sonarqube.sedcat.scanners.NumberTestsComputerTests;
import es.unileon.sonarqube.sedcat.scanners.SuccessUnitTestsComputerTests;
import es.unileon.sonarqube.sedcat.start.GeneralComputerTests;
import es.unileon.sonarqube.sedcat.start.SedcatPluginTests;

@RunWith(Suite.class)

@SuiteClasses({
	
	//start 
	GeneralComputerTests.class,
	SedcatPluginTests.class,
	
	//scanners
	CoverageUnitTestsComputerTests.class,
	SuccessUnitTestsComputerTests.class,
	NumberCodeLinesComputerTests.class,
	NumberTestsComputerTests.class,
	MutationsCoverageSensorTests.class,
	MutationsReportFinderTests.class,
	MutationsReportParserTests.class
	
})

public class AllTests {
	 // the class remains empty,
	  // used only as a holder for the above annotations
}
