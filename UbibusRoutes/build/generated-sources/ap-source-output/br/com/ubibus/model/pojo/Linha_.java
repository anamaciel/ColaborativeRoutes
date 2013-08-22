package br.com.ubibus.model.pojo;

import br.com.ubibus.model.pojo.LinhasParadas;
import br.com.ubibus.model.pojo.LinhasTrechos;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.3.0.v20110604-r9504", date="2013-08-22T09:29:53")
@StaticMetamodel(Linha.class)
public class Linha_ { 

    public static volatile SingularAttribute<Linha, Integer> id;
    public static volatile ListAttribute<Linha, LinhasParadas> linhasParadasList;
    public static volatile SingularAttribute<Linha, String> empresa;
    public static volatile SingularAttribute<Linha, Float> compLinha;
    public static volatile SingularAttribute<Linha, String> nome;
    public static volatile SingularAttribute<Linha, String> numero;
    public static volatile ListAttribute<Linha, LinhasTrechos> linhasTrechoList;

}