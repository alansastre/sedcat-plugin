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
import org.mockito.Mockito;
import org.mockito.internal.matchers.Any;
import org.sonar.api.ce.measure.Measure;
import org.sonar.api.ce.measure.Component.Type;
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

/**
 *  Tests for {@link es.unileon.sonarqube.sedcat.strategies.ExpertSystemQuality}.
 *	@author alan.sastre
 *	@version 1.0
 */
public class ExpertSystemQualityTests {

	private ExpertSystemQuality underTest;
	
	//data 
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
	     
//	     underTest = new ExpertSystemQuality(context);
	     
	}

	@After
	public void tearDown() throws Exception {
	}

//	@Test
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


//	@Test
	public final void testCheckNotNullInputMetricsOk() {

	     context.addMeasure(SedcatMetricsKeys.SUCCESS_UNIT_TESTS_KEY, 87.75);
	     context.addMeasure(SedcatMetricsKeys.COVERAGE_UNIT_TESTS_KEY, 43.25);
	     context.addMeasure(SedcatMetricsKeys.MUTANTS_KEY, 67.214);
	     context.addMeasure(SedcatMetricsKeys.NUMBERTESTS_KEY, 3245);
	     context.addMeasure(SedcatMetricsKeys.CODE_LINES_KEY, 50000);
	     
		Measure[] qualityInputMetrics = new Measure[]{
				
				context.getMeasure(SedcatMetricsKeys.SUCCESS_UNIT_TESTS_KEY),
				context.getMeasure(SedcatMetricsKeys.COVERAGE_UNIT_TESTS_KEY),
				context.getMeasure(SedcatMetricsKeys.MUTANTS_KEY),
				context.getMeasure(SedcatMetricsKeys.NUMBERTESTS_KEY),
				context.getMeasure(SedcatMetricsKeys.CODE_LINES_KEY),

		};

		underTest.checkNotNullInputMetrics(qualityInputMetrics);

	}
	
//	@Test
	public final void testCheckNotNullInputMetricsNull() {

		
		Measure[] qualityInputMetrics = new Measure[]{		
			null,
			null,
			null,
			null,
			null,
		};


	    exit.expectSystemExitWithStatus(-1);
		underTest.checkNotNullInputMetrics(qualityInputMetrics);
	
	}
	
	@Test
	public final void testExpertSystemQuality() throws Exception {
	
		
		QualityMeasureStore storerMock = mock(QualityMeasureStore.class); 
		PowerMockito.whenNew(QualityMeasureStore.class).withNoArguments().thenReturn(storerMock);
		
		Calidad_1 expertSystemMock = mock(Calidad_1.class);
		PowerMockito.whenNew(Calidad_1.class).withNoArguments().thenReturn(expertSystemMock);
		
		ExpertSystemActions expertSystemActions = mock(ExpertSystemActions.class);
		PowerMockito.whenNew(ExpertSystemActions.class).withArguments(context).thenReturn(expertSystemActions);
		
		
		
		ExpertSystemQuality underTest2 = new ExpertSystemQuality(context);

		PowerMockito.verifyNew(ExpertSystemActions.class, times(1)).withArguments(context);
		PowerMockito.verifyNew(QualityMeasureStore.class, times(2)).withNoArguments();
		PowerMockito.verifyNew(Calidad_1.class, times(1)).withNoArguments();


		
		
	}
		
//	@Test
	public final void testXfuzzyProcess() throws Exception {
		
		//for the proper flow execution
		context.addMeasure(SedcatMetricsKeys.SUCCESS_UNIT_TESTS_KEY, 87.75);
		context.addMeasure(SedcatMetricsKeys.COVERAGE_UNIT_TESTS_KEY, 43.25);
		context.addMeasure(SedcatMetricsKeys.MUTANTS_KEY, 67.214);
		context.addMeasure(SedcatMetricsKeys.NUMBERTESTS_KEY, 3245);
		context.addMeasure(SedcatMetricsKeys.CODE_LINES_KEY, 50000);
		
		//test data
		double[] qualityInputMetrics = new double[] { 100, 100, 100, 100, 100, };
		double[] qualityOutputMetrics = new double[] { 100, 100, 100, 100, 100, };
		
		//mocks: creation and configuration
		QualityMeasureStore storerMock = mock(QualityMeasureStore.class);
		PowerMockito.whenNew(QualityMeasureStore.class).withNoArguments().thenReturn(storerMock);

		Calidad_1 expertSystemMock = mock(Calidad_1.class);
		PowerMockito.whenNew(Calidad_1.class).withNoArguments().thenReturn(expertSystemMock);

		PowerMockito.when(underTest, "extractValues").thenReturn(qualityInputMetrics);
		PowerMockito.when(expertSystemMock.crispInference(qualityInputMetrics)).thenReturn(qualityOutputMetrics);

		//execution test method
		underTest.xfuzzyProcess();

		//verificate behaviour
		PowerMockito.verifyPrivate(underTest).invoke("extractValues");
		Mockito.verify(expertSystemMock, times(1)).crispInference(qualityInputMetrics);
		Mockito.verify(storerMock, times(1)).outputMeasureStore(qualityOutputMetrics, context);

		
	}
	
	
}
