//package com.unileon.sonarqube.sedcat.start;
//
//import org.junit.Test;
//import org.sonar.api.ce.measure.MeasureComputer.MeasureComputerDefinition;
//import org.sonar.api.ce.measure.test.TestMeasureComputerContext;
//import org.sonar.api.ce.measure.test.TestMeasureComputerDefinitionContext;
//
//import es.unileon.sonarqube.sedcat.start.MyMeasureComputer;
//import junit.framework.Assert;
//import junit.framework.TestCase;
//
//import static org.hamcrest.Matchers.empty;
//import static org.hamcrest.Matchers.is;
//
////import static org.junit.Assert.assertThat;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
////import java.util.List;
////import java.util.Set;
//import org.sonar.api.ExtensionPoint;
//import org.sonar.api.ce.measure.MeasureComputer.MeasureComputerDefinition.Builder;
//import org.sonar.api.server.ServerSide;
//
//
//import java.util.Arrays;
//
///**
// *	@author alan.sastre
// *	@version 1.0
// */
//public class MyMeasureComputerTest extends TestCase {
//
//	
//	  MyMeasureComputer underTest = new MyMeasureComputer();
//	/**
//	 * @param name
//	 */
//	public MyMeasureComputerTest(String name) {
//		super(name);
//	}
//
//	/* (non-Javadoc)
//	 * @see junit.framework.TestCase#setUp()
//	 */
//	protected void setUp() throws Exception {
//		super.setUp();
//	}
//
//	/* (non-Javadoc)
//	 * @see junit.framework.TestCase#tearDown()
//	 */
//	protected void tearDown() throws Exception {
//		super.tearDown();
//	}
//	
//	 @Test
//	   public void test_definition() {
//	     TestMeasureComputerDefinitionContext defContext = new TestMeasureComputerDefinitionContext();
//	     MeasureComputerDefinition def = underTest.define(defContext);
//	     Assert.assertThat(def).isNotNull();
//	     assertThat(def.getInputMetrics()).containsOnly("ncloc");
//	     assertThat(def.getOutputMetrics()).containsOnly("my_new_metric");
//	   }
//
//	   @Test
//	   public void sum_ncloc_and_issues() {
//	     TestMeasureComputerContext context = new TestMeasureComputerContext(underTest);
//	     context.addMeasure("ncloc", 2);
//	     context.setIssues(Arrays.asList(new TestIssue.Builder().setKey("ABCD").build()));
//	     underTest.compute(context);
//
//	     assertThat(context.getMeasureValue("my_new_metric")).isEqualTo(0.5);
//	   }
//	   
//
//}
