/**
 * 
 */
package es.unileon.sonarqube.sedcat.start;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.sonar.api.ce.measure.MeasureComputer.MeasureComputerDefinition;
import org.sonar.api.ce.measure.test.TestMeasureComputerContext;
import org.sonar.api.ce.measure.test.TestMeasureComputerDefinitionContext;
import java.util.Set;
import org.sonar.api.ce.measure.Component.FileAttributes;
import org.sonar.api.ce.measure.Component.Type;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.*;
import org.sonar.api.ce.measure.test.*;

/**
 *	@author alan.sastre
 *	@version 1.0
 *
 *Tests with:
 *https://github.com/SonarSource/sonarqube/blob/master/sonar-plugin-api/src/main/java/org/sonar/api/ce/measure/test/TestMeasureComputerContext.java
 */
public class GeneralComputerTests{

	GeneralComputer underTest = new GeneralComputer();
	 
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

	/*
	 * Test for state with stubs
	 */

	/**
	 * Test method for {@link es.unileon.sonarqube.sedcat.start.GeneralComputer#define()}.
	 */
	@Test
	public final void define_Correct_State() {

	     TestMeasureComputerDefinitionContext defContext = new TestMeasureComputerDefinitionContext();
	     MeasureComputerDefinition def = underTest.define(defContext);
	     Assert.assertNotNull(def);
	     
	     //Probar metricas de entrada
	     Set<String> inputMetrics = def.getInputMetrics();
	     Assert.assertEquals(inputMetrics.size(), 5);
	     
	     Assert.assertTrue(inputMetrics.contains(SedcatMetricsKeys.SUCCESS_UNIT_TESTS_KEY));
	     Assert.assertTrue(inputMetrics.contains(SedcatMetricsKeys.COVERAGE_UNIT_TESTS_KEY));
	     Assert.assertTrue(inputMetrics.contains(SedcatMetricsKeys.NUMBERTESTS_KEY));
	     Assert.assertTrue(inputMetrics.contains(SedcatMetricsKeys.CODE_LINES_KEY));
	     Assert.assertTrue(inputMetrics.contains(SedcatMetricsKeys.MUTANTS_KEY));

	     //Probar metricas de salida
	     Set<String> ouputMetrics = def.getOutputMetrics();
	     Assert.assertEquals(ouputMetrics.size(), 7);
	     
	     Assert.assertTrue(ouputMetrics.contains(SedcatMetricsKeys.QUALITY_MEASURE_KEY));
	     Assert.assertTrue(ouputMetrics.contains(SedcatMetricsKeys.ACTIONS_TO_PERFORM_KEY));
	     Assert.assertTrue(ouputMetrics.contains(SedcatMetricsKeys.SUCCESS_UNIT_TESTS_KEY));
	     Assert.assertTrue(ouputMetrics.contains(SedcatMetricsKeys.COVERAGE_UNIT_TESTS_KEY));
	     Assert.assertTrue(ouputMetrics.contains(SedcatMetricsKeys.NUMBERTESTS_KEY));
	     Assert.assertTrue(ouputMetrics.contains(SedcatMetricsKeys.CODE_LINES_KEY));
	     Assert.assertTrue(ouputMetrics.contains(SedcatMetricsKeys.MUTANTS_KEY));


	}
	
	
	/*
	 * Test for any Type of Component:
	 *  PROJECT, MODULE, DIRECTORY, FILE, VIEW, SUBVIEW
	 *  
	 */
	/**
	 * Test method for {@link es.unileon.sonarqube.sedcat.start.GeneralComputer#compute()}.
	 */
	@Test
	public final void compute_File_noExecution() {

	     TestMeasureComputerDefinitionContext defContext = new TestMeasureComputerDefinitionContext();
	     MeasureComputerDefinition def = underTest.define(defContext);
	     
	     FileAttributes mockedFileAttributes = mock(FileAttributes.class);
	     TestComponent component = new TestComponent("",Type.FILE, mockedFileAttributes );
	     TestSettings settings = new TestSettings();
	     
	     TestMeasureComputerContext context = new TestMeasureComputerContext(component, settings, def);
	     underTest.compute(context);

	     Assert.assertFalse(underTest.isProject());
	}
	
