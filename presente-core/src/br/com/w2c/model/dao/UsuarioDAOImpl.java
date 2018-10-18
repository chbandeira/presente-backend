package br.com.w2c.model.dao;

import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import br.com.w2c.exception.AplicacaoException;
import br.com.w2c.model.domain.Usuario;

/**
 * 
 * @author charlles
 * @since 12/10/2013
 */
@Repository
public class UsuarioDAOImpl extends BaseDAOImpl<Usuario> implements UsuarioDAO {

	@Override
	public Usuario obterPorLogin(String login) throws AplicacaoException {
		Map<String, Object> parametros = criarParametros();
		parametros.put("login", login);
		return (Usuario) obterResultadoUnico("Usuario.obterPorLogin", parametros);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Usuario> obterListaPorParametros(Usuario usuario) throws AplicacaoException {
		Map<String, Object> parametros = criarParametros();
		parametros.put("login", like(usuario.getLogin()));
		parametros.put("nome", like(usuario.getNome()));
		return obterResultado("Usuario.obterListaPorParametros", parametros);
	}

	@Override
	public boolean isSenhaAtualLogin(String senhaAtual, String login) throws AplicacaoException {
		Map<String, Object> parametros = criarParametros();
		parametros.put("senhaAtual", senhaAtual);
		parametros.put("login", login);
		Long resultado = (Long) obterResultadoUnico("Usuario.isSenhaAtualLogin", parametros);
		return resultado > 0;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Usuario> obterPorCriterioOnline(Usuario entidade) throws AplicacaoException {
		Criteria criteria = createCriteria(Usuario.class);
		criteria.add(Restrictions.eq("identificador", entidade.getIdentificador()));
		criteria.add(Restrictions.eq("login", entidade.getLogin()));
		return criteria.list();
	}
	
}
