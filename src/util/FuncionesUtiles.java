/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import controlador.Amortizacion;
import java.io.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import models.AmortizacionBean;

/**
 *
 * @author B586854
 */
public class FuncionesUtiles {

    /**
     * Método que nos permite validar si la cadena contiene puro número
     *
     * @param cadena
     * @return true si es solo número o retorna false si hay otro caracter
     * diferente a un número
     * El patrón valida si el Número tiene el siguiente formato 
     */
    public static boolean isNumerico(String cadena) {
        return (cadena.matches("[+-]?\\d*(\\,\\d+)?(\\.\\d+)?") && cadena.equals("") == false);

    }

    /**
     * Método que nos permite validar si la cadena contiene puro número y el
     * simbolo de %
     *
     * @param cadena
     * @return true si es solo número o retorna false si hay otro caracter
     * diferente a un número o el símbolo%
     */
    public static boolean isNumericoPorcentaje(String cadena) {
        return (cadena.matches("[+-]?\\d*(\\.\\d+)?(\\,\\d+)?[%]+") && cadena.equals("") == false);

    }

    /**
     * Método que nos permita validar si la fecha contiene el siguiente formato
     * dd/MM/yyyy
     *
     * @param cadena
     * @return true si cumple con el formato, false si no cumple con el mismo
     */
    public static boolean isFormatoFecha(String cadena) {
        boolean resultado;
        SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        resultado = true;
        try {
            Date fecha = formateador.parse(cadena);

        } catch (ParseException e) {
            resultado = false;
        }
        return resultado;
    }

    /**
     * Método que nos permite rellenar con un cero a la izquierda todos los
     * valores menores a 10
     *
     * @param mes
     * @return una cadena de String con el me formateado
     */
    public static String formatearMes(int mes) {
        String mesString;
        if (mes < 10) {
            mesString = "0" + String.valueOf(mes);
        } else {
            mesString = String.valueOf(mes);
        }
        return mesString;
    }

    /**
     * Método que nos permite validar una cadena si viene vacia o no
     *
     * @param cadena
     * @return true si la cadena esta vacia y false si la cadena no esta vacia
     */
    public static boolean isVacio(String cadena) {
        boolean resultado;
        if (cadena.isEmpty()) {
            resultado = true;
        } else {
            resultado = false;
        }
        return resultado;
    }

    public static boolean parametrosIsNull(String cadena) {
        boolean resultado;
        if (cadena == null) {
            resultado = true;
        } else {
            resultado = false;
        }
        return resultado;
    }

    public static boolean validarCampo(Object obj) {
        boolean resultado = false;
        if (obj == null) {
            resultado = true;
        }
        return resultado;
    }

    public static boolean validarCampoEntero(int valor) {
        boolean resultado = false;
        if (valor == 0) {
            resultado = true;
        }
        return resultado;
    }

    /**
     * Método que nos pemite dar formato en decimal de un valor en BigDecimal.
     *
     * @param v
     * @return una cadena de String con el siguiente formato #########0.00
     */
    public static String formatoDouble(BigDecimal v) {
        NumberFormat nf_ = NumberFormat.getNumberInstance(Locale.US);
        DecimalFormat formatoDecimal = (DecimalFormat) nf_;
        formatoDecimal.applyPattern("###,###.###");
        return (formatoDecimal.format(v));
        
    }

    /**
     * Método que nos pemite dar formato en porcentaje de un valor en
     * BigDecimal.
     *
     * @param v
     * @return una cadena de String con el siguiente formato #########0.00%
     */
    public static String formatoPorcentaje(BigDecimal v) {
        BigDecimal divisor = new BigDecimal("100");
        BigDecimal resultado = v.divide(divisor, 3, RoundingMode.CEILING);

        NumberFormat nf_ = NumberFormat.getPercentInstance();
        nf_.setMinimumFractionDigits(2);
        return (nf_.format(resultado));
    }

    

    /**
     * Metodo que nos permite escribir desde un ArrayList
     *
     * @param nombreArchivo se tipo String
     * @param tabla de tipo ArrayList
     * @param metodo de tipo String y nos permite identificar que metodo de
     * amortización se ejecutara
     */
    public static void escribirArchivo(String nombreArchivo, ArrayList<Amortizacion> tabla, String metodo, String montoCredito, String fechaLiquidacion, String plazo) {

        File f;
        FileWriter w;
        BufferedWriter bw = null;
        PrintWriter wr = null;
        AmortizacionBean amortizacionBean = new AmortizacionBean();
        ArrayList<Amortizacion> deuda;
        ArrayList<Amortizacion> solicitud;
        try {
            f = new File("C:\\BM_HOME\\appl\\MACE\\salida\\" + nombreArchivo);
            w = new FileWriter(f);
            bw = new BufferedWriter(w);
            wr = new PrintWriter(bw);
            wr.write("Información de la deuda que esta generando.");
            wr.append("\nMonto Liquidado:" + montoCredito);
            wr.append("\nFecha de Liquidación: " + fechaLiquidacion);
            wr.append("\nLa tasa (Utilizar 24%)");
            wr.append("\nPlazo:" + plazo);
            wr.append("\n ");
            wr.append("\n");

            wr.append("\n N° Cuota");
            wr.append("\t\t\t\t\t\tFecha   ");
            wr.append("\t\t\t\t\t\tTasa   ");
            wr.append("\t\t\t\t\t\tInteres   ");
            wr.append("\t\t\t\t\t\tMonto Cuota   ");
            wr.append("\t\t\t\t\t\tCapital   ");
            wr.append("\t\t\t\t\t\tSaldo   ");

            for (int i = 0; i < tabla.size(); i++) {

                for (int j = 0; j < tabla.get(i).amortizacion(metodo).size(); j++) {

                    amortizacionBean = (AmortizacionBean) tabla.get(i).amortizacion(metodo).get(j);

                    amortizacionBean.getCapital();

                    wr.append("\n" + amortizacionBean.getNroCuota());
                    wr.append("\t\t\t\t\t\t\t" + amortizacionBean.getFechaCobro());
                    wr.append("\t\t\t\t\t\t\t" + amortizacionBean.getTasa().toString());
                    wr.append("\t\t\t\t\t\t\t" + amortizacionBean.getInteres().toString());
                    if (amortizacionBean.getMontoCuota() != null) {
                        wr.append("\t\t\t\t\t\t\t" + amortizacionBean.getMontoCuota().toString());
                    } else {
                        wr.append("\t\t\t\t\t\t\t" + "0");
                    }
                    wr.append("\t\t\t\t\t\t\t" + amortizacionBean.getCapital().toString());
                    wr.append("\t\t\t\t\t\t\t" + amortizacionBean.getSaldo().toString());

                }
            }

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

    }

    /**
     * Método que nos permitira establecer valor 0.00
     *
     * @param valor
     * @return
     */
    public static BigDecimal valorFinal(BigDecimal valor) {
        BigDecimal resultado;
        if (valor.doubleValue() > 0.0 && valor.doubleValue() < 1.0) {
            resultado = new BigDecimal("0.00");

        } else {
            resultado = valor;
        }
        return resultado;
    }
    
    
  
    public static Date convertirDate(String cadena) {
        Date fecha = new Date();
        SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());

        try {
           fecha = formateador.parse(cadena);

        } catch (ParseException e) {
            
        }
        return fecha;
    }
}
