package es.unileon.sonarqube.sedcat.scanners;

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
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * @author alan.sastre
 * @version 1.0
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
	 * Test method for
	 * {@link es.unileon.sonarqube.sedcat.scanners.MutationsReportFinder#findReport(java.io.File)}
	 * .
	 */
	@Test
	public final void testfindReportNull() {

		Assert.assertNull(underTest.findReport(null));

	}

	/**
	 * Test method for
	 * {@link es.unileon.sonarqube.sedcat.scanners.MutationsReportFinder#findReport(java.io.File)}
	 * .
	 */
	@Test
	public final void testfindReportDirectoryNotExists() {

		File fileMocked = mock(File.class);

		when(fileMocked.exists()).thenReturn(false);
		Assert.assertNull(underTest.findReport(fileMocked));
	}

	/**
	 * Test method for
	 * {@link es.unileon.sonarqube.sedcat.scanners.MutationsReportFinder#findReport(java.io.File)}
	 * .
	 */
	@Test
	public final void testfindReportNotDirectory() {

		File fileMocked = mock(File.class);

		when(fileMocked.exists()).thenReturn(true);
		when(fileMocked.isDirectory()).thenReturn(false);
		Assert.assertNull(underTest.findReport(fileMocked));
	}

	/**
	 * Test method for
	 * {@link es.unileon.sonarqube.sedcat.scanners.MutationsReportFinder#findReport(java.io.File)}
	 * .
	 * 
	 * @throws Exception
	 */
	@Test
	public final void testfindReportNullDirectory() throws Exception {

		File fileMocked = mock(File.class);

		when(fileMocked.exists()).thenReturn(true);
		when(fileMocked.isDirectory()).thenReturn(true);
		when(fileMocked.list(new FilenameFilter() {
			public boolean accept(File current, String name) {
				return new File(current, name).isDirectory();
			}
		})).thenReturn(null);

		Assert.assertNull(underTest.findReport(fileMocked));

	}

	/**
	 * Test method for
	 * {@link es.unileon.sonarqube.sedcat.scanners.MutationsReportFinder#findReport(java.io.File)}
	 * .
	 * 
	 * @throws Exception
	 */
	@Test
	public final void testfindReportEmptyDirectory() throws Exception {

		File fileMocked = mock(File.class);
		String[] dataTest = new String[0];

		when(fileMocked.exists()).thenReturn(true);
		when(fileMocked.isDirectory()).thenReturn(true);
		when(fileMocked.list(new FilenameFilter() {
			public boolean accept(File current, String name) {
				return new File(current, name).isDirectory();
			}
		})).thenReturn(dataTest);

		Assert.assertNull(underTest.findReport(fileMocked));

	}

	/**
	 * Test method for
	 * {@link es.unileon.sonarqube.sedcat.scanners.MutationsReportFinder#findReport(java.io.File)}
	 * .
	 * 
	 * @throws Exception
	 */
	@Test
	public final void testfindReportDirectoryWithDirectoriesAndReport() throws Exception {
		/*
		 * Test data manually created in
		 * /root/workspace/sonar-sedcat-plugin/src/main/resources/test-resources
		 * /reports1 Last report directory is 201604111543
		 */

		File reportDirectory = new File(
				"/root/workspace/sonar-sedcat-plugin/src/main/resources/test-resources/reports1");
		File indexReportSearched = underTest.findReport(reportDirectory);

		Assert.assertNotNull(indexReportSearched);
		Assert.assertEquals(
				"/root/workspace/sonar-sedcat-plugin/src/main/resources/test-resources/reports1/201604111543/index.html",
				indexReportSearched.getAbsolutePath());

	}

	/**
	 * Test method for
	 * {@link es.unileon.sonarqube.sedcat.scanners.MutationsReportFinder#findReport(java.io.File)}
	 * .
	 * 
	 * @throws Exception
	 */
	@Test
	public final void testfindReportDirectoryWithDirectoriesAndEmptyReport() throws Exception {
		/*
		 * Test data manually created in
		 * /root/workspace/sonar-sedcat-plugin/src/main/resources/test-resources
		 * /reports2 Last report directory is 201604111543 In this case
		 * index.html report is empty
		 */

		File reportDirectory = new File(
				"/root/workspace/sonar-sedcat-plugin/src/main/resources/test-resources/reports2");
		File indexReportSearched = underTest.findReport(reportDirectory);

		BufferedReader br = new BufferedReader(new FileReader(indexReportSearched.getAbsolutePath()));
		Assert.assertNull(br.readLine());
		br.close();

	}

	/**
	 * Test method for
	 * {@link es.unileon.sonarqube.sedcat.scanners.MutationsReportFinder#findReport(java.io.File)}
	 * .
	 * 
	 * @throws Exception
	 */
	@Test
	public final void testfindReportDirectoryWithDirectoriesAndNoReport() throws Exception {
		/*
		 * Test data manually created in
		 * /root/workspace/sonar-sedcat-plugin/src/main/resources/test-resources
		 * /reports2 Last report directory is 201604111543 In this case
		 * index.html report not exists
		 */

		File reportDirectory = new File(
				"/root/workspace/sonar-sedcat-plugin/src/main/resources/test-resources/reports3");
		File indexReportSearched = underTest.findReport(reportDirectory);
		Assert.assertNull(indexReportSearched);
	}

}
