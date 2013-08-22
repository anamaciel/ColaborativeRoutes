package br.com.ubibus.authentication;

import br.com.ubibus.managedbean.PontosInteressesBean;
import br.com.ubibus.managedbean.RotaBean;
import br.com.ubibus.mapa.ParadaMarker;
import br.com.ubibus.mapa.PontosInteressesMarker;
import br.com.ubibus.model.facade.ParadaFacade;
import br.com.ubibus.model.facade.PontosInteressesFacade;
import br.com.ubibus.model.facade.UsuarioFacade;
import br.com.ubibus.model.pojo.Parada;
import br.com.ubibus.model.pojo.PontosInteresse;
import br.com.ubibus.model.pojo.Usuario;
import br.com.ubibus.util.JSFUtil;
import br.com.ubibus.util.PasswordHash;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.servlet.http.HttpSession;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.postgis.PGgeometry;
import org.postgis.Point;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;

/**
 *
 * @author <a href="mailto:marvas1987@gmail.com">Marcelo F. Vasconcelos</a>
 */
@ManagedBean(name = "authenticatorBean")
@RequestScoped
public class AuthenticatorBean {

    static final Logger log = Logger.getLogger(AuthenticatorBean.class.getName());
    @EJB
    private UsuarioFacade usuarioFacade;
    @EJB
    private PontosInteressesFacade pontosInteressesFacade;
    @EJB
    private ParadaFacade paradaFacade;
    private MapModel mapModel;
    private Usuario usuario;
    private UbibusJdbcRealm realm;
    private List<Parada> paradasProximas;
    private PontosInteressesBean pontosInteresseBean;
    /*
     * tempo limite padrão para sessão 10 min.
     */
    private long timeout = 600000;

