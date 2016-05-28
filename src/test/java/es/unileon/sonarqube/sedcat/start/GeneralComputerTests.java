package es.unileon.sonarqube.sedcat.start;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.sonar.api.ce.measure.MeasureComputer.MeasureComputerDefinition;
import org.sonar.api.ce.measure.test.TestMeasureComputerContext;
import org.sonar.api.ce.measure.test.TestMeasureComputerDefinitionContext;
import org.sonar.api.measures.CoreMetrics;
import es.unileon.sonarqube.sedcat.strategies.ExpertSystemActions;
import es.unileon.sonarqube.sedcat.strategies.ExpertSystemQuality;
import java.util.Set;
import org.sonar.api.ce.measure.Component.Type;
import org.sonar.api.ce.measure.Measure;
import static org.mockito.Mockito.*;
import org.sonar.api.ce.measure.test.*;
import org.powermock.api.mockito.*;

/**
 * @author alan.sastre
 * @version 1.0
 * @see:
 * Tests with:
 * https://github.com/SonarSource/sonarqube/blob/master/sonar-plugin-
 * api/src/main/java/org/sonar/api/ce/measure/test/
 * TestMeasureComputerContext.java
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({ GeneralComputer.class, ExpertSystemQuality.class, ExpertSystemActions.class })
public class GeneralComputerTests {

	private GeneralComputer underTest = new GeneralComputer();
	private TestMeasureComputerDefinitionContext defContext;
	private MeasureComputerDefinition def;
	private TestComponent mockedComponent;
	private TestSettings settingsMocked;
	private String TRUE_EXECUTION = "true";
	private String FALSE_EXECUTION = "false";


	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {

		defContext = new TestMeasureComputerDefinitionContext();
		def = underTest.define(defContext);
		mockedComponent = mock(TestComponent.class);
		settingsMocked = mock(TestSettings.class);
	}

	/*
	 * Test for state with stubs
	 */

	/**
	 * Test method for
	 * {@link es.unileon.sonarqube.sedcat.start.GeneralComputer#define()}.
	 */

	@Test
	public final void testdefine_Correct_State() {

		Assert.assertNotNull(def);

		// Probar metricas de entrada
		Set<String> inputMetrics = def.getInputMetrics();
		Assert.assertEquals(6, inputMetrics.size());
		
		Assert.assertTrue(inputMetrics.contains(CoreMetrics.TEST_SUCCESS_DENSITY_KEY));
		Assert.assertTrue(inputMetrics.contains(CoreMetrics.COVERAGE_KEY));
		Assert.assertTrue(inputMetrics.contains(CoreMetrics.TESTS_KEY));
		Assert.assertTrue(inputMetrics.contains(CoreMetrics.NCLOC_KEY));
		Assert.assertTrue(inputMetrics.contains(SedcatMetricsKeys.MUTANTS_KEY));
		Assert.assertTrue(inputMetrics.contains(SedcatMetricsKeys.COMPLEXITY_CLASS_KEY));
		
		// Probar metricas de salida
		Set<String> ouputMetrics = def.getOutputMetrics();
		Assert.assertEquals(4, ouputMetrics.size());

		Assert.assertTrue(ouputMetrics.contains(SedcatMetricsKeys.QUALITY_MEASURE_KEY));
		Assert.assertTrue(ouputMetrics.contains(SedcatMetricsKeys.QUALITY_MESSAGE_KEY));
		Assert.assertTrue(ouputMetrics.contains(SedcatMetricsKeys.ACTIONS_TO_PERFORM_KEY));
		Assert.assertTrue(ouputMetrics.contains(SedcatMetricsKeys.ACTION_MESSAGE_KEY));

	}

	/*
	 * Test for any Type of Component: PROJECT, MODULE, DIRECTORY, FILE, VIEW,
	 * SUBVIEW
	 * 
	 */
	/**
	 * Test method for
	 * {@link es.unileon.sonarqube.sedcat.start.GeneralComputer#compute()}.
	 */
	@Test
	public final void testcompute_File_noExecution() {

		when(mockedComponent.getType()).thenReturn(Type.FILE);
		when(settingsMocked.getString(SedcatConstants.ACTIVE_MODE_KEY)).thenReturn(TRUE_EXECUTION);
		
		TestMeasureComputerContext context = new TestMeasureComputerContext(mockedComponent, settingsMocked, def);
		underTest.compute(context);

		Assert.assertFalse(underTest.isProject());
	}

	
	/**
	 * Test method for
	 * {@link es.unileon.sonarqube.sedcat.start.GeneralComputer#compute()}.
	 */
	@Test
	public final void testcompute_View_noExecution() {

		when(mockedComponent.getType()).thenReturn(Type.VIEW);
		when(settingsMocked.getString(SedcatConstants.ACTIVE_MODE_KEY)).thenReturn(TRUE_EXECUTION);
		
		TestMeasureComputerContext context = new TestMeasureComputerContext(mockedComponent, settingsMocked, def);
		underTest.compute(context);

		Assert.assertFalse(underTest.isProject());
	}

	/**
	 * Test method for
	 * {@link es.unileon.sonarqube.sedcat.start.GeneralComputer#compute()}.
	 */
	@Test
	public final void testcompute_Subview_noExecution() {

		when(mockedComponent.getType()).thenReturn(Type.SUBVIEW);
		when(settingsMocked.getString(SedcatConstants.ACTIVE_MODE_KEY)).thenReturn(TRUE_EXECUTION);

		TestMeasureComputerContext context = new TestMeasureComputerContext(mockedComponent, settingsMocked, def);
		underTest.compute(context);

		Assert.assertFalse(underTest.isProject());
	}

	/**
	 * Test method for
	 * {@link es.unileon.sonarqube.sedcat.start.GeneralComputer#compute()}.
	 */
	@Test
	public final void testcompute_Directory_noExecution() {

		when(mockedComponent.getType()).thenReturn(Type.DIRECTORY);
		when(settingsMocked.getString(SedcatConstants.ACTIVE_MODE_KEY)).thenReturn(TRUE_EXECUTION);
		
		TestMeasureComputerContext context = new TestMeasureComputerContext(mockedComponent, settingsMocked, def);
		underTest.compute(context);

		Assert.assertFalse(underTest.isProject());
	}

	/**
	 * Test method for
	 * {@link es.unileon.sonarqube.sedcat.start.GeneralComputer#compute()}.
	 */
	@Test
	public final void testcompute_Module_noExecution() {

		when(mockedComponent.getType()).thenReturn(Type.MODULE);
		when(settingsMocked.getString(SedcatConstants.ACTIVE_MODE_KEY)).thenReturn(TRUE_EXECUTION);
		
		TestMeasureComputerContext context = new TestMeasureComputerContext(mockedComponent, settingsMocked, def);
		underTest.compute(context);

		Assert.assertFalse(underTest.isProject());
	}
	
	/**
	 * Test method for
	 * {@link es.unileon.sonarqube.sedcat.start.GeneralComputer#compute()}.
	 */
	@Test
	public final void testcompute_ModeSedcatDeactivated() {

		when(mockedComponent.getType()).thenReturn(Type.PROJECT);
		when(settingsMocked.getString(SedcatConstants.ACTIVE_MODE_KEY)).thenReturn(FALSE_EXECUTION);
		
		TestMeasureComputerContext context = new TestMeasureComputerContext(mockedComponent, settingsMocked, def);
		underTest.compute(context);

		Assert.assertFalse(underTest.isProject());
	}
	
	/*
	 * Tests for behaviour with mocks
	 */

	/**
	 * Test method for
	 * {@link es.unileon.sonarqube.sedcat.start.GeneralComputer#compute()}.
	 * 
	 * Prueba que GeneralComputer hace las llamadas a los sistemas expertos 
	 * @throws Exception
	 */

	@Test
	public final void testcompute_Project_Behaviour() throws Exception {

		when(mockedComponent.getType()).thenReturn(Type.PROJECT);
		when(settingsMocked.getString(SedcatConstants.ACTIVE_MODE_KEY)).thenReturn(TRUE_EXECUTION);
		
		TestMeasureComputerContext context = new TestMeasureComputerContext(mockedComponent, settingsMocked, def);

		ExpertSystemQuality expertSystemQuality = mock(ExpertSystemQuality.class);
		PowerMockito.whenNew(ExpertSystemQuality.class).withArguments(context).thenReturn(expertSystemQuality);

		ExpertSystemActions expertSystemActions = mock(ExpertSystemActions.class);
		PowerMockito.whenNew(ExpertSystemActions.class).withArguments(context).thenReturn(expertSystemActions);

		underTest.compute(context);

		Assert.assertTrue(underTest.isProject());

		PowerMockito.verifyNew(ExpertSystemQuality.class, times(1)).withArguments(context);
		PowerMockito.verifyNew(ExpertSystemActions.class, times(1)).withArguments(context);

		Mockito.verify(expertSystemQuality, times(1)).xfuzzyProcess();
		Mockito.verify(expertSystemActions, times(1)).xfuzzyProcess();
		

	}
	
	
	/**
	 * Test method for
	 * {@link es.unileon.sonarqube.sedcat.start.GeneralComputer#compute()}.
	 * 
	 * Prueba las llamadas a los sistemas expertos y a mayores las veces que se ha operado 
	 * con el contexto en el flujo de ejecución total, además de las métricas almacenadas con el mismo
	 */

	@Test
	public final void testcompute_Project_TotalBehaviour() {

		//mocks creation
		TestMeasureComputerContext contextMocked = mock(TestMeasureComputerContext.class);
		Measure measureMocked = mock(Measure.class);
		
		//mocks setup
		
		//emular nivel de proyecto
		when(mockedComponent.getType()).thenReturn(Type.PROJECT);
		when(contextMocked.getComponent()).thenReturn(mockedComponent);
		
		//habilitar ejecucion sedcat
		when(contextMocked.getSettings()).thenReturn(settingsMocked);
		when(settingsMocked.getString(SedcatConstants.ACTIVE_MODE_KEY)).thenReturn(TRUE_EXECUTION);
		
		when(contextMocked.getMeasure(CoreMetrics.TEST_SUCCESS_DENSITY_KEY)).thenReturn(measureMocked);
		when(contextMocked.getMeasure(CoreMetrics.COVERAGE_KEY)).thenReturn(measureMocked);
		when(contextMocked.getMeasure(SedcatMetricsKeys.MUTANTS_KEY)).thenReturn(measureMocked);
		when(contextMocked.getMeasure(CoreMetrics.TESTS_KEY)).thenReturn(measureMocked);
		when(contextMocked.getMeasure(CoreMetrics.NCLOC_KEY)).thenReturn(measureMocked);
		when(contextMocked.getMeasure(SedcatMetricsKeys.COMPLEXITY_CLASS_KEY)).thenReturn(measureMocked);
		
		when(measureMocked.getDoubleValue()).thenReturn(50.0);
		when(measureMocked.getIntValue()).thenReturn(100);

		//execute 
		underTest.compute(contextMocked);

		Assert.assertTrue(underTest.isProject());

		//verify methods executions
		verify(mockedComponent, times(1)).getType();
		verify(contextMocked, times(1)).getComponent();
		
		verify(measureMocked, times(8)).getDoubleValue();
		verify(measureMocked, times(4)).getIntValue();

		verify(contextMocked, times(1)).addMeasure(SedcatMetricsKeys.ACTIONS_TO_PERFORM_KEY, "1. Complexity</br>2. Unit Test Success</br>3. Unit Test Coverage"
				+ "</br>4. Mutations coverage</br>5. Number Of Tests");
		verify(contextMocked, times(1)).addMeasure(SedcatMetricsKeys.QUALITY_MEASURE_KEY, 5.5);
	}
	
}
