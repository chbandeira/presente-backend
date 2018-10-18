package br.com.w2c.view;

import static br.com.chbandeira.utilitario.Util.isNuloOuVazio;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.imageio.ImageIO;

import org.primefaces.event.CaptureEvent;
import org.primefaces.model.UploadedFile;

import br.com.chbandeira.utilitario.Util;
import br.com.w2c.controller.business.ImagemBO;
import br.com.w2c.controller.business.MatriculaBO;
import br.com.w2c.controller.business.SalaBO;
import br.com.w2c.controller.business.SerieBO;
import br.com.w2c.controller.business.TurmaBO;
import br.com.w2c.exception.AplicacaoException;
import br.com.w2c.exception.NegocioException;
import br.com.w2c.model.domain.Aluno;
import br.com.w2c.model.domain.Matricula;
import br.com.w2c.model.domain.Responsavel;
import br.com.w2c.model.domain.Sala;
import br.com.w2c.model.domain.Serie;
import br.com.w2c.model.domain.Turma;
import br.com.w2c.model.enums.Turno;
import br.com.w2c.util.Constantes;
import br.com.w2c.view.exception.AplicacaoFacesException;

/**
 * @author charlles
 * @since 01/09/2013
 */
@ManagedBean
@SessionScoped
public class AlunoMB extends BaseMB {
	
	private Matricula matricula;
	private Matricula matriculaSelecionada;
	private boolean alteracao;
	private List<Turno> turnos;
	private DataModel<Matricula> matriculas;
	
	private MatriculaBO matriculaBO = getBean("matriculaBO");
	private SalaBO salaBO = getBean("salaBO");
	private SerieBO serieBO = getBean("serieBO");
	private TurmaBO turmaBO = getBean("turmaBO");
	private ImagemBO imagemBO = getBean("imagemBO");
	
	private String filename;
	private byte[] foto;
	
	private UploadedFile uploadedFile;
	
