package org.sonar.plugins.sedcat.xfuzzy;


import org.junit.Before;
import org.junit.Test;
import org.testng.Assert;

public class MembershipFunctionFamilyQualityTests {


	private MembershipFunctionFamilyQuality underTestQuality;
	
	@Before
	public void setUp() throws Exception {
		underTestQuality  = new MembershipFunctionFamilyQuality(2, 3, 4);
	}


	@Test
	public final void testIsEqual() {
		Assert.assertEquals(underTestQuality.isEqual(1, 2.0), 0.0);
	}

	@Test
	public final void testCenter() {
		Assert.assertEquals(underTestQuality.center(2), 0.0);
	}

	@Test
	public final void testBasis() {
		Assert.assertEquals(underTestQuality.basis(4), 0.0);
	}

	@Test
	public final void testParam() {
		Assert.assertEquals(underTestQuality.param(3), 0.0);
	}

	@Test
	public final void testIsSmallerOrEqual() {
		Assert.assertEquals(underTestQuality.isGreaterOrEqual(2, 3.00), 0.00);
	}

	@Test
	public final void testIsGreaterOrEqual() {
		Assert.assertEquals(underTestQuality.isGreaterOrEqual(2, 2.00), 0.0);
	}
}
