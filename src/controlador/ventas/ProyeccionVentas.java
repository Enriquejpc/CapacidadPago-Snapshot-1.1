/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.ventas;

import front.Parametros;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;

import java.util.logging.Level;
import java.util.logging.Logger;

import models.ParametrosBean;
import models.SolicitudBean;
import models.VentasBean;
import util.FuncionesUtiles;

/**
 *
 * @author b586854
 */
public class ProyeccionVentas {

    SolicitudBean _accesoSolicitud = new SolicitudBean();
    Parametros _accesoParametros = new Parametros();
    ParametrosBean _paramBean;
    static ArrayList<ParametrosBean> _matrixParametros = new ArrayList();
    static ArrayList<Double> _ProyMatrizCrecimiento = new ArrayList();
    static ArrayList<BigDecimal> _ProyMatrizVentas = new ArrayList();

    public boolean ProyectarVentas(ArrayList<VentasBean> _matrixVentas, int _IndicEscenario, int _MaxVentas) {
        int AnioIndiceCorte = 0;
        int mesesFaltantes = 0;
        boolean _exito = false;
        boolean indicadorCorte = false;
        int AnioCtrl = 0;
        int NroAniosProy = (_accesoSolicitud.getPlazoMeses().divide(new BigDecimal("12"))).intValue()+1;
        System.out.println("\033[32m"+NroAniosProy);
        double _VentasPermitida = (_MaxVentas);// Maximo permitido por el BCV
        double _PorcionCalculada = 0;//Porcion correspondiente a las ventas para finalizar el año que posee corte;
        double aux = 0; // Calculamos los valores correspondientes
        double InflacionEsperada = 0;

        double _CrecimientoVentasCalculado = 0;
        double _PorcCrecimiento = 0;
       

        String anio[] = _accesoSolicitud.getFechaEstLiq().split("/");
        BigDecimal _CrecA = new BigDecimal(BigInteger.ONE);
        BigDecimal _porcA = new BigDecimal(BigInteger.ONE);
        /**
         * Escenario 1 - Conservador Solo ubica el valor menor en el %Ventas
         * (Vaciado) y lo toma como posicion indice, es decir, para gastos y
         * costos va a tomar el valor que este en la columna indice
         */
        //1.- Clacular la Inflacion Esperada por año y almacenarla en su modelo correspondiente
        aux = Double.parseDouble(_accesoParametros._2013Inf.getText().replace(",", ".")) * _VentasPermitida;
        aux = round(aux, 2) / 100;
        _matrixParametros.add(new ParametrosBean(2013, aux));

        aux = Double.parseDouble(_accesoParametros._2014Inf.getText().replace(",", ".")) * _VentasPermitida;
        aux = round(aux, 2) / 100;
        _matrixParametros.add(new ParametrosBean(2014, aux));

        aux = Double.parseDouble(_accesoParametros._2015Inf.getText().replace(",", ".")) * _VentasPermitida;
        aux = round(aux, 2) / 100;
        _matrixParametros.add(new ParametrosBean(2015, aux));

        aux = Double.parseDouble(_accesoParametros._2016Inf.getText().replace(",", ".")) * _VentasPermitida;
        aux = round(aux, 2) / 100;
        _matrixParametros.add(new ParametrosBean(2016, aux));

        aux = Double.parseDouble(_accesoParametros._2017Inf.getText().replace(",", ".")) * _VentasPermitida;
        aux = round(aux, 2) / 100;
        _matrixParametros.add(new ParametrosBean(2017, aux));

        aux = Double.parseDouble(_accesoParametros._2018Inf.getText().replace(",", ".")) * _VentasPermitida;
        aux = round(aux, 2) / 100;
        _matrixParametros.add(new ParametrosBean(2018, aux));

        aux = Double.parseDouble(_accesoParametros._2019Inf.getText().replace(",", ".")) * _VentasPermitida;
        aux = round(aux, 2) / 100;
        _matrixParametros.add(new ParametrosBean(2019, aux));
        /**
         * ****************
         */
        if (_IndicEscenario == 1) {
            //El año de corte por estar ordenado sera el ultimo o el limite .size()
            AnioIndiceCorte = _matrixVentas.size()-1;

            
            //1) Busco si el vaciado tiene corte
            for (int i = 0; i < _matrixVentas.size(); i++) {
                    if (_matrixVentas.get(i).getTipo().compareToIgnoreCase("Corte") == 0) {
                    indicadorCorte = true;
                    anio = _matrixVentas.get(i).getFecha().split("/");//Anio[2] contiene el año de los periodos reportados
                    System.out.println("hola mundo" +DevolverPorcInflacionEsperada(Integer.parseInt(anio[2]))+"-"+anio[2]);
                }
            }//fin del for
          /*  for (int i = 0; i < _matrixParametros.size(); i++) {
                System.out.println("xxx" + _matrixParametros.get(i).getInflacionEstimada());
            }*/


                if (indicadorCorte == true) {//Debemos calcular la porcion para los meses faltantes
                    //Devuelve el valor de los meses trnscurridos al corte -> El get se maneja en posiciones desde 0
                    mesesFaltantes = 12 - _matrixVentas.get(AnioIndiceCorte).getMeses();

                    //1.1.- Tomo el Valor de las Ventas Netas al ultimo Cierre, (Lo multiplico por la inflación esperada para el año en curso llevada a 12 meses)
                   AnioCtrl = Integer.parseInt(anio[2]);//AnioCtrl -> Es el año del corte y a partir de alli nos movemos por el resto de los años

                   InflacionEsperada = round(DevolverPorcInflacionEsperada(AnioCtrl),2); //Almacena la inflacion esperada dado un año
                   _PorcionCalculada = round(_matrixVentas.get(AnioIndiceCorte-1).getVentasNetas().doubleValue(),2)*round(((100+InflacionEsperada)/12),2);
                   _PorcionCalculada = round(((_PorcionCalculada)/100),2);
                   _PorcionCalculada = round((_PorcionCalculada * mesesFaltantes),2); //Valor de las ventas resultantes que se esperan al cierre despues del corte reportado

                    //1.2.- Auxiliar oara realizar el calculo referencial del año anterior (Ventas al ultimo Cierre * (100 + la inflacion esperada año en curso))
                    aux = _matrixVentas.get(AnioIndiceCorte-1).getVentasNetas().doubleValue()*(100+InflacionEsperada);
                    aux = round(aux,2);
                    //2) Calculamos el crecimiento de las ventas para  finalizar el año proyectado del corte
                 if (_matrixVentas.get(AnioIndiceCorte).getVentasNetas().doubleValue()<aux){
                        _PorcCrecimiento = (_PorcionCalculada + _matrixVentas.get(AnioIndiceCorte).getVentasNetas().doubleValue());
                        _PorcCrecimiento = _PorcCrecimiento/_matrixVentas.get(AnioIndiceCorte-1).getVentasNetas().doubleValue();
                        _PorcCrecimiento = (_PorcCrecimiento - 1)*100; //Lo llevo a su valor porcentual
                        _PorcCrecimiento = round(_PorcCrecimiento, 2);

                       _CrecimientoVentasCalculado = (_PorcionCalculada+_matrixVentas.get(AnioIndiceCorte).getVentasNetas().doubleValue());
                        System.out.println("Ventas Calc"+_CrecimientoVentasCalculado);
                        _ProyMatrizVentas.add(0, new BigDecimal(_CrecimientoVentasCalculado).setScale(2, RoundingMode.CEILING));
                        System.out.println("Crecimiento Calc"+_PorcCrecimiento);
                        _ProyMatrizCrecimiento.add(0,_PorcCrecimiento);
                        
                 }else{
                        _PorcCrecimiento = DevolverPorcInflacionEsperada(AnioCtrl);
                        _ProyMatrizCrecimiento.add(0,_PorcCrecimiento);
                }
                  for (int i =1; i<(NroAniosProy);i++){//For con semilla en 1 porque ya calculamos la porcion para fin de año de corte
                      _PorcCrecimiento = DevolverPorcInflacionEsperada(AnioCtrl + i);
                      _porcA = new BigDecimal(_PorcCrecimiento).setScale(2, BigDecimal.ROUND_CEILING);

                      if (i == 1) {
                          _CrecA = new BigDecimal(_CrecimientoVentasCalculado).setScale(2, BigDecimal.ROUND_CEILING);
                      } else {
                          _CrecA = _CrecA.setScale(2, BigDecimal.ROUND_CEILING);
                      }

                      _CrecA = _CrecA.multiply(new BigDecimal("100").add(_porcA)).setScale(2, RoundingMode.UP);
                      _CrecA = _CrecA.divide(new BigDecimal("100")).setScale(2, BigDecimal.ROUND_CEILING);

                     System.out.println("Crecimiento siguiente" + (_PorcCrecimiento));
                     _ProyMatrizCrecimiento.add(i,_PorcCrecimiento);
                     System.out.println("Ventas siguiete"+_CrecA);
                     _ProyMatrizVentas.add(i,_CrecA);
                    }
                indicadorCorte = false;
            }else{//No hay cortes solo cierres
                    for (int i =0; i<NroAniosProy;i++){
                        _PorcCrecimiento = DevolverPorcInflacionEsperada(AnioCtrl+i);
                        _porcA = new BigDecimal(_PorcCrecimiento).setScale(2, BigDecimal.ROUND_CEILING);
                        _ProyMatrizCrecimiento.add(i,_porcA.doubleValue());
                        _CrecA = _matrixVentas.get(_matrixVentas.size()).getVentasNetas().setScale(2, BigDecimal.ROUND_CEILING);
                        _ProyMatrizVentas.add(i,_CrecA);
                    }
                }//Fin del else
               _exito = GenerarArchivoSalida(_ProyMatrizVentas, _ProyMatrizCrecimiento, AnioCtrl,_IndicEscenario);
         }//Fin Indicador de Escenario 1
        if(_IndicEscenario == 2){
             //El año de corte por estar ordenado sera el ultimo o el limite .size()
            AnioIndiceCorte = _matrixVentas.size()-1;

           
            //1) Busco si el vaciado tiene corte
            for (int i = 0; i < _matrixVentas.size(); i++) {
                    if (_matrixVentas.get(i).getTipo().compareToIgnoreCase("Corte") == 0) {
                    indicadorCorte = true;
                    anio = _matrixVentas.get(i).getFecha().split("/");//Anio[2] contiene el año de los periodos reportados
                    System.out.println("hola mundo" +DevolverPorcInflacionEsperada(Integer.parseInt(anio[2]))+"-"+anio[2]);
                }
            }//fin del for
        

                if (indicadorCorte == true) {//Debemos calcular la porcion para los meses faltantes
                    //Devuelve el valor de los meses trnscurridos al corte -> El get se maneja en posiciones desde 0
                    mesesFaltantes = 12 - _matrixVentas.get(AnioIndiceCorte).getMeses();

                    //1.1.- Tomo el Valor de las Ventas Netas al ultimo Cierre, (Lo multiplico por la inflación esperada para el año en curso llevada a 12 meses)
                   AnioCtrl = Integer.parseInt(anio[2]);//AnioCtrl -> Es el año del corte y a partir de alli nos movemos por el resto de los años

                   InflacionEsperada = round(DevolverPorcInflacionEsperada(AnioCtrl),2); //Almacena la inflacion esperada dado un año
                   _PorcionCalculada = round(_matrixVentas.get(AnioIndiceCorte-1).getVentasNetas().doubleValue(),2)*round(((100+InflacionEsperada)/12),2);
                   _PorcionCalculada = round(((_PorcionCalculada)/100),2);
                   _PorcionCalculada = round((_PorcionCalculada * mesesFaltantes),2); //Valor de las ventas resultantes que se esperan al cierre despues del corte reportado

                    //1.2.- Auxiliar oara realizar el calculo referencial del año anterior (Ventas al ultimo Cierre * (100 + la inflacion esperada año en curso))
                    aux = _matrixVentas.get(AnioIndiceCorte-1).getVentasNetas().doubleValue()*(100+InflacionEsperada);
                    aux = round(aux,2);
                    //2) Calculamos el crecimiento de las ventas para  finalizar el año proyectado del corte
                 if (_matrixVentas.get(AnioIndiceCorte).getVentasNetas().doubleValue()<aux){
                        _PorcCrecimiento = (_PorcionCalculada + _matrixVentas.get(AnioIndiceCorte).getVentasNetas().doubleValue());
                        _PorcCrecimiento = _PorcCrecimiento/_matrixVentas.get(AnioIndiceCorte-1).getVentasNetas().doubleValue();
                        _PorcCrecimiento = (_PorcCrecimiento - 1)*100; //Lo llevo a su valor porcentual
                        _PorcCrecimiento = round(_PorcCrecimiento, 2);

                       _CrecimientoVentasCalculado = (_PorcionCalculada+_matrixVentas.get(AnioIndiceCorte).getVentasNetas().doubleValue());
                        System.out.println("Ventas Calc"+_CrecimientoVentasCalculado);
                        _ProyMatrizVentas.add(0, new BigDecimal(_CrecimientoVentasCalculado).setScale(2, RoundingMode.CEILING));
                        System.out.println("Crecimiento Calc"+_PorcCrecimiento);
                        _ProyMatrizCrecimiento.add(0,_PorcCrecimiento);
                        
                 }else{
                        _PorcCrecimiento = DevolverPorcInflacionEsperada(AnioCtrl);
                        _ProyMatrizCrecimiento.add(0,_PorcCrecimiento);
                }
                  for (int i =1; i<NroAniosProy;i++){//For con semilla en 1 porque ya calculamos la porcion para fin de año de corte
                      _PorcCrecimiento = DevolverPorcInflacionEsperada(AnioCtrl + i);
                      _porcA = new BigDecimal(_PorcCrecimiento).setScale(2, BigDecimal.ROUND_CEILING);

                      if (i == 1) {
                          _CrecA = new BigDecimal(_CrecimientoVentasCalculado).setScale(2, BigDecimal.ROUND_CEILING);
                      } else {
                          _CrecA = _CrecA.setScale(2, BigDecimal.ROUND_CEILING);
                      }

                      _CrecA = _CrecA.multiply(new BigDecimal("100").add(_porcA)).setScale(2, RoundingMode.UP);
                      _CrecA = _CrecA.divide(new BigDecimal("100")).setScale(2, BigDecimal.ROUND_CEILING);

                     
                     _ProyMatrizCrecimiento.add(i,_PorcCrecimiento);
                     _ProyMatrizVentas.add(i,_CrecA);
                    }
                indicadorCorte = false;
            }else{//No hay cortes solo cierres
                    for (int i =0; i<NroAniosProy;i++){
                        _PorcCrecimiento = DevolverPorcInflacionEsperada(AnioCtrl+i);
                        _porcA = new BigDecimal(_PorcCrecimiento).setScale(2, BigDecimal.ROUND_CEILING);
                        _ProyMatrizCrecimiento.add(i,_porcA.doubleValue());
                        _CrecA = _matrixVentas.get(_matrixVentas.size()).getVentasNetas().setScale(2, BigDecimal.ROUND_CEILING);
                        _ProyMatrizVentas.add(i,_CrecA);
                    }
                }//Fin del else
               _exito = GenerarArchivoSalida(_ProyMatrizVentas, _ProyMatrizCrecimiento, AnioCtrl,_IndicEscenario);
        }
        
    return _exito;
    }

