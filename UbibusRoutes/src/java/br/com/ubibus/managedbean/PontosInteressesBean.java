/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ubibus.managedbean;

import br.com.ubibus.mapa.OcorrenciaMarker;
import br.com.ubibus.mapa.ParadaMarker;
import br.com.ubibus.mapa.PontosInteressesMarker;
import br.com.ubibus.model.facade.*;
import br.com.ubibus.model.pojo.*;
import br.com.ubibus.util.JSFUtil;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.postgis.Geometry;
import org.postgis.PGgeometry;
import org.postgis.Point;
import org.primefaces.context.RequestContext;
import org.primefaces.event.map.OverlaySelectEvent;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;

/**
 *
 * @author marcelo
 */
@ManagedBean(name = "pontosInteressesBean")
@ViewScoped
public class PontosInteressesBean {

    @EJB
    private PontosInteressesFacade pontosInteressesFacade;
    @EJB
    private LinhaFacade linhaFacade;
    @EJB
    private TipoFonteFacade tipoFonteFacade;
    @EJB
    private EnderecoFacade enderecoFacade;
    @EJB
    private UsuarioFacade usuarioFacade;
    @EJB
    private ParadaFacade paradaFacade;
    @EJB
    private Usuario_PIFacade usuarioPiFacade;
    @EJB
    private RotaFacade rotaFacade;
    public static MapModel mapModel;
    private PontosInteresse pontosInteresse;
    private PontosInteresse pontosInteresseSelecionado;
    private List<Parada> paradasProximas;
    private List<Linha> linhasAfetadas, linhas;
    private List<PontosInteresse> listaPontosInteresse;
    private List<Parada> paradas;
    private Parada paradaSelecionada;
    //TODO verificar e alterar para o tipoFonte correto referente a esta aplicação
    private final String TIPO_FONTE = "UbibusOcorrencias";
    /**
     * Campo utilizado para realizar buscas (por localizacao/endereco) no mapa.
     */
    private String address;
    /**
     * Este campo define a localizacao inicial onde o mapa será centralizado.
     */
    private String defaultLocation;
    /**
     * Este campo define o zoom inicial do mapa.
     */
    private int zoom = 13;
    /**
     * Indica se um marcador no mapa é apenas um marcador ou se é um marcador de
     * ocorrência.
     */
    private boolean markerOnly = true;
    private boolean markerPontosInteresse = false;
    private boolean markerParada = false;
    /**
     * Marcador padrão utilizado para marcar um ponto no mapa, utilizado apenas
     * quando é realizada uma busca no mapa.
     */
    private Marker searchMarker;

    public PontosInteressesBean() {
        if (pontosInteresse == null) {
            initPontosInteresses();
        }
        if (defaultLocation == null) {
            defaultLocation = "-7.16798126869324, -34.8314046409435";
        }
        searchMarker = new Marker(new LatLng(-7.16798126869324, -34.8314046409435));
        Subject subject = SecurityUtils.getSubject();
        String principal = (String) subject.getPrincipal();
        System.out.println("PRINCIPAL: " + principal);
//        Usuario usuario = usuarioFacade.findByLogin(principal);
//        pontosInteresses = pontosInteressesFacade.find(usuario);
    }

    private void initPontosInteresses() {
        //System.out.println("INICIEI");
        pontosInteresse = new PontosInteresse();
        pontosInteresse.setLocalizacao(new Point());
    }

    public String getImagePathPI() {
        String basePath = JSFUtil.getContext().getExternalContext().getRequestScheme()
                + "://" + JSFUtil.getContext().getExternalContext().getRequestServerName()
                + ":" + JSFUtil.getContext().getExternalContext().getRequestServerPort()
                + JSFUtil.getContext().getExternalContext().getRequestContextPath()
                + "/faces/resources/images/red-dot-pi.png";
        return basePath;
    }

    public String getImagePathP() {
        String basePath = JSFUtil.getContext().getExternalContext().getRequestScheme()
                + "://" + JSFUtil.getContext().getExternalContext().getRequestServerName()
                + ":" + JSFUtil.getContext().getExternalContext().getRequestServerPort()
                + JSFUtil.getContext().getExternalContext().getRequestContextPath()
                + "/faces/resources/images/green-dot-p.png";
        return basePath;
    }

