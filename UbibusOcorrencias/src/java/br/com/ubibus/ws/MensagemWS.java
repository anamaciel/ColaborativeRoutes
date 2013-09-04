package br.com.ubibus.ws;

import br.com.ubibus.model.facade.MensagemFacade;
import br.com.ubibus.model.facade.TipoFonteFacade;
import br.com.ubibus.model.facade.UsuarioFacade;
import br.com.ubibus.model.pojo.Mensagem;
import br.com.ubibus.model.pojo.TipoFonte;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import org.postgis.Geometry;
import org.postgis.Point;

/**
 *
 * @author <a href="mailto:marvas1987@gmail.com">Marcelo F. Vasconcelos</a>
 */
@WebService(serviceName = "mensagemWS", portName = "mensagemWSPort", targetNamespace = "http://ws.ubibus.com.br/")
public class MensagemWS {

    @EJB
    private MensagemFacade ejbMensagem;
    @EJB
    private TipoFonteFacade ejbTipoFonte;
    @EJB
    private UsuarioFacade ejbUsuario;

    @WebMethod(operationName = "createMensagem")
    public String createMensagem(
            @WebParam(name = "mensagem") String mensagem,
            @WebParam(name = "data") long data,
            @WebParam(name = "tipoFonte") String tipoFonte,
            @WebParam(name = "idUsuario") Integer idUsuario,
            @WebParam(name = "localizacaoOcorrencia") String localizacaoOcorrencia) {

        TipoFonte tf = ejbTipoFonte.findByDescricao(tipoFonte);
        if (tf == null) {
            return "TIPO_FONTE_INEXISTENTE";
        }

        Mensagem entity = new Mensagem();
        entity.setMensagem(mensagem);
        entity.setDataHora(new Date(data));
        entity.setTipoFonte(tf);
        entity.setUsuario(ejbUsuario.find(idUsuario));
        entity.setLocalizacaoOcorrencia(stringAslocation(localizacaoOcorrencia));
        ejbMensagem.create(entity);

        return "OK";
    }

    @WebMethod(operationName = "findMensagem")
    public String[] findMensagem(@WebParam(name = "searchKey") String searchKey) {
        List<Mensagem> msgs;
        if (searchKey == null || searchKey.equals("")) {
            msgs = ejbMensagem.find(searchKey);
        } else {
            msgs = ejbMensagem.findAll();
        }
        return prepareResult(msgs);
    }

    private String[] prepareResult(List<Mensagem> searchResult) {

        List<String> resultList = new ArrayList<String>();
        for (Mensagem msg : searchResult) {

            resultList.add(msg.getId()
                    + "$" + msg.getMensagem()
                    + "$" + msg.getDataHora().getTime()
                    + "$" + msg.getTipoFonte().getDescricao()
                    + "$" + msg.getUsuario().getId()
                    + "$" + locationAsString(msg.getLocalizacaoOcorrencia()));
        }
        String[] result = (String[]) (resultList.toArray(new String[resultList.size()]));
        return result;
    }

    private Geometry stringAslocation(String location) {
        String[] latLng = location.split(",");
        Point point = new Point(Double.parseDouble(latLng[0]), Double.parseDouble(latLng[0]));
        return point;
    }

    private String locationAsString(Geometry location) {
        StringBuilder result = new StringBuilder();
        result.append(((Point) location).getX());
        result.append(",");
        result.append(((Point) location).getY());
        return result.toString();
    }
}
