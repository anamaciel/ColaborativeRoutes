/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ubibus.model.facade;

import br.com.ubibus.model.pojo.Usuario_PI;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author <a href="mailto:anacm.maciel@gmail.com">Ana Claudia Maciel</a>
 */
@Stateless
public class Usuario_PIFacade extends AbstractFacade<Usuario_PI>{
    
    @PersistenceContext(unitName = "ubibusPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public Usuario_PIFacade() {
        super(Usuario_PI.class);
    }

//    @PermitAll
//    public Endereco find(Integer cep, String nome) {
//        String jpql = "SELECT e FROM Endereco e WHERE e.cep = :cep AND e.nome = :nome";
//        Query q = em.createQuery(jpql)
//                .setParameter("cep", cep)
//                .setParameter("nome", nome);
//        
//        List<Endereco> list = q.getResultList();
//        return !list.isEmpty() ? list.get(0) : null;
//    }
    
}
