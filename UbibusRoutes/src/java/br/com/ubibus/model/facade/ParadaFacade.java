/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ubibus.model.facade;

import br.com.ubibus.model.pojo.Linha;
import br.com.ubibus.model.pojo.LinhasParadas;
import br.com.ubibus.model.pojo.Parada;
import java.util.List;
import javax.annotation.security.PermitAll;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.postgis.PGgeometry;

/**
 *
 * @author <a href="mailto:anacm.maciel@gmail.com">Ana Claudia Maciel</a>
 */
@Stateless
public class ParadaFacade extends AbstractFacade<Parada> {
    
    @PersistenceContext(unitName = "ubibusPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ParadaFacade() {
        super(Parada.class);
    }

    @PermitAll
    public Parada findByLocation(PGgeometry location) {
        String jpql = "SELECT p FROM Parada p WHERE p.localizacao = :location";
        Query query = em.createQuery(jpql).setParameter("location", location);

        return !query.getResultList().isEmpty() ? (Parada) query.getResultList().get(0) : null;
    }
    
//    @PermitAll
//    public Parada find(PGgeometry location, Boolean status) {
//        String jpql = "SELECT o FROM Parada p WHERE p.localizacao = :location";
//        Query query = em.createQuery(jpql).setParameter("location", location).setParameter("status", status);
//
//        return !query.getResultList().isEmpty() ? (Parada) query.getResultList().get(0) : null;
//    }
    
    @PermitAll
    public List<Parada> findParadas() {
        String jpql = "SELECT p FROM Parada p";
        Query query = em.createQuery(jpql);

        return query.getResultList();
    }
    
    
    @PermitAll
    public LinhasParadas findLinhasParadasOrdem(Integer id_parada, Integer id_linha) {
        String jpql = "SELECT lt FROM LinhasParadas lt WHERE lt.parada.id = :id_parada AND lt.linha.id = :id_linha";
        Query query = em.createQuery(jpql).setParameter("id_parada", id_parada).setParameter("id_linha", id_linha);

        return (LinhasParadas)query.getSingleResult();
    }
    
    @PermitAll
    public List<Linha> findLinhasParadas(Integer id_parada) {
        String jpql = "SELECT DISTINCT l FROM Linha l, "
                + " IN(l.linhasParadasList) lt WHERE"
                + " lt.parada.id = :id_parada";
        Query query = em.createQuery(jpql).setParameter("id_parada", id_parada);

        return query.getResultList();
    }
    
    @PermitAll
    public List<Parada> findParadasProximas(PGgeometry location, Double precision) {
        
        String jpql = "SELECT DISTINCT p FROM Parada p "
                + "WHERE FUNC('ST_DWITHIN', p.localizacao, :location, :precision) = true";
        
        Query q = em.createQuery(jpql)
                .setParameter("location", location)
                .setParameter("precision", precision);

        return q.getResultList();
    }
    
    @PermitAll
    public Double findDistanciaParadaPI(PGgeometry locationP, PGgeometry locationPI) {
        
        String jpql = "SELECT FUNC('ST_Distance_Sphere', :locationP, :locationPI) FROM Parada p";
        
        Query q = em.createQuery(jpql)
                .setParameter("locationP", locationP)
                .setParameter("locationPI", locationPI);
        
        return (Double)q.getResultList().get(0);
    }
    
    @PermitAll
    public List<Parada> findParadasLinhas(Integer id_linha) {
        String jpql = "SELECT DISTINCT p FROM Parada p, "
                + " IN(p.linhasParadasList) lt WHERE"
                + " lt.linha.id = :id_linha ORDER BY lt.ordemParada";
        Query query = em.createQuery(jpql).setParameter("id_linha", id_linha);

        return query.getResultList();
    }
}
