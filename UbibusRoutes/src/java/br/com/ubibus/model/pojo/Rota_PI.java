/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ubibus.model.pojo;

import br.com.ubibus.model.pgconverter.CustomConverter;
import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import org.eclipse.persistence.annotations.Converter;

/**
 *
 * @author <a href="anacm.maciel@gmail.com">Ana Claudia Maciel</a>
 */
@Entity
@Table(name = "rota_poi")
@Converter(name = "converter", converterClass = CustomConverter.class)
public class Rota_PI implements Serializable{
    
//    private static final long serialVersionUID = 1L;
//    @Id
//    @NotNull
//    @SequenceGenerator(name = "SEQ_UI_ID", sequenceName = "SQ_USUARIO_PI", allocationSize = 1)
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_UI_ID")
//    @Column(name = "id_ri")
//    private Integer id;
//    @JoinColumn(name = "id_rota", referencedColumnName = "id_rota")
//    @ManyToOne(optional = false)
//    private Rota rota;
//    @JoinColumn(name = "id_ponto_interesse", referencedColumnName = "id_ponto_interesse")
//    @ManyToOne
//    private PontosInteresse ponto_interesse;    
//    @NotNull
//    @JoinColumn(name = "next_poi")
//    private PontosInteresse next_ponto_interesse;
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected Rota_PiPK rotaPiPk;    
    @JoinColumn(name = "id_rota", referencedColumnName = "id_rota", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Rota rota;
    @JoinColumn(name = "id_ponto_interesse", referencedColumnName = "id_ponto_interesse", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private PontosInteresse ponto_interesse;
    @JoinColumn(name = "next_poi", referencedColumnName = "id_ponto_interesse", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private PontosInteresse next_ponto_interesse;


    public Rota_PI() {
    }

    public Rota_PI(Rota rota, PontosInteresse ponto_interesse, PontosInteresse next_ponto_interesse) {
        this.rota = rota;
        this.ponto_interesse = ponto_interesse;
        this.next_ponto_interesse = next_ponto_interesse;
    }

    public PontosInteresse getNext_ponto_interesse() {
        return next_ponto_interesse;
    }

    public void setNext_ponto_interesse(PontosInteresse next_ponto_interesse) {
        this.next_ponto_interesse = next_ponto_interesse;
    }

    public PontosInteresse getPonto_interesse() {
        return ponto_interesse;
    }

    public void setPonto_interesse(PontosInteresse ponto_interesse) {
        this.ponto_interesse = ponto_interesse;
    }

    public Rota getRota() {
        return rota;
    }

    public void setRota(Rota rota) {
        this.rota = rota;
    }

    public Rota_PiPK getRotaPiPk() {
        return rotaPiPk;
    }

    public void setRotaPiPk(Rota_PiPK rotaPiPk) {
        this.rotaPiPk = rotaPiPk;
    }
    
    
    
}
