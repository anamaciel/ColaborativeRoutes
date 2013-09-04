package br.com.ubibus.model.pojo;

import br.com.ubibus.model.pojo.Linha;
import br.com.ubibus.model.pojo.LinhasParadasPK;
import br.com.ubibus.model.pojo.Parada;
import java.math.BigInteger;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.3.0.v20110604-r9504", date="2013-07-09T10:34:47")
@StaticMetamodel(LinhasParadas.class)
public class LinhasParadas_ { 

    public static volatile SingularAttribute<LinhasParadas, Integer> ordemParada;
    public static volatile SingularAttribute<LinhasParadas, BigInteger> distParadaAnterior;
    public static volatile SingularAttribute<LinhasParadas, Integer> paradaAnterior;
    public static volatile SingularAttribute<LinhasParadas, BigInteger> distParadaPosterior;
    public static volatile SingularAttribute<LinhasParadas, Linha> linha;
    public static volatile SingularAttribute<LinhasParadas, Integer> paradaPosterior;
    public static volatile SingularAttribute<LinhasParadas, LinhasParadasPK> linhasParadasPK;
    public static volatile SingularAttribute<LinhasParadas, Parada> parada;

}