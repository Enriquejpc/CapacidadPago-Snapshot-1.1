/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.beneficio;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;

import models.BeneficiosBean;
import models.SolicitudBean;
import util.FuncionesUtiles;

/**
 *
 * @author b586854
 */
public class ProyeccionBNO {

    SolicitudBean _accesoSolicitud = new SolicitudBean();

    public boolean ProyectarBeneficio(ArrayList<BeneficiosBean> _matrixBeneficio, int _IndicEscenario, int _MaxBNO){
        int indice = 0;
        int criterio = 0;
        boolean _exito = false;
       
        double _BNOPermitido = (_MaxBNO);// Maximo permitido por el BCV
        double _BNOProyectado = 0;//El BNO proyectado el esperado para los años futuros
        double _BNOCalculado = 0;//new BigDecimal(BigInteger.ONE);;

        double _GastosCalculado = 0;
        double _CostosCalculado = 0;
        
        String anio[] = _accesoSolicitud.getFechaEstLiq().split("/");
        /**
         * Escenario 1 - Conservador Solo ubica el valor menor en el %BNO
         * (Vaciado) y lo toma como posicion indice, es decir, para gastos y
         * costos va a tomar el valor que este en la columna indice
         */
        if (_IndicEscenario == 1) {
            Collections.sort(_matrixBeneficio);
           
            _exito = GenerarArchivoSalidaConservador(_matrixBeneficio, indice, anio[2]);
        }
        /**
         * Escenario 2 - Moderado
         * Revisar los pasos en los comentarios
         */
        if (_IndicEscenario == 2) {
            boolean indicadorCorte = false;
            //1) Busco si el vaciado tiene corte
            for (int i = 0; i < _matrixBeneficio.size(); i++) {
                
                if (_matrixBeneficio.get(i).getTipo().compareToIgnoreCase("Corte") == 0) {
                    indicadorCorte = true;
                    }
                }//fin del for
                // 2) Como el vaciado tiene cortes debo en todo momento evaluarlos
                if (indicadorCorte == true) {
                    // 3) Calculo el %BNO Proyectado y le aplico las condiciones.
                    _BNOCalculado = BNOproyModerado(_matrixBeneficio, indicadorCorte);
                   criterio = (CompareDouble(_BNOCalculado, _BNOPermitido));
                    if (criterio == 1) {//1 - Indica que el calculado es mayor al permitido
                        _BNOProyectado = _BNOPermitido;
                    } else {
                        _BNOProyectado = _BNOCalculado;
                    }

                }
                // 4)Calculamos el %Gasto proyectado ()
                _GastosCalculado = GastosproyModerado(_matrixBeneficio, indicadorCorte, _BNOProyectado, _BNOPermitido);
                // 5)Calculamos el %Costo proyectado ()
                _CostosCalculado = CostosproyModerado(_matrixBeneficio, indicadorCorte, _BNOProyectado, _BNOPermitido);
                System.out.println("BNOproy"+_BNOProyectado+"Gas calculado"+_GastosCalculado+ "Cos Pro"+_CostosCalculado);
                //6) Generamos el Archivo de salida con los valores
               _exito = GenerarArchivoSalidaModerado(_BNOProyectado, _GastosCalculado, _CostosCalculado, indice, anio[2]);
        }
        return _exito;
    }

