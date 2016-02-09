/**
 * 
 */
package es.unileon.sonarqube.sedcat.scanners;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sonar.api.ce.measure.Measure;
import org.sonar.api.ce.measure.MeasureComputer;
import org.sonar.api.measures.CoreMetrics;

import es.unileon.sonarqube.sedcat.start.SedcatMetricsKeys;
/**
 *	@author alan.sastre
 *	@version 1.0
 */
public class SuccessUnitTestsComputer implements MeasureComputer {


    /**
     * The logger object for the sensor.
     */
	private static final Logger LOG = LoggerFactory.getLogger(SuccessUnitTestsComputer.class);
	
	
	public void compute(MeasureComputerContext context) {
		
		LOG.info("Computer: SUCCESS TEST");

	     Double success_tests = 0.0;
	     
	     for (Measure childMeasure : context.getChildrenMeasures(CoreMetrics.TEST_SUCCESS_DENSITY_KEY)) {
	    	 success_tests += childMeasure.getDoubleValue();
	       }
	     
	     context.addMeasure(SedcatMetricsKeys.EXITO_COVERAGE_KEY, success_tests);
	     

	}

	public MeasureComputerDefinition define(MeasureComputerDefinitionContext defContext) {

		LOG.info("Computers on definition: NUMBER TESTS");
	    return defContext.newDefinitionBuilder()

	    	     // Input metrics can be empty, for instance if only issues will be read
	    	     .setInputMetrics(CoreMetrics.TEST_SUCCESS_DENSITY_KEY)

	    	     // Output metrics must contains at least one metric
	    	     .setOutputMetrics(SedcatMetricsKeys.EXITO_COVERAGE_KEY)

	    	     .build();
	    
	}
	
	@Override
	  public String toString() {
	    return getClass().getSimpleName();
	  }
	
}

