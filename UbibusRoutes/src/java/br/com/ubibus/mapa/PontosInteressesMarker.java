/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ubibus.mapa;

import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.Marker;

/**
 *
 * @author marcelo
 */
public class PontosInteressesMarker extends Marker{
    
    private Integer idPontosInteresses;

    public PontosInteressesMarker(LatLng latlng, String title, Object data, String icon, String shadow) {
        super(latlng, title, data, icon, shadow);
    }

    public PontosInteressesMarker(LatLng latlng, String title, Object data, String icon) {
        super(latlng, title, data, icon);
    }

    public PontosInteressesMarker(LatLng latlng, String title, Object data) {
        super(latlng, title, data);
    }

    public PontosInteressesMarker(LatLng latlng, String title) {
        super(latlng, title);
    }

    public PontosInteressesMarker(LatLng latlng) {
        super(latlng);
    }

    public PontosInteressesMarker(Integer idPontosInteresses, LatLng latlng) {
        super(latlng);
        this.idPontosInteresses = idPontosInteresses;
    }

    public Integer getIdPontosInteresses() {
        return idPontosInteresses;
    }

    public void setIdPontosInteresses(Integer idPontosInteresses) {
        this.idPontosInteresses = idPontosInteresses;
    }
    
}
