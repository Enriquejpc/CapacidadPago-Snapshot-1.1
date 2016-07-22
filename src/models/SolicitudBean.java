/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.math.*;

import util.FuncionesUtiles;

/**
 *
 * @author b586854
 * @version 1.0
 */
public class SolicitudBean {

    private static BigDecimal montoSolicitado;
    private static String fechaEstLiq;
    private static BigDecimal vigenciaTasa;
    private static int amortizaciones;
    private static int destino;
    private static BigDecimal plazoMeses;
    private static int cartera;
    private static BigDecimal tasa;
    private static BigDecimal comisionFlat;
    private static int escenario;

    public SolicitudBean() {
    }

    /**
     * Constructor que si usa para asignar los datos a las variables de las
     * clases a partir de las siguientes parametros
     *
     * @param montoSolicitado el monto de la solicitud y es de tipo String
     * @param fechaEstLiq la fecha estimada de liquidación de la solicitud y es
     * de tipo String
     * @param vigenciaTasa la vigencia de la tasa de la solicitud y es de tipo
     * String
     * @param cartera la cartera de la solicitud y es de tipo String
     * @param plazoMeses los plazos de la solicitud y es de tipo String
     * @param tasa la tasa de la solicitud y es de tipo String
     * @param amortizaciones la amortización de la solicitud y es de tipo String
     * @param comisionFlat la comisión flat de la solicitud y es de tipo String
     * @param escenario el escenario de la solicitud y es de tipo String
     * @param destino el destino de la solicitud y es de tipo String
     */
    public SolicitudBean(String montoSolicitado, String fechaEstLiq, String vigenciaTasa, String cartera,
            String plazoMeses, String tasa, String comisionFlat, String amortizaciones, String escenario, String destino) {

        if (!FuncionesUtiles.parametrosIsNull(montoSolicitado)) {
            this.montoSolicitado = new BigDecimal(montoSolicitado);
        }

        if (!FuncionesUtiles.parametrosIsNull(fechaEstLiq)) {
            this.fechaEstLiq = fechaEstLiq;
        }

        if (!FuncionesUtiles.parametrosIsNull(vigenciaTasa)) {
            this.vigenciaTasa = new BigDecimal(vigenciaTasa);
        }

        switch (cartera) {
            case "Comercial":
                this.cartera = 1;
                break;
            case "Manufactura Pymi":
                this.cartera = 2;
                break;
            case "Manufactura":
                this.cartera = 3;
                break;
            default:
                break;
        }

        if (!FuncionesUtiles.parametrosIsNull(plazoMeses)) {
            this.plazoMeses = new BigDecimal(plazoMeses);
        }

        if (!FuncionesUtiles.parametrosIsNull(tasa)) {
            this.tasa = new BigDecimal(tasa);
        }

        if (!FuncionesUtiles.parametrosIsNull(comisionFlat)) {
            this.comisionFlat = new BigDecimal(comisionFlat);
        }
        switch (amortizaciones) {

            case "Mensual":
                this.amortizaciones = 1;
                break;
            case "Bimestral":
                this.amortizaciones = 2;
                break;
            case "Trimestral":
                this.amortizaciones = 3;
                break;
            case "Cuatrimestral":
                this.amortizaciones = 4;
                break;
            case "Semestral":
                this.amortizaciones = 5;
                break;
            default:
                break;
        }

        switch (escenario) {
            case "Conservador":
                this.escenario = 1;
                break;
            case "Moderado":
                this.escenario = 2;
                break;
            case "Parametizable":
                this.escenario = 3;
                break;
            default:
                break;
        }

        switch (destino) {
            case "Activo Fijo":
                this.destino = 1;
                break;
            case "Capital Trabajo":
                this.destino = 2;
                break;
            default:
                break;
        }
    }

    /**
     * Metodo que obtiene el montoSolicitado
     *
     * @return el monto de la solicitud y es de tipo BigDecimal
     */
    public static BigDecimal getMontoSolicitado() {
        return montoSolicitado;
    }

    /**
     * Metodo donde se le asigna valos a la variable montoSolicitado
     *
     * @param montoSolicitado el monto de la solicitud y es de tipo BigDecimal
     */
    public static void setMontoSolicitado(BigDecimal montoSolicitado) {
        SolicitudBean.montoSolicitado = montoSolicitado;
    }

