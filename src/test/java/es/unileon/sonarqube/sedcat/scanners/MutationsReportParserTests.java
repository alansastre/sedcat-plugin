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

/**
 *  Unit testing for es.unileon.sonarqube.sedcat.scanners.MutationsReportParser#parseReport
 *	@author alan.sastre
 *	@version 1.0
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
	public final void parseReportNull() {
		
		Assert.assertNull(underTest.parseReport(null));
	}
	

	@Test
	public final void parseReportNotExists() {

		File report = new File("/root/workspace/sonar-sedcat-plugin/target/pit-reports3/20160417536/index.html");
		Assert.assertNull(underTest.parseReport(report));
	}

	@Test
	public final void parseReportDirectory() {

		File report = new File("/root/workspace/sonar-sedcat-plugin/target/pit-reports3/20160417536");
		Assert.assertNull(underTest.parseReport(report));
	}
	

	@Test
	public final void parseReportEmpty() {

		
		File report = new File("/root/workspace/sonar-sedcat-plugin/target/pit-reports2/20160417536/index.html");
		Assert.assertNull(underTest.parseReport(report));

	}

	@Test
	public final void parseReportWithoutTdElements() {
	/*
	 * /root/workspace/sonar-sedcat-plugin/target/pit-reports4/index.html
	 * is a report with plain text without expected td elements 
	 */
		
		File report = new File("/root/workspace/sonar-sedcat-plugin/target/pit-reports4/index.html");
		Assert.assertNull(underTest.parseReport(report));

	}
	

	@Test
	public final void parseReportWithNotEnoughTdElements() {

		
		File report = new File("/root/workspace/sonar-sedcat-plugin/target/pit-reports4/index2.html");
		Assert.assertNull(underTest.parseReport(report));

	}

	
	@Test
	public final void parseReportWithoutCoverageLedgend() {

		/*
		 * /root/workspace/sonar-sedcat-plugin/target/pit-reports4/index3.html
		 * is a real report but not has the wanted values
		 */
		
		File report = new File("/root/workspace/sonar-sedcat-plugin/target/pit-reports4/index3.html");
		Assert.assertNull(underTest.parseReport(report));

	}
	
	@Test
	public final void parseReportWithCoverageLedgendButNotText() {

		/*
		 * /root/workspace/sonar-sedcat-plugin/target/pit-reports4/index3.html
		 * is a real report but not has the wanted values
		 */
		
		File report = new File("/root/workspace/sonar-sedcat-plugin/target/pit-reports4/index4.html");
		Assert.assertNull(underTest.parseReport(report));

	}
	
	
	
	@Test
	public final void parseReportOk() {

		/* OPTIMAL CASE
		 * /root/workspace/sonar-sedcat-plugin/target/pit-reports/20160417536/index.html
		 * is a real report
		 */
		
		File report = new File("/root/workspace/sonar-sedcat-plugin/target/pit-reports/20160417536/index.html");
		double[] result = underTest.parseReport(report);
		
		Assert.assertNotNull(result);
		Assert.assertEquals(result[0], 134.0, 0.0);
		Assert.assertEquals(result[1], 469.0, 0.0);

	}
	

}
