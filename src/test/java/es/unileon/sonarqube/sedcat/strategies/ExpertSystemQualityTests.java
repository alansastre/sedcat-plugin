package es.unileon.sonarqube.sedcat.strategies;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.ExpectedSystemExit;
import org.sonar.api.ce.measure.Measure;
import org.sonar.api.ce.measure.MeasureComputer.MeasureComputerDefinition;
import org.sonar.api.ce.measure.test.TestComponent;
import org.sonar.api.ce.measure.test.TestMeasureComputerContext;
import org.sonar.api.ce.measure.test.TestMeasureComputerDefinitionContext;
import org.sonar.api.ce.measure.test.TestSettings;

import es.unileon.sonarqube.sedcat.start.GeneralComputer;
import es.unileon.sonarqube.sedcat.start.SedcatMetricsKeys;
import org.junit.Assert;

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

	     double[] result = underTest.extractValues(context);
	     
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
	     
		Measure[] qualityInputMetrics = new Measure[]{
				
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
	
	
//	@Test
	public final void testXfuzzyProcess() {
		fail("Not yet implemented"); // TODO
	}
	
	
}
