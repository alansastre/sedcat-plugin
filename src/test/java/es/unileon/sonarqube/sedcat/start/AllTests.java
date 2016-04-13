package es.unileon.sonarqube.sedcat.start;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)

@SuiteClasses({
	
	GeneralComputerTests.class,
	SedcatPluginTests.class,
	CoverageUnitTestsComputerTests.class
	
})

public class AllTests {
	 // the class remains empty,
	  // used only as a holder for the above annotations
}
