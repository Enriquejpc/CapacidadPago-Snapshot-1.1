/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.math.BigDecimal;
/**
 *
 * @author b586854
 * @version 1.0
 */
public class DeudasBean {

    private BigDecimal saldoBcoMercantilDct;
    private BigDecimal saldoBcoBanescoDct;
    private BigDecimal saldoBcoProvincialDct;
    private BigDecimal saldoBcoMercantilDaf;
    private BigDecimal saldoBcoBanescoDaf;
    private BigDecimal saldoBcoProvincialDaf;
    private String fechaMercantil;
    private String fechaBanesco;
    private String fechaProvincial;
    private BigDecimal plazoMercantil;
    private BigDecimal plazoBanesco;
    private BigDecimal plazoProvincial;
    private BigDecimal tasaMercantil;
    private BigDecimal tasaBanesco;
    private BigDecimal tasaProvincial;
    private int amortizacionMercantil;
    private int amortizacionBanesco;
    private int amortizacionProvincial;
    private BigDecimal totalDeuda;

    /**
     * Constructor que si usa para asignar los datos a las variables de las
     * clases a partir de las siguientes parametros
     *
     * @param banco indica el banco proviene la deuda, y es de tipo string
     * @param saldo indica el los saldos de la deuda, y es de tipo String
     * @param fecha indica la fecha en la cual se adquirio la deuda, y es de
     * tipo String
     * @param plazo indica el plazo de pago de la deuda, y es de tipo String
     * @param tasa indica la tasa que se calculara los intereses, y es de tipo
     * String
     * @param amortizacion indica en que periodo se amortizara la deuda, y es de
     * tipo String
     */
    @SuppressWarnings("empty-statement")
    public DeudasBean(String banco, String saldo, String fecha, String plazo, String tasa, String amortizacion) {

        if (banco.equals("001") && fecha.equals("no")) {
            this.saldoBcoMercantilDct = new BigDecimal(saldo);
        } else if (banco.equals("002") && fecha.equals("no")) {
            this.saldoBcoBanescoDct = new BigDecimal(saldo);;
        } else if (banco.equals("003") && fecha.equals("no")) {
            this.saldoBcoProvincialDct = new BigDecimal(saldo);
        } else if (banco.equals("001")) {
            this.saldoBcoMercantilDaf = new BigDecimal(saldo);
            this.fechaMercantil = fecha;
            this.plazoMercantil = new BigDecimal(plazo);
            this.tasaMercantil = new BigDecimal(tasa);

            switch (amortizacion) {

                case "MENSUAL":
                    this.amortizacionMercantil = 1;
                    break;
                case "BIMESTRAL":
                    this.amortizacionMercantil = 2;
                    break;
                case "TRIMESTRAL":
                    this.amortizacionMercantil = 3;
                    break;
                case "CUATRIMESTRAL":
                    this.amortizacionMercantil = 4;
                    break;
                case "SEMESTRAL":
                    this.amortizacionMercantil = 5;
                    break;
                default:
                    break;
            }
        } else if (banco.equals("002")) {
            this.saldoBcoBanescoDaf = new BigDecimal(saldo);
            this.fechaBanesco = fecha;
            this.plazoBanesco = new BigDecimal(plazo);
            this.tasaBanesco = new BigDecimal(tasa);
            switch (amortizacion) {
                case "MENSUAL":
                    this.amortizacionBanesco = 1;
                    break;
                case "BIMESTRAL":
                    this.amortizacionBanesco = 2;
                    break;
                case "TRIMESTRAL":
                    this.amortizacionBanesco = 3;
                    break;
                case "CUATRIMESTRAL":
                    this.amortizacionBanesco = 4;
                    break;
                case "SEMESTRAL":
                    this.amortizacionBanesco = 5;
                    break;
                default:
                    break;
            }

        } else if (banco.equals("003")) {
            this.saldoBcoProvincialDaf = new BigDecimal(saldo);
            this.fechaProvincial = fecha;
            this.plazoProvincial = new BigDecimal(plazo);
            this.tasaProvincial = new BigDecimal(tasa);

            switch (amortizacion) {
                case "MENSUAL":
                    this.amortizacionProvincial = 1;
                    break;
                case "BIMESTRAL":
                    this.amortizacionProvincial = 2;
                    break;
                case "TRIMESTRAL":
                    this.amortizacionProvincial = 3;
                    break;
                case "CUATRIMESTRAL":
                    this.amortizacionProvincial = 4;
                    break;
                case "SEMESTRAL":
                    this.amortizacionProvincial = 5;
                    break;
                default:
                    break;
            }
        }

    }

