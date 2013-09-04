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
public class LinhaFacade extends AbstractFacade<Linha> {

    @PersistenceContext(unitName = "ubibusPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public LinhaFacade() {
        super(Linha.class);
    }

    @PermitAll
    public List<Linha> findByLocation(PGgeometry location) {
        return findByLocation(location, 0.005);
    }

    @PermitAll
    public List<Linha> findByLocation(PGgeometry location, Double precision) {
//                String jpql = "SELECT DISTINCT l FROM Linha l,"
//                + " IN(l.linhasParadasList) lt WHERE"
//                + " FUNC('ST_DWITHIN', lt.parada.localizacao, :location, :precision) = true;";
        
        String jpql = "SELECT DISTINCT l FROM Linha l,"
                + " IN(l.linhasTrechoList) lt WHERE"
                + " FUNC('ST_DWITHIN', lt.trecho.pontosRota, :location, :precision) = true";
        
//                String jpql = "SELECT DISTINCT p FROM Parada p "
//                + " IN(p.linhasParadasList) lt WHERE"
//                + "where FUNC('ST_DWITHIN', p.localizacao, :location, :precision, true)";

        Query q = em.createQuery(jpql)
                .setParameter("location", location)
                .setParameter("precision", precision);

        return q.getResultList();
    }

    @PermitAll
    public List<Linha> findByTrecho(Trecho trecho) {
        String jpql = "SELECT DISTINCT l FROM Linha l,"
                + " IN(l.linhasTrechoList) lt"
                + " WHERE lt.trecho = :trecho";

        Query q = em.createQuery(jpql).setParameter("trecho", trecho);

        return q.getResultList();
    }

    @PermitAll
    public List<Linha> findByNumber(String lineNumber) {
        String jpql = "SELECT l FROM Linha l WHERE l.numero = :numero";
        Query q = em.createQuery(jpql).setParameter("numero", lineNumber);
        return q.getResultList();
    }
}
