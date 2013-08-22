package br.com.ubibus.model.pojo;

import br.com.ubibus.model.pgconverter.CustomConverter;
import java.io.Serializable;
import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlTransient;
import org.eclipse.persistence.annotations.Convert;
import org.eclipse.persistence.annotations.Converter;
import org.postgis.Geometry;

/**
 *
 * @author <a href="mailto:marvas1987@gmail.com">Marcelo F. Vasconcelos</a>
 */
@Entity
@Table(name = "paradas")
@Converter(name = "converter", converterClass = CustomConverter.class)
public class Parada implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @NotNull
    @Column(name = "id_parada")
    private Integer id;
    @Column(name = "nome")
    private String nome;
    @NotNull
    @Column(name = "localizacao")
    @Convert(value = "converter")
    private Geometry localizacao;
    @Column(name = "numero_rua_parada")
    private Integer numeroRuaParada;
    @Column(name = "descricao")
    private String descricao;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "parada")
    private List<LinhasParadas> linhasParadasList;
    @JoinColumn(name = "id_endereco", referencedColumnName = "id_endereco")
    @ManyToOne
    private Endereco endereco;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "paradaOrigem")
    private List<Trecho> trechoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "paradaDestino")
    private List<Trecho> trechoList1;

    public Parada() {
    }

    public Parada(Integer id) {
        this.id = id;
    }

    public Parada(Integer id, Geometry localizacao) {
        this.id = id;
        this.localizacao = localizacao;
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

    public Geometry getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(Geometry localizacao) {
        this.localizacao = localizacao;
    }

    public Integer getNumeroRuaParada() {
        return numeroRuaParada;
    }

    public void setNumeroRuaParada(Integer numeroRuaParada) {
        this.numeroRuaParada = numeroRuaParada;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @XmlTransient
    public List<LinhasParadas> getLinhasParadasList() {
        return linhasParadasList;
    }

    public void setLinhasParadasList(List<LinhasParadas> linhasParadasList) {
        this.linhasParadasList = linhasParadasList;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    @XmlTransient
    public List<Trecho> getTrechoList() {
        return trechoList;
    }

    public void setTrechoList(List<Trecho> trechoList) {
        this.trechoList = trechoList;
    }

    @XmlTransient
    public List<Trecho> getTrechoList1() {
        return trechoList1;
    }

    public void setTrechoList1(List<Trecho> trechoList1) {
        this.trechoList1 = trechoList1;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Parada)) {
            return false;
        }
        Parada other = (Parada) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.edu.utfpr.pojos.Parada[ id=" + id + " ]";
    }
}
