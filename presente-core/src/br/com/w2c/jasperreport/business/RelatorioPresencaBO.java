package br.com.w2c.jasperreport.business;

import java.io.OutputStream;
import java.util.Date;

import org.springframework.stereotype.Component;

import br.com.w2c.exception.AplicacaoException;
import br.com.w2c.exception.NegocioException;
import br.com.w2c.jasperreport.util.ConstantesRelatorio;
import br.com.w2c.model.domain.Matricula;

/**
 * 
 * @author charlles
 * @since 25/02/2017
 */
@Component
public class RelatorioPresencaBO extends GeradorRelatorioBO {
	
	public void gerarCompleto(Date periodoInicial, Date periodoFinal,
			Matricula matricula, String diretorioRealRelatorios,
			OutputStream outputStream) throws AplicacaoException, NegocioException {
		
		super.gerarCompleto(periodoInicial, periodoFinal, matricula,
				diretorioRealRelatorios, outputStream,
				ConstantesRelatorio.ID_RELATORIO_PRESENCA,
				ConstantesRelatorio.JASPER_RELATORIO_PRESENCA_JASPER);
	}
 	
}
