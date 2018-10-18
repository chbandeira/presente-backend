package br.com.w2c.model.dao;

import br.com.w2c.exception.AplicacaoException;
import br.com.w2c.model.domain.ConfiguracaoEscola;

/**
 * 
 * @author charlles
 * @since 29/10/2013
 */
public interface ConfiguracaoEscolaDAO extends BaseDAO<ConfiguracaoEscola> {

	String obterNomeEscola() throws AplicacaoException;

	ConfiguracaoEscola obterConfiguracoesEscola() throws AplicacaoException;

}
