package br.com.w2c.model.dao;

import java.util.Date;
import java.util.List;
import java.util.Set;

import br.com.w2c.exception.AplicacaoException;
import br.com.w2c.model.domain.CalendarioEscolar;
import br.com.w2c.model.wrapper.CalendarioEscolarWrapper;

/**
 * 
 * @author charlles
 * @since 26/11/2013
 */
public interface CalendarioEscolarDAO extends BaseDAO<CalendarioEscolar> {

	List<CalendarioEscolar> obterListaPorParametrosPesquisa(CalendarioEscolar calendarioEscolar) throws AplicacaoException;
	
	CalendarioEscolar obterRepetido(CalendarioEscolar calendarioEscolar) throws AplicacaoException;

	Set<CalendarioEscolarWrapper> obterRecorrentes() throws AplicacaoException;

	List<CalendarioEscolar> obterPorMesAno(Date data) throws AplicacaoException;

	List<CalendarioEscolar> obterPorPeriodoEAulasValidas(Date inicio, Date fim) throws AplicacaoException;

	List<CalendarioEscolar> obterPorPeriodo(Date inicio, Date fim) throws AplicacaoException;

	List<CalendarioEscolar> obterPorPeriodoAno(Integer ano) throws AplicacaoException;

	List<String> obterDataStringPorPeriodoEAulasValidas(Date periodoInicial, Date periodoFinal) throws AplicacaoException;
	
}
