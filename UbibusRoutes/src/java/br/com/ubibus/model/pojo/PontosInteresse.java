package br.com.ubibus.model.pojo;

import br.com.ubibus.model.pgconverter.CustomConverter;
import java.io.Serializable;
import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import org.eclipse.persistence.annotations.Convert;
import org.eclipse.persistence.annotations.Converter;
import org.postgis.Geometry;
import org.primefaces.model.map.LatLng;

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
    @SequenceGenerator(name = "SEQ_PI_ID", sequenceName = "SQ_PONTOS_INTERESSES", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_PI_ID")
    @Column(name = "id_ponto_interesse")
    private Integer id;
    @NotNull
    @Column(name = "nome")
    private String nome;
    @NotNull
    @Column(name = "localizacao", columnDefinition = "geometry")
    @Convert(value = "converter")
    private Geometry localizacao;
    @Column(name = "categoria")
    private String categoria;
    @JoinColumn(name = "id_endereco", referencedColumnName = "id_endereco")
    @ManyToOne
    private Endereco endereco;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ponto_interesse")
    private List<Usuario_PI> usuarioPiList;

    public PontosInteresse() {
    }

    public PontosInteresse(Integer id) {
        this.id = id;
    }

    public PontosInteresse(Integer id, String nome, Geometry localizacao, String categoria) {
        this.id = id;
        this.nome = nome;
        this.localizacao = localizacao;
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
    
    public LatLng getLatLng(){
        LatLng coord1 = new LatLng(this.localizacao.getFirstPoint().getY(), this.localizacao.getFirstPoint().getX()); 
        return coord1;
    }
    public void setLocalizacao(Geometry localizacao) {
        this.localizacao = localizacao;
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

    public List<Usuario_PI> getUsuarioPiList() {
        return usuarioPiList;
    }

    public void setUsuarioPiList(List<Usuario_PI> usuarioPiList) {
        this.usuarioPiList = usuarioPiList;
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
        return nome;
    }
}
