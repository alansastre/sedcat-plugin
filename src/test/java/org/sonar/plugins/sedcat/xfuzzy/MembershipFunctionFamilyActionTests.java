package org.sonar.plugins.sedcat.xfuzzy;


import org.junit.Before;
import org.junit.Test;
import org.testng.Assert;

public class MembershipFunctionFamilyActionTests {

	private MembershipFunctionFamilyAction underTestActions;
	
	@Before
	public void setUp() throws Exception {
		underTestActions  = new MembershipFunctionFamilyAction(2, 3, 4);
	}


	@Test
	public final void testIsEqual() {
		Assert.assertEquals(underTestActions.isEqual(1, 2.0), 0.0);
	}

	@Test
	public final void testCenter() {
		Assert.assertEquals(underTestActions.center(2), 0.0);
	}

	@Test
	public final void testBasis() {
		Assert.assertEquals(underTestActions.basis(4), 0.0);
	}

	@Test
	public final void testParam() {
		Assert.assertEquals(underTestActions.param(3), 0.0);
	}

	@Test
	public final void testIsSmallerOrEqual() {
		Assert.assertEquals(underTestActions.isGreaterOrEqual(2, 3.00), 0.00);
	}

	@Test
	public final void testIsGreaterOrEqual() {
		Assert.assertEquals(underTestActions.isGreaterOrEqual(2, 2.00), 0.0);
	}

}
