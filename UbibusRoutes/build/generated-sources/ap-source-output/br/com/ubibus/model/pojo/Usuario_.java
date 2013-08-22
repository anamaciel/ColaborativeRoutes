package br.com.ubibus.model.pojo;

import br.com.ubibus.model.pojo.Mensagem;
import br.com.ubibus.model.pojo.PreferenciasUsuario;
import br.com.ubibus.model.pojo.Usuario_PI;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import org.postgis.Geometry;

@Generated(value="EclipseLink-2.3.0.v20110604-r9504", date="2013-08-22T09:29:53")
@StaticMetamodel(Usuario.class)
public class Usuario_ { 

    public static volatile SingularAttribute<Usuario, Integer> id;
    public static volatile SingularAttribute<Usuario, PreferenciasUsuario> preferenciasUsuario;
    public static volatile SingularAttribute<Usuario, String> email;
    public static volatile SingularAttribute<Usuario, Integer> tipo;
    public static volatile SingularAttribute<Usuario, String> login;
    public static volatile SingularAttribute<Usuario, String> nome;
    public static volatile ListAttribute<Usuario, Mensagem> mensagens;
    public static volatile SingularAttribute<Usuario, Date> dataUltimoAcesso;
    public static volatile SingularAttribute<Usuario, Date> dataIngresso;
    public static volatile SingularAttribute<Usuario, String> senha;
    public static volatile SingularAttribute<Usuario, Long> idFace;
    public static volatile ListAttribute<Usuario, Usuario_PI> usuarioPiList;
    public static volatile SingularAttribute<Usuario, Geometry> localizacao;

}