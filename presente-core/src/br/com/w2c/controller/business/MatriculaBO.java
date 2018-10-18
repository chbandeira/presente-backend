package br.com.w2c.controller.business;

import static br.com.chbandeira.utilitario.Util.isNuloOuVazio;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.chbandeira.utilitario.Util;
import br.com.chbandeira.utilitario.UtilDate;
import br.com.w2c.exception.AplicacaoException;
import br.com.w2c.exception.NegocioException;
import br.com.w2c.model.dao.MatriculaDAO;
import br.com.w2c.model.domain.Matricula;
import br.com.w2c.model.domain.Sala;
import br.com.w2c.model.domain.Serie;
import br.com.w2c.model.domain.Turma;
import br.com.w2c.model.domain.Usuario;
import br.com.w2c.model.enums.TipoHistoricoAlteracao;
import br.com.w2c.model.wrapper.MatriculaWrapper;
import br.com.w2c.util.Constantes;

/**
 * 
 * @author charlles
 * @since 15/09/2013
 */
@Component
public class MatriculaBO extends BaseBO<Matricula> {
	
	@Autowired
	private MatriculaDAO matriculaDAO;
	@Autowired
	private OcorrenciaImportacaoBO ocorrenciaImportacaoBO;
	
	@Autowired
	private AlunoBO alunoBO;
	@Autowired
	private SalaBO salaBO;
	@Autowired
	private SerieBO serieBO;
	@Autowired
	private TurmaBO turmaBO;
	@Autowired
	private ParametroGeralBO parametroGeralBO;
	@Autowired
	private ResponsavelBO responsavelBO;
	@Autowired
	private HistoricoAlteracaoBO historicoAlteracaoBO;

	private void validarLimiteLicenca() throws NegocioException, AplicacaoException {
		Long qtdAtivos = matriculaDAO.obterQtdAtivos();
		if (qtdAtivos.intValue() >= Constantes.QUANTIDADE_MAX_LICENCA) {
			addMensagem("MSG053");
			validarMensagens();
		}
	}

	public void validarCamposObrigatorios(Matricula matricula) throws NegocioException, AplicacaoException {
		if (isNuloOuVazio(matricula)) {
			lancarMensagemSuportePadrao();
		}
		else if (isNuloOuVazio(matricula.getMatricula())
				|| isNuloOuVazio(matricula.getAluno().getNome())) {
			
			addMensagem("MSG058");
		}
	}
	
	public void validarExisteCadastrado(Matricula matricula) throws NegocioException, AplicacaoException {
		if (!isNuloOuVazio(matricula.getMatricula()) && existe(matricula)) {
			addMensagem("MSG055");
		}
		validarMensagens();
	}

	/**
	 * Verifica se existe matricula cadastrada, sem ser a própria matricula (quando alteração)
	 * @param matricula
	 * @return
	 * @throws AplicacaoException 
	 */
	public boolean existe(Matricula matricula) throws AplicacaoException {
		if (Util.isNuloOuZero(matricula.getAnoLetivo())) {
			matricula.setAnoLetivo(UtilDate.getAnoAtual());
		}
		Matricula matriculaObtida = matriculaDAO.obterRepetidoComMesmoAno(matricula);
		return !isNuloOuVazio(matriculaObtida);
	}
	
	@Transactional(propagation=Propagation.REQUIRED, readOnly=false)
	public void matricular(Matricula matricula) throws NegocioException, AplicacaoException {
		//TODO fazer verificação sobre atualização de matricula
		/*
		 * verificar possibilidade de criar uma matricula duplicada com ano letivo novo e 
		 * guardar a matricula velha para futura implementação
		 * para alterações de matricula
		 */
		if (isNuloOuVazio(matricula.getId())) {
			validarLimiteLicenca();
		}
		validarCamposObrigatorios(matricula);
		validarExisteCadastrado(matricula);
		validarMensagens();
		parametroGeralBO.setAnoLetivo(matricula);
		if (isNuloOuVazio(matricula.getData())) {
			matricula.setData(UtilDate.getDataAtual());
		}

		salvarSala(matricula);
		salvarTurma(matricula);
		salvarSerie(matricula);
		alunoBO.salvar(matricula.getAluno());
		responsavelBO.salvar(matricula.getResponsavel(), matricula.getEnviarEmailRegistro(), matricula.getEnviarSmsRegistro());
		
		TipoHistoricoAlteracao tipo = historicoAlteracaoBO.getTipo(matricula.getId());
		
		trimString(matricula.getMatricula());
		matricula.setAtivo(Boolean.TRUE);
		matriculaDAO.salvar(matricula);
		
		historicoAlteracaoBO.salvar(matricula, tipo);
	}
	
