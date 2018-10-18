package br.com.w2c.model.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import br.com.w2c.exception.AplicacaoException;
import br.com.w2c.model.domain.OcorrenciaImportacao;

/**
 * 
 * @author charlles
 * @since 03/10/2013
 */
@Repository
public class OcorrenciaImportacaoDAOImpl extends BaseDAOImpl<OcorrenciaImportacao> implements OcorrenciaImportacaoDAO {

	@SuppressWarnings("unchecked")
	@Override
	public List<OcorrenciaImportacao> obterTodos() throws AplicacaoException {
		return obterResultado("br.com.w2c.model.dao.OcorrenciaImportacaoDAOImpl.obterTodos");
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<OcorrenciaImportacao> obterPorCriterioOnline(OcorrenciaImportacao entidade) throws AplicacaoException {
		Criteria criteria = createCriteria(OcorrenciaImportacao.class);
		criteria.add(Restrictions.eq("identificador", entidade.getIdentificador()));
		criteria.add(Restrictions.eq("id", entidade.getId()));
		return criteria.list();
	}
	
}
