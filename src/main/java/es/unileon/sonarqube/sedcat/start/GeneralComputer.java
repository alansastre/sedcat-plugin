/**
 * 
 */
package es.unileon.sonarqube.sedcat.start;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sonar.api.ce.measure.Measure;
import org.sonar.api.ce.measure.MeasureComputer;
import org.sonar.api.measures.CoreMetrics;
/**
 *	@author alan.sastre
 *	@version 1.0
 */
public class GeneralComputer implements MeasureComputer {

    /**
     * The logger object for the sensor.
     */
	private static final Logger LOG = LoggerFactory.getLogger(GeneralComputer.class);
	
	
	public void compute(MeasureComputerContext context) {
		
		LOG.info("General computer compute");
		
	     Measure numbertest = context.getMeasure(SedcatMetricsKeys.NUMBERTESTS_KEY);
	     int numbertest_value = numbertest.getIntValue();
	     LOG.info("General computer compute: numer test value is -->"+numbertest_value);
	     
	     Measure numbercodelines = context.getMeasure(SedcatMetricsKeys.CODE_LINES_KEY);
	     int numbercodelines_value = numbercodelines.getIntValue();
	     LOG.info("General computer compute: numer code lines value is -->"+numbercodelines_value);
	     
	     context.addMeasure(SedcatMetricsKeys.GENERAL_COMPUTER_RESULT, numbertest_value+numbercodelines_value);
	}

	public MeasureComputerDefinition define(MeasureComputerDefinitionContext defContext) {

		LOG.info("General computer definition");
	    return defContext.newDefinitionBuilder()

	    	     // Input metrics can be empty, for instance if only issues will be read
	    	     .setInputMetrics(SedcatMetricsKeys.NUMBERTESTS_KEY,SedcatMetricsKeys.CODE_LINES_KEY)
	    	     
	    	     // Output metrics must contains at least one metric
	    	     .setOutputMetrics(SedcatMetricsKeys.GENERAL_COMPUTER_RESULT)

	    	     .build();
	    
	}
	
	@Override
	  public String toString() {
	    return getClass().getSimpleName();
	  }
	
}