	/**
	 * Não há validações
	 * @param matriculas
	 * @throws NegocioException
	 * @throws AplicacaoException 
	 */
	@Transactional(propagation=Propagation.REQUIRED, readOnly=false)
	public int matricularLote(List<Matricula> matriculas) throws NegocioException, AplicacaoException {
		int matriculasSalvas = 0;
		for (Matricula matricula : matriculas) {
			
			if (existe(matricula)) {
				ocorrenciaImportacaoBO.salvar(matricula);
			} else {
				parametroGeralBO.setAnoLetivo(matricula);
				matricula.setData(UtilDate.getDataAtual());
				
				salvarSala(matricula);
				salvarTurma(matricula);
				salvarSerie(matricula);
				alunoBO.salvar(matricula.getAluno());
				responsavelBO.salvar(matricula.getResponsavel(), matricula.getEnviarEmailRegistro(), matricula.getEnviarSmsRegistro());
				
				matricula.setAtivo(Boolean.TRUE);
				matriculaDAO.salvar(matricula);
				matriculasSalvas++;
			}
			
		}
		return matriculasSalvas;
	}

	private void salvarSala(Matricula matricula) throws AplicacaoException {
		if (!isNuloOuVazio(matricula)
				&& !isNuloOuVazio(matricula.getSala()) 
				&& !isNuloOuVazio(matricula.getSala().getDescricao())) {
			
			Sala sala = salaBO.getSalaDAO().obterRepetido(matricula.getSala());
			if (!isNuloOuVazio(sala) && !isNuloOuVazio(sala.getId())) {
				matricula.setSala(sala);
			} else {
				matricula.getSala().setDataUltimaAtualizacao(null);
				salaBO.getSalaDAO().salvar(matricula.getSala());
			}
		}
	}

	private void salvarTurma(Matricula matricula) throws AplicacaoException {
		if (!isNuloOuVazio(matricula)
				&& !isNuloOuVazio(matricula.getTurma()) 
				&& !isNuloOuVazio(matricula.getTurma().getDescricao())) {
			
			Turma turma = turmaBO.getTurmaDAO().obterRepetido(matricula.getTurma());
			if (!isNuloOuVazio(turma) && !isNuloOuVazio(turma.getId())) {
				matricula.setTurma(turma);
			} else {
				matricula.getTurma().setDataUltimaAtualizacao(null);
				turmaBO.getTurmaDAO().salvar(matricula.getTurma());
			}
		}
	}

	private void salvarSerie(Matricula matricula) throws AplicacaoException {
		if (!isNuloOuVazio(matricula)
				&& !isNuloOuVazio(matricula.getSerie()) 
				&& !isNuloOuVazio(matricula.getSerie().getDescricao())) {
			
			Serie serie = serieBO.getSerieDAO().obterRepetido(matricula.getSerie());
			if (!isNuloOuVazio(serie) && !isNuloOuVazio(serie.getId())) {
				matricula.setSerie(serie);
			} else {
				matricula.getSerie().setDataUltimaAtualizacao(null);
				serieBO.getSerieDAO().salvar(matricula.getSerie());
			}
		}
	}

	/**
	 * Necessário passar o ano letivo para consulta
	 * @param matricula
	 * @return
	 * @throws NegocioException
	 * @throws AplicacaoException 
	 */
	public Matricula obterPorMatricula(Matricula matricula) throws NegocioException, AplicacaoException {
		validarEntidade(matricula);
		if (Util.isNuloOuZero(matricula.getAnoLetivo())) {
			matricula.setAnoLetivo(UtilDate.getAnoAtual());
		}
		return matriculaDAO.obterPorMatriculaEAnoLetivo(matricula);
	}
	
