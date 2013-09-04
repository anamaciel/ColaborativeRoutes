package br.com.ubibus.model.facade;

import br.com.ubibus.model.pojo.Mensagem;
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
public class MensagemFacade extends AbstractFacade<Mensagem> {

    @PersistenceContext(unitName = "ubibusPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MensagemFacade() {
        super(Mensagem.class);
    }

    @PermitAll
    public List<Mensagem> find(String searchKey) {
        String jpql = "SELECT m FROM Mensagem m WHERE m.mensagem LIKE :searchKey";
        Query q = getEntityManager().createQuery(jpql).setParameter("searchKey", searchKey);
        return q.getResultList();
    }
}
