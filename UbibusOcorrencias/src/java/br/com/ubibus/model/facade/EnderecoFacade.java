package br.com.ubibus.model.facade;

import br.com.ubibus.model.pojo.Endereco;
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
public class EnderecoFacade extends AbstractFacade<Endereco> {

    @PersistenceContext(unitName = "ubibusPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EnderecoFacade() {
        super(Endereco.class);
    }

    @PermitAll
    public Endereco find(Integer cep, String nome) {
        String jpql = "SELECT e FROM Endereco e WHERE e.cep = :cep AND e.nome = :nome";
        Query q = em.createQuery(jpql)
                .setParameter("cep", cep)
                .setParameter("nome", nome);
        
        List<Endereco> list = q.getResultList();
        return !list.isEmpty() ? list.get(0) : null;
    }
}
