package br.com.w2c.jasperreport.business;

import static br.com.chbandeira.utilitario.Util.isNuloOuVazio;

import java.io.File;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.chbandeira.utilitario.UtilDate;
import br.com.w2c.controller.business.BaseBO;
import br.com.w2c.controller.business.ConfiguracaoEscolaBO;
import br.com.w2c.exception.AplicacaoException;
import br.com.w2c.exception.NegocioException;
import br.com.w2c.jasperreport.enums.Header;
import br.com.w2c.jasperreport.util.Like;
import br.com.w2c.model.dao.RelatorioDAO;
import br.com.w2c.model.domain.ConfiguracaoEscola;
import br.com.w2c.model.domain.Matricula;
import br.com.w2c.util.JasperReportUtil;

/**
 * 
 * @author charlles
 * @since 27/10/2013
 */
@Component
@SuppressWarnings("rawtypes")
public class GeradorRelatorioBO extends BaseBO {
	
	@Autowired
	private ConfiguracaoEscolaBO configuracaoEscolaBO;
	
	@Autowired
	private RelatorioDAO relatorioDAO;

	protected Map<String, Object> getHeaderBaseRelatorio() throws AplicacaoException {
		Map<String, Object> parametros = new HashMap<String, Object>();
		ConfiguracaoEscola configuracoesEscola = configuracaoEscolaBO.obterConfiguracaoEscola();
	
		parametros.put(Header.ESCOLA_NOME.getParametro(), isNuloOuVazio(configuracoesEscola.getNome()) ? "" : configuracoesEscola.getNome());
		parametros.put(Header.ESCOLA_COD.getParametro(), isNuloOuVazio(configuracoesEscola.getUa()) ? "" : configuracoesEscola.getCodigo());
		parametros.put(Header.ESCOLA_UA.getParametro(), isNuloOuVazio(configuracoesEscola.getUa()) ? "" : configuracoesEscola.getUa());

		return parametros;
	}
	
	protected Map<String, Object> getNomeRelatorio(Long idRelatorio) throws AplicacaoException {
		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put(Header.NOME_RELATORIO.getParametro(), relatorioDAO.obterNome(idRelatorio));
		return parametros;
	}
	
	protected Map<String, Object> getPeriodo(Date periodoInicial, Date periodoFinal) {
		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put(Header.DATA_INICIO.getParametro(), periodoInicial);
		parametros.put(Header.DATA_FIM.getParametro(), periodoFinal);
		return parametros;
	}

	protected void validarPeriodoMaiorQueAtual(Date data) throws AplicacaoException, NegocioException {
		if (data.after(UtilDate.getDataAtual())) {
			addMensagem("MSG037");
			validarMensagens();
		}
	}
	
	protected void gerarCompleto(Date periodoInicial, Date periodoFinal,
			Matricula matricula, String diretorioRealRelatorios,
			OutputStream outputStream, Long idRelatorio, String jasperRelatorio) throws AplicacaoException, NegocioException {
		
		gerarCompleto(periodoInicial, periodoFinal, matricula,
				diretorioRealRelatorios, outputStream, idRelatorio,
				jasperRelatorio, null);
	}

	protected void gerarCompleto(Date periodoInicial, Date periodoFinal,
			Matricula matricula, String diretorioRealRelatorios,
			OutputStream outputStream, Long idRelatorio, String jasperRelatorio, Map<String, Object> parametros)
			throws AplicacaoException, NegocioException {
		if (isNuloOuVazio(periodoInicial) || isNuloOuVazio(periodoFinal)) {
			addMensagem("MSG045");
		} else if (UtilDate.isPeriodoMesmoAno(periodoInicial, periodoFinal)) {
			addMensagem("MSG040");
		}
		validarMensagens();
		validarPeriodoMaiorQueAtual(periodoInicial);
		validarPeriodoMaiorQueAtual(periodoFinal);
		try {
			Map<String, Object> params = getHeaderBaseRelatorio();
			params.putAll(getNomeRelatorio(idRelatorio));
			params.putAll(getPeriodo(periodoInicial, periodoFinal));
			params.put(Header.NOME_ALUNO.getParametro(), obterFiltroAluno(matricula));
			params.put(Header.MATRICULA.getParametro(), obterFiltroMatricula(matricula));
			params.put(Header.SERIE.getParametro(), obterFiltroSerie(matricula));
			params.put(Header.TURMA.getParametro(), obterFiltroTurma(matricula));
			params.put(Header.TURNO.getParametro(), obterFiltroTurno(matricula));
			params.put(Header.SALA.getParametro(), obterFiltroSala(matricula));
			params.put(Header.BOLSISTA.getParametro(), obterFiltroBolsista(matricula));
			params.put(Header.ANO.getParametro(), UtilDate.getAno(periodoInicial));
			
			if (parametros != null && !parametros.isEmpty()) {
				params.putAll(parametros);
			}
			
			String sourceFileName = diretorioRealRelatorios + File.separator + jasperRelatorio;
			
			JasperReportUtil.getInstance().exportarPdf(sourceFileName, params, outputStream);
		
		} catch (Exception e) {
			throw new AplicacaoException(e);
		}
	}
	
