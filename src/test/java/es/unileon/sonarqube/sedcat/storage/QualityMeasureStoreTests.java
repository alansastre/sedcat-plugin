/**
 * 
 */
package es.unileon.sonarqube.sedcat.storage;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.ExpectedSystemExit;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.sonar.api.ce.measure.MeasureComputer.MeasureComputerDefinition;
import org.sonar.api.ce.measure.test.TestComponent;
import org.sonar.api.ce.measure.test.TestMeasureComputerContext;
import org.sonar.api.ce.measure.test.TestMeasureComputerDefinitionContext;
import org.sonar.api.ce.measure.test.TestSettings;
import es.unileon.sonarqube.sedcat.start.GeneralComputer;
import es.unileon.sonarqube.sedcat.start.SedcatMetricsKeys;

/**
 *	@author alan.sastre
 *	@version 1.0
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({ QualityMeasureStore.class })
public class QualityMeasureStoreTests {

	
	private QualityMeasureStore underTest;

	// data
	private TestMeasureComputerDefinitionContext defContext;
	private MeasureComputerDefinition def;
	private TestComponent mockedComponent;
	private TestSettings settings;
	private GeneralComputer computerForData;
	private TestMeasureComputerContext context;
	
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
		
		mockedComponent = mock(TestComponent.class);
		settings = new TestSettings();
		defContext = new TestMeasureComputerDefinitionContext();
		computerForData = new GeneralComputer();
		def = computerForData.define(defContext);
		context = new TestMeasureComputerContext(mockedComponent, settings, def);

		underTest = new QualityMeasureStore();
		
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for
	 * {@link es.unileon.sonarqube.sedcat.storage.ActionsMeasureStore#saveMeasure(double, org.sonar.api.ce.measure.MeasureComputer.MeasureComputerContext)}
	 * .
	 */
	@Test
	public final void testSaveMeasure() {

		double[] result = new double[] { 23.56 };
		underTest.saveMeasure(result, context);

		double quality = context.getMeasure(SedcatMetricsKeys.QUALITY_MEASURE_KEY).getDoubleValue();
		Assert.assertEquals(23.56 ,quality, 0.0);

	}

	/**
	 * Test method for
	 * {@link es.unileon.sonarqube.sedcat.storage.AbstractOutputMeasureStore#outputMeasureStore(double[], org.sonar.api.ce.measure.MeasureComputer.MeasureComputerContext)}
	 * .
	 */
	@Test
	public final void testOutputMeasureStore() {

		QualityMeasureStore spy = Mockito.spy(underTest);
		double[] result = new double[] { 23 };

		spy.outputMeasureStore(result, context);

		// verificate behaviour
		Mockito.verify(spy, times(1)).checkOutputDataSet(result);
		Mockito.verify(spy, times(1)).saveMeasure(result, context);

	}
	
	
	/**
	 * Test method for
	 * {@link es.unileon.sonarqube.sedcat.storage.AbstractOutputMeasureStore#checkOutputDataSet(double[])}
	 * .
	 */
	@Test
	public final void testCheckOutputDataSetOk() {

		ActionsMeasureStore underTest = new ActionsMeasureStore();
		underTest.checkOutputDataSet(new double[] { 25 });

	}

	/**
	 * Test method for
	 * {@link es.unileon.sonarqube.sedcat.storage.AbstractOutputMeasureStore#checkOutputDataSet(double[])}
	 * .
	 */
	@Test
	public final void testCheckOutputDataSetNotAllowedUnder() {

		exit.expectSystemExitWithStatus(-1);
		underTest.checkOutputDataSet(new double[] { -0.0001 });

	}

	/**
	 * Test method for
	 * {@link es.unileon.sonarqube.sedcat.storage.AbstractOutputMeasureStore#checkOutputDataSet(double[])}
	 * .
	 */
	@Test
	public final void testCheckOutputDataSetNotAllowedAbove() {

		exit.expectSystemExitWithStatus(-1);
		underTest.checkOutputDataSet(new double[] { 100.0001 });

	}

	/**
	 * Test method for
	 * {@link es.unileon.sonarqube.sedcat.storage.AbstractOutputMeasureStore#checkOutputDataSet(double[])}
	 * .
	 */
	@Test
	public final void testCheckOutputDataSetNull() {

		exit.expectSystemExitWithStatus(-1);
		underTest.checkOutputDataSet(null);

	}

	/**
	 * Test method for
	 * {@link es.unileon.sonarqube.sedcat.storage.AbstractOutputMeasureStore#checkOutputDataSet(double[])}
	 * .
	 */
	@Test
	public final void testCheckOutputDataSetEmpty() {

		exit.expectSystemExitWithStatus(-1);
		underTest.checkOutputDataSet(new double[] {});

	}


}
