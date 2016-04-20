/**
 * 
 */
package es.unileon.sonarqube.sedcat.start;

import static org.junit.Assert.*;

import java.util.List;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.sonar.api.measures.Metric;


/**
 * Test method for {@link es.unileon.sonarqube.sedcat.start.SedcatMetrics#getMetrics()}.
 * 
 *	@author alan.sastre
 *	@version 1.0
 */
public class SedcatMetricsTests {

	
	private SedcatMetrics underTest; 
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		
		underTest = new SedcatMetrics();
	}


	@Test
	public final void testGetMetrics() {

		List<Metric> metrics = underTest.getMetrics();
		Assert.assertFalse(metrics.isEmpty());
		
		String[] expectedMetricsKeys = new String[]{
				
				SedcatMetricsKeys.SUCCESS_UNIT_TESTS_KEY,
				SedcatMetricsKeys.COVERAGE_UNIT_TESTS_KEY,
				SedcatMetricsKeys.MUTANTS_KEY,
				SedcatMetricsKeys.NUMBERTESTS_KEY,
				SedcatMetricsKeys.CODE_LINES_KEY,
				SedcatMetricsKeys.QUALITY_MEASURE_KEY,
				SedcatMetricsKeys.ACTIONS_TO_PERFORM_KEY,
		};
		
		boolean checker = false;
		
		for (Metric concreteMetric : metrics) {

			for (int i = 0; i < expectedMetricsKeys.length; i++) {
				if(concreteMetric.getKey().contains(expectedMetricsKeys[i])){
					checker = true;
					break;
				}
			}
			
			if(checker==true){
				checker=false;
			}else{
				
				//No se ha encontrado una clase
				fail("Falta la metrica: "+ concreteMetric.getKey()); 
			}

		}


	}

}