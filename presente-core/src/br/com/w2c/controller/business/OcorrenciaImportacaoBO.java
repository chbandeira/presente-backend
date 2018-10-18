package br.com.w2c.controller.business;

import static br.com.chbandeira.utilitario.Util.isNuloOuVazio;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.chbandeira.utilitario.Util;
import br.com.chbandeira.utilitario.UtilDate;
import br.com.chbandeira.utilitario.UtilEmail;
import br.com.w2c.exception.NegocioException;
import br.com.w2c.model.dao.OcorrenciaImportacaoDAO;
import br.com.w2c.model.domain.Aluno;
import br.com.w2c.model.domain.Matricula;
import br.com.w2c.model.domain.OcorrenciaImportacao;
import br.com.w2c.model.domain.Responsavel;
import br.com.w2c.model.domain.Sala;
import br.com.w2c.model.domain.Serie;
import br.com.w2c.model.domain.Turma;
import br.com.w2c.model.enums.Turno;
import br.com.w2c.util.KeyValue;
import br.com.w2c.exception.AplicacaoException;

/**
 * Layout: Matricula;Aluno;Série;Turma;Turno;Sala;Data Nascimento;Ano Letivo;Bolsista;Responsável;E-mail Responsável;Celular Responsável;Telefone Responsável
 * @author charlles
 * @since 02/10/2013
 */
@Component
public class OcorrenciaImportacaoBO extends BaseBO<OcorrenciaImportacao> {

	private static final String ISO_8859_1 = "ISO-8859-1";
	@SuppressWarnings("unused")
	private static final String UTF_8 = "UTF-8";
	
	private static Logger log = LogManager.getLogger();
	
	@Autowired
	private OcorrenciaImportacaoDAO ocorrenciaImportacaoDAO;
	@Autowired
	private MatriculaBO matriculaBO;

	public KeyValue importarDados(InputStream inputStream) throws NegocioException, AplicacaoException {
		try {
			return importarDadosStream(inputStream);
		} catch (IOException e) {
			addMensagem("MSG022");
			validarMensagens();
		}
		return null;
	}

	@SuppressWarnings("unused")
	@Deprecated
	private List<Matricula> getMatriculas(InputStream inputStream) throws IOException {
		InputStreamReader isr = new InputStreamReader(inputStream);
		BufferedReader br = new BufferedReader(isr);

		List<Matricula> matriculas = new ArrayList<Matricula>();
		String line = br.readLine();
		while (line != null) {
			String[] strings = line.split(";");
			
			Matricula matricula = new Matricula();
			matricula.setMatricula(getMatricula(strings));
			
			Aluno aluno = new Aluno();
			aluno.setNome(getNome(strings));
			matricula.setAluno(aluno);
			
			Serie serie = new Serie();
			serie.setDescricao(getSerie(strings));
			matricula.setSerie(serie);
			
			Turma turma = new Turma();
			turma.setDescricao(getTurma(strings));
			matricula.setTurma(turma);
			
			Turno turno = getTurno(getTurno(strings));
			matricula.setTurno(turno);
			
			matriculas.add(matricula);
			
			line = br.readLine();
		}
		
		return matriculas;
	}

	private Turno getTurno(String string) {
		Turno turno = null; 
		if (!isNuloOuVazio(string)) {
			if (string.equalsIgnoreCase("MATUTINO") || string.equalsIgnoreCase("MANHA")) {
				turno = Turno.MANHA;
			} else if (string.equalsIgnoreCase("VESPERTINO") || string.equalsIgnoreCase("TARDE")) {
				turno = Turno.TARDE;
			} else if (string.equalsIgnoreCase("NOTURNO") || string.equalsIgnoreCase("NOITE")) {
				turno = Turno.NOITE;
			}
		}
		return turno;
	}
	
