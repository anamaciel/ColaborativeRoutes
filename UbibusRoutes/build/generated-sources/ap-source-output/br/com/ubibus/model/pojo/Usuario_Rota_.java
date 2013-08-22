package br.com.ubibus.model.pojo;

import br.com.ubibus.model.pojo.Rota;
import br.com.ubibus.model.pojo.Usuario;
import br.com.ubibus.model.pojo.UsuarioRotaPK;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.3.0.v20110604-r9504", date="2013-08-22T09:29:53")
@StaticMetamodel(Usuario_Rota.class)
public class Usuario_Rota_ { 

    public static volatile SingularAttribute<Usuario_Rota, UsuarioRotaPK> usuarioRotaPk;
    public static volatile SingularAttribute<Usuario_Rota, Usuario> usuario;
    public static volatile SingularAttribute<Usuario_Rota, Rota> rota;
    public static volatile SingularAttribute<Usuario_Rota, Boolean> dono;

}