package br.com.ubibus.managedbean;

import br.com.ubibus.mapa.OcorrenciaMarker;
import br.com.ubibus.model.facade.*;
import br.com.ubibus.model.pojo.*;
import br.com.ubibus.util.JSFUtil;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
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
 * @author <a href="mailto:marvas1987@gmail.com">Marcelo F. Vasconcelos</a>
 */
@ManagedBean(name = "ocorrenciaBean")
@ViewScoped
public class OcorrenciaBean {

    @EJB
    private OcorrenciaFacade ocorrenciaFacade;
    @EJB
    private LinhaFacade linhaFacade;
    @EJB
    private TipoFonteFacade tipoFonteFacade;
    @EJB
    private EnderecoFacade enderecoFacade;
    @EJB
    private UsuarioFacade usuarioFacade;
    private Ocorrencia ocorrencia;
    private Ocorrencia ocorrenciaSelecionada;
    private MapModel mapModel;
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
    /**
     * Marcador padrão utilizado para marcar um ponto no mapa, utilizado apenas
     * quando é realizada uma busca no mapa.
     */
    private Marker searchMarker;
    private List<Linha> linhasAfetadas;

    public OcorrenciaBean() {
        if (ocorrencia == null) {
            initOcorrencia();
        }
        if (defaultLocation == null) {
            defaultLocation = "-7.16798126869324, -34.8314046409435";
        }
        searchMarker = new Marker(new LatLng(-7.16798126869324, -34.8314046409435));
    }

    private void initOcorrencia() {
        System.out.println("INICIEI");
        ocorrencia = new Ocorrencia();
        ocorrencia.setLocalizacao(new Point());
    }

    public String getImagePath() {
        String basePath = JSFUtil.getContext().getExternalContext().getRequestScheme()
                + "://" + JSFUtil.getContext().getExternalContext().getRequestServerName()
                + ":" + JSFUtil.getContext().getExternalContext().getRequestServerPort()
                + JSFUtil.getContext().getExternalContext().getRequestContextPath()
                + "/faces/resources/images/red-dot-oc.png";
        return basePath;
    }

    /**
     * Adiciona um marcador no mapa referenciando a ocorrência e grava os dados
     * da ocorrência no banco.
     */
    public void addOcorrencia() {
        validate();
        System.out.println("TESTE LOCALIZAÇÂO ADD:" + ocorrencia.getLocalizacao());
        OcorrenciaMarker occMarker = new OcorrenciaMarker(new LatLng(
                ocorrencia.getLocalizacao().getFirstPoint().getY(),
                ocorrencia.getLocalizacao().getFirstPoint().getX()));
        occMarker.setTitle(ocorrencia.getTipoOcorrencia().toString());
        occMarker.setIcon(getImagePath());
        occMarker.setClickable(true);
        occMarker.setDraggable(false);
        
        System.out.println("OCORRENCIA: " + ocorrencia);
//        ocorrenciaFacade.create(ocorrencia);
        int id = salvarOcorrencia();
//
//        ocorrenciaFacade.create(ocorrencia);
//        inserirMensagem();
//        int id = ocorrencia.getId();
        occMarker.setIdOcorreincia(id);
        mapModel.addOverlay(occMarker);
        defaultLocation = occMarker.getLatlng().getLat() + ", " + occMarker.getLatlng().getLng();

        JSFUtil.addSuccessMessage("Ocorrência Adicionada", "Localização:" + occMarker.getLatlng().getLat() + ", " + occMarker.getLatlng().getLng());
        initOcorrencia();

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
        if (marker instanceof OcorrenciaMarker) {            
            ocorrenciaSelecionada = ocorrenciaFacade.find(((OcorrenciaMarker) marker).getIdOcorreincia());

            linhasAfetadas = linhaFacade.findByLocation(new PGgeometry(ocorrenciaSelecionada.getLocalizacao()));
            System.out.println("TESTE LOCALIZAÇÃO: " + ocorrenciaSelecionada.getLocalizacao());
            markerOnly = false;
        } else {
            markerOnly = true;
        }
    }    
    
    private int salvarOcorrencia() {
        montarOcorrencia();
        System.out.println("OCORRENCIA: " + ocorrencia);
        ocorrenciaFacade.create(ocorrencia);
        inserirMensagem();
        int id = ocorrencia.getId();
        initOcorrencia();
        return id;
    }

