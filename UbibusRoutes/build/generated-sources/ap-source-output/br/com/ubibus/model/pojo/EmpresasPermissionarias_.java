package br.com.ubibus.model.pojo;

import br.com.ubibus.model.pojo.Carro;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.3.0.v20110604-r9504", date="2013-09-08T20:29:02")
@StaticMetamodel(EmpresasPermissionarias.class)
public class EmpresasPermissionarias_ { 

    public static volatile SingularAttribute<EmpresasPermissionarias, Integer> id;
    public static volatile SingularAttribute<EmpresasPermissionarias, String> nome_fantasia;
    public static volatile SingularAttribute<EmpresasPermissionarias, String> email;
    public static volatile SingularAttribute<EmpresasPermissionarias, String> telefone;
    public static volatile SingularAttribute<EmpresasPermissionarias, String> cnpj;
    public static volatile SingularAttribute<EmpresasPermissionarias, String> razao_social;
    public static volatile ListAttribute<EmpresasPermissionarias, Carro> carrosList;

}