	/**
	 * Test method for {@link es.unileon.sonarqube.sedcat.start.GeneralComputer#compute()}.
	 */
	@Test
	public final void compute_View_noExecution() {

		fail("Not yet implemented");
		//TODO
	}
	
	/**
	 * Test method for {@link es.unileon.sonarqube.sedcat.start.GeneralComputer#compute()}.
	 */
	@Test
	public final void compute_Subview_noExecution() {

		fail("Not yet implemented");
		//TODO
	}
	
	/**
	 * Test method for {@link es.unileon.sonarqube.sedcat.start.GeneralComputer#compute()}.
	 */
	@Test
	public final void compute_Directory_noExecution() {

		fail("Not yet implemented");
		//TODO
	}
	
	/**
	 * Test method for {@link es.unileon.sonarqube.sedcat.start.GeneralComputer#compute()}.
	 */
	@Test
	public final void compute_Module_noExecution() {

		fail("Not yet implemented");
		//TODO
	}
	
	/**
	 * Test method for {@link es.unileon.sonarqube.sedcat.start.GeneralComputer#compute()}.
	 */
	@Test
	public final void compute_Project_Execution() {


	     TestMeasureComputerDefinitionContext defContext = new TestMeasureComputerDefinitionContext();
	     MeasureComputerDefinition def = underTest.define(defContext);


	     TestComponent mockedComponent = mock(TestComponent.class);
	     TestSettings settings = new TestSettings();
	     
	     TestMeasureComputerContext context = new TestMeasureComputerContext(mockedComponent, settings, def);

	     when(mockedComponent.getType()).thenReturn(Type.PROJECT);

	     //añadir valores a las metricas de entrada para el test: 
	     context.addMeasure(SedcatMetricsKeys.SUCCESS_UNIT_TESTS_KEY, 100.0);
	     context.addMeasure(SedcatMetricsKeys.COVERAGE_UNIT_TESTS_KEY, 100.0);
	     context.addMeasure(SedcatMetricsKeys.NUMBERTESTS_KEY, 100);
	     context.addMeasure(SedcatMetricsKeys.CODE_LINES_KEY, 100);
	     context.addMeasure(SedcatMetricsKeys.MUTANTS_KEY, 100.0);
	     
	     underTest.compute(context);

	     Assert.assertTrue(underTest.isProject());


	     //Comprobamos la integridad de las metricas de entrada
	     Assert.assertEquals(context.getMeasure(SedcatMetricsKeys.SUCCESS_UNIT_TESTS_KEY).getDoubleValue(), 100.0, 0.00);
	     Assert.assertEquals(context.getMeasure(SedcatMetricsKeys.COVERAGE_UNIT_TESTS_KEY).getDoubleValue(), 100.0, 0.00);
	     Assert.assertEquals(context.getMeasure(SedcatMetricsKeys.NUMBERTESTS_KEY).getIntValue(), 100, 0.00);
	     Assert.assertEquals(context.getMeasure(SedcatMetricsKeys.CODE_LINES_KEY).getIntValue(), 100, 0.00);
	     Assert.assertEquals(context.getMeasure(SedcatMetricsKeys.MUTANTS_KEY).getDoubleValue(), 100.0, 0.00);
	     
	     //Comprobamos metricas de salida
	     Assert.assertEquals(context.getMeasure(SedcatMetricsKeys.QUALITY_MEASURE_KEY).getDoubleValue(),  73.55144775847887, 0.00);
	     Assert.assertEquals(context.getMeasure(SedcatMetricsKeys.ACTIONS_TO_PERFORM_KEY).getStringValue(),
	    		 "Improve the following parameters in order: Number Of Tests",
	    		 "Improve the following parameters in order: Number Of Tests");
 
	}
	
	
/*
 * Tests for behaviour with mocks
 */

}
