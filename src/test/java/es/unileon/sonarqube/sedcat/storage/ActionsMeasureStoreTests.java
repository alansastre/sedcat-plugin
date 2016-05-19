package es.unileon.sonarqube.sedcat.storage;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import java.util.Properties;
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
 * @author alan.sastre
 * @version 1.0
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({ ActionsMeasureStore.class })
public class ActionsMeasureStoreTests {

	private ActionsMeasureStore underTest;

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
	@Before
	public void setUp() throws Exception {

		mockedComponent = mock(TestComponent.class);
		settings = new TestSettings();
		defContext = new TestMeasureComputerDefinitionContext();
		computerForData = new GeneralComputer();
		def = computerForData.define(defContext);
		context = new TestMeasureComputerContext(mockedComponent, settings, def);

		underTest = new ActionsMeasureStore();

	}

	/**
	 * Test method for
	 * {@link es.unileon.sonarqube.sedcat.storage.ActionsMeasureStore#saveMeasure(double, org.sonar.api.ce.measure.MeasureComputer.MeasureComputerContext)}
	 * .
	 */
	@Test
	public final void testSaveMeasure() {

		double[] result = new double[] { 23 };
		underTest.saveMeasure(result, context);
		
		Assert.assertEquals("Mutations coverage > Unit Test Success > Unit Test Coverage > Number Of Tests",
				context.getMeasure(SedcatMetricsKeys.ACTIONS_TO_PERFORM_KEY).getStringValue());

	}

	/**
	 * Test method for
	 * {@link es.unileon.sonarqube.sedcat.storage.AbstractOutputMeasureStore#outputMeasureStore(double[], org.sonar.api.ce.measure.MeasureComputer.MeasureComputerContext)}
	 * .
	 */
	@Test
	public final void testOutputMeasureStore() {

		ActionsMeasureStore spy = Mockito.spy(underTest);
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
		underTest.checkOutputDataSet(new double[] { 65.0001 });

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

	/**
	 * Test method for
	 * {@link es.unileon.sonarqube.sedcat.storage.ActionsMeasureStore#loadProperties(String)}
	 * .
	 * 
	 * @throws Exception
	 */
	@Test
	public final void testloadPropertiesOk() throws Exception {

		Properties testProperties = underTest.loadProperties(
				"/root/workspace/sonar-sedcat-plugin/src/main/resources/org/sonar/l10n/expertSystemActions.properties");

		Assert.assertNotNull(testProperties);
		Assert.assertFalse(testProperties.isEmpty());
		Assert.assertEquals(66, testProperties.size());

	}

	/**
	 * Test method for
	 * {@link es.unileon.sonarqube.sedcat.storage.ActionsMeasureStore#loadProperties(String)}
	 * .
	 * 
	 * @throws Exception
	 */
	@Test
	public final void testloadPropertiesNull() throws Exception {

		Properties testProperties = underTest
				.loadProperties("/root/workspace/sonar-sedcat-plugin/src/main/resources/org/sonar/l10n/expertSy");
		Assert.assertTrue(testProperties.isEmpty());

	}
	
	
	/**
	 * Test method for
	 * {@link es.unileon.sonarqube.sedcat.storage.ActionsMeasureStore#saveMeasure(double, org.sonar.api.ce.measure.MeasureComputer.MeasureComputerContext)}
	 * .
	 */
	@Test
	public final void testSaveMeasureNull() {
		
		TestMeasureComputerContext contextMocked1 = mock (TestMeasureComputerContext.class);

		double[] result = new double[] { 89 };

		underTest.saveMeasure(result, contextMocked1);
		// verificate behaviour
		Mockito.verify(contextMocked1, times(1)).addMeasure(SedcatMetricsKeys.ACTIONS_TO_PERFORM_KEY, "No se han encontrado posibles soluciones.");
		Mockito.verify(contextMocked1, times(1)).addMeasure(SedcatMetricsKeys.ACTION_MESSAGE_KEY, "No se han encontrado posibles soluciones.");

		
	}

	
	
	/**
	 * Test method for
	 * {@link es.unileon.sonarqube.sedcat.storage.ActionsMeasureStore#saveMeasure(double, org.sonar.api.ce.measure.MeasureComputer.MeasureComputerContext)}
	 * .
	 */
	@Test
	public final void testSaveMeasureMessageSet() {
		
		
		TestMeasureComputerContext contextMocked1 = mock (TestMeasureComputerContext.class);

		double[] result = new double[] { 25 };

		underTest.saveMeasure(result, contextMocked1);
		
		Mockito.verify(contextMocked1, times(1)).addMeasure(SedcatMetricsKeys.ACTION_MESSAGE_KEY, ActionsMessageConstants.MESSAGE_SET25 + AbstractOutputMeasureStore.MESSAGE_ALERT_HACK);
	}
}
