package br.com.w2c.view;

import static br.com.chbandeira.utilitario.Util.isNuloOuVazio;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

import br.com.chbandeira.utilitario.Util;
import br.com.w2c.controller.business.OcorrenciaImportacaoBO;
import br.com.w2c.exception.AplicacaoException;
import br.com.w2c.exception.NegocioException;
import br.com.w2c.model.domain.OcorrenciaImportacao;
import br.com.w2c.model.enums.Turno;
import br.com.w2c.util.Constantes;
import br.com.w2c.util.KeyValue;
import br.com.w2c.view.exception.AplicacaoFacesException;

/**
 * 
 * @author charlles
 * @since 02/10/2013
 */
@ManagedBean
@SessionScoped
public class ImportacaoMB extends BaseMB {

	private OcorrenciaImportacao ocorrencia;
	private OcorrenciaImportacao ocorrenciaSelecionada;
	private Turno turno;
	private boolean desabilitar;
	private StreamedContent file;  
	private UploadedFile uploadedFile;
	
	private DataModel<OcorrenciaImportacao> ocorrencias;
	private List<Turno> turnos;
	
	private OcorrenciaImportacaoBO ocorrenciaBO = getBean("ocorrenciaImportacaoBO");

	public ImportacaoMB() {
		iniciarEntidades();
	}
    
    public StreamedContent getFile() throws IOException, AplicacaoFacesException {
    	InputStream stream = null;
    	try {
			stream = ocorrenciaBO.criarArquivo();
    		file = new DefaultStreamedContent(stream, Constantes.TYPE_APPLICATION_TEXT, "ocorrencias.txt");
		} catch (NegocioException e) {
			mensagemErro(e.getMessages());
		} catch (Exception e) {
			throw new AplicacaoFacesException(e);
		} finally {
			if (stream == null) {
				file = null;
			}
		}
    	return file;  
    }
	
	protected void iniciarEntidades() {
		ocorrencia = new OcorrenciaImportacao();
		turnos = new ArrayList<Turno>();
	}
	
	private void carregarTurnos() {
		turnos = new ArrayList<Turno>();
		Turno[] values = Turno.values();
		for (Turno turno : values) {
			this.turnos.add(turno);
		}
	}
	
	public void importarArquivoDados() throws AplicacaoFacesException {  
		try {
			UploadedFile file = getUploadedFile();
			
			validarArquivo(file);

			KeyValue keyValue = ocorrenciaBO.importarDados(file.getInputstream());
			mensagemInfo(getMessage("MSG019", keyValue.getKeyLong(), file.getFileName()));
			if (keyValue.getValueLong().compareTo(0L) > 0) {
				mensagemAviso(getMessage("MSG023", keyValue.getValueLong()));
			}
		} catch (NegocioException e) {
			mensagemErro(e.getMessages());
		} catch (Exception e) {
			throw new AplicacaoFacesException(e);
		}
    }

	private void validarArquivo(UploadedFile file) throws NegocioException, AplicacaoException {
		if (!file.getContentType().equals(Constantes.TYPE_APPLICATION_TEXT)) {
			mensagemErro(getMessage("MSG020"));
			throw new NegocioException(null);
		}
		if (file.getSize() > 10485760l) {//10MB
			mensagemErro(getMessage("MSG021"));
			throw new NegocioException(null);
		}
	}
	
	public void pageImportarDadosAntigos(ActionEvent event) {
		redirect.redirecionaPaginaJSF(Constantes.PAGE_IMPORTAR_DADOS_ANTIGOS);
	}
	
	public void pageOcorrencias(ActionEvent event) {
		redirect.redirecionaPaginaJSF(Constantes.PAGE_OCORRENCIAS);
	}

	public void pageCadastro(ActionEvent event) {
		carregarTurnos();
		desabilitar = false;
		redirect.redirecionaPaginaJSF(Constantes.PAGE_CADASTRO_OCORRENCIA_IMPORTACAO);
	}
	
	public void consultar() throws AplicacaoFacesException {
		try {
			List<OcorrenciaImportacao> lista = ocorrenciaBO.obterTodos();
			ocorrencias = new ListDataModel<OcorrenciaImportacao>(lista);
		} catch (Exception e) {
			throw new AplicacaoFacesException(e);
		}
	}
	
	public void salvarCorrecao(ActionEvent event) throws AplicacaoFacesException {
		try {
			if (!Util.isNuloOuVazio(turno)) {
				ocorrencia.setTurno(turno.name());
			}
			ocorrenciaBO.salvarCorrecao(ocorrencia);
			mensagemSalvoSucesso();
			iniciarEntidades();
			desabilitar = true;
		} catch (NegocioException e) {
			mensagemErro(e.getMessages());
		} catch (Exception e) {
			throw new AplicacaoFacesException(e);
		}
	}
	
	public void alterar(ActionEvent event) {
		ocorrencia = ocorrencias.getRowData();
		pageCadastro(event);
	}
	
	public void excluir(ActionEvent event) throws AplicacaoFacesException {
		try {
			ocorrenciaBO.excluir(ocorrenciaSelecionada);
			mensagemExcluidoSucesso();
			consultar();
		} catch (Exception e) {
			throw new AplicacaoFacesException(e);
		}
	}
	
	public void excluirTodos(ActionEvent event) throws AplicacaoFacesException {
		try {
			ocorrenciaBO.excluirTodos();
			mensagemExcluidoSucesso();
			consultar();
		} catch (NegocioException e) {
			mensagemErro(e.getMessages());
		} catch (Exception e) {
			throw new AplicacaoFacesException(e);
		}
		
	}
	
	public DataModel<OcorrenciaImportacao> getOcorrencias() throws AplicacaoFacesException {
		if (!isNuloOuVazio(ocorrencia)) {
			consultar();
		}
		return ocorrencias;
	}

	public OcorrenciaImportacao getOcorrencia() {
		return ocorrencia;
	}

	public void setOcorrencia(OcorrenciaImportacao ocorrencia) {
		this.ocorrencia = ocorrencia;
	}

	public OcorrenciaImportacao getOcorrenciaSelecionada() {
		return ocorrenciaSelecionada;
	}

	public void setOcorrenciaSelecionada(OcorrenciaImportacao ocorrenciaSelecionada) {
		this.ocorrenciaSelecionada = ocorrenciaSelecionada;
	}

	public void setOcorrencias(DataModel<OcorrenciaImportacao> ocorrencias) {
		this.ocorrencias = ocorrencias;
	}

	public List<Turno> getTurnos() {
		return turnos;
	}

	public void setTurnos(List<Turno> turnos) {
		this.turnos = turnos;
	}

	public Turno getTurno() {
		return turno;
	}

	public void setTurno(Turno turno) {
		this.turno = turno;
	}

	public boolean isDesabilitar() {
		return desabilitar;
	}

	public void setDesabilitar(boolean desabilitar) {
		this.desabilitar = desabilitar;
	}

	public UploadedFile getUploadedFile() {
		return uploadedFile;
	}

	public void setUploadedFile(UploadedFile uploadedFile) {
		this.uploadedFile = uploadedFile;
	}
	
}
