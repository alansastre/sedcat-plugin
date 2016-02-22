/**
 * 
 */
package es.unileon.sonarqube.sedcat.scanners;

import java.io.File;
import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.sonar.api.batch.BatchSide;


/**
 *	@author alan.sastre
 *	@version 1.0
 */
@BatchSide
public class MutationsReportParser {


	protected double parseReport(File reportPath) throws IOException{
		
		
		Document doc = Jsoup.parse(reportPath, null);
		Elements content = doc.getElementsByTag("td");
		String value = content.get(2).ownText();
		double valueMutation = Double.parseDouble(value.substring(0, value.length()-1));
		return valueMutation;
		
	}
}
