package br.com.w2c.model.dao;

import java.util.List;

import br.com.w2c.exception.AplicacaoException;
import br.com.w2c.model.domain.Disciplina;

/**
 * 
 * @author charlles
 * @since 05/05/2015
 */
public interface DisciplinaDAO extends BaseDAO<Disciplina> {

	List<Disciplina> obterListaPorParametros(Disciplina disciplina) throws AplicacaoException;

	Disciplina obterRepetido(Disciplina disciplina) throws AplicacaoException;

}
