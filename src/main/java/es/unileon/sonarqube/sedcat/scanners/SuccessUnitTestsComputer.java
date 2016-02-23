/**
 * 
 */
package es.unileon.sonarqube.sedcat.scanners;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sonar.api.batch.InstantiationStrategy;
import org.sonar.api.ce.measure.Measure;
import org.sonar.api.ce.measure.MeasureComputer;
import org.sonar.api.measures.CoreMetrics;

import es.unileon.sonarqube.sedcat.start.SedcatMetricsKeys;
/**
 *	@author alan.sastre
 *	@version 1.0
 */
//@InstantiationStrategy(InstantiationStrategy.PER_PROJECT)
public class SuccessUnitTestsComputer implements MeasureComputer {


    /**
     * The logger object for the sensor.
     */
	private static final Logger LOG = LoggerFactory.getLogger(SuccessUnitTestsComputer.class);
	
	
	public void compute(MeasureComputerContext context) {
		
//		LOG.info("tipo: "+context.getComponent().getType());
////		LOG.info("Computer: SUCCESS TESTS atributos: "+context.getComponent().getFileAttributes());
//		if(!context.getComponent().getType().toString().equalsIgnoreCase("FILE")){
//			return;
//		}
//		
//		LOG.info("isunittest: "+context.getComponent().getFileAttributes().isUnitTest());
//		if(!context.getComponent().getFileAttributes().isUnitTest()){
//			return;
//		}
//
//		Measure success = context.getMeasure(SedcatMetricsKeys.SUCCESS_UNIT_TESTS_KEY);
//		double successValue = 0;
//		if (success!=null) {
//			successValue = context.getMeasure(SedcatMetricsKeys.SUCCESS_UNIT_TESTS_KEY).getDoubleValue();
//			successValue=successValue+context.getMeasure(CoreMetrics.TEST_SUCCESS_DENSITY_KEY).getDoubleValue();
//			LOG.info("if: "+successValue);
//		}else{
//			successValue=context.getMeasure(CoreMetrics.TEST_SUCCESS_DENSITY_KEY).getDoubleValue();			
//			LOG.info("else: "+successValue);
//		}
//		context.addMeasure(SedcatMetricsKeys.SUCCESS_UNIT_TESTS_KEY, successValue);
//	     LOG.info("Computer: SUCCESS TEST guardada ha sido: "+successValue);

//		if(!context.getComponent().getType().toString().equalsIgnoreCase("MODULE")){
//			return;
//		}

		LOG.info("tipo: "+context.getComponent().getType());
		
		
		Measure success = context.getMeasure(CoreMetrics.TEST_SUCCESS_DENSITY_KEY);
		if (success!=null) {
			LOG.info("tipo: "+success.getDoubleValue());
			context.addMeasure(SedcatMetricsKeys.SUCCESS_UNIT_TESTS_KEY, success.getDoubleValue());
		}else{
			LOG.info("Esta metrica ha sido nula, el sistema la considerara como cero. ");
			context.addMeasure(SedcatMetricsKeys.SUCCESS_UNIT_TESTS_KEY, 0.0);
		}
		
//	     Double success_tests = 0.0;
//	     
//	     for (Measure childMeasure : context.getChildrenMeasures(CoreMetrics.TEST_SUCCESS_DENSITY_KEY)) {
//	    	 
//	    	 success_tests += childMeasure.getDoubleValue();
//	       }
//	     
//	     context.addMeasure(SedcatMetricsKeys.SUCCESS_UNIT_TESTS_KEY, success_tests);
//	     LOG.info("guardada ha sido: "+success_tests);
	    /*
	     * Revisar
	     * https://github.com/SonarSource/sonarqube/blob/master/server/sonar-server/src/main/java/org/sonar/server/computation/step/UnitTestMeasuresStep.java
	     */

	}

	public MeasureComputerDefinition define(MeasureComputerDefinitionContext defContext) {

	    return defContext.newDefinitionBuilder()

	    	     // Input metrics can be empty, for instance if only issues will be read
	    	     .setInputMetrics(CoreMetrics.TEST_SUCCESS_DENSITY_KEY)

	    	     // Output metrics must contains at least one metric
	    	     .setOutputMetrics(SedcatMetricsKeys.SUCCESS_UNIT_TESTS_KEY)

	    	     .build();
	    
	}
	
	@Override
	  public String toString() {
	    return getClass().getSimpleName();
	  }
	
}

