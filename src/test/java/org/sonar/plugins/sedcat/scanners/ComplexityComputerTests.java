package org.sonar.plugins.sedcat.scanners;

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
import org.sonar.api.measures.CoreMetrics;
import org.sonar.plugins.sedcat.scanners.ComplexityComputer;
import org.sonar.plugins.sedcat.start.SedcatConstants;
import org.sonar.plugins.sedcat.start.SedcatMetricsKeys;
import org.sonar.plugins.sedcat.strategies.ExpertSystemActions;
import org.sonar.plugins.sedcat.strategies.ExpertSystemQuality;

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

	private TestSettings settingsMocked;
	private String TRUE_EXECUTION = "true";
	private String FALSE_EXECUTION = "false";

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		
		
		computerContextMocked = mock(TestMeasureComputerContext.class);
		componentMocked = mock(TestComponent.class);
		measureMockedComplexity = mock(Measure.class);

		settingsMocked = mock(TestSettings.class);
		
		when(computerContextMocked.getComponent()).thenReturn(componentMocked);
		when(componentMocked.getType()).thenReturn(Type.PROJECT);
		
		//habilitar ejecucion sedcat
		when(settingsMocked.getString(SedcatConstants.ACTIVE_MODE_KEY)).thenReturn(TRUE_EXECUTION);
		when(computerContextMocked.getSettings()).thenReturn(settingsMocked);
		
		underTest = new ComplexityComputer();
	}


	/**
	 * Test method for {@link org.sonar.plugins.sedcat.scanners.ComplexityComputer#define(org.sonar.api.ce.measure.MeasureComputer.MeasureComputerDefinitionContext)}.
	 */
	@Test
	public final void testDefineCorrectState() {
		
		
		 TestMeasureComputerDefinitionContext defContext = new TestMeasureComputerDefinitionContext();
		 MeasureComputerDefinition def = underTest.define(defContext);
	     Assert.assertNotNull(def);
	     
	     //Probar metricas de entrada
	     Set<String> inputMetrics = def.getInputMetrics();
	     Assert.assertEquals(inputMetrics.size(), 1);
	     
	     Assert.assertTrue(inputMetrics.contains(CoreMetrics.CLASS_COMPLEXITY_KEY));

	     //Probar metricas de salida
	     Set<String> ouputMetrics = def.getOutputMetrics();
	     Assert.assertEquals(ouputMetrics.size(), 1);

	     Assert.assertTrue(ouputMetrics.contains(SedcatMetricsKeys.COMPLEXITY_CLASS_KEY));

	}

	
	/**
	 * Test method for {@link org.sonar.plugins.sedcat.scanners.ComplexityComputer#compute(org.sonar.api.ce.measure.MeasureComputer.MeasureComputerContext)}.
	 * Prueba que el computer no se ejecuta en los niveles que no sean proyecto
	 */
	@Test
	public final void testComputeNoProject() {

		//Comprobar que el computer no se ejecuta a un nivel que no sea PROJECT
		
	     when(computerContextMocked.getComponent()).thenReturn(componentMocked);
	    

	     
	     //******** DIRECTORY ************
	     when(componentMocked.getType()).thenReturn(Type.DIRECTORY);

	     underTest.compute(computerContextMocked);
	     
	     //verificamos que no se ha ejecutado comprobando que no se ha llamado al metodo getMeasure
	     Mockito.verify(computerContextMocked, times(0)).getMeasure(CoreMetrics.CLASS_COMPLEXITY_KEY);


	     
	     //******** FILE ************
	     when(componentMocked.getType()).thenReturn(Type.FILE);

	     underTest.compute(computerContextMocked);
	     
	     //verificamos que no se ha ejecutado comprobando que no se ha llamado al metodo getMeasure
	     Mockito.verify(computerContextMocked, times(0)).getMeasure(CoreMetrics.CLASS_COMPLEXITY_KEY);

	     
	     
	     //******** MODULE ************
	     when(componentMocked.getType()).thenReturn(Type.MODULE);

	     underTest.compute(computerContextMocked);
	     
	     //verificamos que no se ha ejecutado comprobando que no se ha llamado al metodo getMeasure
	     Mockito.verify(computerContextMocked, times(0)).getMeasure(CoreMetrics.CLASS_COMPLEXITY_KEY);
	     
	     
	     //******** VIEW ************
	     when(componentMocked.getType()).thenReturn(Type.VIEW);

	     underTest.compute(computerContextMocked);
	     
	     //verificamos que no se ha ejecutado comprobando que no se ha llamado al metodo getMeasure
	     Mockito.verify(computerContextMocked, times(0)).getMeasure(CoreMetrics.CLASS_COMPLEXITY_KEY);
	     
	     
	     //******** SUBVIEW ************
	     when(componentMocked.getType()).thenReturn(Type.SUBVIEW);

	     underTest.compute(computerContextMocked);
	     
	     //verificamos que no se ha ejecutado comprobando que no se ha llamado al metodo getMeasure
	     Mockito.verify(computerContextMocked, times(0)).getMeasure(CoreMetrics.CLASS_COMPLEXITY_KEY);
	     
	}
	
	
	/**
	 * Test method for {@link org.sonar.plugins.sedcat.scanners.ComplexityComputer#compute(org.sonar.api.ce.measure.MeasureComputer.MeasureComputerContext)}.
	 */
	@Test
	public final void testComputeProjectNullMetrics() {

		
	     when(computerContextMocked.getMeasure(CoreMetrics.CLASS_COMPLEXITY_KEY)).thenReturn(null);
	     
	     underTest.compute(computerContextMocked);
	     
	     //verificamos que no se ha ejecutado comprobando que solo se ha llamado al metodo getMeasure una vez
	     Mockito.verify(computerContextMocked, times(1)).getMeasure(CoreMetrics.CLASS_COMPLEXITY_KEY);

	     //En este caso al ser las metricas nulas se pasa como parametro una complejidad de 15 por defecto
	     Mockito.verify(computerContextMocked, times(1)).addMeasure(SedcatMetricsKeys.COMPLEXITY_CLASS_KEY, 15.0);
	     

	}

	
	/**
	 * Test method for {@link org.sonar.plugins.sedcat.scanners.ComplexityComputer#compute(org.sonar.api.ce.measure.MeasureComputer.MeasureComputerContext)}.
	 */
	@Test
	public final void testComputeProjectMetricsWithinRanges() {

		when(settingsMocked.getString(SedcatConstants.COMPLEXITY_THRESHOLD_KEY)).thenReturn("30.0");
		when(measureMockedComplexity.getDoubleValue()).thenReturn(18.0);
		when(computerContextMocked.getMeasure(CoreMetrics.CLASS_COMPLEXITY_KEY)).thenReturn(measureMockedComplexity);


		underTest.compute(computerContextMocked);

		// verificamos que no se ha ejecutado comprobando que solo se ha llamado
		// al metodo getMeasure una vez
		Mockito.verify(computerContextMocked, times(2)).getMeasure(CoreMetrics.CLASS_COMPLEXITY_KEY);
		// En este caso al ser la complejidad menor que el umbral se considera
		// 15, es decir ideal ya que tiene un valor permitido
		Mockito.verify(computerContextMocked, times(1)).addMeasure(SedcatMetricsKeys.COMPLEXITY_CLASS_KEY, 15.0);

	}
	
	
	/**
	 * Test method for {@link org.sonar.plugins.sedcat.scanners.ComplexityComputer#compute(org.sonar.api.ce.measure.MeasureComputer.MeasureComputerContext)}.
	 */
	@Test
	public final void testComputeProjectThresoldGreaterThan60() {

		when(settingsMocked.getString(SedcatConstants.COMPLEXITY_THRESHOLD_KEY)).thenReturn("89.0");
		
		when(measureMockedComplexity.getDoubleValue()).thenReturn(60.0);

		when(computerContextMocked.getMeasure(CoreMetrics.CLASS_COMPLEXITY_KEY)).thenReturn(measureMockedComplexity);

		underTest.compute(computerContextMocked);

		Mockito.verify(computerContextMocked, times(1)).addMeasure(SedcatMetricsKeys.COMPLEXITY_CLASS_KEY, 15.0);

		

	}
	
	
	/**
	 * Test method for {@link org.sonar.plugins.sedcat.scanners.ComplexityComputer#compute(org.sonar.api.ce.measure.MeasureComputer.MeasureComputerContext)}.
	 */
	@Test
	public final void testComputeProjectComplexityGreaterThan60() {
		

		when(settingsMocked.getString(SedcatConstants.COMPLEXITY_THRESHOLD_KEY)).thenReturn("89.0");
		/*
		 * Caso superior, resultado complejidad < 60 
		 */
		when(measureMockedComplexity.getDoubleValue()).thenReturn(80.0);
		when(computerContextMocked.getMeasure(CoreMetrics.CLASS_COMPLEXITY_KEY)).thenReturn(measureMockedComplexity);


		underTest.compute(computerContextMocked);

		// Se verifica utilizando una complejidad mayor que el umbral, en este
		// caso hará el calculo del else, comprobamos el mismo resultado:
		Mockito.verify(computerContextMocked, times(1)).addMeasure(SedcatMetricsKeys.COMPLEXITY_CLASS_KEY, 50.0);
		
		/*
		 * Caso muy superior, resultado complejidad > 60, se asigna 60
		 */
		when(measureMockedComplexity.getDoubleValue()).thenReturn(100.0);

		underTest.compute(computerContextMocked);

		// Se verifica utilizando una complejidad mayor que el umbral, en este
		// caso hará el calculo del else, comprobamos el mismo resultado:
		Mockito.verify(computerContextMocked, times(1)).addMeasure(SedcatMetricsKeys.COMPLEXITY_CLASS_KEY, 60.0);

	}
	

	/**
	 * Test method for {@link org.sonar.plugins.sedcat.scanners.ComplexityComputer#compute(org.sonar.api.ce.measure.MeasureComputer.MeasureComputerContext)}.
	 */
	@Test
	public final void testComputeProjectThresoldLessThan0() {
		
		when(settingsMocked.getString(SedcatConstants.COMPLEXITY_THRESHOLD_KEY)).thenReturn("-5.0");
		
		when(measureMockedComplexity.getDoubleValue()).thenReturn(60.0);

		when(computerContextMocked.getMeasure(CoreMetrics.CLASS_COMPLEXITY_KEY)).thenReturn(measureMockedComplexity);

		underTest.compute(computerContextMocked);

		Mockito.verify(computerContextMocked, times(1)).addMeasure(SedcatMetricsKeys.COMPLEXITY_CLASS_KEY, 60.0);
		
	}
		
		

}
