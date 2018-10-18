package br.com.w2c.util;

/**
 * 
 * @author charlles
 * @since 15/09/2013
 */
public interface Constantes {
	
	/**
	 * Páginas xhtml/jsf
	 */
	public static final String PAGE_CADASTRO_ALUNO = "/views/aluno/cadastroAluno.jsf";
	public static final String PAGE_CONSULTA_ALUNO = "/views/aluno/consultaAluno.jsf";
	public static final String PAGE_CADASTRO_SERIE = "/views/serie/cadastroSerie.jsf";
	public static final String PAGE_CONSULTA_SERIE = "/views/serie/consultaSerie.jsf";
	public static final String PAGE_CADASTRO_TURMA = "/views/turma/cadastroTurma.jsf";
	public static final String PAGE_CONSULTA_TURMA = "/views/turma/consultaTurma.jsf";
	public static final String PAGE_CADASTRO_SALA = "/views/sala/cadastroSala.jsf";
	public static final String PAGE_CONSULTA_SALA = "/views/sala/consultaSala.jsf";
	public static final String PAGE_REGISTRO_ENTRADA = "/views/registro/registroEntrada.jsf";
	public static final String PAGE_REGISTRO_SAIDA = "/views/registro/registroSaida.jsf";
	public static final String PAGE_CONSULTA_PRESENCA = "/views/presenca/consultaPresenca.jsf";
	public static final String PAGE_ERROR_APP = "/views/error/errorApp.jsf";
	public static final String PAGE_GERAR_CODIGO_BARRAS = "/views/barcode/geradorBarcode.jsf";
	public static final String PAGE_IMPORTAR_DADOS_ANTIGOS = "/views/importacao/importacaoDados.jsf";
	public static final String PAGE_OCORRENCIAS = "/views/importacao/ocorrencia.jsf";
	public static final String PAGE_CADASTRO_OCORRENCIA_IMPORTACAO = "/views/importacao/cadastroOcorrencia.jsf";
	public static final String PAGE_CADASTRO_USUARIO = "/views/usuario/cadastroUsuario.jsf";
	public static final String PAGE_CONSULTA_USUARIO = "/views/usuario/consultaUsuario.jsf";
	public static final String PAGE_BAIXAR_LOG = "/views/log/baixarLog.jsf";
	public static final String PAGE_ALTERAR_SENHA = "/views/usuario/alterarSenha.jsf";
	public static final String PAGE_PARAMETRO_GERAL = "/views/parametro/parametrosGerais.jsf";
	public static final String PAGE_CONFIGURACAO_ESCOLA = "/views/configuracao/configuracao.jsf";
	public static final String PAGE_CADASTRO_CALENDARIO_ESCOLAR = "/views/calendario/cadastroCalendario.jsf";
	public static final String PAGE_CONSULTA_CALENDARIO_ESCOLAR = "/views/calendario/consultaCalendario.jsf";
	public static final String PAGE_RELATORIO_FALTAS = "/views/relatorio/relatorioFaltas.jsf";
	public static final String PAGE_RELATORIO_PRESENCA = "/views/relatorio/relatorioPresenca.jsf";
	public static final String PAGE_RELATORIO_OCORRENCIA = "/views/relatorio/relatorioOcorrencias.jsf";
	public static final String PAGE_RELATORIO_FICHA_DISCIPLINAR = "/views/relatorio/relatorioFichaDisciplinar.jsf";
	public static final String PAGE_ERROR_RELATORIO = "/views/error/errorRelatorio.jsf";
	public static final String PAGE_MANUAL_COMPLETO = "/views/manual/completo.jsf";
	public static final String PAGE_CADASTRO_DISCIPLINA = "/views/disciplina/cadastroDisciplina.jsf";
	public static final String PAGE_CONSULTA_DISCIPLINA = "/views/disciplina/consultaDisciplina.jsf";
	public static final String PAGE_CADASTRO_OCORRENCIA = "/views/ocorrencia/cadastroOcorrencia.jsf";
	public static final String PAGE_CONSULTA_OCORRENCIA = "/views/ocorrencia/consultaOcorrencia.jsf";
	public static final String PAGE_CADASTRO_TIPO_OCORRENCIA = "/views/tipoOcorrencia/cadastroTipoOcorrencia.jsf";
	public static final String PAGE_CONSULTA_TIPO_OCORRENCIA = "/views/tipoOcorrencia/consultaTipoOcorrencia.jsf";
	
