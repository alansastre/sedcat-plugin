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
import es.unileon.sonarqube.sedcat.start.SedcatMetricsTests;
import es.unileon.sonarqube.sedcat.start.SedcatPluginTests;
import es.unileon.sonarqube.sedcat.storage.ActionsMeasureStoreTests;

@RunWith(Suite.class)

@SuiteClasses({
	
	//start 
	GeneralComputerTests.class,
	SedcatPluginTests.class,
	SedcatMetricsTests.class,
	
	//scanners
	CoverageUnitTestsComputerTests.class,
	SuccessUnitTestsComputerTests.class,
	NumberCodeLinesComputerTests.class,
	NumberTestsComputerTests.class,
	MutationsCoverageSensorTests.class,
	MutationsReportFinderTests.class,
	MutationsReportParserTests.class,
	
	//storage
	ActionsMeasureStoreTests.class
	
})
/**
 * Unit Test are FIRST:
 * 
 * - Fast
 * - Independent or Isolated
 * - Repeteable
 * - Self-Validating
 * - Timely
 * 
 *	@author alan.sastre
 *	@version 1.0
 */
public class AllTests {
	 // the class remains empty,
	  // used only as a holder for the above annotations
}
