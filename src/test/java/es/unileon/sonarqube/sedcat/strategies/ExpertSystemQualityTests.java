package es.unileon.sonarqube.sedcat.strategies;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
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
import org.sonar.api.ce.measure.MeasureComputer.MeasureComputerDefinition;
import org.sonar.api.ce.measure.test.TestComponent;
import org.sonar.api.ce.measure.test.TestMeasureComputerContext;
import org.sonar.api.ce.measure.test.TestMeasureComputerDefinitionContext;
import org.sonar.api.ce.measure.test.TestSettings;
import es.unileon.sonarqube.sedcat.start.GeneralComputer;
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
		Calidad_1.class })
public class ExpertSystemQualityTests {

	private ExpertSystemQuality underTest;

	// data
	private TestMeasureComputerDefinitionContext defContext;
	private MeasureComputerDefinition def;
	private TestComponent mockedComponent;
	private TestSettings settings;
	private GeneralComputer computerForData;
	private TestMeasureComputerContext context;

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

		mockedComponent = mock(TestComponent.class);
		settings = new TestSettings();
		defContext = new TestMeasureComputerDefinitionContext();
		computerForData = new GeneralComputer();
		def = computerForData.define(defContext);
		context = new TestMeasureComputerContext(mockedComponent, settings, def);

		underTest = new ExpertSystemQuality(context);

	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public final void testExtractValues() {

		context.addMeasure(SedcatMetricsKeys.SUCCESS_UNIT_TESTS_KEY, 87.75);
		context.addMeasure(SedcatMetricsKeys.COVERAGE_UNIT_TESTS_KEY, 43.25);
		context.addMeasure(SedcatMetricsKeys.MUTANTS_KEY, 67.214);
		context.addMeasure(SedcatMetricsKeys.NUMBERTESTS_KEY, 3245);
		context.addMeasure(SedcatMetricsKeys.CODE_LINES_KEY, 50000);

		double[] result = underTest.extractValues();

		Assert.assertFalse(result.length == 0);

		Assert.assertEquals(87.75, result[0], 0.0);
		Assert.assertEquals(43.25, result[1], 0.0);
		Assert.assertEquals(67.214, result[2], 0.0);
		Assert.assertEquals(3245, result[3], 0.0);
		Assert.assertEquals(50000, result[4], 0.0);
	}

	@Test
	public final void testCheckNotNullInputMetricsOk() {

		context.addMeasure(SedcatMetricsKeys.SUCCESS_UNIT_TESTS_KEY, 87.75);
		context.addMeasure(SedcatMetricsKeys.COVERAGE_UNIT_TESTS_KEY, 43.25);
		context.addMeasure(SedcatMetricsKeys.MUTANTS_KEY, 67.214);
		context.addMeasure(SedcatMetricsKeys.NUMBERTESTS_KEY, 3245);
		context.addMeasure(SedcatMetricsKeys.CODE_LINES_KEY, 50000);

		Measure[] qualityInputMetrics = new Measure[] {

			context.getMeasure(SedcatMetricsKeys.SUCCESS_UNIT_TESTS_KEY),
			context.getMeasure(SedcatMetricsKeys.COVERAGE_UNIT_TESTS_KEY),
			context.getMeasure(SedcatMetricsKeys.MUTANTS_KEY),
			context.getMeasure(SedcatMetricsKeys.NUMBERTESTS_KEY),
			context.getMeasure(SedcatMetricsKeys.CODE_LINES_KEY),

		};

		underTest.checkNotNullInputMetrics(qualityInputMetrics);

	}

	@Test
	public final void testCheckNotNullInputMetricsNull() {

		Measure[] qualityInputMetrics = new Measure[] { null, null, null, null, null, };

		exit.expectSystemExitWithStatus(-1);
		underTest.checkNotNullInputMetrics(qualityInputMetrics);

	}

	@Test
	public final void testExpertSystemQuality() throws Exception {

		QualityMeasureStore storerMock = mock(QualityMeasureStore.class);
		PowerMockito.whenNew(QualityMeasureStore.class).withNoArguments().thenReturn(storerMock);

		Calidad_1 expertSystemMock = mock(Calidad_1.class);
		PowerMockito.whenNew(Calidad_1.class).withNoArguments().thenReturn(expertSystemMock);

		ExpertSystemQuality underTest2 = new ExpertSystemQuality(context);

		PowerMockito.verifyNew(QualityMeasureStore.class, times(1)).withNoArguments();
		PowerMockito.verifyNew(Calidad_1.class, times(1)).withNoArguments();
		Assert.assertEquals(context, underTest2.context);

	}

	@Test
	public final void testXfuzzyProcessBehaviour() throws Exception {

		// test data
		context.addMeasure(SedcatMetricsKeys.SUCCESS_UNIT_TESTS_KEY, 87.75);
		context.addMeasure(SedcatMetricsKeys.COVERAGE_UNIT_TESTS_KEY, 43.25);
		context.addMeasure(SedcatMetricsKeys.MUTANTS_KEY, 67.214);
		context.addMeasure(SedcatMetricsKeys.NUMBERTESTS_KEY, 3245);
		context.addMeasure(SedcatMetricsKeys.CODE_LINES_KEY, 50000);

		double[] qualityInputMetrics = new double[] { 100, 100, 100, 100, 100, };
		double[] qualityOutputMetrics = new double[] { 100, 100, 100, 100, 100, };

		// mocks: creation and configuration
		QualityMeasureStore storerMock = mock(QualityMeasureStore.class);
		PowerMockito.whenNew(QualityMeasureStore.class).withNoArguments().thenReturn(storerMock);

		Calidad_1 expertSystemMock = mock(Calidad_1.class);
		PowerMockito.whenNew(Calidad_1.class).withNoArguments().thenReturn(expertSystemMock);

		PowerMockito.when(expertSystemMock.crispInference(qualityInputMetrics)).thenReturn(qualityOutputMetrics);

		// partial mocking with spies
		ExpertSystemQuality underTest2 = new ExpertSystemQuality(context);
		ExpertSystemQuality spy = Mockito.spy(underTest2);

		when(spy.extractValues()).thenReturn(qualityInputMetrics);

		// real method
		spy.xfuzzyProcess();

		// verificate behaviour
		// 2 = 1 from xfuzzyProcess + 1 from when setup
		Mockito.verify(spy, times(2)).extractValues();
		Mockito.verify(expertSystemMock, times(1)).crispInference(qualityInputMetrics);
		Mockito.verify(storerMock, times(1)).outputMeasureStore(qualityOutputMetrics, context);

	}

	@Test
	public final void testXfuzzyProcessState() throws Exception {

		context.addMeasure(SedcatMetricsKeys.SUCCESS_UNIT_TESTS_KEY, 87.75);
		context.addMeasure(SedcatMetricsKeys.COVERAGE_UNIT_TESTS_KEY, 43.25);
		context.addMeasure(SedcatMetricsKeys.MUTANTS_KEY, 67.214);
		context.addMeasure(SedcatMetricsKeys.NUMBERTESTS_KEY, 3245);
		context.addMeasure(SedcatMetricsKeys.CODE_LINES_KEY, 50000);
		underTest.xfuzzyProcess();
		Assert.assertEquals(42.186557392413185,
				context.getMeasure(SedcatMetricsKeys.QUALITY_MEASURE_KEY).getDoubleValue(), 0.0);
	}

}
