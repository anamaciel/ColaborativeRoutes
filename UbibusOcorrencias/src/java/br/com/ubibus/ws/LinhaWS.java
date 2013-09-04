package br.com.ubibus.ws;

import br.com.ubibus.model.facade.LinhaFacade;
import br.com.ubibus.model.pojo.Linha;
import br.com.ubibus.model.pojo.LinhasTrechos;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author marcelo
 */
@WebService(serviceName = "linhaWS", portName = "linhaWSPort", targetNamespace = "http://ws.ubibus.com.br/")
public class LinhaWS {

    @EJB
    private LinhaFacade ejbLinha;

    /**
     * This is a sample web service operation
     */
    @WebMethod(operationName = "findLinha")
    public String[] findLinha(@WebParam(name = "searchKey") String searchKey, @WebParam(name = "cidade") String cidade) {
        
        /*
         * @TODO implentar a busca utilizando a cidade (se é que isso é possivel...)
         */
        
        List<Linha> linhas;
        String result = null;
        if (searchKey == null || searchKey.equals("")) {
            linhas = ejbLinha.findByNumber(searchKey);
            if(linhas.isEmpty()){
                result = "INVALID_KEY";
            }
        } else {
            linhas = ejbLinha.findAll();
        }
        if (result == null){
            return prepareResult(linhas);
        } else {
            return new String[]{result};
        }
    }

    private String[] prepareResult(List<Linha> searchResult) {

        List<String> resultList = new ArrayList<String>();
        for (Linha linha : searchResult) {

            resultList.add(linha.getId()
                    + "$" + linha.getNumero()
                    + "$" + linha.getNome()
                    + "$" + linha.getCompLinha()
                    + "$" + pontosAsString(linha.getLinhasTrechoList()));
        }
        String[] result = (String[]) (resultList.toArray(new String[resultList.size()]));
        return result;
    }

    private String pontosAsString(List<LinhasTrechos> linhasTrecho) {
        // Ordena os trecho de acordo com a sequencia
        TrechosSort.sortList(linhasTrecho, 0, linhasTrecho.size() - 1);

        StringBuilder result = new StringBuilder();
        for (LinhasTrechos lt : linhasTrecho) {
            String value = lt.getTrecho().getPontosRota().getValue();
            if (value != null && value.length() > 0) {
                result.append(value.substring(1, value.length() - 1));
            }
        }
        return result.toString();
    }
}
