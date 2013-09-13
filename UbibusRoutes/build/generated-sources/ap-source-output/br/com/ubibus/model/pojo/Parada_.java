package br.com.ubibus.model.pojo;

import br.com.ubibus.model.pojo.Endereco;
import br.com.ubibus.model.pojo.LinhasParadas;
import br.com.ubibus.model.pojo.Trecho;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import org.postgis.Geometry;

@Generated(value="EclipseLink-2.3.0.v20110604-r9504", date="2013-09-12T15:05:52")
@StaticMetamodel(Parada.class)
public class Parada_ { 

    public static volatile SingularAttribute<Parada, Integer> id;
    public static volatile SingularAttribute<Parada, Integer> numeroRuaParada;
    public static volatile ListAttribute<Parada, Trecho> trechoList;
    public static volatile ListAttribute<Parada, LinhasParadas> linhasParadasList;
    public static volatile SingularAttribute<Parada, String> nome;
    public static volatile SingularAttribute<Parada, String> descricao;
    public static volatile SingularAttribute<Parada, Endereco> endereco;
    public static volatile SingularAttribute<Parada, Geometry> localizacao;
    public static volatile ListAttribute<Parada, Trecho> trechoList1;

}