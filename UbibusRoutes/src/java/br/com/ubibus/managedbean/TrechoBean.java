/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ubibus.managedbean;

import br.com.ubibus.model.facade.ParadaFacade;
import br.com.ubibus.model.facade.RotaFacade;
import br.com.ubibus.model.facade.TrechoFacade;
import br.com.ubibus.model.pojo.Trecho;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author AnaaMaciel
 */
@ManagedBean (name = "trechoBean")
@SessionScoped
public class TrechoBean {
    
    @EJB
    private RotaFacade rotaFacade;
    @EJB
    private ParadaFacade paradaFacade;
    @EJB
    private TrechoFacade trechoFacade;
    private List<Trecho> trechos;
    
    /**
     * Creates a new instance of TrechoBean
     */
    public TrechoBean() {
    }

    public RotaFacade getRotaFacade() {
        return rotaFacade;
    }

    public void setRotaFacade(RotaFacade rotaFacade) {
        this.rotaFacade = rotaFacade;
    }

    public ParadaFacade getParadaFacade() {
        return paradaFacade;
    }

    public void setParadaFacade(ParadaFacade paradaFacade) {
        this.paradaFacade = paradaFacade;
    }

    public TrechoFacade getTrechoFacade() {
        return trechoFacade;
    }

    public void setTrechoFacade(TrechoFacade trechoFacade) {
        this.trechoFacade = trechoFacade;
    }

    public List<Trecho> getTrechos() {
        trechos = trechoFacade.findTrechos();
        return trechos;
    }

    public void setTrechos(List<Trecho> trechos) {
        this.trechos = trechos;
    }    
    
    
}
