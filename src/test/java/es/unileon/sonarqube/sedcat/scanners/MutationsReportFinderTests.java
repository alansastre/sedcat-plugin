/**
 * 
 */
package es.unileon.sonarqube.sedcat.scanners;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FilenameFilter;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.powermock.api.mockito.PowerMockito;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

/**
 *	@author alan.sastre
 *	@version 1.0
 */
public class MutationsReportFinderTests {

	private MutationsReportFinder underTest;
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
		
		 underTest = new MutationsReportFinder();
		 
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link es.unileon.sonarqube.sedcat.scanners.MutationsReportFinder#findReport(java.io.File)}.
	 */
	@Test
	public final void findReportNull() {
		
		Assert.assertNull(underTest.findReport(null));

	}
	/**
	 * Test method for {@link es.unileon.sonarqube.sedcat.scanners.MutationsReportFinder#findReport(java.io.File)}.
	 */
	@Test
	public final void findReportDirectoryNotExists() {

		File fileMocked = mock(File.class);
		
		when(fileMocked.exists()).thenReturn(false);
		Assert.assertNull(underTest.findReport(fileMocked));
	}
	
	/**
	 * Test method for {@link es.unileon.sonarqube.sedcat.scanners.MutationsReportFinder#findReport(java.io.File)}.
	 */
	@Test
	public final void findReportNotDirectory() {
		
		File fileMocked = mock(File.class);
		
		when(fileMocked.exists()).thenReturn(true);
		when(fileMocked.isDirectory()).thenReturn(false);
		Assert.assertNull(underTest.findReport(fileMocked));
	}
	
	/**
	 * Test method for {@link es.unileon.sonarqube.sedcat.scanners.MutationsReportFinder#findReport(java.io.File)}.
	 * @throws Exception 
	 */
	@Test
	public final void findReportNullDirectory() throws Exception {
		
		File fileMocked = mock(File.class);
		
		when(fileMocked.exists()).thenReturn(true);
		when(fileMocked.isDirectory()).thenReturn(true);		
		when(fileMocked.list(new FilenameFilter() {
			  public boolean accept(File current, String name) {
				    return new File(current, name).isDirectory();
				  }
			}))
		.thenReturn(null);

		Assert.assertNull(underTest.findReport(fileMocked));

		
	}
	
	/**
	 * Test method for {@link es.unileon.sonarqube.sedcat.scanners.MutationsReportFinder#findReport(java.io.File)}.
	 * @throws Exception 
	 */
	@Test
	public final void findReportEmptyDirectory() throws Exception {
		
		File fileMocked = mock(File.class);
		String[] dataTest = new String[0];
		
		when(fileMocked.exists()).thenReturn(true);
		when(fileMocked.isDirectory()).thenReturn(true);		
		when(fileMocked.list(new FilenameFilter() {
			  public boolean accept(File current, String name) {
				    return new File(current, name).isDirectory();
				  }
			}))
		.thenReturn(dataTest);

		Assert.assertNull(underTest.findReport(fileMocked));

		
	}
	
	
	/**
	 * Test method for {@link es.unileon.sonarqube.sedcat.scanners.MutationsReportFinder#findReport(java.io.File)}.
	 * @throws Exception 
	 */
	@Test
	public final void findReportDirectoryWithDirectoriesAndReport() throws Exception {
		/*
		 * Test data manually created in /target/pit-reports
		 * Last report directory is 20160417536
		 */

		File reportDirectory  = new File("/root/workspace/sonar-sedcat-plugin/target/pit-reports");
		File indexReportSearched = underTest.findReport(reportDirectory);
		

		Assert.assertNotNull(indexReportSearched);
		Assert.assertEquals("/root/workspace/sonar-sedcat-plugin/target/pit-reports/20160417536/index.html", 
				indexReportSearched.getAbsolutePath());

	}
	
	/**
	 * Test method for {@link es.unileon.sonarqube.sedcat.scanners.MutationsReportFinder#findReport(java.io.File)}.
	 * @throws Exception 
	 */
	@Test
	public final void findReportDirectoryWithDirectoriesAndEmptyReport() throws Exception {
		/*
		 * Test data manually created in /target/pit-reports2
		 * Last report directory is 20160417536
		 * In this case index.html report is empty
		 */

		File reportDirectory  = new File("/root/workspace/sonar-sedcat-plugin/target/pit-reports2");
		File indexReportSearched = underTest.findReport(reportDirectory);
		
		BufferedReader br = new BufferedReader(new FileReader(indexReportSearched.getAbsolutePath()));  
		Assert.assertNull(br.readLine());
		br.close();
		
	}
	
	/**
	 * Test method for {@link es.unileon.sonarqube.sedcat.scanners.MutationsReportFinder#findReport(java.io.File)}.
	 * @throws Exception 
	 */
	@Test
	public final void findReportDirectoryWithDirectoriesAndNoReport() throws Exception {
		/*
		 * Test data manually created in /target/pit-reports3
		 * Last report directory is 20160417536
		 * In this case index.html report not exists
		 */

		File reportDirectory  = new File("/root/workspace/sonar-sedcat-plugin/target/pit-reports3");
		File indexReportSearched = underTest.findReport(reportDirectory);
		Assert.assertNull(indexReportSearched);
	}
	
	

}
