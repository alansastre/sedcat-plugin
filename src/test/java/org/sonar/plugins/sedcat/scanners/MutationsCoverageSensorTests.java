/**
 * 
 */
package org.sonar.plugins.sedcat.scanners;

import java.io.File;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;
import org.sonar.api.batch.fs.internal.DefaultFileSystem;
import org.sonar.api.batch.SensorContext;
import org.sonar.api.config.PropertyDefinitions;
import org.sonar.api.config.Settings;
import org.sonar.api.resources.Project;
import org.sonar.plugins.sedcat.scanners.MutationsCoverageSensor;
import org.sonar.plugins.sedcat.scanners.MutationsReportFinder;
import org.sonar.plugins.sedcat.scanners.MutationsReportParser;
import org.sonar.plugins.sedcat.start.SedcatConstants;
import org.sonar.plugins.sedcat.start.SedcatPlugin;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

/**
 *	@author alan.sastre
 *	@version 1.0
 */
public class MutationsCoverageSensorTests {

	private Settings settings;
	private File baseDirectoryPath;
	private File reportDirectoryPath;
	private File ficticiousReportPath;
	private DefaultFileSystem fileSystem;
	
	//mocks
	private MutationsReportFinder mutationsFinderMocked;
	private MutationsReportParser mutationsParserMocked;
	private Project mockedProject;
	private MutationsCoverageSensor underTest;
	private SensorContext mockedcontext;
	
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
		
		//setup
		settings = new Settings(new PropertyDefinitions(SedcatPlugin.class));
		baseDirectoryPath = new File("/root/workspace/sonar-sedcat-plugin");
		reportDirectoryPath = new File("/root/workspace/sonar-sedcat-plugin/target/pit-reports");
		ficticiousReportPath = new File("/root/workspace/sonar-sedcat-plugin/target/pit-reports/index.html");
		fileSystem = new DefaultFileSystem(baseDirectoryPath);
		
		//create mocks
		mutationsFinderMocked = mock(MutationsReportFinder.class);
		mutationsParserMocked = mock(MutationsReportParser.class);
		mockedProject = mock(Project.class);
		mockedcontext = mock(SensorContext.class);
		
