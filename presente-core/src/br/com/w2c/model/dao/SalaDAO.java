package br.com.w2c.model.dao;

import java.util.List;

import br.com.w2c.exception.AplicacaoException;
import br.com.w2c.model.domain.Sala;

/**
 * 
 * @author charlles
 * @since 14/09/2013
 */
public interface SalaDAO extends BaseDAO<Sala> {

	List<Sala> obterListaPorParametros(Sala sala) throws AplicacaoException;
	
	Sala obterPorParametrosUnicos(Sala sala) throws AplicacaoException;

	List<Sala> obterListaPorSugestaoDescricao(String descricao) throws AplicacaoException;

	Sala obterRepetido(Sala sala) throws AplicacaoException;
	
}