    public boolean GenerarArchivoSalidaConservador(ArrayList<BeneficiosBean> _matrixBeneficio, int indice, String Anio) {
        File f;
        FileWriter w;
        BufferedWriter bw = null;
        PrintWriter wr = null;
        boolean _retorno = false;
        String Cabecera = "Conceptos";
        String Tabulacion = "        \t";
        /**
         * Se anualiza el plazo
         */
        int NroAniosProy = (_accesoSolicitud.getPlazoMeses().divide(new BigDecimal("12"))).intValue();
        ArrayList<String> Conceptos = new ArrayList<>();

        Conceptos.add("%Costo");
        Conceptos.add("%Gasto");
        Conceptos.add("%Beneficio");
        /**
         * Estructuración del Archivo
         */
        try {
            f = new File("C:\\BM_HOME\\appl\\MACE\\salida\\" + "PBNOConservador.txt");
            w = new FileWriter(f);
            bw = new BufferedWriter(w);
            wr = new PrintWriter(bw);
            wr.write("Proyeccion del Beneficio. - Escenario Conservador");
            wr.append("\nLa tasa (Utilizar 24%)");
            wr.append("\nPlazo:" + _accesoSolicitud.getPlazoMeses());
            wr.append("\n ");
            wr.append(Cabecera + "\t\t");
            for (int columna = 0; columna < NroAniosProy; columna++) {
                wr.append((Integer.parseInt(Anio) + columna) + "E\t");
            }

            wr.append("\n" + Conceptos.get(0) );
            wr.append(Tabulacion);
            for (int columna = 0; columna < NroAniosProy; columna++) {
                wr.append((_matrixBeneficio.get(indice).getPorcCostos()).toString().replace(".", ",") + "%\t");
            }
            wr.append("\n" + Conceptos.get(1));
            wr.append(Tabulacion);
            for (int columna = 0; columna < NroAniosProy; columna++) {
                wr.append((_matrixBeneficio.get(indice).getPorcGasto()).toString().replace(".", ",") + "%\t");
            }
            wr.append("\n" + Conceptos.get(2));
            wr.append("   \t");
            for (int columna = 0; columna < NroAniosProy; columna++) {
                wr.append((_matrixBeneficio.get(indice).getBnoVentas()).toString().replace(".", ",") + "%\t");
            }
            _retorno = true;

        } catch (Exception e) {
            /// JOptionPane.showMessageDialog(null, "Ha ocurrido un error al imprimir" + e);
            e.printStackTrace();
        } finally {
            wr.close();
            try {
                bw.close();
            } catch (IOException ex) {
                Logger.getLogger(FuncionesUtiles.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return _retorno;
    }

    public boolean GenerarArchivoSalidaModerado(double _BNOproyectado, double _GastosProyectado, double _CostosProyectado, int indice, String Anio) {
        File f;
        FileWriter w;
        BufferedWriter bw = null;
        PrintWriter wr = null;
        boolean _retorno = false;
        String Cabecera = "Conceptos";
        String Tabulacion = "        \t";
        _BNOproyectado = round(_BNOproyectado, 2);
        /**
         * Se anualiza el plazo
         */
        int NroAniosProy = (_accesoSolicitud.getPlazoMeses().divide(new BigDecimal("12"))).intValue();
        ArrayList<String> Conceptos = new ArrayList<>();

        Conceptos.add("%Costo");
        Conceptos.add("%Gasto");
        Conceptos.add("%Beneficio");
        /**
         * Estructuración del Archivo
         */
        try {
            f = new File("C:\\BM_HOME\\appl\\MACE\\salida\\" + "PBNOModerado.txt");
            w = new FileWriter(f);
            bw = new BufferedWriter(w);
            wr = new PrintWriter(bw);
            wr.write("Proyeccion del Beneficio. - Escenario Conservador");
            wr.append("\nLa tasa (Utilizar 24%)");
            wr.append("\nPlazo:" + _accesoSolicitud.getPlazoMeses());
            wr.append("\n ");
            wr.append(Cabecera + "\t\t");
             for (int columna = 0; columna < NroAniosProy; columna++) {
                wr.append((Integer.parseInt(Anio) + columna) + "E\t");
            }
          
          wr.append("\n" + Conceptos.get(0));
            wr.append(Tabulacion);
            for (int columna = 0; columna < NroAniosProy; columna++) {
                wr.append(String.valueOf(_CostosProyectado).replace(".", ",") + "%\t");
            }
            wr.append("\n" + Conceptos.get(1));
            wr.append(Tabulacion);
            for (int columna = 0; columna < NroAniosProy; columna++) {
                 wr.append(String.valueOf(_GastosProyectado).replace(".", ",") + "%\t");
            }
            wr.append("\n" + Conceptos.get(2));
            wr.append("   \t");
            for (int columna = 0; columna < NroAniosProy; columna++) {
                 wr.append(String.valueOf(_BNOproyectado).replace(".", ",") + "%\t");
            }
           
            _retorno = true;

        } catch (Exception e) {
            /// JOptionPane.showMessageDialog(null, "Ha ocurrido un error al imprimir" + e);
            e.printStackTrace();
        } finally {
            wr.close();
            try {
                bw.close();
            } catch (IOException ex) {
                Logger.getLogger(FuncionesUtiles.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return _retorno;
    }

    public double BNOproyModerado(ArrayList<BeneficiosBean> _matrixBeneficio, boolean _indicCorte) {
        //Para los valores porcentauales se utilizan  como tipo de dato DOUBLE
        String ValorFinalCalculado = "0";
        double _BNOproy = 1;
        double _Sumatoria = 0;

        if (_indicCorte == true) {// Indica que el vaciado tiene cortes
            for (int i = 0; i < _matrixBeneficio.size(); i++) {
              _Sumatoria = _Sumatoria + _matrixBeneficio.get(i).getBnoVentas().doubleValue();
            }
            _BNOproy = _Sumatoria / _matrixBeneficio.size();
            //_BNOproy = (_Sumatoria.divide(new BigDecimal(_matrixBeneficio.size()), 2, RoundingMode.HALF_UP)); // Calculo el promedio

        }
        _BNOproy = round(_BNOproy, 2);
        
        return (_BNOproy);
    }

    public double GastosproyModerado(ArrayList<BeneficiosBean> _matrixBeneficio, boolean _indicCorte, double _BNOProy, double _BNOPermitido) throws NumberFormatException{
        int _Criterio = CompareDouble(_BNOProy, _BNOPermitido);
       
        double TotalPorcentaje = 100; //new BigDecimal("100");
        double porcGastoCorte;// = new BigDecimal(BigInteger.ONE);
        double BNOCorte;//= new BigDecimal(BigInteger.ONE);
        double Distribucionponderada = 0;// = new BigDecimal(BigInteger.ONE);
        double Promedioponderado = 0;// = new BigDecimal(BigInteger.ONE);
        double porcGastoProy = 0;// = new BigDecimal(BigInteger.ONE);
        double _Sumatoria = 0;
        double _PromedioCierres = 0;

        
        //Obtenemos el valor del corte
        BNOCorte = _matrixBeneficio.get(_matrixBeneficio.size() - 1).getBnoVentas().doubleValue();
        //1) Verificamos si el BNO Proyectado es igual al BNO permitido
        if (_Criterio == 0) {
             //2) Debemos Calcular la Distribución ponderada Utilizando el %Gasto del Corte (Vaciado)
            //porcGastoCorte = _matrixBeneficio.get(_matrixBeneficio.size()-1).getPorcGasto();// Se realiza size - 1, porque el vaciado esta ordenado de manera que el corte es el ultimo
            porcGastoCorte = _matrixBeneficio.get(_matrixBeneficio.size() - 1).getPorcGasto().doubleValue();
            
            Distribucionponderada = (porcGastoCorte / (TotalPorcentaje - (BNOCorte))) * (TotalPorcentaje);


            //3)Calculamos el promedio ponderado
            Promedioponderado = TotalPorcentaje - _BNOProy;//TotalPorcentaje.subtract(_BNOProy);

            porcGastoProy = ((Promedioponderado * Distribucionponderada) / TotalPorcentaje);//(Promedioponderado.multiply(Distribucionponderada));//.divide(TotalPorcentaje,2,RoundingMode.UNNECESSARY);
            porcGastoProy = round(porcGastoProy, 2);
        } else {//BNOProy != BNOPermitido
            if (_indicCorte == true) {
                //1) Calculo el promedio de los CIERRES
                for (int i = 0; i < _matrixBeneficio.size()-1; i++) {// hasta size - 1 para excluir el corte
                    _Sumatoria = _Sumatoria + _matrixBeneficio.get(i).getBnoVentas().doubleValue();
                }
                _PromedioCierres = _Sumatoria / _matrixBeneficio.size()-1;
                //2) Una vez calculado el promedio lo comparamos con el BNO  del Cierre
                if (BNOCorte<_PromedioCierres){
                    _Sumatoria = 0;
                    for (int i = 0; i<_matrixBeneficio.size(); i++){
                        _Sumatoria = _Sumatoria + _matrixBeneficio.get(i).getPorcGasto().doubleValue();
                    }
                  porcGastoProy = _Sumatoria/_matrixBeneficio.size();
                  porcGastoProy = round(porcGastoProy, 2);// Formateamos el valor a dos decimales
                  
                } else {//Caso Contrario sería el promedio  los cierres
                    _Sumatoria = 0;
                    for (int i = 0; i<_matrixBeneficio.size()-1; i++){
                        _Sumatoria = _Sumatoria + _matrixBeneficio.get(i).getPorcGasto().doubleValue();
                    }
                     porcGastoProy = _Sumatoria/(_matrixBeneficio.size()-1);
                     porcGastoProy = round(porcGastoProy, 2);
                }//Fin del Else
                

            }
        }
        System.out.println("Hola"+porcGastoProy);
        return (porcGastoProy);
    }

    public double CostosproyModerado(ArrayList<BeneficiosBean> _matrixBeneficio, boolean _indicCorte, double _BNOProy, double _BNOPermitido) {
        int _Criterio = CompareDouble(_BNOProy, _BNOPermitido);
       
        double TotalPorcentaje = 100; //new BigDecimal("100");
        double porcCostoCorte;// = new BigDecimal(BigInteger.ONE);
        double BNOCorte;//= new BigDecimal(BigInteger.ONE);
        double Distribucionponderada = 0;// = new BigDecimal(BigInteger.ONE);
        double Promedioponderado = 0;// = new BigDecimal(BigInteger.ONE);
        double porcCostoProy = 0;// = new BigDecimal(BigInteger.ONE);
        double _Sumatoria = 0;
        double _PromedioCierres = 0;

        
        //Obtenemos el valor del corte
        BNOCorte = _matrixBeneficio.get(_matrixBeneficio.size() - 1).getBnoVentas().doubleValue();
        //1) Verificamos si el BNO Proyectado es igual al BNO permitido
        if (_Criterio == 0) {
             //2) Debemos Calcular la Distribución ponderada Utilizando el %Gasto del Corte (Vaciado)
            //porcGastoCorte = _matrixBeneficio.get(_matrixBeneficio.size()-1).getPorcGasto();// Se realiza size - 1, porque el vaciado esta ordenado de manera que el corte es el ultimo
            porcCostoCorte = _matrixBeneficio.get(_matrixBeneficio.size() - 1).getPorcCostos().doubleValue();
            
            Distribucionponderada = (porcCostoCorte / (TotalPorcentaje - (BNOCorte))) * (TotalPorcentaje);


            //3)Calculamos el promedio ponderado
            Promedioponderado = TotalPorcentaje - _BNOProy;//TotalPorcentaje.subtract(_BNOProy);

            porcCostoProy = ((Promedioponderado * Distribucionponderada) / TotalPorcentaje);//(Promedioponderado.multiply(Distribucionponderada));//.divide(TotalPorcentaje,2,RoundingMode.UNNECESSARY);
            porcCostoProy = round(porcCostoProy, 2);
        } else {//BNOProy != BNOPermitido
            if (_indicCorte == true) {
                //1) Calculo el promedio de los CIERRES
                for (int i = 0; i < _matrixBeneficio.size()-1; i++) {// hasta size - 1 para excluir el corte
                    _Sumatoria = _Sumatoria + _matrixBeneficio.get(i).getBnoVentas().doubleValue();
                }
                _PromedioCierres = _Sumatoria / _matrixBeneficio.size()-1;
                //2) Una vez calculado el promedio lo comparamos con el BNO  del Cierre
                if (BNOCorte<_PromedioCierres){
                    _Sumatoria = 0;
                    for (int i = 0; i<_matrixBeneficio.size(); i++){
                        _Sumatoria = _Sumatoria + _matrixBeneficio.get(i).getPorcCostos().doubleValue();
                    }
                  porcCostoProy = _Sumatoria/_matrixBeneficio.size();
                  porcCostoProy = round(porcCostoProy, 2);// Formateamos el valor a dos decimales
                  
                } else {//Caso Contrario sería el promedio  los cierres
                    _Sumatoria = 0;
                    for (int i = 0; i<_matrixBeneficio.size()-1; i++){
                        _Sumatoria = _Sumatoria + _matrixBeneficio.get(i).getPorcCostos().doubleValue();
                    }
                     porcCostoProy = _Sumatoria/(_matrixBeneficio.size()-1);
                     porcCostoProy = round(porcCostoProy, 2);
                }//Fin del Else
                

            }
        }
        System.out.println("Hola"+porcCostoProy);
        return (porcCostoProy);
    }

    public BigDecimal IntegerToBigDecimal(int valor) {

        return new BigDecimal(String.valueOf(valor));
    }

    public int CompareBigDecimal(BigDecimal a, BigDecimal b) {
        //a = BNOCalculado
        //b = BNO Permitido 
        int resultado = a.compareTo(b);

        if (resultado == 0)//son iguales
        {
            resultado = 0;
        }
        if (resultado == 1)//a mayor que b
        {
            resultado = 1;
        }
        if (resultado == 2)//a menor que b
        {
            resultado = 2;
        }
        return resultado;
    }
    
    public int CompareDouble(double a, double b) {
        //a = BNOCalculado
        //b = BNO Permitido 
        int resultado = 0;

        if (a==b)//son iguales
        {
            resultado = 0;
        }
        if (a > b)//a mayor que b
        {
            resultado = 1;
        }
        if (a < b)//a menor que b
        {
            resultado = 2;
        }
        return resultado;
    }

    public ArrayList DevolverValoresCorte(ArrayList<BeneficiosBean> _matrixBeneficio) {
        ArrayList<BigDecimal> ValoresCorte = new ArrayList<>();

        //for(int i = 0;i<_matrixBeneficio.size();i++){
        ValoresCorte.add(_matrixBeneficio.get(_matrixBeneficio.size() - 1).getBnoVentas());
        ValoresCorte.add(_matrixBeneficio.get(_matrixBeneficio.size() - 1).getPorcGasto());
        ValoresCorte.add(_matrixBeneficio.get(_matrixBeneficio.size() - 1).getPorcCostos());
         //}

        return null;
    }
    public BigDecimal FormatearDouble(double _Valor) {
        String resultado = "";
        resultado = new DecimalFormat("#.##").format(_Valor);

        return new BigDecimal(resultado);
    }
   
    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
}


}
