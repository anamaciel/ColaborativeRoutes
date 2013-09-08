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
@Table(name = "carros")
@Converter(name = "converter", converterClass = CustomConverter.class)
public class Carro implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @NotNull
    @Column(name = "id_carro")
    private Integer id;
    @Column(name = "capacidade_passag_pe")
    private Integer capacidade_passag_pe;
    @Column(name = "capacidade_passag_sen")
    private Integer capacidade_passag_sen;
    @Column(name = "capacidade_tanque")
    private Integer capacidade_tanque;
    @Column(name = "acessibilidade")
    private boolean acessibilidade;
    @Column(name = "cor")
    private String cor;
    @Column(name = "placa")
    private String placa;
    @Column(name = "tipo_localizador")
    private Integer tipo_localizador;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "carro")
    private List<LinhasCarros> linhasCarrosList;
    @JoinColumn(name = "id_empresa_permissionaria", referencedColumnName = "id_empresa_permissionaria")
    @ManyToOne
    private EmpresasPermissionarias empresa_permicionaria;

    public Carro() {
    }

    public Carro(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCapacidade_passag_pe() {
        return capacidade_passag_pe;
    }

    public void setCapacidade_passag_pe(Integer capacidade_passag_pe) {
        this.capacidade_passag_pe = capacidade_passag_pe;
    }

    public Integer getCapacidade_passag_sen() {
        return capacidade_passag_sen;
    }

    public void setCapacidade_passag_sen(Integer capacidade_passag_sen) {
        this.capacidade_passag_sen = capacidade_passag_sen;
    }

    public Integer getCapacidade_tanque() {
        return capacidade_tanque;
    }

    public void setCapacidade_tanque(Integer capacidade_tanque) {
        this.capacidade_tanque = capacidade_tanque;
    }

    public boolean isAcessibilidade() {
        return acessibilidade;
    }

    public void setAcessibilidade(boolean acessibilidade) {
        this.acessibilidade = acessibilidade;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public Integer getTipo_localizador() {
        return tipo_localizador;
    }

    public void setTipo_localizador(Integer tipo_localizador) {
        this.tipo_localizador = tipo_localizador;
    }

    public List<LinhasCarros> getLinhasCarrosList() {
        return linhasCarrosList;
    }

    public void setLinhasCarrosList(List<LinhasCarros> linhasCarrosList) {
        this.linhasCarrosList = linhasCarrosList;
    }

    public EmpresasPermissionarias getEmpresa_permicionaria() {
        return empresa_permicionaria;
    }

    public void setEmpresa_permicionaria(EmpresasPermissionarias empresa_permicionaria) {
        this.empresa_permicionaria = empresa_permicionaria;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Carro)) {
            return false;
        }
        Carro other = (Carro) object;
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
