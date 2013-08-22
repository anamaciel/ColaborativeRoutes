package br.com.ubibus.model.pojo;

import br.com.ubibus.model.pojo.Endereco;
import br.com.ubibus.model.pojo.Mensagem;
import br.com.ubibus.model.pojo.TipoOcorrencia;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import org.postgis.Geometry;

@Generated(value="EclipseLink-2.3.0.v20110604-r9504", date="2013-08-22T09:29:53")
@StaticMetamodel(Ocorrencia.class)
public class Ocorrencia_ { 

    public static volatile SingularAttribute<Ocorrencia, Integer> id;
    public static volatile SingularAttribute<Ocorrencia, TipoOcorrencia> tipoOcorrencia;
    public static volatile SingularAttribute<Ocorrencia, Integer> duracaoEstimada;
    public static volatile ListAttribute<Ocorrencia, Mensagem> mensagemList;
    public static volatile SingularAttribute<Ocorrencia, Float> pesoFonte;
    public static volatile SingularAttribute<Ocorrencia, Date> dataHora;
    public static volatile SingularAttribute<Ocorrencia, Endereco> endereco;
    public static volatile SingularAttribute<Ocorrencia, String> referencia2;
    public static volatile SingularAttribute<Ocorrencia, String> descricao;
    public static volatile SingularAttribute<Ocorrencia, Geometry> localizacao;
    public static volatile SingularAttribute<Ocorrencia, Boolean> statusResolvido;
    public static volatile SingularAttribute<Ocorrencia, String> referencia;

}