    /**
     * Metodo que obtiene el getSaldoBcoMercantilDct
     *
     * @return el saldo de la deuda en el banco Mercantil y es de tipo
     * BigDecimal
     */
    public BigDecimal getSaldoBcoMercantilDct() {
        return saldoBcoMercantilDct;
    }

    /**
     * Metodo donde se le asigna valos a la variable saldoBcoMercantil
     *
     * @param saldoBcoMercantilDct el saldo de la deuda en el banco Mercantil y
     * es de tipo BigDecimal
     */
    public void setSaldoBcoMercantilDct(BigDecimal saldoBcoMercantilDct) {
        this.saldoBcoMercantilDct = saldoBcoMercantilDct;
    }

    /**
     * Metodo que obtiene el saldoBcoBanesco
     *
     * @return el saldo de la deuda en el banco banesco y es de tipo BigDecimal
     */
    public BigDecimal getSaldoBcoBanescoDct() {
        return saldoBcoBanescoDct;
    }

    /**
     * Metodo donde se le asigna valos a la variable saldoBcoBanesco
     *
     * @param saldoBcoBanescoDct el saldo de la deuda en el banco banesco y es
     * de tipo BigDecimal
     */
    public void setSaldoBcoBanesco(BigDecimal saldoBcoBanescoDct) {
        this.saldoBcoBanescoDct = saldoBcoBanescoDct;
    }

    /**
     * Metodo que obtiene el saldoBcoProvincial
     *
     * @return el saldo de la deuda en el banco provincial y es de tipo
     * BigDecimal
     */
    public BigDecimal getSaldoBcoProvincialDct() {
        return saldoBcoProvincialDct;
    }

    /**
     * Metodo donde se le asigna valos a la variable saldoBcoProvincial
     *
     * @param saldoBcoProvincialDct el saldo de la deuda en el banco provincial
     * y es de tipo BigDecimal
     */
    public void setSaldoBcoProvincialDct(BigDecimal saldoBcoProvincialDct) {
        this.saldoBcoProvincialDct = saldoBcoProvincialDct;
    }

    /**
     * Metodo que obtiene el saldoBcoMercantilDaf
     *
     * @return el saldo de la deuda en el banco Mercantil activo fijo y es de
     * tipo BigDecimal
     */
    public BigDecimal getSaldoBcoMercantilDaf() {
        return saldoBcoMercantilDaf;
    }

    /**
     * Metodo donde se le asigna valos a la variable saldoBcoMercantilDaf
     *
     * @param saldoBcoMercantilDaf el saldo de la deuda en el banco Mercantil
     * activo fijo y es de tipo BigDecimal
     */
    public void setSaldoBcoMercantilDaf(BigDecimal saldoBcoMercantilDaf) {
        this.saldoBcoMercantilDaf = saldoBcoMercantilDaf;
    }

    /**
     * Metodo que obtiene el saldoBcoBanescoDaf
     *
     * @return el saldo de la deuda en el banco banesco activo fijo y es de tipo
     * BigDecimal
     */
    public BigDecimal getSaldoBcoBanescoDaf() {
        return saldoBcoBanescoDaf;
    }

    /**
     * Metodo donde se le asigna valos a la variable saldoBcoBanescoDaf
     *
     * @param saldoBcoBanescoDaf el saldo de la deuda en el banco banesco activo
     * fijo y es de tipo BigDecimal
     */
    public void setSaldoBcoBanescoDcp(BigDecimal saldoBcoBanescoDaf) {
        this.saldoBcoBanescoDaf = saldoBcoBanescoDaf;
    }

