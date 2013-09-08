package br.com.ubibus.model.pojo;

import br.com.ubibus.model.pojo.Ocorrencia;
import br.com.ubibus.model.pojo.Parada;
import br.com.ubibus.model.pojo.PontosInteresse;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.3.0.v20110604-r9504", date="2013-09-08T20:29:02")
@StaticMetamodel(Endereco.class)
public class Endereco_ { 

    public static volatile SingularAttribute<Endereco, Integer> id;
    public static volatile SingularAttribute<Endereco, String> bairro;
    public static volatile SingularAttribute<Endereco, Integer> cep;
    public static volatile ListAttribute<Endereco, Parada> paradaList;
    public static volatile ListAttribute<Endereco, Ocorrencia> ocorrenciaList;
    public static volatile ListAttribute<Endereco, PontosInteresse> pontosInteressesList;
    public static volatile SingularAttribute<Endereco, Integer> tipo;
    public static volatile SingularAttribute<Endereco, String> nome;

}