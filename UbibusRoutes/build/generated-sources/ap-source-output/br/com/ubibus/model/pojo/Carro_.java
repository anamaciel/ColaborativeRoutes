package br.com.ubibus.model.pojo;

import br.com.ubibus.model.pojo.EmpresasPermissionarias;
import br.com.ubibus.model.pojo.LinhasCarros;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.3.0.v20110604-r9504", date="2013-09-12T15:13:05")
@StaticMetamodel(Carro.class)
public class Carro_ { 

    public static volatile SingularAttribute<Carro, Integer> id;
    public static volatile SingularAttribute<Carro, Integer> capacidade_tanque;
    public static volatile ListAttribute<Carro, LinhasCarros> linhasCarrosList;
    public static volatile SingularAttribute<Carro, String> cor;
    public static volatile SingularAttribute<Carro, Boolean> acessibilidade;
    public static volatile SingularAttribute<Carro, String> placa;
    public static volatile SingularAttribute<Carro, Integer> capacidade_passag_sen;
    public static volatile SingularAttribute<Carro, EmpresasPermissionarias> empresa_permicionaria;
    public static volatile SingularAttribute<Carro, Integer> tipo_localizador;
    public static volatile SingularAttribute<Carro, Integer> capacidade_passag_pe;

}