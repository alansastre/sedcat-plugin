/**
 * 
 */
package es.unileon.sonarqube.sedcat.xfuzzy;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.Assert;

import es.unileon.sonarqube.sedcat.xfuzzy.quality.Calidad_1;


/**
 *	@author alan.sastre
 *	@version 1.0
 */
public class QualityTests {

	//max value for success, coverage, mutations
	private static final double PERCENTAGE_MAX = 100;
	
	//numbertest
	private static final int NUMBERTEST_MAX = 5000;
	
	//number code lines
	private static final int CODELINES_MAX = 100000;

	//number of input metrics 
	private static final int NUMBER_METRICS = 5;
	
	private Calidad_1 underTest;
	
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
		
		underTest = new Calidad_1();
		
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	
	/**
	 * Test method for {@link es.unileon.sonarqube.sedcat.xfuzzy.quality.Calidad_1#crispInference(double[])}.
	 */
	@Test
	public final void testCrispInferenceDoubleArray() {

		double[] inputMetricsValues = new double[NUMBER_METRICS];
		
		double result = 0;
		
		for (int success = 0; success <= PERCENTAGE_MAX; success+=25) {
			inputMetricsValues[0] = success;
			
			for (int coverage = 0; coverage <= PERCENTAGE_MAX; coverage+=25) {
				inputMetricsValues[1] = coverage;
				
				for (int mutations = 0; mutations <= PERCENTAGE_MAX; mutations+=25) {
					inputMetricsValues[2] = mutations;
					
					for (int numbertests = 0; numbertests <= NUMBERTEST_MAX; numbertests+=200) {
						inputMetricsValues[3] = numbertests;
						
						for (int codelines = 0; codelines <= CODELINES_MAX; codelines+=6000) {
							inputMetricsValues[4] = codelines;

							result = underTest.crispInference(inputMetricsValues)[0];

							if (success <= 25 && coverage <= 25 && mutations <= 25 && numbertests <= 200
									&& codelines <= 6000 && success != 0 && coverage != 0) {

								Assert.assertTrue(result > 0 && result <= 53);

							} else if (success > 25 && coverage > 25 && mutations > 25 && numbertests > 200
									&& codelines > 6000 && success <= 50 && coverage <= 50 && mutations <= 50
									&& numbertests <= 2000 && codelines <= 25000) {

								Assert.assertTrue(result > 0 && result <= 53);

							} else if (success > 50 && coverage > 50 && mutations > 50 && numbertests > 2000
									&& codelines > 25000 && success <= 75 && coverage <= 75 && mutations <= 75
									&& numbertests <= 4000 && codelines <= 75000) {

								System.out.println(result);
								Assert.assertTrue(result > 25 && result <= 75);

							} else if (success > 75 && coverage > 75 && mutations > 75 && numbertests > 4000
									&& codelines > 75000) {

								Assert.assertTrue(result > 69.971);

							} else if (success == 0 || coverage == 0) {

								Assert.assertEquals(0.0, result, 0.0);

							}

						}
					}
				}
			}
		}

		
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
		 * result is the default value, in this case 50
		 */
		 Assert.assertEquals(50.0, underTest.crispInference(inputMetricsValues)[0], 0.0);
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

		Assert.assertEquals(50.0, underTest.crispInference(inputMetricsValues)[0], 0.0);

		
		 inputMetricsValues = new double[]{
					1000,
					1000,
					1000,
					7000,
					1000000
			};
		 
		/*
		 * outside the boundary of the universe of variables xfuzzy the 
		 * result is the default value, in this case 50 (the middle of max value of output)
		 */
		 Assert.assertEquals(50.0, underTest.crispInference(inputMetricsValues)[0], 0.0);
	}
	
}
