package br.com.ubibus.model.pojo;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author <a href="mailto:marvas1987@gmail.com">Marcelo F. Vasconcelos</a>
 */
@Entity
@Table(name = "linhas")
public class Linha implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @NotNull
    @Column(name = "id_linha")
    private Integer id;
    @Size(max = 6)
    @Column(name = "numero")
    private String numero;
    @NotNull
    @Column(name = "nome")
    private String nome;
    @NotNull
    @Column(name = "empresa")
    private String empresa;
    @Column(name = "preco")
    private Double preco;
    @Column(name = "comprimento_linha")
    private Float compLinha;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "linha")
    private List<LinhasParadas> linhasParadasList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "linha")
    private List<LinhasTrechos> linhasTrechoList;

    public Linha() {
    }

    public Linha(Integer id) {
        this.id = id;
    }

    public Linha(Integer id, String nome, String empresa) {
        this.id = id;
        this.nome = nome;
        this.empresa = empresa;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public Float getCompLinha() {
        return compLinha;
    }

    public void setCompLinha(Float compLinha) {
        this.compLinha = compLinha;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }
    
    @XmlTransient
    public List<LinhasParadas> getLinhasParadasList() {
        return linhasParadasList;
    }

    public void setLinhasParadasList(List<LinhasParadas> linhasParadasList) {
        this.linhasParadasList = linhasParadasList;
    }

    @XmlTransient
    public List<LinhasTrechos> getLinhasTrechoList() {
        return linhasTrechoList;
    }

    public void setLinhasTrechoList(List<LinhasTrechos> linhasTrechoList) {
        this.linhasTrechoList = linhasTrechoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Linha)) {
            return false;
        }
        Linha other = (Linha) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.edu.utfpr.pojos.Linha[ id=" + id + " ]";
    }
}
