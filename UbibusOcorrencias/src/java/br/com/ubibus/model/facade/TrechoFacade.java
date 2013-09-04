package br.com.ubibus.model.facade;

import br.com.ubibus.model.pojo.Linha;
import br.com.ubibus.model.pojo.Trecho;
import java.util.List;
import javax.annotation.security.PermitAll;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.postgis.PGgeometry;

/**
 *
 * @author <a href="mailto:marvas1987@gmail.com">Marcelo F. Vasconcelos</a>
 */
@Stateless
public class TrechoFacade extends AbstractFacade<Trecho> {

    @PersistenceContext(unitName = "ubibusPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TrechoFacade() {
        super(Trecho.class);
    }

    @PermitAll
    public List<Trecho> findByLocation(PGgeometry location) {
        return findByLocation(location, 0.005);
    }

    @PermitAll
    public List<Trecho> findByLocation(PGgeometry location, Double precision) {
        String jpql = "SELECT DISTINCT t FROM Trecho t WHERE"
                + " FUNC('ST_DWITHIN', t.pontosRota, :location, :precision) = true;";

        Query q = em.createQuery(jpql)
                .setParameter("location", location)
                .setParameter("precision", precision);

        return q.getResultList();
    }

    @PermitAll
    public List<Trecho> findByLinha(Linha linha) {
        String jpql = "SELECT DISTINCT t FROM Trecho t,"
                + " IN(t.linhasTrechoList) lt"
                + " WHERE lt.linha = :linha";

        Query q = em.createQuery(jpql).setParameter("linha", linha);

        return q.getResultList();
    }
}
