/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ubibus.managedbean;

import br.com.ubibus.mapa.ParadaMarker;
import br.com.ubibus.mapa.PontoParadaMarker;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import org.primefaces.event.map.OverlaySelectEvent;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;

/**
 *
 * @author marcelo
 */
@ManagedBean(name = "addMarkerBean")
@RequestScoped
public class AddMarkerBean implements Serializable{
    
    private MapModel emptyModel;  
      
    private String title;  
      
    private double lat;  
      
    private double lng;  
    
    private Marker marker; 
  
    public AddMarkerBean() {  
        emptyModel = new DefaultMapModel();  
        lat=0.0;
        lng=0.0;
          
        //Shared coordinates  
        LatLng coord1 = new LatLng(36.879466, 30.667648);  
        LatLng coord2 = new LatLng(36.883707, 30.689216);  
        LatLng coord3 = new LatLng(36.879703, 30.706707);  
        LatLng coord4 = new LatLng(36.885233, 30.702323);  
          
        //Basic marker  
        emptyModel.addOverlay(new Marker(coord1, "Konyaalti"));  
        emptyModel.addOverlay(new Marker(coord2, "Ataturk Parki"));  
        emptyModel.addOverlay(new Marker(coord3, "Karaalioglu Parki"));  
        emptyModel.addOverlay(new Marker(coord4, "Kaleici"));  
    }  
      
    public MapModel getEmptyModel() {  
        return emptyModel;  
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Marker getMarker() {
        return marker;
    }

    public void setMarker(Marker marker) {
        this.marker = marker;
    }
    
    
      
    public void addMessage(FacesMessage message) {  
        FacesContext.getCurrentInstance().addMessage(null, message);  
    } 
    
    public void addMarker(ActionEvent actionEvent) { 
        
        LatLng coord1 = new LatLng(lat, lng);
        Marker marker = new Marker(new LatLng(lat, lng), title);  
//        emptyModel.addOverlay(marker);  
        
//        PontoParadaMarker markerParada = new PontoParadaMarker(new LatLng(lat,lng),title);
        
        marker.setClickable(true);
//        marker.setDraggable(false);
            
        emptyModel.addOverlay(marker);
          
        addMessage(new FacesMessage(FacesMessage.SEVERITY_INFO, "Marker Added", "Lat:" + lat + ", Lng:" + lng));  
    }
    
    
    public void onMarkerSelect(OverlaySelectEvent event) {  
        System.out.println("teste markerSelect");
        marker = (Marker) event.getOverlay();  
          
//        addMessage(new FacesMessage(FacesMessage.SEVERITY_INFO, "Marker Selected", marker.getTitle()));  
//        marker = (Marker) event.getOverlay();  
//        System.out.println("oiiiii");
//        if (marker instanceof ParadaMarker) {            
//            paradaSelecionada = paradaFacade.find(((ParadaMarker) marker).getIdParada());
//            
//            System.out.println("ID PARADA SELECIONADA: " + paradaSelecionada.getId());
//            
//            linhas = paradaFacade.findLinhasParadas(paradaSelecionada.getId());
//            
//            System.out.println("LINHAS: " + linhas);
//
//            linhaParada = linhaFacade.findByLocation(new PGgeometry(ocorrenciaSelecionada.getLocalizacao()));
//            System.out.println("TESTE LOCALIZAÇÃO: " + ocorrenciaSelecionada.getLocalizacao());
//          
//        } 
    }
}
