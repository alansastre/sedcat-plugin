//++++++++++++++++++++++++++++++++++++++++++++++++++++++//
//                                                      //
// Class:  TP_pruebaCALIDAD2_Cobertura                           //
//                                                      //
// Author: Automatically generated by Xfuzzy            //
//                                                      //
// Description: Type "Cobertura"                      //
//                                                      //
//++++++++++++++++++++++++++++++++++++++++++++++++++++++//

package es.unileon.sonarqube.sedcat.xfuzzy;

public class TP_pruebaCALIDAD2_Cobertura {
 private double min = 0.0;
 private double max = 100.0;
 private double step = 1.0;
 double _ps_Bajo[] = { 0.0,-1.0 };
 double _pl_Bajo[] = {  };
 double _ps_Medio[] = { 40.0,12.0 };
 double _pl_Medio[] = {  };
 double _ps_medioAlto[] = { 60.0,12.0 };
 double _pl_medioAlto[] = {  };
 double _ps_Alto[] = { 70.0,3.4000000000000004 };
 double _pl_Alto[] = {  };
 MF_xfl_sigma Bajo = new MF_xfl_sigma(min,max,step,_ps_Bajo,_pl_Bajo);
 MF_xfl_bell Medio = new MF_xfl_bell(min,max,step,_ps_Medio,_pl_Medio);
 MF_xfl_bell medioAlto = new MF_xfl_bell(min,max,step,_ps_medioAlto,_pl_medioAlto);
 MF_xfl_sigma Alto = new MF_xfl_sigma(min,max,step,_ps_Alto,_pl_Alto);
}


