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


	private static final Logger LOG = LoggerFactory.getLogger(NumberTestsComputer.class);
	
	
	public void compute(MeasureComputerContext context) {
		
		
		
		LOG.info("tipo: "+context.getComponent().getType());
		
		
		Measure num_tests = context.getMeasure(CoreMetrics.TESTS_KEY);
		if (num_tests!=null) {
			LOG.info("tipo: "+num_tests.getIntValue());
			context.addMeasure(SedcatMetricsKeys.NUMBERTESTS_KEY, num_tests.getIntValue());
		}else{
			LOG.info("Esta metrica ha sido nula, el sistema la considerara como cero. ");
			context.addMeasure(SedcatMetricsKeys.NUMBERTESTS_KEY, 0);
		}
		
//		LOG.info("Computer: NUMBER TEST");
//
//	     int num_tests = 0;
//	     
//	     for (Measure childMeasure : context.getChildrenMeasures(CoreMetrics.TESTS_KEY)) {
//	    	 num_tests += childMeasure.getIntValue();
//	       }
//	     
//	     context.addMeasure(SedcatMetricsKeys.NUMBERTESTS_KEY, num_tests);
//	     LOG.info("Computer: NUMBER TEST metrica guardada");

	}

	public MeasureComputerDefinition define(MeasureComputerDefinitionContext defContext) {

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

