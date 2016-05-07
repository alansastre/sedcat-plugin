package es.unileon.sonarqube.sedcat.scanners;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sonar.api.ce.measure.MeasureComputer;
import org.sonar.api.measures.CoreMetrics;
import es.unileon.sonarqube.sedcat.start.SedcatMetricsKeys;

/**
 *  Extrae la complejidad media por clases de la plataforma para el proyecto y 
 *  el umbral de complejidad admitido establecido por el usuario, en caso de no 
 *  ser establecido recibe el umbral por defecto. Calcula la complejidad 
 *  derivada de considerar el umbral. 
 *	@author alan.sastre
 *	@version 1.0
 */
public class ComplexityComputer implements MeasureComputer {

	private static final Logger LOG = LoggerFactory.getLogger(ComplexityComputer.class);
	private double ideal = 15;

	/**
	 * Traduce la complejidad a un valor dentro de las etiquetas linguisticas
	 * de xfuzzy
	 * 
	 * Ideal: 0 - 30
	 * Alto: 30 - 40
	 * XAlto: 40 - 50 
	 * XXAlto: 50 - 60 o +60
	 * 
	 * Cuanto mas lejos por encima esté la complejidad del umbral mayor será la
	 * etiqueta seleccionada y mayor será la influencia sobre la calidad final
	 * 
	 * Caso 1: Si la complejidad está dentro del umbral o es igual se devuelve un valor Ideal
	 * Caso 2: Si la complejidad es mayor al umbral entre 0 y 10 se devuelve un valor Alto
	 * Caso 3: Si la complejidad es mayor al umbral entre 10 y 20 se devuelve un valor XAlto
	 * Caso 4: Si la complejidad es mayor al umbral entre 20 y 30 se devuelve un valor XXAlto
	 * Caso 5: Si la complejidad es mayor al umbral en más de 30 se devuelve un valor XXAlto
	 */
	@Override
	public void compute(MeasureComputerContext context) {

		if (("PROJECT").equalsIgnoreCase(context.getComponent().getType().toString())) {
			
			LOG.info("Entrada ComplexityComputer");
			
			double complexity = context.getMeasure(CoreMetrics.CLASS_COMPLEXITY_KEY).getDoubleValue();
			double complexityThresold = context.getMeasure(SedcatMetricsKeys.COMPLEXITY_THRESOLD_KEY).getDoubleValue();
			
				LOG.info("Calculating complexity based on threshold");
				
				//Establecer umbral dentro de los limites
				if (complexityThresold > 60) {
					complexityThresold = 60;
					LOG.warn("Suggested complexity threshold is greater"
							+ " than permitted. The value considered in this case is 60");
				}else if(complexityThresold < 0){
					LOG.warn("Suggested complexity threshold " + complexityThresold
							+ " is less than 0, it will be considered default value (30)");
					complexityThresold = 30;
				}
				
				//Adaptar complejidad dentro de los limites
				if(complexity > 60){
					complexity = 60;
				}
				
				//Calcular complejidad en funcion de umbral
				if(complexity <= complexityThresold){
					//Caso 1
					complexity = ideal;
				}else{
					//Resto de casos
					complexity = (complexity - complexityThresold) + 30;
				}

				LOG.info("complexity: "+complexity);
				LOG.info("complexityThresold: "+complexityThresold);
				
				context.addMeasure(SedcatMetricsKeys.COMPLEXITY_CLASS_KEY, complexity);

		} 

	}

	/**
	 * Se define que lee la complejidad media por clases de la api sonar y 
	 * almacena el resultado de operar con el umbral en una metrica complejidad particular
	 */
	@Override
	public MeasureComputerDefinition define(MeasureComputerDefinitionContext defContext) {

		return defContext.newDefinitionBuilder()

				// Input metrics can be empty, for instance if only issues will
				// be read
				.setInputMetrics(CoreMetrics.CLASS_COMPLEXITY_KEY,
						SedcatMetricsKeys.COMPLEXITY_THRESOLD_KEY)

				// Output metrics must contains at least one metric
				.setOutputMetrics(SedcatMetricsKeys.COMPLEXITY_CLASS_KEY)

				.build();

	}

	@Override
	public String toString() {
		return getClass().getSimpleName();
	}
	
}
