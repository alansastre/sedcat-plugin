package es.unileon.sonarqube.sedcat.start;

import static es.unileon.sonarqube.sedcat.start.SedcatMetricsKeys.*;
import java.util.Arrays;
import java.util.List;
import org.sonar.api.measures.Metric;
import org.sonar.api.measures.Metrics;

/**
 * Metrics for the sonar SEDCAT plugin.
 * 
 * @author alan.sastre
 * @version 1.0
 */
public class SedcatMetrics implements Metrics {

	public static final String SEDCAT_DOMAIN = "Testing Quality";
/*
 * metricas de entrada utilizadas
 */
	
	//Metrica para  MUTANTES
	public static final Metric MUTANTS = new Metric.Builder(MUTANTS_KEY, "Mutations Coverage", Metric.ValueType.PERCENT)
		    .setDescription("Mutations coverage percentage obtained from the Pitest tool")
		    .setDirection(Metric.DIRECTION_BETTER)
		    .setQualitative(false)
		    .setDomain(SEDCAT_DOMAIN)
		    .setWorstValue(0.0)
		    .setBestValue(100.0)
		    .create();
	
	//Metrica COMPLEJIDAD / CLASE
	public static final Metric COMPLEXITY_AVERAGE_CLASS = new Metric.Builder(COMPLEXITY_CLASS_KEY, "Complexity by threshold /class", Metric.ValueType.FLOAT)
		    .setDescription("Complexity average by class based on threshold")
		    .setDirection(Metric.DIRECTION_WORST)
		    .setQualitative(false)
		    .setDomain(SEDCAT_DOMAIN)
		    .create();
	

/*
 * metricas de salida generadas
 */
		//MEDIDA CALIDAD
	public static final Metric QUALITY_MEASURE = new Metric.Builder(QUALITY_MEASURE_KEY, "Quality of unit testing", Metric.ValueType.PERCENT)
		    .setDescription("Quality of unit testing based on input metrics project")
		    .setDirection(Metric.DIRECTION_BETTER)
		    .setQualitative(false)
		    .setDomain(SEDCAT_DOMAIN)
		    .setWorstValue(0.0)
		    .setBestValue(100.0)
		    .create();
	//ACCIONES RECOMENDADAS - MENSAJE
public static final Metric<String> QUALITY_MESSAGE = new Metric.Builder(QUALITY_MESSAGE_KEY, "Quality description message", Metric.ValueType.STRING)
	    .setDescription("Description of quality unit testing based on input metrics project")
	    .setDirection(Metric.DIRECTION_NONE)
	    .setQualitative(false)
	    .setDomain(SEDCAT_DOMAIN)
	    .create();

		//ACCIONES RECOMENDADAS
	public static final Metric<String> ACTIONS_TO_PERFORM = new Metric.Builder(ACTIONS_TO_PERFORM_KEY, "Improvement actions", Metric.ValueType.STRING)
		    .setDescription("Improvement actions to take to improve the quality of unit testing")
		    .setDirection(Metric.DIRECTION_NONE)
		    .setQualitative(false)
		    .setDomain(SEDCAT_DOMAIN)
		    .create();
	//ACCIONES RECOMENDADAS - MENSAJE
public static final Metric<String> ACTIONS_MESSAGE = new Metric.Builder(ACTION_MESSAGE_KEY, "Actions to improve unit test quality", Metric.ValueType.STRING)
	    .setDescription("Improvement actions to take to improve the quality of unit testing")
	    .setDirection(Metric.DIRECTION_NONE)
	    .setQualitative(false)
	    .setDomain(SEDCAT_DOMAIN)
	    .create();


	@Override
	public List<Metric> getMetrics() {

		return Arrays.<Metric>asList(
				//input metrics
				MUTANTS, COMPLEXITY_AVERAGE_CLASS,

				//output metrics
				QUALITY_MEASURE, QUALITY_MESSAGE, ACTIONS_TO_PERFORM, ACTIONS_MESSAGE

		);

	}
	
}