	@Transactional(propagation=Propagation.REQUIRES_NEW, readOnly=false)
	private KeyValue importarDadosStream(InputStream is) throws IOException, NegocioException, AplicacaoException {
		
		int qtdRestanteLimite = matriculaBO.obterQtdRestanteLimiteMatriculas();
		
		List<OcorrenciaImportacao> matriculas = new ArrayList<OcorrenciaImportacao>();
		List<OcorrenciaImportacao> ocorrencias = new ArrayList<OcorrenciaImportacao>();
		
		List<String> readLines = IOUtils.readLines(is, ISO_8859_1);
		
		for (int i = 0; i < readLines.size(); i++) {
			String[] strings = readLines.get(i).split(";");
			OcorrenciaImportacao oi = new OcorrenciaImportacao();
			if (qtdRestanteLimite > 0 && strings.length == 13) {
				
				oi = converterParaOcorrenciaImportacao(strings);
				
				int search = Collections.binarySearch(matriculas, oi, new Comparator<OcorrenciaImportacao>() {
					@Override
					public int compare(OcorrenciaImportacao o1, OcorrenciaImportacao o2) {
						if (o1.getMatricula().compareTo(o2.getMatricula()) == 0
								&& o1.getAnoLetivo().compareTo(o2.getAnoLetivo()) == 0) {
							return 0;
						}
						return -1;
					}
				});
				if (search >= 0) {
					OcorrenciaImportacao oiRepetido = matriculas.get(search);
					ocorrencias.add(oiRepetido);
					ocorrencias.add(oi);
					matriculas.remove(oiRepetido);
				} else {
					matriculas.add(oi);
					qtdRestanteLimite--;
				}
			} else {
				oi = converterParaOcorrenciaImportacao(strings);
				
				ocorrencias.add(oi);
			}
		}
		
		List<Matricula> listaMatricula = new ArrayList<Matricula>();
		for (OcorrenciaImportacao oi : matriculas) {
			Matricula matricula = converterParaMatricula(oi);
			listaMatricula.add(matricula);	
		}
		
		salvandoOcorrencias(ocorrencias);
		int ocorrenciasSalvas = ocorrencias.size();
		
		int matriculasQueSeraoSalvas = listaMatricula.size();
		int matriculasSalvas = matriculaBO.matricularLote(listaMatricula);
		int maisOcorrencias = matriculasQueSeraoSalvas - matriculasSalvas;
		ocorrenciasSalvas += maisOcorrencias;
		KeyValue keyValue = new KeyValue(String.valueOf(matriculasSalvas), String.valueOf(ocorrenciasSalvas));
		return keyValue;
	}

	private OcorrenciaImportacao converterParaOcorrenciaImportacao(String[] strings) {
		OcorrenciaImportacao oi = new OcorrenciaImportacao();
		oi.setMatricula(getMatricula(strings));
		oi.setNome(getNome(strings));
		oi.setSerie(getSerie(strings));
		oi.setTurma(getTurma(strings));
		String turno = getTurno(strings);
		oi.setTurno(isNuloOuVazio(turno) ? null : getTurno(turno).name());
		oi.setSala(getSala(strings));
		oi.setDataNascimento(getDataNascimento(strings));
		oi.setAnoLetivo(getAnoLetivo(strings));
		oi.setBolsista(getBolsista(strings));
		oi.setResponsavel(getResponsavel(strings));
		oi.setResponsavelEmail(getResponsavelEmail(strings));
		oi.setResponsavelCelular(getResponsavelCelular(strings));
		oi.setResponsavelTelefone(getResponsavelTelefone(strings));
		return oi;
	}

	private String getResponsavelTelefone(String[] strings) {
		if (strings.length > 12) {
			return strings[12];
		} else {
			return "";
		}
	}

	private String getResponsavelCelular(String[] strings) {
		if (strings.length > 11) {
			return strings[11];
		} else {
			return "";
		}
	}

	private String getResponsavelEmail(String[] strings) {
		if (strings.length > 10) {
			return strings[10];
		} else {
			return "";
		}
	}

	private String getResponsavel(String[] strings) {
		if (strings.length > 9) {
			return strings[9];
		} else {
			return "";
		}
	}

	private Boolean getBolsista(String[] strings) {
		if (strings.length > 8 && !isNuloOuVazio(strings[8])) {
			return Boolean.valueOf(strings[8]);
		} else {
			return false;
		}
	}

	private Integer getAnoLetivo(String[] strings) {
		if (strings.length > 7 && !isNuloOuVazio(strings[7])) {
			return Integer.valueOf(strings[7]);
		} else {
			return null;
		}
	}

