package br.com.w2c.model.dao;

import static br.com.chbandeira.utilitario.Util.isNuloOuVazio;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import br.com.chbandeira.utilitario.UtilDate;
import br.com.w2c.exception.AplicacaoException;
import br.com.w2c.model.domain.CalendarioEscolar;
import br.com.w2c.model.wrapper.CalendarioEscolarWrapper;
import br.com.w2c.util.Constantes;

/**
 * 
 * @author charlles
 * @since 26/11/2013
 */
@Repository
public class CalendarioEscolarDAOImpl extends BaseDAOImpl<CalendarioEscolar> implements CalendarioEscolarDAO {

	@Override
	public List<CalendarioEscolar> obterListaPorParametrosPesquisa(CalendarioEscolar calendarioEscolar) throws AplicacaoException {
		Map<String, Object> parametros = criarParametros();
		parametros.put("descricao", like(calendarioEscolar.getDescricao()));
		parametros.put("recorrente", calendarioEscolar.getRecorrente());
		parametros.put("status",calendarioEscolar.getStatus());
		List<CalendarioEscolar> retorno = null;
		if (isNuloOuVazio(calendarioEscolar.getData())) {
			retorno = obterListaPorParametrosPesquisaSemData(parametros);
		} else {
			parametros.put("data", UtilDate.getDataFormatada(calendarioEscolar.getData(), Constantes.DD_MM_YYYY_DB));
			retorno = obterListaPorParametrosPesquisaComData(parametros);
		}
		return retorno;
	}

	@SuppressWarnings("unchecked")
	private List<CalendarioEscolar> obterListaPorParametrosPesquisaComData(Map<String, Object> parametros) throws AplicacaoException {
		return obterResultado("CalendarioEscolar.obterListaPorParametrosPesquisaComData", parametros);
	}

	@SuppressWarnings("unchecked")
	private List<CalendarioEscolar> obterListaPorParametrosPesquisaSemData(Map<String, Object> parametros) throws AplicacaoException {
		return obterResultado("CalendarioEscolar.obterListaPorParametrosPesquisaSemData", parametros);
	}

	@Override
	public CalendarioEscolar obterRepetido(CalendarioEscolar calendarioEscolar) throws AplicacaoException {
		Map<String, Object> parametros = criarParametros();
		parametros.put("descricao", calendarioEscolar.getDescricao());
		parametros.put("id", isNuloOuVazio(calendarioEscolar.getId()) ? 0L : calendarioEscolar.getId());
		return (CalendarioEscolar) obterResultadoUnico("CalendarioEscolar.obterRepetido", parametros);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Set<CalendarioEscolarWrapper> obterRecorrentes() throws AplicacaoException {
		return new HashSet<CalendarioEscolarWrapper>(obterResultado("CalendarioEscolar.obterRecorrentes"));
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CalendarioEscolar> obterPorMesAno(Date data) throws AplicacaoException {
		Map<String, Object> parametros = criarParametros();
		parametros.put("mesAno", UtilDate.getDataFormatada(data, Constantes.MM_YYYY_DB));
		return obterResultado("CalendarioEscolar.obterPorMesAno", parametros);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CalendarioEscolar> obterPorPeriodoEAulasValidas(Date inicio, Date fim) throws AplicacaoException {
		Map<String, Object> parametros = criarParametros();
		parametros.put("inicio", UtilDate.getDataFormatada(inicio, Constantes.DD_MM_YYYY_DB));
		parametros.put("fim", UtilDate.getDataFormatada(fim, Constantes.DD_MM_YYYY_DB));
		return obterResultado("CalendarioEscolar.obterPorPeriodoEAulasValidas", parametros);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CalendarioEscolar> obterPorPeriodo(Date inicio, Date fim) throws AplicacaoException {
		Map<String, Object> parametros = criarParametros();
		parametros.put("inicio", UtilDate.getDataFormatada(inicio, Constantes.DD_MM_YYYY_DB));
		parametros.put("fim", UtilDate.getDataFormatada(fim, Constantes.DD_MM_YYYY_DB));
		return obterResultado("CalendarioEscolar.obterPorPeriodo", parametros);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CalendarioEscolar> obterPorPeriodoAno(Integer ano) throws AplicacaoException {
		Map<String, Object> parametros = criarParametros();
		parametros.put("ano", ano.toString());
		return obterResultado("CalendarioEscolar.obterPorPeriodoAno", parametros);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<String> obterDataStringPorPeriodoEAulasValidas(Date periodoInicial, Date periodoFinal) throws AplicacaoException {
		Map<String, Object> parametros = criarParametros();
		parametros.put("inicio", UtilDate.getDataFormatada(periodoInicial, Constantes.DD_MM_YYYY_DB));
		parametros.put("fim", UtilDate.getDataFormatada(periodoFinal, Constantes.DD_MM_YYYY_DB));
		return obterResultado("CalendarioEscolar.obterDataStringPorPeriodoEAulasValidas", parametros);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CalendarioEscolar> obterPorCriterioOnline(CalendarioEscolar entidade) throws AplicacaoException {
		Criteria criteria = createCriteria(CalendarioEscolar.class);
		criteria.add(Restrictions.eq("identificador", entidade.getIdentificador()));
		criteria.add(Restrictions.eq("data", entidade.getData()));
		return criteria.list();
	}
	
}
