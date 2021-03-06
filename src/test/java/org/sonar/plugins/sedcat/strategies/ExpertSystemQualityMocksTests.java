package org.sonar.plugins.sedcat.strategies;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.sonar.api.ce.measure.Measure;
import org.sonar.api.ce.measure.test.TestComponent;
import org.sonar.api.ce.measure.test.TestMeasureComputerContext;
import org.sonar.api.measures.CoreMetrics;
import org.sonar.plugins.sedcat.start.SedcatMetricsKeys;
import org.sonar.plugins.sedcat.storage.QualityMeasureStore;
import org.sonar.plugins.sedcat.xfuzzy.quality.Quality;

/**
 * Tests for {@link org.sonar.plugins.sedcat.strategies.ExpertSystemQuality}.
 * 
 * @author alan.sastre
 * @version 1.0.0
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({ QualityMeasureStore.class, ExpertSystemQuality.class, TestMeasureComputerContext.class,
		Quality.class, Measure.class, TestComponent.class,
		})
public class ExpertSystemQualityMocksTests {

	private ExpertSystemQuality underTest;
	private TestMeasureComputerContext contextMocked;
	private Measure measureMocked;

	@Before
	public void setUp() throws Exception {

		contextMocked = mock(TestMeasureComputerContext.class);
		measureMocked = mock(Measure.class);
		
		//mocks setup
		when(measureMocked.getDoubleValue()).thenReturn(50.0);
		when(measureMocked.getIntValue()).thenReturn(200);
		
		when(contextMocked.getMeasure(CoreMetrics.TEST_SUCCESS_DENSITY_KEY)).thenReturn(measureMocked);
		when(contextMocked.getMeasure(CoreMetrics.COVERAGE_KEY)).thenReturn(measureMocked);
		when(contextMocked.getMeasure(SedcatMetricsKeys.MUTANTS_KEY)).thenReturn(measureMocked);
		when(contextMocked.getMeasure(CoreMetrics.TESTS_KEY)).thenReturn(measureMocked);
		when(contextMocked.getMeasure(CoreMetrics.NCLOC_KEY)).thenReturn(measureMocked);
		when(contextMocked.getMeasure(SedcatMetricsKeys.COMPLEXITY_CLASS_KEY)).thenReturn(measureMocked);
		

		underTest = new ExpertSystemQuality(contextMocked);

	}


	/**
	 * Test method for {@link org.sonar.plugins.sedcat.strategies.ExpertSystemQuality#extractValues()}.
	 */
	@Test
	public final void testExtractValues() {


		double[] result = underTest.extractValues();

		Assert.assertFalse(result.length == 0);
		
		//verify methods executions
		verify(measureMocked, times(4)).getDoubleValue();
		verify(measureMocked, times(2)).getIntValue();
	}
	
	/**
	 * Test method for {@link org.sonar.plugins.sedcat.strategies.ExpertSystemQuality#extractValues()}.
	 */
	@Test
	public final void testExtractValuesArrayLength() {
		Assert.assertTrue(underTest.extractValues().length>=6);
	}
	
	
	/**
	 * Test method for {@link org.sonar.plugins.sedcat.strategies.AbstractInferenceProcess#getMeasureDoubleChecked()}.
	 */
	@Test
	public final void testGetMeasureDoubleChecked() {
		double result = underTest.getMeasureDoubleChecked(CoreMetrics.TEST_SUCCESS_DENSITY_KEY);
		Assert.assertEquals(50.0, result, 0.0);
	}
	
	/**
	 * Test method for {@link org.sonar.plugins.sedcat.strategies.AbstractInferenceProcess#getMeasureDoubleChecked()}.
	 */
	@Test
	public final void testGetMeasureIntChecked() {
		int result = underTest.getMeasureIntChecked(CoreMetrics.TEST_SUCCESS_DENSITY_KEY);
		Assert.assertEquals(200.0, result, 0.0);
	}
	/**
	 * Test method for {@link org.sonar.plugins.sedcat.strategies.ExpertSystemQuality#ExpertSystemQuality(org.sonar.api.ce.measure.MeasureComputer.MeasureComputerContext)}.
	 */
	@Test
	public final void testExpertSystemQualityNotNull() throws Exception {
		
		underTest = new ExpertSystemQuality(contextMocked);
		
		Assert.assertNotNull(underTest.context);
		Assert.assertNotNull(underTest.measureStorer);
		Assert.assertNotNull(underTest.expertSystem);
		Assert.assertNotNull(underTest.START_SYSTEM_MESSAGE);
	}

	/**
	 * Test method for {@link org.sonar.plugins.sedcat.strategies.ExpertSystemQuality#ExpertSystemQuality(org.sonar.api.ce.measure.MeasureComputer.MeasureComputerContext)}.
	 */
	@Test
	public final void testExpertSystemQuality() throws Exception {

		QualityMeasureStore storerMock = mock(QualityMeasureStore.class);
		PowerMockito.whenNew(QualityMeasureStore.class).withNoArguments().thenReturn(storerMock);

		Quality expertSystemMock = mock(Quality.class);
		PowerMockito.whenNew(Quality.class).withNoArguments().thenReturn(expertSystemMock);

		ExpertSystemQuality underTest2 = new ExpertSystemQuality(contextMocked);

		PowerMockito.verifyNew(QualityMeasureStore.class, times(1)).withNoArguments();
		PowerMockito.verifyNew(Quality.class, times(1)).withNoArguments();
		Assert.assertEquals(contextMocked, underTest2.context);

	}

	/**
	 * Test method for {@link org.sonar.plugins.sedcat.strategies.AbstractInferenceProcess#xfuzzyProcess()}.
	 */

	@Test
	public final void testXfuzzyProcessBehaviour() throws Exception {

		// test data
		double[] qualityInputMetrics = new double[] { 100.0, 100.0, 100.0, 100.0, 100.0, 30.0};
		double[] qualityOutputMetrics = new double[] { 100, 100, 100, 100, 100, };

		// mocks: creation and configuration
		QualityMeasureStore storerMock = mock(QualityMeasureStore.class);
		PowerMockito.whenNew(QualityMeasureStore.class).withNoArguments().thenReturn(storerMock);

		Quality expertSystemMock = mock(Quality.class);
		PowerMockito.whenNew(Quality.class).withNoArguments().thenReturn(expertSystemMock);

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
	 * Test method for {@link org.sonar.plugins.sedcat.strategies.AbstractInferenceProcess#xfuzzyProcess()}.
	 */
	@Test
	public final void testXfuzzyProcessState() throws Exception {

		underTest.xfuzzyProcess();
		verify(contextMocked, times(1)).addMeasure(SedcatMetricsKeys.QUALITY_MEASURE_KEY, 5.5);

	}

}
