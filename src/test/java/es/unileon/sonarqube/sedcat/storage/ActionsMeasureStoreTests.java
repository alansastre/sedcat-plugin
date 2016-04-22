/**
 * 
 */
package es.unileon.sonarqube.sedcat.storage;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.powermock.api.mockito.PowerMockito;
import org.sonar.api.ce.measure.Component.Type;
import org.sonar.api.ce.measure.MeasureComputer.MeasureComputerDefinition;
import org.sonar.api.ce.measure.test.TestComponent;
import org.sonar.api.ce.measure.test.TestMeasureComputerContext;
import org.sonar.api.ce.measure.test.TestMeasureComputerDefinitionContext;
import org.sonar.api.ce.measure.test.TestSettings;

import es.unileon.sonarqube.sedcat.start.GeneralComputer;
import es.unileon.sonarqube.sedcat.start.SedcatMetricsKeys;
import es.unileon.sonarqube.sedcat.strategies.ExpertSystemActions;

/**
 *	@author alan.sastre
 *	@version 1.0
 */
public class ActionsMeasureStoreTests {

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
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link es.unileon.sonarqube.sedcat.storage.ActionsMeasureStore#saveMeasure(double, org.sonar.api.ce.measure.MeasureComputer.MeasureComputerContext)}.
	 */
	@Test
	public final void testSaveMeasure() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link es.unileon.sonarqube.sedcat.storage.ActionsMeasureStore#ActionsMeasureStore()}.
	 */
	@Test
	public final void testActionsMeasureStore() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link es.unileon.sonarqube.sedcat.storage.AbstractOutputMeasureStore#outputMeasureStore(double[], org.sonar.api.ce.measure.MeasureComputer.MeasureComputerContext)}.
	 */
	@Test
	public final void testOutputMeasureStore() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link es.unileon.sonarqube.sedcat.storage.AbstractOutputMeasureStore#checkValue(double)}.
	 */
	@Test
	public final void testCheckValue() {
		fail("Not yet implemented"); // TODO
	}
	
	
	/**
	 * Test method for {@link es.unileon.sonarqube.sedcat.storage.AbstractOutputMeasureStore#checkValue(double)}.
	 * @throws Exception 
	 */
//	@Test
	public final void loadPropertiesOk() throws Exception {

		ActionsMeasureStore underTest = new ActionsMeasureStore();
		
	     TestComponent mockedComponent = mock(TestComponent.class);
	     TestSettings settings = new TestSettings();
	     TestMeasureComputerDefinitionContext defContext = new TestMeasureComputerDefinitionContext();
	     GeneralComputer computerForData = new GeneralComputer();
	     MeasureComputerDefinition def = computerForData.define(defContext);
	     TestMeasureComputerContext context = new TestMeasureComputerContext(mockedComponent, settings, def);
	     
	     underTest.saveMeasure(100.0, context);
	     
	     PowerMockito.verifyPrivate(ActionsMeasureStore.class).invoke("loadProperties");
	     
	     context.getMeasure(SedcatMetricsKeys.ACTIONS_TO_PERFORM_KEY);
	     
//		PowerMockito.whenNew(ExpertSystemActions.class).withArguments(context).thenReturn(expertSystemActions);
	}
	

}
