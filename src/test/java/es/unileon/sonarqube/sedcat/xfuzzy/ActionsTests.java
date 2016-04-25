/**
 * 
 */
package es.unileon.sonarqube.sedcat.xfuzzy;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import es.unileon.sonarqube.sedcat.xfuzzy.actions.Acciones_1;

/**
 *	@author alan.sastre
 *	@version 1.0
 */
public class ActionsTests {

	
	private Acciones_1 underTest;
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
		
		underTest = new Acciones_1();
		
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link es.unileon.sonarqube.sedcat.xfuzzy.actions.Acciones_1#crispInference(double[])}.
	 */
	@Test
	public final void testCrispInferenceDoubleArray() {

		//OPTIMAL CASE
		double[] inputMetricsValues = new double[]{
				100,
				100,
				5000,
				100,
				10000,
		};
		Assert.assertEquals(12.0, underTest.crispInference(inputMetricsValues)[0], 0.0);
		
		inputMetricsValues = new double[]{
				0,
				0,
				0,
				0,
				0,
		};
		Assert.assertEquals(0.0, underTest.crispInference(inputMetricsValues)[0], 0.0);
		
		inputMetricsValues = new double[]{
				50,
				50,
				3000,
				100,
				100000,
		};
		Assert.assertEquals(32.0, underTest.crispInference(inputMetricsValues)[0], 0.0);

		
	}
	
	
	/**
	 * Test method for {@link es.unileon.sonarqube.sedcat.xfuzzy.quality.Calidad_1#crispInference(double[])}.
	 */
	@Test
	public final void testCrispInferenceDoubleArrayOnLimits_Under() {
		
		double[] inputMetricsValues = new double[]{
				-1,
				-1,
				-1,
				-1,
				-1,
				
		};
		Assert.assertEquals(0.0, underTest.crispInference(inputMetricsValues)[0], 0.0);

		
		 inputMetricsValues = new double[]{
					-11111,
					-11111,
					-11111,
					-111111,
					-111111,
			};
		 
		/*
		 * outside the boundary of the universe of variables xfuzzy the 
		 * result is the default value, in this case 16 (the middle of max value of output)
		 */
		 Assert.assertEquals(16.0, underTest.crispInference(inputMetricsValues)[0], 0.0);
	}
	
	
	
	/**
	 * Test method for {@link es.unileon.sonarqube.sedcat.xfuzzy.quality.Calidad_1#crispInference(double[])}.
	 */
	@Test
	public final void testCrispInferenceDoubleArrayOnLimits_Above() {
		
		double[] inputMetricsValues = new double[]{
				101,
				101,
				101,
				5001,
				100001,
				
		};

		Assert.assertEquals(16.0, underTest.crispInference(inputMetricsValues)[0], 0.0);

		
		 inputMetricsValues = new double[]{
					1000,
					1000,
					1000,
					7000,
					1000000
			};
		 
		/*
		 * outside the boundary of the universe of variables xfuzzy the 
		 * result is the default value, in this case 16 (the middle of max value of output)
		 */
		 Assert.assertEquals(16.0, underTest.crispInference(inputMetricsValues)[0], 0.0);
	}

}
