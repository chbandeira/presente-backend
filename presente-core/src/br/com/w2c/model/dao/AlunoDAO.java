package br.com.w2c.model.dao;

import java.util.List;

import br.com.w2c.exception.AplicacaoException;
import br.com.w2c.model.domain.Aluno;
import br.com.w2c.model.domain.Usuario;

/**
 * 
 * @author charlles
 * @since 14/09/2013
 */
public interface AlunoDAO extends BaseDAO<Aluno> {

	List<Aluno> obterPorNome(String nome) throws AplicacaoException;

	Aluno obterPorNomeExato(String nome) throws AplicacaoException;

	List<Aluno> obterPorNomeEUsuario(String nome, Usuario usuario) throws AplicacaoException;

}
