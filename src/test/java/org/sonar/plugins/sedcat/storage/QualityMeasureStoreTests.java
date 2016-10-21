package org.sonar.plugins.sedcat.storage;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.sonar.api.ce.measure.MeasureComputer.MeasureComputerDefinition;
import org.sonar.api.ce.measure.test.TestComponent;
import org.sonar.api.ce.measure.test.TestMeasureComputerContext;
import org.sonar.api.ce.measure.test.TestMeasureComputerDefinitionContext;
import org.sonar.api.ce.measure.test.TestSettings;
import org.sonar.plugins.sedcat.start.GeneralComputer;
import org.sonar.plugins.sedcat.start.SedcatMetricsKeys;
import org.sonar.plugins.sedcat.storage.AbstractOutputMeasureStore;
import org.sonar.plugins.sedcat.storage.QualityMeasureStore;

/**
 * @author alan.sastre
 * @version 1.0
 */
//@RunWith(PowerMockRunner.class)
//@PrepareForTest({ QualityMeasureStore.class })
public class QualityMeasureStoreTests {

	private QualityMeasureStore underTest;

	// data
	private TestMeasureComputerDefinitionContext defContext;
	private MeasureComputerDefinition def;
	private TestComponent mockedComponent;
	private TestSettings settings;
	private GeneralComputer computerForData;
	private TestMeasureComputerContext context;
	
	private String qualityMessage = "This quality metric is calculated at project level, so no data at the component"
			+ " level that can be displayed.</br>This metric is obtained from sets of rules activated to varying"
			+ " degrees depending on input metrics read about the project in each analysis.";


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
	 * Test method for
	 * {@link org.sonar.plugins.sedcat.storage.ActionsMeasureStore#saveMeasure(double, org.sonar.api.ce.measure.MeasureComputer.MeasureComputerContext)}
	 * .
	 */
	@Test
	public final void testSaveMeasure() {

		double[] result = new double[] { 23.56 };
		underTest.saveMeasure(result, context);

		double quality = context.getMeasure(SedcatMetricsKeys.QUALITY_MEASURE_KEY).getDoubleValue();
		Assert.assertEquals(23.56, quality, 0.0);

	}

	/**
	 * Test method for
	 * {@link org.sonar.plugins.sedcat.storage.ActionsMeasureStore#saveMeasure(double, org.sonar.api.ce.measure.MeasureComputer.MeasureComputerContext)}
	 * .
	 */
	@Test
	public final void testSaveDescriptionMessage() {

		TestMeasureComputerContext contextMocked1 = mock (TestMeasureComputerContext.class);

		double[] result = new double[] { 25 };

		underTest.saveMeasure(result, contextMocked1);
		
		Mockito.verify(contextMocked1, times(1)).addMeasure(SedcatMetricsKeys.QUALITY_MESSAGE_KEY,
				AbstractOutputMeasureStore.MESSAGE_RESOLUTION + "</br>Quality of unit tests is 25 %</br></br>" + qualityMessage + AbstractOutputMeasureStore.MESSAGE_ALERT_HACK);

	}
	
	/**
	 * Test method for
	 * {@link org.sonar.plugins.sedcat.storage.AbstractOutputMeasureStore#outputMeasureStore(double[], org.sonar.api.ce.measure.MeasureComputer.MeasureComputerContext)}
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
	 * {@link org.sonar.plugins.sedcat.storage.AbstractOutputMeasureStore#checkOutputDataSet(double[])}
	 * .
	 */
	@Test
	public final void testCheckOutputDataSetOk() {

		double[] resultado = underTest.checkOutputDataSet(new double[] { 25 });
		Assert.assertTrue(resultado.length == 1);
		Assert.assertTrue(resultado[0] == 25);

	}
	
	

	/**
	 * Test method for
	 * {@link org.sonar.plugins.sedcat.storage.AbstractOutputMeasureStore#checkOutputDataSet(double[])}
	 * .
	 */
	@Test
	public final void testCheckOutputDataSetNotAllowedUnder() {

		double[] resultado = underTest.checkOutputDataSet(new double[] { -0.0001 });
		Assert.assertTrue(resultado.length == 0);

	}

	/**
	 * Test method for
	 * {@link org.sonar.plugins.sedcat.storage.AbstractOutputMeasureStore#checkOutputDataSet(double[])}
	 * .
	 */
	@Test
	public final void testCheckOutputDataSetNotAllowedAbove() {

		double[] resultado = underTest.checkOutputDataSet(new double[] { 100.59 });
		Assert.assertTrue(resultado.length == 0);
	}

	/**
	 * Test method for
	 * {@link org.sonar.plugins.sedcat.storage.AbstractOutputMeasureStore#checkOutputDataSet(double[])}
	 * .
	 */
	@Test
	public final void testCheckOutputDataSetNull() {

		double[] resultado = underTest.checkOutputDataSet(null);
		Assert.assertTrue(resultado.length == 0);


	}

	/**
	 * Test method for
	 * {@link org.sonar.plugins.sedcat.storage.AbstractOutputMeasureStore#checkOutputDataSet(double[])}
	 * .
	 */
	@Test
	public final void testCheckOutputDataSetEmpty() {

		double[] resultado = underTest.checkOutputDataSet(new double[] {});
		Assert.assertTrue(resultado.length == 0);

	}


}