    public static double round(double value, int places) {
        if (places < 0) {
            throw new IllegalArgumentException();
        }

        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }

    /**
     *@param Anio -> Expresa el año del cual voy a buscar su valor porcentual asociado a la inflacion estimada
     *@return Inflacion estimada de la tabla de parametros
     */
    public double DevolverPorcInflacionEsperada(int _Anio){
        double resultado = 0;
        for (int i = 0; i<_matrixParametros.size(); i++){
            if(_Anio == _matrixParametros.get(i).getAnio()){
                resultado = _matrixParametros.get(i).getInflacionEstimada();
           }
        }
        return resultado;
    }

     /**
     *@param Anio -> Expresa el año del cual voy a buscar los meses restantes (<->Corte)
     *@return los meses restantes
     */
    public int DevolverMesesRestantes(int _Anio){
        int resultado = 0;
        for (int i = 0; i<_matrixParametros.size(); i++){
            if(_Anio == _matrixParametros.get(i).getAnio()){
                //resultado = _matrixParametros.get(i).ge;
                System.out.println("Encontrado"+resultado);
            }
        }
        return resultado;
    }
    
    
    public boolean GenerarArchivoSalida(ArrayList<BigDecimal> MatrixVentas, ArrayList<Double> MatrixCrecimiento, int Anio, int IndicEscenario) {
        File f;
        FileWriter w;
        BufferedWriter bw = null;
        PrintWriter wr = null;
        boolean _retorno = false;
        String Cabecera = "Conceptos";
        String Tabulacion = "              \t\t";
        String NombreArchivo = "";
        
        /**
         * Se anualiza el plazo
         */
        int NroAniosProy = (_accesoSolicitud.getPlazoMeses().divide(new BigDecimal("12"))).intValue()+1;
        ArrayList<String> Conceptos = new ArrayList();

        Conceptos.add("Ventas\t");
        Conceptos.add("%Crecimiento Ventas\t");
        if(IndicEscenario == 1){
           NombreArchivo =  "VENTASConservador.txt"; 
        }else{
            NombreArchivo =  "VENTASModerado.txt"; 
        }
            
        /**
         * Estructuración del Archivo
         */
        try {
            f = new File("C:\\BM_HOME\\appl\\MACE\\salida\\" + NombreArchivo);
            w = new FileWriter(f);
            bw = new BufferedWriter(w);
            wr = new PrintWriter(bw);
            wr.write("Proyeccion de las Ventas. - Escenario Conservador");
            wr.append("\nLa tasa (Utilizar 24%)");
            wr.append("\nPlazo:" + _accesoSolicitud.getPlazoMeses());
            wr.append("\n ");
            wr.append(Cabecera + "\t\t\t\t");
            for (int columna = 0; columna < NroAniosProy; columna++) {
                wr.append((Anio + columna) + "E\t\t");
            }

            wr.append("\n" + Conceptos.get(0) );
            wr.append("\t\t\t\t");
            for (int columna = 0; columna < NroAniosProy; columna++) {
                wr.append(FormatearMontos( MatrixVentas.get(columna).toString()).toString() + "\t");
            }
            wr.append("\n" + Conceptos.get(1));
            wr.append("\t");
            for (int columna = 0; columna < NroAniosProy; columna++) {
                wr.append(FormatearMontos(MatrixCrecimiento.get(columna).toString()).toString() + "%\t\t");
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

    public String FormatearMontos(String valor) {
        BigDecimal _val = new BigDecimal(valor);
        DecimalFormat formateador = new DecimalFormat("###,###.##");
        System.out.println(formateador.format(_val));
        return  formateador.format(_val);
    }
}
