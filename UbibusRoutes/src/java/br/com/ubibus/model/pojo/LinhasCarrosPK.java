package br.com.ubibus.model.pojo;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author <a href="anacm.maciel@gmail.com">Ana Claudia Maciel</a>
 */
@Embeddable
public class LinhasCarrosPK implements Serializable {

    @NotNull
    @Column(name = "id_carro")
    private int idCarro;
    @NotNull
    @Column(name = "id_linhas")
    private int idLinhas;

    public LinhasCarrosPK() {
    }

    public LinhasCarrosPK(int idCarro, int idLinhas) {
        this.idCarro = idCarro;
        this.idLinhas = idLinhas;
    }

    public int getIdCarro() {
        return idCarro;
    }

    public void setIdCarro(int idCarro) {
        this.idCarro = idCarro;
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
        hash += (int) idCarro;
        hash += (int) idLinhas;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof LinhasCarrosPK)) {
            return false;
        }
        LinhasCarrosPK other = (LinhasCarrosPK) object;
        if (this.idCarro != other.idCarro) {
            return false;
        }
        if (this.idLinhas != other.idLinhas) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.edu.utfpr.pojos.LinhasParadasPK[ idCarro=" + idCarro + ", idLinhas=" + idLinhas + " ]";
    }
}