    /**
     * Metodo que obtiene el fechaEstLiq
     *
     * @return la fecha estimada de liquidación de la solicitud y es de tipo
     * String
     */
    public static String getFechaEstLiq() {
        return fechaEstLiq;
    }

    /**
     * Metodo donde se le asigna valos a la variable fechaEstLiq
     *
     * @param fechaEstLiq la fecha estimada de liquidación de la solicitud y es
     * de tipo String
     */
     public static void setFechaEstLiq(String fechaEstLiq) {
        SolicitudBean.fechaEstLiq = fechaEstLiq;
    }

    /**
     * Metodo que obtiene el vigenciaTasa
     *
     * @return la vigencia de la tasa de la solicitud y es de tipo BigDecimal
     */
    public BigDecimal getVigenciaTasa() {
        return vigenciaTasa;
    }

    /**
     * Metodo donde se le asigna valos a la variable vigenciaTasa
     *
     * @param vigenciaTasa la vigencia de la tasa de la solicitud y es de tipo
     * BigDecimal
     */
     public static void setVigenciaTasa(BigDecimal vigenciaTasa) {
        SolicitudBean.vigenciaTasa = vigenciaTasa;
    }

    /**
     * Metodo que obtiene el amortizaciones
     *
     * @return la amortización de la solicitud y es de tipo int
     */
    public int getAmortizaciones() {
        return amortizaciones;
    }

    /**
     * Metodo donde se le asigna valos a la variable amortizaciones
     *
     * @param amortizaciones la amortización de la solicitud y es de tipo int
     */
     public static void setAmortizaciones(int amortizaciones) {
        SolicitudBean.amortizaciones = amortizaciones;
    }

    /**
     * Metodo que obtiene el destino
     *
     * @return la amortización de la solicitud y es de tipo int
     */
    public int getDestino() {
        return destino;
    }

    /**
     * Metodo donde se le asigna valos a la variable destino
     *
     * @param destino la amortización de la solicitud y es de tipo int
     */
     public static void setDestino(int destino) {
        SolicitudBean.destino = destino;
    }

    /**
     * Metodo que obtiene el plazoMeses
     *
     * @return los plazos de la solicitud y es de tipo BigDecimal
     */
    public static BigDecimal getPlazoMeses() {
        return plazoMeses;
    }

    /**
     * Metodo donde se le asigna valos a la variable plazoMeses
     *
     * @param plazoMeses los plazos de la solicitud y es de tipo BigDecimal
     */
    public static void setPlazoMeses(BigDecimal plazoMeses) {
        SolicitudBean.plazoMeses = plazoMeses;
    }

    /**
     * Metodo que obtiene el cartera
     *
     * @return la cartera de la solicitud y es de tipo int
     */
    public int getCartera() {
        return cartera;
    }

    /**
     * Metodo donde se le asigna valos a la variable cartera
     *
     * @param cartera la cartera de la solicitud y es de tipo int
     */
     public static void setCartera(int cartera) {
        SolicitudBean.cartera = cartera;
    }

    /**
     * Metodo que obtiene el tasa
     *
     * @return la tasa de la solicitud y es de tipo BigDecimal
     */
    public BigDecimal getTasa() {
        return tasa;
    }

    /**
     * Metodo donde se le asigna valos a la variable tasa
     *
     * @param tasa la tasa de la solicitud y es de tipo BigDecimal
     */
     public static void setTasa(BigDecimal tasa) {
        SolicitudBean.tasa = tasa;
    }

    /**
     * Metodo que obtiene el comisionFlat
     *
     * @return la comisión flat de la solicitud y es de tipo BigDecimal
     */
    public BigDecimal getComisionFlat() {
        return comisionFlat;
    }

    /**
     * Metodo donde se le asigna valos a la variable comisionFlat
     *
     * @param comisionFlat la comisión flat de la solicitud y es de tipo
     * BigDecimal
     */
     public static void setComisionFlat(BigDecimal comisionFlat) {
        SolicitudBean.comisionFlat = comisionFlat;
    }

    /**
     * Metodo que obtiene el escenario
     *
     * @return el escenario de la solicitud y es de tipo int
     */
    public int getEscenario() {
        return escenario;
    }

    /**
     * Metodo donde se le asigna valos a la variable escenario
     *
     * @param escenario el escenario de la solicitud y es de tipo int
     */
     public static void setEscenario(int escenario) {
        SolicitudBean.escenario = escenario;
    }

}