	public List<Matricula> obterListaPorParametros(Matricula matricula) throws NegocioException, AplicacaoException {
		if (isNuloOuVazio(matricula) || isNuloOuVazio(matricula.getAluno())) {
			lancarMensagemSuportePadrao();
		}
		
		if (Util.isNuloOuZero(matricula.getAnoLetivo())) {
			return matriculaDAO.obterListaPorParametros(matricula);
		}
		else {
			return matriculaDAO.obterListaPorParametrosEAnoLetivo(matricula);
		}
	}
	
	@Transactional(propagation=Propagation.REQUIRED, readOnly=false)
	public void cancelarMatricula(Matricula matricula) throws NegocioException, AplicacaoException {
		validarEntidade(matricula);
		alunoBO.desativar(matricula.getAluno());
		desativar(matricula);
	}
	
	public void desativar(Matricula matricula) throws NegocioException, AplicacaoException {
		validarCamposParaExclusao(matricula);
		matricula = matriculaDAO.obter(Matricula.class, matricula.getId());
		validarCamposParaExclusao(matricula);
		validarMensagens();
		
		historicoAlteracaoBO.salvar(matricula, TipoHistoricoAlteracao.DESATIVACAO);
	
		matricula.setAtivo(Boolean.FALSE);
		matriculaDAO.salvar(matricula);
	}
	
	public String[] obterMatriculasPorPeriodo(Matricula matricula, Date periodoInicial, Date periodoFinal) throws NegocioException, AplicacaoException {
		validarEntidade(matricula);
		List<Matricula> matriculas;
		if (!isNuloOuVazio(periodoInicial) && !isNuloOuVazio(periodoFinal)) {
			matriculas = matriculaDAO.obterMatriculasPorPeriodo(matricula, periodoInicial, periodoFinal);
			
		} else if (!isNuloOuVazio(periodoInicial)) {
			matriculas = matriculaDAO.obterMatriculasPorPeriodoInicial(matricula, periodoInicial);
			
		} else if (!isNuloOuVazio(periodoFinal)) {
			matriculas = matriculaDAO.obterMatriculasPorPeriodoFinal(matricula, periodoFinal);
			
		} else if (Util.isNuloOuZero(matricula.getAnoLetivo())) {
			matriculas = matriculaDAO.obterListaPorParametrosOrdemData(matricula);
			
		} else {
			matriculas = matriculaDAO.obterListaPorParametrosEAnoLetivoOrdemData(matricula);
		}
		
		List<String> arrayMatriculas = new ArrayList<String>();
		for (Matricula m : matriculas) {
			arrayMatriculas.add(m.getMatricula());
		}
		String[] string = new String[arrayMatriculas.size()];
		arrayMatriculas.toArray(string);
		return string;
	}
	
	public boolean isAtingiuLimiteLicenca() throws AplicacaoException {
		return matriculaDAO.obterQtdAtivos().intValue() >= Constantes.QUANTIDADE_MAX_LICENCA;
	}
	
	public int obterQtdAtivos() throws AplicacaoException {
		return matriculaDAO.obterQtdAtivos().intValue();
	}
	
	public int obterQtdRestanteLimiteMatriculas() throws AplicacaoException {
		return Constantes.QUANTIDADE_MAX_LICENCA - obterQtdAtivos();
	}

	public List<Matricula> obterListaPorSugestaoNomeAluno(String nomeAluno) throws AplicacaoException {
		
		if (StringUtils.isNumeric(nomeAluno)) {
			return matriculaDAO.obterListaPorSugestaoMatricula(nomeAluno);
		}
		
		return matriculaDAO.obterListaPorSugestaoNomeAluno(nomeAluno);
	}
	
	public Matricula obterPorId(Long idMatricula) {
		return matriculaDAO.obter(Matricula.class, idMatricula);
	}
	
	public List<Matricula> obterPorTurma(Turma turma) throws AplicacaoException {
		return matriculaDAO.obterPorTurma(turma);
	}
	