	public AlunoMB() {
		iniciarEntidades();
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

	private void carregarTurnos() {
		turnos = new ArrayList<Turno>();
		Turno[] values = Turno.values();
		for (Turno turno : values) {
			this.turnos.add(turno);
		}
	}

	protected void iniciarEntidades() {
		matricula = new Matricula();
		matricula.setAluno(new Aluno());
		matricula.setBolsista(Boolean.FALSE);
		matricula.setSala(new Sala());
		matricula.setSerie(new Serie());
		matricula.setTurma(new Turma());
		matricula.setResponsavel(new Responsavel());
		matricula.setEnviarEmailRegistro(false);
		matricula.setEnviarSmsRegistro(false);
		matriculas = new ListDataModel<Matricula>();
		turnos = new ArrayList<Turno>();
		carregarListas();
		filename = null;
	}
	
	private void carregarListas() {
		carregarTurnos();
	}
	
	public void pageCadastro(ActionEvent event) {
		if (!alteracao) {
			iniciarEntidades();
		}
		carregarListas();
		alteracao = false;
		redirect.redirecionaPaginaJSF(Constantes.PAGE_CADASTRO_ALUNO);
	}
	
	public void pageConsulta(ActionEvent event) {
		iniciarEntidades();
		redirect.redirecionaPaginaJSF(Constantes.PAGE_CONSULTA_ALUNO);
	}

	public void salvar(ActionEvent event) throws AplicacaoFacesException {
		try {
			if (matricula.getAluno().getId() != null) {
				redirectPageConsulta = true;
			}
			
			matriculaBO.matricular(matricula);
			mensagemSalvoSucesso();
			iniciarEntidades();
			
			if (redirectPageConsulta) {
				redirectPageConsulta = false;
				redirect.redirecionaPaginaJSF(Constantes.PAGE_CONSULTA_ALUNO);
			}
		} catch (NegocioException e) {
			mensagemErro(e.getMessages());
		} catch (Exception e) {
			throw new AplicacaoFacesException(e);
		}
	}
	
	public void consultar(ActionEvent event) throws AplicacaoFacesException {
		try {
			List<Matricula> lista = matriculaBO.obterListaPorParametros(matricula);
			matriculas = new ListDataModel<Matricula>(lista);
			if (Util.isNuloOuZero(matricula.getAnoLetivo())) {
				matricula.setAnoLetivo(null);
			}
		} catch (NegocioException e) {
			mensagemErro(e.getMessages());
		} catch (Exception e) {
			throw new AplicacaoFacesException(e);
		}
	}
	
	public void excluir(ActionEvent event) throws AplicacaoFacesException {
		try {
			matriculaBO.cancelarMatricula(matriculaSelecionada);
			mensagemExcluidoSucesso();
			consultar(event);
			iniciarEntidades();
		} catch (NegocioException e) {
			mensagemErro(e.getMessages());
		} catch (Exception e) {
			throw new AplicacaoFacesException(e);
		}
	}
	
	public void alterar(ActionEvent event) throws AplicacaoException {
		alteracao = true;
		matricula = matriculas.getRowData();
		
		foto = matricula.getAluno().getFoto();
		filename = imagemBO.getRandomImageName();
		imagemBO.carregarFoto(filename, getDiretorioFotos(), foto);
		
		pageCadastro(event);
	}

	public DataModel<Matricula> getMatriculas() throws AplicacaoFacesException {
		if (!isNuloOuVazio(matricula) && !isNuloOuVazio(matricula.getAluno())
				&& (!isNuloOuVazio(matricula.getMatricula()) || !isNuloOuVazio(matricula.getAluno().getNome()))) {
			consultar(null);
		}
		return matriculas;
	}
	
	//Foto
	
    public String getFilename() {
        return filename;
    }
    
    public void tirarFoto(CaptureEvent captureEvent) throws AplicacaoException {
    	foto = captureEvent.getData();
        loadFoto();
    }
    
    public void apagarFoto(ActionEvent event) {
    	filename = null;
    	foto = null;
    	matricula.getAluno().setFoto(foto);
    }
    
    public void carregarFoto() throws AplicacaoException {
    	if (!isNuloOuVazio(uploadedFile)
    			&& uploadedFile.getSize() > 0) {

    		try {
    			BufferedImage bi = ImageIO.read(uploadedFile.getInputstream());
    			
	    		if ((uploadedFile.getContentType().equals("image/jpeg")
	    				|| uploadedFile.getContentType().equals("image/jpg")
	    				|| uploadedFile.getContentType().equals("image/png"))
	    				&& uploadedFile.getSize() < 1048576l //1MB
	    				&& bi.getHeight() <= Constantes.ALTURA_FOTO
	    				&& bi.getWidth() <= Constantes.LARGURA_FOTO) {
	    			
	    			
	    			foto = uploadedFile.getContents();
	    			uploadedFile = null;
	    			loadFoto();
	    		}
	    		else {
	    			mensagemErro(getMessage("MSG059"));
	    		}
	    		
    		} catch (IOException e) {
    			throw new AplicacaoFacesException(e);
    		}
    	}
    	else {
    		mensagemAviso(getMessage("MSG061"));
    		apagarFoto(null);
    	}
    }

	private void loadFoto() throws AplicacaoException {
		filename = imagemBO.getRandomImageName();
    	matricula.getAluno().setFoto(foto);
    	imagemBO.carregarFoto(filename, getDiretorioFotos(), foto);
	}
	
	/* ===================================
	 * 			GETTERS & SETTERS
	 * =================================== */
	
	public List<Turno> getTurnos() {
		return turnos;
	}

	public void setTurnos(List<Turno> turnos) {
		this.turnos = turnos;
	}

	public Matricula getMatricula() {
		return matricula;
	}

	public void setMatricula(Matricula matricula) {
		this.matricula = matricula;
	}

	public boolean isAlteracao() {
		return alteracao;
	}

	public void setAlteracao(boolean alteracao) {
		this.alteracao = alteracao;
	}

	public Matricula getMatriculaSelecionada() {
		return matriculaSelecionada;
	}

	public void setMatriculaSelecionada(Matricula matriculaSelecionada) {
		this.matriculaSelecionada = matriculaSelecionada;
	}

	public UploadedFile getUploadedFile() {
		return uploadedFile;
	}

	public void setUploadedFile(UploadedFile uploadedFile) {
		this.uploadedFile = uploadedFile;
	}
	
}
