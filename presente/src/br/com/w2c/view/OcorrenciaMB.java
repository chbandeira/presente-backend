package br.com.w2c.view;

import static br.com.chbandeira.utilitario.Util.isNuloOuVazio;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

import org.apache.commons.collections.IteratorUtils;

import br.com.chbandeira.utilitario.UtilDate;
import br.com.w2c.controller.business.AlunoBO;
import br.com.w2c.controller.business.MatriculaBO;
import br.com.w2c.controller.business.OcorrenciaBO;
import br.com.w2c.controller.business.SerieBO;
import br.com.w2c.controller.business.TipoOcorrenciaBO;
import br.com.w2c.controller.business.TurmaBO;
import br.com.w2c.exception.AplicacaoException;
import br.com.w2c.exception.NegocioException;
import br.com.w2c.jasperreport.business.RelatorioOcorrenciaBO;
import br.com.w2c.model.domain.Aluno;
import br.com.w2c.model.domain.Matricula;
import br.com.w2c.model.domain.Ocorrencia;
import br.com.w2c.model.domain.Serie;
import br.com.w2c.model.domain.TipoOcorrencia;
import br.com.w2c.model.domain.Turma;
import br.com.w2c.model.enums.Turno;
import br.com.w2c.util.Constantes;
import br.com.w2c.util.SpringUtil;
import br.com.w2c.view.exception.AplicacaoFacesException;

/**
 * 
 * @author charlles
 * @since 19/05/2015
 */
@ManagedBean
@SessionScoped
public class OcorrenciaMB extends BaseMB {

	private Ocorrencia ocorrencia;
	private Ocorrencia ocorrenciaSelecionada;
	private Matricula matriculaSelecionada;
	private Turma turmaSelecionada;
	private boolean alteracao;
	private boolean visualizarCampos;
	private boolean cadastroPorAluno;
	private boolean enviarEmail;
	private boolean imprimirDuplicado;
	
	private DataModel<Ocorrencia> ocorrencias;
	private DataModel<Matricula> matriculasSelecionadas;
	private DataModel<Turma> turmasSelecionadas;
	private List<Turno> turnos;
	
	private OcorrenciaBO ocorrenciaBO = getBean("ocorrenciaBO");
	private TipoOcorrenciaBO tipoOcorrenciaBO = getBean("tipoOcorrenciaBO");
	private AlunoBO alunoBO = getBean("alunoBO");
	private MatriculaBO matriculaBO = getBean("matriculaBO");
	private TurmaBO turmaBO = getBean("turmaBO");
	private SerieBO serieBO = getBean("serieBO");
	private RelatorioOcorrenciaBO relatorioOcorrenciaBO = SpringUtil.getBean("relatorioOcorrenciaBO");
	
	public OcorrenciaMB() {
		iniciarEntidades();
	}
	
	@Override
	protected void iniciarEntidades() {
		ocorrencia = new Ocorrencia(UtilDate.getDataAtualTimestamp());
		ocorrencia.setMatricula(new Matricula());
		ocorrencia.getMatricula().setAluno(new Aluno());
		ocorrencia.setTipo(new TipoOcorrencia());
		ocorrencia.setUsuario(getUsuarioSession());
		ocorrenciaSelecionada = new Ocorrencia();
		ocorrencias = new ListDataModel<Ocorrencia>();
		limparMatriculaSelecionada();
		matriculasSelecionadas = new ListDataModel<Matricula>();
		turmaSelecionada = new Turma();
		turmasSelecionadas = new ListDataModel<Turma>();
		visualizarCampos = true;
		carregarTurnos();
	}
	
	public void pageCadastroPorAluno(ActionEvent event) {
		cadastroPorAluno = true;
		pageCadastro(event);
	}
	
	public void pageCadastroPorTurma(ActionEvent event) {
		cadastroPorAluno = false;
		pageCadastro(event);
	}
	
	public void pageCadastro(ActionEvent event) {
		if (!alteracao) {
			iniciarEntidades();
		}
		alteracao = false;
		redirect.redirecionaPaginaJSF(Constantes.PAGE_CADASTRO_OCORRENCIA);
	}
	
	public void pageConsulta(ActionEvent event) {
		iniciarEntidades();
		redirect.redirecionaPaginaJSF(Constantes.PAGE_CONSULTA_OCORRENCIA);
	}

