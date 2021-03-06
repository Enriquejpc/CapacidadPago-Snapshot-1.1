/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.math.BigDecimal;

/**
 *
 * @author C613702
 */
public final class AmortizacionBean {

    private BigDecimal cantidadAñoProyeccion;
    private BigDecimal numeroTotalCuotas;
    private BigDecimal cuotasCobroCapital;
    private BigDecimal capital;
    private BigDecimal montoSolicitado;
    private int destino;
    private String fechaCobro;
    private BigDecimal interes = new BigDecimal("24");
    private BigDecimal cuota;
    private BigDecimal tasa;
    private BigDecimal saldo = new BigDecimal("0");
    private BigDecimal comisionFlat;
    private BigDecimal plazo;
    private BigDecimal periodicidad;
    private BigDecimal montoCuota = new BigDecimal("0");
    private BigDecimal sumaIntereses = new BigDecimal("0");
    private BigDecimal añoProyeccion = new BigDecimal("0");
    private int nroCuota;
    private BigDecimal interesesAnio[];
    private BigDecimal porcionCteCorrAnio[];
    private BigDecimal porcionCteCorrDeudaLPAnio[];
    private BigDecimal DeudaLargoPzo[];

    
    
  
  
    /**
     * 
     * @return BigDecimal de cantidad Año Proyeccion
     */

    public BigDecimal getCantidadAñoProyeccion() {
        return cantidadAñoProyeccion;
    }
    /**
     * 
     * @param cantidadAñoProyeccion 
     */
    public void setCantidadAñoProyeccion(BigDecimal cantidadAñoProyeccion) {
        this.cantidadAñoProyeccion = cantidadAñoProyeccion;
    }
    
    /**
     * 
     * @return BigDecimal de numero Total Cuotas
     */
    public BigDecimal getNumeroTotalCuotas() {
        return numeroTotalCuotas;
    }
    
    /**
     * 
     * @param numeroTotalCuotas 
     */
    public void setNumeroTotalCuotas(BigDecimal numeroTotalCuotas) {
        this.numeroTotalCuotas = numeroTotalCuotas;
    }
    
    /**
     * 
     * @return BigDecimal de cuotas Cobro Capital
     */
    public BigDecimal getCuotasCobroCapital() {
        return cuotasCobroCapital;
    }
    /**
     * 
     * @param cuotasCobroCapital 
     */
    public void setCuotasCobroCapital(BigDecimal cuotasCobroCapital) {
        this.cuotasCobroCapital = cuotasCobroCapital;
    }
    
    /**
     * 
     * @return BigDecimal de capital
     */
    public BigDecimal getCapital() {
        return capital;
    }
    
    /**
     * 
     * @param capital 
     */
    public void setCapital(BigDecimal capital) {
        this.capital = capital;
    }
    
    /**
     * 
     * @return BigDecimal de monto Solicitado
     */
    public BigDecimal getMontoSolicitado() {
        return montoSolicitado;
    }
    
    /**
     * 
     * @param montoSolicitado 
     */
    public void setMontoSolicitado(BigDecimal montoSolicitado) {
        this.montoSolicitado = montoSolicitado;
    }
    
    /**
     * 
     * @return BigDecimal de Destino
     */
    public int getDestino() {
        return destino;
    }
    
    /**
     * 
     * @param destino 
     */
    public void setDestino(int destino) {
        this.destino = destino;
    }
    
    /**
     * 
     * @return BigDecimal de fechaCobro
     */
    public String getFechaCobro() {
        return fechaCobro;
    }
    
    /**
     * 
     * @param fechaCobro 
     */
    public void setFechaCobro(String fechaCobro) {
        this.fechaCobro = fechaCobro;
    }
    
    /**
     * 
     * @return BigDecimal de interes
     */
    public BigDecimal getInteres() {
        return interes;
    }
    
    /**
     * 
     * @param interes 
     */
    public void setInteres(BigDecimal interes) {
        this.interes = interes;
    }
    
    /**
     * 
     * @return BigDecimal de cuota
     */
    public BigDecimal getCuota() {
        return cuota;
    }
    
    /**
     * 
     * @param cuota 
     */
    public void setCuota(BigDecimal cuota) {
        this.cuota = cuota;
    }
    
    /**
     * 
     * @return BigDecimal de Tasa
     */
    public BigDecimal getTasa() {
        return tasa;
    }
    
