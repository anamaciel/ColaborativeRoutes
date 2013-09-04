package br.com.ubibus.model.facade;

import br.com.ubibus.model.pojo.Usuario;
import java.util.List;
import javax.annotation.security.PermitAll;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author <a href="mailto:marvas1987@gmail.com">Marcelo F. Vasconcelos</a>
 */
@Stateless
public class UsuarioFacade extends AbstractFacade<Usuario> {

    @PersistenceContext(unitName = "ubibusPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsuarioFacade() {
        super(Usuario.class);
    }

    @PermitAll
    public Usuario findByLogin(String login) {
        String jpql = "SELECT u FROM Usuario u WHERE u.login = :login";

        Query q = getEntityManager().createQuery(jpql).setParameter("login", login);
        Usuario usuario;

        try {
            usuario = (Usuario) q.getSingleResult();
        } catch (NoResultException ex) {
            usuario = null;
        }

        return usuario;
    }

    @PermitAll
    public Usuario findByLoginCB(String login) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Usuario> query = builder.createQuery(Usuario.class);
        Root<Usuario> user = query.from(Usuario.class);
        query.where(builder.equal(user.get("login"), login));

        final List<Usuario> results = em.createQuery(query).getResultList();

        return results.isEmpty() ? null : results.get(0);
    }
}