    /**
     * Adiciona um marcador no mapa referenciando a ocorrência e grava os dados
     * da ocorrência no banco.
     */
    public void addPontoInteresse() {
        System.out.println("TESTE LOCALIZAÇÂO ADD:" + pontosInteresse.getLocalizacao());
        PontosInteressesMarker occMarker = new PontosInteressesMarker(new LatLng(
                pontosInteresse.getLocalizacao().getFirstPoint().getY(),
                pontosInteresse.getLocalizacao().getFirstPoint().getX()));
        occMarker.setTitle(pontosInteresse.getNome().toString());
        occMarker.setIcon(getImagePathPI());
        occMarker.setClickable(true);
        occMarker.setDraggable(false);

        int id = salvarPontoInteresse();        

        occMarker.setIdPontosInteresses(id);
        mapModel.addOverlay(occMarker);
        defaultLocation = occMarker.getLatlng().getLat() + ", " + occMarker.getLatlng().getLng();

        JSFUtil.addSuccessMessage("Ponto Interesse Adicionado", "Localização:" + occMarker.getLatlng().getLat() + ", " + occMarker.getLatlng().getLng());

    }

    /**
     * Este método deve ser chamado quando se clicar sobre um marcador no
     * mapa.<br/> Permite exibir os dados referentes à ocorrência vinculada ao
     * marcador.
     *
     * @param event - evento gerado ao selecionar o marcador.
     */
    public void onMarkerSelect(OverlaySelectEvent event) {
        Marker marker = (Marker) event.getOverlay();
        if (marker instanceof PontosInteressesMarker) {
            pontosInteresseSelecionado = pontosInteressesFacade.find(((PontosInteressesMarker) marker).getIdPontosInteresses());

            //Geometry teste = new Point(marker.getLatlng().getLng(),marker.getLatlng().getLat());
            //paradasProximas = paradaFacade.findParadasProximas(new PGgeometry(teste), 0.003);

            //System.out.println("PARADAS PROXIMAS: " + paradaSelecionada.getNome());


            paradasProximas = paradaFacade.findParadasProximas(new PGgeometry(pontosInteresseSelecionado.getLocalizacao()), 0.01);
            System.out.println("ID ponto: " + pontosInteresseSelecionado.getId());
            System.out.println("TESTE LOCALIZAÇÃO: " + pontosInteresseSelecionado.getLocalizacao());
            System.out.println("Paradas proximas: " + paradasProximas);
            markerParada = false;
            markerPontosInteresse = true;

            for (Parada parada : paradasProximas) {
                ParadaMarker markerParada = new ParadaMarker(new LatLng(
                        parada.getLocalizacao().getFirstPoint().getY(),
                        parada.getLocalizacao().getFirstPoint().getX()),
                        parada.getNome());
                System.out.println(parada.getLocalizacao().getFirstPoint().getY() + "," + parada.getLocalizacao().getFirstPoint().getX());
                markerParada.setClickable(true);
                markerParada.setDraggable(false);
                markerParada.setIdParada(parada.getId());
                markerParada.setIcon(getImagePathP());
                System.out.println("oi, estou aqui :)");
                PontosInteressesBean.mapModel.addOverlay(markerParada);
            }



        } else {
            markerPontosInteresse = false;
        }

    }

    private int salvarPontoInteresse() {
        montarPontoInteresse();
        Subject subject = SecurityUtils.getSubject();
        String principal = (String) subject.getPrincipal();
        Usuario usuario = usuarioFacade.findByLogin(principal);
//        System.out.println("Pontos interesse: " + pontosInteresse.getNome());
//        System.out.println("Pontos interesse: " + pontosInteresse.getLocalizacao());
//        System.out.println("Pontos interesse: " + pontosInteresse.getCategoria());
        pontosInteressesFacade.create(pontosInteresse);
        UsuariosPiPK usuariosPiPk = new UsuariosPiPK();
        Usuario_PI usuarioPi = new Usuario_PI();
        usuariosPiPk.setIdPontosInteresse(pontosInteresse.getId());
        usuariosPiPk.setIdUsuario(usuario.getId());        
        usuarioPi.setPontos_interesse(pontosInteresse);
        usuarioPi.setUsuario(usuario);
        usuarioPi.setUsuariosPiPK(usuariosPiPk);
        pontosInteresse.getUsuarioPiList().add(usuarioPi);
        pontosInteressesFacade.edit(pontosInteresse);
        
        //inserirUsuarioPI();
        int id = pontosInteresse.getId();
        Rota_PI rotapoi = new Rota_PI();
        Rota_PiPK rotapoiPk = new Rota_PiPK();

        rotapoi.setRota(RotaBean.selectedRota);
        rotapoiPk.setIdRota(RotaBean.selectedRota.getId());
        rotapoiPk.setIdPi(pontosInteresse.getId());
        rotapoiPk.setNextPi(pontosInteresse.getId());
        rotapoi.setPonto_interesse(pontosInteresse);
        rotapoi.setRotaPiPk(rotapoiPk);
        RotaBean.selectedRota.getRotasPontosList().add(rotapoi);
        rotaFacade.edit(RotaBean.selectedRota);
        initPontosInteresses();
        return id;
    }

