package org.sonar.plugins.sedcat.suite;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import org.sonar.plugins.sedcat.scanners.ComplexityComputerTests;
import org.sonar.plugins.sedcat.scanners.MutationsCoverageSensorTests;
import org.sonar.plugins.sedcat.scanners.MutationsReportFinderTests;
import org.sonar.plugins.sedcat.scanners.MutationsReportParserTests;
import org.sonar.plugins.sedcat.start.GeneralComputerTests;
import org.sonar.plugins.sedcat.start.SedcatMetricsTests;
import org.sonar.plugins.sedcat.start.SedcatPluginTests;
import org.sonar.plugins.sedcat.storage.ActionsMeasureStoreTests;
import org.sonar.plugins.sedcat.storage.QualityMeasureStoreTests;
import org.sonar.plugins.sedcat.strategies.ExpertSystemActionsTests;
import org.sonar.plugins.sedcat.strategies.ExpertSystemQualityTests;
import org.sonar.plugins.sedcat.xfuzzy.ActionsTests;
import org.sonar.plugins.sedcat.xfuzzy.QualityTests;

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
