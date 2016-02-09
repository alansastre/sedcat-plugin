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
public class NumberTestsComputer implements MeasureComputer {

    /**
     * The logger object for the sensor.
     */
	private static final Logger LOG = LoggerFactory.getLogger(NumberTestsComputer.class);
	
	
	public void compute(MeasureComputerContext context) {
		
		LOG.info("Computer: NUMBER TEST");

	     int num_tests = 0;
	     
	     for (Measure childMeasure : context.getChildrenMeasures(CoreMetrics.TESTS_KEY)) {
	    	 num_tests += childMeasure.getIntValue();
	       }
	     
	     context.addMeasure(SedcatMetricsKeys.NUMBERTESTS_KEY, num_tests);
	     

	}

	public MeasureComputerDefinition define(MeasureComputerDefinitionContext defContext) {

		LOG.info("Computers on definition: NUMBER TESTS");
	    return defContext.newDefinitionBuilder()

	    	     // Input metrics can be empty, for instance if only issues will be read
	    	     .setInputMetrics(CoreMetrics.TESTS_KEY)

	    	     // Output metrics must contains at least one metric
	    	     .setOutputMetrics(SedcatMetricsKeys.NUMBERTESTS_KEY)

	    	     .build();
	    
	}
	
	@Override
	  public String toString() {
	    return getClass().getSimpleName();
	  }
	
}

