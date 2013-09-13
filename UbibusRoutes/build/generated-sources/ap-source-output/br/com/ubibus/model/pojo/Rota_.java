package br.com.ubibus.model.pojo;

import br.com.ubibus.model.pojo.Rota_PI;
import br.com.ubibus.model.pojo.Usuario_Rota;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.3.0.v20110604-r9504", date="2013-09-12T15:05:52")
@StaticMetamodel(Rota.class)
public class Rota_ { 

    public static volatile SingularAttribute<Rota, Integer> id;
    public static volatile ListAttribute<Rota, Rota_PI> rotasPontosList;
    public static volatile ListAttribute<Rota, Usuario_Rota> usuarioRotaList;
    public static volatile SingularAttribute<Rota, String> nome;

}