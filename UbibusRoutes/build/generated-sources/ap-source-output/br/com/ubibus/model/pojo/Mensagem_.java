package br.com.ubibus.model.pojo;

import br.com.ubibus.model.pojo.Ocorrencia;
import br.com.ubibus.model.pojo.TipoFonte;
import br.com.ubibus.model.pojo.Usuario;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import org.postgis.Geometry;

@Generated(value="EclipseLink-2.3.0.v20110604-r9504", date="2013-09-08T20:29:02")
@StaticMetamodel(Mensagem.class)
public class Mensagem_ { 

    public static volatile SingularAttribute<Mensagem, Integer> id;
    public static volatile SingularAttribute<Mensagem, Ocorrencia> ocorrencia;
    public static volatile SingularAttribute<Mensagem, Usuario> usuario;
    public static volatile SingularAttribute<Mensagem, String> mensagem;
    public static volatile SingularAttribute<Mensagem, TipoFonte> tipoFonte;
    public static volatile SingularAttribute<Mensagem, Date> dataHora;
    public static volatile SingularAttribute<Mensagem, Geometry> localizacaoOcorrencia;
    public static volatile SingularAttribute<Mensagem, Geometry> localizacaoUsuario;

}