    private void montarPontoInteresse() {
        pontosInteresse.setEndereco(getEndereco());
    }

    private void inserirUsuarioPI() {
        Usuario_PI usuarioPI = new Usuario_PI();
        usuarioPI.setPontos_interesse(pontosInteresse);

        Subject subject = SecurityUtils.getSubject();
        String principal = (String) subject.getPrincipal();
        Usuario usuario = usuarioFacade.findByLogin(principal);
        usuarioPI.setUsuario(usuario);
        System.out.println("id usuario: " + usuario.getId());
        System.out.println("id ponto interesse: " + pontosInteresse.getId());

        System.out.println("id usuario PI: " + usuarioPI.getUsuario().getId());
        System.out.println("id ponto interesse PI: " + usuarioPI.getPontos_interesse().getId());

        pontosInteresse.getUsuarioPiList().add(usuarioPI);
        pontosInteressesFacade.edit(pontosInteresse);
        //usuarioPiFacade.create(usuarioPI);
    }

    /**
     * Controi um modelo de mapa com marcadores para todas as ocorrências
     * cadastradas na base de dados.
     */
    @PostConstruct
    public void buildMapModel() {
        mapModel = new DefaultMapModel();
//        Subject subject = SecurityUtils.getSubject();
//        String principal = (String) subject.getPrincipal();
//        Usuario usuario = usuarioFacade.findByLogin(principal);
//        List<PontosInteresse> list = pontosInteressesFacade.find(usuario.getId());
//        System.out.println("LISTA POS CONSTRUCT: " + list);
//        for (PontosInteresse ocr : list) {
//            PontosInteressesMarker marker = new PontosInteressesMarker(new LatLng(
//                    ocr.getLocalizacao().getFirstPoint().getY(),
//                    ocr.getLocalizacao().getFirstPoint().getX()),
//                    ocr.getNome().toString());
//            marker.setClickable(true);
//            marker.setDraggable(false);
//            marker.setIdPontosInteresses(ocr.getId());
//            marker.setIcon(getImagePathPI());
//            mapModel.addOverlay(marker);
//        }
//        paradas = paradaFacade.findParadas();
//        for (Parada parada : paradas) {
//            ParadaMarker markerParada = new ParadaMarker(new LatLng(
//                    parada.getLocalizacao().getFirstPoint().getY(),
//                    parada.getLocalizacao().getFirstPoint().getX()),
//                    parada.getNome());
//            System.out.println(parada.getLocalizacao().getFirstPoint().getY() + "," + parada.getLocalizacao().getFirstPoint().getX());
//            markerParada.setClickable(true);
//            markerParada.setDraggable(false);
//            markerParada.setIdParada(parada.getId());
//            markerParada.setIcon(getImagePathP());
//            mapModel.addOverlay(markerParada);
//        } 
    }

    /**
     * Lista todas as ocorrências cadastradas na base de dados
     *
     * @return uma lista com todas as ocorrencias
     */
    public List<PontosInteresse> getOccurrenceList() {
        return pontosInteressesFacade.findAll();
    }

