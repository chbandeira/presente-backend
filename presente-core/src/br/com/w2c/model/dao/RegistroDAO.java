package br.com.w2c.model.dao;

import java.util.Date;
import java.util.List;

import br.com.w2c.exception.AplicacaoException;
import br.com.w2c.model.domain.Matricula;
import br.com.w2c.model.domain.Registro;
import br.com.w2c.model.domain.Usuario;

/**
 * 
 * @author charlles
 * @since 17/09/2013
 */
public interface RegistroDAO extends BaseDAO<Registro> {

	List<Registro> obterListaPorParametros(Registro registro) throws AplicacaoException;
	
	Registro obterListaPorParametrosUnicos(Registro registro) throws AplicacaoException;

	List<Registro> obterRepetido(Registro registro) throws AplicacaoException;

	List<Registro> obterPorMatriculaEPeriodo(Matricula matricula, Date periodoInicial, Date periodoFinal) throws AplicacaoException;

	List<Registro> obterPorPeriodoInicial(Registro registro, Date periodoInicial) throws AplicacaoException;

	List<Registro> obterPorPeriodoFinal(Registro registro, Date periodoFinal) throws AplicacaoException;

	List<Registro> obterSemPeriodo(Registro registro) throws AplicacaoException;

	List<Registro> obterPorPeriodo(Date periodoInicial, Date periodoFinal) throws AplicacaoException;

	List<String> obterDataStringPorMatriculaEPeriodo(Matricula matricula, Date periodoInicial, Date periodoFinal) throws AplicacaoException;

	List<Registro> obterSmsNaoEnviado() throws AplicacaoException;

	List<Registro> obterEmailNaoEnviado() throws AplicacaoException;

	List<Registro> obterNaoEnviado() throws AplicacaoException;

	List<Registro> obterRegistrosPorUsuario(Matricula matricula,
			Date periodoInicial, Date periodoFinal, Usuario usuario) throws AplicacaoException;
	
	public int obterQtdRegistrosEntradaPorUsuario(Matricula matricula,
			Date periodoInicial, Date periodoFinal, Usuario usuario) throws AplicacaoException;
}
	