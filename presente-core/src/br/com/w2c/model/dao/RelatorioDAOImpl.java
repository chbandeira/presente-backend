package br.com.w2c.model.dao;

import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import br.com.w2c.exception.AplicacaoException;
import br.com.w2c.model.domain.Relatorio;

/**
 * 
 * @author charlles
 * @since 04/12/2013
 */
@Repository
public class RelatorioDAOImpl extends BaseDAOImpl<Relatorio> implements RelatorioDAO {

	@Override
	public String obterNome(Long idRelatorio) throws AplicacaoException {
		Map<String, Object> parametros = criarParametros();
		parametros.put("idRelatorio", idRelatorio);
		return (String) obterResultadoUnico("br.com.w2c.model.dao.RelatorioDAOImpl.obterNome", parametros);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Relatorio> obterPorCriterioOnline(Relatorio entidade) throws AplicacaoException {
		Criteria criteria = createCriteria(Relatorio.class);
		criteria.add(Restrictions.eq("identificador", entidade.getIdentificador()));
		criteria.add(Restrictions.eq("nome", entidade.getNome()));
		return criteria.list();
	}
	
}
