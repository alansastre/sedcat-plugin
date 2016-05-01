/**
 * 
 */
package es.unileon.sonarqube.sedcat.strategies;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.Rule;
import org.junit.contrib.java.lang.system.ExpectedSystemExit;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.sonar.api.ce.measure.Measure;
import org.sonar.api.ce.measure.Component.Type;
import org.sonar.api.ce.measure.MeasureComputer.MeasureComputerDefinition;
import org.sonar.api.ce.measure.test.TestComponent;
import org.sonar.api.ce.measure.test.TestMeasureComputerContext;
import org.sonar.api.ce.measure.test.TestMeasureComputerDefinitionContext;
import org.sonar.api.ce.measure.test.TestSettings;
import es.unileon.sonarqube.sedcat.start.GeneralComputer;
import es.unileon.sonarqube.sedcat.start.SedcatMetricsKeys;
import es.unileon.sonarqube.sedcat.storage.ActionsMeasureStore;
import es.unileon.sonarqube.sedcat.xfuzzy.actions.Acciones_1;
//import es.unileon.sonarqube.sedcat.xfuzzy.actions.*;
import org.junit.Assert;
import org.powermock.api.mockito.*;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

/**
 * Tests for {@link es.unileon.sonarqube.sedcat.strategies.ExpertSystemActions}.
 * 
 * @author alan.sastre
 * @version 1.0
 */
/*
 * Bug in PowerMockRunner (JVM method size)
 * https://github.com/jayway/powermock/pull/661 It affects Acciones_1.class
 * http://docs.oracle.com/javase/specs/jvms/se8/html/jvms-4.html#jvms-4.7.3
 */

@RunWith(PowerMockRunner.class)
@PrepareForTest({ ActionsMeasureStore.class, ExpertSystemActions.class, TestMeasureComputerContext.class,
		// Acciones_1.class
})
public class ExpertSystemActionsTests {

	private ExpertSystemActions underTest;

	// data
	// private TestMeasureComputerDefinitionContext defContext;
	// private MeasureComputerDefinition def;
	// private TestSettings settings;
	// private GeneralComputer computerForData;
	private TestMeasureComputerContext context;

	private TestComponent mockedComponent;
	private TestMeasureComputerContext contextMocked;
	private Measure measureMocked;

	@Rule
	public final ExpectedSystemExit exit = ExpectedSystemExit.none();

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

		// mockedComponent = mock(TestComponent.class);
		// settings = new TestSettings();
		// defContext = new TestMeasureComputerDefinitionContext();
		// computerForData = new GeneralComputer();
		// def = computerForData.define(defContext);
		// context = new TestMeasureComputerContext(mockedComponent, settings,
		// def);

		contextMocked = mock(TestMeasureComputerContext.class);
		measureMocked = mock(Measure.class);

		// mocks setup
		when(contextMocked.getComponent()).thenReturn(mockedComponent);
		when(contextMocked.getMeasure(SedcatMetricsKeys.SUCCESS_UNIT_TESTS_KEY)).thenReturn(measureMocked);
		when(contextMocked.getMeasure(SedcatMetricsKeys.COVERAGE_UNIT_TESTS_KEY)).thenReturn(measureMocked);
		when(contextMocked.getMeasure(SedcatMetricsKeys.NUMBERTESTS_KEY)).thenReturn(measureMocked);
		when(contextMocked.getMeasure(SedcatMetricsKeys.CODE_LINES_KEY)).thenReturn(measureMocked);
		when(contextMocked.getMeasure(SedcatMetricsKeys.MUTANTS_KEY)).thenReturn(measureMocked);
		when(measureMocked.getDoubleValue()).thenReturn(100.0);
		when(measureMocked.getIntValue()).thenReturn(100);

