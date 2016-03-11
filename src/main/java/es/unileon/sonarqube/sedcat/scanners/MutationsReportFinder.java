/**
 * 
 */
package es.unileon.sonarqube.sedcat.scanners;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sonar.api.batch.BatchSide;
import org.sonar.api.internal.apachecommons.io.filefilter.FileFilterUtils;


/**
 *	@author alan.sastre
 *	@version 1.0
 */
@BatchSide
public class MutationsReportFinder{
	
	private static final Logger LOG = LoggerFactory.getLogger(MutationsReportFinder.class);
	
	
	public File findReport(File reportDirectory){
		
		
		//TODO - DIRECTORIOS
//		String[] directories = file.list(new FilenameFilter() {
//			  @Override
//			  public boolean accept(File current, String name) {
//			    return new File(current, name).isDirectory();
//			  }
//			});

		if (reportDirectory== null || !reportDirectory.exists() || !reportDirectory.isDirectory()) {
		    return null;
		}
		Collection<File> reports = FileUtils.listFiles(reportDirectory, null, false);
		
		if (reports!=null) {
			LOG.info("Reportes encontrados: "+reports.size());
		}
				File latestReport = null;
		for (File report : reports) {
			  if (latestReport == null || FileUtils.isFileNewer(report, latestReport)) {
			    latestReport = report;
			  }
		}
		
		File indexReportSearched = null;
		
		Collection<File> indexReport = FileUtils.listFiles(latestReport, new String[]{"html"}, false);
		
		if (indexReport!=null) {
			indexReportSearched= (File) indexReport.toArray()[0];
			LOG.info("Reportes encontrados: "+indexReport.size());
		}
		
		return indexReportSearched;


	}
	
}
