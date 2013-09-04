package br.com.ubibus.model.pojo;

import br.com.ubibus.model.pojo.Usuario;
import java.io.Serializable;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.3.0.v20110604-r9504", date="2013-07-09T10:34:47")
@StaticMetamodel(PreferenciasUsuario.class)
public class PreferenciasUsuario_ { 

    public static volatile SingularAttribute<PreferenciasUsuario, Integer> id;
    public static volatile SingularAttribute<PreferenciasUsuario, Serializable> linhasPreferenciais;
    public static volatile SingularAttribute<PreferenciasUsuario, Long> horariosPreferenciais;
    public static volatile SingularAttribute<PreferenciasUsuario, Usuario> usuario;
    public static volatile SingularAttribute<PreferenciasUsuario, Serializable> pontosInteresses;
    public static volatile SingularAttribute<PreferenciasUsuario, Boolean> portNecEspecial;
    public static volatile SingularAttribute<PreferenciasUsuario, Integer> tipoNecEspecial;

}