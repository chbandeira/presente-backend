package br.com.w2c.model.dao;

import static br.com.chbandeira.utilitario.Util.isNuloOuVazio;

import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import br.com.chbandeira.utilitario.UtilDate;
import br.com.w2c.exception.AplicacaoException;
import br.com.w2c.model.domain.Turma;
import br.com.w2c.model.domain.Usuario;

/**
 * 
 * @author charlles
 * @since 14/09/2013
 */
@Repository
public class TurmaDAOImpl extends BaseDAOImpl<Turma> implements TurmaDAO {

	@SuppressWarnings("unchecked")
	@Override
	public List<Turma> obterListaPorParametros(Turma turma) throws AplicacaoException {
		Map<String, Object> parametros = criarParametros();
		parametros.put("descricao", like(turma.getDescricao()));
		return obterResultado("br.com.w2c.model.dao.TurmaDAOImpl.obterListaPorParametros", parametros);
	}
	
	@Override
	public Turma obterPorParametrosUnicos(Turma turma) throws AplicacaoException {
		Map<String, Object> parametros = criarParametros();
		parametros.put("descricao", turma.getDescricao());
		return (Turma) obterResultadoUnico("br.com.w2c.model.dao.TurmaDAOImpl.obterPorParametrosUnicos", parametros);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Turma> obterListaPorSugestaoDescricao(String descricao) throws AplicacaoException {
		Map<String, Object> parametros = criarParametros();
		parametros.put("descricao", like(descricao));
		return obterResultado("br.com.w2c.model.dao.TurmaDAOImpl.obterListaPorSugestaoDescricao", parametros);
	}

	@Override
	public Turma obterRepetido(Turma turma) throws AplicacaoException {
		Map<String, Object> parametros = criarParametros();
		parametros.put("descricao", turma.getDescricao());
		parametros.put("id", isNuloOuVazio(turma.getId()) ? 0L : turma.getId());
		return (Turma) obterResultadoUnico("br.com.w2c.model.dao.TurmaDAOImpl.obterRepetido", parametros);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Turma> obterListaPorSugestaoDescricaoMatriculados(String descricao) throws AplicacaoException {
		Map<String, Object> parametros = criarParametros();
		parametros.put("descricao", like(descricao));
		parametros.put("anoLetivo", UtilDate.getAnoAtual());
		return obterResultado("br.com.w2c.model.dao.TurmaDAOImpl.obterListaPorSugestaoDescricaoMatriculados", parametros);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Turma> obterPorCriterioOnline(Turma entidade) throws AplicacaoException {
		Criteria criteria = createCriteria(Turma.class);
		criteria.add(Restrictions.eq("identificador", entidade.getIdentificador()));
		criteria.add(Restrictions.eq("descricao", entidade.getDescricao()));
		return criteria.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Turma> obterListaPorSugestaoDescricaoMatriculadosEUsuario(String descricaoTurma, Usuario usuario) throws AplicacaoException {
		Map<String, Object> parametros = criarParametros();
		parametros.put("descricao", like(descricaoTurma));
		parametros.put("identificador", usuario.getIdentificador());
		return obterResultado("br.com.w2c.model.dao.TurmaDAOImpl.obterListaPorSugestaoDescricaoMatriculadosEUsuario", parametros);
	}
	
}
