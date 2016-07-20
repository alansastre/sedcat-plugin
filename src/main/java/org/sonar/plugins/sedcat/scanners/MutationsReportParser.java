/**
 * 
 */
package org.sonar.plugins.sedcat.scanners;

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
			LOG.warn("he report of mutants does not exist, this measure is considered as zero");
			return null;
		}

		Document doc = new Document("");

		try {

			doc = Jsoup.parse(reportFile, null);

		} catch (Exception e1) {
			LOG.warn("Failed to parse mutants report");
			LOG.warn(e1.getMessage());
			return null;
		}

		if (!doc.hasText()) {
			LOG.warn("The report mutant has no content, this measure is considered as zero");
			return null;
		}

		Elements content = doc.getElementsByTag("td");

		// tiene que haber al menos 3 td
		if (content == null || !(content.size() > 2)) {
			LOG.warn("The report of mutants does not have the desired results or has changed its structure");
			return null;
		}

		// El tercer td es el que contiene la cobertura de mutantes
		Element td = content.get(2);
		Elements div = td.getElementsByClass("coverage_ledgend");

		if (!(div.size() > 0)) {
			LOG.warn("The report does not have the coverage mutants legend element with the result");
			return null;
		}
		// cogemos el div en el que esta el resultado
		String mutationsResult = div.get(0).ownText();

		if (!(mutationsResult.length() > 0)) {
			LOG.warn("The element coverage_ledgend has no text");
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
