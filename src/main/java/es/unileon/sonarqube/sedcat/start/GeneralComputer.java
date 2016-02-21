/**
 * 
 */
package es.unileon.sonarqube.sedcat.start;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sonar.api.batch.InstantiationStrategy;
import org.sonar.api.batch.SensorContext;
import org.sonar.api.batch.fs.FileSystem;
import org.sonar.api.ce.measure.Measure;
import org.sonar.api.ce.measure.MeasureComputer;
import org.sonar.api.config.Settings;
import org.sonar.api.measures.CoreMetrics;

import es.unileon.sonarqube.sedcat.storage.ActionsMeasureStore;
import es.unileon.sonarqube.sedcat.storage.QualityMeasureStore;
import es.unileon.sonarqube.sedcat.strategies.ExpertSystemActions;
import es.unileon.sonarqube.sedcat.strategies.ExpertSystemQuality;
import es.unileon.sonarqube.sedcat.strategies.IExpertSystemStrategy;
/**
 *	@author alan.sastre
 *	@version 1.0
 */
//@InstantiationStrategy(InstantiationStrategy.PER_PROJECT)
public class GeneralComputer implements MeasureComputer {

	/**
	 * The logger object for the sensor.
	 */
	private static final Logger LOG = LoggerFactory.getLogger(GeneralComputer.class);
	private IExpertSystemStrategy strategy;
	
	private static final String[] INPUT_METRICS_KEYS = new String[]{
			SedcatMetricsKeys.SUCCESS_UNIT_TESTS_KEY,
			
	};
	

	  /**
	   * 
	   */
	public void compute(MeasureComputerContext context) {
		

		LOG.info("General computer compute: inicio");
		LOG.info("getComponent(): "+context.getComponent());
		LOG.info("getComponent(): "+context.getComponent().getType());
		LOG.info("getComponent(): "+context.getComponent().getType().toString());

		
		if(!context.getComponent().getType().toString().equalsIgnoreCase("PROJECT")){
			return;
		}

		
		//Ejecutar sistemas expertos
	     	//quality
		this.setExpertSystemStrategy(new ExpertSystemQuality());
		this.performExpertSystemStrategy(context);
	     	//actions
		this.setExpertSystemStrategy(new ExpertSystemActions());
		this.performExpertSystemStrategy(context);


			
	}

	public MeasureComputerDefinition define(MeasureComputerDefinitionContext defContext) {
		
		
		LOG.info("General computer definition");
	    return defContext.newDefinitionBuilder()

	    	     // Input metrics can be empty, for instance if only issues will be read
	    	     .setInputMetrics(
	    	    		 SedcatMetricsKeys.SUCCESS_UNIT_TESTS_KEY,
	    	    		 SedcatMetricsKeys.COVERAGE_UNIT_TESTS_KEY,
	    	    		 SedcatMetricsKeys.NUMBERTESTS_KEY,
	    	    		 SedcatMetricsKeys.CODE_LINES_KEY
//	    	    		 SedcatMetricsKeys.MUTANTS_KEY,
	    	    		 )
	    	     
	    	     // Output metrics must contains at least one metric
	    	     .setOutputMetrics(SedcatMetricsKeys.QUALITY_MEASURE_KEY,SedcatMetricsKeys.ACTIONS_TO_PERFORM_KEY)

	    	     .build();
	    
	}
	
	@Override
	  public String toString() {
	    return getClass().getSimpleName();
	  }
	
	/**
	 * @param strategy
	 */
	  public void setExpertSystemStrategy(IExpertSystemStrategy strategy){
			this.strategy = strategy;
		}
	  
	  /**
	   * 
	   * @param inputMetrics
	   * @return
	   */
	  public void performExpertSystemStrategy(MeasureComputerContext context){
		  
		  this.strategy.xfuzzyProcess(context);
	  }
	
}


