package br.com.w2c.model.dao;

import java.util.List;

import br.com.w2c.exception.AplicacaoException;
import br.com.w2c.model.domain.Ocorrencia;
import br.com.w2c.model.domain.Usuario;

/**
 * 
 * @author charlles
 * @since 19/05/2015
 */
public interface OcorrenciaDAO extends BaseDAO<Ocorrencia> {

	List<Ocorrencia> obterListaPorParametros(Ocorrencia ocorrencia);

	List<Ocorrencia> obterPorUsuario(Usuario usuario) throws AplicacaoException;

	List<Ocorrencia> obterListaPorParametrosEUsuario(Ocorrencia ocorrencia, Usuario usuario);

}