	public List<Matricula> obterPorTurmaSerieAnoLetivo(Turma turma) throws AplicacaoException {
		if (turma.getSerie() != null) {
			turma.setSerie(serieBO.obterPorDescricao(turma.getSerie().getDescricao()));
			return matriculaDAO.obterPorTurmaSerieAnoLetivo(turma);
		}
		return matriculaDAO.obterPorTurmaAnoLetivo(turma);
	}
	
	public List<Matricula> obterPorUsuario(Usuario usuario) throws AplicacaoException {
		return matriculaDAO.obterPorUsuario(usuario);
	}
	
	public List<MatriculaWrapper> obterRelatorioOnlineFaltas(Date periodoInicial,
			Date periodoFinal, Matricula matricula) throws AplicacaoException, NegocioException {
		return matriculaDAO.obterRelatorioOnlineFaltas(periodoInicial, periodoFinal, matricula);
	}
	
	public List<Matricula> obterUltimosAtualizados(Date dataHoraUltimaAtualizacao) throws AplicacaoException {
		return matriculaDAO.obterUltimosAtualizados(dataHoraUltimaAtualizacao, Matricula.class);
	}
	
	public void salvarOnline(Matricula matricula) throws AplicacaoException, NegocioException {
		Matricula retorno = obterPorCriterioOnline(matricula);
		
		if (retorno == null) {
			retorno = new Matricula();
			retorno.setIdentificador(matricula.getIdentificador());
			retorno.setMatricula(matricula.getMatricula());
		}
		
		retorno.setAluno(alunoBO.obterPorCriterioOnline(matricula.getAluno()));
		retorno.setAnoLetivo(matricula.getAnoLetivo());
		retorno.setAtivo(matricula.getAtivo());
		retorno.setBolsista(matricula.getBolsista());
		retorno.setEnviarEmailRegistro(matricula.getEnviarEmailRegistro());
		retorno.setEnviarSmsRegistro(matricula.getEnviarSmsRegistro());
		retorno.setData(matricula.getData());
		if (matricula.getResponsavel() != null) {
			retorno.setResponsavel(responsavelBO.obterPorCriterioOnline(matricula.getResponsavel()));
		}
		if (matricula.getSala() != null) {
			retorno.setSala(salaBO.obterPorCriterioOnline(matricula.getSala()));
		}
		if (matricula.getSerie() != null) {
			retorno.setSerie(serieBO.obterPorCriterioOnline(matricula.getSerie()));
		}
		if (matricula.getTurma() != null) {
			retorno.setTurma(turmaBO.obterPorCriterioOnline(matricula.getTurma()));
		}
		retorno.setTurno(matricula.getTurno());
		retorno.setDataUltimaAtualizacao(matricula.getDataUltimaAtualizacao());

		matriculaDAO.salvar(retorno);
	}

	public Matricula obterPorCriterioOnline(Matricula matricula)
			throws AplicacaoException, NegocioException {
		List<Matricula> lista = matriculaDAO.obterPorCriterioOnline(matricula);
		String objeto = getLabel("matricula") + " " + matricula.getMatricula();
		Matricula retorno = validarObjetoUnico(lista, objeto);
		return retorno;
	}
	
	public List<Matricula> obterListaPorSugestaoNomeAluno(String nomeAluno, Usuario usuario) throws AplicacaoException {
		if (usuario == null) {
			return obterListaPorSugestaoNomeAluno(nomeAluno);
		}
		else {
			return matriculaDAO.obterListaPorSugestaoNomeAlunoEUsuario(nomeAluno, usuario); 
		}
	}
	
	public List<MatriculaWrapper> obterRelatorioOnlineFaltasEUsuario(
			Date periodoInicial, Date periodoFinal, Matricula matricula,
			Usuario usuario) throws AplicacaoException {
		return matriculaDAO.obterRelatorioOnlineFaltasEUsuario(periodoInicial, periodoFinal, matricula, usuario);
	}

	/* ===================================
	 * 			GETTERS & SETTERS
	 * =================================== */
	
	public MatriculaDAO getMatriculaDAO() {
		return matriculaDAO;
	}

}
