/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ubibus.mapa;

import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.Marker;



/**
 *
 * @author AnaMaciel
 */
public class PontoParadaMarker extends Marker{
    
    private Integer idPontoParada;

    public PontoParadaMarker(LatLng latlng, String title, Object data, String icon, String shadow) {
        super(latlng, title, data, icon, shadow);
    }

    public PontoParadaMarker(LatLng latlng, String title, Object data, String icon) {
        super(latlng, title, data, icon);
    }

    public PontoParadaMarker(LatLng latlng, String title, Object data) {
        super(latlng, title, data);
    }

    public PontoParadaMarker(LatLng latlng, String title) {
        super(latlng, title);
    }

    public PontoParadaMarker(LatLng latlng) {
        super(latlng);
    }

    public PontoParadaMarker(Integer idPontoParada, LatLng latlng) {
        super(latlng);
        this.idPontoParada = idPontoParada;
    }

    public Integer getIdPontoParada() {
        return idPontoParada;
    }

    public void setIdPontoParada(Integer idPontoParada) {
        this.idPontoParada = idPontoParada;
    }
    
    

    
}
