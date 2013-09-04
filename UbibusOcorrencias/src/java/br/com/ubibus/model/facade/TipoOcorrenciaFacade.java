package br.com.ubibus.model.facade;

import br.com.ubibus.model.pojo.TipoOcorrencia;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author <a href="mailto:marvas1987@gmail.com">Marcelo F. Vasconcelos</a>
 */
@Stateless
public class TipoOcorrenciaFacade extends AbstractFacade<TipoOcorrencia> {

    @PersistenceContext(unitName = "ubibusPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TipoOcorrenciaFacade() {
        super(TipoOcorrencia.class);
    }
}
