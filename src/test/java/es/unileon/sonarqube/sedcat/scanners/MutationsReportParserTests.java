package es.unileon.sonarqube.sedcat.scanners;

import java.io.File;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Unit testing for
 * es.unileon.sonarqube.sedcat.scanners.MutationsReportParser#parseReport
 * 
 * @author alan.sastre
 * @version 1.0
 */
public class MutationsReportParserTests {

	private MutationsReportParser underTest;

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

		underTest = new MutationsReportParser();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	@Test
	public final void testparseReportNull() {

		Assert.assertNull(underTest.parseReport(null));
	}

	@Test
	public final void testparseReportNotExists() {

		File report = new File(
				"/root/workspace/sonar-sedcat-plugin/src/main/resources/test-resources/reports3/201604111543/index.html");
		Assert.assertNull(underTest.parseReport(report));
	}

	@Test
	public final void testparseReportDirectory() {

		File report = new File(
				"/root/workspace/sonar-sedcat-plugin/src/main/resources/test-resources/reports3/201604111543");
		Assert.assertNull(underTest.parseReport(report));
	}

	@Test
	public final void testparseReportEmpty() {

		File report = new File(
				"/root/workspace/sonar-sedcat-plugin/src/main/resources/test-resources/reports2/201604111543/index.html");
		Assert.assertNull(underTest.parseReport(report));

	}

	@Test
	public final void testparseReportWithoutTdElements() {
		/*
		 * /root/workspace/sonar-sedcat-plugin/src/main/resources/test-resources
		 * /reports4/201604111543/index.html is a report with plain text without
		 * td elements
		 */

		File report = new File(
				"/root/workspace/sonar-sedcat-plugin/src/main/resources/test-resources/reports4/201604111543/index.html");
		Assert.assertNull(underTest.parseReport(report));

	}

	@Test
	public final void testparseReportWithNotEnoughTdElements() {
		/*
		 * /root/workspace/sonar-sedcat-plugin/src/main/resources/test-resources
		 * /reports4/201604111543/index2.html is a report with plain text
		 * without EXPECTED td elements
		 */

		File report = new File(
				"/root/workspace/sonar-sedcat-plugin/src/main/resources/test-resources/reports4/201604111543/index2.html");
		Assert.assertNull(underTest.parseReport(report));

	}

	@Test
	public final void testparseReportWithoutCoverageLedgend() {

		/*
		 * /root/workspace/sonar-sedcat-plugin/src/main/resources/test-resources
		 * /reports4/201604111543/index4.html is a report with plain text
		 * without coverage ledgend elements
		 */

		File report = new File(
				"/root/workspace/sonar-sedcat-plugin/src/main/resources/test-resources/reports4/201604111543/index4.html");
		Assert.assertNull(underTest.parseReport(report));

	}

	@Test
	public final void testparseReportWithCoverageLedgendButNotText() {

		/*
		 * /root/workspace/sonar-sedcat-plugin/src/main/resources/test-resources
		 * /reports4/201604111543/index3.html is a report with plain text
		 * without text in coverage ledgend elements
		 */

		File report = new File(
				"/root/workspace/sonar-sedcat-plugin/src/main/resources/test-resources/reports4/201604111543/index3.html");
		Assert.assertNull(underTest.parseReport(report));

	}

	@Test
	public final void testparseReportOk() {

		/*
		 * OPTIMAL CASE
		 * /root/workspace/sonar-sedcat-plugin/target/pit-reports/20160417536/
		 * index.html is a real report
		 */

		File report = new File(
				"/root/workspace/sonar-sedcat-plugin/src/main/resources/test-resources/reports1/201604111543/index.html");
		double[] result = underTest.parseReport(report);

		Assert.assertNotNull(result);
		Assert.assertEquals(134.0, result[0], 0.0);
		Assert.assertEquals(469.0, result[1], 0.0);

	}

}
