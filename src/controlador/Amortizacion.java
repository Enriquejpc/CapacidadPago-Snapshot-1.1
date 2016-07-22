/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import models.AmortizacionBean;
import util.FuncionesUtiles;

/**
 *
 * @author C613702
 */
public final class Amortizacion {

    private String tipoCobro = "Anticipado";
    private int destino;
    private String fecha;
    private BigDecimal mesesAños;
    private BigDecimal sumCapital = new BigDecimal("0");
    private BigDecimal sumIntereses = new BigDecimal("0");
    private BigDecimal periodicidad;
    private BigDecimal plazo;
    private BigDecimal montoSolicitado;
    private BigDecimal numeroTotalCuotas;
    private BigDecimal cantidadAñoProyeccion;
    private BigDecimal cuotasCobroCapital;
    private BigDecimal tasa = new BigDecimal(24);
    private BigDecimal comisionFlat;
    private BigDecimal capital;
    private BigDecimal saldo = new BigDecimal("0");
    private BigDecimal interes;
    private BigDecimal montoCuota;
    AmortizacionBean amortizacionBean;

    /**
     * Constructor que permite obtener los valores necesarios para realizar las
     * diferentes amortizaciones
     *
     * @param plazo de tipo String
     * @param amortizacion de tipo int
     * @param montoSolicitado de tipo String
     * @param destino de tipo int
     * @param fecha de tipo String
     * @param tasa de tipo String
     * @param comisionFlat de tipo String
     */
    public Amortizacion(String plazo, int amortizacion, String montoSolicitado, int destino, String fecha, String tasa, String comisionFlat) {
        if (amortizacion == 5) {

            amortizacion = amortizacion + 1;
        }

        this.periodicidad = new BigDecimal(String.valueOf(amortizacion));
        this.plazo = new BigDecimal(plazo);
        this.mesesAños = new BigDecimal("12");
        this.montoSolicitado = new BigDecimal(montoSolicitado);
        this.numeroTotalCuotas = this.plazo.add(new BigDecimal("1"));
        this.cantidadAñoProyeccion = this.plazo.divide(mesesAños).add(new BigDecimal("1"));//año proyección       
        this.cuotasCobroCapital = this.plazo.divide(periodicidad);//cuotasEfectivas
        this.destino = destino;
        this.fecha = fecha;

        if (!comisionFlat.equals("0")) {
            this.comisionFlat = new BigDecimal(comisionFlat);
        }

    }

    /**
     * constructor que nos permite aceptar que metodo de amortización se va a
     * utilizar
     *
     * @param metodo para
     * @return
     */
    public ArrayList<AmortizacionBean> amortizacion(String metodo) {
        ArrayList<AmortizacionBean> retorno = new ArrayList<AmortizacionBean>();
        if (metodo.equals("Solicitud")) {
            retorno = amortizacionMontoSolicitud();
        } else if (metodo.equals("Deuda")) {
            retorno = amortizacionDeuda();
        }

        return retorno;
    }

    /**
     * Metodo que permite calcular la Amortización del Monto Solicitud
     *
     * @return ArrayList<AmortizacionBean>
     */
    public ArrayList<AmortizacionBean> amortizacionMontoSolicitud() {
        ArrayList<AmortizacionBean> amortizacion = new ArrayList<AmortizacionBean>();
        int cuotaActual = 0;
        int dia;
        int mes;
        int año;
        dia = Integer.parseInt(fecha.substring(0, 2));
        mes = Integer.parseInt(fecha.substring(3, 5));
        año = Integer.parseInt(fecha.substring(6, 10));
        for (int i = 0; i <= this.plazo.intValueExact(); i++) {
            amortizacionBean = new AmortizacionBean();
            if (i == 0) {
                this.capital = this.montoSolicitado;
                this.interes = this.capital.multiply(tasa).divide(mesesAños).divide(new BigDecimal("100"), 2, RoundingMode.HALF_DOWN);
                this.montoCuota = this.interes;
                this.saldo = this.capital;

            } else {
                this.capital = montoSolicitado.divide(this.plazo, 2, RoundingMode.HALF_DOWN);
                this.saldo = this.saldo.subtract(this.capital);
                this.interes = this.saldo.multiply(this.tasa).divide(this.mesesAños).divide(new BigDecimal("100"), 2, RoundingMode.HALF_UP);
                this.montoCuota = this.interes.add(this.capital);

            }

            amortizacionBean.setNumeroTotalCuotas(this.numeroTotalCuotas);
            amortizacionBean.setTasa(this.tasa);
            amortizacionBean.setPlazo(this.plazo);
            amortizacionBean.setCapital(this.capital);
            amortizacionBean.setInteres(FuncionesUtiles.valorFinal(this.interes));     
            amortizacionBean.setSaldo(FuncionesUtiles.valorFinal(this.saldo));
            amortizacionBean.setMontoCuota(montoCuota);
            amortizacionBean.setNroCuota(cuotaActual);
            amortizacionBean.setAñoProyeccion(cantidadAñoProyeccion);

            if (cuotaActual == 0) {
                mes = mes;
            } else if (mes < 12) {
                mes = mes + 1;
            } else {
                mes = 1;
                año = año + 1;
            }           
            amortizacionBean.setFechaCobro(String.valueOf(dia) + "/" + FuncionesUtiles.formatearMes(mes) + "/" + String.valueOf(año));
            cuotaActual = cuotaActual + 1;
            amortizacion.add(amortizacionBean);
        }
        return amortizacion;
    }

