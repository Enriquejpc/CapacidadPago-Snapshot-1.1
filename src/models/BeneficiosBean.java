/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;


import java.math.BigDecimal;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import util.FuncionesUtiles;

/**
 *
 * @author b586854
 * @since Version 1.0
 */
public class BeneficiosBean implements Comparable<BeneficiosBean> {

    private String tipo;
    private String fecha;
    private int meses;
    private BigDecimal bnoVentas;
    private BigDecimal porcGasto;
    private BigDecimal porcCostos;
   

 
    /**
     * Constructor que si usa para asignar los datos a las variables de las
     * clases a partir de las siguientes parametros
     *
     * @param secuencia contiene el numero de la línea del archivo dondes se
     * esta leyendo la información, es de tipo int,
     * @param tipo indica si de tipo de operación que se realizo y es de tipo
     * String
     * @param fecha indica la fecha en que se realizo de tipo String
     * @param meses indica los meses a calcular y es de tipo String
     * @param bnoVentas indica el % de ventas y de tipo String
     *
     */
    public BeneficiosBean(String tipo, String fecha,
            String meses, String bnoVentas, String porcGasto, String porcCostos) {

        this.tipo = tipo;
        this.fecha = fecha;
        this.meses = Integer.parseInt(meses);
        this.bnoVentas = new BigDecimal(bnoVentas);
        this.porcCostos = new BigDecimal(porcCostos);
        this.porcGasto = new BigDecimal(porcGasto);

    }

    public BeneficiosBean() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Metodo que obtiene el tipo
     *
     * @return String indica el primer tipo de operación
     *
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * Metodo donde se le asigna valos a la variable tipo
     *
     * @param tipo iindica el primer tipo de operación, es tipo String
     *
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    /**
     * Metodo que obtiene la fecha
     *
     * @return String indica la primera fecha de beneficio
     */
    public String getFecha() {
        return fecha;
    }

    /**
     * Metodo donde se le asigna valos a la variable fecha
     *
     * @param fecha indica la primera fecha de beneficio, es de tipo String
     */
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    /**
     * Metodo que obtiene la meses
     *
     * @return int indica la primer mes del beneficio
     */
    public int getMeses() {
        return meses;
    }

    /**
     * Metodo donde se le asigna valos a la variable meses
     *
     * @param meses indica el primer mes del beneficio, es tipo int
     */
    public void setMeses(int meses) {
        this.meses = meses;
    }

    /**
     * Metodo que obtiene la bnoVentas
     *
     * @return BigDecimal indica el primer % de ventas del beneficio
     */
    public BigDecimal getBnoVentas() {
        return bnoVentas;
    }

    /**
     * Metodo donde se le asigna valos a la variable bnoVentas
     *
     * @param bnoVentas indica el primer % de ventas del beneficio, es de tipo
     * BigDecimal
     */
    public void setBnoVentas(BigDecimal bnoVentas) {
        this.bnoVentas = bnoVentas;
    }
       
    /**
     * Metodo que ordena el arrayList de menor a mayor, siendo el menor el primero en aparecer
    **/
    @Override
    public int compareTo(BeneficiosBean b) {
       return this.getBnoVentas().compareTo(b.getBnoVentas());
    }

    public BigDecimal getPorcGasto() {
        return porcGasto;
    }

    public void setPorcGasto(BigDecimal porcGasto) {
        this.porcGasto = porcGasto;
    }

    public BigDecimal getPorcCostos() {
        return porcCostos;
    }

    public void setPorcCostos(BigDecimal porcCostos) {
        this.porcCostos = porcCostos;
    }

}
