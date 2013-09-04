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
@Table(name = "tipos_ocorrencias")
public class TipoOcorrencia implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @NotNull
    @Column(name = "id_tipo_ocorrencia")
    private Integer id;
    @NotNull
    @Column(name = "tipo")
    private String tipo;
    @NotNull
    @Column(name = "categoria")
    private String categoria;
    @NotNull
    @Column(name = "estimativa_atraso")
    private int estimativaAtraso;
    @NotNull
    @Column(name = "impacto_ocorrencia")
    private int impactoOcorrencia;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tipoOcorrencia")
    private List<Ocorrencia> ocorrenciaList;

    public TipoOcorrencia() {
    }

    public TipoOcorrencia(Integer id) {
        this.id = id;
    }

    public TipoOcorrencia(Integer id, String tipo, String categoria, int estimativaAtraso, int impactoOcorrencia) {
        this.id = id;
        this.tipo = tipo;
        this.categoria = categoria;
        this.estimativaAtraso = estimativaAtraso;
        this.impactoOcorrencia = impactoOcorrencia;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public int getEstimativaAtraso() {
        return estimativaAtraso;
    }

    public void setEstimativaAtraso(int estimativaAtraso) {
        this.estimativaAtraso = estimativaAtraso;
    }

    public int getImpactoOcorrencia() {
        return impactoOcorrencia;
    }

    public void setImpactoOcorrencia(int impactoOcorrencia) {
        this.impactoOcorrencia = impactoOcorrencia;
    }

    @XmlTransient
    public List<Ocorrencia> getOcorrenciaList() {
        return ocorrenciaList;
    }

    public void setOcorrenciaList(List<Ocorrencia> ocorrenciaList) {
        this.ocorrenciaList = ocorrenciaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof TipoOcorrencia)) {
            return false;
        }
        TipoOcorrencia other = (TipoOcorrencia) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return this.categoria + " - " + this.tipo;
    }
}
