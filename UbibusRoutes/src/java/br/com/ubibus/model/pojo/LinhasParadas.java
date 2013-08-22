package br.com.ubibus.model.pojo;

import java.io.Serializable;
import java.math.BigInteger;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 *
 * @author <a href="mailto:marvas1987@gmail.com">Marcelo F. Vasconcelos</a>
 */
@Entity
@Table(name = "linhas_paradas")
public class LinhasParadas implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected LinhasParadasPK linhasParadasPK;
    @NotNull
    @Column(name = "ordem_parada")
    private int ordemParada;
    @NotNull
    @Column(name = "dist_parada_anterior")
    private BigInteger distParadaAnterior;
    @NotNull
    @Column(name = "parada_anterior")
    private int paradaAnterior;
    @NotNull
    @Column(name = "parada_posterior")
    private int paradaPosterior;
    @NotNull
    @Column(name = "dist_parada_posterior")
    private BigInteger distParadaPosterior;
    @JoinColumn(name = "id_parada", referencedColumnName = "id_parada", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Parada parada;
    @JoinColumn(name = "id_linha", referencedColumnName = "id_linha", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Linha linha;

    public LinhasParadas() {
    }

    public LinhasParadas(LinhasParadasPK linhasParadasPK) {
        this.linhasParadasPK = linhasParadasPK;
    }

    public LinhasParadas(LinhasParadasPK linhasParadasPK, int ordemParada, BigInteger distParadaAnt, int paradaAnterior, int paradaPosterior, BigInteger distParadaPost) {
        this.linhasParadasPK = linhasParadasPK;
        this.ordemParada = ordemParada;
        this.distParadaAnterior = distParadaAnt;
        this.paradaAnterior = paradaAnterior;
        this.paradaPosterior = paradaPosterior;
        this.distParadaPosterior = distParadaPost;
    }

    public LinhasParadas(int idParadas, int idLinhas) {
        this.linhasParadasPK = new LinhasParadasPK(idParadas, idLinhas);
    }

    public LinhasParadasPK getLinhasParadasPK() {
        return linhasParadasPK;
    }

    public void setLinhasParadasPK(LinhasParadasPK linhasParadasPK) {
        this.linhasParadasPK = linhasParadasPK;
    }

    public int getOrdemParada() {
        return ordemParada;
    }

    public void setOrdemParada(int ordemParada) {
        this.ordemParada = ordemParada;
    }

    public BigInteger getDistParadaAnterior() {
        return distParadaAnterior;
    }

    public void setDistParadaAnterior(BigInteger distParadaAnt) {
        this.distParadaAnterior = distParadaAnt;
    }

    public int getParadaAnterior() {
        return paradaAnterior;
    }

    public void setParadaAnterior(int paradaAnterior) {
        this.paradaAnterior = paradaAnterior;
    }

    public int getParadaPosterior() {
        return paradaPosterior;
    }

    public void setParadaPosterior(int paradaPosterior) {
        this.paradaPosterior = paradaPosterior;
    }

    public BigInteger getDistParadaPosterior() {
        return distParadaPosterior;
    }

    public void setDistParadaPosterior(BigInteger distParadaPosterior) {
        this.distParadaPosterior = distParadaPosterior;
    }

    public Parada getParada() {
        return parada;
    }

    public void setParada(Parada parada) {
        this.parada = parada;
    }

    public Linha getLinha() {
        return linha;
    }

    public void setLinha(Linha linha) {
        this.linha = linha;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (linhasParadasPK != null ? linhasParadasPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof LinhasParadas)) {
            return false;
        }
        LinhasParadas other = (LinhasParadas) object;
        if ((this.linhasParadasPK == null && other.linhasParadasPK != null) || (this.linhasParadasPK != null && !this.linhasParadasPK.equals(other.linhasParadasPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.edu.utfpr.pojos.LinhasParadas[ linhasParadasPK=" + linhasParadasPK + " ]";
    }
}