    private void montarOcorrencia() {
        //TODO verificar a forma correta de inserir os valores de 'pesoFonte' e 'duracaoEstimada'
        ocorrencia.setStatusResolvido(false);
        ocorrencia.setPesoFonte(0f);
        ocorrencia.setDuracaoEstimada(0);
        ocorrencia.setDataHora(new Date());
        //ocorrencia.setEndereco(getEndereco());
    }

    private void inserirMensagem() {
        Mensagem msg = new Mensagem();
        msg.setMensagem(ocorrencia.getDescricao());
        msg.setDataHora(ocorrencia.getDataHora());
        TipoFonte tf = tipoFonteFacade.findByDescricao(TIPO_FONTE);
        msg.setTipoFonte(tf);
        msg.setOcorrencia(ocorrencia);
        msg.setLocalizacaoOcorrencia(ocorrencia.getLocalizacao());

        Subject subject = SecurityUtils.getSubject();
        String principal = (String) subject.getPrincipal();
        Usuario usuario = usuarioFacade.findByLogin(principal);
        msg.setUsuario(usuario);

        ocorrencia.getMensagemList().add(msg);
        ocorrenciaFacade.edit(ocorrencia);
    }

    /**
     * Controi um modelo de mapa com marcadores para todas as ocorrências
     * cadastradas na base de dados.
     */
    @PostConstruct
    public void buildMapModel() {
        mapModel = new DefaultMapModel();
        List<Ocorrencia> list = ocorrenciaFacade.find(false);
        for (Ocorrencia ocr : list) {
            OcorrenciaMarker marker = new OcorrenciaMarker(new LatLng(
                    ocr.getLocalizacao().getFirstPoint().getY(),
                    ocr.getLocalizacao().getFirstPoint().getX()),
                    ocr.getTipoOcorrencia().toString());
            marker.setClickable(true);
            marker.setDraggable(false);
            marker.setIdOcorreincia(ocr.getId());
            marker.setIcon(getImagePath());
            mapModel.addOverlay(marker);
        }
    }

    /**
     * Lista todas as ocorrências cadastradas na base de dados
     *
     * @return uma lista com todas as ocorrencias
     */
    public List<Ocorrencia> getOccurrenceList() {
        return ocorrenciaFacade.findAll();
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
            int cep = Integer.parseInt(cepStr);
            String nome = (String) parameterMap.get("route");
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
        initOcorrencia();
    }

    /////////// Converter /////////////////////
    /**
     * Obtem o conveter para a classe
     * <code>Ocorrencia</code>.
     *
     * @return uma intancia de
     * <code>OccurrenceConverter</code>.
     */
    public OcorrenciaBean.OccurrenceConverter getOccurrenceConverter() {
        return new OcorrenciaBean.OccurrenceConverter();
    }

    @FacesConverter(forClass = Ocorrencia.class)
    public static class OccurrenceConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0 || value.equals("null")) {
                return null;
            }
            OcorrenciaBean ocorrenciaBean = (OcorrenciaBean) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "ocorrenciaBean");
            return ocorrenciaBean.ocorrenciaFacade.find(getKey(value));
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
            if (object instanceof Ocorrencia) {
                Ocorrencia o = (Ocorrencia) object;
                return getStringKey(o.getId());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + Ocorrencia.class.getName());
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

    public boolean isMarkerOnly() {
        return markerOnly;
    }

    public void setMarkerOnly(boolean markerOnly) {
        this.markerOnly = markerOnly;
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

    public Ocorrencia getOcorrencia() {
        return ocorrencia;
    }

    public void setOcorrencia(Ocorrencia ocorrencia) {
        this.ocorrencia = ocorrencia;
    }

    public Ocorrencia getOcorrenciaSelecionada() {
        return ocorrenciaSelecionada;
    }

    public void setOcorrenciaSelecionada(Ocorrencia ocorrenciaSelecionada) {
        this.ocorrenciaSelecionada = ocorrenciaSelecionada;
    }

    public int getZoom() {
        return zoom;
    }

    public void setZoom(int zoom) {
        this.zoom = zoom;
    }

    public List<Linha> getLinhasAfetadas() {
        return linhasAfetadas;
    }

    public void setLinhasAfetadas(List<Linha> linhasAfetadas) {
        this.linhasAfetadas = linhasAfetadas;
    }
}