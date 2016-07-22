/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.math.BigDecimal;
import java.util.Date;
import util.FuncionesUtiles;

/**
 *
 * @author b586854
 */
public class VentasBean implements Comparable<VentasBean> {

    private String tipo;
    private String fecha;
    private int meses;
    private BigDecimal ventasNetas;
    private BigDecimal varVentas;
   
    /**
     * Método que Obtiene el valor correspondiente dada una variable a buscar
     *
     * @param secuencia contiene el numero de la línea del archivo dondes se
     * esta leyendo la información, es de tipo int
     * @param tipo indica si de tipo de operación que se realizo y es de tipo
     * String
     * @param fecha indica la fecha en que se realizo la venta de tipo String
     * @param meses indica los meses a calcular y es de tipo String
     * @param ventasNetas indica la ventas netas, y es de tipo String
     * @param varVentas indica el porcentaje de las ventas, y es de tipo String
     */
    public VentasBean(int secuencia, String tipo, String fecha,
            String meses, String ventasNetas, String varVentas) {

                this.tipo = tipo;
                this.fecha = fecha;
                this.meses = Integer.parseInt(meses);
                this.ventasNetas = new BigDecimal(ventasNetas);
                this.varVentas = new BigDecimal(varVentas);               
          
    }

    /**
     * Metodo que obtiene el tipo
     *
     * @return String indica el primer tipo de operación
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * Metodo donde se le asigna valos a la variable tipo
     *
     * @param tipo iindica el primer tipo de operación, es tipo String
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

     /**
     * Metodo que obtiene la fecha
     *
     * @return String indica la primera fecha de la venta
     */
    public String getFecha() {
        return fecha;
    }

    /**
     * Metodo donde se le asigna valos a la variable fecha
     *
     * @param fecha indica la primera fecha de la venta, es de tipo String
     */
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    /**
     * Metodo que obtiene la meses
     *
     * @return int indica el primer mes del la venta
     */
    public int getMeses() {
        return meses;
    }

    /**
     * Metodo donde se le asigna valos a la variable meses
     *
     * @param meses indica el primer mes de la venta, es tipo int
     */
    public void setMeses(int meses) {
        this.meses = meses;
    }

    /**
     * Metodo que obtiene la ventas Netas
     *
     * @return indica la primea ventas netas, es de tipo BigDecimal
     */
    public BigDecimal getVentasNetas() {
        return ventasNetas;
    }

    /**
     * Metodo donde se le asigna valos a la variable ventasNetas
     *
     * @param ventasNetas indica la primera ventas netas, es de tipo BigDecimal
     */
    public void setVentasNetas(BigDecimal ventasNetas) {
        this.ventasNetas = ventasNetas;
    }

  
    /**
     * Metodo que obtiene la variación de ventas
     *
     * @return indica el primer porcentaje de variación de ventas , es de tipo
     * BigDecimal
     */
    public BigDecimal getVarVentas() {
        return varVentas;
    }

    /**
     * Metodo donde se le asigna valos a la variable varVentas
     *
     * @param varVentas indica el primer porcentaje de variación de ventas , es
     * de tipo BigDecimal
     */
    public void setVarVentas(BigDecimal varVentas) {
        this.varVentas = varVentas;
    }    

    
    @Override
     public int compareTo(VentasBean b) {
       return this.getVentasNetas().compareTo(b.getVentasNetas());
    }

 
}
