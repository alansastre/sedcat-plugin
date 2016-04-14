/**
 * 
 */
package es.unileon.sonarqube.sedcat.scanners;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.sonar.api.batch.fs.FileSystem;
import org.sonar.api.batch.fs.internal.DefaultFileSystem;
import org.sonar.api.batch.fs.internal.DefaultInputFile;
//import org.sonar.api.batch.sensor.SensorContext;
import org.sonar.api.batch.SensorContext;
import org.sonar.api.batch.sensor.internal.SensorContextTester;
import org.sonar.api.ce.measure.MeasureComputer.MeasureComputerDefinition;
import org.sonar.api.ce.measure.test.TestComponent;
import org.sonar.api.ce.measure.test.TestMeasureComputerContext;
import org.sonar.api.ce.measure.test.TestMeasureComputerDefinitionContext;
import org.sonar.api.ce.measure.test.TestSettings;
import org.sonar.api.config.PropertyDefinitions;
import org.sonar.api.config.Settings;
import org.sonar.api.resources.Project;

import es.unileon.sonarqube.sedcat.start.GeneralComputer;
import es.unileon.sonarqube.sedcat.start.SedcatConstants;
import es.unileon.sonarqube.sedcat.start.SedcatMetrics;
import es.unileon.sonarqube.sedcat.start.SedcatMetricsKeys;
import es.unileon.sonarqube.sedcat.start.SedcatPlugin;
import es.unileon.sonarqube.sedcat.strategies.ExpertSystemActions;
import es.unileon.sonarqube.sedcat.strategies.ExpertSystemQuality;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

/**
 *	@author alan.sastre
 *	@version 1.0
 */
public class MutationsCoverageSensorTests {

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link es.unileon.sonarqube.sedcat.scanners.MutationsCoverageSensor#MutationsCoverageSensor(org.sonar.api.batch.fs.FileSystem, org.sonar.api.config.Settings, es.unileon.sonarqube.sedcat.scanners.MutationsReportFinder, es.unileon.sonarqube.sedcat.scanners.MutationsReportParser)}.
	 */
	@Test
	public final void testMutationsCoverageSensor() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link es.unileon.sonarqube.sedcat.scanners.MutationsCoverageSensor#shouldExecuteOnProject(org.sonar.api.resources.Project)}.
	 */
	@Test
	public final void testShouldExecuteOnProject() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link es.unileon.sonarqube.sedcat.scanners.MutationsCoverageSensor#analyse(org.sonar.api.resources.Project, org.sonar.api.batch.SensorContext)}.
	 * @throws Exception 
	 */
	@Test
	public final void testAnalyse() throws Exception {

		//setup
		Settings settings = new Settings(new PropertyDefinitions(SedcatPlugin.class));
		File baseDirectoryPath = new File("/root/workspace/sonar-sedcat-plugin");
		File reportDirectoryPath = new File("/root/workspace/sonar-sedcat-plugin/target/pit-reports");
		File ficticiousReportPath = new File("/root/workspace/sonar-sedcat-plugin/target/pit-reports/index.html");
		DefaultFileSystem fileSystem = new DefaultFileSystem(baseDirectoryPath);

		//create mocks
	    MutationsReportFinder mutationsFinderMocked = mock(MutationsReportFinder.class);
	    MutationsReportParser mutationsParserMocked = mock(MutationsReportParser.class);
	    Project mockedProject = mock(Project.class);
	    

	    //setup mocks

	    when(mutationsFinderMocked.findReport(reportDirectoryPath)).thenReturn(ficticiousReportPath);
	    when(mutationsParserMocked.parseReport(ficticiousReportPath)).thenReturn(new double[]{100.0, 100.0});
	    //execute
	    MutationsCoverageSensor mutsensor = new MutationsCoverageSensor(fileSystem, settings, mutationsFinderMocked, mutationsParserMocked);
		SensorContext mockedcontext = mock(SensorContext.class);
		mutsensor.analyse(mockedProject, mockedcontext);

	    //verify

		PowerMockito.verifyNew(File.class, times(1)).withArguments(baseDirectoryPath);
		PowerMockito.verifyNew(File.class, times(1)).withArguments(reportDirectoryPath);
		PowerMockito.verifyNew(File.class, times(1)).withArguments(ficticiousReportPath);
		
	
		Mockito.verify(mutationsFinderMocked, times(1)).findReport(reportDirectoryPath);
		Mockito.verify(mutationsParserMocked, times(1)).parseReport(ficticiousReportPath);

		//verificar valor de medida: mediante un computer 
		GeneralComputer underTest = new GeneralComputer();
		TestMeasureComputerDefinitionContext defContext = new TestMeasureComputerDefinitionContext();
		MeasureComputerDefinition def = underTest.define(defContext);
	    TestComponent mockedComponent = mock(TestComponent.class);
	    TestSettings settingsComputer = new TestSettings();
	    TestMeasureComputerContext contextComputer = new TestMeasureComputerContext(mockedComponent, settingsComputer, def);
	    
	    
		Assert.assertEquals(contextComputer.getMeasure(SedcatMetricsKeys.MUTANTS_KEY).getDoubleValue(),100.0,0.0);
		
		Assert.assertEquals(SedcatConstants.mutationsDetected, 100.0, 0.0);
		Assert.assertEquals(SedcatConstants.mutationsTotal, 100.0, 0.0);
	}

}
