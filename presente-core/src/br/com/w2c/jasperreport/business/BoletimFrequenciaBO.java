package br.com.w2c.jasperreport.business;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import br.com.chbandeira.utilitario.UtilDate;
import br.com.w2c.exception.AplicacaoException;
import br.com.w2c.jasperreport.enums.Header;
import br.com.w2c.jasperreport.wrapper.BoletimFrequenciaHeaderWrapper;
import br.com.w2c.jasperreport.wrapper.BoletimFrequenciaWrapper;
import br.com.w2c.model.enums.Turno;
import br.com.w2c.util.Constantes;

/**
 * 
 * @author charlles
 * @since 27/10/2013
 */
@Component
public class BoletimFrequenciaBO extends GeradorRelatorioBO {

	public void gerarBoletimFrequencia() throws AplicacaoException {
		Map<String, Object> parametros = getHeaderBaseRelatorio();
		parametros.putAll(getHeader());
		parametros.putAll(getBoletimFrequenciaHeader());

		List<BoletimFrequenciaWrapper> lista = new ArrayList<BoletimFrequenciaWrapper>();
		popularListaBoletimFrequencia(lista);
		
//		JasperReportUtil.compilarJrxmlParaPdf(lista, parametros, Constantes.PATH_JRXML, Constantes.PATH_PADRAO, "BoletimFrequencia");
	}

	private void popularListaBoletimFrequencia(List<BoletimFrequenciaWrapper> lista) {
		BoletimFrequenciaWrapper wrapper = new BoletimFrequenciaWrapper();
		wrapper.setDia(1);
		wrapper.setEntradaMatutino("7:00");
		wrapper.setFrequenciaMatutino("Presente");
		wrapper.setSaidaMatutino("12:00");
		lista.add(wrapper);
		
		for (int i = 2; i <= 31; i++) {
			wrapper = new BoletimFrequenciaWrapper();
			wrapper.setDia(i);
			wrapper.setEntradaVespertino("13:00");
			wrapper.setFrequenciaVespertino("Outro");
			wrapper.setSaidaVespertino("17:00");
			lista.add(wrapper);
		}
	}
	
	private Map<String, Object> getBoletimFrequenciaHeader() {
		BoletimFrequenciaHeaderWrapper wrapper = new BoletimFrequenciaHeaderWrapper();
		wrapper.setDataNascimento(UtilDate.getDataFormatada(new Date(), Constantes.DD_MM_YYYY));
		wrapper.setMatricula("123");
		wrapper.setNomeAluno("Charlles Bandeira de Oliveira");
		wrapper.setOcorrencias("NOME DA INSTITUIÇÃO"
				+"INFORMAÇÕES DA ESCOLA UA: 004 – Coordenação Regional de Ensino do Gama"
				+"Código Escola: 990200000027 – CENTRO DE ENSINO FUNDAMENTAL 03 DO GAMA"
				+"BOLETIM DE FREQUÊNCIA	MÊS/ANO REFERÊNCIA: OUTUBRO/2013");
		wrapper.setResponsavelAluno("Givanilda Bandeira Maciel");
		wrapper.setSala("32");
		wrapper.setSerie("1 ano");
		wrapper.setTurma("B");
		wrapper.setTurno(Turno.MANHA.getDescricao());
		
		Map<String, Object> parametros = new HashMap<String, Object>();
		
		parametros.put("dataNascimento", wrapper.getDataNascimento());
		parametros.put("matricula", wrapper.getMatricula());
		parametros.put("nomeAluno", wrapper.getNomeAluno());
		parametros.put("ocorrencias", wrapper.getOcorrencias());
		parametros.put("responsavelAluno", wrapper.getResponsavelAluno());
		parametros.put("sala", wrapper.getSala());
		parametros.put("serie", wrapper.getSerie());
		parametros.put("turma", wrapper.getTurma());
		parametros.put("turno", wrapper.getTurno());
		
		return parametros;
	}
	
	private Map<String, Object> getHeader() {
		Map<String, Object> parametros = new HashMap<String, Object>();
		for (Header header : Header.values()) {
			if (header.equals(Header.NOME_RELATORIO)) {
				parametros.put(header.getParametro(), "BOLETIM DE FREQUÊNCIA");
			}
		}
		return parametros;
	}
	
}
