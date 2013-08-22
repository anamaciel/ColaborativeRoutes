package br.com.ubibus.managedbean;

import br.com.ubibus.model.facade.TipoOcorrenciaFacade;
import br.com.ubibus.model.pojo.TipoOcorrencia;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author <a href="mailto:marvas1987@gmail.com">Marcelo F. Vasconcelos</a>
 */
@ManagedBean(name = "tipoOcorrenciaBean")
@SessionScoped
public class TipoOcorrenciaBean {

    @EJB
    private TipoOcorrenciaFacade tipoOcorrenciaFacade;

    public TipoOcorrenciaBean() {
    }

    public List<TipoOcorrencia> getTipoOcorrenciaList() {
        return tipoOcorrenciaFacade.findAll();
    }

    public TipoOcorrenciaConverter getTipoOcorrenciaConverter() {
        return new TipoOcorrenciaConverter();
    }

    @FacesConverter(forClass = TipoOcorrencia.class)
    public static class TipoOcorrenciaConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0 || value.equals("null")) {
                return null;
            }
            TipoOcorrenciaBean tpBean = (TipoOcorrenciaBean) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "tipoOcorrenciaBean");
            return tpBean.tipoOcorrenciaFacade.find(getKey(value));
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
            if (object instanceof TipoOcorrencia) {
                TipoOcorrencia o = (TipoOcorrencia) object;
                return getStringKey(o.getId());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + TipoOcorrencia.class.getName());
            }
        }
    }
}
