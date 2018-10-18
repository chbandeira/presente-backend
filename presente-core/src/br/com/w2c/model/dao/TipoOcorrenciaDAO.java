package br.com.w2c.model.dao;

import java.util.List;

import br.com.w2c.exception.AplicacaoException;
import br.com.w2c.model.domain.TipoOcorrencia;
import br.com.w2c.model.domain.Usuario;

/**
 * 
 * @author charlles
 * @since 19/05/2015
 */
public interface TipoOcorrenciaDAO extends BaseDAO<TipoOcorrencia> {

	List<TipoOcorrencia> obterListaPorParametros(TipoOcorrencia tipoOcorrencia) throws AplicacaoException;

	TipoOcorrencia obterPorDescricao(TipoOcorrencia tipoOcorrencia) throws AplicacaoException;

	List<TipoOcorrencia> obterListaPorSugestaoDescricao(String descricao) throws AplicacaoException;

	List<TipoOcorrencia> obterListaPorSugestaoDescricaoEUsuario(String descricao, Usuario usuario) throws AplicacaoException;

}
