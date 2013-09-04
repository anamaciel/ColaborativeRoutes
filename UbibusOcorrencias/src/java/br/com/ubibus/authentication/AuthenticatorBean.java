package br.com.ubibus.authentication;

import br.com.ubibus.model.facade.UsuarioFacade;
import br.com.ubibus.model.pojo.Usuario;
import br.com.ubibus.util.JSFUtil;
import br.com.ubibus.util.PasswordHash;
import java.util.Date;
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
    private Usuario usuario;
    private UbibusJdbcRealm realm;
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
}
