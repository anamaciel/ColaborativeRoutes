package br.com.ubibus.model.pojo;

import java.io.Serializable;
import java.math.BigInteger;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import org.postgis.Geometry;

/**
 *
 * @author <a href="mailto:marvas1987@gmail.com">Marcelo F. Vasconcelos</a>
 */
@Entity
@Table(name = "linhas_carros")
public class LinhasCarros implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected LinhasCarrosPK LinhasCarrosPK;
    @NotNull
    @Column(name = "intervalo_saida")
    private int ordemParada;
    @Column(name = "localizacao")
    @org.eclipse.persistence.annotations.Convert(value = "converter")
    private Geometry localizacao;
    @NotNull
    @Column(name = "em_transito")
    private boolean em_transito;
    @Lob 
    @Column(name = "periodo_percurso")
    private byte[] periodo_percurso; 
    @JoinColumn(name = "id_carro", referencedColumnName = "id_carro", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Carro carro;
    @JoinColumn(name = "id_linha", referencedColumnName = "id_linha", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Linha linha;

    public LinhasCarros() {
    }

    public LinhasCarrosPK getLinhasCarrosPK() {
        return LinhasCarrosPK;
    }

    public void setLinhasCarrosPK(LinhasCarrosPK LinhasCarrosPK) {
        this.LinhasCarrosPK = LinhasCarrosPK;
    }

    public int getOrdemParada() {
        return ordemParada;
    }

    public void setOrdemParada(int ordemParada) {
        this.ordemParada = ordemParada;
    }

    public Geometry getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(Geometry localizacao) {
        this.localizacao = localizacao;
    }

    public boolean isEm_transito() {
        return em_transito;
    }

    public void setEm_transito(boolean em_transito) {
        this.em_transito = em_transito;
    }

    public byte[] getPeriodo_percurso() {
        return periodo_percurso;
    }

    public void setPeriodo_percurso(byte[] periodo_percurso) {
        this.periodo_percurso = periodo_percurso;
    }

    public Carro getCarro() {
        return carro;
    }

    public void setCarro(Carro carro) {
        this.carro = carro;
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
        hash += (LinhasCarrosPK != null ? LinhasCarrosPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof LinhasCarros)) {
            return false;
        }
        LinhasCarros other = (LinhasCarros) object;
        if ((this.LinhasCarrosPK == null && other.LinhasCarrosPK != null) || (this.LinhasCarrosPK != null && !this.LinhasCarrosPK.equals(other.LinhasCarrosPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.edu.utfpr.pojos.LinhasCarros[ LinhasCarrosPK=" + LinhasCarrosPK + " ]";
    }
}
