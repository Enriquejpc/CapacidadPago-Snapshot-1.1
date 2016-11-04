package controlador.amortizaciones;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.ArrayList;
import models.AmortizacionBean;
import models.SolicitudBean;
import util.FuncionesUtiles;

/**
 *
 * @author C613702
 */
public final class Amortizacion {

    private String tipoCobro = "Anticipado";
    private int destino;
    private String fecha;
    int cuotaActual = 0;
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
    BigDecimal _auxCapital = new BigDecimal(BigInteger.ZERO);
    BigDecimal Zero = new BigDecimal(BigInteger.ZERO);
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
        this.montoSolicitado = new BigDecimal(montoSolicitado.replaceAll(",", "."));

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
     * Constructor que nos permite aceptar que metodo de amortización se va a
     * utilizar
     *
     * @param metodo para
     * @return
     */
    public ArrayList<AmortizacionBean> amortizacion(String metodo) {
      
        ArrayList<AmortizacionBean> retorno = new ArrayList<AmortizacionBean>();
        if (metodo.equals("Solicitud") || metodo.equals("Deuda")) {
            retorno = amortizacionDeudas(metodo);
        }
       
        return retorno;
    }

    /**
     * Metodo que permite calcular la Amortización del Monto Solicitud
     *
     * @return ArrayList<AmortizacionBean>
     * @param Metodo --> referencia al metodo porque si es la deuda por la
     * solicitud debo añadir a los intereses del primer año el cobro de la
     * comision flat
     */
    public ArrayList<AmortizacionBean> amortizacionDeudas(String metodo) {
        ArrayList<AmortizacionBean> amortizacion = new ArrayList<AmortizacionBean>();
        int dia;
        int mes;
        int año;
        dia = Integer.parseInt(fecha.substring(0, 2));
        mes = Integer.parseInt(fecha.substring(3, 5));
        año = Integer.parseInt(fecha.substring(6, 10));
        /**
         * Calculo de las amortizaciones
         */
        for (int _cuotaActual = 0; _cuotaActual <= this.plazo.intValueExact(); _cuotaActual++) {
            amortizacionBean = new AmortizacionBean();
            if (_cuotaActual == 0) {
                this.capital = this.montoSolicitado;
                if (this.tipoCobro.equalsIgnoreCase("Anticipado")) {
                    this.interes = this.capital.multiply(tasa).divide(mesesAños).divide(new BigDecimal("100"), 2, RoundingMode.HALF_DOWN);
                } else {
                    this.interes = Zero;
                }
                this.montoCuota = this.interes;
                this.saldo = this.capital;

            } else {
                if (this.periodicidad.intValue() != 1) {
                    if (_cuotaActual % this.periodicidad.intValue() == 0) {
                        _auxCapital = this.capital;
                        this.capital = montoSolicitado.divide(this.cuotasCobroCapital, 2, RoundingMode.HALF_DOWN);
                        this.saldo = this.saldo.subtract(this.capital);//_auxCapital.subtract(this.capital);//Saldo - capital
                        this.interes = this.saldo.multiply(this.tasa).divide(this.mesesAños).divide(new BigDecimal("100"), 2, RoundingMode.HALF_UP);
                        this.montoCuota = this.interes.add(this.capital);
                    } else {
                        _auxCapital = this.capital;
                        this.capital = Zero;

                        if (this.capital.intValue() != 0) {
                            this.saldo = _auxCapital.subtract(this.capital);
                        } else {
                            this.saldo = this.saldo;
                        }
                        this.interes = this.saldo.multiply(this.tasa).divide(this.mesesAños).divide(new BigDecimal("100"), 2, RoundingMode.HALF_UP);
                        this.montoCuota = this.interes.add(this.capital);
                    }
                } else {
                    this.capital = montoSolicitado.divide(this.plazo, 2, RoundingMode.HALF_DOWN);
                    this.saldo = this.saldo.subtract(this.capital);
                    this.interes = this.saldo.multiply(this.tasa).divide(this.mesesAños).divide(new BigDecimal("100"), 2, RoundingMode.HALF_UP);
                    this.montoCuota = this.interes.add(this.capital);
                }
            }
            /**
             * Fin Calculo de las amortizaciones
             */
            amortizacionBean.setNumeroTotalCuotas(this.numeroTotalCuotas);
            amortizacionBean.setTasa(this.tasa);
            amortizacionBean.setPlazo(this.plazo);
            amortizacionBean.setCapital(this.capital);
            amortizacionBean.setInteres(FuncionesUtiles.valorFinal(this.interes));
            amortizacionBean.setSaldo(FuncionesUtiles.valorFinal(this.saldo));
            amortizacionBean.setMontoCuota(montoCuota);
            amortizacionBean.setNroCuota(_cuotaActual);
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
        amortizacionBean.setInteresesAnio(iA(amortizacion, metodo));
        amortizacionBean.setPorcionCteCorrAnio(pCC(amortizacion, metodo));
        amortizacionBean.setPorcionCteCorrDeudaLPAnio(pCDLP(amortizacion, metodo));
        amortizacionBean.setDeudaLargoPzo(dLP(amortizacion)); 
        return amortizacion;
    }

    /**
     * @param Amortización envia la tabla de amortización, metodo indica si la
     * deuda es por solicitud o deuda anterior
     * @return un Bigdecimal de porcion Corriente Corregida
     */
    public BigDecimal[] pCC(ArrayList<AmortizacionBean> amortizacion, String metodo) {

        BigDecimal capitalAcumulado = new BigDecimal(BigInteger.ZERO);
        String _date[] = this.fecha.split("/");//Fecha de Liquidacion
        int mesCurso = Integer.parseInt(_date[1]) + 1;//Sumamos 1 al mes por que la porcion corriente se empieza a calcular al mes siguiente
        int plazoAnualizado = (this.plazo.intValue() / 12) + 1;//Plazo anualizado
        BigDecimal porcionCorrienteCorregida[] = new BigDecimal[plazoAnualizado];//Array de prueba para guardar los interese acumulados por año
        int anioCtrl = 0;//Año que me indica la posicion donde guardare los intereses acumulados

        for (int y = 1; (y <= this.plazo.intValue())/*&& anioCtrl==0*/; y++) {//for comienza en 1 porque se ignora la cuota cero
            if (mesCurso <= 12) {// Se hace hasta 12 porque los intereses acumulados son calculados por la anualidad
                if (anioCtrl == 0) {
                    capitalAcumulado = capitalAcumulado.add(amortizacion.get(y).getCapital());
                } else {
                    capitalAcumulado = new BigDecimal(BigInteger.ZERO);
                }
                mesCurso++;
                porcionCorrienteCorregida[anioCtrl] = capitalAcumulado;

            } else {
                anioCtrl++; //aumento el año de proyección
                mesCurso = 1;// Llevo los meses a 1, para que sume los intereses de cada mes               
                capitalAcumulado = new BigDecimal(BigInteger.ZERO);
                y = y - 1;// es mi variable de control, resto uno, para evitar que se salte una cuota
            }
        }
        return porcionCorrienteCorregida;
    }

       /**
     * @param Amortización envia la tabla de amortización, metodo indica si la
     * deuda es por solicitud o deuda anterior
     * @return un Bigdecimal de porcion Corriente Corregida
     */
    public BigDecimal[] pCDLP(ArrayList<AmortizacionBean> amortizacion, String metodo) {

        BigDecimal capitalAcumulado = new BigDecimal(BigInteger.ZERO);
        String _date[] = this.fecha.split("/");//Fecha de Liquidacion
        int mesCurso = Integer.parseInt(_date[1]) + 1;//Sumamos 1 al mes por que la porcion corriente se empieza a calcular al mes siguiente
        int plazoAnualizado = (this.plazo.intValue() / 12) + 1;//Plazo anualizado
        BigDecimal porcionCorrienteDeudaLargop[] = new BigDecimal[plazoAnualizado];//Array de prueba para guardar los interese acumulados por año
        int anioCtrl = 0;//Año que me indica la posicion donde guardare los intereses acumulados

        for (int y = 1; (y <= this.plazo.intValue())/*&& anioCtrl==0*/; y++) {//for comienza en 1 porque se ignora la cuota cero
            if (mesCurso <= 12) {// Se hace hasta 12 porque los intereses acumulados son calculados por la anualidad
                if (anioCtrl > 0) {
                    capitalAcumulado = capitalAcumulado.add(amortizacion.get(y).getCapital());
                } else {
                    capitalAcumulado = new BigDecimal(BigInteger.ZERO);
                }
                mesCurso++;
                porcionCorrienteDeudaLargop[anioCtrl] = capitalAcumulado;

            } else {
                anioCtrl++; //aumento el año de proyección
                mesCurso = 1;// Llevo los meses a 1, para que sume los intereses de cada mes
                //_yyyy = _yyyy - 1;
                capitalAcumulado = new BigDecimal(BigInteger.ZERO);
                y = y - 1;// es mi variable de control, resto uno, para evitar que se salte una cuota
            }
        }
        return porcionCorrienteDeudaLargop;
    }

    /**
     *
     * @return un Bigdecimal de deuda Largo Plazo
     */
    public BigDecimal []dLP(ArrayList<AmortizacionBean> amortizacion) {
        
        int plazo = this.plazo.intValue();// Plazo en Meses
        int plazoAnualizado = (plazo / 12) + 1;//Plazo anualizado
        int ctrlPCDLP = 1;
        BigDecimal Cero = new BigDecimal(BigInteger.ZERO);
        BigDecimal deudaLargoPlazo[] = new BigDecimal[plazoAnualizado];
        BigDecimal value = new BigDecimal(BigInteger.ZERO);
        
        if (amortizacionBean.getDestino() == 2) {
            return deudaLargoPlazo;
        } else{ 
            for (int x = 0; (x<=plazoAnualizado );x++)
            {
                if (x == 0){
                    value = this.montoSolicitado.subtract(amortizacionBean.getPorcionCteCorrDeudaLPAnio()[ctrlPCDLP]);
                    value = value.subtract(amortizacionBean.getPorcionCteCorrAnio()[x]); 
                }else{
                    ctrlPCDLP++;
                    value = value.subtract(amortizacionBean.getPorcionCteCorrDeudaLPAnio()[ctrlPCDLP]); 
                }
            deudaLargoPlazo[x] = value;  
            if(value.intValueExact() == 0){                    
                    for(int i=(x+1);i<plazoAnualizado;i++){
                        deudaLargoPlazo[i] = Cero;
                    }
                    break;
                }
            }
          
            
           /*for (int z=0;z<deudaLargoPlazo.length;z++)
            {
                if(deudaLargoPlazo[z]== null)
                {
                    System.out.println("NULLAZO");
                    break;
                }else{
                System.out.println("erere:"+deudaLargoPlazo[z]);
                }
            }*/
            
           
        }
        return deudaLargoPlazo;
    }

    /**
     *
     * @param amortizacion
     * @param metodo
     * @return un BigDecimal de intereses Acumulados
     */
    public BigDecimal[] iA(ArrayList<AmortizacionBean> amortizacion, String metodo) {
        BigDecimal interesesAcumulados = new BigDecimal("0");
        SolicitudBean _acceso = new SolicitudBean();
        String _date[] = this.fecha.split("/");//Fecha de Liquidacion
        int plazo = this.plazo.intValue();// Plazo en Meses
        int mesCurso = Integer.parseInt(_date[1]);//
       /* int _yyyy = Integer.parseInt(_date[2]);
         int valor = _acceso.getDestino();*/
        int plazoAnualizado = (plazo / 12) + 1;//Plazo anualizado
        BigDecimal interesesAnio[] = new BigDecimal[plazoAnualizado];//Array de prueba para guardar los interese acumulados por año
        int anioCtrl = 0;//Año que me indica la posicion donde guardare los intereses acumulados
        BigDecimal CobroFlat = new BigDecimal(BigInteger.ZERO);

        if ("Solicitud".compareToIgnoreCase(metodo) == 0) {

            CobroFlat = ((this.comisionFlat).multiply(this.montoSolicitado)).divide(new BigDecimal("100"), 2, RoundingMode.HALF_UP);
        } else {
            CobroFlat = new BigDecimal(BigInteger.ZERO);
        }

        for (int y = 0; y <= this.plazo.intValue(); y++) {
            if (mesCurso <= 12) {// Se hace hasta 12 porque los intereses acumulados son calculados por la anualidad
                interesesAcumulados = interesesAcumulados.add(amortizacion.get(y).getInteres());
                mesCurso++;
                interesesAnio[anioCtrl] = interesesAcumulados;

            } else {
                if (anioCtrl == 0) {
                    interesesAnio[anioCtrl] = interesesAnio[anioCtrl].add(CobroFlat);
                }
                anioCtrl++; //aumento el año de proyección
                mesCurso = 1;// Llevo los meses a 1, para que sume los intereses de cada mes
                //_yyyy = _yyyy - 1;
                interesesAcumulados = new BigDecimal(BigInteger.ZERO);
                y = y - 1;// es mi variable de control, resto uno, para evitar que se salte una cuota
            }
        }
        return interesesAnio;
    }

    /**
     *
     * @return un BigDecima de porcion Corriente Corregida
     */
    /*public BigDecimal pCCAF() {
     BigDecimal porcionCorrienteCorregida = new BigDecimal("0");
     if (this.cantidadAñoProyeccion.intValue() == 1) {
     porcionCorrienteCorregida = amortizacionBean.getCapital();
     }
     return porcionCorrienteCorregida;
     }*/
    /**
     *
     * @return un BigDecimal de porcion Corriente Corregida
     */
    /*public BigDecimal pCDLPAF() {
     BigDecimal porcionCorrienteCorregida = new BigDecimal("0");
     for (int i = 0; i < this.cantidadAñoProyeccion.intValue(); i++) {
     porcionCorrienteCorregida = amortizacionBean.getInteres().add(amortizacionBean.getInteres()).divide(amortizacionBean.getCantidadAñoProyeccion());
     }
     return porcionCorrienteCorregida = amortizacionBean.getInteres();
     }*/
    /**
     *
     * @return un BigDecimal de deuda Largo Plazo
     */
    /* public BigDecimal dLPAF() {
     BigDecimal deudaLargoPlazo = new BigDecimal("0");

     if (this.cantidadAñoProyeccion.intValue() == 0) {
     deudaLargoPlazo = amortizacionBean.getCapital().subtract(pCC().subtract(pCDLP()));
     } else {
     deudaLargoPlazo = deudaLargoPlazo.subtract(pCDLP());
     }

     return deudaLargoPlazo;
     }*/
    /**
     * Metodo que permite calcular la Amortización de la Deuda
     *
     * @return ArrayList<AmortizacionBean>
     */
    /* public ArrayList<AmortizacionBean> DeprecatedamortizacionDeuda() {
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
     }*/
}
