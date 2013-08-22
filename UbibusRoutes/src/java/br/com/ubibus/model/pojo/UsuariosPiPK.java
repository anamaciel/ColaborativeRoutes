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
public class UsuariosPiPK implements Serializable {

    @NotNull
    @Column(name = "id_usuario")
    private int idUsuario;
    @NotNull
    @Column(name = "id_ponto_interesse")
    private int idPontosInteresse;
    

    public UsuariosPiPK() {
    }

    public UsuariosPiPK(int idUsuario, int idPontosInteresse) {
        this.idUsuario = idUsuario;
        this.idPontosInteresse = idPontosInteresse;
    }

    public int getIdPontosInteresse() {
        return idPontosInteresse;
    }

    public void setIdPontosInteresse(int idPontosInteresse) {
        this.idPontosInteresse = idPontosInteresse;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    @Override
    public String toString() {
        return "UsuariosPiPK{" + "idUsuario=" + idUsuario + ", idPontosInteresse=" + idPontosInteresse + '}';
    }    
        
}
