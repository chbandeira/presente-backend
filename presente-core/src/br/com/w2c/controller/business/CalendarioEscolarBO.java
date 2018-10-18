package br.com.w2c.controller.business;

import static br.com.chbandeira.utilitario.Util.isNuloOuVazio;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.chbandeira.utilitario.UtilDate;
import br.com.w2c.controller.business.comparator.CalendarioEscolarDiaMesAnoComparator;
import br.com.w2c.exception.AplicacaoException;
import br.com.w2c.exception.NegocioException;
import br.com.w2c.model.dao.CalendarioEscolarDAO;
import br.com.w2c.model.domain.CalendarioEscolar;
import br.com.w2c.model.domain.ConfiguracaoEscola;
import br.com.w2c.model.enums.StatusCalendarioEscolar;
import br.com.w2c.model.wrapper.CalendarioEscolarWrapper;
import br.com.w2c.util.comparator.CalendarDiaMesAnoComparator;

/**
 * 
 * @author charlles
 * @since 26/11/2013
 */
@Component
public class CalendarioEscolarBO extends BaseBO<CalendarioEscolar> {

	@Autowired
	private CalendarioEscolarDAO calendarioEscolarDAO;
	
	/**
	 * Atualiza a data de acordo com o ano letivo
	 * @param novoCalendarioEscolar
	 * @param wrapper
	 * @param anoLetivoAtual
	 * @throws NegocioException 
	 * @throws AplicacaoException 
	 */
	private void setAtualizarData(CalendarioEscolar novoCalendarioEscolar, CalendarioEscolarWrapper wrapper, Integer anoLetivoAtual) throws NegocioException, AplicacaoException {
		try {
			Calendar calendar = UtilDate.getApenasData();
			calendar.set(Calendar.DAY_OF_MONTH, Integer.valueOf(wrapper.getDia()));
			calendar.set(Calendar.MONTH, Integer.valueOf(wrapper.getMes()));
			calendar.set(Calendar.YEAR, anoLetivoAtual);
			novoCalendarioEscolar.setData(calendar.getTime());
			
		} catch (ParseException e) {
			throw new AplicacaoException(e);
		}
	}

	@Transactional(propagation=Propagation.REQUIRED, readOnly=false)
	private void gerarCalendarioPorPeriodo(Calendar periodoInicial, Calendar periodoFinal) throws NegocioException, AplicacaoException {
		
		try {
			excluirForaDoPeriodoInicial(periodoInicial);
			excluirForaDoPeriodoFinal(periodoFinal);
			calendarioEscolarDAO.flush();
			Integer ano = verificarSeEstaNoMesmoAno(periodoInicial, periodoFinal);
			gerarCalendarios(periodoInicial, periodoFinal, ano);
			
		} catch (ParseException e) {
			throw new AplicacaoException(e);
		}
	}

	@Transactional(propagation=Propagation.REQUIRED, readOnly=false)
	private void gerarCalendarios(Calendar periodoInicial, Calendar periodoFinal, Integer ano) throws NegocioException, AplicacaoException {
		List<CalendarioEscolar> calendario = calendarioEscolarDAO.obterPorPeriodoAno(ano);
		
		CalendarDiaMesAnoComparator compare = new CalendarDiaMesAnoComparator();
		whilePrincipal : while (periodoInicial.before(periodoFinal) || compare.compare(periodoInicial, periodoFinal)==0) {
	
			CalendarioEscolar calendarioEscolar = criarCalendarioLetivo(periodoInicial);
			if (isContemCalendarioEscolar(calendario, calendarioEscolar)) {
				periodoInicial.add(Calendar.DATE, 1);
				continue whilePrincipal;
			}
	
			salvar(calendarioEscolar);
			periodoInicial.add(Calendar.DATE, 1);
		}
	}

	private boolean isContemCalendarioEscolar(List<CalendarioEscolar> calendario, CalendarioEscolar calendarioEscolar) {
		return Collections.binarySearch(calendario, calendarioEscolar, new CalendarioEscolarDiaMesAnoComparator()) >= 0;
	}

	private Integer verificarSeEstaNoMesmoAno(Calendar periodoInicial, Calendar periodoFinal) throws NegocioException, AplicacaoException {
		if (UtilDate.isPeriodoMesmoAno(periodoInicial.getTime(), periodoFinal.getTime())) {
			addMensagem("MSG040");
		}
		validarMensagens();
		
		return UtilDate.getAno(periodoInicial.getTime());
	}

