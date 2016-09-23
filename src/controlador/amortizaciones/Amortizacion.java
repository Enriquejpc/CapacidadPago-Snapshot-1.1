
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
     * constructor que nos permite aceptar que metodo de amortización se va a
     * utilizar
     *
     * @param metodo para
     * @return
     */
    public ArrayList<AmortizacionBean> amortizacion(String metodo) {
       int cont = 0;
       ArrayList<AmortizacionBean> retorno = new ArrayList<AmortizacionBean>();
       if (metodo.equals("Solicitud") || metodo.equals("Deuda")) {
           cont++; 
           retorno = amortizacionDeudas(metodo);
        } 
        System.out.println("Total"+cont);
        return retorno;
    }

    /**
     * Metodo que permite calcular la Amortización del Monto Solicitud
     *
     * @return ArrayList<AmortizacionBean>
     * @param Metodo --> referencia al metodo porque si es la deuda por la solicitud debo añadir a los intereses del primer año el cobro de la comision flat
     */
    public ArrayList<AmortizacionBean> amortizacionDeudas(String metodo) {
        ArrayList<AmortizacionBean> amortizacion = new ArrayList<AmortizacionBean>();
        int dia;
        int mes;
        int año;
        dia = Integer.parseInt(fecha.substring(0, 2));
        mes = Integer.parseInt(fecha.substring(3, 5));
        año = Integer.parseInt(fecha.substring(6, 10));
       
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
                if(this.periodicidad.intValue()!=1){
                     if (_cuotaActual%this.periodicidad.intValue()==0){
                        _auxCapital = this.capital;
                        this.capital = montoSolicitado.divide( this.cuotasCobroCapital, 2, RoundingMode.HALF_DOWN);  
                        this.saldo = this.saldo.subtract(this.capital);//_auxCapital.subtract(this.capital);//Saldo - capital
                        this.interes = this.saldo.multiply(this.tasa).divide(this.mesesAños).divide(new BigDecimal("100"), 2, RoundingMode.HALF_UP);
                        this.montoCuota = this.interes.add(this.capital);
                     }else{                        
                        _auxCapital = this.capital;
                        this.capital = Zero;
                        
                        if(this.capital.intValue()!=0){
                            this.saldo = _auxCapital.subtract(this.capital);
                        }else{
                            this.saldo = this.saldo;
                        }
                        this.interes = this.saldo.multiply(this.tasa).divide(this.mesesAños).divide(new BigDecimal("100"), 2, RoundingMode.HALF_UP);  
                        this.montoCuota = this.interes.add(this.capital);  
                     }
                }else{
                    this.capital = montoSolicitado.divide(this.plazo, 2, RoundingMode.HALF_DOWN);
                    this.saldo = this.saldo.subtract(this.capital);
                    this.interes = this.saldo.multiply(this.tasa).divide(this.mesesAños).divide(new BigDecimal("100"), 2, RoundingMode.HALF_UP);
                    this.montoCuota = this.interes.add(this.capital);
                }
             }
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
           amortizacionBean.setInteresesAnio( iA(amortizacion,metodo));
        return amortizacion;
    }

  /**
     *
     * @return un Bigdecimal de porcion Corriente Corregida
     */
    public BigDecimal pCC(ArrayList<AmortizacionBean> amortizacion) {
        BigDecimal porcionCorrienteCorregida = new BigDecimal("0");
        BigDecimal porAño;
        //int a = amortizacionBean.getDestino() ;
        if (amortizacionBean.getDestino() == 2) {
           // System.out.println("a");
            return porcionCorrienteCorregida;
        } else {
            for (int i = 1; i <= this.cantidadAñoProyeccion.intValue(); i++) {
                if (i == 1) {
                    porAño = new BigDecimal(String.valueOf(i));
                    porcionCorrienteCorregida = this.sumCapital.divide(porAño);
                    //System.out.println("PCC"+porcionCorrienteCorregida);
                }
            }
            return porcionCorrienteCorregida;
        }
    }

    /**
     *
     * @return un BigDecimal de porcion Corriente Deuda Largo Plazo
     */
    public BigDecimal pCDLP(ArrayList<AmortizacionBean> amortizacion) {
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
    public BigDecimal dLP(ArrayList<AmortizacionBean> amortizacion) {
        BigDecimal deudaLargoPlazo = new BigDecimal("0");
        if (amortizacionBean.getDestino() == 2) {
            return deudaLargoPlazo;
        } else if (this.cantidadAñoProyeccion.intValue() == 0) {
            deudaLargoPlazo = amortizacionBean.getCapital().subtract(pCC(amortizacion).subtract(pCDLP(amortizacion)));

        } else {
            deudaLargoPlazo = deudaLargoPlazo.subtract(pCDLP(amortizacion));
        }

        return deudaLargoPlazo;
    }

    /**
     *
     * @return un BigDecimal de intereses Acumulados
     */
    public BigDecimal[] iA(ArrayList<AmortizacionBean> amortizacion, String metodo) {
        BigDecimal interesesAcumulados = new BigDecimal("0");
        SolicitudBean _acceso = new SolicitudBean();
        String _date[] = this.fecha.split("/");//Fecha de Liquidacion
        int plazo =  this.plazo.intValue();// Plazo en Meses
        int _mm = Integer.parseInt(_date[1]);// 
        int _yyyy = Integer.parseInt(_date[2]);
        int valor = _acceso.getDestino();
        int plazoAnualizado = (plazo/12)+1;//Plazo anualizado
        BigDecimal interesesAnio[] = new BigDecimal[plazoAnualizado];//Array de prueba para guardar los interese acumulados por año
        int anioCtrl = 0 ;//Año que me indica la posicion donde guardare los intereses acumulados
        BigDecimal CobroFlat = amortizacionBean.getComisionFlat().multiply(amortizacionBean.getMontoSolicitado());
        if("Solicitud".compareToIgnoreCase(metodo) == 0){
            CobroFlat = amortizacionBean.getComisionFlat().multiply(amortizacionBean.getMontoSolicitado());
        }else{
            CobroFlat = new BigDecimal(BigInteger.ZERO);
        }
        
        for (int y = 0; y<=this.plazo.intValue(); y++ )
        {
         if (_mm<=12){
            interesesAcumulados = interesesAcumulados.add(amortizacion.get(y).getInteres());
            _mm++;
            interesesAnio[anioCtrl] = interesesAcumulados;
           
         } else{
             //System.out.println("Salida"+interesesAcumulados);
            anioCtrl++; 
            _mm = 1;
            _yyyy = _yyyy - 1;
            interesesAcumulados = new BigDecimal(BigInteger.ZERO);
            y = y - 1;
         }
        }
      
    /*  for (int x = 0;x<plazoAnualizado;x++){
          System.out.println(interesesAnio[x]);
      }
        /* if (valor == 1)// Capital de Trabajo
       {
           for (int i = 0;i<this.cantidadAñoProyeccion.intValue(); i++)
           {
                if (i == 0){
                    interesesAcumulados = amortizacion.get(i).getCapital().multiply(this.comisionFlat);
                }else{
                    //interesesAcumulados = 
                }
           }
       }*/
       // System.out.println("Value-->"+interesesAnio);
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
