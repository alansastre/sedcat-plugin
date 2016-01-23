//++++++++++++++++++++++++++++++++++++++++++++++++++++++//
//                                                      //
// Class:  TP_calidad_1_CALIDAD                           //
//                                                      //
// Author: Automatically generated by Xfuzzy            //
//                                                      //
// Description: Type "CALIDAD"                      //
//                                                      //
//++++++++++++++++++++++++++++++++++++++++++++++++++++++//

package es.unileon.sonarqube.sedcat.xfuzzy.quality;

public class TP_calidad_1_CALIDAD {
 private double min = 0.0;
 private double max = 100.0;
 private double step = 1.0;
 double _ps_muybajo[] = { -12.5,0.0,5.0,10.0 };
 double _pl_muybajo[] = {  };
 double _ps_bajo[] = { 8.0,15.0,25.0,37.5 };
 double _pl_bajo[] = {  };
 double _ps_medio[] = { 23.23232323,37.37373737,55.55555555,63.63636363 };
 double _pl_medio[] = {  };
 double _ps_medioAlto[] = { 60.60606,75.757575,90.90909,95.0 };
 double _pl_medioAlto[] = {  };
 double _ps_alto[] = { 99.98,99.99,100.0,109.0909090909091 };
 double _pl_alto[] = {  };
 MF_xfl_trapezoid muybajo = new MF_xfl_trapezoid(min,max,step,_ps_muybajo,_pl_muybajo);
 MF_xfl_trapezoid bajo = new MF_xfl_trapezoid(min,max,step,_ps_bajo,_pl_bajo);
 MF_xfl_trapezoid medio = new MF_xfl_trapezoid(min,max,step,_ps_medio,_pl_medio);
 MF_xfl_trapezoid medioAlto = new MF_xfl_trapezoid(min,max,step,_ps_medioAlto,_pl_medioAlto);
 MF_xfl_trapezoid alto = new MF_xfl_trapezoid(min,max,step,_ps_alto,_pl_alto);
}


