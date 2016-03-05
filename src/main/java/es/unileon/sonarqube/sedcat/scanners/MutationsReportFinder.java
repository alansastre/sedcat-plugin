/**
 * 
 */
package es.unileon.sonarqube.sedcat.scanners;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Collection;

import org.apache.commons.io.FileUtils;
import org.sonar.api.batch.BatchSide;
import org.sonar.api.internal.apachecommons.io.filefilter.FileFilterUtils;


/**
 *	@author alan.sastre
 *	@version 1.0
 */
@BatchSide
public class MutationsReportFinder{
	

	public File findReport(File reportDirectory){
		
		if (reportDirectory== null || !reportDirectory.exists() || !reportDirectory.isDirectory()) {
		    return null;
		}
//		Collection<File> reports = FileUtils.listFiles(reportDirectory, new String[]{"index.html"}, true);
		//TODO - arreglar
		File[] reports = reportDirectory.listFiles(new FilenameFilter() {
		    public boolean accept(File dir, String name) {
		        return name.startsWith("index") && name.endsWith("html");
		    }
		});

		File latestReport = null;
		for (File report : reports) {
		  if (latestReport == null || FileUtils.isFileNewer(report, latestReport)) {
		    latestReport = report;
		  }
		}
		return latestReport;

	}
	
}
