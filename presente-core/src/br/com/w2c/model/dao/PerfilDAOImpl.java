package br.com.w2c.model.dao;

import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import br.com.w2c.exception.AplicacaoException;
import br.com.w2c.model.domain.Perfil;
import br.com.w2c.util.Constantes;

/**
 * 
 * @author charlles
 * @since 13/10/2013
 */
@Repository
public class PerfilDAOImpl extends BaseDAOImpl<Perfil> implements PerfilDAO {

	@Override
	public Perfil obterPorNome(String nomePerfil) throws AplicacaoException {
		Map<String, Object> parametros = criarParametros();
		parametros.put("nome", nomePerfil);
		return (Perfil) obterResultadoUnico("Perfil.obterPorNome", parametros);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Perfil> obterTodos() throws AplicacaoException {
		return obterResultado("Perfil.obterTodos");
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Perfil> obterTodosSemAdmin() throws AplicacaoException {
		Map<String, Object> parametros = criarParametros();
		parametros.put("perfil", Constantes.ADMIN.toLowerCase());
		return obterResultado("Perfil.obterTodosSemAdmin", parametros);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Perfil> obterPorCriterioOnline(Perfil entidade) throws AplicacaoException {
		Criteria criteria = createCriteria(Perfil.class);
		criteria.add(Restrictions.eq("identificador", entidade.getIdentificador()));
		criteria.add(Restrictions.eq("perfil", entidade.getPerfil()));
		return criteria.list();
	}
	
}
