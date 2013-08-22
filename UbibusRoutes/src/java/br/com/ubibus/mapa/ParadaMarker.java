/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ubibus.mapa;

import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.Marker;

/**
 *
 * @author <a href="mailto:anacm.maciel@gmail.com">Ana Claudia Maciel</a>
 */
public class ParadaMarker extends Marker{
    
    private Integer idParada;

    public ParadaMarker(LatLng latlng, String title, Object data, String icon, String shadow) {
        super(latlng, title, data, icon, shadow);
    }

    public ParadaMarker(LatLng latlng, String title, Object data, String icon) {
        super(latlng, title, data, icon);
    }

    public ParadaMarker(LatLng latlng, String title, Object data) {
        super(latlng, title, data);
    }

    public ParadaMarker(LatLng latlng, String title) {
        super(latlng, title);
    }

    public ParadaMarker(LatLng latlng) {
        super(latlng);
    }

    public ParadaMarker(Integer idParada, LatLng latlng) {
        super(latlng);
        this.idParada = idParada;
    }

    public Integer getIdParada() {
        return idParada;
    }

    public void setIdParada(Integer idParada) {
        this.idParada = idParada;
    }
    
}