    /**
     * 
     * @param tasa 
     */
    public void setTasa(BigDecimal tasa) {
        this.tasa = tasa;
    }
    
    /**
     * 
     * @return BigDecimal de Saldo
     */
    public BigDecimal getSaldo() {
        return saldo;
    }
    
    /**
     * 
     * @param saldo 
     */
    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }
    
    /**
     * 
     * @return BigDecimal de Comision Flat
     */
    public BigDecimal getComisionFlat() {
        return comisionFlat;
    }
    
    /**
     * 
     * @param comisionFlat 
     */
    public void setComisionFlat(BigDecimal comisionFlat) {
        this.comisionFlat = comisionFlat;
    }
    
    /**
     * 
     * @return BigDecimal de Plazo
     */
    public BigDecimal getPlazo() {
        return plazo;
    }
    
    /**
     * 
     * @param plazo 
     */
    public void setPlazo(BigDecimal plazo) {
        this.plazo = plazo;
    }
    
    /**
     * 
     * @return BigDecimal de Periocidad
     */
    public BigDecimal getPeriodicidad() {
        return periodicidad;
    }
    
    /**
     * 
     * @param periodicidad  
     */
    public void setPeriodicidad(BigDecimal periodicidad) {
        this.periodicidad = periodicidad;
    }
    /**
     * 
     * @return BigDecimal de Monto Cuota
     */
    public BigDecimal getMontoCuota() {
        return montoCuota;
    }
    
    /**
     * 
     * @param montoCuota 
     */

    public void setMontoCuota(BigDecimal montoCuota) {
        this.montoCuota = montoCuota;
    }
    
    /**
     * 
     * @return BigDecimal de Suma Intereses
     */

    public BigDecimal getSumaIntereses() {
        return sumaIntereses;
    }
    
    /**
     * 
     * @param sumaIntereses 
     */

    public void setSumaIntereses(BigDecimal sumaIntereses) {
        this.sumaIntereses = sumaIntereses;
    }
    /**
     * 
     * @return BigDecimal de Año de Proyeccion
     */

    public BigDecimal getAñoProyeccion() {
        return añoProyeccion;
    }
    /**
     * 
     * @param añoProyeccion 
     */

    public void setAñoProyeccion(BigDecimal añoProyeccion) {
        this.añoProyeccion = añoProyeccion;
    }
  /**
   * 
   * @return BigDecimal de Nro Cuota
   */

    public int getNroCuota() {
        return nroCuota;
    }
    /**
     * 
     * @param nroCuota 
     */

    public void setNroCuota(int nroCuota) {
        this.nroCuota = nroCuota;
    }
    
    
    /**
    * @Date 22/09/2016
    * @author b586854
    * @version 1.0
    * Almacenamiento de los intereses acumulados de cada tabla de amortización.
    * **/
   public BigDecimal[] getInteresesAnio() {
        return interesesAnio;
    }

    public void setInteresesAnio(BigDecimal[] interesesAnio) {
        this.interesesAnio = interesesAnio;
    }
    
    /**
    * @Date 11/10/2016
    * @author b586854
    * @version 1.0
    * Almacenamiento de la porción corriente corregida de cada tabla de amortización.
    * **/
    public BigDecimal[] getPorcionCteCorrAnio() {
        return porcionCteCorrAnio;
    }

    public void setPorcionCteCorrAnio(BigDecimal[] porcionCteCorrAnio) {
        this.porcionCteCorrAnio = porcionCteCorrAnio;
    }
     /**
    * @Date 11/10/2016
    * @author b586854
    * @version 1.0
    * Almacenamiento de la porción corriente corregida de cada tabla de amortización.
    * **/
    public BigDecimal[] getPorcionCteCorrDeudaLPAnio() {
        return porcionCteCorrDeudaLPAnio;
    }

    public void setPorcionCteCorrDeudaLPAnio(BigDecimal[] porcionCteCorrDeudaLPAnio) {
        this.porcionCteCorrDeudaLPAnio = porcionCteCorrDeudaLPAnio;
    }
   
    /**
    * @Date 11/10/2016
    * @author b586854
    * @version 1.0
    * Almacenamiento de la Deuda a Largo Plazo.
    * **/
    
    public BigDecimal[] getDeudaLargoPzo() {
        return DeudaLargoPzo;
    }

    public void setDeudaLargoPzo(BigDecimal[] DeudaLargoPzo) {
        this.DeudaLargoPzo = DeudaLargoPzo;
    }
    
}
