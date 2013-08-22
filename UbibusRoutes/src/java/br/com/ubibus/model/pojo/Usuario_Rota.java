/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ubibus.model.pojo;

import br.com.ubibus.model.pgconverter.CustomConverter;
import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import org.eclipse.persistence.annotations.Converter;

/**
 *
 * @author <a href="anacm.maciel@gmail.com">Ana Claudia Maciel</a>
 */
@Entity
@Table(name = "usuario_rota")
@Converter(name = "converter", converterClass = CustomConverter.class)
public class Usuario_Rota implements Serializable{
    
    
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected UsuarioRotaPK usuarioRotaPk;    
    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Usuario usuario;
    @JoinColumn(name = "id_rota", referencedColumnName = "id_rota", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Rota rota;    
    @NotNull
    @Column(name = "dono")
    private Boolean dono;


    public Usuario_Rota() {
    }

    public Usuario_Rota(UsuarioRotaPK usuarioRotaPk, Usuario usuario, Rota rota, Boolean dono) {
        this.usuarioRotaPk = usuarioRotaPk;
        this.usuario = usuario;
        this.rota = rota;
        this.dono = dono;
    }

    public Boolean getDono() {
        return dono;
    }

    public void setDono(Boolean dono) {
        this.dono = dono;
    }

    public Rota getRota() {
        return rota;
    }

    public void setRota(Rota rota) {
        this.rota = rota;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public UsuarioRotaPK getUsuarioRotaPk() {
        return usuarioRotaPk;
    }

    public void setUsuarioRotaPk(UsuarioRotaPK usuarioRotaPk) {
        this.usuarioRotaPk = usuarioRotaPk;
    }
    
    
}
