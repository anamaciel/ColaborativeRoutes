package br.com.ubibus.model.pojo;

import br.com.ubibus.model.pojo.Ocorrencia;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.3.0.v20110604-r9504", date="2013-09-12T15:05:52")
@StaticMetamodel(TipoOcorrencia.class)
public class TipoOcorrencia_ { 

    public static volatile SingularAttribute<TipoOcorrencia, Integer> id;
    public static volatile SingularAttribute<TipoOcorrencia, String> categoria;
    public static volatile SingularAttribute<TipoOcorrencia, Integer> estimativaAtraso;
    public static volatile ListAttribute<TipoOcorrencia, Ocorrencia> ocorrenciaList;
    public static volatile SingularAttribute<TipoOcorrencia, String> tipo;
    public static volatile SingularAttribute<TipoOcorrencia, Integer> impactoOcorrencia;

}