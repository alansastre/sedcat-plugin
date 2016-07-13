//++++++++++++++++++++++++++++++++++++++++++++++++++++++//
//                                                      //
// Class:  TP_Quality_NumeroLineasCodigo                           //
//                                                      //
// Author: Automatically generated by Xfuzzy            //
//                                                      //
// Description: Type "NumeroLineasCodigo"                      //
//                                                      //
//++++++++++++++++++++++++++++++++++++++++++++++++++++++//

package org.sonar.plugins.sedcat.xfuzzy.quality;

public class TP_Quality_NumeroLineasCodigo {
 private double min = 0.0;
 private double max = 5000000.0;
 private double step = 1.0;
 double _ps_muybajo[] = { -1000.0,0.0,500.0,1000.01 };
 double _pl_muybajo[] = {  };
 double _ps_bajo[] = { 1000.01,1500.0,2000.0,3000.01 };
 double _pl_bajo[] = {  };
 double _ps_bajoMedio[] = { 3000.01,5000.0,7000.0,8000.01 };
 double _pl_bajoMedio[] = {  };
 double _ps_medio[] = { 8000.01,12000.0,15000.0,18000.01 };
 double _pl_medio[] = {  };
 double _ps_medioAlto[] = { 18000.01,30000.0,40000.0,50000.01 };
 double _pl_medioAlto[] = {  };
 double _ps_alto[] = { 50000.01,100000.0,150000.0,200000.01 };
 double _pl_alto[] = {  };
 double _ps_muyAlto[] = { 200000.01,500000.0,1000000.0,50000000.01 };
 double _pl_muyAlto[] = {  };
 MF_xfl_trapezoid muybajo = new MF_xfl_trapezoid(min,max,step,_ps_muybajo,_pl_muybajo);
 MF_xfl_trapezoid bajo = new MF_xfl_trapezoid(min,max,step,_ps_bajo,_pl_bajo);
 MF_xfl_trapezoid bajoMedio = new MF_xfl_trapezoid(min,max,step,_ps_bajoMedio,_pl_bajoMedio);
 MF_xfl_trapezoid medio = new MF_xfl_trapezoid(min,max,step,_ps_medio,_pl_medio);
 MF_xfl_trapezoid medioAlto = new MF_xfl_trapezoid(min,max,step,_ps_medioAlto,_pl_medioAlto);
 MF_xfl_trapezoid alto = new MF_xfl_trapezoid(min,max,step,_ps_alto,_pl_alto);
 MF_xfl_trapezoid muyAlto = new MF_xfl_trapezoid(min,max,step,_ps_muyAlto,_pl_muyAlto);
}

