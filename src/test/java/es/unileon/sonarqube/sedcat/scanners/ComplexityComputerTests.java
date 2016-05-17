package es.unileon.sonarqube.sedcat.scanners;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.sonar.api.ce.measure.MeasureComputer.MeasureComputerDefinition;
import org.sonar.api.ce.measure.test.TestMeasureComputerContext;
import org.sonar.api.ce.measure.test.TestMeasureComputerDefinitionContext;
import org.sonar.api.measures.CoreMetrics;

import es.unileon.sonarqube.sedcat.start.SedcatMetricsKeys;
import es.unileon.sonarqube.sedcat.strategies.ExpertSystemActions;
import es.unileon.sonarqube.sedcat.strategies.ExpertSystemQuality;
import java.util.Set;
import org.sonar.api.ce.measure.Component.FileAttributes;
import org.sonar.api.ce.measure.Component.Type;
import org.sonar.api.ce.measure.Measure;
import static org.mockito.Mockito.*;
import org.sonar.api.ce.measure.test.*;
import org.powermock.api.mockito.*;


/** Tests para la clase scanner ComplexityComputer
 *	@author alan.sastre
 *	@version 1.0
 */
public class ComplexityComputerTests {
	
	private ComplexityComputer underTest;
	
	private TestMeasureComputerContext computerContextMocked;
	private TestComponent componentMocked;
	private Measure measureMockedComplexity;
	private Measure measureMockedComplexitythreshold;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		
		computerContextMocked = mock(TestMeasureComputerContext.class);
		componentMocked = mock(TestComponent.class);
		measureMockedComplexity = mock(Measure.class);
		measureMockedComplexitythreshold = mock(Measure.class);
		
