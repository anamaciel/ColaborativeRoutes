/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ubibus.model.pojo;

import br.com.ubibus.model.pgconverter.CustomConverter;
import java.io.Serializable;
import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import org.eclipse.persistence.annotations.Converter;

/**
 *
 * @author <a href="anacm.maciel@gmail.com">Ana Claudia Maciel</a>
 */
@Entity
@Table(name = "rota")
@Converter(name = "converter", converterClass = CustomConverter.class)
public class Rota implements Serializable{
    private static final long serialVersionUID = 1L;
    @Id
    @NotNull
    @SequenceGenerator(name = "SEQ_R_ID", sequenceName = "SQ_ROTA", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_R_ID")
    @Column(name = "id_rota")
    private Integer id;    
    @NotNull
    @Column(name = "nome")
    private String nome;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "rota")
    private List<Rota_PI> rotasPontosList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "rota")
    private List<Usuario_Rota> usuarioRotaList;

    public Rota() {
    }

    public Rota(Integer id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Rota_PI> getRotasPontosList() {
        return rotasPontosList;
    }

    public void setRotasPontosList(List<Rota_PI> rotasPontosList) {
        this.rotasPontosList = rotasPontosList;
    }

    public List<Usuario_Rota> getUsuarioRotaList() {
        return usuarioRotaList;
    }

    public void setUsuarioRotaList(List<Usuario_Rota> usuarioRotaList) {
        this.usuarioRotaList = usuarioRotaList;
    }
    
    
    
}
