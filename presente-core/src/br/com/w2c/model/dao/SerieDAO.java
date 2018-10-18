package br.com.w2c.model.dao;

import java.util.List;

import br.com.w2c.exception.AplicacaoException;
import br.com.w2c.model.domain.Serie;

/**
 * 
 * @author charlles
 * @since 14/09/2013
 */
public interface SerieDAO extends BaseDAO<Serie> {

	Serie obterPorParametrosUnicos(Serie serie) throws AplicacaoException;

	List<Serie> obterListaPorParametros(Serie serie) throws AplicacaoException;

	List<Serie> obterListaPorSugestaoDescricao(String descricao) throws AplicacaoException;

	Serie obterRepetido(Serie serie) throws AplicacaoException;
	
}
