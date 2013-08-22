package br.com.ubibus.model.facade;

import br.com.ubibus.model.pojo.Rota_PI;
import br.com.ubibus.model.pojo.TipoFonte;
import java.util.List;
import javax.annotation.security.PermitAll;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author <a href="mailto:marvas1987@gmail.com">Marcelo F. Vasconcelos</a>
 */
@Stateless
public class RotaPoiFacade extends AbstractFacade<Rota_PI> {

    @PersistenceContext(unitName = "ubibusPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public RotaPoiFacade() {
        super(Rota_PI.class);
    }

    @PermitAll
    public TipoFonte findByDescricao(String descricao) {
        String jpql = "SELECT DISTINCT tf FROM TipoFonte tf WHERE tf.descricao = :descricao";
        Query q = em.createQuery(jpql).setParameter("descricao", descricao);
        List list = q.getResultList();
        return !list.isEmpty() ? (TipoFonte) list.get(0) : null;
    }
}
