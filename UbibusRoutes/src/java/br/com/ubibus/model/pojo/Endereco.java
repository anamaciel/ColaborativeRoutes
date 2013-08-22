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
@Table(name = "enderecos")
public class Endereco implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @NotNull
    @SequenceGenerator(name = "SEQ_END_ID", sequenceName = "SQ_ENDERECOS", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "SEQ_END_ID")
    @Column(name = "id_endereco")
    private Integer id;
    @NotNull
    @Column(name = "nome")
    private String nome;
    @Column(name = "bairro")
    private String bairro;
    @Column(name = "cep")
    private Integer cep;
    @Column(name = "tipo_endereco")
    private Integer tipo;
    @OneToMany(mappedBy = "endereco")
    private List<Parada> paradaList;
    @OneToMany(mappedBy = "endereco")
    private List<Ocorrencia> ocorrenciaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "endereco")
    private List<PontosInteresse> pontosInteressesList;

    public Endereco() {
    }

    public Endereco(Integer id) {
        this.id = id;
    }

    public Endereco(Integer id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public Integer getCep() {
        return cep;
    }

    public void setCep(Integer cep) {
        this.cep = cep;
    }

    public Integer getTipo() {
        return tipo;
    }

    public void setTipo(Integer tipo) {
        this.tipo = tipo;
    }

    @XmlTransient
    public List<Parada> getParadaList() {
        return paradaList;
    }

    public void setParadaList(List<Parada> paradaList) {
        this.paradaList = paradaList;
    }

    @XmlTransient
    public List<Ocorrencia> getOcorrenciaList() {
        return ocorrenciaList;
    }

    public void setOcorrenciaList(List<Ocorrencia> ocorrenciaList) {
        this.ocorrenciaList = ocorrenciaList;
    }

    @XmlTransient
    public List<PontosInteresse> getPontosInteressesList() {
        return pontosInteressesList;
    }

    public void setPontosInteressesList(List<PontosInteresse> pontosInteressesList) {
        this.pontosInteressesList = pontosInteressesList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Endereco)) {
            return false;
        }
        Endereco other = (Endereco) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.edu.utfpr.pojos.Endereco[ id=" + id + " ]";
    }
}
