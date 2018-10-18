package br.com.w2c.model.dao;

import br.com.w2c.exception.AplicacaoException;
import br.com.w2c.model.domain.Relatorio;

/**
 * 
 * @author charlles
 * @since 04/12/2013
 */
public interface RelatorioDAO extends BaseDAO<Relatorio> {

	String obterNome(Long idRelatorio) throws AplicacaoException;
	
}
