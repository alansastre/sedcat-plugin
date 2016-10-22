package org.sonar.plugins.sedcat.xfuzzy;

import org.sonar.plugins.sedcat.xfuzzy.quality.MembershipFunctionFamily;

/**
 * Class for test MembershipFunctionFamily
 *	@author alan.sastre
 *	@version 1.0.0
 */
public class MembershipFunctionFamilyQuality extends MembershipFunctionFamily {

	@Override
	public double param(int i) {
		return 0;
	}

	@Override
	public double isEqual(int i, double x) {
		return 0;
	}
	
	public MembershipFunctionFamilyQuality(double min, double max, double step) {
		this.min = min;
		this.max = max;
		this.step = step;
	}
}