	private Date getDataNascimento(String[] strings) {
		if (strings.length > 6 && !isNuloOuVazio(strings[6])) {
			try {
				return UtilDate.getStringToDate(strings[6]);
			} catch (ParseException e) {
				return null;
			}
		} else {
			return null;
		}
	}
	
	private String getSala(String[] strings) {
		if (strings.length > 5) {
			return strings[5];
		} else {
			return "";
		}
	}

	private String getTurno(String[] strings) {
		if (strings.length > 4) {
			return strings[4];
		} else {
			return "";
		}
	}

	private String getTurma(String[] strings) {
		if (strings.length > 3) {
			return strings[3];
		} else {
			return "";
		}
	}

	private String getSerie(String[] strings) {
		if (strings.length > 2) {
			return strings[2];
		} else {
			return "";
		}
	}

	private String getNome(String[] strings) {
		if (strings.length > 1) {
			return strings[1];
		} else {
			return "";
		}
	}

	private String getMatricula(String[] strings) {
		if (strings.length > 0) {
			return strings[0];
		} else {
			return "";
		}
	}

	private Matricula converterParaMatricula(OcorrenciaImportacao oi) {
		Aluno aluno = new Aluno(oi.getNome());
		aluno.setDataNascimento(oi.getDataNascimento());
		
		Responsavel responsavel = new Responsavel();
		responsavel.setNome(oi.getResponsavel());
		responsavel.setEmail(oi.getResponsavelEmail());
		responsavel.setTelefoneCelular(oi.getResponsavelCelular());
		responsavel.setTelefoneFixo(oi.getResponsavelTelefone());
		
		Matricula matricula = new Matricula();
		matricula.setMatricula(oi.getMatricula());
		matricula.setAluno(aluno);
		matricula.setSerie(isNuloOuVazio(oi.getSerie()) ? null : new Serie(oi.getSerie()));
		matricula.setTurma(isNuloOuVazio(oi.getTurma()) ? null : new Turma(oi.getTurma()));
		matricula.setSala(isNuloOuVazio(oi.getSala()) ? null : new Sala(oi.getSala()));
		matricula.setTurno(getTurno(oi.getTurno()));
		matricula.setAnoLetivo(oi.getAnoLetivo());
		matricula.setBolsista(oi.getBolsista());
		matricula.setResponsavel(responsavel);
		matricula.setEnviarEmailRegistro(
				!Util.isNuloOuVazio(oi.getResponsavelEmail()) && UtilEmail.isEmailValido(oi.getResponsavelEmail()) ? 
				Boolean.TRUE : Boolean.FALSE);
		matricula.setEnviarSmsRegistro(
				!Util.isNuloOuVazio(oi.getResponsavelCelular()) && oi.getResponsavelCelular().length()==10 ? 
				Boolean.TRUE : Boolean.FALSE);
		matricula.setAtivo(Boolean.TRUE);
		
		return matricula;
	}

	@Transactional(propagation=Propagation.REQUIRED, readOnly=false)
	private void salvandoOcorrencias(List<OcorrenciaImportacao> ocorrencias) {
		for (OcorrenciaImportacao ocorrenciaImportacao : ocorrencias) {
			ocorrenciaImportacaoDAO.salvar(ocorrenciaImportacao);
		}
	}

	@SuppressWarnings("unused")
	private void imprimirSysout(List<OcorrenciaImportacao> matriculas, List<OcorrenciaImportacao> ocorrencias) {
//			System.out.println(file);
		System.out.println("Matriculas realizadas: "+matriculas.size());
		System.out.println("Ocorrencias: "+ocorrencias.size());
		for (OcorrenciaImportacao oi : ocorrencias) {
			System.out.println(oi);
		}
		System.out.println("================");
	}

	public void excluir(OcorrenciaImportacao ocorrencia) {
		ocorrenciaImportacaoDAO.excluir(ocorrencia);
	}

	public List<OcorrenciaImportacao> obterTodos() throws AplicacaoException {
		return ocorrenciaImportacaoDAO.obterTodos();
	}

