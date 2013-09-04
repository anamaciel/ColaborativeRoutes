package br.com.ubibus.model.pojo;

import br.com.ubibus.model.pojo.Endereco;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import org.postgis.Geometry;

@Generated(value="EclipseLink-2.3.0.v20110604-r9504", date="2013-07-09T10:34:47")
@StaticMetamodel(PontosInteresse.class)
public class PontosInteresse_ { 

    public static volatile SingularAttribute<PontosInteresse, Integer> id;
    public static volatile SingularAttribute<PontosInteresse, String> categoria;
    public static volatile SingularAttribute<PontosInteresse, Integer> numeroLocalizacao;
    public static volatile SingularAttribute<PontosInteresse, String> nome;
    public static volatile SingularAttribute<PontosInteresse, Endereco> endereco;
    public static volatile SingularAttribute<PontosInteresse, Geometry> localizacao;

}