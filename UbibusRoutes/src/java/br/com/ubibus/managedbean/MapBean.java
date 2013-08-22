/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ubibus.managedbean;

import br.com.ubibus.mapa.ParadaMarker;
import br.com.ubibus.mapa.PontoParadaMarker;
import br.com.ubibus.model.facade.LinhaFacade;
import br.com.ubibus.model.facade.ParadaFacade;
import br.com.ubibus.model.pojo.Linha;
import br.com.ubibus.model.pojo.Parada;
import br.com.ubibus.util.JSFUtil;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.postgis.Geometry;
import org.postgis.PGgeometry;
import org.postgis.Point;
import org.primefaces.event.map.OverlaySelectEvent;
import org.primefaces.event.map.PointSelectEvent;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;

/**
 *
 * @author marcelo
 */
@ManagedBean(name = "mapBean")
@ViewScoped
public class MapBean implements Serializable {

    @EJB
    private LinhaFacade linhaFacade;
    private Geometry localizacaoParada;
    private List<Linha> linhas;
    private List<Parada> paradas;
    private List<Parada> paradasProximas;
    private MapModel mapModel;
    @EJB
    private ParadaFacade paradaFacade;
    private Parada paradaSelecionada;
    private Marker marker;  
    private Linha linhaParada;
    private MapModel simpleModel;
  
    public MapBean() {  
        simpleModel = new DefaultMapModel();  
          
        //Shared coordinates  
//        LatLng coord1 = new LatLng(36.879466, 30.667648);  
//        LatLng coord2 = new LatLng(36.883707, 30.689216);  
//        LatLng coord3 = new LatLng(36.879703, 30.706707);  
//        LatLng coord4 = new LatLng(36.885233, 30.702323);  
//          
//        //Basic marker  
//        simpleModel.addOverlay(new Marker(coord1, "Konyaalti"));  
//        simpleModel.addOverlay(new Marker(coord2, "Ataturk Parki"));  
//        simpleModel.addOverlay(new Marker(coord3, "Karaalioglu Parki"));  
//        simpleModel.addOverlay(new Marker(coord4, "Kaleici"));  

    }

    public void onPointSelect(PointSelectEvent event) {
        LatLng latlng = event.getLatLng();

        addMessage(new FacesMessage(FacesMessage.SEVERITY_INFO, "Point Selected", "Lat:" + latlng.getLat() + ", Lng:" + latlng.getLng()));
//        System.out.println("Lat:" + latlng.getLat() + ", Lng:" + latlng.getLng());
//        localizacaoParada = new Point(latlng.getLng(), latlng.getLat());
//
//        paradas = linhaFacade.findParadas(new PGgeometry(localizacaoParada),5000.0);
//        
//        //paradas = linhaFacade.findTeste();
//
//        System.out.println("resultado: " + paradas);
        
    }

