/**
 * 
 */
package es.unileon.sonarqube.sedcat.scanners;

import static org.junit.Assert.*;

import java.io.File;
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
	 * Test method for {@link es.unileon.sonarqube.sedcat.scanners.MutationsReportFinder#findReport(java.io.File)}.
	 */
	@Test
	public final void findReportNull() {
		
		MutationsReportFinder underTest = new MutationsReportFinder();
		Assert.assertNull(underTest.findReport(null));

	}
	/**
	 * Test method for {@link es.unileon.sonarqube.sedcat.scanners.MutationsReportFinder#findReport(java.io.File)}.
	 */
	@Test
	public final void findReportNotExists() {
		
		MutationsReportFinder underTest = new MutationsReportFinder();
		File fileMocked = mock(File.class);
		
		when(fileMocked.exists()).thenReturn(false);
		Assert.assertNull(underTest.findReport(fileMocked));
	}
	
	/**
	 * Test method for {@link es.unileon.sonarqube.sedcat.scanners.MutationsReportFinder#findReport(java.io.File)}.
	 */
	@Test
	public final void findReportNotDirectory() {
		
		MutationsReportFinder underTest = new MutationsReportFinder();
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
		
		MutationsReportFinder underTest = new MutationsReportFinder();
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
		
		MutationsReportFinder underTest = new MutationsReportFinder();
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
	public final void findReportDirectoryWithDirectories() throws Exception {
		
		//TODO

		
	}
	
	

}
