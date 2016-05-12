package es.unileon.sonarqube.sedcat.xfuzzy;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.Assert;

import es.unileon.sonarqube.sedcat.xfuzzy.quality.Calidad;

/**
 *  Test unitarios para la clase es.unileon.sonarqube.sedcat.xfuzzy.quality.Calidad
 *  Las variables de entrada que recibe son:
 *  
 *  	-Porcentaje de Éxito de los casos de test unitarios
 *  	-Porcentaje de cobertura de los casos de test unitarios
 *  	-Porcentaje de cobertura por mutantes de los casos de test unitarios
 *  	-Número de casos de test unitarios	
 *  	-Número de líneas de código que tiene el proyecto
 *  	-Complejidad media por clase 
 *  
 * @author alan.sastre
 * @version 1.0
 */
public class QualityTests {

	// max value for success, coverage, mutations
	private static final double PERCENTAGE_MAX = 100;

	// numbertest
	private static final int NUMBERTEST_MAX = 5000;

	// number code lines
	private static final int CODELINES_MAX = 100000;

	// Complexity max
	private static final int COMPLEXITY_MAX = 60;
	
	// number of input metrics
	private static final int NUMBER_METRICS = 6;

	private Calidad underTest;
	
	double[] inputMetricsValues;

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

		underTest = new Calidad();

	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for
	 * {@link es.unileon.sonarqube.sedcat.xfuzzy.quality.Calidad#crispInference(double[])}
	 * .
	 */

	@Test
	public final void testCrispInferenceDoubleArray() {

		double[] inputMetricsValues = new double[NUMBER_METRICS];

		double result = 0;
		inputMetricsValues[5] = 30.0;

		for (int success = 0; success <= PERCENTAGE_MAX; success += 25) {
			inputMetricsValues[0] = success;

			for (int coverage = 0; coverage <= PERCENTAGE_MAX; coverage += 25) {
				inputMetricsValues[1] = coverage;

				for (int mutations = 0; mutations <= PERCENTAGE_MAX; mutations += 25) {
					inputMetricsValues[2] = mutations;

					for (int numbertests = 0; numbertests <= NUMBERTEST_MAX; numbertests += 200) {
						inputMetricsValues[3] = numbertests;

						for (int codelines = 0; codelines <= CODELINES_MAX; codelines += 6000) {
							inputMetricsValues[4] = codelines;
							
								System.out.println(result = underTest.crispInference(inputMetricsValues)[0]);
//								result = underTest.crispInference(inputMetricsValues)[0];
	
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
	 * Test method for
	 * {@link es.unileon.sonarqube.sedcat.xfuzzy.quality.Calidad#crispInference(double[])}
	 * .
	 */
	@Test
	public final void testOptimalCase() {

		inputMetricsValues = new double[]{
			100,
			100,
			100,
			15000,
			250000,
			30.0
		};
		
		Assert.assertEquals(100.0, underTest.crispInference(inputMetricsValues)[0], 0.0);

	}
	
	/**
	 * Test method for
	 * {@link es.unileon.sonarqube.sedcat.xfuzzy.quality.Calidad#crispInference(double[])}
	 * .
	 */
	@Test
	public final void testNullSuccess() {

		inputMetricsValues = new double[]{
			0,
			100,
			100,
			15000,
			250000,
			30.0
		};
		
		Assert.assertEquals(0.0, underTest.crispInference(inputMetricsValues)[0], 0.0);

	}
	
	/**
	 * Test method for
	 * {@link es.unileon.sonarqube.sedcat.xfuzzy.quality.Calidad#crispInference(double[])}
	 * .
	 */
	@Test
	public final void testNullCoverage() {

		inputMetricsValues = new double[]{
			100,
			0,
			100,
			15000,
			250000,
			30.0
		};
		
		Assert.assertEquals(0.0, underTest.crispInference(inputMetricsValues)[0], 0.0);

	}


}
