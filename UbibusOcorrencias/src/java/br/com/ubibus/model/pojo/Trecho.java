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
@Table(name = "trechos")
@Converter(name = "converter", converterClass = CustomConverter.class)
public class Trecho implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @NotNull
    @Column(name = "id_trecho")
    private Integer id;
    @Column(name = "hora_trans_ruins")
    private Serializable horaTransRuins;
    @Column(name = "comprimento")
    private Integer comprimento;
    @Column(name = "vel_media")
    private Float velMedia;
    @Column(name = "duracao_estimada")
    private Float duracaoEstimada;
    @Column(name = "pontos_rota", columnDefinition="geometry")
    @Convert(value = "converter")
    private Geometry pontosRota;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "trecho")
    private List<LinhasTrechos> linhasTrechoList;
    @JoinColumn(name = "id_parada_origem", referencedColumnName = "id_parada")
    @ManyToOne(optional = false)
    private Parada paradaOrigem;
    @JoinColumn(name = "id_parada_destino", referencedColumnName = "id_parada")
    @ManyToOne(optional = false)
    private Parada paradaDestino;

    public Trecho() {
    }

    public Trecho(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Serializable getHoraTransRuins() {
        return horaTransRuins;
    }

    public void setHoraTransRuins(Serializable horaTransRuins) {
        this.horaTransRuins = horaTransRuins;
    }

    public Integer getComprimento() {
        return comprimento;
    }

    public void setComprimento(Integer comprimento) {
        this.comprimento = comprimento;
    }

    public Float getVelMedia() {
        return velMedia;
    }

    public void setVelMedia(Float velMedia) {
        this.velMedia = velMedia;
    }

    public Float getDuracaoEstimada() {
        return duracaoEstimada;
    }

    public void setDuracaoEstimada(Float duracaoEstimada) {
        this.duracaoEstimada = duracaoEstimada;
    }

    public Geometry getPontosRota() {
        return pontosRota;
    }

    public void setPontosRota(Geometry pontosRota) {
        this.pontosRota = pontosRota;
    }

    @XmlTransient
    public List<LinhasTrechos> getLinhasTrechoList() {
        return linhasTrechoList;
    }

    public void setLinhasTrechoList(List<LinhasTrechos> linhasTrechoList) {
        this.linhasTrechoList = linhasTrechoList;
    }

    public Parada getParadaOrigem() {
        return paradaOrigem;
    }

    public void setParadaOrigem(Parada paradaOrigem) {
        this.paradaOrigem = paradaOrigem;
    }

    public Parada getParadaDestino() {
        return paradaDestino;
    }

    public void setParadaDestino(Parada paradaDestino) {
        this.paradaDestino = paradaDestino;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Trecho)) {
            return false;
        }
        Trecho other = (Trecho) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.edu.utfpr.pojos.Trecho[ id=" + id + " ]";
    }
}
