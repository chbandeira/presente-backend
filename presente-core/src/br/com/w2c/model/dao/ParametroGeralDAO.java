package br.com.w2c.model.dao;

import java.util.List;

import br.com.w2c.exception.AplicacaoException;
import br.com.w2c.model.domain.ParametroGeral;

/**
 * 
 * @author charlles
 * @since 21/09/2013
 */
public interface ParametroGeralDAO extends BaseDAO<ParametroGeral> {

	ParametroGeral obterPorChave(String chave) throws AplicacaoException;

	List<ParametroGeral> obterPorChaves(List<String> chaves) throws AplicacaoException;

	ParametroGeral obterPorChaveIdentificador(String chave, Long identificador) throws AplicacaoException;

}
