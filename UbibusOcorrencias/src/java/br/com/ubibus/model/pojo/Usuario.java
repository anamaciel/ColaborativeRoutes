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
@Table(name = "usuarios")
@Converter(name = "converter", converterClass = CustomConverter.class)
public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @NotNull
    @SequenceGenerator(name = "SEQ_USR_ID", sequenceName = "SQ_USUARIOS", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_USR_ID")
    @Column(name = "id_usuario")
    private Integer id;
    @Column(name = "id_face")
    private Long idFace;
    @NotNull
    @Column(name = "nome")
    private String nome;
    @NotNull
    @Column(name = "login")
    private String login;
    @NotNull
    @Column(name = "senha")
    private String senha;
    @NotNull
    @Column(name = "email")
    private String email;
    @NotNull
    @Column(name = "data_ingresso")
    @Temporal(TemporalType.DATE)
    private Date dataIngresso;
    @NotNull
    @Column(name = "data_ultimo_acesso")
    @Temporal(TemporalType.DATE)
    private Date dataUltimoAcesso;
    @NotNull
    @Column(name = "tipo_usuario")
    private Integer tipo;
    @Column(name = "localizacao", columnDefinition = "geometry")
    @Convert(value = "converter")
    private Geometry localizacao;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "usuario", orphanRemoval = true)
    private PreferenciasUsuario preferenciasUsuario;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuario", orphanRemoval = true)
    private List<Mensagem> mensagens;

    public Usuario() {
    }

    public Usuario(Integer idUsuario) {
        this.id = idUsuario;
    }

    public Usuario(Integer id, String nomeUsuario, String login, String senha, String email, Date dataIngresso, Date dataUltimoAcesso, Integer tipo, Geometry localizacao) {
        this.id = id;
        this.nome = nomeUsuario;
        this.login = login;
        this.senha = senha;
        this.email = email;
        this.dataIngresso = dataIngresso;
        this.dataUltimoAcesso = dataUltimoAcesso;
        this.tipo = tipo;
        this.localizacao = localizacao;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getIdFace() {
        return idFace;
    }

    public void setIdFace(Long idFace) {
        this.idFace = idFace;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDataIngresso() {
        return dataIngresso;
    }

    public void setDataIngresso(Date dataIngresso) {
        this.dataIngresso = dataIngresso;
    }

    public Date getDataUltimoAcesso() {
        return dataUltimoAcesso;
    }

    public void setDataUltimoAcesso(Date dataUltimoAcesso) {
        this.dataUltimoAcesso = dataUltimoAcesso;
    }

    public Integer getTipo() {
        return tipo;
    }

    public void setTipo(Integer tipo) {
        this.tipo = tipo;
    }

    public Geometry getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(Geometry localizacao) {
        this.localizacao = localizacao;
    }

    public PreferenciasUsuario getPreferenciasUsuario() {
        return preferenciasUsuario;
    }

    public void setPreferenciasUsuario(PreferenciasUsuario preferenciasUsuario) {
        this.preferenciasUsuario = preferenciasUsuario;
    }

    public List<Mensagem> getMensagens() {
        return mensagens;
    }

    public void setMensagens(List<Mensagem> mensagens) {
        this.mensagens = mensagens;
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
        if (!(object instanceof Usuario)) {
            return false;
        }
        Usuario other = (Usuario) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.edu.utfpr.pojos.Usuario[ id=" + id + " ]";
    }
}
