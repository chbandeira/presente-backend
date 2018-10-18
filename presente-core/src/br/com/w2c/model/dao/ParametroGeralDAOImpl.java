package br.com.w2c.model.dao;

import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import br.com.w2c.exception.AplicacaoException;
import br.com.w2c.model.domain.ParametroGeral;

/**
 * 
 * @author charlles
 * @since 21/09/2013
 */
@Repository
public class ParametroGeralDAOImpl extends BaseDAOImpl<ParametroGeral> implements ParametroGeralDAO {

	@Override
	public ParametroGeral obterPorChave(String chave) throws AplicacaoException {
		Map<String, Object> parametros = criarParametros();
		parametros.put("chave", chave);
		return (ParametroGeral) obterResultadoUnico("br.com.w2c.model.dao.ParametroGeralDAOImpl.obterPorChave", parametros);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ParametroGeral> obterPorChaves(List<String> chaves) throws AplicacaoException {
		Map<String, Object> parametros = criarParametros();
		parametros.put("chaves", chaves);
		return obterResultado("br.com.w2c.model.dao.ParametroGeralDAOImpl.obterPorChaves", parametros);
	}

	@Override
	public ParametroGeral obterPorChaveIdentificador(String chave, Long identificador) throws AplicacaoException {
		Map<String, Object> parametros = criarParametros();
		parametros.put("chave", chave);
		parametros.put("identificador", identificador);
		return (ParametroGeral) obterResultadoUnico("br.com.w2c.model.dao.ParametroGeralDAOImpl.obterPorChaveIdentificador", parametros);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ParametroGeral> obterPorCriterioOnline(ParametroGeral entidade) throws AplicacaoException {
		Criteria criteria = createCriteria(ParametroGeral.class);
		criteria.add(Restrictions.eq("identificador", entidade.getIdentificador()));
		criteria.add(Restrictions.eq("chave", entidade.getChave()));
		return criteria.list();
	}
	
}
