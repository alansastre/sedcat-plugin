package org.sonar.plugins.sedcat.strategies;

import org.junit.Assert;
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
import org.sonar.plugins.sedcat.xfuzzy.quality.FuzzySingleton;
import org.sonar.plugins.sedcat.xfuzzy.quality.MF_xfl_trapezoid;
/**
 * Tests for {@link org.sonar.plugins.sedcat.strategies.ExpertSystemQuality}.
 * 
 * @author alan.sastre
 * @version 1.0.0
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

	
	@Test
	public final void testMF_xfl_trapezoid() throws Exception {

		MF_xfl_trapezoid trap = new MF_xfl_trapezoid(5.0, 10.0, 20.0, new double[]{ 2.0, 3.0, 4.0, 5.0}, new double[]{ 2.0, 2.0});
		
		Assert.assertTrue(trap.param(0) == 2.0);
		Assert.assertTrue(trap.param(1) == 3.0);
		Assert.assertTrue(trap.param(2) == 4.0);
		Assert.assertTrue(trap.param(3) == 5.0);
		
		Assert.assertEquals(0.0, trap.isGreaterOrEqual(2.0), 0.0);
		Assert.assertEquals(1.0, trap.isSmallerOrEqual(2.0), 0.0);
		Assert.assertEquals(3.5, trap.center(), 0.0);
		Assert.assertEquals(3.0, trap.basis(), 0.0);
		
	}
	
	@Test
	public final void testFuzzySingleton() throws Exception {

		FuzzySingleton fzs = new FuzzySingleton(2.0);
		Assert.assertEquals(0.0, fzs.compute(3.0), 0.0);
		Assert.assertEquals(0.0, fzs.compute(2.0), 1.0);
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
