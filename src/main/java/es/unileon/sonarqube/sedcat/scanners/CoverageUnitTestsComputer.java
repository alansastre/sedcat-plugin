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
public class CoverageUnitTestsComputer implements MeasureComputer {



    /**
     * The logger object for the sensor.
     */
	private static final Logger LOG = LoggerFactory.getLogger(CoverageUnitTestsComputer.class);
	
	
	public void compute(MeasureComputerContext context) {
		
		LOG.info("Computer: COVERAGE TESTS");

	     Double success_tests = 0.0;
	     
	     for (Measure childMeasure : context.getChildrenMeasures(CoreMetrics.COVERAGE_KEY)) {
	    	 success_tests += childMeasure.getDoubleValue();
	       }
	     
	     context.addMeasure(SedcatMetricsKeys.COVERAGE_UNIT_TESTS_KEY, success_tests);
	     

	}

	public MeasureComputerDefinition define(MeasureComputerDefinitionContext defContext) {

		LOG.info("Computers on definition: COVERAGE TESTS");
	    return defContext.newDefinitionBuilder()

	    	     // Input metrics can be empty, for instance if only issues will be read
	    	     .setInputMetrics(CoreMetrics.COVERAGE_KEY)

	    	     // Output metrics must contains at least one metric
	    	     .setOutputMetrics(SedcatMetricsKeys.COVERAGE_UNIT_TESTS_KEY)

	    	     .build();
	    
	}
	
	@Override
	  public String toString() {
	    return getClass().getSimpleName();
	  }
	
}


