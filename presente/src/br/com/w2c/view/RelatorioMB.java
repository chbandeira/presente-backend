package br.com.w2c.view;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import br.com.w2c.controller.business.SalaBO;
import br.com.w2c.controller.business.SerieBO;
import br.com.w2c.controller.business.TipoOcorrenciaBO;
import br.com.w2c.controller.business.TurmaBO;
import br.com.w2c.exception.AplicacaoException;
import br.com.w2c.exception.NegocioException;
import br.com.w2c.jasperreport.business.RelatorioFaltasBO;
import br.com.w2c.jasperreport.business.RelatorioFichaDisciplinarBO;
import br.com.w2c.jasperreport.business.RelatorioOcorrenciaBO;
import br.com.w2c.jasperreport.business.RelatorioPresencaBO;
import br.com.w2c.jasperreport.util.ConstantesRelatorio;
import br.com.w2c.model.domain.Aluno;
import br.com.w2c.model.domain.Matricula;
import br.com.w2c.model.domain.Ocorrencia;
import br.com.w2c.model.domain.Responsavel;
import br.com.w2c.model.domain.Sala;
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
 * @since 04/12/2013
 */
@ManagedBean
@RequestScoped
public class RelatorioMB extends BaseMB {

	private Date periodoInicial;
	private Date periodoFinal;
	private StreamedContent file;
	private Matricula matricula;
	private List<Turno> turnos;
	private Ocorrencia ocorrencia;
	private boolean imprimirDuplicado;
	
	private SalaBO salaBO = getBean("salaBO");
	private SerieBO serieBO = getBean("serieBO");
	private TurmaBO turmaBO = getBean("turmaBO");
	private RelatorioFaltasBO relatorioFaltasBO = SpringUtil.getBean("relatorioFaltasBO");
	private RelatorioPresencaBO relatorioPresencaBO = SpringUtil.getBean("relatorioPresencaBO");
	private RelatorioOcorrenciaBO relatorioOcorrenciaBO = SpringUtil.getBean("relatorioOcorrenciaBO");
	private RelatorioFichaDisciplinarBO relatorioFichaDisciplinarBO = SpringUtil.getBean("relatorioFichaDisciplinarBO");
	private TipoOcorrenciaBO tipoOcorrenciaBO = getBean("tipoOcorrenciaBO");
	
	public RelatorioMB() {
		iniciarEntidades();
	}
	
	private void carregarTurnos() {
		setTurnos(new ArrayList<Turno>());
		Turno[] values = Turno.values();
		for (Turno turno : values) {
			this.getTurnos().add(turno);
		}
	}
	
	public List<Sala> carregarSalas(String descricao) throws AplicacaoException {
		return salaBO.obterListaPorSugestaoDescricao(descricao);
	}

	public List<Turma> carregarTurmas(String descricao) throws AplicacaoException {
		return turmaBO.obterListaPorSugestaoDescricao(descricao);
	}

	public List<Serie> carregarSeries(String descricao) throws AplicacaoException {
		return serieBO.obterListaPorSugestaoDescricao(descricao);
	}

	protected void iniciarEntidades() {
		setMatricula(new Matricula());
		setOcorrencia(new Ocorrencia());
		getMatricula().setAluno(new Aluno());
		getMatricula().setBolsista(Boolean.FALSE);
		getMatricula().setSala(new Sala());
		getMatricula().setSerie(new Serie());
		getMatricula().setTurma(new Turma());
		getMatricula().setResponsavel(new Responsavel());
		getMatricula().setEnviarEmailRegistro(false);
		getMatricula().setEnviarSmsRegistro(false);
		setTurnos(new ArrayList<Turno>());
		carregarListas();
	}
	
	private void carregarListas() {
		carregarTurnos();
	}
	
	public void pageBoletimFrequencia(ActionEvent event) {
		
	}
	
	public void pageRelatorioFaltas(ActionEvent event) {
		redirect.redirecionaPaginaJSF(Constantes.PAGE_RELATORIO_FALTAS);
	}
	
	public void pageRelatorioPresenca(ActionEvent event) {
		redirect.redirecionaPaginaJSF(Constantes.PAGE_RELATORIO_PRESENCA);
	}
	
	public void pageRelatorioOcorrencias(ActionEvent event) {
		redirect.redirecionaPaginaJSF(Constantes.PAGE_RELATORIO_OCORRENCIA);
	}
	
