package br.com.w2c.model.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import br.com.w2c.exception.AplicacaoException;
import br.com.w2c.model.domain.ConfiguracaoEscola;

/**
 * 
 * @author charlles
 * @since 29/10/2013
 */
@Repository
public class ConfiguracaoEscolaDAOImpl extends BaseDAOImpl<ConfiguracaoEscola> implements ConfiguracaoEscolaDAO {

	@Override
	public String obterNomeEscola() throws AplicacaoException {
		return (String) obterResultadoUnico("ConfiguracaoEscola.obterNomeEscola");
	}

	@Override
	public ConfiguracaoEscola obterConfiguracoesEscola() throws AplicacaoException {
		return (ConfiguracaoEscola) obterResultadoUnico("ConfiguracaoEscola.obterConfiguracoesEscola");
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ConfiguracaoEscola> obterPorCriterioOnline(ConfiguracaoEscola entidade) throws AplicacaoException {
		Criteria criteria = createCriteria(ConfiguracaoEscola.class);
		criteria.add(Restrictions.eq("identificador", entidade.getIdentificador()));
		return criteria.list();
	}
	
}
