package br.com.w2c.model.dao;

import static br.com.chbandeira.utilitario.Util.isNuloOuVazio;

import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import br.com.w2c.exception.AplicacaoException;
import br.com.w2c.model.domain.Disciplina;

/**
 * 
 * @author charlles
 * @since 14/09/2013
 */
@Repository
public class DisciplinaDAOImpl extends BaseDAOImpl<Disciplina> implements DisciplinaDAO {

	@SuppressWarnings("unchecked")
	@Override
	public List<Disciplina> obterListaPorParametros(Disciplina disciplina) throws AplicacaoException {
		Map<String, Object> parametros = criarParametros();
		parametros.put("nome", like(disciplina.getNome()));
		return obterResultado("br.com.w2c.model.dao.DisciplinaDAOImpl.obterListaPorParametros", parametros);
	}

	@Override
	public Disciplina obterRepetido(Disciplina disciplina) throws AplicacaoException {
		Map<String, Object> parametros = criarParametros();
		parametros.put("id", isNuloOuVazio(disciplina.getId()) ? 0L : disciplina.getId());
		parametros.put("nome", disciplina.getNome());
		return (Disciplina) obterResultadoUnico("br.com.w2c.model.dao.DisciplinaDAOImpl.obterRepetido", parametros);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Disciplina> obterPorCriterioOnline(Disciplina entidade) throws AplicacaoException {
		Criteria criteria = createCriteria(Disciplina.class);
		criteria.add(Restrictions.eq("identificador", entidade.getIdentificador()));
		criteria.add(Restrictions.eq("nome", entidade.getNome()));
		return criteria.list();
	}

}
