/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package models;

/**
 *
 * @author b586854
 */
public class ParametrosBean {
   
    private int _Anio;
    private double _InflacionEstimada;

    public ParametrosBean(int _Anio, double _InflacionEstimada) {
        this._Anio = _Anio;
        this._InflacionEstimada = _InflacionEstimada;
    }

  
    
    public int getAnio() {
        return _Anio;
    }

    public void setAnio(int _Anio) {
        this._Anio = _Anio;
    }

    public double getInflacionEstimada() {
        return _InflacionEstimada;
    }

    public void setInflacionEstimada(double _InflacionEstimada) {
        this._InflacionEstimada = _InflacionEstimada;
    }
    
    
}