	@Transactional(propagation=Propagation.REQUIRED, readOnly=false)
	private void excluirForaDoPeriodoFinal(Calendar periodoFinal) throws ParseException, NegocioException, AplicacaoException {
		Calendar fimAno = UtilDate.getFimAnoApenasData();
		Calendar fimPeriodo = UtilDate.getApenasData();
		fimPeriodo.setTime(periodoFinal.getTime());
		fimPeriodo.add(Calendar.DATE, 1);
		List<CalendarioEscolar> calendarioFim = calendarioEscolarDAO.obterPorPeriodo(fimPeriodo.getTime(), fimAno.getTime());
		for (CalendarioEscolar calendarioEscolar : calendarioFim) {
			if (calendarioEscolar.getAutomatico()) {
				excluir(calendarioEscolar);
			}
		}
	}

	@Transactional(propagation=Propagation.REQUIRED, readOnly=false)
	private void excluirForaDoPeriodoInicial(Calendar periodoInicial) throws ParseException, NegocioException, AplicacaoException {
		Calendar inicioAno = UtilDate.getInicioAnoApenasData();
		Calendar inicioPeriodo = UtilDate.getApenasData();
		inicioPeriodo.setTime(periodoInicial.getTime());
		inicioPeriodo.add(Calendar.DATE, -1);
		List<CalendarioEscolar> calendarioInicio = calendarioEscolarDAO.obterPorPeriodo(inicioAno.getTime(), inicioPeriodo.getTime());
		for (CalendarioEscolar calendarioEscolar : calendarioInicio) {
			if (calendarioEscolar.getAutomatico()) {
				excluir(calendarioEscolar);
			}
		}
	}

