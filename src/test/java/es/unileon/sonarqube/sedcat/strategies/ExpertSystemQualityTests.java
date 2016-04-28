package es.unileon.sonarqube.sedcat.strategies;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.ExpectedSystemExit;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.sonar.api.ce.measure.Measure;
import org.sonar.api.ce.measure.test.TestComponent;
import org.sonar.api.ce.measure.test.TestMeasureComputerContext;
import es.unileon.sonarqube.sedcat.start.SedcatMetricsKeys;
import es.unileon.sonarqube.sedcat.storage.QualityMeasureStore;
import es.unileon.sonarqube.sedcat.xfuzzy.quality.Calidad_1;
import org.junit.Assert;
import org.powermock.api.mockito.*;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

/**
 * Tests for {@link es.unileon.sonarqube.sedcat.strategies.ExpertSystemQuality}.
 * 
 * @author alan.sastre
 * @version 1.0
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({ QualityMeasureStore.class, ExpertSystemQuality.class, TestMeasureComputerContext.class,
		Calidad_1.class, Measure.class, TestComponent.class,
		})
public class ExpertSystemQualityTests {

	private ExpertSystemQuality underTest;
	private TestMeasureComputerContext contextMocked;
	private Measure measureMocked;

	@Rule
	public final ExpectedSystemExit exit = ExpectedSystemExit.none();

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {

		contextMocked = mock(TestMeasureComputerContext.class);
		measureMocked = mock(Measure.class);
		
		//mocks setup
		when(measureMocked.getDoubleValue()).thenReturn(100.0);
		when(measureMocked.getIntValue()).thenReturn(200);
		when(contextMocked.getMeasure(SedcatMetricsKeys.SUCCESS_UNIT_TESTS_KEY)).thenReturn(measureMocked);
		when(contextMocked.getMeasure(SedcatMetricsKeys.COVERAGE_UNIT_TESTS_KEY)).thenReturn(measureMocked);
		when(contextMocked.getMeasure(SedcatMetricsKeys.MUTANTS_KEY)).thenReturn(measureMocked);
		when(contextMocked.getMeasure(SedcatMetricsKeys.NUMBERTESTS_KEY)).thenReturn(measureMocked);
		when(contextMocked.getMeasure(SedcatMetricsKeys.CODE_LINES_KEY)).thenReturn(measureMocked);

		underTest = new ExpertSystemQuality(contextMocked);

	}

	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link es.unileon.sonarqube.sedcat.strategies.ExpertSystemQuality#extractValues()}.
	 */

	@Test
	public final void testExtractValues() {


		double[] result = underTest.extractValues();

		Assert.assertFalse(result.length == 0);
		
		//verify methods executions
		verify(measureMocked, times(3)).getDoubleValue();
		verify(measureMocked, times(2)).getIntValue();
	}

	/**
	 * Test method for {@link es.unileon.sonarqube.sedcat.strategies.AbstractInferenceProcess#checkNotNullInputMetrics(org.sonar.api.ce.measure.Measure[])}.
	 */
	@Test
	public final void testCheckNotNullInputMetricsOk() {

		Measure[] qualityInputMetrics = new Measure[] {

				measureMocked,
				measureMocked,
				measureMocked,
				measureMocked,
				measureMocked,

		};

		underTest.checkNotNullInputMetrics(qualityInputMetrics);

	}

	/**
	 * Test method for {@link es.unileon.sonarqube.sedcat.strategies.AbstractInferenceProcess#checkNotNullInputMetrics(org.sonar.api.ce.measure.Measure[])}.
	 */
	@Test
	public final void testCheckNotNullInputMetricsNull() {

		Measure[] qualityInputMetrics = new Measure[] { null, null, null, null, null, };

		exit.expectSystemExitWithStatus(-1);
		underTest.checkNotNullInputMetrics(qualityInputMetrics);

	}

	/**
	 * Test method for {@link es.unileon.sonarqube.sedcat.strategies.ExpertSystemQuality#ExpertSystemQuality(org.sonar.api.ce.measure.MeasureComputer.MeasureComputerContext)}.
	 */
	@Test
	public final void testExpertSystemQuality() throws Exception {

		QualityMeasureStore storerMock = mock(QualityMeasureStore.class);
		PowerMockito.whenNew(QualityMeasureStore.class).withNoArguments().thenReturn(storerMock);

		Calidad_1 expertSystemMock = mock(Calidad_1.class);
		PowerMockito.whenNew(Calidad_1.class).withNoArguments().thenReturn(expertSystemMock);

		ExpertSystemQuality underTest2 = new ExpertSystemQuality(contextMocked);

		PowerMockito.verifyNew(QualityMeasureStore.class, times(1)).withNoArguments();
		PowerMockito.verifyNew(Calidad_1.class, times(1)).withNoArguments();
		Assert.assertEquals(contextMocked, underTest2.context);

	}

	/**
	 * Test method for {@link es.unileon.sonarqube.sedcat.strategies.AbstractInferenceProcess#xfuzzyProcess()}.
	 */

	@Test
	public final void testXfuzzyProcessBehaviour() throws Exception {

		// test data
		double[] qualityInputMetrics = new double[] { 100.0, 100.0, 100.0, 100.0, 100.0};
		double[] qualityOutputMetrics = new double[] { 100, 100, 100, 100, 100, };

		// mocks: creation and configuration
		QualityMeasureStore storerMock = mock(QualityMeasureStore.class);
		PowerMockito.whenNew(QualityMeasureStore.class).withNoArguments().thenReturn(storerMock);

		Calidad_1 expertSystemMock = mock(Calidad_1.class);
		PowerMockito.whenNew(Calidad_1.class).withNoArguments().thenReturn(expertSystemMock);

		PowerMockito.when(expertSystemMock.crispInference(qualityInputMetrics)).thenReturn(qualityOutputMetrics);

		// partial mocking with spies
		ExpertSystemQuality underTest2 = new ExpertSystemQuality(contextMocked);
		ExpertSystemQuality spy = Mockito.spy(underTest2);

		Mockito.doReturn(qualityInputMetrics).when(spy).extractValues();
		// real method
		spy.xfuzzyProcess();

		// verificate behaviour
		// 2 = 1 from xfuzzyProcess + 1 from when setup
		Mockito.verify(spy, times(1)).extractValues();
		Mockito.verify(expertSystemMock, times(1)).crispInference(qualityInputMetrics);
		Mockito.verify(storerMock, times(1)).outputMeasureStore(qualityOutputMetrics, contextMocked);

	}

	/**
	 * Test method for {@link es.unileon.sonarqube.sedcat.strategies.AbstractInferenceProcess#xfuzzyProcess()}.
	 */

	@Test
	public final void testXfuzzyProcessState() throws Exception {

		underTest.xfuzzyProcess();
		verify(contextMocked, times(1)).addMeasure(SedcatMetricsKeys.QUALITY_MEASURE_KEY, 73.55144775847887);

	}

}
