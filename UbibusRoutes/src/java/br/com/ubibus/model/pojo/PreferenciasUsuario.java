package br.com.ubibus.model.pojo;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 *
 * @author <a href="mailto:marvas1987@gmail.com">Marcelo F. Vasconcelos</a>
 */
@Entity
@Table(name = "preferencias_usuarios")
public class PreferenciasUsuario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @NotNull
    @Column(name = "id_usuario")
    private Integer id;
    @NotNull
    @Column(name = "linhas_preferenciais")
    private Serializable linhasPreferenciais;
    @NotNull
    @Column(name = "horarios_preferenciais")
    private long horariosPreferenciais;
    @NotNull
    @Column(name = "pontos_interesses")
    private Serializable pontosInteresses;
    @NotNull
    @Column(name = "port_nec_especial")
    private boolean portNecEspecial;
    @NotNull
    @Column(name = "tipo_nec_especial")
    private int tipoNecEspecial;
    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Usuario usuario;

    public PreferenciasUsuario() {
    }

    public PreferenciasUsuario(Integer id) {
        this.id = id;
    }

    public PreferenciasUsuario(Integer id, Serializable linhasPreferenciais, long horariosPreferenciais, Serializable pontosInteresses, boolean portNecEspecial, int tipoNecEspecial) {
        this.id = id;
        this.linhasPreferenciais = linhasPreferenciais;
        this.horariosPreferenciais = horariosPreferenciais;
        this.pontosInteresses = pontosInteresses;
        this.portNecEspecial = portNecEspecial;
        this.tipoNecEspecial = tipoNecEspecial;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Serializable getLinhasPreferenciais() {
        return linhasPreferenciais;
    }

    public void setLinhasPreferenciais(Serializable linhasPreferenciais) {
        this.linhasPreferenciais = linhasPreferenciais;
    }

    public long getHorariosPreferenciais() {
        return horariosPreferenciais;
    }

    public void setHorariosPreferenciais(long horariosPreferenciais) {
        this.horariosPreferenciais = horariosPreferenciais;
    }

    public Serializable getPontosInteresses() {
        return pontosInteresses;
    }

    public void setPontosInteresses(Serializable pontosInteresses) {
        this.pontosInteresses = pontosInteresses;
    }

    public boolean getPortNecEspecial() {
        return portNecEspecial;
    }

    public void setPortNecEspecial(boolean portNecEspecial) {
        this.portNecEspecial = portNecEspecial;
    }

    public int getTipoNecEspecial() {
        return tipoNecEspecial;
    }

    public void setTipoNecEspecial(int tipoNecEspecial) {
        this.tipoNecEspecial = tipoNecEspecial;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof PreferenciasUsuario)) {
            return false;
        }
        PreferenciasUsuario other = (PreferenciasUsuario) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.edu.utfpr.pojos.PreferenciasUsuario[ id=" + id + " ]";
    }
}
