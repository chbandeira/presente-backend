package br.com.w2c.model.dao;

import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import br.com.w2c.exception.AplicacaoException;
import br.com.w2c.model.domain.Aluno;
import br.com.w2c.model.domain.Usuario;

/**
 * 
 * @author charlles
 * @since 14/09/2013
 */
@Repository
public class AlunoDAOImpl extends BaseDAOImpl<Aluno> implements AlunoDAO {

	@SuppressWarnings("unchecked")
	@Override
	public List<Aluno> obterPorNome(String nome) throws AplicacaoException {
		Map<String, Object> parametros = criarParametros();
		parametros.put("nome", like(nome));
		return obterResultado("Aluno.obterPorNome", parametros);
	}

	@Override
	public Aluno obterPorNomeExato(String nome) throws AplicacaoException {
		Map<String, Object> parametros = criarParametros();
		parametros.put("nome", nome);
		return (Aluno) obterResultadoUnico("Aluno.obterPorNomeExato", parametros);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Aluno> obterPorCriterioOnline(Aluno aluno) throws AplicacaoException {
		Criteria criteria = createCriteria(Aluno.class);
		criteria.add(Restrictions.eq("identificador", aluno.getIdentificador()));
		criteria.add(Restrictions.eq("nome", aluno.getNome()));
		return criteria.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Aluno> obterPorNomeEUsuario(String nome, Usuario usuario) throws AplicacaoException {
		Map<String, Object> parametros = criarParametros();
		parametros.put("nome", like(nome));
		parametros.put("identificador", usuario.getIdentificador());
		return obterResultado("Aluno.obterPorNomeEUsuario", parametros);
	}

}
