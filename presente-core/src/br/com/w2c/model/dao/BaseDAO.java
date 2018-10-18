package br.com.w2c.model.dao;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import br.com.w2c.exception.AplicacaoException;

/**
 * 
 * @author charlles
 * @since 14/09/2013
 * @param <T>
 */
public interface BaseDAO<T> {

	void salvar(T entidade);
	
	void excluir(T entidade);
	
	T obter(Class<T> clazz, Serializable id);
	
	void flush();
	
	List<T> obterUltimosAtualizados(Date dataHora, Class<T> clazz) throws AplicacaoException;
	
	List<T> obterPorCriterioOnline(T entidade) throws AplicacaoException;
}
