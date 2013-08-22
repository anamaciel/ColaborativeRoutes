package br.com.ubibus.managedbean;

import br.com.ubibus.util.JSFUtil;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Locale;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author <a href="mailto:marvas1987@gmail.com">Marcelo F. Vasconcelos</a>
 */
@ManagedBean(name = "preferencesBean")
@SessionScoped
public class PreferencesBean implements Serializable {

    /**
     * Default theme
     */
    private String applicationTheme = "aristo";

    public String getApplicationTheme() {
        Map<String, String> params = JSFUtil.getContext().getExternalContext().getRequestParameterMap();

        if (params.containsKey("theme")) {
            applicationTheme = params.get("theme");
        }

        return applicationTheme;
    }

    public void setApplicationTheme(String temaAplicacao) {
        this.applicationTheme = temaAplicacao;
    }

    public int getYear() {
        return Calendar.getInstance().get(Calendar.YEAR);
    }
    
    public Locale getLocale() {
        return JSFUtil.getContext().getViewRoot().getLocale();
    }
}
