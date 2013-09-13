package br.com.ubibus.model.pojo;

import br.com.ubibus.model.pojo.Carro;
import br.com.ubibus.model.pojo.Linha;
import br.com.ubibus.model.pojo.LinhasCarrosPK;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import org.postgis.Geometry;

@Generated(value="EclipseLink-2.3.0.v20110604-r9504", date="2013-09-12T15:05:52")
@StaticMetamodel(LinhasCarros.class)
public class LinhasCarros_ { 

    public static volatile SingularAttribute<LinhasCarros, Integer> ordemParada;
    public static volatile SingularAttribute<LinhasCarros, Boolean> em_transito;
    public static volatile SingularAttribute<LinhasCarros, byte[]> periodo_percurso;
    public static volatile SingularAttribute<LinhasCarros, Carro> carro;
    public static volatile SingularAttribute<LinhasCarros, Linha> linha;
    public static volatile SingularAttribute<LinhasCarros, LinhasCarrosPK> LinhasCarrosPK;
    public static volatile SingularAttribute<LinhasCarros, Geometry> localizacao;

}