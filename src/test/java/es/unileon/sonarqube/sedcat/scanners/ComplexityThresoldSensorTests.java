package es.unileon.sonarqube.sedcat.scanners;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.sonar.api.batch.SensorContext;
import org.sonar.api.config.Settings;
import org.sonar.api.resources.Project;
import es.unileon.sonarqube.sedcat.start.SedcatConstants;
import es.unileon.sonarqube.sedcat.start.SedcatMetrics;

/**
 *	@author alan.sastre
 *	@version 1.0
 */
public class ComplexityThresoldSensorTests {

	private Settings settings;
	private Project mockedProject;
	private SensorContext mockedcontext;
	private ComplexityThresoldSensor underTest;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		
		settings = mock(Settings.class);
		mockedProject = mock(Project.class);
		mockedcontext = mock(SensorContext.class);
		
		underTest = new ComplexityThresoldSensor(settings);
		
	}

	/**
	 * Test method for {@link es.unileon.sonarqube.sedcat.scanners.ComplexityThresoldSensor#analyse(org.sonar.api.resources.Project, org.sonar.api.batch.SensorContext)}.
	 */
	@Test
	public final void testAnalyse() {

		when(settings.getString(SedcatConstants.COMPLEXITY_THRESHOLD_KEY)).thenReturn("50");

		underTest.analyse(mockedProject, mockedcontext);

		Mockito.verify(mockedcontext, times(1)).saveMeasure(SedcatMetrics.COMPLEXITY_THRESHOLD, 50.0);
	}
	
	/**
	 * Test method for {@link es.unileon.sonarqube.sedcat.scanners.ComplexityThresoldSensor#analyse(org.sonar.api.resources.Project, org.sonar.api.batch.SensorContext)}.
	 */
	@Test
	public final void testAnalyseException() {

		when(settings.getString(SedcatConstants.COMPLEXITY_THRESHOLD_KEY)).thenReturn("prueba");

		underTest.analyse(mockedProject, mockedcontext);

		Mockito.verify(mockedcontext, times(1)).saveMeasure(SedcatMetrics.COMPLEXITY_THRESHOLD, 30.0);
	}

	
	/**
	 * Test method for {@link es.unileon.sonarqube.sedcat.scanners.ComplexityThresoldSensor#analyse(org.sonar.api.resources.Project, org.sonar.api.batch.SensorContext)}.
	 */
	@Test
	public final void testAnalyseGreatherThan60() {

		when(settings.getString(SedcatConstants.COMPLEXITY_THRESHOLD_KEY)).thenReturn("70.0");

		underTest.analyse(mockedProject, mockedcontext);

		Mockito.verify(mockedcontext, times(1)).saveMeasure(SedcatMetrics.COMPLEXITY_THRESHOLD, 60.0);
	}
	
	/**
	 * Test method for {@link es.unileon.sonarqube.sedcat.scanners.ComplexityThresoldSensor#analyse(org.sonar.api.resources.Project, org.sonar.api.batch.SensorContext)}.
	 */
	@Test
	public final void testAnalyseEquals60() {

		when(settings.getString(SedcatConstants.COMPLEXITY_THRESHOLD_KEY)).thenReturn("60.0");

		underTest.analyse(mockedProject, mockedcontext);

		Mockito.verify(mockedcontext, times(1)).saveMeasure(SedcatMetrics.COMPLEXITY_THRESHOLD, 60.0);
	}
	
	/**
	 * Test method for {@link es.unileon.sonarqube.sedcat.scanners.ComplexityThresoldSensor#analyse(org.sonar.api.resources.Project, org.sonar.api.batch.SensorContext)}.
	 */
	@Test
	public final void testAnalyseLessThan0() {

		when(settings.getString(SedcatConstants.COMPLEXITY_THRESHOLD_KEY)).thenReturn("-50.0");

		underTest.analyse(mockedProject, mockedcontext);

		Mockito.verify(mockedcontext, times(1)).saveMeasure(SedcatMetrics.COMPLEXITY_THRESHOLD, 30.0);
	}
	
}
