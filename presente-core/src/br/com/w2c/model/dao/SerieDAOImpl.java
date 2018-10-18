package br.com.w2c.model.dao;

import static br.com.chbandeira.utilitario.Util.isNuloOuVazio;

import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import br.com.w2c.exception.AplicacaoException;
import br.com.w2c.model.domain.Serie;

/**
 * 
 * @author charlles
 * @since 14/09/2013
 */
@Repository
public class SerieDAOImpl extends BaseDAOImpl<Serie> implements SerieDAO {

	@SuppressWarnings("unchecked")
	@Override
	public List<Serie> obterListaPorParametros(Serie serie) throws AplicacaoException {
		Map<String, Object> parametros = criarParametros();
		parametros.put("descricao", like(serie.getDescricao()));
		return obterResultado("Serie.obterListaPorParametros", parametros);
	}
	
	@Override
	public Serie obterPorParametrosUnicos(Serie serie) throws AplicacaoException {
		Map<String, Object> parametros = criarParametros();
		parametros.put("descricao", serie.getDescricao());
		return (Serie) obterResultadoUnico("Serie.obterPorParametrosUnicos", parametros);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Serie> obterListaPorSugestaoDescricao(String descricao) throws AplicacaoException {
		Map<String, Object> parametros = criarParametros();
		parametros.put("descricao", like(descricao));
		return obterResultado("Serie.obterListaPorSugestaoDescricao", parametros);
	}

	@Override
	public Serie obterRepetido(Serie serie) throws AplicacaoException {
		Map<String, Object> parametros = criarParametros();
		parametros.put("descricao", serie.getDescricao());
		parametros.put("id", isNuloOuVazio(serie.getId()) ? 0L : serie.getId());
		return (Serie) obterResultadoUnico("Serie.obterRepetido", parametros);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Serie> obterPorCriterioOnline(Serie entidade) throws AplicacaoException {
		Criteria criteria = createCriteria(Serie.class);
		criteria.add(Restrictions.eq("identificador", entidade.getIdentificador()));
		criteria.add(Restrictions.eq("descricao", entidade.getDescricao()));
		return criteria.list();
	}
	
}
