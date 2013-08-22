package br.com.ubibus.model.pojo;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author <a href="mailto:marvas1987@gmail.com">Marcelo F. Vasconcelos</a>
 */
@Embeddable
public class LinhasTrechosPK implements Serializable {

    @NotNull
    @Column(name = "id_trecho")
    private int idTrecho;
    @NotNull
    @Column(name = "id_linha")
    private int idLinha;

    public LinhasTrechosPK() {
    }

    public LinhasTrechosPK(int idTrecho, int idLinha) {
        this.idTrecho = idTrecho;
        this.idLinha = idLinha;
    }

    public int getIdTrecho() {
        return idTrecho;
    }

    public void setIdTrecho(int idTrecho) {
        this.idTrecho = idTrecho;
    }

    public int getIdLinha() {
        return idLinha;
    }

    public void setIdLinha(int idLinha) {
        this.idLinha = idLinha;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idTrecho;
        hash += (int) idLinha;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof LinhasTrechosPK)) {
            return false;
        }
        LinhasTrechosPK other = (LinhasTrechosPK) object;
        if (this.idTrecho != other.idTrecho) {
            return false;
        }
        if (this.idLinha != other.idLinha) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.edu.utfpr.pojos.LinhasTrechoPK[ idTrecho=" + idTrecho + ", idLinha=" + idLinha + " ]";
    }
}
