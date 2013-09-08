package br.com.ubibus.model.pojo;

import br.com.ubibus.model.pojo.Mensagem;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.3.0.v20110604-r9504", date="2013-09-08T20:29:02")
@StaticMetamodel(TipoFonte.class)
public class TipoFonte_ { 

    public static volatile SingularAttribute<TipoFonte, Integer> id;
    public static volatile ListAttribute<TipoFonte, Mensagem> mensagenList;
    public static volatile SingularAttribute<TipoFonte, String> descricao;

}