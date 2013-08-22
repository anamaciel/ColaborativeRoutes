package br.com.ubibus.model.pojo;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author <a href="anacm.maciel@gmail.com">Ana Claudia Maciel</a>
 */
@Embeddable
public class UsuarioRotaPK implements Serializable {

    @NotNull
    @Column(name = "id_usuario")
    private int idUsuario;
    @NotNull
    @Column(name = "id_rota")
    private int idRota;
    

    public UsuarioRotaPK() {
    }

    public UsuarioRotaPK(int idUsuario, int idRota) {
        this.idUsuario = idUsuario;
        this.idRota = idRota;
    }

    public int getIdRota() {
        return idRota;
    }

    public void setIdRota(int idRota) {
        this.idRota = idRota;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }   
        
}
