package br.com.w2c.model.dao;

import java.util.List;

import br.com.w2c.exception.AplicacaoException;
import br.com.w2c.model.domain.Perfil;

/**
 * 
 * @author charlles
 * @since 13/10/2013
 */
public interface PerfilDAO extends BaseDAO<Perfil> {

	Perfil obterPorNome(String nomePerfil) throws AplicacaoException;

	List<Perfil> obterTodos() throws AplicacaoException;

	List<Perfil> obterTodosSemAdmin() throws AplicacaoException;

}