	public void pageRelatorioFichaDisciplinar(ActionEvent event) {
		redirect.redirecionaPaginaJSF(Constantes.PAGE_RELATORIO_FICHA_DISCIPLINAR);
	}
	
	public void pageRelatorioFrequenciaPorTurma(ActionEvent event) {
		//TODO
	}

	@Deprecated
	public StreamedContent getRelatorioFaltas() throws AplicacaoFacesException {
		InputStream stream;
		try {
			stream = relatorioFaltasBO.gerarRelatorioFaltas(periodoInicial, periodoFinal, getDiretorioRealRelatorios());
			file = new DefaultStreamedContent(stream, Constantes.TYPE_APPLICATION_PDF, ConstantesRelatorio.JASPER_RELATORIO_FALTAS_PDF);
		
		} catch (NegocioException e) {
			mensagemErro(e.getMessages());
		} catch (Exception e) {
			throw new AplicacaoFacesException(e);
		} 
        return file;
	}
	
	public void gerarRelatorioFaltas(ActionEvent event) throws AplicacaoFacesException {
		FacesContext facesContext = null;
		try {
			facesContext = FacesContext.getCurrentInstance();
			OutputStream outputStream = facesContext.getExternalContext().getResponseOutputStream();
			relatorioFaltasBO.gerarCompleto(periodoInicial, periodoFinal, matricula, getDiretorioRealRelatorios(), outputStream);
			
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
	
	public void gerarRelatorioPresenca(ActionEvent event) throws AplicacaoFacesException {
		FacesContext facesContext = null;
		try {
			facesContext = FacesContext.getCurrentInstance();
			OutputStream outputStream = facesContext.getExternalContext().getResponseOutputStream();
			relatorioPresencaBO.gerarCompleto(periodoInicial, periodoFinal, matricula, getDiretorioRealRelatorios(), outputStream);
			
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
	
	public void gerarRelatorioOcorrencias(ActionEvent event) throws AplicacaoFacesException {
		FacesContext facesContext = null;
		try {
			facesContext = FacesContext.getCurrentInstance();
			OutputStream outputStream = facesContext.getExternalContext().getResponseOutputStream();
			if (isImprimirDuplicado()) {
				relatorioOcorrenciaBO.gerarCompletoFolhaDuplicada(
						periodoInicial, periodoFinal, matricula, getDiretorioRealRelatorios(), outputStream, ocorrencia);
			}
			else {
				relatorioOcorrenciaBO.gerarCompleto(
						periodoInicial, periodoFinal, matricula, getDiretorioRealRelatorios(), outputStream, ocorrencia);
			}
			
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
	
	public void gerarFichaDisciplinar(ActionEvent event) throws AplicacaoFacesException {
		FacesContext facesContext = null;
		try {
			facesContext = FacesContext.getCurrentInstance();
			OutputStream outputStream = facesContext.getExternalContext().getResponseOutputStream();
			relatorioFichaDisciplinarBO.gerarCompleto(
					periodoInicial, periodoFinal, matricula, getDiretorioRealRelatorios(), outputStream, ocorrencia);
			
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
	
	public List<TipoOcorrencia> carregarTipoOcorrencias(String descricao) throws AplicacaoException {
		return tipoOcorrenciaBO.obterListaPorSugestaoDescricao(descricao);
	}
	
	/*get set*/
	
	public Date getPeriodoInicial() {
		return periodoInicial;
	}

	public void setPeriodoInicial(Date periodoInicial) {
		this.periodoInicial = periodoInicial;
	}

	public Date getPeriodoFinal() {
		return periodoFinal;
	}

	public void setPeriodoFinal(Date periodoFinal) {
		this.periodoFinal = periodoFinal;
	}

	public Matricula getMatricula() {
		return matricula;
	}

	public void setMatricula(Matricula matricula) {
		this.matricula = matricula;
	}

	public List<Turno> getTurnos() {
		return turnos;
	}

	public void setTurnos(List<Turno> turnos) {
		this.turnos = turnos;
	}

	public Ocorrencia getOcorrencia() {
		return ocorrencia;
	}

	public void setOcorrencia(Ocorrencia ocorrencia) {
		this.ocorrencia = ocorrencia;
	}

	public boolean isImprimirDuplicado() {
		return imprimirDuplicado;
	}

	public void setImprimirDuplicado(boolean imprimirDuplicado) {
		this.imprimirDuplicado = imprimirDuplicado;
	}
	
}
