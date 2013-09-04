package br.com.ubibus.model.pojo;

import br.com.ubibus.model.pgconverter.CustomConverter;
import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import org.eclipse.persistence.annotations.Convert;
import org.eclipse.persistence.annotations.Converter;
import org.postgis.Geometry;

/**
 *
 * @author <a href="mailto:marvas1987@gmail.com">Marcelo F. Vasconcelos</a>
 */
@Entity
@Table(name = "pontos_interesses")
@Converter(name = "converter", converterClass = CustomConverter.class)
public class PontosInteresse implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @NotNull
    @Column(name = "id_ponto_interesse")
    private Integer id;
    @NotNull
    @Column(name = "nome")
    private String nome;
    @NotNull
    @Column(name = "localizacao", columnDefinition="geometry")
    @Convert(value = "converter")
    private Geometry localizacao;
    @NotNull
    @Column(name = "numero_localizacao")
    private int numeroLocalizacao;
    @NotNull
    @Column(name = "categoria")
    private String categoria;
    @JoinColumn(name = "id_endereco", referencedColumnName = "id_endereco")
    @ManyToOne(optional = false)
    private Endereco endereco;

    public PontosInteresse() {
    }

    public PontosInteresse(Integer id) {
        this.id = id;
    }

    public PontosInteresse(Integer id, String nome, Geometry localizacao, int numeroLocalizacao, String categoria) {
        this.id = id;
        this.nome = nome;
        this.localizacao = localizacao;
        this.numeroLocalizacao = numeroLocalizacao;
        this.categoria = categoria;
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

    public int getNumeroLocalizacao() {
        return numeroLocalizacao;
    }

    public void setNumeroLocalizacao(int numeroLocalizacao) {
        this.numeroLocalizacao = numeroLocalizacao;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof PontosInteresse)) {
            return false;
        }
        PontosInteresse other = (PontosInteresse) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.edu.utfpr.pojos.PontosInteresses[ id=" + id + " ]";
    }
}