	protected void gerarCompletoMatriculas(Date periodoInicial, Date periodoFinal,
			List<Matricula> matriculas, String diretorioRealRelatorios,
			OutputStream outputStream, Long idRelatorio, String jasperRelatorio, Map<String, Object> parametros)
			throws AplicacaoException, NegocioException {
		if (isNuloOuVazio(periodoInicial) || isNuloOuVazio(periodoFinal)) {
			addMensagem("MSG045");
		} else if (UtilDate.isPeriodoMesmoAno(periodoInicial, periodoFinal)) {
			addMensagem("MSG040");
		}
		validarMensagens();
		validarPeriodoMaiorQueAtual(periodoInicial);
		validarPeriodoMaiorQueAtual(periodoFinal);
		try {
			Map<String, Object> params = getHeaderBaseRelatorio();
			params.putAll(getNomeRelatorio(idRelatorio));
			params.putAll(getPeriodo(periodoInicial, periodoFinal));
			params.put(Header.NOME_ALUNO.getParametro(), Like.semTexto());
			params.put(Header.MATRICULA.getParametro(), null);
			params.put(Header.MATRICULAS.getParametro(), obterFiltroMatriculas(matriculas));
			params.put(Header.SERIE.getParametro(), Like.semTexto());
			params.put(Header.TURMA.getParametro(), Like.semTexto());
			params.put(Header.TURNO.getParametro(), Like.semTexto());
			params.put(Header.SALA.getParametro(), Like.semTexto());
			params.put(Header.BOLSISTA.getParametro(), null);
			params.put(Header.ANO.getParametro(), UtilDate.getAno(periodoInicial));
			
			if (parametros != null && !parametros.isEmpty()) {
				params.putAll(parametros);
			}
			
			String sourceFileName = diretorioRealRelatorios + File.separator + jasperRelatorio;
			
			JasperReportUtil.getInstance().exportarPdf(sourceFileName, params, outputStream);
		
		} catch (Exception e) {
			throw new AplicacaoException(e);
		}
	}
	
	private String obterFiltroMatriculas(List<Matricula> matriculas) {
		
		List<Long> listaMatriculas = new ArrayList<Long>();
		
		for (Matricula m : matriculas) {
			listaMatriculas.add(m.getId());
		}
		
		return listaMatriculas.toString().replace("[", "").replace("]", "");
	}

	protected Boolean obterFiltroBolsista(Matricula matricula) {
		if (matricula.getBolsista() == null) {
			return Boolean.FALSE;
		}
		return matricula.getBolsista();
	}

	protected String obterFiltroSala(Matricula matricula) {
		
		if (matricula.getSala() == null
				|| matricula.getSala().getDescricao().trim().equals("")) {
			
			return Like.semTexto(); 
		}
		return matricula.getSala().getDescricao().trim();
	}

	protected String obterFiltroTurno(Matricula matricula) {
		
		if (matricula.getTurno() == null) {
			return Like.semTexto(); 
		}

		return matricula.getTurno().name();
	}

	protected String obterFiltroTurma(Matricula matricula) {
		
		if (matricula.getTurma() == null
				|| matricula.getTurma().getDescricao().trim().equals("")) {
			
			return Like.semTexto(); 
		}
		
		return matricula.getTurma().getDescricao().trim();
	}

	protected String obterFiltroSerie(Matricula matricula) {
		
		if (matricula.getSerie() == null
				|| matricula.getSerie().getDescricao().trim().equals("")) {
				
			return Like.semTexto(); 
		}
		
		return matricula.getSerie().getDescricao().trim();
	}

	protected String obterFiltroMatricula(Matricula matricula) {
		
		if (matricula.getMatricula() == null
				|| matricula.getMatricula().trim().equals("")) {
			
			return Like.semTexto();
		}
		
		return matricula.getMatricula().trim();
	}

	protected String obterFiltroAluno(Matricula matricula) {
		
		if (matricula.getAluno() == null
				|| matricula.getAluno().getNome().trim().equals("")) {
			
			return Like.semTexto();
		}
		
		return Like.colocarNoInicioEFinal(matricula.getAluno().getNome().trim());
	}
}