    /**
     * Metodo que permite calcular la Amortización de la Deuda
     *
     * @return ArrayList<AmortizacionBean>
     */
    public ArrayList<AmortizacionBean> amortizacionDeuda() {
        ArrayList<AmortizacionBean> amortizacion = new ArrayList<AmortizacionBean>();
        int cuotaActual = 0;
        int dia;
        int mes;
        int año;
        dia = Integer.parseInt(this.fecha.substring(0, 2));
        mes = Integer.parseInt(this.fecha.substring(3, 5));
        año = Integer.parseInt(this.fecha.substring(6, 10));

        if (this.periodicidad.intValue() == 1) {
            while (cuotaActual < this.numeroTotalCuotas.intValueExact()) {
                amortizacionBean = new AmortizacionBean();

                if (cuotaActual == 0) {
                    this.capital = this.montoSolicitado;
                    this.interes = this.capital.multiply(this.tasa).divide(this.mesesAños).divide(new BigDecimal("100"), 2, RoundingMode.HALF_UP);
                    this.montoCuota = this.interes;
                    this.saldo = this.capital;

                } else {
                    this.capital = this.montoSolicitado.divide(this.plazo);
                    this.saldo = this.saldo.subtract(this.capital);
                    this.interes = this.capital.multiply(this.tasa).divide(this.mesesAños).divide(new BigDecimal("100"), 2, RoundingMode.HALF_UP);
                    this.montoCuota = this.interes.add(this.capital);

                }

                amortizacionBean.setNumeroTotalCuotas(this.numeroTotalCuotas);
                amortizacionBean.setTasa(this.tasa);
                amortizacionBean.setPlazo(this.plazo);
                amortizacionBean.setCapital(this.capital);
                amortizacionBean.setInteres(FuncionesUtiles.valorFinal(this.interes));
                amortizacionBean.setSaldo(FuncionesUtiles.valorFinal(this.saldo));
                amortizacionBean.setMontoCuota(this.montoCuota);
                amortizacionBean.setNroCuota(cuotaActual);
                amortizacionBean.setAñoProyeccion(cantidadAñoProyeccion);
                if (cuotaActual == 0) {
                    mes = mes;
                } else if (mes < 12) {
                    mes = mes + 1;
                } else {
                    mes = 1;
                    año = año + 1;
                }
      
                amortizacionBean.setFechaCobro(String.valueOf(dia) + "/" + FuncionesUtiles.formatearMes(mes) + "/" + String.valueOf(año));

                cuotaActual = cuotaActual + 1;
                amortizacion.add(amortizacionBean);
            }

        } else {

            while (cuotaActual < this.numeroTotalCuotas.intValue()) {
                amortizacionBean = new AmortizacionBean();

                if (cuotaActual == 0) {

                    this.capital = this.montoSolicitado;
                    this.interes = this.capital.multiply(this.tasa).divide(mesesAños).divide(new BigDecimal("100"), 2, RoundingMode.HALF_DOWN);
                    this.montoCuota = this.interes;
                    this.saldo = this.capital;

                } else if (cuotaActual % this.periodicidad.intValue() == 0) {
                    this.capital = this.montoSolicitado.divide(this.cuotasCobroCapital, 2, RoundingMode.HALF_DOWN);
                    this.saldo = this.saldo.subtract(this.capital);
                    this.interes = this.saldo.multiply(this.tasa).divide(mesesAños).divide(new BigDecimal("100"), 2, RoundingMode.HALF_DOWN);
                    this.montoCuota = this.interes.add(this.capital);

                } else {
                    this.capital = new BigDecimal("0");
                    this.saldo = this.saldo.subtract(this.capital);
                    this.interes = this.saldo.multiply(this.tasa).divide(mesesAños).divide(new BigDecimal("100"), 2, RoundingMode.HALF_UP);
                    this.montoCuota = this.interes.add(this.capital);

                }

                amortizacionBean.setNumeroTotalCuotas(this.numeroTotalCuotas);
                amortizacionBean.setTasa(this.tasa);
                amortizacionBean.setPlazo(this.plazo);
                amortizacionBean.setCapital(this.capital);
                amortizacionBean.setInteres(FuncionesUtiles.valorFinal(this.interes));
                amortizacionBean.setSaldo(FuncionesUtiles.valorFinal(this.saldo));
                amortizacionBean.setNroCuota(cuotaActual);
                amortizacionBean.setMontoCuota(montoCuota);
                amortizacionBean.setAñoProyeccion(cantidadAñoProyeccion);
                if (cuotaActual == 0) {
                    mes = mes;
                } else if (mes < 12) {
                    mes = mes + 1;
                } else {
                    mes = 1;
                    año = año + 1;
                }
       
                amortizacionBean.setFechaCobro(String.valueOf(dia) + "/" + FuncionesUtiles.formatearMes(mes) + "/" + String.valueOf(año));
                cuotaActual = cuotaActual + 1;
                amortizacion.add(amortizacionBean);

            }
        }
        return amortizacion;
    }

