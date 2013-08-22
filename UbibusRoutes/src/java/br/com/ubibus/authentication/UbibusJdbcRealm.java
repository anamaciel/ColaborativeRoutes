package br.com.ubibus.authentication;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import org.apache.shiro.realm.jdbc.JdbcRealm;
import org.apache.shiro.util.JdbcUtils;

/**
 * Classe responsável pelo acesso a base de dados e obtenção das permissões de usuário.
 * Aqui estão definidas as princiapis querys SQL para obtenção de usuario e suas
 * permissões.
 *
 * @author <a href="mailto:marvas1987@gmail.com">Marcelo F. Vasconcelos</a>
 */
public class UbibusJdbcRealm extends JdbcRealm {

    static final Logger log = Logger.getLogger(UbibusJdbcRealm.class.getName());
    /**
     * The default query used to retrieve account data for the user.
     */
    private static final String AUTHENTICATION_QUERY = "SELECT senha FROM usuarios WHERE login = ?";
    /**
     *eg
     */
    private static final String USER_NAME_QUERY = "SELECT nome FROM usuarios WHERE login = ?";
    /**
     * The default query used to retrieve the roles that apply to a user.
     */
    private static final String USER_ROLES_QUERY = "SELECT tipo_usuario FROM usuarios WHERE login = ?";

    /**
     * Este construtor cria um novo objeto UbibusJdbcRealm configurando a
     * base de dados padrão do sistema.
     */
    public UbibusJdbcRealm() {

        super();
        this.authenticationQuery = AUTHENTICATION_QUERY;
        this.userRolesQuery = USER_ROLES_QUERY;

        InitialContext ic;
        DataSource ds;
        try {
            ic = new InitialContext();
            ds = (DataSource) ic.lookup("jdbc/ubibus");            
            this.setDataSource(ds);
        } catch (NamingException e) {
            log.severe(e.getMessage());
        }

    }

    /**
     * Busca o nome de um usuário a partir do username/login recebido como
     * parametro
     *
     * @param username username/login do usuario do qual se deseja obter o nome
     * @return uma
     * <code>String</code> com o nome do usuário.
     */
    public String getNameForUser(String username) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        String name = null;
        try {
            Connection conn = dataSource.getConnection();
            ps = conn.prepareStatement(USER_NAME_QUERY);
            ps.setString(1, username);

            // Execute query
            rs = ps.executeQuery();

            // Loop over results and add each returned role to a set
            if (rs.next()) {
                name = rs.getString(1);
            }
        } catch (SQLException e) {
            final String message = "There was a SQL error while authorizing user [" + username + "]";
            log.severe(message);
        } finally {
            JdbcUtils.closeResultSet(rs);
            JdbcUtils.closeStatement(ps);
        }
        return name;
    }
}