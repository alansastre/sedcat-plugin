package org.sonar.plugins.sedcat.start;

import org.junit.Before;
import org.junit.Test;
import org.testng.Assert;


public class SedcatDashboardWidgetTests {

	private SedcatDashboardWidget underTest;
	
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {

		underTest = new SedcatDashboardWidget();
		

	}
	@Test
	public final void testGetters() {
		Assert.assertNotNull(underTest.getId());
		Assert.assertNotNull(underTest.getTemplate());
		Assert.assertNotNull(underTest.getTitle());
		Assert.assertNotNull(underTest.getTemplatePath());
	}

}
