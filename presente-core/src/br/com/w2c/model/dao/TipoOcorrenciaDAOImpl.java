package br.com.w2c.model.dao;

import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import br.com.w2c.exception.AplicacaoException;
import br.com.w2c.model.domain.TipoOcorrencia;
import br.com.w2c.model.domain.Usuario;

/**
 * 
 * @author charlles
 * @since 14/09/2013
 */
@Repository
public class TipoOcorrenciaDAOImpl extends BaseDAOImpl<TipoOcorrencia> implements TipoOcorrenciaDAO {

	@SuppressWarnings("unchecked")
	@Override
	public List<TipoOcorrencia> obterListaPorParametros(TipoOcorrencia tipoOcorrencia) throws AplicacaoException {
		Map<String, Object> parametros = criarParametros();
		parametros.put("descricao", like(tipoOcorrencia.getDescricao()));
		return obterResultado("br.com.w2c.model.dao.TipoOcorrenciaDAOImpl.obterListaPorParametros", parametros);
	}

	@Override
	public TipoOcorrencia obterPorDescricao(TipoOcorrencia tipoOcorrencia) throws AplicacaoException {
		Map<String, Object> parametros = criarParametros();
		parametros.put("descricao", tipoOcorrencia.getDescricao());
		return (TipoOcorrencia) obterResultadoUnico("br.com.w2c.model.dao.TipoOcorrenciaDAOImpl.obterPorDescricao", parametros);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TipoOcorrencia> obterListaPorSugestaoDescricao(String descricao) throws AplicacaoException {
		Map<String, Object> parametros = criarParametros();
		parametros.put("descricao", like(descricao));
		return obterResultado("br.com.w2c.model.dao.TipoOcorrenciaDAOImpl.obterListaPorSugestaoDescricao", parametros);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TipoOcorrencia> obterPorCriterioOnline(TipoOcorrencia entidade) throws AplicacaoException {
		Criteria criteria = createCriteria(TipoOcorrencia.class);
		criteria.add(Restrictions.eq("identificador", entidade.getIdentificador()));
		criteria.add(Restrictions.eq("descricao", entidade.getDescricao()));
		return criteria.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TipoOcorrencia> obterListaPorSugestaoDescricaoEUsuario(String descricao, Usuario usuario) throws AplicacaoException {
		Map<String, Object> parametros = criarParametros();
		parametros.put("descricao", like(descricao));
		parametros.put("identificador", usuario.getIdentificador());
		return obterResultado("br.com.w2c.model.dao.TipoOcorrenciaDAOImpl.obterListaPorSugestaoDescricaoEUsuario", parametros);
	}

}
