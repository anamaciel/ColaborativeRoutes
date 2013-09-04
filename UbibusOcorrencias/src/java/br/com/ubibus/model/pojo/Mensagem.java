package br.com.ubibus.model.pojo;

import br.com.ubibus.model.pgconverter.CustomConverter;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import org.eclipse.persistence.annotations.Convert;
import org.eclipse.persistence.annotations.Converter;
import org.postgis.Geometry;

/**
 *
 * @author <a href="mailto:marvas1987@gmail.com">Marcelo F. Vasconcelos</a>
 */
@Entity
@Table(name = "mensagens")
@Converter(name = "converter", converterClass = CustomConverter.class)
public class Mensagem implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @SequenceGenerator(name = "SEQ_MSG_ID", sequenceName = "SQ_MENSAGENS", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_MSG_ID")
    @Column(name = "id_mensagem")
    private Integer id;
    @Column(name = "mensagem")
    private String mensagem;
    @Column(name = "data_hora")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataHora;
    @JoinColumn(name = "id_tipo_fonte", referencedColumnName = "id_tipo_fonte")
    @ManyToOne(optional = false)
    private TipoFonte tipoFonte;
    @JoinColumn(name = "id_ocorrencia", referencedColumnName = "id_ocorrencia")
    @ManyToOne
    private Ocorrencia ocorrencia;
    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario")
    @ManyToOne(optional = false)
    private Usuario usuario;
    @Column(name = "localizacao_ocorrencia", columnDefinition = "geometry")
    @Convert(value = "converter")
    private Geometry localizacaoOcorrencia;
    @Column(name = "localizacao_usuario", columnDefinition = "geometry")
    @Convert(value = "converter")
    private Geometry localizacaoUsuario;

    public Mensagem() {
    }

    public Mensagem(Integer id) {
        this.id = id;
    }

    public Mensagem(Integer id, String mensagem, Date dataHora) {
        this.id = id;
        this.mensagem = mensagem;
        this.dataHora = dataHora;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public Date getDataHora() {
        return dataHora;
    }

    public void setDataHora(Date dataHora) {
        this.dataHora = dataHora;
    }

    public TipoFonte getTipoFonte() {
        return tipoFonte;
    }

    public void setTipoFonte(TipoFonte tipoFonte) {
        this.tipoFonte = tipoFonte;
    }

    public Ocorrencia getOcorrencia() {
        return ocorrencia;
    }

    public void setOcorrencia(Ocorrencia ocorrencia) {
        this.ocorrencia = ocorrencia;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Geometry getLocalizacaoOcorrencia() {
        return localizacaoOcorrencia;
    }

    public void setLocalizacaoOcorrencia(Geometry localizacaoOcorrencia) {
        this.localizacaoOcorrencia = localizacaoOcorrencia;
    }

    public Geometry getLocalizacaoUsuario() {
        return localizacaoUsuario;
    }

    public void setLocalizacaoUsuario(Geometry localizacaoUsuario) {
        this.localizacaoUsuario = localizacaoUsuario;
    }

    /**
     * Verifica se este objeto novo ou se ele foi recuperado a partir de uma
     * fonte dados.
     *
     * @return retorna
     * {@link true} caso seja um objeto e
     * {@link false} caso contrário.
     */
    public boolean isNew() {
        return (id == null || id == 0);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Mensagem)) {
            return false;
        }
        Mensagem other = (Mensagem) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.edu.utfpr.pojos.Mensagen[ id=" + id + " ]";
    }
}
