/**
 * 
 */
package es.unileon.sonarqube.sedcat.scanners;

import java.io.File;
import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sonar.api.batch.BatchSide;


/**
 *  Clase que parsea un html y encuentra el numero total de mutantes y el numero de mutantes detectados
 *	@author alan.sastre
 *	@version 1.0
 */
@BatchSide
public class MutationsReportParser {

	private static final Logger LOG = LoggerFactory.getLogger(MutationsReportParser.class);


	public double[] parseReport(File reportPath) {
		
		
		Document doc = new Document("");
		try {
			doc = Jsoup.parse(reportPath, null);
		} catch (IOException e1) {

			e1.printStackTrace();
		}
		Elements content = doc.getElementsByTag("td");

		if(content!= null){
			//tiene que haber al menos 3 td 
			if (content.size()>2) {
				LOG.info("hay mutantes");

				//El tercer td es el que contiene la cobertura de mutantes
				Element td = content.get(2);
				Elements div = td.getElementsByClass("coverage_ledgend");
				if (div.size()>0) {
					//cogemos el div en el que esta el resultado
					String mutationsResult = div.get(0).ownText();
					if (mutationsResult.length()>0) {
						
						String[] values = mutationsResult.split("/");
						double[] mutantsValues = new double[2];
						mutantsValues[0] = Double.parseDouble(values[0]);
						mutantsValues[1] = Double.parseDouble(values[1]);
						
						return mutantsValues;
					
					}else{
						LOG.info("No hay mutantes, se considera esta medida como cero.");
						return null;
					}
				}else{
					LOG.info("No hay mutantes, se considera esta medida como cero.");
					return null;
				}
			}else{
				LOG.info("No hay mutantes, se considera esta medida como cero.");
				return null;
			}
		}else{
			LOG.info("No hay mutantes, se considera esta medida como cero.");
			return null;
		}

		
	}

		
}
