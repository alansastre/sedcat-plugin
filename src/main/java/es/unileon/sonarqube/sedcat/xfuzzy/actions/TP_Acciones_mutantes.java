//++++++++++++++++++++++++++++++++++++++++++++++++++++++//
//                                                      //
// Class:  TP_Acciones_mutantes                           //
//                                                      //
// Author: Automatically generated by Xfuzzy            //
//                                                      //
// Description: Type "mutantes"                      //
//                                                      //
//++++++++++++++++++++++++++++++++++++++++++++++++++++++//

package es.unileon.sonarqube.sedcat.xfuzzy.actions;

public class TP_Acciones_mutantes {
 private double min = 0.0;
 private double max = 100.0;
 private double step = 1.0;
 double _ps_bajo[] = { -12.5,0.0,25.0,35.01 };
 double _pl_bajo[] = {  };
 double _ps_medio[] = { 35.01,45.0,55.0,70.01 };
 double _pl_medio[] = {  };
 double _ps_alto[] = { 70.01,80.0,99.0,99.11 };
 double _pl_alto[] = {  };
 double _ps_maximo[] = { 99.11,99.3,99.5,100.9 };
 double _pl_maximo[] = {  };
 MF_xfl_trapezoid bajo = new MF_xfl_trapezoid(min,max,step,_ps_bajo,_pl_bajo);
 MF_xfl_trapezoid medio = new MF_xfl_trapezoid(min,max,step,_ps_medio,_pl_medio);
 MF_xfl_trapezoid alto = new MF_xfl_trapezoid(min,max,step,_ps_alto,_pl_alto);
 MF_xfl_trapezoid maximo = new MF_xfl_trapezoid(min,max,step,_ps_maximo,_pl_maximo);
}