    public void addMessage(FacesMessage message) {
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public MapModel getMapModel() {
        return mapModel;
    }

    public void setMapModel(MapModel mapModel) {
        this.mapModel = mapModel;
    }

    public LinhaFacade getLinhaFacade() {
        return linhaFacade;
    }

    public void setLinhaFacade(LinhaFacade linhaFacade) {
        this.linhaFacade = linhaFacade;
    }

    public Linha getLinhaParada() {
        return linhaParada;
    }

    public void setLinhaParada(Linha linhaParada) {
        this.linhaParada = linhaParada;
    }

    public List<Linha> getLinhas() {
        return linhas;
    }

    public void setLinhas(List<Linha> linhas) {
        this.linhas = linhas;
    }

    public Geometry getLocalizacaoParada() {
        return localizacaoParada;
    }

    public void setLocalizacaoParada(Geometry localizacaoParada) {
        this.localizacaoParada = localizacaoParada;
    }

    public Marker getMarker() {
        return marker;
    }

    public void setMarker(Marker marker) {
        this.marker = marker;
    }

    public ParadaFacade getParadaFacade() {
        return paradaFacade;
    }

    public void setParadaFacade(ParadaFacade paradaFacade) {
        this.paradaFacade = paradaFacade;
    }

    public Parada getParadaSelecionada() {
        return paradaSelecionada;
    }

    public void setParadaSelecionada(Parada paradaSelecionada) {
        this.paradaSelecionada = paradaSelecionada;
    }

    public List<Parada> getParadas() {
        return paradas;
    }

    public void setParadas(List<Parada> paradas) {
        this.paradas = paradas;
    }

    public MapModel getSimpleModel() {
        return simpleModel;
    }

    public void setSimpleModel(MapModel simpleModel) {
        this.simpleModel = simpleModel;
    }
    
    
    
    
    
    
    public String getImagePath() {
        String basePath = JSFUtil.getContext().getExternalContext().getRequestScheme()
                + "://" + JSFUtil.getContext().getExternalContext().getRequestServerName()
                + ":" + JSFUtil.getContext().getExternalContext().getRequestServerPort()
                + JSFUtil.getContext().getExternalContext().getRequestContextPath()
                + "/faces/resources/images/red-dot-p.png";
        return basePath;
    }
    
    public void onMarkerSelect(OverlaySelectEvent event) {  
        System.out.println("teste markerSelect");
        marker = (Marker) event.getOverlay();  
          
        addMessage(new FacesMessage(FacesMessage.SEVERITY_INFO, "Marker Selected", marker.getTitle()));  
        marker = (Marker) event.getOverlay();  
        System.out.println("oiiiii");
        if (marker instanceof ParadaMarker) {            
            paradaSelecionada = paradaFacade.find(((ParadaMarker) marker).getIdParada());
            
            System.out.println("ID PARADA SELECIONADA: " + paradaSelecionada.getId());
            
            linhas = paradaFacade.findLinhasParadas(paradaSelecionada.getId());
            
            System.out.println("LINHAS: " + linhas);

//            linhaParada = linhaFacade.findByLocation(new PGgeometry(ocorrenciaSelecionada.getLocalizacao()));
//            System.out.println("TESTE LOCALIZAÇÃO: " + ocorrenciaSelecionada.getLocalizacao());
          
        } 
    }
    
    public void onMarkerSelectParadas(OverlaySelectEvent event) {  
        System.out.println("Estou aqui onMarkerSelectParadas");
        marker = (Marker) event.getOverlay();  
        
        //if (marker instanceof PontoParadaMarker) {            
            Geometry teste = new Point(marker.getLatlng().getLng(),marker.getLatlng().getLat());
             paradasProximas = paradaFacade.findParadasProximas(new PGgeometry(teste), 0.05);
            
            System.out.println("PARADAS PROXIMAS: " + paradaSelecionada.getNome());
            
//            linhaParada = linhaFacade.findByLocation(new PGgeometry(ocorrenciaSelecionada.getLocalizacao()));
//            System.out.println("TESTE LOCALIZAÇÃO: " + ocorrenciaSelecionada.getLocalizacao());
          
        //} 
    }
    
    
    @PostConstruct
    public void buildMapModel() {
        mapModel = new DefaultMapModel();
        paradas = paradaFacade.findParadas();
        System.out.println("paradas testando: " + paradas);
        for (Parada parada : paradas) {
            ParadaMarker markerParada = new ParadaMarker(new LatLng(
                    parada.getLocalizacao().getFirstPoint().getY(),
                    parada.getLocalizacao().getFirstPoint().getX()));
            System.out.println(parada.getLocalizacao().getFirstPoint().getY() + "," + parada.getLocalizacao().getFirstPoint().getX());
            markerParada.setClickable(true);
            markerParada.setDraggable(false);
            markerParada.setIdParada(parada.getId());
            markerParada.setIcon(getImagePath());
            mapModel.addOverlay(markerParada);
        } 
    }  
    
}