	/**
	 * Patterns
	 */
	public static final String HH_MM_SS = "HH:mm:ss";
	public static final String YYYY = "yyyy";
	public static final String MM_YYYY = "MM/yyyy";
	public static final String MM_YYYY_DB = "yyyy-MM";
	public static final String DD_MM_YYYY = "dd/MM/yyyy";
	public static final String DD_MM_YYYY_DB = "yyyy-MM-dd";
	public static final String DD_MM_YYYY_HH_MM = "dd/MM/yyyy HH:mm";
	public static final String DD_MM_YYYY_HH_MM_SS = "dd/MM/yyyy HH:mm:ss";
	public static final String DD_MM_YYYY_EEEE = "dd/MM/yyyy, EEEE";
	
	/**
	 * Arquivo de código de barras
	 */
	public static final String DIRETORIO_CODIGO_BARRAS = "barcodes.pdf";
	
	/**
	 * Sessions
	 */
	public static final String SESSION_USUARIO = "SESSION_USUARIO";
	public static final String SESSION_VERSAO_SISTEMA = "SESSION_VERSAO_SISTEMA";
	public static final String SESSION_CONFIGURACAO_ESCOLA = "SESSION_CONFIGURACAO_ESCOLA";
	public static final String SESSION_VERSAO_BASE_DADOS = "SESSION_VERSAO_BASE_DADOS";
	public static final String SESSION_LICENCA = "SESSION_LICENCA";
	public static final String SESSION_NOME_EMPRESA = "SESSION_NOME_EMPRESA";
	public static final String SESSION_EMAIL_CONTATO_ADM = "SESSION_EMAIL_CONTATO_ADM";
	public static final String SESSION_ERROR_RELATORIO = "SESSION_ERROR_RELATORIO";
	public static final String SESSION_JRE_VERSION = "SESSION_JRE_VERSION";
	public static final String SESSION_JRE_IMPL_VERSION = "SESSION_JRE_IMPL_VERSION";
	public static final String SESSION_OS = "SESSION_OS";
	public static final String SESSION_QTD_LIMITE_LICENCA = "SESSION_QTD_LIMITE_LICENCA";
	public static final String SESSION_NOME_CHAVE_ESCOLA = "SESSION_NOME_CHAVE_ESCOLA";
	public static final String SESSION_ULTIMA_SINCRONIZACAO_WEB = "SESSION_ULTIMA_SINCRONIZACAO_WEB";
	
	/**
	 * Parametros de configuração
	 */
	public static final String VERSAO_BASE_DADOS = "VERSAO_BASE_DADOS";
	public static final String NOME_EMPRESA = "NOME_EMPRESA";
	public static final String EMAIL_CONTATO_ADM = "EMAIL_CONTATO_ADM";
	public static final String ULTIMA_ATUALIZACAO_WEB = "ULTIMA_ATUALIZACAO_WEB";
	
	
	/**
	 * Perfis
	 */
	public static final String ANONYMOUS_USER = "anonymousUser";      
	public static final String ADMIN = "ADMIN";      
	public static final String DIRETOR = "DIRET";    
	public static final String COORDENADOR = "COORD";
	public static final String COMUM = "COMUM";
	public static final String RESPONSAVEL = "RESPO";
	
	/**
	 * @see path.properties
	 */
	public static final String PATH_LOG_SERVIDOR = "log.servidor";
	public static final String PATH_JRXML = "jrxml";
	public static final String PATH_IMG = "img";
	public static final String PATH_PADRAO = "padrao";
	public static final String PATH_RELATORIO = "relatorio";
	
	public static final String TYPE_APPLICATION_PDF = "application/pdf";
	public static final String TYPE_APPLICATION_TEXT = "text/plain";
	
	public static final String MAVEN_POM_LOCAL = "/META-INF/maven/br.com.w2c";
	public static final String MAVEN_POM = "pom.properties";
	
	public static final String DDI_LOCAL = "55";
	public static final String PATH_FOTO = "foto";
	
	public static final int ALTURA_FOTO = 800;
	public static final int LARGURA_FOTO = 600;
	public static final int QUANTIDADE_MAX_LICENCA = 100000;
	
}
