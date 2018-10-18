package br.com.w2c.model.dao;

import static br.com.chbandeira.utilitario.Util.isNuloOuVazio;

import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import br.com.w2c.exception.AplicacaoException;
import br.com.w2c.model.domain.Sala;

/**
 * 
 * @author charlles
 * @since 14/09/2013
 */
@Repository
public class SalaDAOImpl extends BaseDAOImpl<Sala> implements SalaDAO {

	@SuppressWarnings("unchecked")
	@Override
	public List<Sala> obterListaPorParametros(Sala sala) throws AplicacaoException {
		Map<String, Object> parametros = criarParametros();
		parametros.put("descricao", like(sala.getDescricao()));
		return obterResultado("br.com.w2c.model.dao.SalaDAOImpl.obterListaPorParametros", parametros);
	}

	@Override
	public Sala obterPorParametrosUnicos(Sala sala) throws AplicacaoException {
		Map<String, Object> parametros = criarParametros();
		parametros.put("descricao", sala.getDescricao());
		return (Sala) obterResultadoUnico("br.com.w2c.model.dao.SalaDAOImpl.obterPorParametrosUnicos", parametros);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Sala> obterListaPorSugestaoDescricao(String descricao) throws AplicacaoException {
		Map<String, Object> parametros = criarParametros();
		parametros.put("descricao", like(descricao));
		return obterResultado("br.com.w2c.model.dao.SalaDAOImpl.obterListaPorSugestaoDescricao", parametros);
	}

	@Override
	public Sala obterRepetido(Sala sala) throws AplicacaoException {
		Map<String, Object> parametros = criarParametros();
		parametros.put("descricao", sala.getDescricao());
		parametros.put("id", isNuloOuVazio(sala.getId()) ? 0L : sala.getId());
		return (Sala) obterResultadoUnico("br.com.w2c.model.dao.SalaDAOImpl.obterRepetido", parametros);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Sala> obterPorCriterioOnline(Sala entidade) throws AplicacaoException {
		Criteria criteria = createCriteria(Sala.class);
		criteria.add(Restrictions.eq("identificador", entidade.getIdentificador()));
		criteria.add(Restrictions.eq("descricao", entidade.getDescricao()));
		return criteria.list();
	}
	
}