	public void salvar(ActionEvent event) throws AplicacaoFacesException {
		try {
			
			if (ocorrencia.getId() != null) {
				redirectPageConsulta = true;
				if (cadastroPorAluno) {
					ocorrencia.setMatricula(matriculaSelecionada);
					ocorrenciaBO.salvar(ocorrencia, enviarEmail);
				}
			}
			else {
				if (cadastroPorAluno) {
					ocorrenciaBO.salvar(ocorrencia, IteratorUtils.toList(matriculasSelecionadas.iterator()), enviarEmail);
				}
				else {
					ocorrenciaBO.salvar(ocorrencia, IteratorUtils.toList(turmasSelecionadas.iterator()), enviarEmail);
				}
			}
			
			mensagemSalvoSucesso();
			iniciarEntidades();
			
			if (redirectPageConsulta) {
				redirectPageConsulta = false;
				redirect.redirecionaPaginaJSF(Constantes.PAGE_CONSULTA_OCORRENCIA);
			}
			
			visualizarCampos = true;
			
		} catch (NegocioException e) {
			mensagemErro(e.getMessages());
		} catch (Exception e) {
			throw new AplicacaoFacesException(e);
		}
	}
	
	@SuppressWarnings("unchecked")
	public void salvarImprimir(ActionEvent event) throws AplicacaoFacesException {
		FacesContext facesContext = null;
		try {
			
			if (ocorrencia.getId() != null) {
				redirectPageConsulta = true;
				if (cadastroPorAluno) {
					ocorrencia.setMatricula(matriculaSelecionada);
					ocorrenciaBO.salvar(ocorrencia, enviarEmail);
					
					facesContext = FacesContext.getCurrentInstance();
					OutputStream outputStream = facesContext.getExternalContext().getResponseOutputStream();
					
					if (isImprimirDuplicado()) {
						relatorioOcorrenciaBO.gerarCompletoFolhaDuplicada(
								ocorrencia.getData(), ocorrencia.getData(), matriculaSelecionada, getDiretorioRealRelatorios(), outputStream, ocorrencia);
					}
					else {
						relatorioOcorrenciaBO.gerarCompleto(
								ocorrencia.getData(), ocorrencia.getData(), matriculaSelecionada, getDiretorioRealRelatorios(), outputStream, ocorrencia);
					}
				}
			}
			else {
				if (cadastroPorAluno) {
					List<Long> idsSalvos = ocorrenciaBO.salvar(ocorrencia, IteratorUtils.toList(matriculasSelecionadas.iterator()), enviarEmail);
					
					facesContext = FacesContext.getCurrentInstance();
					OutputStream outputStream = facesContext.getExternalContext().getResponseOutputStream();
					
					if (isImprimirDuplicado()) {
						relatorioOcorrenciaBO.gerarCompletoMatriculasFolhaDuplicada(
								ocorrencia.getData(), ocorrencia.getData(), IteratorUtils.toList(matriculasSelecionadas.iterator()), getDiretorioRealRelatorios(), outputStream, ocorrencia, idsSalvos);
					}
					else {
						relatorioOcorrenciaBO.gerarCompletoMatriculas(
								ocorrencia.getData(), ocorrencia.getData(), IteratorUtils.toList(matriculasSelecionadas.iterator()), getDiretorioRealRelatorios(), outputStream, ocorrencia, idsSalvos);
					}
				}
				else {
					List<Long> idsSalvos = ocorrenciaBO.salvar(ocorrencia, IteratorUtils.toList(turmasSelecionadas.iterator()), enviarEmail);
					
					facesContext = FacesContext.getCurrentInstance();
					OutputStream outputStream = facesContext.getExternalContext().getResponseOutputStream();
					
					if (isImprimirDuplicado()) {
						relatorioOcorrenciaBO.gerarCompletoTurmasFolhaDuplicada(
								ocorrencia.getData(), ocorrencia.getData(), IteratorUtils.toList(turmasSelecionadas.iterator()), getDiretorioRealRelatorios(), outputStream, ocorrencia, idsSalvos);
					}
					else {
						relatorioOcorrenciaBO.gerarCompletoTurmas(
								ocorrencia.getData(), ocorrencia.getData(), IteratorUtils.toList(turmasSelecionadas.iterator()), getDiretorioRealRelatorios(), outputStream, ocorrencia, idsSalvos);
					}
				}
			}
			
			mensagemSalvoSucesso();
			visualizarCampos = true;
			
		} catch (NegocioException e) {
			super.getSession().put(Constantes.SESSION_ERROR_RELATORIO, e.getMessages());
			redirect.redirecionaPaginaJSF(Constantes.PAGE_ERROR_RELATORIO);
		} catch (AplicacaoFacesException e) {
			throw e;
		} catch (Exception e) {
			throw new AplicacaoFacesException(e);
		} finally {
			facesContext.responseComplete();
		}
	}
	
