package br.com.ubibus.model.facade;

import br.com.ubibus.model.pojo.Ocorrencia;
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
public class OcorrenciaFacade extends AbstractFacade<Ocorrencia> {

    @PersistenceContext(unitName = "ubibusPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public OcorrenciaFacade() {
        super(Ocorrencia.class);
    }

    @PermitAll
    public Ocorrencia findByLocation(PGgeometry location) {
        String jpql = "SELECT o FROM Ocorrencia o WHERE o.localizacao = :location";
        Query query = em.createQuery(jpql).setParameter("location", location);

        return !query.getResultList().isEmpty() ? (Ocorrencia) query.getResultList().get(0) : null;
    }
    
    @PermitAll
    public Ocorrencia find(PGgeometry location, Boolean status) {
        String jpql = "SELECT o FROM Ocorrencia o WHERE o.localizacao = :location AND o.statusResolvido = :status";
        Query query = em.createQuery(jpql).setParameter("location", location).setParameter("status", status);

        return !query.getResultList().isEmpty() ? (Ocorrencia) query.getResultList().get(0) : null;
    }
    
    @PermitAll
    public List<Ocorrencia> find(Boolean status) {
        String jpql = "SELECT o FROM Ocorrencia o WHERE o.statusResolvido = :status";
        Query query = em.createQuery(jpql).setParameter("status", status);

        return query.getResultList();
    }
}