	private CalendarioEscolar criarCalendarioLetivo(Calendar calendar) throws AplicacaoException {
		CalendarioEscolar calendarioEscolar = new CalendarioEscolar();
		calendarioEscolar.setData(calendar.getTime());
		calendarioEscolar.setRecorrente(Boolean.FALSE);
		calendarioEscolar.setAutomatico(Boolean.TRUE);
		//Verificar o dia da semana
		if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY 
				|| calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
			
			calendarioEscolar.setStatus(StatusCalendarioEscolar.EXCLUIR.getStatus());
			calendarioEscolar.setDescricao(getLabel("finalDeSemana"));
		} else {
			calendarioEscolar.setStatus(StatusCalendarioEscolar.INCLUIR.getStatus());
			calendarioEscolar.setDescricao(getLabel("aulaNormal"));
		}
		return calendarioEscolar;
	}

	@Override
	public void validarCamposObrigatorios(CalendarioEscolar entidade) throws NegocioException, AplicacaoException {
		super.validarCamposObrigatorios(entidade);
		if (isNuloOuVazio(entidade.getData())) {
			addMensagemCampo("data");
		}
		if (isNuloOuVazio(entidade.getDescricao())) {
			addMensagemCampo("descricao");
		}
		validarMensagens();
	}
	
	@Override
	public void validarCamposParaExclusao(CalendarioEscolar entidade) throws NegocioException, AplicacaoException {
		super.validarCamposParaExclusao(entidade);
		if (isNuloOuVazio(entidade.getId())) {
			lancarMensagemSuportePadrao();
		}
	}
	
	/**
	 * Por padrão, as novas datas inseridas serão excluidas do calendario academico, 
	 * assim não os contabilizando como datas de aula.<br> 
	 * Pode ser que uma data seja incluida (status 1) para que conte como data de aula. 
	 * Exemplo dia de sábado (reposição de aula).
	 * @param calendarioEscolar
	 * @throws NegocioException 
	 * @throws AplicacaoException 
	 */
	public void salvar(CalendarioEscolar calendarioEscolar) throws NegocioException, AplicacaoException {
		validarCamposObrigatorios(calendarioEscolar);
		if (isNuloOuVazio(calendarioEscolar.getStatus())) {
			calendarioEscolar.setStatus(StatusCalendarioEscolar.EXCLUIR.getStatus());
		}
		if (isNuloOuVazio(calendarioEscolar.getRecorrente())) {
			calendarioEscolar.setRecorrente(Boolean.FALSE);
		}
		if (isNuloOuVazio(calendarioEscolar.getAutomatico())) {
			calendarioEscolar.setAutomatico(Boolean.FALSE);
		}
		calendarioEscolar.setDataUltimaAtualizacao(null);
		calendarioEscolarDAO.salvar(calendarioEscolar);
	}

	public List<CalendarioEscolar> obterListaPorParametrosPesquisa(CalendarioEscolar calendarioEscolar) throws NegocioException, AplicacaoException {
		return calendarioEscolarDAO.obterListaPorParametrosPesquisa(calendarioEscolar);
	}

	public void excluir(CalendarioEscolar calendarioEscolar) throws NegocioException, AplicacaoException {
		validarCamposParaExclusao(calendarioEscolar);
		calendarioEscolarDAO.excluir(calendarioEscolar);
	}

	public List<CalendarioEscolarWrapper> obterRecorrentes() throws AplicacaoException {
		return new ArrayList<CalendarioEscolarWrapper>(calendarioEscolarDAO.obterRecorrentes());
	}

	@Transactional(propagation=Propagation.REQUIRED, readOnly=false)
	public void atualizarDatasRecorrentes(Integer anoLetivoAtual) throws NegocioException, AplicacaoException {
		List<CalendarioEscolarWrapper> recorrentes = obterRecorrentes();
		for (CalendarioEscolarWrapper wrapper : recorrentes) {
			
			CalendarioEscolar novoCalendarioEscolar = new CalendarioEscolar();
			novoCalendarioEscolar.setDescricao(wrapper.getDescricao());
			novoCalendarioEscolar.setRecorrente(wrapper.getRecorrente());
			novoCalendarioEscolar.setAutomatico(wrapper.getAutomatico());
			novoCalendarioEscolar.setStatus(wrapper.getStatus());
			
			setAtualizarData(novoCalendarioEscolar, wrapper, anoLetivoAtual);
			
			novoCalendarioEscolar.setDataUltimaAtualizacao(null);
			calendarioEscolarDAO.salvar(novoCalendarioEscolar);
		}
	}

	public List<CalendarioEscolar> obterPorMesAno(Date data) throws AplicacaoException {
		 return calendarioEscolarDAO.obterPorMesAno(data);
	}
	
	public List<CalendarioEscolar> obterPorPeriodoEAulasValidas(Date inicio, Date fim) throws AplicacaoException {
		return calendarioEscolarDAO.obterPorPeriodoEAulasValidas(inicio, fim);
	}
	
	public void gerarCalendarioEscolar(ConfiguracaoEscola configuracaoEscola) throws NegocioException, AplicacaoException {
		try {
			Calendar inicioAnoLetivo = UtilDate.getApenasData();
			Date inicioAnoLetivoConfig = configuracaoEscola.getInicioAnoLetivo();
			if (isNuloOuVazio(inicioAnoLetivoConfig)) {
				inicioAnoLetivoConfig = UtilDate.getInicioAnoApenasData().getTime();
			}
			inicioAnoLetivo.setTime(inicioAnoLetivoConfig);
			
			Calendar fimAnoLetivo = UtilDate.getApenasData();
			Date fimAnoLetivoConfig = configuracaoEscola.getFimAnoLetivo();
			if (isNuloOuVazio(fimAnoLetivoConfig)) {
				fimAnoLetivoConfig = UtilDate.getFimAnoApenasData().getTime(); 
			}
			fimAnoLetivo.setTime(fimAnoLetivoConfig);
	
			gerarCalendarioPorPeriodo(inicioAnoLetivo, fimAnoLetivo);
			
		} catch (ParseException e) {
			throw new AplicacaoException(e);
		}
	}

	public int obterQtdDiasAulaNormal(Date periodoInicial, Date periodoFinal) throws AplicacaoException {
		return obterPorPeriodoEAulasValidas(periodoInicial, periodoFinal).size();
	}

	public List<CalendarioEscolar> obterUltimosAtualizados(Date dataHoraUltimaAtualizacao) throws AplicacaoException {
		return calendarioEscolarDAO.obterUltimosAtualizados(dataHoraUltimaAtualizacao, CalendarioEscolar.class);
	}

	public void salvarOnline(CalendarioEscolar calendario) throws AplicacaoException, NegocioException {
		CalendarioEscolar retorno = obterPorCriterioOnline(calendario);
		
		if (retorno == null) {
			retorno = new CalendarioEscolar();
			retorno.setIdentificador(calendario.getIdentificador());
			retorno.setData(calendario.getData());
		}
		
		retorno.setAutomatico(calendario.getAutomatico());
		retorno.setDescricao(calendario.getDescricao());
		retorno.setRecorrente(calendario.getRecorrente());
		retorno.setStatus(calendario.getStatus());
		retorno.setDataUltimaAtualizacao(calendario.getDataUltimaAtualizacao());
		
		calendarioEscolarDAO.salvar(retorno);
	}

	public CalendarioEscolar obterPorCriterioOnline(CalendarioEscolar calendario)
			throws AplicacaoException, NegocioException {
		List<CalendarioEscolar> lista = calendarioEscolarDAO.obterPorCriterioOnline(calendario);
		String objeto = getLabel("data") + " " + UtilDate.getDataFormatada(calendario.getData());
		CalendarioEscolar retorno = validarObjetoUnico(lista, objeto);
		return retorno;
	}

	public CalendarioEscolarDAO getCalendarioEscolarDAO() {
		return calendarioEscolarDAO;
	}

}
