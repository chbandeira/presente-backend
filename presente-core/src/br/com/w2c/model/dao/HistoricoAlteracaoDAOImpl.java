package br.com.w2c.model.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import br.com.w2c.exception.AplicacaoException;
import br.com.w2c.model.domain.HistoricoAlteracao;

/**
 * 
 * @author Charlles
 * @since 09/02/2014
 */
@Repository
public class HistoricoAlteracaoDAOImpl extends BaseDAOImpl<HistoricoAlteracao> implements HistoricoAlteracaoDAO {

	@SuppressWarnings("unchecked")
	@Override
	public List<HistoricoAlteracao> obterPorCriterioOnline(HistoricoAlteracao entidade) throws AplicacaoException {
		Criteria criteria = createCriteria(HistoricoAlteracao.class);
		criteria.add(Restrictions.eq("identificador", entidade.getIdentificador()));
		criteria.add(Restrictions.eq("id", entidade.getId()));
		return criteria.list();
	}

}
