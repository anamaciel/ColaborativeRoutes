package br.com.ubibus.mapa;

import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.Marker;

/**
 *
 * @author <a href="mailto:marvas1987@gmail.com">Marcelo F. Vasconcelos</a>
 */
public class OcorrenciaMarker extends Marker {

    private Integer idOcorreincia;

    public OcorrenciaMarker(LatLng latlng, String title, Object data, String icon, String shadow) {
        super(latlng, title, data, icon, shadow);
    }

    public OcorrenciaMarker(LatLng latlng, String title, Object data, String icon) {
        super(latlng, title, data, icon);
    }

    public OcorrenciaMarker(LatLng latlng, String title, Object data) {
        super(latlng, title, data);
    }

    public OcorrenciaMarker(LatLng latlng, String title) {
        super(latlng, title);
    }

    public OcorrenciaMarker(LatLng latlng) {
        super(latlng);
    }

    public OcorrenciaMarker(Integer idOcorreincia, LatLng latlng) {
        super(latlng);
        this.idOcorreincia = idOcorreincia;
    }

    public Integer getIdOcorreincia() {
        return idOcorreincia;
    }

    public void setIdOcorreincia(Integer idOcorreincia) {
        this.idOcorreincia = idOcorreincia;
    }
        
}
