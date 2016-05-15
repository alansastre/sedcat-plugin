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


	public double[] parseReport(File reportFile) {

		if (reportFile == null || !reportFile.exists() || reportFile.isDirectory()) {
			LOG.warn("El reporte de mutantes no existe, se considera esta medida como cero.");
			return null;
		}

		Document doc = new Document("");

		try {

			doc = Jsoup.parse(reportFile, null);

		} catch (Exception e1) {
			LOG.warn("Error al parsear reporte mutantes");
			LOG.warn(e1.getMessage());
			return null;
		}

		if (!doc.hasText()) {
			LOG.warn("El reporte de mutantes no tiene contenido, se considera esta medida como cero.");
			return null;
		}

		Elements content = doc.getElementsByTag("td");

		// tiene que haber al menos 3 td
		if (content == null || !(content.size() > 2)) {
			LOG.warn("El reporte de mutantes no tiene los resultados buscados o se ha cambiado su disposición");
			return null;
		}

		// El tercer td es el que contiene la cobertura de mutantes
		Element td = content.get(2);
		Elements div = td.getElementsByClass("coverage_ledgend");

		if (!(div.size() > 0)) {
			LOG.warn("El reporte de mutantes no tiene el elemento coverage_ledgend con el resultado");
			return null;
		}
		// cogemos el div en el que esta el resultado
		String mutationsResult = div.get(0).ownText();

		if (!(mutationsResult.length() > 0)) {
			LOG.warn("El elemento coverage_ledgend no tiene texto");
			return null;
		}

		//Se ha encontrado el resultado
		String[] values = mutationsResult.split("/");
		double[] mutantsValues = new double[2];
		mutantsValues[0] = Double.parseDouble(values[0]);
		mutantsValues[1] = Double.parseDouble(values[1]);

		return mutantsValues;

	}

		
}