    /**
     *
     * @return un Bigdecimal de porcion Corriente Corregida
     */
    public BigDecimal pCC() {
        BigDecimal porcionCorrienteCorregida = new BigDecimal("0");
        BigDecimal porAño;
        if (amortizacionBean.getDestino() == 2) {
            return porcionCorrienteCorregida;
        } else {
            for (int i = 1; i <= this.cantidadAñoProyeccion.intValue(); i++) {
                if (i == 1) {
                    porAño = new BigDecimal(String.valueOf(i));
                    porcionCorrienteCorregida = this.sumCapital.divide(porAño);
                }
            }
            return porcionCorrienteCorregida;
        }
    }

    /**
     *
     * @return un BigDecimal de porcion Corriente Deuda Largo Plazo
     */
    public BigDecimal pCDLP() {
        BigDecimal porcionCorrienteDeudaLargoPlazo = new BigDecimal("0");
        BigDecimal porAño;
        if (amortizacionBean.getDestino() == 2) {
            porcionCorrienteDeudaLargoPlazo = new BigDecimal("0");
        } else {

            for (int i = 1; i <= this.cantidadAñoProyeccion.intValue(); i++) {
                if (i >= 2) {
                    porAño = new BigDecimal(String.valueOf(i));
                    porcionCorrienteDeudaLargoPlazo = this.sumCapital.divide(porAño);
                }
            }
        }
        return porcionCorrienteDeudaLargoPlazo;
    }

    /**
     *
     * @return un Bigdecimal de deuda Largo Plazo
     */
    public BigDecimal dLP() {
        BigDecimal deudaLargoPlazo = new BigDecimal("0");
        if (amortizacionBean.getDestino() == 2) {
            return deudaLargoPlazo;
        } else if (this.cantidadAñoProyeccion.intValue() == 0) {
            deudaLargoPlazo = amortizacionBean.getCapital().subtract(pCC().subtract(pCDLP()));

        } else {
            deudaLargoPlazo = deudaLargoPlazo.subtract(pCDLP());
        }

        return deudaLargoPlazo;
    }

    /**
     *
     * @return un BigDecimal de intereses Acumulados
     */
    public BigDecimal iA() {
        BigDecimal interesesAcumulados = new BigDecimal("0");
        if (this.cantidadAñoProyeccion.intValue() == 0) {
            if (amortizacionBean.getDestino() == 2) {
                interesesAcumulados = amortizacionBean.getCapital().multiply(this.comisionFlat);
            } else {
                interesesAcumulados = amortizacionBean.getInteres().add(amortizacionBean.getCapital()).multiply(this.comisionFlat);
            }
        } else {
            interesesAcumulados = amortizacionBean.getInteres().divide(this.cantidadAñoProyeccion);
        }

        return interesesAcumulados;
    }

    /**
     *
     * @return un BigDecima de porcion Corriente Corregida
     */
    public BigDecimal pCCAF() {
        BigDecimal porcionCorrienteCorregida = new BigDecimal("0");
        if (this.cantidadAñoProyeccion.intValue() == 1) {
            porcionCorrienteCorregida = amortizacionBean.getCapital();
        }
        return porcionCorrienteCorregida;
    }

    /**
     *
     * @return un BigDecimal de porcion Corriente Corregida
     */
    public BigDecimal pCDLPAF() {
        BigDecimal porcionCorrienteCorregida = new BigDecimal("0");
        for (int i = 0; i < this.cantidadAñoProyeccion.intValue(); i++) {
            porcionCorrienteCorregida = amortizacionBean.getInteres().add(amortizacionBean.getInteres()).divide(amortizacionBean.getCantidadAñoProyeccion());
        }
        return porcionCorrienteCorregida = amortizacionBean.getInteres();
    }

    /**
     *
     * @return un BigDecimal de deuda Largo Plazo
     */
    public BigDecimal dLPAF() {
        BigDecimal deudaLargoPlazo = new BigDecimal("0");

        if (this.cantidadAñoProyeccion.intValue() == 0) {
            deudaLargoPlazo = amortizacionBean.getCapital().subtract(pCC().subtract(pCDLP()));
        } else {
            deudaLargoPlazo = deudaLargoPlazo.subtract(pCDLP());
        }

        return deudaLargoPlazo;
    }

    /**
     *
     * @return un BigDecimal de intereses Acumulados
     */
    public BigDecimal iAAF() {
        BigDecimal interesesAcumulados = new BigDecimal("0");

        return interesesAcumulados;
    }

}