    /**
     * Metodo que obtiene el saldoBcoProvincialDaf
     *
     * @return el saldo de la deuda en el banco provincial activo fijo y es de
     * tipo BigDecimal
     */
    public BigDecimal getSaldoBcoProvincialDaf() {
        return saldoBcoProvincialDaf;
    }

    /**
     * Metodo donde se le asigna valos a la variable saldoBcoProvincialDaf
     *
     * @param saldoBcoProvincialDaf el saldo de la deuda en el banco provincial
     * activo fijo y es de tipo BigDecimal
     */
    public void setSaldoBcoProvincialDcp(BigDecimal saldoBcoProvincialDaf) {
        this.saldoBcoProvincialDaf = saldoBcoProvincialDaf;
    }

    /**
     * Metodo que obtiene el fechaMercantil
     *
     * @return la fecha que se adquirio la deuda en el banco mercantil y es de
     * tipo String
     */
    public String getFechaMercantil() {
        return fechaMercantil;
    }

    /**
     * Metodo donde se le asigna valos a la variable fechaMercantil
     *
     * @param fechaMercantil la fecha que se adquirio la deuda en el banco
     * Mercantil y es de tipo String
     */
    public void setFechaMercantil(String fechaMercantil) {
        this.fechaMercantil = fechaMercantil;
    }

    /**
     * Metodo que obtiene el fechaBanesco
     *
     * @return la fecha que se adquirio la deuda en el banco banesco y es de
     * tipo String
     */
    public String getFechaBanesco() {
        return fechaBanesco;
    }

    /**
     * Metodo donde se le asigna valos a la variable fechaBanesco
     *
     * @param fechaBanesco la fecha que se adquirio la deuda en el banco banesco
     * y es de tipo String
     */
    public void setFechaBanesco(String fechaBanesco) {
        this.fechaBanesco = fechaBanesco;
    }

    /**
     * Metodo que obtiene el fechaProvincial
     *
     * @return la fecha que se adquirio la deuda en el banco provincial y es de
     * tipo String
     */
    public String getFechaProvincial() {
        return fechaProvincial;
    }

    /**
     * Metodo donde se le asigna valos a la variable fechaProvincial
     *
     * @param fechaProvincial la fecha que se adquirio la deuda en el banco
     * provincial y es de tipo String
     */
    public void setFechaProvincial(String fechaProvincial) {
        this.fechaProvincial = fechaProvincial;
    }

    /**
     * Metodo que obtiene el plazoMercantil
     *
     * @return el plazo de pago de la deuda en el Mercantil, es de tipo int
     */
    public BigDecimal getPlazoMercantil() {
        return plazoMercantil;
    }

    /**
     * Metodo donde se le asigna valos a la variable plazoMercantil
     *
     * @param plazoMercantil el plazo de pago de la deuda en el Mercantil, es
     * tipo int
     */
    public void setPlazoMercantil(BigDecimal plazoMercantil) {
        this.plazoMercantil = plazoMercantil;
    }

    /**
     * Metodo que obtiene el plazoBanesco
     *
     * @return el plazo de pago de la deuda en el Banesco, es tipo int
     */
    public BigDecimal getPlazoBanesco() {
        return plazoBanesco;
    }

    /**
     * Metodo donde se le asigna valos a la variable plazoBanesco
     *
     * @param plazoBanesco el plazo de pago de la deuda en el Banesco, es tipo
     * int
     */
    public void setPlazoBanesco(BigDecimal plazoBanesco) {
        this.plazoBanesco = plazoBanesco;
    }

    /**
     * Metodo que obtiene el plazoProvincial
     *
     * @return el plazo de pago de la deuda en el Provincial, es tipo int
     */
    public BigDecimal getPlazoProvincial() {
        return plazoProvincial;
    }

    /**
     * Metodo donde se le asigna valos a la variable plazoProvincial
     *
     * @param plazoProvincial el plazo de pago de la deuda en el Provincial, es
     * tipo int
     */
    public void setPlazoProvincial(BigDecimal plazoProvincial) {
        this.plazoProvincial = plazoProvincial;
    }

