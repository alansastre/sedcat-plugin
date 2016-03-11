/**
 * 
 */
package es.unileon.sonarqube.sedcat.scanners;

import java.io.File;
import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sonar.api.batch.BatchSide;


/**
 *	@author alan.sastre
 *	@version 1.0
 */
@BatchSide
public class MutationsReportParser {

	private static final Logger LOG = LoggerFactory.getLogger(MutationsReportParser.class);


	public double parseReport(File reportPath) throws IOException{
		
		
		Document doc = Jsoup.parse(reportPath, null);
		Elements content = doc.getElementsByTag("td");
		if(content!= null){
			if (content.size()>1) {
				LOG.info("hay mutantes");
				String value = content.get(2).ownText();
				LOG.info("valor mutantes extraido: "+value);
				if(value.length()>0){
					double valueMutation = Double.parseDouble(value.substring(0, value.length()-1));
					return valueMutation;
				}else{
					LOG.info("No hay mutantes, se considera esta media como cero.");
					return 0;
				}
			}else{
				LOG.info("No hay mutantes, se considera esta media como cero.");
				return 0;
			}
		}else{
			LOG.info("No hay mutantes, se considera esta media como cero.");
			return 0;
		}
		
	}
}
