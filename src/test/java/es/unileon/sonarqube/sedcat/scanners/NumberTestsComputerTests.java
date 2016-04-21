/**
 * 
 */
package es.unileon.sonarqube.sedcat.scanners;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

import java.util.Set;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;
import org.sonar.api.ce.measure.Measure;
import org.sonar.api.ce.measure.Component.Type;
import org.sonar.api.ce.measure.MeasureComputer.MeasureComputerDefinition;
import org.sonar.api.ce.measure.test.TestComponent;
import org.sonar.api.ce.measure.test.TestMeasureComputerContext;
import org.sonar.api.ce.measure.test.TestMeasureComputerDefinitionContext;
import org.sonar.api.measures.CoreMetrics;
import es.unileon.sonarqube.sedcat.start.SedcatMetricsKeys;

/**
 *	@author alan.sastre
 *	@version 1.0
 */
public class NumberTestsComputerTests {

	NumberTestsComputer underTest = new NumberTestsComputer();
	
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
	 * Test method for {@link es.unileon.sonarqube.sedcat.scanners.NumberTestsComputer#compute(org.sonar.api.ce.measure.MeasureComputer.MeasureComputerContext)}.
	 */
	@Test
	public final void compute_notNull_Measure() {

	     TestMeasureComputerContext computerContextMocked = mock(TestMeasureComputerContext.class);
	     TestComponent componentMocked = mock(TestComponent.class);
	     
	     when(computerContextMocked.getComponent()).thenReturn(componentMocked);
	     when(componentMocked.getType()).thenReturn(Type.PROJECT);
	     
	     Measure measureMocked = mock(Measure.class);
	     
	     when(computerContextMocked.getMeasure(CoreMetrics.TESTS_KEY)).thenReturn(measureMocked);
	     when(measureMocked.getIntValue()).thenReturn(100);

	     underTest.compute(computerContextMocked);
	     
	     Mockito.verify(computerContextMocked, times(1)).getMeasure(CoreMetrics.TESTS_KEY);
	     Mockito.verify(measureMocked, times(2)).getIntValue();
	     Mockito.verify(computerContextMocked, times(1)).addMeasure(SedcatMetricsKeys.NUMBERTESTS_KEY, measureMocked.getIntValue());

	     Assert.assertEquals(100, measureMocked.getIntValue(), 0);
	     
	}

	/**
	 * Test method for {@link es.unileon.sonarqube.sedcat.scanners.NumberTestsComputer#define(org.sonar.api.ce.measure.MeasureComputer.MeasureComputerDefinitionContext)}.
	 */
	@Test
	public final void define_Correct_State() {
		
		
		 TestMeasureComputerDefinitionContext defContext = new TestMeasureComputerDefinitionContext();
		 MeasureComputerDefinition def = underTest.define(defContext);
	     Assert.assertNotNull(def);
	     
	     //Probar metricas de entrada
	     Set<String> inputMetrics = def.getInputMetrics();
	     Assert.assertEquals(1, inputMetrics.size());
	     
	     Assert.assertTrue(inputMetrics.contains(CoreMetrics.TESTS_KEY));

	     //Probar metricas de salida
	     Set<String> ouputMetrics = def.getOutputMetrics();
	     Assert.assertEquals(1, ouputMetrics.size());

	     Assert.assertTrue(ouputMetrics.contains(SedcatMetricsKeys.NUMBERTESTS_KEY));

	}
	
	
	/**
	 * Test method for {@link es.unileon.sonarqube.sedcat.scanners.NumberTestsComputer#compute(org.sonar.api.ce.measure.MeasureComputer.MeasureComputerContext)}.
	 */
	@Test
	public final void compute_Null_Measure() {

		
	     TestMeasureComputerContext computerContextMocked = mock(TestMeasureComputerContext.class);
	     TestComponent componentMocked = mock(TestComponent.class);
	     
	     when(computerContextMocked.getComponent()).thenReturn(componentMocked);
	     when(componentMocked.getType()).thenReturn(Type.PROJECT);
	     
	     Measure measureMocked = mock(Measure.class);
	     
	     when(computerContextMocked.getMeasure(CoreMetrics.TESTS_KEY)).thenReturn(null);


	     underTest.compute(computerContextMocked);
	     
	     Mockito.verify(computerContextMocked, times(1)).getMeasure(CoreMetrics.TESTS_KEY);
	     Mockito.verify(measureMocked, times(0)).getIntValue();
	     Mockito.verify(computerContextMocked, times(1)).addMeasure(SedcatMetricsKeys.NUMBERTESTS_KEY, 0);

	     Assert.assertEquals(0, measureMocked.getIntValue(), 0);

	}

}
