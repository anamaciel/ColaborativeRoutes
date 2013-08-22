/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ubibus.model.facade;

import br.com.ubibus.model.pojo.PontosInteresse;
import br.com.ubibus.model.pojo.Usuario;
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
public class PontosInteressesFacade extends AbstractFacade<PontosInteresse>{
    @PersistenceContext(unitName = "ubibusPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PontosInteressesFacade() {
        super(PontosInteresse.class);
    }

    @PermitAll
    public PontosInteresse findByLocation(PGgeometry location) {
        String jpql = "SELECT o FROM PontosInteresse o WHERE o.localizacao = :location";
        Query query = em.createQuery(jpql).setParameter("location", location);

        return !query.getResultList().isEmpty() ? (PontosInteresse) query.getResultList().get(0) : null;
    }
    
    @PermitAll
    public PontosInteresse find(PGgeometry location, Boolean status) {
        String jpql = "SELECT o FROM PontosInteresse o WHERE o.localizacao = :location";
        Query query = em.createQuery(jpql).setParameter("location", location);

        return !query.getResultList().isEmpty() ? (PontosInteresse) query.getResultList().get(0) : null;
    }
    
    @PermitAll
    public List<PontosInteresse> find(Boolean status) {
        String jpql = "SELECT o FROM PontosInteresse o";
        Query query = em.createQuery(jpql);

        return query.getResultList();
    }
    
    @PermitAll
    public List<PontosInteresse> find(Usuario usuario) {
        String jpql = "SELECT o FROM PontosInteresse o, Usuario_PI u WHERE u.usuario = :usuario AND o = u.ponto_interesse";
        Query query = em.createQuery(jpql).setParameter("usuario", usuario);
//        Query query = em.createQuery(jpql);

        return query.getResultList();
    }
    
    @PermitAll
    public PontosInteresse findPonto(String nome) {
        String jpql = "SELECT o FROM PontosInteresse o WHERE o.nome = :nome";
        Query query = em.createQuery(jpql).setParameter("nome", nome);
//        Query query = em.createQuery(jpql);

        return (PontosInteresse) query.getSingleResult();
    }
    
   
}
