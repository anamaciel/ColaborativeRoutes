package br.com.ubibus.model.pojo;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 *
 * @author <a href="mailto:marvas1987@gmail.com">Marcelo F. Vasconcelos</a>
 */
@Entity
@Table(name = "linhas_trechos")
public class LinhasTrechos implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected LinhasTrechosPK linhasTrechoPK;
    @NotNull
    @Column(name = "sequencia")
    private int sequencia;
    @JoinColumn(name = "id_trecho", referencedColumnName = "id_trecho", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Trecho trecho;
    @JoinColumn(name = "id_linha", referencedColumnName = "id_linha", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Linha linha;

    public LinhasTrechos() {
    }

    public LinhasTrechos(LinhasTrechosPK linhasTrechoPK) {
        this.linhasTrechoPK = linhasTrechoPK;
    }

    public LinhasTrechos(LinhasTrechosPK linhasTrechoPK, int sequencia) {
        this.linhasTrechoPK = linhasTrechoPK;
        this.sequencia = sequencia;
    }

    public LinhasTrechos(int idTrecho, int idLinha) {
        this.linhasTrechoPK = new LinhasTrechosPK(idTrecho, idLinha);
    }

    public LinhasTrechosPK getLinhasTrechoPK() {
        return linhasTrechoPK;
    }

    public void setLinhasTrechoPK(LinhasTrechosPK linhasTrechoPK) {
        this.linhasTrechoPK = linhasTrechoPK;
    }

    public int getSequencia() {
        return sequencia;
    }

    public void setSequencia(int sequencia) {
        this.sequencia = sequencia;
    }

    public Trecho getTrecho() {
        return trecho;
    }

    public void setTrecho(Trecho trecho) {
        this.trecho = trecho;
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
        hash += (linhasTrechoPK != null ? linhasTrechoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof LinhasTrechos)) {
            return false;
        }
        LinhasTrechos other = (LinhasTrechos) object;
        if ((this.linhasTrechoPK == null && other.linhasTrechoPK != null) || (this.linhasTrechoPK != null && !this.linhasTrechoPK.equals(other.linhasTrechoPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.edu.utfpr.pojos.LinhasTrecho[ linhasTrechoPK=" + linhasTrechoPK + " ]";
    }
}
