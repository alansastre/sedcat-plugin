//++++++++++++++++++++++++++++++++++++++++++++++++++++++//
//                                                      //
// Class:  Conclusion                                   //
//                                                      //
// Author: Automatically generated by Xfuzzy            //
//                                                      //
// Description: Conclusion of a fuzzy rule              //
//                                                      //
//++++++++++++++++++++++++++++++++++++++++++++++++++++++//

package es.unileon.sonarqube.sedcat.xfuzzy;

public class Conclusion {
 private double degree;
 private InputMembershipFunction mf;
 private OperatorSet op;

 public Conclusion(double degree, InputMembershipFunction mf, OperatorSet op) {
  this.op = op;
  this.degree = degree;
  this.mf = mf;
 }

 public double degree() {
  return degree;
 }

 public double compute(double x) {
  return op.imp(degree,mf.isEqual(x));
 }

 public double center() {
  return mf.center();
 }

 public double basis() {
  return mf.basis();
 }

 public double param(int i) {
  return mf.param(i);
 }

 public double min() {
  return mf.min;
 }

 public double max() {
  return mf.max;
 }

 public double step() {
  return mf.step;
 }

 public boolean isSingleton() { 
  return mf.getClass().getName().endsWith("MF_xfl_singleton");
 }
}
