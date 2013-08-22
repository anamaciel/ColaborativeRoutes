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
@Table(name = "usuario_pi")
@Converter(name = "converter", converterClass = CustomConverter.class)
public class Usuario_PI implements Serializable{
    
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected UsuariosPiPK usuariosPiPK;  
    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Usuario usuario;
    @JoinColumn(name = "id_ponto_interesse", referencedColumnName = "id_ponto_interesse", insertable = false, updatable = false)
    @ManyToOne
    private PontosInteresse ponto_interesse;

    public Usuario_PI(UsuariosPiPK usuariosPiPK, Usuario usuario, PontosInteresse ponto_interesse) {
        this.usuariosPiPK = usuariosPiPK;
        this.usuario = usuario;
        this.ponto_interesse = ponto_interesse;
    }

    public Usuario_PI() {
    }

    public PontosInteresse getPontos_interesse() {
        return ponto_interesse;
    }

    public void setPontos_interesse(PontosInteresse pontos_interesse) {
        this.ponto_interesse = pontos_interesse;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public UsuariosPiPK getUsuariosPiPK() {
        return usuariosPiPK;
    }

    public void setUsuariosPiPK(UsuariosPiPK usuariosPiPK) {
        this.usuariosPiPK = usuariosPiPK;
    }   
     
    
}
