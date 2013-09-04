package br.com.ubibus.model.pojo;

import br.com.ubibus.model.pgconverter.CustomConverter;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
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
@Table(name = "ocorrencias")
@Converter(name = "converter", converterClass = CustomConverter.class)
public class Ocorrencia implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @SequenceGenerator(name = "SEQ_OCR_ID", sequenceName = "SQ_OCORRENCIAS", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_OCR_ID")
    @Column(name = "id_ocorrencia")
    private Integer id;
    @Column(name = "descr_ocorrencia")
    private String descricao;
    @Column(name = "data_hora")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataHora;
    @Column(name = "peso_fonte")
    private float pesoFonte;
    @Column(name = "localizacao", columnDefinition="geometry")
    @Convert(value = "converter")
    private Geometry localizacao;
    @Column(name = "status_resolvido")
    private boolean statusResolvido;
    @NotNull
    @Column(name = "referencia")
    private String referencia;
    @Column(name = "referencia_2")
    private String referencia2;
    @Column(name = "duracao_estimada")
    private int duracaoEstimada;
    @JoinColumn(name = "id_tipo_ocorrencia", referencedColumnName = "id_tipo_ocorrencia")
    @ManyToOne(optional = false)
    private TipoOcorrencia tipoOcorrencia;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ocorrencia")
    private List<Mensagem> mensagemList;
    @JoinColumn(name = "id_endereco", referencedColumnName = "id_endereco")
    @ManyToOne
    private Endereco endereco;

    public Ocorrencia() {
    }

    public Ocorrencia(Integer id) {
        this.id = id;
    }

    public Ocorrencia(Integer id, String descricao, Date dataHora, float pesoFonte, Geometry localizacao, boolean statusResolvido, String referencia, String referencia2, int duracaoEstimada) {
        this.id = id;
        this.descricao = descricao;
        this.dataHora = dataHora;
        this.pesoFonte = pesoFonte;
        this.localizacao = localizacao;
        this.statusResolvido = statusResolvido;
        this.referencia = referencia;
        this.referencia2 = referencia2;
        this.duracaoEstimada = duracaoEstimada;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Date getDataHora() {
        return dataHora;
    }

    public void setDataHora(Date dataHora) {
        this.dataHora = dataHora;
    }

    public float getPesoFonte() {
        return pesoFonte;
    }

    public void setPesoFonte(float pesoFonte) {
        this.pesoFonte = pesoFonte;
    }

    public Geometry getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(Geometry localizacao) {
        this.localizacao = localizacao;
    }

    public boolean getStatusResolvido() {
        return statusResolvido;
    }

    public void setStatusResolvido(boolean statusResolvido) {
        this.statusResolvido = statusResolvido;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public String getReferencia2() {
        return referencia2;
    }

    public void setReferencia2(String referencia2) {
        this.referencia2 = referencia2;
    }

    public int getDuracaoEstimada() {
        return duracaoEstimada;
    }

    public void setDuracaoEstimada(int duracaoEstimada) {
        this.duracaoEstimada = duracaoEstimada;
    }

    public TipoOcorrencia getTipoOcorrencia() {
        return tipoOcorrencia;
    }

    public void setTipoOcorrencia(TipoOcorrencia tipoOcorrencia) {
        this.tipoOcorrencia = tipoOcorrencia;
    }

    public List<Mensagem> getMensagemList() {
        return mensagemList;
    }

    public void setMensagemList(List<Mensagem> mensagemList) {
        this.mensagemList = mensagemList;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
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
        if (!(object instanceof Ocorrencia)) {
            return false;
        }
        Ocorrencia other = (Ocorrencia) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.edu.utfpr.pojos.Ocorrencia[ id=" + id + " ]";
    }
}
