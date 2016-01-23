//++++++++++++++++++++++++++++++++++++++++++++++++++++++//
//                                                      //
// Class:  FamiliarMembershipFunction                   //
//                                                      //
// Author: Automatically generated by Xfuzzy            //
//                                                      //
// Description: Membership function of an input         //
//              variable described as part of a family  //
//                                                      //
//++++++++++++++++++++++++++++++++++++++++++++++++++++++//

package es.unileon.sonarqube.sedcat.xfuzzy.quality;

public class FamiliarMembershipFunction extends InputMembershipFunction {
 double min;
 double max;
 double step;
 MembershipFunctionFamily family;
 int index;

 public FamiliarMembershipFunction(MembershipFunctionFamily fam, int i) {
  this.min = fam.min;
  this.max = fam.max;
  this.step = fam.step;
  this.family = fam;
  this.index = i;
 }

 public double center() {
  return family.center(index);
 }

 public double basis() {
  return family.center(index);
 }

 public double param(int i) {
  return family.param(i);
 }

 public double isEqual(double x) {
  return family.isEqual(index,x);
 }

 public double isSmallerOrEqual(double x) {
  return family.isSmallerOrEqual(index,x);
 }

 public double isGreaterOrEqual(double x) {
  return family.isGreaterOrEqual(index,x);
 }
}
