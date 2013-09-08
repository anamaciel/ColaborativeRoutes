package br.com.ubibus.model.pojo;

import br.com.ubibus.model.pojo.Linha;
import br.com.ubibus.model.pojo.LinhasTrechosPK;
import br.com.ubibus.model.pojo.Trecho;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.3.0.v20110604-r9504", date="2013-09-08T20:29:02")
@StaticMetamodel(LinhasTrechos.class)
public class LinhasTrechos_ { 

    public static volatile SingularAttribute<LinhasTrechos, Linha> linha;
    public static volatile SingularAttribute<LinhasTrechos, LinhasTrechosPK> linhasTrechoPK;
    public static volatile SingularAttribute<LinhasTrechos, Integer> sequencia;
    public static volatile SingularAttribute<LinhasTrechos, Trecho> trecho;

}