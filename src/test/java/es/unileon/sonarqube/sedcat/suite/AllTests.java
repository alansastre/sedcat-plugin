package es.unileon.sonarqube.sedcat.suite;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import es.unileon.sonarqube.sedcat.scanners.ComplexityComputerTests;
import es.unileon.sonarqube.sedcat.scanners.ComplexityThresholdSensorTests;
import es.unileon.sonarqube.sedcat.scanners.MutationsCoverageSensorTests;
import es.unileon.sonarqube.sedcat.scanners.MutationsReportFinderTests;
import es.unileon.sonarqube.sedcat.scanners.MutationsReportParserTests;
import es.unileon.sonarqube.sedcat.start.GeneralComputerTests;
import es.unileon.sonarqube.sedcat.start.SedcatMetricsTests;
import es.unileon.sonarqube.sedcat.start.SedcatPluginTests;
import es.unileon.sonarqube.sedcat.storage.ActionsMeasureStoreTests;
import es.unileon.sonarqube.sedcat.storage.QualityMeasureStoreTests;
import es.unileon.sonarqube.sedcat.strategies.ExpertSystemActionsTests;
import es.unileon.sonarqube.sedcat.strategies.ExpertSystemQualityTests;
import es.unileon.sonarqube.sedcat.xfuzzy.ActionsTests;
import es.unileon.sonarqube.sedcat.xfuzzy.QualityTests;

@RunWith(Suite.class)

@SuiteClasses({
	
	//start 
	GeneralComputerTests.class,
	SedcatPluginTests.class,
	SedcatMetricsTests.class,
	
	//scanners
	MutationsCoverageSensorTests.class,
	MutationsReportFinderTests.class,
	MutationsReportParserTests.class,
	ComplexityComputerTests.class,
	ComplexityThresholdSensorTests.class,
	
	//storage
	ActionsMeasureStoreTests.class,
	QualityMeasureStoreTests.class,
	
	//strategies
	ExpertSystemQualityTests.class,
	ExpertSystemActionsTests.class,
	
	//xfuzzy
	QualityTests.class,
	ActionsTests.class
	
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
