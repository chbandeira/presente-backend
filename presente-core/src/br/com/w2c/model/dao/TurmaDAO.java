package br.com.w2c.model.dao;

import java.util.List;

import br.com.w2c.exception.AplicacaoException;
import br.com.w2c.model.domain.Turma;
import br.com.w2c.model.domain.Usuario;

/**
 * 
 * @author charlles
 * @since 14/09/2013
 */
public interface TurmaDAO extends BaseDAO<Turma> {

	List<Turma> obterListaPorParametros(Turma turma) throws AplicacaoException;
	
	Turma obterPorParametrosUnicos(Turma turma) throws AplicacaoException;

	List<Turma> obterListaPorSugestaoDescricao(String descricao) throws AplicacaoException;

	Turma obterRepetido(Turma turma) throws AplicacaoException;

	List<Turma> obterListaPorSugestaoDescricaoMatriculados(String descricaoTurma) throws AplicacaoException;

	List<Turma> obterListaPorSugestaoDescricaoMatriculadosEUsuario(String descricaoTurma, Usuario usuario) throws AplicacaoException;
	
}
