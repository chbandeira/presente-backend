package br.com.w2c.jasperreport.business;

import static br.com.chbandeira.utilitario.Util.isNuloOuVazio;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.chbandeira.utilitario.Util;
import br.com.chbandeira.utilitario.UtilDate;
import br.com.chbandeira.utilitario.UtilFile;
import br.com.w2c.controller.business.MatriculaBO;
import br.com.w2c.exception.AplicacaoException;
import br.com.w2c.exception.NegocioException;
import br.com.w2c.jasperreport.enums.Header;
import br.com.w2c.jasperreport.util.ConstantesRelatorio;
import br.com.w2c.model.dao.RelatorioDAO;
import br.com.w2c.model.domain.Matricula;
import br.com.w2c.model.domain.Usuario;
import br.com.w2c.model.wrapper.MatriculaWrapper;
import br.com.w2c.util.Constantes;
import br.com.w2c.util.JasperReportUtil;
import br.com.w2c.util.ResourceUtil;

/**
 * 
 * @author charlles
 * @since 27/10/2013
 */
@Component
public class RelatorioFaltasBO extends GeradorRelatorioBO {
	
	@Autowired
	private RelatorioDAO relatorioDAO;
	@Autowired
	private MatriculaBO matriculaBO;

	private Map<String, Object> getNomeRelatorio() throws AplicacaoException {
		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put(Header.NOME_RELATORIO.getParametro(), relatorioDAO.obterNome(ConstantesRelatorio.ID_RELATORIO_FALTAS));
		return parametros;
	}
	
	@Deprecated
	public InputStream gerarRelatorioFaltas(Date periodoInicial, Date periodoFinal, String diretorioRealRelatorios) throws AplicacaoException, NegocioException {
		if (isNuloOuVazio(periodoInicial) || isNuloOuVazio(periodoFinal)) {
			addMensagem("MSG045");
		} else if (UtilDate.isPeriodoMesmoAno(periodoInicial, periodoFinal)) {
			addMensagem("MSG040");
		}
		validarMensagens();
		try {
			Map<String, Object> params = getHeaderBaseRelatorio();
			params.putAll(getNomeRelatorio());
			params.putAll(getPeriodo(periodoInicial, periodoFinal));
			
			String destFileName = ResourceUtil.getPath(Constantes.PATH_RELATORIO) + ConstantesRelatorio.JASPER_RELATORIO_FALTAS_PDF;
			String sourceFileName = diretorioRealRelatorios + File.separator + ConstantesRelatorio.JASPER_RELATORIO_FALTAS_JASPER;
			
			JasperReportUtil.getInstance().exportar(sourceFileName, params, destFileName);
			
			return UtilFile.getInputStream(destFileName);
		
		} catch (Exception e) {
			throw new AplicacaoException(e);
		}
	}

	public void gerar(Date periodoInicial, Date periodoFinal, String diretorioRealRelatorios, OutputStream outputStream) throws NegocioException, AplicacaoException {
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
			params.putAll(getNomeRelatorio());
			params.putAll(getPeriodo(periodoInicial, periodoFinal));
			params.put(Header.ANO.getParametro(), UtilDate.getAno(periodoInicial));
			
			String sourceFileName = diretorioRealRelatorios + File.separator + ConstantesRelatorio.JASPER_RELATORIO_FALTAS_JASPER;
			
			JasperReportUtil.getInstance().exportarPdf(sourceFileName, params, outputStream);
		
		} catch (Exception e) {
			throw new AplicacaoException(e);
		}

	}

	public void gerar(Date periodoInicial, Date periodoFinal,
			String diretorioRealRelatorios, OutputStream outputStream, 
			Matricula matricula) throws NegocioException, AplicacaoException {
		
		if (isNuloOuVazio(periodoInicial) || isNuloOuVazio(periodoFinal)) {
			addMensagem("MSG045");
		} 
		else if (UtilDate.isPeriodoMesmoAno(periodoInicial, periodoFinal)) {
			addMensagem("MSG040");
		}
		else if (Util.isNuloOuVazio(matricula) || Util.isNuloOuVazio(obterFiltroMatricula(matricula))) {
			addMensagem("MSG071");
		}
		validarMensagens();
		validarPeriodoMaiorQueAtual(periodoInicial);
		validarPeriodoMaiorQueAtual(periodoFinal);
		try {
			Map<String, Object> params = getHeaderBaseRelatorio();
			params.putAll(getNomeRelatorio());
			params.putAll(getPeriodo(periodoInicial, periodoFinal));
			params.put(Header.ANO.getParametro(), UtilDate.getAno(periodoInicial));
			params.put("MATRICULA", obterFiltroMatricula(matricula));
			
			String sourceFileName = diretorioRealRelatorios + File.separator + ConstantesRelatorio.JASPER_RELATORIO_ONLINE_FALTAS_JASPER;
			
			JasperReportUtil.getInstance().exportarPdf(sourceFileName, params, outputStream);
		
		} catch (Exception e) {
			throw new AplicacaoException(e);
		}
	}

	public List<Matricula> obterPorUsuario(Usuario usuario) throws AplicacaoException {
		return matriculaBO.obterPorUsuario(usuario);
	}

	public List<MatriculaWrapper> obterRelatorioOnlineFaltas(
			Date periodoInicial, Date periodoFinal, Matricula matricula)
			throws AplicacaoException, NegocioException {
		
		validarCamposObrigatoriosRelatorioOnlineFaltas(periodoInicial, periodoFinal, matricula);
		validarPeriodoMaiorQueAtual(periodoInicial);
		validarPeriodoMaiorQueAtual(periodoFinal);
		
		return matriculaBO.obterRelatorioOnlineFaltas(periodoInicial, periodoFinal, matricula);
	}

	public void validarCamposObrigatoriosRelatorioOnlineFaltas(
			Date periodoInicial, Date periodoFinal, Matricula matricula)
			throws NegocioException, AplicacaoException {

		if (isNuloOuVazio(matricula) || isNuloOuVazio(obterFiltroMatricula(matricula))) {
			addMensagemCampo("aluno_s");
		}
		else if (isNuloOuVazio(periodoInicial)) {
			addMensagemCampo("periodoInicial");
		}
		else if (isNuloOuVazio(periodoFinal)) {
			addMensagemCampo("periodoFinal");
		}
		validarMensagens();
	}

	public List<MatriculaWrapper> obterRelatorioOnlineFaltasEUsuario(
			Date periodoInicial, Date periodoFinal, Matricula matricula,
			Usuario usuario) throws AplicacaoException, NegocioException {
		if (usuario == null) {
			return obterRelatorioOnlineFaltas(periodoInicial, periodoFinal, matricula);
		}
		else {
			return matriculaBO.obterRelatorioOnlineFaltasEUsuario(periodoInicial, periodoFinal, matricula, usuario);
		}
	}

	public void gerarCompleto(Date periodoInicial, Date periodoFinal,
			Matricula matricula, String diretorioRealRelatorios,
			OutputStream outputStream) throws AplicacaoException, NegocioException {
		
		super.gerarCompleto(periodoInicial, periodoFinal, matricula,
				diretorioRealRelatorios, outputStream,
				ConstantesRelatorio.ID_RELATORIO_FALTAS,
				ConstantesRelatorio.JASPER_RELATORIO_FALTAS_JASPER);
	}
 	
}
