package br.com.w2c.model.dao;

import java.util.List;

import br.com.w2c.exception.AplicacaoException;
import br.com.w2c.model.domain.OcorrenciaImportacao;

/**
 * 
 * @author charlles
 * @since 03/10/2013
 */
public interface OcorrenciaImportacaoDAO extends BaseDAO<OcorrenciaImportacao> {

	List<OcorrenciaImportacao> obterTodos() throws AplicacaoException;

}