    /**
     * Adiciona um marcador no mapa, os dados a serem inserido no marcador são
     * obtidos via javaScript e passados pelo contexto da aplicação via ajax. O
     * marcador adicionado por este método é utilizado apenas para marcar a
     * posição referente a uma busca realizada no mapa, não influencia nos
     * marcadores de ocorrências.
     */
    public void setLocation() {
        FacesContext context = FacesContext.getCurrentInstance();
        Map<String, String> parameterMap = context.getExternalContext().getRequestParameterMap();
        double latitude = Double.parseDouble((String) parameterMap.get("latitude"));
        double longitude = Double.parseDouble((String) parameterMap.get("longitude"));
        String formattedAddress = (String) parameterMap.get("formattedAddress");
        if (mapModel.getMarkers().contains(searchMarker)) {
            mapModel.getMarkers().remove(searchMarker);
        }
        LatLng latLng = new LatLng(latitude, longitude);
        searchMarker.setLatlng(latLng);
        searchMarker.setTitle(formattedAddress);
        mapModel.addOverlay(searchMarker);
        defaultLocation = latLng.getLat() + ", " + latLng.getLng();
        //System.out.println("localização: "+defaultLocation);
    }

    /**
     * Grava um endereço no banco e retorna o endereço persistido. Os dados a
     * serem inserido no endereço são obtidos via javaScript e passados pelo
     * contexto da aplicação via ajax.
     *
     * @return o objeto endereço persistido ou {@code null} caso nenhum endereço
     * tenha sido obtido.
     */
    public Endereco getEndereco() {
        Endereco end = null;
        FacesContext context = FacesContext.getCurrentInstance();
        Map<String, String> parameterMap = context.getExternalContext().getRequestParameterMap();
        String status = (String) parameterMap.get("status");
        if (status != null & status.equals("OK")) {
            String cepStr = ((String) parameterMap.get("postal_code")).replaceAll("-", "");
            System.out.println("CEP: " + cepStr);
            int cep = Integer.parseInt(cepStr);
            String nome = (String) parameterMap.get("route");
            System.out.println("NOME ENDERECO: " + nome);
            String sublocality = (String) parameterMap.get("sublocality");
            end = enderecoFacade.find(cep, nome);
            System.out.println("ENDERECO: " + end);
            if (end == null) {
                end = new Endereco();
                end.setCep(cep);
                end.setTipo(1);
                end.setNome(nome);
                end.setBairro(sublocality);
                enderecoFacade.create(end);
            }
        }
        return end;
    }

    /**
     * Valida os dados do formulário de cadastro de ocorrências. Este método é
     * chamado antes do cadastro da ocorrência, isto é necessário devido à
     * algumas operações serem executadas via javascript então o durante a
     * chamada deste método o jsf realiza a validação do formulário e caso os
     * dados sejam inválidos o processo retorna daqui sem necessidade de
     * executar o javascript.
     */
    public void validate() {
        RequestContext context = RequestContext.getCurrentInstance();
        context.execute("");
    }

    public void clearFields() {
        initPontosInteresses();
    }

    /////////// Converter /////////////////////
    /**
     * Obtem o conveter para a classe
     * <code>PontosInteresse</code>.
     *
     * @return uma intancia de
     * <code>OccurrenceConverter</code>.
     */
    public OccurrenceConverter getOccurrenceConverter() {
        return new OccurrenceConverter();
    }

