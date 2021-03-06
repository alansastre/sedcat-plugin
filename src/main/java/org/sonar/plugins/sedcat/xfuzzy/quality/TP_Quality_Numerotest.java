//++++++++++++++++++++++++++++++++++++++++++++++++++++++//
//                                                      //
// Class:  TP_Quality_Numerotest                           //
//                                                      //
// Author: Automatically generated by Xfuzzy            //
//                                                      //
// Description: Type "Numerotest"                      //
//                                                      //
//++++++++++++++++++++++++++++++++++++++++++++++++++++++//

package org.sonar.plugins.sedcat.xfuzzy.quality;

public class TP_Quality_Numerotest {
 private double min = 0.0;
 private double max = 15000.0;
 private double step = 1.0;
 double _ps_muybajo[] = { -10.0,0.0,25.0,50.01 };
 double _pl_muybajo[] = {  };
 double _ps_bajo[] = { 50.01,100.0,250.0,300.01 };
 double _pl_bajo[] = {  };
 double _ps_bajoMedio[] = { 300.01,400.0,500.0,600.01 };
 double _pl_bajoMedio[] = {  };
 double _ps_medio[] = { 600.01,800.0,1000.0,1100.01 };
 double _pl_medio[] = {  };
 double _ps_medioAlto[] = { 1100.01,2000.0,2500.0,3000.01 };
 double _pl_medioAlto[] = {  };
 double _ps_alto[] = { 3000.01,3887.036,4442.366,5625.01 };
 double _pl_alto[] = {  };
 double _ps_muyAlto[] = { 5625.01,7000.0,12000.0,20000.9 };
 double _pl_muyAlto[] = {  };
 MF_xfl_trapezoid muybajo = new MF_xfl_trapezoid(min,max,step,_ps_muybajo,_pl_muybajo);
 MF_xfl_trapezoid bajo = new MF_xfl_trapezoid(min,max,step,_ps_bajo,_pl_bajo);
 MF_xfl_trapezoid bajoMedio = new MF_xfl_trapezoid(min,max,step,_ps_bajoMedio,_pl_bajoMedio);
 MF_xfl_trapezoid medio = new MF_xfl_trapezoid(min,max,step,_ps_medio,_pl_medio);
 MF_xfl_trapezoid medioAlto = new MF_xfl_trapezoid(min,max,step,_ps_medioAlto,_pl_medioAlto);
 MF_xfl_trapezoid alto = new MF_xfl_trapezoid(min,max,step,_ps_alto,_pl_alto);
 MF_xfl_trapezoid muyAlto = new MF_xfl_trapezoid(min,max,step,_ps_muyAlto,_pl_muyAlto);
}


