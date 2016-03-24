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
		
		LOG.info("target pit reports path: "+reportDirectory.getAbsolutePath());
		
		if (reportDirectory== null || !reportDirectory.exists() || !reportDirectory.isDirectory()) {
			LOG.info("Does not exists directory: " +reportDirectory.getAbsolutePath());
		    return null;
		}

		String[] directoriesPaths = reportDirectory.list(new FilenameFilter() {
			  public boolean accept(File current, String name) {
			    return new File(current, name).isDirectory();
			  }
			});
		LOG.info("total report directories: "+directoriesPaths.length);
		
		if(directoriesPaths.length==0){
			return null;
		}
		for (int i = 0; i < directoriesPaths.length; i++) {
			LOG.info("directory : "+directoriesPaths[i].toString());
		}
		ArrayList<File> directories  = new ArrayList<File>();
		for (int i = 0; i < directoriesPaths.length; i++) {
			//Unimos la ruta del directorio de reportes mas cada uno de los directorioss
			directories.add(new File(reportDirectory.getAbsolutePath()+ "/" + directoriesPaths[i]));
		}

		File latestReport = null;
		for (File report : directories) {
			if (latestReport == null || report.lastModified() > latestReport.lastModified()) {
			    latestReport = report;
			  }
		}
		LOG.info("latest directory: "+latestReport.getAbsolutePath());
		
		File indexReportSearched = null;
		String indexReportSearchedPath = latestReport.getAbsolutePath()+"/";
		File indexReportDirectory = new File(indexReportSearchedPath);
		
		if(indexReportDirectory.list().length>0){
			
			Collection<File> indexReport = FileUtils.listFiles(indexReportDirectory, new String[]{"html"}, false);
			
			if (indexReport!=null && !indexReport.isEmpty()) {
				indexReportSearched= (File) indexReport.toArray()[0];
			}
		}
		
		return indexReportSearched;
	}
	
}
