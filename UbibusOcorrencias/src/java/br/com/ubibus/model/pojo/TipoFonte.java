package br.com.ubibus.model.pojo;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author <a href="mailto:marvas1987@gmail.com">Marcelo F. Vasconcelos</a>
 */
@Entity
@Table(name = "tipos_fontes")
public class TipoFonte implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @NotNull
    @Column(name = "id_tipo_fonte")
    private Integer id;
    @NotNull
    @Column(name = "descricao")
    private String descricao;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tipoFonte")
    private List<Mensagem> mensagenList;

    public TipoFonte() {
    }

    public TipoFonte(Integer id) {
        this.id = id;
    }

    public TipoFonte(Integer id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @XmlTransient
    public List<Mensagem> getMensagenList() {
        return mensagenList;
    }

    public void setMensagenList(List<Mensagem> mensagenList) {
        this.mensagenList = mensagenList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof TipoFonte)) {
            return false;
        }
        TipoFonte other = (TipoFonte) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.edu.utfpr.pojos.TipoFonte[ id=" + id + " ]";
    }
}
