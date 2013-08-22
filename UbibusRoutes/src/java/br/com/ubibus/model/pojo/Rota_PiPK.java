package br.com.ubibus.model.pojo;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author <a href="mailto:anacm.maciel@gmail.com">Ana Claudia Maciel</a>
 */
@Embeddable
public class Rota_PiPK implements Serializable {

    @NotNull
    @Column(name = "id_rota")
    private int idRota;
    @NotNull
    @Column(name = "id_ponto_interesse")
    private int idPi;
    @Column(name = "next_poi")
    private int nextPi;

    public Rota_PiPK() {
    }

    public Rota_PiPK(int idRota, int idPi, int nextPi) {
        this.idRota = idRota;
        this.idPi = idPi;
        this.nextPi = nextPi;
    }

    public int getIdPi() {
        return idPi;
    }

    public void setIdPi(int idPi) {
        this.idPi = idPi;
    }

    public int getIdRota() {
        return idRota;
    }

    public void setIdRota(int idRota) {
        this.idRota = idRota;
    }

    public int getNextPi() {
        return nextPi;
    }

    public void setNextPi(int nextPi) {
        this.nextPi = nextPi;
    }    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idPi;
        hash += (int) idRota;
        hash += (int) nextPi;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Rota_PiPK)) {
            return false;
        }
        Rota_PiPK other = (Rota_PiPK) object;
        if (this.idRota != other.idRota) {
            return false;
        }
        if (this.idPi != other.idPi) {
            return false;
        }
        if (this.nextPi != other.nextPi) {
            return false;
        }
        return true;
    }

}
