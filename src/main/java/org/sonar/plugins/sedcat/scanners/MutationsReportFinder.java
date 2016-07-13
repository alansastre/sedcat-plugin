package org.sonar.plugins.sedcat.scanners;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.Collection;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sonar.api.batch.BatchSide;

/**
 *	@author alan.sastre
 *	@version 1.0
 */
@BatchSide
public class MutationsReportFinder{
	
	private static final Logger LOG = LoggerFactory.getLogger(MutationsReportFinder.class);

	public File findReport(File reportDirectory){
				
		if (reportDirectory == null || !reportDirectory.exists() || !reportDirectory.isDirectory()) {
			LOG.warn("Not found the directory specified in the Pitest Report Directory configuration");
		    return null;
		}
		
		LOG.info("target pit reports path has been found: "+reportDirectory.getAbsolutePath());

		String[] directoriesPaths = reportDirectory.list(new FilenameFilter() {
			@Override
			  public boolean accept(File current, String name) {
			    return new File(current, name).isDirectory();
			  }
		});

		if(directoriesPaths == null || directoriesPaths.length==0){
			LOG.warn("Pitest Report Directory is empty");
			return null;
		}
		
		LOG.info("total report directories: "+directoriesPaths.length);
		
		for (int i = 0; i < directoriesPaths.length; i++) {
			LOG.info("directory : "+directoriesPaths[i]);
		}
		
		ArrayList<File> directories  = new ArrayList<>();
		
		for (int i = 0; i < directoriesPaths.length; i++) {
			//Creamos rutas a cada directorio de reportes
			directories.add(new File(reportDirectory.getAbsolutePath()+ "/" + directoriesPaths[i]));
		}

		//Buscamos el ultimo directorio con reportes
		File latestReportDirectory = null;
		for (File directoryToExamine : directories) {
			if (latestReportDirectory == null || directoryToExamine.lastModified() > latestReportDirectory.lastModified()) {
			    latestReportDirectory = directoryToExamine;
			  }
		}
		if (latestReportDirectory == null) {
			return null;
		}
		
		LOG.info("latest directory: "+latestReportDirectory.getAbsolutePath());
		
		//Extraemos el archivo index.html del directorio (es el que contiene los reportes totales del modulo)
		File indexReportSearched = null;
		String indexReportSearchedPath = latestReportDirectory.getAbsolutePath()+"/";
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
