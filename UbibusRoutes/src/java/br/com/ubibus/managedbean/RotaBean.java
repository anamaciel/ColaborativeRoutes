/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ubibus.managedbean;

import br.com.ubibus.mapa.PontosInteressesMarker;
import br.com.ubibus.model.facade.PontosInteressesFacade;
import br.com.ubibus.model.facade.RotaFacade;
import br.com.ubibus.model.facade.RotaPoiFacade;
import br.com.ubibus.model.facade.UsuarioFacade;
import br.com.ubibus.model.pgconverter.PontosInteresseConverter;
import br.com.ubibus.model.pojo.*;
import br.com.ubibus.util.JSFUtil;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;

/**
 *
 * @author marcelo
 */
@ManagedBean(name = "rotaBean")
@SessionScoped
public class RotaBean {

    @EJB
    private PontosInteressesFacade pontosInteressesFacade;
    @EJB
    private UsuarioFacade usuarioFacade;
    @EJB
    private RotaFacade rotaFacade;
    @EJB
    private RotaPoiFacade rotaPoiFacade;
    //para usar caso queira criar novas rotas usando pontos de interesses já cadastrados;
    private List<PontosInteresse> pontosInteresseSelecionados;
    //usado para listar todos os pontos de interesses na criação de novas rotas;
    public static List<PontosInteresse> pontosInteresses;
    private PontosInteresse pontosInteressesTeste;
    private List<PontosInteresse> pontos;
    private Rota rota;
    private Rota_PI rota_poi;
    private List<PontosInteresse> pontosLista;
    private List<Rota> rotasLista;
    public static Rota selectedRota;
    private List<PontosInteresse> listaPontos;
    private Usuario donoRota;

    /**
     * Creates a new instance of RotaBean
     */
    public RotaBean() {
        rota = new Rota();
        selectedRota = new Rota();
    }  
    
    
    public List<PontosInteresse> getPontosInteresseSelecionados() {
        return pontosInteresseSelecionados;
    }

    public void setPontosInteresseSelecionados(List<PontosInteresse> pontosInteresseSelecionados) {
        this.pontosInteresseSelecionados = pontosInteresseSelecionados;
    }

    public List<PontosInteresse> getPontosInteresses() {        
        return pontosInteresses;
    }

    public static void setPontosInteresses(List<PontosInteresse> pontosInteresses) {
        RotaBean.pontosInteresses = pontosInteresses;
    }

    public List<PontosInteresse> getPontos() {
        return pontos;
    }

    public void setPontos(List<PontosInteresse> pontos) {
        this.pontos = pontos;
    }

    public PontosInteressesFacade getPontosInteressesFacade() {
        return pontosInteressesFacade;
    }

    public void setPontosInteressesFacade(PontosInteressesFacade pontosInteressesFacade) {
        this.pontosInteressesFacade = pontosInteressesFacade;
    }

    public Rota getRota() {
        return rota;
    }

    public void setRota(Rota rota) {
        this.rota = rota;
    }

    public UsuarioFacade getUsuarioFacade() {
        return usuarioFacade;
    }

    public void setUsuarioFacade(UsuarioFacade usuarioFacade) {
        this.usuarioFacade = usuarioFacade;
    }

    public RotaFacade getRotaFacade() {
        return rotaFacade;
    }

    public void setRotaFacade(RotaFacade rotaFacade) {
        this.rotaFacade = rotaFacade;
    }

    public RotaPoiFacade getRotaPoiFacade() {
        return rotaPoiFacade;
    }

    public void setRotaPoiFacade(RotaPoiFacade rotaPoiFacade) {
        this.rotaPoiFacade = rotaPoiFacade;
    }

    public Rota_PI getRota_poi() {
        return rota_poi;
    }

    public void setRota_poi(Rota_PI rota_poi) {
        this.rota_poi = rota_poi;
    }

    public void initRota() {
        rota = new Rota();
    }

    public PontosInteresse getPontosInteressesTeste() {
        return pontosInteressesTeste;
    }

    public void setPontosInteressesTeste(PontosInteresse pontosInteressesTeste) {
        this.pontosInteressesTeste = pontosInteressesTeste;
    }

    public Rota getSelectedRota() {        
        //Integer id = Integer.parseInt(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id"));
        System.out.println("ID Rota: "+ this.selectedRota.getId());
        pontos = rotaFacade.findPontosInteressesRotas(this.selectedRota.getId());
        //pontos = rotaFacade.findPontosInteressesRotas(id);
        System.out.println("TESTE PONTOS: "+ pontos);        
        return selectedRota;
    }

    public void setSelectedRota(Rota selectedRota) {
        this.selectedRota = selectedRota;
    }
    
    public String criaSelectedRota(){
        //RotaBean.selectedRota = new Rota();
        System.out.println("TESTE");
        return "teste";
    }

    public List<PontosInteresse> getListaPontos() {
        return listaPontos;
    }

