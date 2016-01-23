//++++++++++++++++++++++++++++++++++++++++++++++++++++++//
//                                                      //
// Class:  TP_calidad_1_Exito                           //
//                                                      //
// Author: Automatically generated by Xfuzzy            //
//                                                      //
// Description: Type "Exito"                      //
//                                                      //
//++++++++++++++++++++++++++++++++++++++++++++++++++++++//

package es.unileon.sonarqube.sedcat.xfuzzy.quality;

public class TP_calidad_1_Exito {
 private double min = 0.0;
 private double max = 100.0;
 private double step = 1.0;
 double _ps_muybajo[] = { -12.5,0.0,5.0,10.0 };
 double _pl_muybajo[] = {  };
 double _ps_bajo[] = { 8.0,15.0,25.0,37.5 };
 double _pl_bajo[] = {  };
 double _ps_medio[] = { 25.0,37.5,62.5,75.0 };
 double _pl_medio[] = {  };
 double _ps_alto[] = { 62.5,75.0,99.0,99.1 };
 double _pl_alto[] = {  };
 double _ps_maximo[] = { 99.0,99.1,99.5,100.9 };
 double _pl_maximo[] = {  };
 MF_xfl_trapezoid muybajo = new MF_xfl_trapezoid(min,max,step,_ps_muybajo,_pl_muybajo);
 MF_xfl_trapezoid bajo = new MF_xfl_trapezoid(min,max,step,_ps_bajo,_pl_bajo);
 MF_xfl_trapezoid medio = new MF_xfl_trapezoid(min,max,step,_ps_medio,_pl_medio);
 MF_xfl_trapezoid alto = new MF_xfl_trapezoid(min,max,step,_ps_alto,_pl_alto);
 MF_xfl_trapezoid maximo = new MF_xfl_trapezoid(min,max,step,_ps_maximo,_pl_maximo);
}


