package br.com.w2c.jasperreport.util;

/**
 * 
 * @author charlles
 * @since 04/12/2013
 */
public interface ConstantesRelatorio {
	
	public static final String EXTENSAO_JASPER = ".jasper";
	public static final String EXTENSAO_PDF = ".pdf";
	public static final Long ID_BOLETIM_FREQUENCIA = 1L;
	public static final Long ID_RELATORIO_FALTAS = 2L;
	public static final Long ID_RELATORIO_FREQUENCIA_POR_TURMA = 3L;
	public static final Long ID_RELATORIO_PRESENCA = 4L;
	public static final Long ID_RELATORIO_OCORRENCIA = 5L;
	public static final Long ID_RELATORIO_FICHA_DISCIPLINAR = 6L;
	public static final Long ID_RELATORIO_OCORRENCIA_DUPLICADO = 7L;
	
	public static final String JASPER_RELATORIO_FALTAS = "RelatorioFaltas";
	public static final String JASPER_RELATORIO_PRESENCA = "Presenca";
	public static final String JASPER_RELATORIO_ONLINE_FALTAS = "RelatorioOnlineFaltas";
	public static final String JASPER_RELATORIO_OCORRENCIA = "Ocorrencias";
	public static final String JASPER_RELATORIO_FICHA_DISCIPLINAR = "FichaDisciplinar";
	public static final String JASPER_RELATORIO_OCORRENCIA_DUPLICADO = "Ocorrencias2";
	
	public static final String JASPER_RELATORIO_FALTAS_PDF = JASPER_RELATORIO_FALTAS + EXTENSAO_PDF;
	public static final String JASPER_RELATORIO_FALTAS_JASPER = JASPER_RELATORIO_FALTAS + EXTENSAO_JASPER;
	public static final String JASPER_RELATORIO_ONLINE_FALTAS_JASPER = JASPER_RELATORIO_ONLINE_FALTAS + EXTENSAO_JASPER;
	public static final String JASPER_RELATORIO_PRESENCA_JASPER = JASPER_RELATORIO_PRESENCA + EXTENSAO_JASPER;
	public static final String JASPER_RELATORIO_OCORRENCIA_JASPER = JASPER_RELATORIO_OCORRENCIA + EXTENSAO_JASPER;
	public static final String JASPER_RELATORIO_FICHA_DISCIPLINAR_JASPER = JASPER_RELATORIO_FICHA_DISCIPLINAR + EXTENSAO_JASPER;
	public static final String JASPER_RELATORIO_OCORRENCIA_DUPLICADO_JASPER = JASPER_RELATORIO_OCORRENCIA_DUPLICADO + EXTENSAO_JASPER;
	
}
