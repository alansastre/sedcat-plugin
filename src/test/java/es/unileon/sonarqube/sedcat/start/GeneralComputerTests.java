/**
 * 
 */
package es.unileon.sonarqube.sedcat.start;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.sonar.api.ce.measure.MeasureComputer.MeasureComputerDefinition;
import org.sonar.api.ce.measure.test.TestMeasureComputerContext;
import org.sonar.api.ce.measure.test.TestMeasureComputerDefinitionContext;
import es.unileon.sonarqube.sedcat.strategies.ExpertSystemActions;
import es.unileon.sonarqube.sedcat.strategies.ExpertSystemQuality;
import java.util.Set;
import org.sonar.api.ce.measure.Component.FileAttributes;
import org.sonar.api.ce.measure.Component.Type;
import static org.mockito.Mockito.*;
import org.sonar.api.ce.measure.test.*;
import org.powermock.api.mockito.*;

/**
 * @author alan.sastre
 * @version 1.0
 * @see:
 * Tests with:
 * https://github.com/SonarSource/sonarqube/blob/master/sonar-plugin-
 * api/src/main/java/org/sonar/api/ce/measure/test/
 * TestMeasureComputerContext.java
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({ GeneralComputer.class, ExpertSystemQuality.class, ExpertSystemActions.class })
public class GeneralComputerTests {

	private GeneralComputer underTest = new GeneralComputer();
	private TestMeasureComputerDefinitionContext defContext;
	private MeasureComputerDefinition def;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {

		defContext = new TestMeasureComputerDefinitionContext();
		def = underTest.define(defContext);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/*
	 * Test for state with stubs
	 */

	/**
	 * Test method for
	 * {@link es.unileon.sonarqube.sedcat.start.GeneralComputer#define()}.
	 */
	@Ignore("pending sonarqube check unique outputs")  
	@Test
	public final void testdefine_Correct_State() {

		Assert.assertNotNull(def);

		// Probar metricas de entrada
		Set<String> inputMetrics = def.getInputMetrics();
		Assert.assertEquals(5, inputMetrics.size());

		Assert.assertTrue(inputMetrics.contains(SedcatMetricsKeys.SUCCESS_UNIT_TESTS_KEY));
		Assert.assertTrue(inputMetrics.contains(SedcatMetricsKeys.COVERAGE_UNIT_TESTS_KEY));
		Assert.assertTrue(inputMetrics.contains(SedcatMetricsKeys.NUMBERTESTS_KEY));
		Assert.assertTrue(inputMetrics.contains(SedcatMetricsKeys.CODE_LINES_KEY));
		Assert.assertTrue(inputMetrics.contains(SedcatMetricsKeys.MUTANTS_KEY));

		// Probar metricas de salida
		Set<String> ouputMetrics = def.getOutputMetrics();
		Assert.assertEquals(7, ouputMetrics.size());

		Assert.assertTrue(ouputMetrics.contains(SedcatMetricsKeys.QUALITY_MEASURE_KEY));
		Assert.assertTrue(ouputMetrics.contains(SedcatMetricsKeys.ACTIONS_TO_PERFORM_KEY));
		Assert.assertTrue(ouputMetrics.contains(SedcatMetricsKeys.SUCCESS_UNIT_TESTS_KEY));
		Assert.assertTrue(ouputMetrics.contains(SedcatMetricsKeys.COVERAGE_UNIT_TESTS_KEY));
		Assert.assertTrue(ouputMetrics.contains(SedcatMetricsKeys.NUMBERTESTS_KEY));
		Assert.assertTrue(ouputMetrics.contains(SedcatMetricsKeys.CODE_LINES_KEY));
		Assert.assertTrue(ouputMetrics.contains(SedcatMetricsKeys.MUTANTS_KEY));

	}

	/*
	 * Test for any Type of Component: PROJECT, MODULE, DIRECTORY, FILE, VIEW,
	 * SUBVIEW
	 * 
	 */
	/**
	 * Test method for
	 * {@link es.unileon.sonarqube.sedcat.start.GeneralComputer#compute()}.
	 */
	@Ignore("pending sonarqube check unique outputs")  
	@Test
	public final void testcompute_File_noExecution() {

		FileAttributes mockedFileAttributes = mock(FileAttributes.class);
		TestComponent component = new TestComponent("", Type.FILE, mockedFileAttributes);
		TestSettings settings = new TestSettings();

		TestMeasureComputerContext context = new TestMeasureComputerContext(component, settings, def);
		underTest.compute(context);

		Assert.assertFalse(underTest.isProject());
	}

	/**
	 * Test method for
	 * {@link es.unileon.sonarqube.sedcat.start.GeneralComputer#compute()}.
	 */
	@Ignore("pending sonarqube check unique outputs")  
	@Test
	public final void testcompute_View_noExecution() {

		TestSettings settings = new TestSettings();
		TestComponent mockedComponent = mock(TestComponent.class);
		when(mockedComponent.getType()).thenReturn(Type.VIEW);

		TestMeasureComputerContext context = new TestMeasureComputerContext(mockedComponent, settings, def);
		underTest.compute(context);

		Assert.assertFalse(underTest.isProject());
	}

	/**
	 * Test method for
	 * {@link es.unileon.sonarqube.sedcat.start.GeneralComputer#compute()}.
	 */
	@Ignore("pending sonarqube check unique outputs")  
	@Test
	public final void testcompute_Subview_noExecution() {

		TestSettings settings = new TestSettings();
		TestComponent mockedComponent = mock(TestComponent.class);
		when(mockedComponent.getType()).thenReturn(Type.SUBVIEW);

		TestMeasureComputerContext context = new TestMeasureComputerContext(mockedComponent, settings, def);
		underTest.compute(context);

		Assert.assertFalse(underTest.isProject());
	}

	/**
	 * Test method for
	 * {@link es.unileon.sonarqube.sedcat.start.GeneralComputer#compute()}.
	 */
	@Ignore("pending sonarqube check unique outputs")  
	@Test
	public final void testcompute_Directory_noExecution() {

		TestSettings settings = new TestSettings();
		TestComponent mockedComponent = mock(TestComponent.class);
		when(mockedComponent.getType()).thenReturn(Type.DIRECTORY);

		TestMeasureComputerContext context = new TestMeasureComputerContext(mockedComponent, settings, def);
		underTest.compute(context);

		Assert.assertFalse(underTest.isProject());
	}

	/**
	 * Test method for
	 * {@link es.unileon.sonarqube.sedcat.start.GeneralComputer#compute()}.
	 */
	@Ignore("pending sonarqube check unique outputs")  
	@Test
	public final void testcompute_Module_noExecution() {

		TestSettings settings = new TestSettings();
		TestComponent mockedComponent = mock(TestComponent.class);
		when(mockedComponent.getType()).thenReturn(Type.MODULE);

		TestMeasureComputerContext context = new TestMeasureComputerContext(mockedComponent, settings, def);
		underTest.compute(context);

		Assert.assertFalse(underTest.isProject());
	}

	/**
	 * Test method for
	 * {@link es.unileon.sonarqube.sedcat.start.GeneralComputer#compute()}.
	 */
	@Ignore("pending sonarqube check unique outputs")  
	@Test
	public final void testcompute_Project_Execution() {

		TestComponent mockedComponent = mock(TestComponent.class);
		TestSettings settings = new TestSettings();

		TestMeasureComputerContext context = new TestMeasureComputerContext(mockedComponent, settings, def);

		when(mockedComponent.getType()).thenReturn(Type.PROJECT);

		// añadir valores a las metricas de entrada para el test:
		context.addMeasure(SedcatMetricsKeys.SUCCESS_UNIT_TESTS_KEY, 100.0);
		context.addMeasure(SedcatMetricsKeys.COVERAGE_UNIT_TESTS_KEY, 100.0);
		context.addMeasure(SedcatMetricsKeys.NUMBERTESTS_KEY, 100);
		context.addMeasure(SedcatMetricsKeys.CODE_LINES_KEY, 100);
		context.addMeasure(SedcatMetricsKeys.MUTANTS_KEY, 100.0);

		underTest.compute(context);

		Assert.assertTrue(underTest.isProject());

		// Comprobamos la integridad de las metricas de entrada
		Assert.assertEquals(100.0, context.getMeasure(SedcatMetricsKeys.SUCCESS_UNIT_TESTS_KEY).getDoubleValue(), 0.00);
		Assert.assertEquals(100.0, context.getMeasure(SedcatMetricsKeys.COVERAGE_UNIT_TESTS_KEY).getDoubleValue(),
				0.00);
		Assert.assertEquals(100, context.getMeasure(SedcatMetricsKeys.NUMBERTESTS_KEY).getIntValue(), 0.00);
		Assert.assertEquals(100, context.getMeasure(SedcatMetricsKeys.CODE_LINES_KEY).getIntValue(), 0.00);
		Assert.assertEquals(100.0, context.getMeasure(SedcatMetricsKeys.MUTANTS_KEY).getDoubleValue(), 0.00);

		// Comprobamos metricas de salida
		Assert.assertEquals(73.55144775847887,
				context.getMeasure(SedcatMetricsKeys.QUALITY_MEASURE_KEY).getDoubleValue(), 0.00);
		Assert.assertEquals("Improve the following parameters in order: Number Of Tests",
				context.getMeasure(SedcatMetricsKeys.ACTIONS_TO_PERFORM_KEY).getStringValue());

	}

	/*
	 * Tests for behaviour with mocks
	 */

	/**
	 * Test method for
	 * {@link es.unileon.sonarqube.sedcat.start.GeneralComputer#compute()}.
	 * 
	 * @throws Exception
	 */
	@Ignore("pending sonarqube check unique outputs")  
	@Test
	public final void testcompute_Project_Behaviour() throws Exception {

		TestComponent mockedComponent = mock(TestComponent.class);
		TestSettings settings = new TestSettings();
		when(mockedComponent.getType()).thenReturn(Type.PROJECT);

		TestMeasureComputerContext context = new TestMeasureComputerContext(mockedComponent, settings, def);

		ExpertSystemQuality expertSystemQuality = mock(ExpertSystemQuality.class);
		PowerMockito.whenNew(ExpertSystemQuality.class).withArguments(context).thenReturn(expertSystemQuality);

		ExpertSystemActions expertSystemActions = mock(ExpertSystemActions.class);
		PowerMockito.whenNew(ExpertSystemActions.class).withArguments(context).thenReturn(expertSystemActions);

		underTest.compute(context);

		Assert.assertTrue(underTest.isProject());

		PowerMockito.verifyNew(ExpertSystemQuality.class, times(1)).withArguments(context);
		PowerMockito.verifyNew(ExpertSystemActions.class, times(1)).withArguments(context);

		Mockito.verify(expertSystemQuality, times(1)).xfuzzyProcess();
		Mockito.verify(expertSystemActions, times(1)).xfuzzyProcess();

	}
}