		underTest = new ComplexityComputer();
	}


	/**
	 * Test method for {@link es.unileon.sonarqube.sedcat.scanners.ComplexityComputer#define(org.sonar.api.ce.measure.MeasureComputer.MeasureComputerDefinitionContext)}.
	 */
	@Test
	public final void testDefineCorrectState() {
		
		
		 TestMeasureComputerDefinitionContext defContext = new TestMeasureComputerDefinitionContext();
		 MeasureComputerDefinition def = underTest.define(defContext);
	     Assert.assertNotNull(def);
	     
	     //Probar metricas de entrada
	     Set<String> inputMetrics = def.getInputMetrics();
	     Assert.assertEquals(inputMetrics.size(), 2);
	     
	     Assert.assertTrue(inputMetrics.contains(CoreMetrics.CLASS_COMPLEXITY_KEY));
	     Assert.assertTrue(inputMetrics.contains(SedcatMetricsKeys.COMPLEXITY_THRESHOLD_KEY));

	     //Probar metricas de salida
	     Set<String> ouputMetrics = def.getOutputMetrics();
	     Assert.assertEquals(ouputMetrics.size(), 1);

	     Assert.assertTrue(ouputMetrics.contains(SedcatMetricsKeys.COMPLEXITY_CLASS_KEY));

	}
	
	
	/**
	 * Test method for {@link es.unileon.sonarqube.sedcat.scanners.ComplexityComputer#compute(org.sonar.api.ce.measure.MeasureComputer.MeasureComputerContext)}.
	 */
	@Test
	public final void testComputeNoProject() {

		//Comprobar que el scanner no se ejecuta a un nivel que no sea PROJECT
		
	     when(computerContextMocked.getComponent()).thenReturn(componentMocked);
	     
	     //******** DIRECTORY ************
	     when(componentMocked.getType()).thenReturn(Type.DIRECTORY);

	     underTest.compute(computerContextMocked);
	     
	     //verificamos que no se ha ejecutado comprobando que no se ha llamado al metodo getMeasure
	     Mockito.verify(computerContextMocked, times(0)).getMeasure(CoreMetrics.CLASS_COMPLEXITY_KEY);
	     Mockito.verify(computerContextMocked, times(0)).getMeasure(SedcatMetricsKeys.COMPLEXITY_THRESHOLD_KEY);

	     
	     //******** FILE ************
	     when(componentMocked.getType()).thenReturn(Type.FILE);

	     underTest.compute(computerContextMocked);
	     
	     //verificamos que no se ha ejecutado comprobando que no se ha llamado al metodo getMeasure
	     Mockito.verify(computerContextMocked, times(0)).getMeasure(CoreMetrics.CLASS_COMPLEXITY_KEY);
	     Mockito.verify(computerContextMocked, times(0)).getMeasure(SedcatMetricsKeys.COMPLEXITY_THRESHOLD_KEY);
	     
	     
	     //******** MODULE ************
	     when(componentMocked.getType()).thenReturn(Type.MODULE);

	     underTest.compute(computerContextMocked);
	     
	     //verificamos que no se ha ejecutado comprobando que no se ha llamado al metodo getMeasure
	     Mockito.verify(computerContextMocked, times(0)).getMeasure(CoreMetrics.CLASS_COMPLEXITY_KEY);
	     Mockito.verify(computerContextMocked, times(0)).getMeasure(SedcatMetricsKeys.COMPLEXITY_THRESHOLD_KEY);
	     
	     
	     //******** VIEW ************
	     when(componentMocked.getType()).thenReturn(Type.VIEW);

	     underTest.compute(computerContextMocked);
	     
	     //verificamos que no se ha ejecutado comprobando que no se ha llamado al metodo getMeasure
	     Mockito.verify(computerContextMocked, times(0)).getMeasure(CoreMetrics.CLASS_COMPLEXITY_KEY);
	     Mockito.verify(computerContextMocked, times(0)).getMeasure(SedcatMetricsKeys.COMPLEXITY_THRESHOLD_KEY);
	     
	     
	     //******** SUBVIEW ************
	     when(componentMocked.getType()).thenReturn(Type.SUBVIEW);

	     underTest.compute(computerContextMocked);
	     
	     //verificamos que no se ha ejecutado comprobando que no se ha llamado al metodo getMeasure
	     Mockito.verify(computerContextMocked, times(0)).getMeasure(CoreMetrics.CLASS_COMPLEXITY_KEY);
	     Mockito.verify(computerContextMocked, times(0)).getMeasure(SedcatMetricsKeys.COMPLEXITY_THRESHOLD_KEY);
	     
	}
	
	
	/**
	 * Test method for {@link es.unileon.sonarqube.sedcat.scanners.ComplexityComputer#compute(org.sonar.api.ce.measure.MeasureComputer.MeasureComputerContext)}.
	 */
	@Test
	public final void testComputeProjectNullMetrics() {

		
	     when(computerContextMocked.getComponent()).thenReturn(componentMocked);
	     when(componentMocked.getType()).thenReturn(Type.PROJECT);

	     when(computerContextMocked.getMeasure(CoreMetrics.CLASS_COMPLEXITY_KEY)).thenReturn(null);
	     when(computerContextMocked.getMeasure(SedcatMetricsKeys.COMPLEXITY_THRESHOLD_KEY)).thenReturn(null);
	     
	     underTest.compute(computerContextMocked);
	     
	     //verificamos que no se ha ejecutado comprobando que solo se ha llamado al metodo getMeasure una vez
	     Mockito.verify(computerContextMocked, times(1)).getMeasure(CoreMetrics.CLASS_COMPLEXITY_KEY);
	     Mockito.verify(computerContextMocked, times(1)).getMeasure(SedcatMetricsKeys.COMPLEXITY_THRESHOLD_KEY);
	     //En este caso al ser las metricas nulas se pasa como parametro una complejidad de 15 por defecto
	     Mockito.verify(computerContextMocked, times(1)).addMeasure(SedcatMetricsKeys.COMPLEXITY_CLASS_KEY, 15.0);
	     

	}

	
	/**
	 * Test method for {@link es.unileon.sonarqube.sedcat.scanners.ComplexityComputer#compute(org.sonar.api.ce.measure.MeasureComputer.MeasureComputerContext)}.
	 */
	@Test
	public final void testComputeProjectMetricsWithinRanges() {


		when(computerContextMocked.getComponent()).thenReturn(componentMocked);
		when(componentMocked.getType()).thenReturn(Type.PROJECT);
		when(measureMockedComplexity.getDoubleValue()).thenReturn(18.0);
		when(measureMockedComplexitythreshold.getDoubleValue()).thenReturn(30.0);
		when(computerContextMocked.getMeasure(CoreMetrics.CLASS_COMPLEXITY_KEY)).thenReturn(measureMockedComplexity);
		when(computerContextMocked.getMeasure(SedcatMetricsKeys.COMPLEXITY_THRESHOLD_KEY))
				.thenReturn(measureMockedComplexitythreshold);

		underTest.compute(computerContextMocked);

		// verificamos que no se ha ejecutado comprobando que solo se ha llamado
		// al metodo getMeasure una vez
		Mockito.verify(computerContextMocked, times(2)).getMeasure(CoreMetrics.CLASS_COMPLEXITY_KEY);
		Mockito.verify(computerContextMocked, times(2)).getMeasure(SedcatMetricsKeys.COMPLEXITY_THRESHOLD_KEY);
		// En este caso al ser la complejidad menor que el umbral se considera
		// 15, es decir ideal ya que tiene un valor permitido
		Mockito.verify(computerContextMocked, times(1)).addMeasure(SedcatMetricsKeys.COMPLEXITY_CLASS_KEY, 15.0);

	}
	
	
	/**
	 * Test method for {@link es.unileon.sonarqube.sedcat.scanners.ComplexityComputer#compute(org.sonar.api.ce.measure.MeasureComputer.MeasureComputerContext)}.
	 */
	@Test
	public final void testComputeProjectThresoldGreaterThan60() {

		when(computerContextMocked.getComponent()).thenReturn(componentMocked);
		when(componentMocked.getType()).thenReturn(Type.PROJECT);
		when(measureMockedComplexity.getDoubleValue()).thenReturn(60.0);
		when(measureMockedComplexitythreshold.getDoubleValue()).thenReturn(89.0);

		when(computerContextMocked.getMeasure(CoreMetrics.CLASS_COMPLEXITY_KEY)).thenReturn(measureMockedComplexity);
		when(computerContextMocked.getMeasure(SedcatMetricsKeys.COMPLEXITY_THRESHOLD_KEY))
				.thenReturn(measureMockedComplexitythreshold);

		underTest.compute(computerContextMocked);

		Mockito.verify(computerContextMocked, times(1)).addMeasure(SedcatMetricsKeys.COMPLEXITY_CLASS_KEY, 15.0);

		

	}
	
	
	/**
	 * Test method for {@link es.unileon.sonarqube.sedcat.scanners.ComplexityComputer#compute(org.sonar.api.ce.measure.MeasureComputer.MeasureComputerContext)}.
	 */
	@Test
	public final void testComputeProjectComplexityGreaterThan60() {
		

		when(computerContextMocked.getComponent()).thenReturn(componentMocked);
		when(componentMocked.getType()).thenReturn(Type.PROJECT);
		/*
		 * Caso superior, resultado complejidad < 60 
		 */
		when(measureMockedComplexity.getDoubleValue()).thenReturn(80.0);
		when(measureMockedComplexitythreshold.getDoubleValue()).thenReturn(89.0);

		when(computerContextMocked.getMeasure(CoreMetrics.CLASS_COMPLEXITY_KEY)).thenReturn(measureMockedComplexity);
		when(computerContextMocked.getMeasure(SedcatMetricsKeys.COMPLEXITY_THRESHOLD_KEY))
				.thenReturn(measureMockedComplexitythreshold);

		underTest.compute(computerContextMocked);

		// Se verifica utilizando una complejidad mayor que el umbral, en este
		// caso hará el calculo del else, comprobamos el mismo resultado:
		Mockito.verify(computerContextMocked, times(1)).addMeasure(SedcatMetricsKeys.COMPLEXITY_CLASS_KEY, 50.0);
		
		/*
		 * Caso muy superior, resultado complejidad > 60, se asigna 60
		 */
		when(measureMockedComplexity.getDoubleValue()).thenReturn(100.0);
		when(measureMockedComplexitythreshold.getDoubleValue()).thenReturn(89.0);

		underTest.compute(computerContextMocked);

		// Se verifica utilizando una complejidad mayor que el umbral, en este
		// caso hará el calculo del else, comprobamos el mismo resultado:
		Mockito.verify(computerContextMocked, times(1)).addMeasure(SedcatMetricsKeys.COMPLEXITY_CLASS_KEY, 60.0);

	}
	

	/**
	 * Test method for {@link es.unileon.sonarqube.sedcat.scanners.ComplexityComputer#compute(org.sonar.api.ce.measure.MeasureComputer.MeasureComputerContext)}.
	 */
	@Test
	public final void testComputeProjectThresoldLessThan0() {
		
		when(computerContextMocked.getComponent()).thenReturn(componentMocked);
		when(componentMocked.getType()).thenReturn(Type.PROJECT);
		when(measureMockedComplexity.getDoubleValue()).thenReturn(60.0);
		when(measureMockedComplexitythreshold.getDoubleValue()).thenReturn(-5.0);

		when(computerContextMocked.getMeasure(CoreMetrics.CLASS_COMPLEXITY_KEY)).thenReturn(measureMockedComplexity);
		when(computerContextMocked.getMeasure(SedcatMetricsKeys.COMPLEXITY_THRESHOLD_KEY))
				.thenReturn(measureMockedComplexitythreshold);

		underTest.compute(computerContextMocked);

		Mockito.verify(computerContextMocked, times(1)).addMeasure(SedcatMetricsKeys.COMPLEXITY_CLASS_KEY, 60.0);
		
	}
		
		

}