    @FacesConverter(forClass = PontosInteresse.class)
    public static class OccurrenceConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
//            if (value == null || value.length() == 0 || value.equals("null")) {
//                return null;
//            }
//            PontosInteressesBean ocorrenciaBean = (PontosInteressesBean) facesContext.getApplication().getELResolver().
//                    getValue(facesContext.getELContext(), null, "ocorrenciaBean");
//            return OcorrenciaFacade.find(getKey(value));
            return null;
        }

        java.lang.Integer getKey(String value) {
            java.lang.Integer key;
            key = Integer.valueOf(value);
            return key;
        }

        String getStringKey(java.lang.Integer value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof PontosInteresse) {
                PontosInteresse o = (PontosInteresse) object;
                return getStringKey(o.getId());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + PontosInteresse.class.getName());
            }
        }
    }

    /////////////Getters and Setters
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<PontosInteresse> getListaPontosInteresse() {
        return listaPontosInteresse;
    }

    public void setListaPontosInteresse(List<PontosInteresse> listaPontosInteresse) {
        this.listaPontosInteresse = listaPontosInteresse;
    }

    public ParadaFacade getParadaFacade() {
        return paradaFacade;
    }

    public void setParadaFacade(ParadaFacade paradaFacade) {
        this.paradaFacade = paradaFacade;
    }

    public List<Parada> getParadasProximas() {
        return paradasProximas;
    }

    public void setParadasProximas(List<Parada> paradasProximas) {
        this.paradasProximas = paradasProximas;
    }

    public boolean isMarkerOnly() {
        return markerOnly;
    }

    public void setMarkerOnly(boolean markerOnly) {
        this.markerOnly = markerOnly;
    }

    public boolean isMarkerParada() {
        return markerParada;
    }

    public void setMarkerParada(boolean markerParada) {
        this.markerParada = markerParada;
    }

    public boolean isMarkerPontosInteresse() {
        return markerPontosInteresse;
    }

    public void setMarkerPontosInteresse(boolean markerPontosInteresse) {
        this.markerPontosInteresse = markerPontosInteresse;
    }

    public String getDefaultLocation() {
        return defaultLocation;
    }

    public void setDefaultLocation(String defaultLocation) {
        this.defaultLocation = defaultLocation;
    }

    public Marker getSearchMarker() {
        return searchMarker;
    }

    public void setSearchMarker(Marker searchMarker) {
        this.searchMarker = searchMarker;
    }

    public MapModel getMapModel() {
        return mapModel;
    }

    public void setMapModel(MapModel mapModel) {
        this.mapModel = mapModel;
    }

    public EnderecoFacade getEnderecoFacade() {
        return enderecoFacade;
    }

    public void setEnderecoFacade(EnderecoFacade enderecoFacade) {
        this.enderecoFacade = enderecoFacade;
    }

    public LinhaFacade getLinhaFacade() {
        return linhaFacade;
    }

    public void setLinhaFacade(LinhaFacade linhaFacade) {
        this.linhaFacade = linhaFacade;
    }

    public List<Linha> getLinhasAfetadas() {
        return linhasAfetadas;
    }

    public void setLinhasAfetadas(List<Linha> linhasAfetadas) {
        this.linhasAfetadas = linhasAfetadas;
    }

    public PontosInteresse getPontosInteresse() {
        return pontosInteresse;
    }

    public void setPontosInteresse(PontosInteresse pontosInteresse) {
        this.pontosInteresse = pontosInteresse;
    }

    public PontosInteresse getPontosInteresseSelecionado() {
        return pontosInteresseSelecionado;
    }

    public void setPontosInteresseSelecionado(PontosInteresse pontosInteresseSelecionado) {
        this.pontosInteresseSelecionado = pontosInteresseSelecionado;
    }

    public Parada getParadaSelecionada() {
        return paradaSelecionada;
    }

    public void setParadaSelecionada(Parada paradaSelecionada) {
        this.paradaSelecionada = paradaSelecionada;
    }

    public PontosInteressesFacade getPontosInteressesFacade() {
        return pontosInteressesFacade;
    }

    public void setPontosInteressesFacade(PontosInteressesFacade pontosInteressesFacade) {
        this.pontosInteressesFacade = pontosInteressesFacade;
    }

    public TipoFonteFacade getTipoFonteFacade() {
        return tipoFonteFacade;
    }

    public void setTipoFonteFacade(TipoFonteFacade tipoFonteFacade) {
        this.tipoFonteFacade = tipoFonteFacade;
    }

    public UsuarioFacade getUsuarioFacade() {
        return usuarioFacade;
    }

    public void setUsuarioFacade(UsuarioFacade usuarioFacade) {
        this.usuarioFacade = usuarioFacade;
    }

    public List<Parada> getParadas() {
        return paradas;
    }

    public void setParadas(List<Parada> paradas) {
        this.paradas = paradas;
    }

    public List<Linha> getLinhas() {
        return linhas;
    }

    public void setLinhas(List<Linha> linhas) {
        this.linhas = linhas;
    }

    public int getZoom() {
        return zoom;
    }

    public void setZoom(int zoom) {
        this.zoom = zoom;
    }

    public Usuario_PIFacade getUsuarioPiFacade() {
        return usuarioPiFacade;
    }

    public void setUsuarioPiFacade(Usuario_PIFacade usuarioPiFacade) {
        this.usuarioPiFacade = usuarioPiFacade;
    }
}
