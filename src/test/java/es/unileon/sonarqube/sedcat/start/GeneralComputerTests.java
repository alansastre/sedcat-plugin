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
import static org.mockito.Mockito.*;
import org.sonar.api.ce.measure.test.*;

/**
 *	@author alan.sastre
 *	@version 1.0
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

	

	/**
	 * Test method for {@link es.unileon.sonarqube.sedcat.start.GeneralComputer#define()}.
	 */
	@Test
	public final void define_correct_behaviour() {

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
	     Assert.assertEquals(ouputMetrics.size(), 2);
	     
	     Assert.assertTrue(ouputMetrics.contains(SedcatMetricsKeys.QUALITY_MEASURE_KEY));
	     Assert.assertTrue(ouputMetrics.contains(SedcatMetricsKeys.ACTIONS_TO_PERFORM_KEY));


	}
	
	/**
	 * Test method for {@link es.unileon.sonarqube.sedcat.start.GeneralComputer#compute()}.
	 */
	@Test
	public final void compute_component_NoProject() {

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
	public final void compute_Project() {

		//TODO
	}
	

}
