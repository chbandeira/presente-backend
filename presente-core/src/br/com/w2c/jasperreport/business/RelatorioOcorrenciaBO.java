package br.com.w2c.jasperreport.business;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.w2c.controller.business.MatriculaBO;
import br.com.w2c.exception.AplicacaoException;
import br.com.w2c.exception.NegocioException;
import br.com.w2c.jasperreport.enums.Header;
import br.com.w2c.jasperreport.util.ConstantesRelatorio;
import br.com.w2c.model.domain.Matricula;
import br.com.w2c.model.domain.Ocorrencia;
import br.com.w2c.model.domain.Turma;

/**
 * 
 * @author charlles
 * @since 13/05/2017
 */
@Component
public class RelatorioOcorrenciaBO extends GeradorRelatorioBO {
	
	@Autowired
	private MatriculaBO matriculaBO;
	
	public void gerarCompletoFolhaDuplicada(Date periodoInicial, Date periodoFinal, Matricula matricula,
			String diretorioRealRelatorios, OutputStream outputStream, Ocorrencia ocorrencia) 
					throws AplicacaoException, NegocioException {
		
		Map<String, Object> params = gerarCompleto(ocorrencia);
		
		super.gerarCompleto(periodoInicial, periodoFinal, matricula,
				diretorioRealRelatorios, outputStream,
				ConstantesRelatorio.ID_RELATORIO_OCORRENCIA_DUPLICADO,
				ConstantesRelatorio.JASPER_RELATORIO_OCORRENCIA_DUPLICADO_JASPER,
				params);
	}

	public void gerarCompleto(Date periodoInicial, Date periodoFinal,
			Matricula matricula, String diretorioRealRelatorios,
			OutputStream outputStream, Ocorrencia ocorrencia) throws AplicacaoException, NegocioException {
		
		Map<String, Object> params = gerarCompleto(ocorrencia);
		
		super.gerarCompleto(periodoInicial, periodoFinal, matricula,
				diretorioRealRelatorios, outputStream,
				ConstantesRelatorio.ID_RELATORIO_OCORRENCIA,
				ConstantesRelatorio.JASPER_RELATORIO_OCORRENCIA_JASPER,
				params);
	}

	private Map<String, Object> gerarCompleto(Ocorrencia ocorrencia) throws AplicacaoException {
		Map<String, Object> params = getHeaderBaseRelatorio();
		
		Long idOcorrencia = 0l;
		if (ocorrencia.getId() != null) {
			idOcorrencia = ocorrencia.getId();
		}
		params.put(Header.ID_OCORRENCIA.getParametro(), idOcorrencia);
		params.put(Header.ID_OCORRENCIAS.getParametro(), null);
		
		Long idTipoOcorrencia = 0l;
		if (ocorrencia.getTipo() != null) {
			idTipoOcorrencia = ocorrencia.getTipo().getId();
		}
		params.put(Header.ID_TIPO_OCORRENCIA.getParametro(), idTipoOcorrencia);
		return params;
	}

	public void gerarCompletoMatriculasFolhaDuplicada(Date periodoInicial, Date periodoFinal, List<Matricula> matriculas,
			String diretorioRealRelatorios, OutputStream outputStream, Ocorrencia ocorrencia, List<Long> idsSalvos) 
					throws AplicacaoException, NegocioException {
		
		Map<String, Object> params = gerarCompletoMatriculas(ocorrencia, idsSalvos);
		
		super.gerarCompletoMatriculas(periodoInicial, periodoFinal, matriculas,
				diretorioRealRelatorios, outputStream,
				ConstantesRelatorio.ID_RELATORIO_OCORRENCIA_DUPLICADO,
				ConstantesRelatorio.JASPER_RELATORIO_OCORRENCIA_DUPLICADO_JASPER,
				params);
	}

	public void gerarCompletoMatriculas(Date periodoInicial, Date periodoFinal,
			List<Matricula> matriculas, String diretorioRealRelatorios,
			OutputStream outputStream, Ocorrencia ocorrencia, List<Long> idsSalvos) throws AplicacaoException, NegocioException {
		
		Map<String, Object> params = gerarCompletoMatriculas(ocorrencia, idsSalvos);
		
		super.gerarCompletoMatriculas(periodoInicial, periodoFinal, matriculas,
				diretorioRealRelatorios, outputStream,
				ConstantesRelatorio.ID_RELATORIO_OCORRENCIA,
				ConstantesRelatorio.JASPER_RELATORIO_OCORRENCIA_JASPER,
				params);
	}

	private Map<String, Object> gerarCompletoMatriculas(Ocorrencia ocorrencia, List<Long> idsSalvos)
			throws AplicacaoException {
		Map<String, Object> params = getHeaderBaseRelatorio();
		
		params.put(Header.ID_OCORRENCIA.getParametro(), 0l);
		params.put(Header.ID_OCORRENCIAS.getParametro(), obterFiltroListIn(idsSalvos));
		
		Long idTipoOcorrencia = 0l;
		if (ocorrencia.getTipo() != null) {
			idTipoOcorrencia = ocorrencia.getTipo().getId();
		}
		params.put(Header.ID_TIPO_OCORRENCIA.getParametro(), idTipoOcorrencia);
		return params;
	}
	
	public void gerarCompletoTurmasFolhaDuplicada(Date periodoInicial, Date periodoFinal, List<Turma> turmas,
			String diretorioRealRelatorios, OutputStream outputStream, Ocorrencia ocorrencia, List<Long> idsSalvos) 
					throws AplicacaoException, NegocioException {
		
		List<Matricula> matriculas = new ArrayList<>();
		Map<String, Object> params = gerarCompletoTurmas(turmas, ocorrencia, idsSalvos, matriculas);
		
		super.gerarCompletoMatriculas(periodoInicial, periodoFinal, matriculas,
				diretorioRealRelatorios, outputStream,
				ConstantesRelatorio.ID_RELATORIO_OCORRENCIA_DUPLICADO,
				ConstantesRelatorio.JASPER_RELATORIO_OCORRENCIA_DUPLICADO_JASPER,
				params);
	}

	public void gerarCompletoTurmas(Date periodoInicial, Date periodoFinal,
			List<Turma> turmas, String diretorioRealRelatorios,
			OutputStream outputStream, Ocorrencia ocorrencia, List<Long> idsSalvos) throws AplicacaoException, NegocioException {
		
		List<Matricula> matriculas = new ArrayList<>();
		Map<String, Object> params = gerarCompletoTurmas(turmas, ocorrencia, idsSalvos, matriculas);
		
		super.gerarCompletoMatriculas(periodoInicial, periodoFinal, matriculas,
				diretorioRealRelatorios, outputStream,
				ConstantesRelatorio.ID_RELATORIO_OCORRENCIA,
				ConstantesRelatorio.JASPER_RELATORIO_OCORRENCIA_JASPER,
				params);
	}

	private Map<String, Object> gerarCompletoTurmas(List<Turma> turmas, Ocorrencia ocorrencia, List<Long> idsSalvos,
			List<Matricula> matriculas) throws AplicacaoException {
		for (Turma turma : turmas) {
			matriculas.addAll(matriculaBO.obterPorTurmaSerieAnoLetivo(turma));
		}
	
		Map<String, Object> params = gerarCompletoMatriculas(ocorrencia, idsSalvos);
		return params;
	}

	private String obterFiltroListIn(List<Long> listaIds) {
		return listaIds.toString().replace("[", "").replace("]", "");
	}

}
