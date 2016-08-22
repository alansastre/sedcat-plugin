package org.sonar.plugins.sedcat.strategies;

import org.junit.Before;
import org.junit.Test;
import org.sonar.api.ce.measure.Component.FileAttributes;
import org.sonar.api.ce.measure.Component.Type;
import org.sonar.api.ce.measure.MeasureComputer.MeasureComputerDefinition;
import org.sonar.api.ce.measure.test.TestComponent;
import org.sonar.api.ce.measure.test.TestMeasureComputerContext;
import org.sonar.api.ce.measure.test.TestMeasureComputerDefinitionContext;
import org.sonar.api.ce.measure.test.TestSettings;
import org.sonar.plugins.sedcat.start.GeneralComputer;
import org.sonar.plugins.sedcat.strategies.ExpertSystemQuality;
import org.junit.Assert;
/**
 * Tests for {@link org.sonar.plugins.sedcat.strategies.ExpertSystemQuality}.
 * 
 * @author alan.sastre
 * @version 1.0
 */

public class ExpertSystemQualityTests {

	private ExpertSystemQuality underTest;
	private TestMeasureComputerContext context;
	private TestMeasureComputerDefinitionContext defContext;
	private MeasureComputerDefinition def;
	private TestComponent mockedComponent;
	private TestSettings settings;
	private GeneralComputer computerForData;
	private FileAttributes fileAttributes;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {

		mockedComponent = new TestComponent("prueba.prueba", Type.PROJECT, fileAttributes);

		settings = new TestSettings();
		defContext = new TestMeasureComputerDefinitionContext();
		computerForData = new GeneralComputer();
		def = computerForData.define(defContext);
		context = new TestMeasureComputerContext(mockedComponent, settings, def);

		underTest = new ExpertSystemQuality(context);
	}

	/**
	 * Test method for
	 * {@link org.sonar.plugins.sedcat.strategies.ExpertSystemActions#extractValues()}
	 * .
	 */
	@Test
	public final void testExtractValues() {

		// execute
		double[] result = underTest.extractValues();
		Assert.assertTrue(result.length == 6);
		for (int i = 0; i < result.length; i++) {
			Assert.assertTrue(result[i] == 0.0);
		}

	}

	
	/**
	 * Test method for
	 * {@link org.sonar.plugins.sedcat.strategies.ExpertSystemActions#ExpertSystemActions(org.sonar.api.ce.measure.MeasureComputer.MeasureComputerContext)}
	 * .
	 * 
	 * @throws Exception
	 */

	@Test
	public final void testExpertSystemActions() throws Exception {

		underTest = new ExpertSystemQuality(context);
		
		Assert.assertNotNull(underTest.context);
		Assert.assertNotNull(underTest.expertSystem);
		Assert.assertNotNull(underTest.measureStorer);
		
	}

}