	public void consultar(ActionEvent event) throws AplicacaoFacesException {
		try {
			List<Ocorrencia> lista = ocorrenciaBO.obterListaPorParametrosPesquisa(ocorrencia);
			ocorrencias = new ListDataModel<Ocorrencia>(lista);
		} catch (NegocioException e) {
			mensagemErro(e.getMessages());
		} catch (Exception e) {
			throw new AplicacaoFacesException(e);
		}
	}
	
	public void alterar(ActionEvent event) {
		visualizarCampos = false;
		cadastroPorAluno = true;
		alteracao = true;
		ocorrencia = ocorrencias.getRowData();
		matriculaSelecionada = ocorrencia.getMatricula();
		pageCadastro(event);
	}
	
	public void excluir() throws AplicacaoFacesException {
		try {
			ocorrenciaBO.desativar(ocorrenciaSelecionada);
			mensagemExcluidoSucesso();
			consultar(null);
			iniciarEntidades();
		} catch (NegocioException e) {
			mensagemErro(e.getMessages());
		} catch (Exception e) {
			throw new AplicacaoFacesException(e);
		}
	}
	
	public DataModel<Ocorrencia> getOcorrencias() throws AplicacaoFacesException {
		if (!isNuloOuVazio(ocorrencia) && !isNuloOuVazio(ocorrencia.getId())) {
			consultar(null);
		}
		return ocorrencias;
	}
	
	public List<TipoOcorrencia> carregarTipoOcorrencias(String descricao) throws AplicacaoException {
		return tipoOcorrenciaBO.obterListaPorSugestaoDescricao(descricao);
	}
	
	public List<Aluno> carregarAlunos(String nome) throws AplicacaoException {
		return alunoBO.obterListaPorSugestaoNome(nome);
	}
	
	public List<Matricula> carregarMatriculas(String nomeAluno) throws AplicacaoException {
		return matriculaBO.obterListaPorSugestaoNomeAluno(nomeAluno);
	}
	
	public List<Turma> carregarTurmas(String descricaoTurma) throws AplicacaoException {
		return turmaBO.obterListaPorSugestaoDescricaoMatriculados(descricaoTurma);
	}
	
	public List<Serie> carregarSeries(String descricao) throws AplicacaoException {
		return serieBO.obterListaPorSugestaoDescricao(descricao);
	}
	
	public void adicionarNaLista(ActionEvent event) {
		if (cadastroPorAluno) {
			if (!getListDataModelMatriculasSelecionadas().contains(matriculaSelecionada)) {
				addListDataModelMatriculaSelecionada();
				limparMatriculaSelecionada();
			}
		}
		else {
			@SuppressWarnings("unchecked")
			List<Turma> listaTurmas = IteratorUtils.toList(turmasSelecionadas.iterator());
			if (isNuloOuVazio(listaTurmas)) {
				adicionarNaListaDeTurmas(listaTurmas);
			}
			else {
				for (Turma turma : listaTurmas) {
					if (turmasIguais(turma, turmaSelecionada) < 0) {
						adicionarNaListaDeTurmas(listaTurmas);
					}
				}
			}
			turmaSelecionada = new Turma();
		}
	}

	private void adicionarNaListaDeTurmas(List<Turma> listaTurmas) {
		listaTurmas.add(turmaSelecionada);
		turmasSelecionadas = new ListDataModel<Turma>(listaTurmas);
	}

	private int turmasIguais(Turma turma, Turma turmaSelecionada) {
		if (turma.getId().compareTo(turmaSelecionada.getId()) == 0
				&& turma.getSerie().getDescricao().equalsIgnoreCase(turmaSelecionada.getSerie().getDescricao())
				&& turma.getAnoLetivo().compareTo(turmaSelecionada.getAnoLetivo()) == 0) {
			return 0;
		}
		else {
			return -1;
		}
	}

	private void limparMatriculaSelecionada() {
		matriculaSelecionada = new Matricula();
	}
	
