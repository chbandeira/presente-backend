package br.com.w2c.model.dao;

import java.util.List;

import br.com.w2c.exception.AplicacaoException;
import br.com.w2c.model.domain.Usuario;

/**
 * 
 * @author charlles
 * @since 12/10/2013
 */
public interface UsuarioDAO extends BaseDAO<Usuario> {

	Usuario obterPorLogin(String login) throws AplicacaoException;

	List<Usuario> obterListaPorParametros(Usuario usuario) throws AplicacaoException;

	boolean isSenhaAtualLogin(String senhaAtual, String login) throws AplicacaoException;
	
}