    /**
     * Metodo que obtiene el tasaMercantil
     *
     * @return la tasa en que se va a calcular los intereses en el banco
     * Mercantil y es de tipo int
     */
    public BigDecimal getTasaMercantil() {
        return tasaMercantil;
    }

    /**
     * Metodo donde se le asigna valos a la variable tasaMercantil
     *
     * @param tasaMercantil la tasa en que se va a calcular los intereses en el
     * banco Mercantil y es de tipo int
     */
    public void setTasaMercantil(BigDecimal tasaMercantil) {
        this.tasaMercantil = tasaMercantil;
    }

    /**
     * Metodo que obtiene el tasaBanesco
     *
     * @return la tasa en que se va a calcular los intereses en el banco banesco
     * y es de tipo int
     */
    public BigDecimal getTasaBanesco() {
        return tasaBanesco;
    }

    /**
     * Metodo donde se le asigna valos a la variable tasaBanesco
     *
     * @param tasaBanesco la tasa en que se va a calcular los intereses en el
     * banco banesco y es de tipo int
     */
    public void setTasaBanesco(BigDecimal tasaBanesco) {
        this.tasaBanesco = tasaBanesco;
    }

    /**
     * Metodo que obtiene el tasaProvincial
     *
     * @return la tasa en que se va a calcular los intereses en el banco
     * provincial y es de tipo int
     */
    public BigDecimal getTasaProvincial() {
        return tasaProvincial;
    }

    /**
     * Metodo donde se le asigna valos a la variable tasaProvincial
     *
     * @param tasaProvincial la tasa en que se va a calcular los intereses en el
     * banco provincial y es de tipo int
     */
    public void setTasaProvincial(BigDecimal tasaProvincial) {
        this.tasaProvincial = tasaProvincial;
    }

    /**
     * Metodo que obtiene el amortizacionMercantil
     *
     * @return el periodo en que amortizara la deuda en el Banco Mercantil, es
     * de tipo int
     */
    public int getAmortizacionMercantil() {
        return amortizacionMercantil;
    }

    /**
     * Metodo donde se le asigna valos a la variable amortizacionMercantil
     *
     * @param amortizacionMercantil el periodo en que amortizara la deuda en el
     * Banco Mercantil, es de tipo int
     */
    public void setAmortizacionMercantil(int amortizacionMercantil) {
        this.amortizacionMercantil = amortizacionMercantil;
    }

    /**
     * Metodo que obtiene el amortizacionBanesco
     *
     * @return el periodo en que amortizara la deuda en el Banco banesco, es de
     * tipo int
     */
    public int getAmortizacionBanesco() {
        return amortizacionBanesco;
    }

    /**
     * Metodo donde se le asigna valos a la variable amortizacionBanesco
     *
     * @param amortizacionBanesco el periodo en que amortizara la deuda en el
     * Banco banesco, es de tipo int
     */
    public void setAmortizacionBanesco(int amortizacionBanesco) {
        this.amortizacionBanesco = amortizacionBanesco;
    }

    /**
     * Metodo que obtiene el amortizacionProvincial
     *
     * @return el periodo en que amortizara la deuda en el Banco provincial, es
     * de tipo int
     */
    public int getAmortizacionProvincial() {
        return amortizacionProvincial;
    }

    /**
     * Metodo donde se le asigna valos a la variable amortizacionProvincial
     *
     * @param amortizacionProvincial el periodo en que amortizara la deuda en el
     * Banco banesco, es de tipo int
     */
    public void setAmortizacionProvincial(int amortizacionProvincial) {
        this.amortizacionProvincial = amortizacionProvincial;
    }

    /**
     * Metodo que obtiene el totalDeuda
     *
     * @return el total de la deuda, es de tipo BigDecimal
     */
    public BigDecimal getTotalDeuda() {

        return totalDeuda;
    }

    /**
     * Metodo donde se le asigna valos a la variable totalDeuda
     *
     * @param totalDeuda el total de la deuda, es de tipo BigDecimal
     */
    public void setTotalDeuda(BigDecimal totalDeuda) {

        this.totalDeuda = totalDeuda;
    }
}
