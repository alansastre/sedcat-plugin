package org.sonar.plugins.sedcat.strategies;

import static org.mockito.Mockito.mock;

import org.junit.Before;
import org.junit.Test;
import org.sonar.plugins.sedcat.xfuzzy.quality.FamiliarMembershipFunction;
import org.sonar.plugins.sedcat.xfuzzy.quality.FuzzySingleton;
import org.sonar.plugins.sedcat.xfuzzy.quality.MembershipFunctionFamily;
import org.sonar.plugins.sedcat.xfuzzy.quality.OP_Quality__default_;
import org.testng.Assert;

public class FamiliarMembershipFunctionQualityTests {

	private FamiliarMembershipFunction underTestQuality;
	private MembershipFunctionFamily membershipFamily;
	private FuzzySingleton membershipFuntion;
	private OP_Quality__default_ operatorSet;
	
	@Before
	public void setUp() throws Exception {
		membershipFamily = mock(MembershipFunctionFamily.class);
		membershipFuntion = new FuzzySingleton(2);
		operatorSet = new OP_Quality__default_();

		underTestQuality = new FamiliarMembershipFunction(membershipFamily, 2);

	}

	@Test
	public final void testCenter() {
		Assert.assertEquals(underTestQuality.center(), 0.0);
	}

	@Test
	public final void testBasis() {
		Assert.assertEquals(underTestQuality.basis(), 0.0);
	}

	@Test
	public final void testParam() {
		Assert.assertEquals(underTestQuality.param(2), 0.0);
	}

	@Test
	public final void testIsEqualDouble() {
		Assert.assertEquals(underTestQuality.isEqual(2.00), 0.0);
	}

	@Test
	public final void testIsSmallerOrEqualDouble() {
		Assert.assertEquals(underTestQuality.isSmallerOrEqual(2.00), 0.0);
	}

	@Test
	public final void testIsGreaterOrEqualDouble() {
		Assert.assertEquals(underTestQuality.isGreaterOrEqual(2.00), 0.0);
	}

	@Test
	public final void testFamiliarMembershipFunction() {

		FamiliarMembershipFunction underTest1 = new FamiliarMembershipFunction(membershipFamily, 2);
		underTest1.getClass().equals(FamiliarMembershipFunction.class);
		Assert.assertNotNull(underTest1);

	}

	@Test
	public final void testIsEqualMembershipFunction() {
		Assert.assertEquals(underTestQuality.isEqual(membershipFuntion), 0.0);
	}

	@Test
	public final void testIsGreaterOrEqualMembershipFunction() {
		Assert.assertEquals(underTestQuality.isGreaterOrEqual(membershipFuntion), 0.0);
	}

	@Test
	public final void testIsSmallerOrEqualMembershipFunction() {
		Assert.assertEquals(underTestQuality.isSmallerOrEqual(membershipFuntion), 0.0);
	}

	@Test
	public final void testIsGreater() {
		Assert.assertEquals(underTestQuality.isGreater(membershipFuntion, operatorSet), 1.0);
	}

	@Test
	public final void testIsSmaller() {
		Assert.assertEquals(underTestQuality.isSmaller(membershipFuntion, operatorSet), 1.0);
	}

	@Test
	public final void testIsNotEqual() {
		Assert.assertEquals(underTestQuality.isNotEqual(membershipFuntion, operatorSet), 1.0);
	}

	@Test
	public final void testIsApproxEqual() {
		Assert.assertEquals(underTestQuality.isApproxEqual(membershipFuntion, operatorSet), 0.0);
	}

	@Test
	public final void testIsVeryEqual() {
		Assert.assertEquals(underTestQuality.isVeryEqual(membershipFuntion, operatorSet), 0.0);
	}

	@Test
	public final void testIsSlightlyEqual() {
		Assert.assertEquals(underTestQuality.isSlightlyEqual(membershipFuntion, operatorSet), 0.0);
	}

}