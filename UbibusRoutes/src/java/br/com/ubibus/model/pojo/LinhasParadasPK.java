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
public class LinhasParadasPK implements Serializable {

    @NotNull
    @Column(name = "id_paradas")
    private int idParadas;
    @NotNull
    @Column(name = "id_linhas")
    private int idLinhas;

    public LinhasParadasPK() {
    }

    public LinhasParadasPK(int idParadas, int idLinhas) {
        this.idParadas = idParadas;
        this.idLinhas = idLinhas;
    }

    public int getIdParadas() {
        return idParadas;
    }

    public void setIdParadas(int idParadas) {
        this.idParadas = idParadas;
    }

    public int getIdLinhas() {
        return idLinhas;
    }

    public void setIdLinhas(int idLinhas) {
        this.idLinhas = idLinhas;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idParadas;
        hash += (int) idLinhas;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof LinhasParadasPK)) {
            return false;
        }
        LinhasParadasPK other = (LinhasParadasPK) object;
        if (this.idParadas != other.idParadas) {
            return false;
        }
        if (this.idLinhas != other.idLinhas) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.edu.utfpr.pojos.LinhasParadasPK[ idParadas=" + idParadas + ", idLinhas=" + idLinhas + " ]";
    }
}