    public Usuario getDonoRota(Integer id) {
        donoRota = rotaFacade.findDonoRota(id);
        return donoRota;
    }

    public void setDonoRota(Usuario donoRota) {
        this.donoRota = donoRota;
    }
    

    public void setListaPontos(List<PontosInteresse> listaPontos) {
        this.listaPontos = listaPontos;
    }

    public List<PontosInteresse> getPontosLista() {
        Subject subject = SecurityUtils.getSubject();
        String principal = (String) subject.getPrincipal();
        Usuario usuario = usuarioFacade.findByLogin(principal);
        pontosLista = pontosInteressesFacade.find(usuario);
        return pontosLista;
    }

    public void setPontosLista(List<PontosInteresse> pontosLista) {
        this.pontosLista = pontosLista;
    }

    public List<Rota> getRotasLista() {
        rotasLista = rotaFacade.findRotas();
        return rotasLista;
    }

    public void setRotasLista(List<Rota> rotasLista) {
        this.rotasLista = rotasLista;
    }

    public String salvarRota() {
        rotaFacade.create(rota);
        int id = rota.getId();
        System.out.println("SALVAR ROTA");
        System.out.println(pontosInteresseSelecionados);
        for (int i = 0; i < pontosInteresseSelecionados.size(); i++) {
            System.out.println(pontosInteresseSelecionados.get(i));
            Rota_PI rotapoi = new Rota_PI();
            Rota_PiPK rotapoiPk = new Rota_PiPK();
            PontosInteresse ponto = pontosInteressesFacade.findPonto(pontosInteresseSelecionados.get(i) + "");
            rotapoi.setRota(rota);
            rotapoiPk.setIdRota(rota.getId());
            rotapoiPk.setIdPi(ponto.getId());
            rotapoiPk.setNextPi(ponto.getId());
            rotapoi.setPonto_interesse(ponto);
            rotapoi.setRotaPiPk(rotapoiPk);
            rota.getRotasPontosList().add(rotapoi);
            rotaFacade.edit(rota);
            //rotaPoiFacade.create(rota_poi);

        }
        Subject subject = SecurityUtils.getSubject();
        String principal = (String) subject.getPrincipal();
        Usuario usuario = usuarioFacade.findByLogin(principal);
        UsuarioRotaPK usuarioRotaPk = new UsuarioRotaPK();
        usuarioRotaPk.setIdRota(rota.getId());
        usuarioRotaPk.setIdUsuario(usuario.getId());
        Usuario_Rota usuarioRota = new Usuario_Rota();
        usuarioRota.setUsuarioRotaPk(usuarioRotaPk);
        usuarioRota.setRota(rota);
        usuarioRota.setUsuario(usuario);
        usuarioRota.setDono(true);
        rota.getUsuarioRotaList().add(usuarioRota);
        rotaFacade.edit(rota);
        //System.out.println("teste fatal =)>" + teste.getId());

        //System.out.println("TESTE FATAL:" + pontosInteressesFacade.findPonto(pontosInteresseSelecionados.get(i)+""));   
        JSFUtil.addSuccessMessage("Sucesso", "Rota criada!");


        PontosInteressesBean.mapModel = new DefaultMapModel();

        initRota();
        //return id;
        return "0";

    }

    public String preencherMapa() {
        PontosInteressesBean.mapModel = new DefaultMapModel();
        System.out.println("PREENCHER MAPA");
        System.out.println(selectedRota.getNome());
        listaPontos = rotaFacade.findPontosInteressesRotas(selectedRota.getId());
        System.out.println(listaPontos);
        for (PontosInteresse ocr : listaPontos) {
            PontosInteressesMarker marker = new PontosInteressesMarker(new LatLng(
                    ocr.getLocalizacao().getFirstPoint().getY(),
                    ocr.getLocalizacao().getFirstPoint().getX()),
                    ocr.getNome().toString());
            marker.setClickable(true);
            marker.setDraggable(false);
            marker.setIdPontosInteresses(ocr.getId());
            marker.setIcon(getImagePathPI());
            PontosInteressesBean.mapModel.addOverlay(marker);
            //paradaMaisProxima(ocr);
            //paradaMaisLonge(ocr);
            //sugerirParadas(ocr);
        }
        return "0";

    }

    public String excluirRota() {
        rotaFacade.remove(selectedRota);
        JSFUtil.addSuccessMessage("Sucesso", "Rota excluida!");
        return "0";
    }

    public Object getPontoInteresseConverter() {
        return new PontosInteresseConverter();
    }

    public String getImagePathPI() {
        String basePath = JSFUtil.getContext().getExternalContext().getRequestScheme()
                + "://" + JSFUtil.getContext().getExternalContext().getRequestServerName()
                + ":" + JSFUtil.getContext().getExternalContext().getRequestServerPort()
                + JSFUtil.getContext().getExternalContext().getRequestContextPath()
                + "/faces/resources/images/red-dot-pi.png";
        return basePath;
    }
}