	public void excluirTodos() throws NegocioException, AplicacaoException {
		limparMensagens();
		List<OcorrenciaImportacao> todos = obterTodos();
		if (isNuloOuVazio(todos)) {
			addMensagem("MSG024");
			validarMensagens();
		} else {
			for (OcorrenciaImportacao oi : todos) {
				ocorrenciaImportacaoDAO.excluir(oi);
			}
		}
	}

	@Transactional(propagation=Propagation.REQUIRED, readOnly=false)
	public void salvarCorrecao(OcorrenciaImportacao ocorrencia) throws NegocioException, AplicacaoException {
		try {
			Matricula matricula = converterParaMatricula(ocorrencia);
			matriculaBO.matricular(matricula);
			ocorrenciaImportacaoDAO.excluir(ocorrencia);
		} catch (NegocioException e) {
			throw e;
		}
	}

	public void salvar(Matricula matricula) {
		OcorrenciaImportacao oi = converterParaOcorrencia(matricula);
		ocorrenciaImportacaoDAO.salvar(oi);
	}

	private OcorrenciaImportacao converterParaOcorrencia(Matricula matricula) {
		Aluno aluno = isNuloOuVazio(matricula.getAluno()) ? new Aluno() : matricula.getAluno();
		Responsavel responsavel = isNuloOuVazio(matricula.getResponsavel()) ? new Responsavel() : matricula.getResponsavel();
		
		OcorrenciaImportacao oi = new OcorrenciaImportacao();
		oi.setMatricula(matricula.getMatricula());
		oi.setNome(aluno.getNome());
		oi.setSerie(isNuloOuVazio(matricula.getSerie()) ? null : matricula.getSerie().getDescricao());
		oi.setTurma(isNuloOuVazio(matricula.getTurma()) ? null : matricula.getTurma().getDescricao());
		oi.setTurno(isNuloOuVazio(matricula.getTurno()) ? null : matricula.getTurno().name());
		oi.setSala(isNuloOuVazio(matricula.getSala()) ? null : matricula.getSala().getDescricao());
		oi.setDataNascimento(aluno.getDataNascimento());
		oi.setAnoLetivo(matricula.getAnoLetivo());
		oi.setBolsista(matricula.getBolsista());
		oi.setResponsavel(responsavel.getNome());
		oi.setResponsavelEmail(responsavel.getEmail());
		oi.setResponsavelCelular(responsavel.getTelefoneCelular());
		oi.setResponsavelTelefone(responsavel.getTelefoneFixo());
		
		return oi;
	}

	public InputStream criarArquivo() throws NegocioException, AplicacaoException {
		limparMensagens();
		InputStream is = null;
		
		try {
			List<OcorrenciaImportacao> ocorrencias = ocorrenciaImportacaoDAO.obterTodos();
			
			if (isNuloOuVazio(ocorrencias)) {
				addMensagem("MSG050");
				throw new NegocioException(getMensagens());
			}
			
			File arquivo = new File("ocorrencias.txt");  
			FileWriter fw = new FileWriter(arquivo);  
			BufferedWriter bw = new BufferedWriter(fw);  

			for (int i = 0; i < ocorrencias.size(); i++) {
				OcorrenciaImportacao oi = ocorrencias.get(i);
				bw.write(oi.toStringExporter());
				bw.newLine();
			}
			
			bw.flush();  
			bw.close(); 
			is = new FileInputStream(arquivo);
			
		} catch (FileNotFoundException e) {
			log.error(e.getMessage(), e);
			throw new AplicacaoException(e);
		} catch (IOException e) {
			log.error(e.getMessage(), e);
			throw new AplicacaoException(e);
		}
		
		return is;
	}

	public List<OcorrenciaImportacao> obterUltimosAtualizados(Date dataHoraUltimaAtualizacao) throws AplicacaoException {
		return ocorrenciaImportacaoDAO.obterUltimosAtualizados(dataHoraUltimaAtualizacao, OcorrenciaImportacao.class);
	}
	
//	public static void main(String[] args) {
//		String[] files = new String[]{"cef05.txt","cef03.txt","cem02.txt"};
//		for (String file : files) {
//			importarDados(file);
//		}
//	}
	
}