		underTest = new ExpertSystemActions(contextMocked);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for
	 * {@link es.unileon.sonarqube.sedcat.strategies.ExpertSystemActions#extractValues()}
	 * .
	 */

	@Test
	public final void testExtractValues() {

		// execute
		double[] result = underTest.extractValues();

		Assert.assertFalse(result.length == 0);

		// verify methods executions
		verify(measureMocked, times(3)).getDoubleValue();
		verify(measureMocked, times(2)).getIntValue();

	}

	/**
	 * Test method for
	 * {@link es.unileon.sonarqube.sedcat.strategies.AbstractInferenceProcess#checkNotNullInputMetrics(org.sonar.api.ce.measure.Measure[])}
	 * .
	 */
	@Test
	public final void testCheckNotNullInputMetricsOk() {

		Measure[] actionsInputMetrics = new Measure[] {

				measureMocked, measureMocked, measureMocked, measureMocked, measureMocked,

		};

		underTest.checkNotNullInputMetrics(actionsInputMetrics);

	}

	/**
	 * Test method for
	 * {@link es.unileon.sonarqube.sedcat.strategies.AbstractInferenceProcess#checkNotNullInputMetrics(org.sonar.api.ce.measure.Measure[])}
	 */
	@Test
	public final void testCheckNotNullInputMetricsNull() {

		Measure[] actionsInputMetrics = new Measure[] { null, null, null, null, null, };

		exit.expectSystemExitWithStatus(-1);
		underTest.checkNotNullInputMetrics(actionsInputMetrics);

	}

	/**
	 * Test method for
	 * {@link es.unileon.sonarqube.sedcat.strategies.ExpertSystemActions#ExpertSystemActions(org.sonar.api.ce.measure.MeasureComputer.MeasureComputerContext)}
	 * .
	 * 
	 * @throws Exception
	 */

	@Test
	public final void testExpertSystemActions() throws Exception {

		ActionsMeasureStore storerMock = mock(ActionsMeasureStore.class);
		PowerMockito.whenNew(ActionsMeasureStore.class).withNoArguments().thenReturn(storerMock);

		// FIXME (powermock bug)
		// Acciones_1 expertSystemMock = mock(Acciones_1.class);
		// PowerMockito.whenNew(Acciones_1.class).withNoArguments().thenReturn(expertSystemMock);

		ExpertSystemActions underTest2 = new ExpertSystemActions(context);

		PowerMockito.verifyNew(ActionsMeasureStore.class, times(1)).withNoArguments();
		// PowerMockito.verifyNew(Acciones_1.class, times(1)).withNoArguments();
		Assert.assertEquals(context, underTest2.context);
	}

	/**
	 * Test method for
	 * {@link es.unileon.sonarqube.sedcat.strategies.AbstractInferenceProcess#xfuzzyProcess()}
	 * .
	 */
	@Ignore("pending Powermock bug fixes")
	@Test
	public final void testXfuzzyProcessBehaviour() throws Exception {

		// test data
		context.addMeasure(SedcatMetricsKeys.SUCCESS_UNIT_TESTS_KEY, 87.75);
		context.addMeasure(SedcatMetricsKeys.COVERAGE_UNIT_TESTS_KEY, 43.25);
		context.addMeasure(SedcatMetricsKeys.MUTANTS_KEY, 67.214);
		context.addMeasure(SedcatMetricsKeys.NUMBERTESTS_KEY, 3245);
		context.addMeasure(SedcatMetricsKeys.CODE_LINES_KEY, 50000);

		double[] actionsInputMetrics = new double[] { 100, 100, 100, 100, 100, };
		double[] actionsOutputMetrics = new double[] { 100, 100, 100, 100, 100, };

		// mocks: creation and configuration
		ActionsMeasureStore storerMock = mock(ActionsMeasureStore.class);
		PowerMockito.whenNew(ActionsMeasureStore.class).withNoArguments().thenReturn(storerMock);

		Acciones_1 expertSystemMock = mock(Acciones_1.class);
		PowerMockito.whenNew(Acciones_1.class).withNoArguments().thenReturn(expertSystemMock);

		PowerMockito.when(expertSystemMock.crispInference(actionsInputMetrics)).thenReturn(actionsOutputMetrics);

		// partial mocking with spies
		ExpertSystemActions underTest2 = new ExpertSystemActions(context);
		ExpertSystemActions spy = Mockito.spy(underTest2);

		when(spy.extractValues()).thenReturn(actionsInputMetrics);

		// real method
		spy.xfuzzyProcess();

		// verificate behaviour
		// 2 = 1 from xfuzzyProcess + 1 from when setup
		Mockito.verify(spy, times(2)).extractValues();
		Mockito.verify(expertSystemMock, times(1)).crispInference(actionsInputMetrics);
		Mockito.verify(storerMock, times(1)).outputMeasureStore(actionsOutputMetrics, context);
		/*
		 * TODO - verify result is stored verify(contextMocked,
		 * times(1)).addMeasure(SedcatMetricsKeys.ACTIONS_TO_PERFORM_KEY,
		 * "Improve the following parameters in order: Number Of Tests");
		 */

	}

}
