/**
 * 
 */
package es.unileon.sonarqube.sedcat.scanners;

import java.io.File;
import java.util.Collection;

import org.apache.commons.io.FileUtils;
import org.sonar.api.batch.BatchSide;


/**
 *	@author alan.sastre
 *	@version 1.0
 */
@BatchSide
public class MutationsReportFinder{
	

	protected File findReport(File reportDirectory){
		
	    if (reportDirectory== null || !reportDirectory.exists() || !reportDirectory.isDirectory()) {
	        return null;
	      }
	      Collection<File> reports = FileUtils.listFiles(reportDirectory, new String[]{"html"}, true);
	      File latestReport = null;
	      for (File report : reports) {
	        if (latestReport == null || FileUtils.isFileNewer(report, latestReport)) {
	          latestReport = report;
	        }
	      }
	      return latestReport;

	}
	
}
