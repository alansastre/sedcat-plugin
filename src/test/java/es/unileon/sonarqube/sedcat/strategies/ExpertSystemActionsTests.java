package es.unileon.sonarqube.sedcat.strategies;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.sonar.api.ce.measure.Measure;
import org.sonar.api.ce.measure.test.TestComponent;
import org.sonar.api.ce.measure.test.TestMeasureComputerContext;
import org.sonar.api.measures.CoreMetrics;
import es.unileon.sonarqube.sedcat.start.SedcatMetricsKeys;
import es.unileon.sonarqube.sedcat.storage.ActionsMeasureStore;
import es.unileon.sonarqube.sedcat.xfuzzy.actions.Acciones;
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
@PrepareForTest({ ActionsMeasureStore.class, ExpertSystemActions.class, TestMeasureComputerContext.class
})
public class ExpertSystemActionsTests {

	private ExpertSystemActions underTest;
	private TestMeasureComputerContext context;
	private TestComponent mockedComponent;
	private TestMeasureComputerContext contextMocked;
	private Measure measureMocked;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {

		contextMocked = mock(TestMeasureComputerContext.class);
		measureMocked = mock(Measure.class);

		// mocks setup
		when(contextMocked.getComponent()).thenReturn(mockedComponent);
		
		when(contextMocked.getMeasure(CoreMetrics.TEST_SUCCESS_DENSITY_KEY)).thenReturn(measureMocked);
		when(contextMocked.getMeasure(CoreMetrics.COVERAGE_KEY)).thenReturn(measureMocked);
		when(contextMocked.getMeasure(SedcatMetricsKeys.MUTANTS_KEY)).thenReturn(measureMocked);
		when(contextMocked.getMeasure(CoreMetrics.TESTS_KEY)).thenReturn(measureMocked);
		when(contextMocked.getMeasure(CoreMetrics.NCLOC_KEY)).thenReturn(measureMocked);
		when(contextMocked.getMeasure(SedcatMetricsKeys.COMPLEXITY_CLASS_KEY)).thenReturn(measureMocked);

		when(measureMocked.getDoubleValue()).thenReturn(59.0);
		when(measureMocked.getIntValue()).thenReturn(100);

		underTest = new ExpertSystemActions(contextMocked);
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
		verify(measureMocked, times(4)).getDoubleValue();
		verify(measureMocked, times(2)).getIntValue();

	}
	
	/**
	 * Test method for
	 * {@link es.unileon.sonarqube.sedcat.strategies.ExpertSystemActions#extractValues()}
	 * .
	 */
	@Test
	public final void testExtractValuesGetCheckMeasure() {

		ExpertSystemActions spy = Mockito.spy(underTest);
		// execute
		spy.extractValues();

		// verify methods executions
		verify(spy, times(1)).getMeasureDoubleChecked(CoreMetrics.TEST_SUCCESS_DENSITY_KEY);
		verify(spy, times(1)).getMeasureDoubleChecked(CoreMetrics.COVERAGE_KEY);
		verify(spy, times(1)).getMeasureDoubleChecked(SedcatMetricsKeys.COMPLEXITY_CLASS_KEY);
		verify(spy, times(1)).getMeasureDoubleChecked(SedcatMetricsKeys.MUTANTS_KEY);

		verify(spy, times(1)).getMeasureIntChecked(CoreMetrics.TESTS_KEY);
		verify(spy, times(1)).getMeasureIntChecked(CoreMetrics.NCLOC_KEY);
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
		Acciones actionsMock = mock(Acciones.class);
		
		PowerMockito.whenNew(ActionsMeasureStore.class).withNoArguments().thenReturn(storerMock);
		PowerMockito.whenNew(Acciones.class).withNoArguments().thenReturn(actionsMock);
		
		ExpertSystemActions underTest2 = new ExpertSystemActions(context);

		PowerMockito.verifyNew(ActionsMeasureStore.class, times(1)).withNoArguments();
		PowerMockito.verifyNew(Acciones.class, times(1)).withNoArguments();

		Assert.assertEquals(context, underTest2.context);
	}

	
	/**
	 * Test method for
	 * {@link es.unileon.sonarqube.sedcat.strategies.AbstractInferenceProcess#xfuzzyProcess()}
	 * .
	 */

	@Test
	public final void testXfuzzyProcessBehaviour() throws Exception {

		double[] actionsInputMetrics = new double[] { 59.0, 59.0, 100.0, 59.0, 100.0, 59.0 };
		double[] actionsOutputMetrics = new double[] { 1 };

		// mocks: creation and configuration
		ActionsMeasureStore storerMock = mock(ActionsMeasureStore.class);
		PowerMockito.whenNew(ActionsMeasureStore.class).withNoArguments().thenReturn(storerMock);

		Acciones expertSystemMock = mock(Acciones.class);
		PowerMockito.whenNew(Acciones.class).withNoArguments().thenReturn(expertSystemMock);

		PowerMockito.when(expertSystemMock.crispInference(actionsInputMetrics)).thenReturn(actionsOutputMetrics);

		// partial mocking with spies
		ExpertSystemActions underTest2 = new ExpertSystemActions(contextMocked);
		ExpertSystemActions spy = Mockito.spy(underTest2);

		Mockito.doReturn(actionsInputMetrics).when(spy).extractValues();
		// real method
		spy.xfuzzyProcess();

		// verificate behaviour
		Mockito.verify(spy, times(1)).extractValues();
		Mockito.verify(expertSystemMock, times(1)).crispInference(actionsInputMetrics);
		Mockito.verify(storerMock, times(1)).outputMeasureStore(actionsOutputMetrics, contextMocked);


	}

}