		underTest = null;
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}


	/**
	 * Test method for {@link org.sonar.plugins.sedcat.scanners.MutationsCoverageSensor#analyse(org.sonar.api.resources.Project, org.sonar.api.batch.SensorContext)}.
	 * @throws Exception 
	 */
	@Test
	public final void testAnalyseWithReport() throws Exception {

		//Reset sedcat Constants
		SedcatConstants.mutationsDetected = 0;
		SedcatConstants.mutationsTotal = 0;

	    //setup mocks
	    when(mutationsFinderMocked.findReport(reportDirectoryPath)).thenReturn(ficticiousReportPath);
	    when(mutationsParserMocked.parseReport(ficticiousReportPath)).thenReturn(new double[]{100.0, 100.0});
	    
	    //execute
	    underTest = new MutationsCoverageSensor(fileSystem, settings, mutationsFinderMocked, mutationsParserMocked);
		underTest.analyse(mockedProject, mockedcontext);

	    //verify
		Mockito.verify(mutationsFinderMocked, times(1)).findReport(reportDirectoryPath);
		Mockito.verify(mutationsParserMocked, times(1)).parseReport(ficticiousReportPath);

		//verificar valor de medida:
		Assert.assertNotNull(underTest);
		Assert.assertEquals(100.0, SedcatConstants.mutationsDetected, 0.0);
		Assert.assertEquals(100.0, SedcatConstants.mutationsTotal, 0.0);
		Assert.assertEquals(100.0, underTest.getMutationsValueFound(), 0.0);
	}
	
	/**
	 * Test method for {@link org.sonar.plugins.sedcat.scanners.MutationsCoverageSensor#analyse(org.sonar.api.resources.Project, org.sonar.api.batch.SensorContext)}.
	 * 
	 * This case is only in multi-module projects
	 * @throws Exception 
	 */
	@Test
	public final void testAnalyseWithMultipleReport() throws Exception {

		//Reset sedcat Constants
		SedcatConstants.mutationsDetected = 0;
		SedcatConstants.mutationsTotal = 0;

	    //setup mocks
	    when(mutationsFinderMocked.findReport(reportDirectoryPath)).thenReturn(ficticiousReportPath);
	    when(mutationsParserMocked.parseReport(ficticiousReportPath)).thenReturn(new double[]{100.0, 100.0});
	    
	    //Cada iteración equivale a la ejecución del sensor sobre un módulo diferente
	    for (int i = 0; i < 100; i++) {

		    //execute
		    underTest = new MutationsCoverageSensor(fileSystem, settings, mutationsFinderMocked, mutationsParserMocked);
			underTest.analyse(mockedProject, mockedcontext);

		}
	    
	    //verify
		Mockito.verify(mutationsFinderMocked, times(100)).findReport(reportDirectoryPath);
		Mockito.verify(mutationsParserMocked, times(100)).parseReport(ficticiousReportPath);
	    
		Assert.assertNotNull(underTest);
		Assert.assertEquals(10000.0, SedcatConstants.mutationsDetected, 0.0);
		Assert.assertEquals(10000.0, SedcatConstants.mutationsTotal, 0.0);
		Assert.assertEquals(100.0, underTest.getMutationsValueFound(), 0.0);

	}
	
	/**
	 * Test method for {@link org.sonar.plugins.sedcat.scanners.MutationsCoverageSensor#analyse(org.sonar.api.resources.Project, org.sonar.api.batch.SensorContext)}.
	 * @throws Exception 
	 */
	@Test
	public final void testAnalyseWithReportParserNull() throws Exception {

	    //setup mocks
	    when(mutationsFinderMocked.findReport(reportDirectoryPath)).thenReturn(ficticiousReportPath);
	    when(mutationsParserMocked.parseReport(ficticiousReportPath)).thenReturn(null);
	    
	    //execute
	    underTest = new MutationsCoverageSensor(fileSystem, settings, mutationsFinderMocked, mutationsParserMocked);
		underTest.analyse(mockedProject, mockedcontext);

	    //verify
		Mockito.verify(mutationsFinderMocked, times(1)).findReport(reportDirectoryPath);
		Mockito.verify(mutationsParserMocked, times(1)).parseReport(ficticiousReportPath);

		//verificar valor de medida:
		Assert.assertNotNull(underTest);
		Assert.assertEquals(0, SedcatConstants.mutationsDetected, 0.0);
		Assert.assertEquals(0, SedcatConstants.mutationsTotal, 0.0);
		Assert.assertEquals(0.0, underTest.getMutationsValueFound(), 0.0);
	}
	
	
	/**
	 * Test method for {@link org.sonar.plugins.sedcat.scanners.MutationsCoverageSensor#analyse(org.sonar.api.resources.Project, org.sonar.api.batch.SensorContext)}.
	 * @throws Exception 
	 */
	@Test
	public final void testAnalyseWithNullReport() throws Exception {
	    
	    //setup mocks
	    when(mutationsFinderMocked.findReport(reportDirectoryPath)).thenReturn(null);
	    
	    //execute
	    underTest = new MutationsCoverageSensor(fileSystem, settings, mutationsFinderMocked, mutationsParserMocked);
		underTest.analyse(mockedProject, mockedcontext);

	    //verify
		Mockito.verify(mutationsFinderMocked, times(1)).findReport(reportDirectoryPath);
		
		//verificar valor de medida:
		Assert.assertNotNull(underTest);
		Assert.assertEquals(0, SedcatConstants.mutationsDetected, 0.0);
		Assert.assertEquals(0, SedcatConstants.mutationsTotal, 0.0);
		Assert.assertEquals(0.0, underTest.getMutationsValueFound(), 0.0);

		
	}
	

}