	public void removerDaLista(ActionEvent event) {
		if (cadastroPorAluno) {
			matriculaSelecionada = matriculasSelecionadas.getRowData();
			removeListDataModelMatriculaSelecionada();
			limparMatriculaSelecionada();
		}
		else {
			turmaSelecionada = turmasSelecionadas.getRowData();
			@SuppressWarnings("unchecked")
			List<Turma> listaTurma = IteratorUtils.toList(turmasSelecionadas.iterator());
			int indice = Collections.binarySearch(listaTurma, turmaSelecionada, new Comparator<Turma>() {

				@Override
				public int compare(Turma o1, Turma o2) {
					return turmasIguais(o1, o2);
				}
				
			});
			if (indice > -1) {
				listaTurma.remove(indice);
				turmasSelecionadas = new ListDataModel<Turma>(listaTurma);
			}
			turmaSelecionada = new Turma();
		}
	}
	
	@SuppressWarnings("unchecked")
	private List<Matricula> getListDataModelMatriculasSelecionadas() {
		return IteratorUtils.toList(matriculasSelecionadas.iterator());
	}
	
	@SuppressWarnings("unchecked")
	private void addListDataModelMatriculaSelecionada() {
		List<Matricula> matriculas = IteratorUtils.toList(matriculasSelecionadas.iterator());
		matriculas.add(matriculaSelecionada);
		matriculasSelecionadas = new ListDataModel<Matricula>(matriculas);
	}
	
	@SuppressWarnings("unchecked")
	private void removeListDataModelMatriculaSelecionada() {
		List<Matricula> matriculas = IteratorUtils.toList(matriculasSelecionadas.iterator());
		matriculas.remove(matriculaSelecionada);
		matriculasSelecionadas = new ListDataModel<Matricula>(matriculas);
	}
	
	private void carregarTurnos() {
		turnos = new ArrayList<Turno>();
		Turno[] values = Turno.values();
		for (Turno turno : values) {
			this.turnos.add(turno);
		}
	}
	
	/* ===================================
	 * 			GETTERS & SETTERS
	 * =================================== */
	
	public Ocorrencia getOcorrencia() {
		return ocorrencia;
	}

	public void setOcorrencia(Ocorrencia ocorrencia) {
		this.ocorrencia = ocorrencia;
	}

	public Ocorrencia getOcorrenciaSelecionada() {
		return ocorrenciaSelecionada;
	}

	public void setOcorrenciaSelecionada(Ocorrencia ocorrenciaSelecionada) {
		this.ocorrenciaSelecionada = ocorrenciaSelecionada;
	}

	public DataModel<Matricula> getMatriculasSelecionadas() {
		return matriculasSelecionadas;
	}

	public void setMatriculasSelecionadas(DataModel<Matricula> matriculasSelecionadas) {
		this.matriculasSelecionadas = matriculasSelecionadas;
	}

	public Matricula getMatriculaSelecionada() {
		return matriculaSelecionada;
	}

	public void setMatriculaSelecionada(Matricula matriculaSelecionada) {
		this.matriculaSelecionada = matriculaSelecionada;
	}

	public boolean isVisualizarCampos() {
		return visualizarCampos;
	}

	public void setVisualizarCampos(boolean visualizarCampos) {
		this.visualizarCampos = visualizarCampos;
	}

	public boolean isCadastroPorAluno() {
		return cadastroPorAluno;
	}

	public void setCadastroPorAluno(boolean cadastroPorAluno) {
		this.cadastroPorAluno = cadastroPorAluno;
	}

	public DataModel<Turma> getTurmasSelecionadas() {
		return turmasSelecionadas;
	}

	public void setTurmasSelecionadas(DataModel<Turma> turmasSelecionadas) {
		this.turmasSelecionadas = turmasSelecionadas;
	}

	public Turma getTurmaSelecionada() {
		return turmaSelecionada;
	}

	public void setTurmaSelecionada(Turma turmaSelecionada) {
		this.turmaSelecionada = turmaSelecionada;
	}

	public List<Turno> getTurnos() {
		return turnos;
	}

	public void setTurnos(List<Turno> turnos) {
		this.turnos = turnos;
	}

	public boolean isEnviarEmail() {
		return enviarEmail;
	}

	public void setEnviarEmail(boolean enviarEmail) {
		this.enviarEmail = enviarEmail;
	}

	public boolean isImprimirDuplicado() {
		return imprimirDuplicado;
	}

	public void setImprimirDuplicado(boolean imprimirDuplicado) {
		this.imprimirDuplicado = imprimirDuplicado;
	}

	
}
