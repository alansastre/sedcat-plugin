package org.sonar.plugins.sedcat.strategies;

import static org.mockito.Mockito.mock;

import org.junit.Before;
import org.junit.Test;
import org.sonar.plugins.sedcat.xfuzzy.actions.FamiliarMembershipFunction;
import org.sonar.plugins.sedcat.xfuzzy.actions.FuzzySingleton;
import org.sonar.plugins.sedcat.xfuzzy.actions.MembershipFunctionFamily;
import org.sonar.plugins.sedcat.xfuzzy.actions.OP_Actions__default_;
import org.testng.Assert;

public class FamiliarMembershipFunctionActionTests {

	private FamiliarMembershipFunction underTestActions;
	private MembershipFunctionFamily membershipFamily;
	private FuzzySingleton membershipFuntion;
	private OP_Actions__default_ operatorSet;
	
	@Before
	public void setUp() throws Exception {
		membershipFamily = mock(MembershipFunctionFamily.class);
		membershipFuntion = new FuzzySingleton(2);
		operatorSet = new OP_Actions__default_();

		underTestActions = new FamiliarMembershipFunction(membershipFamily, 2);

	}

	@Test
	public final void testCenter() {
		Assert.assertEquals(underTestActions.center(), 0.0);
	}

	@Test
	public final void testBasis() {
		Assert.assertEquals(underTestActions.basis(), 0.0);
	}

	@Test
	public final void testParam() {
		Assert.assertEquals(underTestActions.param(2), 0.0);
	}

	@Test
	public final void testIsEqualDouble() {
		Assert.assertEquals(underTestActions.isEqual(2.00), 0.0);
	}

	@Test
	public final void testIsSmallerOrEqualDouble() {
		Assert.assertEquals(underTestActions.isSmallerOrEqual(2.00), 0.0);
	}

	@Test
	public final void testIsGreaterOrEqualDouble() {
		Assert.assertEquals(underTestActions.isGreaterOrEqual(2.00), 0.0);
	}

	@Test
	public final void testFamiliarMembershipFunction() {

		FamiliarMembershipFunction underTest1 = new FamiliarMembershipFunction(membershipFamily, 2);
		underTest1.getClass().equals(FamiliarMembershipFunction.class);
		Assert.assertNotNull(underTest1);

	}

	@Test
	public final void testIsEqualMembershipFunction() {
		Assert.assertEquals(underTestActions.isEqual(membershipFuntion), 0.0);
	}

	@Test
	public final void testIsGreaterOrEqualMembershipFunction() {
		Assert.assertEquals(underTestActions.isGreaterOrEqual(membershipFuntion), 0.0);
	}

	@Test
	public final void testIsSmallerOrEqualMembershipFunction() {
		Assert.assertEquals(underTestActions.isSmallerOrEqual(membershipFuntion), 0.0);
	}

	@Test
	public final void testIsGreater() {
		Assert.assertEquals(underTestActions.isGreater(membershipFuntion, operatorSet), 1.0);
	}

	@Test
	public final void testIsSmaller() {
		Assert.assertEquals(underTestActions.isSmaller(membershipFuntion, operatorSet), 1.0);
	}

	@Test
	public final void testIsNotEqual() {
		Assert.assertEquals(underTestActions.isNotEqual(membershipFuntion, operatorSet), 1.0);
	}

	@Test
	public final void testIsApproxEqual() {
		Assert.assertEquals(underTestActions.isApproxEqual(membershipFuntion, operatorSet), 0.0);
	}

	@Test
	public final void testIsVeryEqual() {
		Assert.assertEquals(underTestActions.isVeryEqual(membershipFuntion, operatorSet), 0.0);
	}

	@Test
	public final void testIsSlightlyEqual() {
		Assert.assertEquals(underTestActions.isSlightlyEqual(membershipFuntion, operatorSet), 0.0);
	}

}
