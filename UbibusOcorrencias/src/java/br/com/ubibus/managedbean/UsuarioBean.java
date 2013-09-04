package br.com.ubibus.managedbean;

import br.com.ubibus.authentication.AuthenticatorBean;
import br.com.ubibus.model.facade.LinhaFacade;
import br.com.ubibus.model.facade.UsuarioFacade;
import br.com.ubibus.model.pojo.Linha;
import br.com.ubibus.model.pojo.Usuario;
import br.com.ubibus.util.JSFUtil;
import br.com.ubibus.util.PasswordHash;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

/**
 *
 * @author <a href="mailto:marvas1987@gmail.com">Marcelo F. Vasconcelos</a>
 */
@ManagedBean(name = "usuarioBean")
@SessionScoped
public class UsuarioBean {

    @EJB
    private UsuarioFacade usuarioFacade;
    @EJB
    private LinhaFacade linhaFacade;
    private Usuario usuario;
    private String confirmaSenha;
    private String novaSenha;
    private String senhaAtual;
    private boolean senhaEditavel = false;

    public UsuarioBean() {
        if (usuario == null) {
            usuario = new Usuario();
        }
    }

    public void salvar() {
        if (!usuario.getSenha().equals(confirmaSenha)) {

            JSFUtil.addCustomMessage(FacesMessage.SEVERITY_WARN, "Atenção", "Senhas não conferem");
            JSFUtil.addMessageToComponentID("", "Senhas não conferem", "confirm");
            confirmaSenha = null;
            return;
        }
        if (usuarioJaExiste(usuario.getLogin())) {
            JSFUtil.addErrorMessage("Atenção", "Este login não está disponível, escolha outro.");
            JSFUtil.addMessageToComponentID("", "Login indisponível", "login");
            return;
        }
        try {
            // criptografando a senha do usuário
            String hash = new PasswordHash().hash256(usuario.getSenha());
            usuario.setSenha(hash);
            // informando a datas e tipo de usuario
            usuario.setDataIngresso(new Date());
            usuario.setDataUltimoAcesso(new Date());
            usuario.setTipo(1);

            // persistindo usuario no banco
            usuarioFacade.create(usuario);
            // limpando dados
            usuario = new Usuario();
            confirmaSenha = null;
            JSFUtil.addSuccessMessage("Concluído", "Conta criada com sucesso");

        } catch (Exception ex) {
            JSFUtil.addCustomMessage(FacesMessage.SEVERITY_FATAL, "Falha no servidor", "Houve um erro interno e não foi possivel realizar o cadastro");
            usuario.setSenha(confirmaSenha);
        }
    }

    public void editar() {
        if (senhaEditavel) {
            if (new AuthenticatorBean().checkAuthenticity(usuario, senhaAtual)) {
                if (!novaSenha.equals(confirmaSenha)) {
                    JSFUtil.addCustomMessage(FacesMessage.SEVERITY_WARN, "Atenção", "Senhas não conferem");
                    JSFUtil.addMessageToComponentID("", "Senhas não conferem", "confirm");
                    confirmaSenha = null;
                    return;
                }
                // criptografando a senha do usuário
                String hash = new PasswordHash().hash256(novaSenha);
                usuario.setSenha(hash);
            } else {
                JSFUtil.addCustomMessage(FacesMessage.SEVERITY_WARN, "Atenção", "A senha atual está incorreta");
                JSFUtil.addMessageToComponentID("", "Senha incorreta", "senhaAtual");
                clearFields();
                return;
            }
        }
        try {
            // persistindo usuario no banco
            usuarioFacade.edit(usuario);
            JSFUtil.addSuccessMessage("Concluído", "Seus dados foram alterados com sucesso!");
        } catch (Exception ex) {
            JSFUtil.addCustomMessage(FacesMessage.SEVERITY_FATAL, "Falha no servidor", "Houve um erro interno e não foi possivel realizar as alterações");
            cancelarEdicao();
            return;
        }
        clearFields();
        setSenhaEditavel(false);
    }

    private boolean usuarioJaExiste(String login) {
        Usuario u = usuarioFacade.findByLogin(login);
        return u != null;
    }

    public void alterarConfiguracao() {
        Subject subject = SecurityUtils.getSubject();
        String principal = (String) subject.getPrincipal();
        this.usuario = usuarioFacade.findByLogin(principal);
    }

    public void alterarSenha() {
        this.senhaEditavel = true;
    }

    public void limparCampos() {
        usuario = new Usuario();
    }

    public void cancelarEdicao() {
        alterarConfiguracao();
        setSenhaEditavel(false);
    }

    private void clearFields() {
        this.confirmaSenha = null;
        this.novaSenha = null;
        this.senhaAtual = null;
    }

    ///////// Converter //////////
    public UsuarioConverter getUsuarioConverter() {
        return new UsuarioConverter();
    }

    @FacesConverter(forClass = Usuario.class)
    public static class UsuarioConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0 || value.equals("null")) {
                return null;
            }
            UsuarioBean usuarioBean = (UsuarioBean) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "usuarioBean");
            return usuarioBean.usuarioFacade.find(getKey(value));
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
            if (object instanceof Usuario) {
                Usuario o = (Usuario) object;
                return getStringKey(o.getId());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + Usuario.class.getName());
            }
        }
    }

    public String getConfirmaSenha() {
        return confirmaSenha;
    }

    public void setConfirmaSenha(String confirmaSenha) {
        this.confirmaSenha = confirmaSenha;
    }

    public String getNovaSenha() {
        return novaSenha;
    }

    public void setNovaSenha(String novaSenha) {
        this.novaSenha = novaSenha;
    }

    public String getSenhaAtual() {
        return senhaAtual;
    }

    public void setSenhaAtual(String senhaAtual) {
        this.senhaAtual = senhaAtual;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<Usuario> getUsuarioList() {
        List<Usuario> lista = usuarioFacade.findAll();
        return lista;
    }

    public List<Linha> getLinhaList() {
        return linhaFacade.findAll();
    }

    public boolean isSenhaEditavel() {
        return senhaEditavel;
    }

    public void setSenhaEditavel(boolean senhaEditavel) {
        this.senhaEditavel = senhaEditavel;
    }
}
