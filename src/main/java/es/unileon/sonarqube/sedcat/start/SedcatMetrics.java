package es.unileon.sonarqube.sedcat.start;

import static es.unileon.sonarqube.sedcat.start.SedcatMetricsKeys.*;
import java.util.Arrays;
import java.util.List;
import org.sonar.api.measures.CoreMetrics;
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
	
	//Metrica COMPLEJIDAD UMBRAL
	public static final Metric COMPLEXITY_THRESHOLD = new Metric.Builder(COMPLEXITY_THRESHOLD_KEY, "Complexity Threshold", Metric.ValueType.FLOAT)
		    .setDescription("Maximum allowed threshold of complexity average by class")
		    .setDirection(Metric.DIRECTION_NONE)
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
		//ACCIONES RECOMENDADAS
	public static final Metric<String> ACTIONS_TO_PERFORM = new Metric.Builder(ACTIONS_TO_PERFORM_KEY, "Improvement actions", Metric.ValueType.STRING)
		    .setDescription("Improvement actions to take to improve the quality of unit testing")
		    .setDirection(Metric.DIRECTION_NONE)
		    .setQualitative(false)
		    .setDomain(SEDCAT_DOMAIN)
		    .create();
	
	
	
	//METRICAS DE PRUEBA
	public static final Metric<String> MESSAGE = new Metric.Builder(MESSAGE_KEY_1, "mensaje1", Metric.ValueType.STRING)
		    .setDescription("This is a metric to store a well known message")
		    .setDirection(Metric.DIRECTION_WORST)
		    .setQualitative(false)
		    .setDomain(CoreMetrics.DOMAIN_GENERAL)
		    .create();
	
	public static final Metric<String> MESSAGE_2 = new Metric.Builder(MESSAGE_KEY_2, "mensaje2", Metric.ValueType.STRING)
		    .setDescription("This is a metric to store a well known message")
		    .setDirection(Metric.DIRECTION_WORST)
		    .setQualitative(false)
		    .setDomain(CoreMetrics.DOMAIN_GENERAL)
		    .create();
	
	//Metrica CODELINES - computers
	public static final Metric CODE_LINES_COMPUTERS = new Metric.Builder(LINESOFCODE_COMPUTER, "Numero de lineas de codigo computadas", Metric.ValueType.INT)
		    .setDescription("Numero total de lineas de codigo en el proyecto (sin contar los test) computadas")
		    .setDirection(Metric.DIRECTION_BETTER)
		    .setQualitative(false)
		    .setDomain(CoreMetrics.DOMAIN_GENERAL)
		    .create();
	//Metrica numbertests - computers
	public static final Metric NUMBERTESTS_COMPUTERS = new Metric.Builder(NUMBERTESTS_COMPUTER, "Numero de tests computados", Metric.ValueType.INT)
		    .setDescription("Numero total de tests en el proyecto ")
		    .setDirection(Metric.DIRECTION_BETTER)
		    .setQualitative(false)
		    .setDomain(CoreMetrics.DOMAIN_GENERAL)
		    .create();
	//Metrica general - computers
	public static final Metric GENERAL_RESULT_COMPUTERS = new Metric.Builder(GENERAL_COMPUTER_RESULT, "Numero de tests computados", Metric.ValueType.INT)
		    .setDescription("Numero total de tests en el proyecto ")
		    .setDirection(Metric.DIRECTION_BETTER)
		    .setQualitative(false)
		    .setDomain(CoreMetrics.DOMAIN_GENERAL)
		    .create();
	
	// getMetrics() method is defined in the Metrics interface and is used by
	@Override
	public List<Metric> getMetrics() {

		return Arrays.<Metric>asList(

				MUTANTS, COMPLEXITY_AVERAGE_CLASS, COMPLEXITY_THRESHOLD,

				QUALITY_MEASURE, ACTIONS_TO_PERFORM

		);

	}
	
}
