/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ubibus.model.facade;

import br.com.ubibus.model.pojo.PontosInteresse;
import br.com.ubibus.model.pojo.Rota;
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
public class RotaFacade extends AbstractFacade<Rota>{
    @PersistenceContext(unitName = "ubibusPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public RotaFacade() {
        super(Rota.class);
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
    public List<Rota> findRotas() {
        String jpql = "SELECT o FROM Rota o ORDER BY o.nome ASC";
        Query query = em.createQuery(jpql);
//        Query query = em.createQuery(jpql);

        return query.getResultList();
    }
    
    @PermitAll
    public Usuario findDonoRota(Integer rota) {
        //SELECT u.*, r.* FROM usuarios u, rota r, usuario_rota ur WHERE ur.id_usuario = u.id_usuario AND r.id_rota = ur.id_rota AND ur.dono = true AND r.id_rota = 31
        String jpql = "SELECT u FROM Rota o, Usuario, Usuario_Rota ur WHERE ur.usuario.id = u.id AND r.id = ur.rota.id AND ur.dono = true AND r.rota.id = :rota ORDER BY o.nome ASC";
        Query query = em.createQuery(jpql).setParameter("rota", rota);

        return (Usuario) query.getSingleResult();
    }
    
    @PermitAll
    public List<PontosInteresse> findPontosInteressesRotas(Integer rota) {
        String jpql = "SELECT pi FROM Rota r, Rota_PI rp, PontosInteresse pi WHERE r.id = :rota AND r.id = rp.rota.id AND pi.id = rp.ponto_interesse.id";
        Query query = em.createQuery(jpql).setParameter("rota", rota);
//        Query query = em.createQuery(jpql);

        return query.getResultList();
    }
    
    @PermitAll
    public List<PontosInteresse> findPontosInteressesRotas() {
        String jpql = "SELECT pi FROM Rota r, Rota_PI rp, PontosInteresse pi WHERE r.id>0 AND r.id = rp.rota.id AND pi.id = rp.ponto_interesse.id";
//        Query query = em.createQuery(jpql).setParameter("rota", rota);
        Query query = em.createQuery(jpql);

        return query.getResultList();
    }
    
   
}
