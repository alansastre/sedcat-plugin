package org.sonar.plugins.sedcat.storage;

import org.junit.Assert;
import org.junit.Test;


public class ActionMessageConstantsTest {

	private static final int NUMBER_CONSTANTS = 65;

	@Test
	public final void testConstants() throws NoSuchFieldException, SecurityException {

		for (int i = 0; i <= NUMBER_CONSTANTS; i++) {
			Assert.assertTrue(
					ActionsMessageConstants.class.getField("MESSAGE_SET" + i).getName().length() > 0);
		}
	}
	

}
