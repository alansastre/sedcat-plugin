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
import org.sonar.plugins.sedcat.start.SedcatConstants;
import org.sonar.plugins.sedcat.start.SedcatMetricsKeys;
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
	private TestComponent component;
	private TestSettings settings;
	private GeneralComputer computerForData;
	private FileAttributes fileAttributes;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {

		component = new TestComponent("prueba.prueba", Type.PROJECT, fileAttributes);

		settings = new TestSettings();
		
		settings.setValue(SedcatConstants.ACTIVE_MODE_KEY, "true");
		
		defContext = new TestMeasureComputerDefinitionContext();
		computerForData = new GeneralComputer();
		def = computerForData.define(defContext);
		context = new TestMeasureComputerContext(component, settings, def);

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
	
	@Test
	public final void testXfuzzyProcess() throws Exception {
		
		underTest = new ExpertSystemQuality(context);
		underTest.xfuzzyProcess();
		
		Assert.assertEquals(context.getMeasure(SedcatMetricsKeys.QUALITY_MEASURE_KEY).getDoubleValue(), 0.0, 0);
		Assert.assertTrue(context.getMeasure(SedcatMetricsKeys.QUALITY_MESSAGE_KEY).getStringValue().length() > 0);

	}
	
	@Test
	public final void testGeneralComputerXfuzzyProcess() throws Exception {
		
		computerForData.compute(context);
		Assert.assertEquals(context.getMeasure(SedcatMetricsKeys.QUALITY_MEASURE_KEY).getDoubleValue(), 0.0, 0);
		Assert.assertTrue(context.getMeasure(SedcatMetricsKeys.QUALITY_MESSAGE_KEY).getStringValue().length() > 0);
		Assert.assertTrue(context.getMeasure(SedcatMetricsKeys.ACTIONS_TO_PERFORM_KEY).getStringValue().length() > 0);
		Assert.assertTrue(context.getMeasure(SedcatMetricsKeys.ACTIONS_MESSAGE_KEY).getStringValue().length() > 0);

	}

}
