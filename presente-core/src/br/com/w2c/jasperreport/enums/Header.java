package br.com.w2c.jasperreport.enums;

/**
 * 
 * @author charlles
 * @since 27/10/2013
 */
public enum Header {

	ESCOLA_COD("ESCOLA_COD"), 
	ESCOLA_NOME("ESCOLA_NOME"),
	ESCOLA_UA("ESCOLA_UA"),
	NOME_RELATORIO("NOME_RELATORIO"),
	DATA_INICIO("DATA_INICIO"),
	DATA_FIM("DATA_FIM"),
	ANO("ANO"),
	NOME_ALUNO("NOME_ALUNO"),
	MATRICULA("MATRICULA"),
	SERIE("SERIE"),
	TURMA("TURMA"),
	TURNO("TURNO"),
	SALA("SALA"),
	BOLSISTA("BOLSISTA"), 
	ID_OCORRENCIA("ID_OCORRENCIA"),
	ID_TIPO_OCORRENCIA("ID_TIPO_OCORRENCIA"), 
	MATRICULAS("MATRICULAS"), 
	ID_OCORRENCIAS("ID_OCORRENCIAS");
	
	private String parametro;
	
	Header(String parametro) {
		this.parametro = parametro;
	}
	
	public String getParametro() {
		return parametro;
	}
	
}

