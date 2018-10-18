package br.com.w2c.view;

import java.io.InputStream;
import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.ServletContext;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import br.com.w2c.controller.business.CodigoBarrasBO;
import br.com.w2c.view.exception.AplicacaoFacesException;
import br.com.w2c.exception.NegocioException;
import br.com.w2c.model.domain.Aluno;
import br.com.w2c.model.domain.Matricula;
import br.com.w2c.util.Constantes;

/**
 * 
 * @author charlles
 * @since 29/09/2013
 */
@ManagedBean
@SessionScoped
public class CodigoBarrasMB extends BaseMB {

	private Matricula matricula;
	private Date periodoInicial;
	private Date periodoFinal;
	private String arquivoPdf;
	private boolean habilitarBotaoPdf;
	
	private CodigoBarrasBO codigoBarrasBO = getBean("codigoBarrasBO");
	
	@Override
	protected void iniciarEntidades() {
		matricula = new Matricula();
		matricula.setAluno(new Aluno());
		arquivoPdf = "";
		periodoInicial = null;
		periodoFinal = null;
		habilitarBotaoPdf = false;
	}
	
	public void pageGerar(ActionEvent event) {
		iniciarEntidades();
		redirect.redirecionaPaginaJSF(Constantes.PAGE_GERAR_CODIGO_BARRAS);
	}
	
	public void gerar(ActionEvent event) throws AplicacaoFacesException {
		try {
			codigoBarrasBO.gerarPdfCodigoBarras(matricula, periodoInicial, periodoFinal, getArquivoPdf());
			mensagemInfo(getMessage("MSG015"));
			habilitarBotaoPdf = true;
		} catch (NegocioException e) {
			mensagemErro(e.getMessages());
		} catch (Exception e) {
			throw new AplicacaoFacesException(e);
		}
	}
	
	public StreamedContent getPdf() {
		InputStream stream = ((ServletContext)FacesContext.getCurrentInstance().getExternalContext().getContext()).getResourceAsStream(Constantes.DIRETORIO_CODIGO_BARRAS);  
		StreamedContent file = new DefaultStreamedContent(stream, "application/pdf", Constantes.DIRETORIO_CODIGO_BARRAS);  
        return file;
	}
	
	public Matricula getMatricula() {
		return matricula;
	}

	public void setMatricula(Matricula matricula) {
		this.matricula = matricula;
	}

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

	public String getArquivoPdf() {
		arquivoPdf = getDiretorioReal(Constantes.DIRETORIO_CODIGO_BARRAS);
		return arquivoPdf;
	}

	public boolean isHabilitarBotaoPdf() {
		return habilitarBotaoPdf;
	}

	public void setHabilitarBotaoPdf(boolean habilitarBotaoPdf) {
		this.habilitarBotaoPdf = habilitarBotaoPdf;
	}
	
}
