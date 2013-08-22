package br.com.ubibus.model.pojo;

import br.com.ubibus.model.pojo.LinhasTrechos;
import br.com.ubibus.model.pojo.Parada;
import java.io.Serializable;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import org.postgis.Geometry;

@Generated(value="EclipseLink-2.3.0.v20110604-r9504", date="2013-08-22T09:29:53")
@StaticMetamodel(Trecho.class)
public class Trecho_ { 

    public static volatile SingularAttribute<Trecho, Integer> id;
    public static volatile SingularAttribute<Trecho, Float> duracaoEstimada;
    public static volatile SingularAttribute<Trecho, Float> velMedia;
    public static volatile SingularAttribute<Trecho, Geometry> pontosRota;
    public static volatile SingularAttribute<Trecho, Parada> paradaOrigem;
    public static volatile SingularAttribute<Trecho, Parada> paradaDestino;
    public static volatile SingularAttribute<Trecho, Integer> comprimento;
    public static volatile SingularAttribute<Trecho, Serializable> horaTransRuins;
    public static volatile ListAttribute<Trecho, LinhasTrechos> linhasTrechoList;

}