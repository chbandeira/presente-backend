package br.com.w2c.view;

import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

import br.com.w2c.controller.business.PresencaBO;
import br.com.w2c.view.exception.AplicacaoFacesException;
import br.com.w2c.exception.NegocioException;
import br.com.w2c.model.domain.Aluno;
import br.com.w2c.model.domain.Matricula;
import br.com.w2c.model.domain.Registro;
import br.com.w2c.util.Constantes;

/**
 * @author charlles
 * @since 15/09/2013
 */
@ManagedBean
@SessionScoped
public class PresencaMB extends BaseMB {
	
	private Registro registro;
	private Date periodoInicial;
	private Date periodoFinal;
	
	private DataModel<Registro> registros;
	
	private PresencaBO presencaBO = getBean("presencaBO");
	
	public PresencaMB() {
		iniciarEntidades();
	}

	protected void iniciarEntidades() {
		registro = new Registro();
		registro.setMatricula(new Matricula());
		registro.getMatricula().setAluno(new Aluno());
		periodoInicial = null;
		periodoFinal = null;
		limparListaRegistros();
	}

	private void limparListaRegistros() {
		registros = new ListDataModel<Registro>();
	}
	
	public void pageConsulta(ActionEvent event) {
		iniciarEntidades();
		redirect.redirecionaPaginaJSF(Constantes.PAGE_CONSULTA_PRESENCA);
	}

	public void consultar(ActionEvent event) throws AplicacaoFacesException {
		try {
			limparListaRegistros();
			List<Registro> lista = presencaBO.obterRegistrosPorPeriodo(registro, periodoInicial, periodoFinal);
			registros = new ListDataModel<Registro>(lista);
		} catch (NegocioException e) {
			mensagemErro(e.getMessages());
		} catch (Exception e) {
			throw new AplicacaoFacesException(e);
		}
	}
	
	public void limpar(ActionEvent event) {
		pageConsulta(null);
	}
	
	/* ===================================
	 * 			GETTERS & SETTERS
	 * =================================== */

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

	public Registro getRegistro() {
		return registro;
	}

	public void setRegistro(Registro registro) {
		this.registro = registro;
	}

	public DataModel<Registro> getRegistros() {
		return registros;
	}

	public void setRegistros(DataModel<Registro> registros) {
		this.registros = registros;
	}
	
}
