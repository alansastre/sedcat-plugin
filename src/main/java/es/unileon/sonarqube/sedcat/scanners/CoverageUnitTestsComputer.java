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


	private static final Logger LOG = LoggerFactory.getLogger(CoverageUnitTestsComputer.class);
	
	
	public void compute(MeasureComputerContext context) {
		

		LOG.info("tipo: "+context.getComponent().getType());
		
		
		Measure success = context.getMeasure(CoreMetrics.COVERAGE_KEY);
		if (success!=null) {
			LOG.info("valor: "+success.getDoubleValue());
			context.addMeasure(SedcatMetricsKeys.COVERAGE_UNIT_TESTS_KEY, success.getDoubleValue());
		}else{
			LOG.info("Esta metrica ha sido nula, el sistema la considerara como cero. ");
			context.addMeasure(SedcatMetricsKeys.COVERAGE_UNIT_TESTS_KEY, 0.0);
		}


	}

	public MeasureComputerDefinition define(MeasureComputerDefinitionContext defContext) {

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


