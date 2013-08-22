package br.com.ubibus.model.pgconverter;

import br.com.ubibus.model.facade.PontosInteressesFacade;
import br.com.ubibus.model.pojo.PontosInteresse;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.convert.FacesConverter;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;

@FacesConverter(value = "pontosInteresseConverter")
public class PontosInteresseConverter implements Converter{
   
   //private RemetenteDao remetenteDao = new RemetenteDao();
    
    @EJB
    public PontosInteressesFacade pontosInteressesFacade;

//    public void setRemetenteFacade(RemetenteDao facade) {
//        this.remetenteDao = facade;
//    }

   public List<PontosInteresse> pontos;

//    public PontosInteresseConverter() {
//        pontos = pontosInteressesFacade.findAll();
//    }  
   
    
   @Override
   public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
       System.out.println("OBJETO");
       if(value == null || value.length() == 0 || value.equals("null")){
           return null;
       }
       System.out.println("==========="+value);
       //System.out.println(pontosInteressesFacade.findPonto(3645));
       //return pontosInteressesFacade.findPonto(3645);
       return value;
       
//       if (value.trim().equals("")) {
//            return null;
//        } else {
//            try {
//                int number = Integer.parseInt(value);
//
//                for (PontosInteresse u : pontos) {
//                    System.out.println("==========="+u.getNome());
//                    if (u.getId()== number) {
//                        return u;
//                    }
//                }
//
//            } catch (NumberFormatException exception) {
//                throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Conversion Error", "Not a valid player"));
//            }
//        }

        //return null;
   }

   @Override
   public String getAsString(FacesContext facesContext, UIComponent component, Object value) {
       System.out.println("STRING");
       if ((value == null) || (value instanceof String)){
           return null;
       }        
       return ((PontosInteresse)value).getId().toString();
   }
}