    public AuthenticatorBean() {
        usuario = new Usuario();
        realm = new UbibusJdbcRealm();
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    /**
     * <p>Loga um usuário no sistema. Verifica se o nome de usuário e a senha
     * estão corretos, se estiverem efetua o login mantendo na sessão o usuário
     * autenticado e o nome desse usuário(attribute=userFullName), caso
     * contrário emite uma mensagem ao usuário indicando o problema encontrado
     * durante o processo de autenticação.</p>
     */
    public void login() {
        try {
            //create a UsernamePasswordToken using the
            //username and password provided by the user
            UsernamePasswordToken token = new UsernamePasswordToken(
                    usuario.getLogin(),
                    new PasswordHash().hash256(usuario.getSenha()));


            //get the user (aka subject) associated with this request.
            Subject subject = SecurityUtils.getSubject();

            subject.login(token);
            //clear the information stored in the token
            token.clear();
            // alterando a date do ultimo acesso
            usuario = usuarioFacade.findByLogin(usuario.getLogin());
            usuario.setDataUltimoAcesso(new Date());
            usuarioFacade.edit(usuario);

            Session session = subject.getSession();

            // take the user full name
            String fullName = realm.getNameForUser(usuario.getLogin());

            session.setAttribute("userName", fullName);
            session.setTimeout(this.timeout);
            this.usuario = new Usuario();
            PontosInteressesBean.mapModel = new DefaultMapModel();
            String principal = (String) subject.getPrincipal();
            Usuario usuario = usuarioFacade.findByLogin(principal);
            List<PontosInteresse> list = pontosInteressesFacade.find(usuario);

            //System.out.println("LISTA POS CONSTRUCT: " + list);
//            for (PontosInteresse ocr : list) {
//                PontosInteressesMarker marker = new PontosInteressesMarker(new LatLng(
//                        ocr.getLocalizacao().getFirstPoint().getY(),
//                        ocr.getLocalizacao().getFirstPoint().getX()),
//                        ocr.getNome().toString());
//                marker.setClickable(true);
//                marker.setDraggable(false);
//                marker.setIdPontosInteresses(ocr.getId());
//                marker.setIcon(getImagePathPI());
//                PontosInteressesBean.mapModel.addOverlay(marker);
//                paradaMaisProxima(ocr);
//                //paradaMaisLonge(ocr);
//                //sugerirParadas(ocr);
//            }
            RotaBean.pontosInteresses = pontosInteressesFacade.find(usuario);
            JSFUtil.addSuccessMessage("", "Login efetuado com sucesso!");

        } catch (UnknownAccountException ex) {
            //username provided was not found
            JSFUtil.addErrorMessage("Falha de autenticação", "Não existe usuário cadastrado com este login");
        } catch (IncorrectCredentialsException ex) {
            JSFUtil.addErrorMessage("Falha de autenticação", "Senha informada está incorreta");
        } catch (Exception ex) {
            log.severe(ex.getMessage());
            JSFUtil.addCustomMessage(FacesMessage.SEVERITY_FATAL,
                    "Falha no servidor", "Falha no login devido a problemas no servidor. Tente novamente mais tarde.");
        }
    }

    public void sugerirParadas(Parada pMenor, List<Parada> paradasProximas) {

        for (Parada ocr : paradasProximas) {
            if (ocr.getId() != pMenor.getId()) {
                ParadaMarker marker = new ParadaMarker(new LatLng(
                        ocr.getLocalizacao().getFirstPoint().getY(),
                        ocr.getLocalizacao().getFirstPoint().getX()),
                        ocr.getNome().toString());
                marker.setClickable(true);
                marker.setDraggable(false);
                //marker.setIdParada(ocr.getId());
                marker.setIcon(getImagePathP());
                PontosInteressesBean.mapModel.addOverlay(marker);
            }
        }

    }

    public void paradaMaisProxima(PontosInteresse pi) {


        Parada paradaProxima = new Parada();
        Parada paradaLonge = new Parada();
        Double maior = 0.0, menor = 0.0;
        int i = 0;

        double distancia = 0.001; //aproximadamente 111m
        double distanciaPontos;
        paradasProximas = paradaFacade.findParadasProximas(new PGgeometry(pi.getLocalizacao()), distancia);

        while (paradasProximas.size() <= 0) {
            distancia += 0.001;
            paradasProximas = paradaFacade.findParadasProximas(new PGgeometry(pi.getLocalizacao()), distancia);
        }

        for (Parada parada : paradasProximas) {
            //System.out.println("PI localização: " + pi.getLocalizacao()+ " Parada localização: " + parada.getLocalizacao());
            distanciaPontos = paradaFacade.findDistanciaParadaPI(new PGgeometry(pi.getLocalizacao()), new PGgeometry(parada.getLocalizacao()));
            if (i == 0) {
                menor = distanciaPontos;
                //System.out.println("I: " + i + " menor: " + distanciaPontos + "\n");
            }

            paradaProxima = parada;
            paradaLonge = parada;
            if (distanciaPontos < menor) {
                //System.out.println("I: " + i + " Entrei no menor / Distancia: " + distanciaPontos + "\n");
                menor = distanciaPontos;
                paradaProxima = parada;
            } else {
                //System.out.println("I: " + i + " NÂO Entrei no menor / Distancia: " + distanciaPontos + "\n");
            }
            //System.out.println("menor: " + menor + "id parada menor: " + paradaProxima.getId());
            i++;            

        }
        
        //sugerirParadas(paradaProxima, paradasProximas);


        ParadaMarker markerParadaPerto = new ParadaMarker(new LatLng(
                paradaProxima.getLocalizacao().getFirstPoint().getY(),
                paradaProxima.getLocalizacao().getFirstPoint().getX()),
                paradaProxima.getNome() + "\nDistância: " + distancia + "\nPI: " + pi.getNome() + "\nDistancia do PI: " + menor);

        markerParadaPerto.setClickable(true);
        markerParadaPerto.setDraggable(false);
        markerParadaPerto.setIdParada(paradaProxima.getId());
        markerParadaPerto.setIcon(getImagePathPMenorDistancia());
        PontosInteressesBean.mapModel.addOverlay(markerParadaPerto);
        
        //sugerirParadas(paradaProxima, paradasProximas);


    }

    /**
     * <p>Desloga o usuário do sistema</p>
     */
    public void logout() {
        Subject subject = SecurityUtils.getSubject();

        if (subject != null) {
            subject.logout();
        }

        HttpSession session = JSFUtil.getSession(false);
        if (session != null) {
            session.invalidate();
        }

        JSFUtil.redirect("index.xhtml");
    }

    public boolean checkAuthenticity(Usuario user, String pwd) {
        String hashedPassword = new PasswordHash().hash256(pwd);
        return user.getSenha().equals(hashedPassword);
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

    public String getImagePathPMaiorDistancia() {
        String basePath = JSFUtil.getContext().getExternalContext().getRequestScheme()
                + "://" + JSFUtil.getContext().getExternalContext().getRequestServerName()
                + ":" + JSFUtil.getContext().getExternalContext().getRequestServerPort()
                + JSFUtil.getContext().getExternalContext().getRequestContextPath()
                + "/faces/resources/images/red-dot-pmaior.png";
        return basePath;
    }

    public String getImagePathPMenorDistancia() {
        String basePath = JSFUtil.getContext().getExternalContext().getRequestScheme()
                + "://" + JSFUtil.getContext().getExternalContext().getRequestServerName()
                + ":" + JSFUtil.getContext().getExternalContext().getRequestServerPort()
                + JSFUtil.getContext().getExternalContext().getRequestContextPath()
                + "/faces/resources/images/red-dot-pmenor.png";
        return basePath;
    }
}
