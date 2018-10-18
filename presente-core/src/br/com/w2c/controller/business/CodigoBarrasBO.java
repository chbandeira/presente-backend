package br.com.w2c.controller.business;

import static br.com.chbandeira.utilitario.Util.isNuloOuVazio;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.w2c.exception.AplicacaoException;
import br.com.w2c.exception.NegocioException;
import br.com.w2c.model.domain.Matricula;
import br.com.w2c.util.GeradorPdfBarcode;

/**
 * 
 * @author charlles
 * @since 29/09/2013
 */
@Component
@SuppressWarnings("rawtypes")
public class CodigoBarrasBO extends BaseBO {

	@Autowired
	private MatriculaBO matriculaBO;
	
	public void gerarPdfCodigoBarras(Matricula matricula, Date periodoInicial, Date periodoFinal, String diretorio) throws NegocioException, AplicacaoException {
		String[] matriculas = matriculaBO.obterMatriculasPorPeriodo(matricula, periodoInicial, periodoFinal);
		if (isNuloOuVazio(matriculas)) {
			addMensagem("MSG016");
			validarMensagens();
		}
		matriculas = formatarMatriculas(matriculas);
		new GeradorPdfBarcode().createPdfCodebar(diretorio, matriculas);
	}

	/**
	 * Formata as matriculas com A no início e A no final para a geração de cógidos de barras
	 * @param matriculas
	 */
	public String[] formatarMatriculas(String[] matriculas) {
		String[] matriculasFormatadas = new String[matriculas.length];
		for (int i = 0; i < matriculas.length; i++) {
			matriculasFormatadas[i] = "A"+matriculas[i]+"A";
		}
		return matriculasFormatadas;
	}
	
}
