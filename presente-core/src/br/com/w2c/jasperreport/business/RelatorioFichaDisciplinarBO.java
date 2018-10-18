package br.com.w2c.jasperreport.business;

import static br.com.chbandeira.utilitario.Util.isNuloOuVazio;

import java.io.File;
import java.io.OutputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.chbandeira.utilitario.UtilDate;
import br.com.w2c.controller.business.ConfiguracaoEscolaBO;
import br.com.w2c.exception.AplicacaoException;
import br.com.w2c.exception.NegocioException;
import br.com.w2c.jasperreport.enums.Header;
import br.com.w2c.jasperreport.util.ConstantesRelatorio;
import br.com.w2c.model.domain.ConfiguracaoEscola;
import br.com.w2c.model.domain.Matricula;
import br.com.w2c.model.domain.Ocorrencia;
import br.com.w2c.util.JasperReportUtil;

/**
 * 
 * @author charlles
 * @since 07/09/2017
 */
@Component
public class RelatorioFichaDisciplinarBO extends GeradorRelatorioBO {
	
	@Autowired
	private ConfiguracaoEscolaBO configuracaoEscolaBO;
	
	public void gerarCompleto(Date periodoInicial, Date periodoFinal,
			Matricula matricula, String diretorioRealRelatorios,
			OutputStream outputStream, Ocorrencia ocorrencia) throws AplicacaoException, NegocioException {
	
			Map<String, Object> params = new HashMap<String, Object>();
			ConfiguracaoEscola configuracoesEscola = configuracaoEscolaBO.obterConfiguracaoEscola();
		
			validarCampos(periodoInicial, periodoFinal);
			
		try {
			addParametros(periodoInicial, periodoFinal, matricula, ocorrencia, params, configuracoesEscola);
			
			String sourceFileName = diretorioRealRelatorios + File.separator + ConstantesRelatorio.JASPER_RELATORIO_FICHA_DISCIPLINAR_JASPER;
			
			JasperReportUtil.getInstance().exportarPdf(sourceFileName, params, outputStream);
		
		} catch (Exception e) {
			throw new AplicacaoException(e);
		}
	
	}

	private void addParametros(Date periodoInicial, Date periodoFinal, Matricula matricula, Ocorrencia ocorrencia,
			Map<String, Object> params, ConfiguracaoEscola configuracoesEscola) {
		Long idTipoOcorrencia = 0l;
		if (ocorrencia.getTipo() != null) {
			idTipoOcorrencia = ocorrencia.getTipo().getId();
		}
		params.put(Header.ID_TIPO_OCORRENCIA.getParametro(), idTipoOcorrencia);
		params.put(Header.ESCOLA_NOME.getParametro(), isNuloOuVazio(configuracoesEscola.getNome()) ? "" : configuracoesEscola.getNome());
		params.put(Header.MATRICULA.getParametro(), obterFiltroMatricula(matricula));
		params.put(Header.NOME_ALUNO.getParametro(), obterFiltroAluno(matricula));
		params.put(Header.SERIE.getParametro(), obterFiltroSerie(matricula));
		params.put(Header.TURMA.getParametro(), obterFiltroTurma(matricula));
		params.put(Header.TURNO.getParametro(), obterFiltroTurno(matricula));
		params.putAll(getPeriodo(periodoInicial, periodoFinal));
	}

	private void validarCampos(Date periodoInicial, Date periodoFinal) throws AplicacaoException, NegocioException {
		if (isNuloOuVazio(periodoInicial) || isNuloOuVazio(periodoFinal)) {
			addMensagem("MSG045");
		} else if (UtilDate.isPeriodoMesmoAno(periodoInicial, periodoFinal)) {
			addMensagem("MSG040");
		}
		validarMensagens();
		validarPeriodoMaiorQueAtual(periodoInicial);
		validarPeriodoMaiorQueAtual(periodoFinal);
	}

}
