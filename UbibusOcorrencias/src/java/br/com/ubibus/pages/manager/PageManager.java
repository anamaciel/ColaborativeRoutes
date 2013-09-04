package br.com.ubibus.pages.manager;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 * Classe responsável por gerenciar o fluxo de páginas da aplicação.
 *
 * @author <a href="mailto:marvas1987@gmail.com">Marcelo F. Vasconcelos</a>
 */
@ManagedBean(name = "pageManager")
@SessionScoped
public class PageManager {

    private String paginaAtiva = "./pages/mapAddOcorrencia.xhtml";
    private String topContent = "./components/top.xhtml";
    private String leftMenu = "./components/menu.xhtml";
    private String login = "./components/login.xhtml";
    private String bottomContent = "./components/bottom.xhtml";
    private boolean pageMap = true;

    public PageManager() {
    }

    public String getPaginaAtiva() {
        return paginaAtiva;
    }

    public void setPaginaAtiva(String paginaAtiva) {
        this.paginaAtiva = paginaAtiva;
    }

    public String getTopContent() {
        return topContent;
    }

    public void setTopContent(String topContent) {
        this.topContent = topContent;
    }

    public String getLeftMenu() {
        return leftMenu;
    }

    public void setLeftMenu(String leftMenu) {
        this.leftMenu = leftMenu;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public boolean isPageMap() {
        return pageMap;
    }

    public void setPageMap(boolean pageMap) {
        this.pageMap = pageMap;
    }

    public String getBottomContent() {
        return bottomContent;
    }

    public void setBottomContent(String bottomContent) {
        this.bottomContent = bottomContent;
    }

    public String setMapAddOcorrencia() {
        this.paginaAtiva = "./pages/mapAddOcorrencia.xhtml";
        setPageMap(true);
        return "refreshPage";
    }

    public String setCadastroUsuario() {
        this.paginaAtiva = "./pages/createUsuario.xhtml";
        setPageMap(false);
        return "refreshPage";
    }

    public String setTutorial() {
        this.paginaAtiva = "./pages/tutorial.xhtml";
        setPageMap(false);
        return "refreshPage";
    }

    public String setAlterarConfiguracao() {
        this.paginaAtiva = "./pages/usuarioConfig.xhtml";
        setPageMap(false);
        return "refreshPage";
    }
}