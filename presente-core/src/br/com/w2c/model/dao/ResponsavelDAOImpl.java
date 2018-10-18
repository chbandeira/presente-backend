package br.com.w2c.model.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import br.com.w2c.exception.AplicacaoException;
import br.com.w2c.model.domain.Responsavel;

/**
 * 
 * @author charlles
 * @since 18/10/2013
 */
@Repository
public class ResponsavelDAOImpl extends BaseDAOImpl<Responsavel> implements ResponsavelDAO {

	@SuppressWarnings("unchecked")
	@Override
	public List<Responsavel> obterPorCriterioOnline(Responsavel entidade) throws AplicacaoException {
		Criteria criteria = createCriteria(Responsavel.class);
		criteria.add(Restrictions.eq("identificador", entidade.getIdentificador()));
		criteria.add(Restrictions.eq("nome", entidade.getNome()));
		return criteria.list();
	}
	
}
