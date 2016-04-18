/**
 * 
 */
package es.unileon.sonarqube.sedcat.scanners;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *	@author alan.sastre
 *	@version 1.0
 */
public class MutationsReportParserTests {

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
	 * Test method for {@link es.unileon.sonarqube.sedcat.scanners.MutationsReportParser#parseReport(java.io.File)}.
	 */
	@Test
	public final void parseReportNull() {

		MutationsReportParser underTest = new MutationsReportParser();
		Assert.assertNull(underTest.parseReport(null));
	}
	
	/**
	 * Test method for {@link es.unileon.sonarqube.sedcat.scanners.MutationsReportParser#parseReport(java.io.File)}.
	 */
	@Test
	public final void parseReportNotExists() {

		MutationsReportParser underTest = new MutationsReportParser();
		File report = new File("/root/workspace/sonar-sedcat-plugin/target/pit-reports3/20160417536/index.html");
		Assert.assertNull(underTest.parseReport(report));
	}
	
	/**
	 * Test method for {@link es.unileon.sonarqube.sedcat.scanners.MutationsReportParser#parseReport(java.io.File)}.
	 */
	@Test
	public final void parseReportDirectory() {

		MutationsReportParser underTest = new MutationsReportParser();
		File report = new File("/root/workspace/sonar-sedcat-plugin/target/pit-reports3/20160417536");
		Assert.assertNull(underTest.parseReport(report));
	}
	
	
	
	/**
	 * Test method for {@link es.unileon.sonarqube.sedcat.scanners.MutationsReportParser#parseReport(java.io.File)}.
	 */
	@Test
	public final void parseReportEmpty() {

		
		MutationsReportParser underTest = new MutationsReportParser();
		File report = new File("/root/workspace/sonar-sedcat-plugin/target/pit-reports2/20160417536/index.html");
		Assert.assertNull(underTest.parseReport(report));

	}
	
	
	/**
	 * Test method for {@link es.unileon.sonarqube.sedcat.scanners.MutationsReportParser#parseReport(java.io.File)}.
	 */
	@Test
	public final void parseReportWithoutTdElements() {
	/*
	 * /root/workspace/sonar-sedcat-plugin/target/pit-reports4/index.html
	 * is a report with plain text without expected td elements 
	 */
		
		MutationsReportParser underTest = new MutationsReportParser();
		File report = new File("/root/workspace/sonar-sedcat-plugin/target/pit-reports4/index.html");
		Assert.assertNull(underTest.parseReport(report));

	}
	
	
	/**
	 * Test method for {@link es.unileon.sonarqube.sedcat.scanners.MutationsReportParser#parseReport(java.io.File)}.
	 */
	@Test
	public final void parseReportWithNotEnoughTdElements() {

		
		MutationsReportParser underTest = new MutationsReportParser();
		File report = new File("/root/workspace/sonar-sedcat-plugin/target/pit-reports4/index2.html");
		Assert.assertNull(underTest.parseReport(report));

	}
	
	/*
	 * TODO more tests
	 */
	

}
