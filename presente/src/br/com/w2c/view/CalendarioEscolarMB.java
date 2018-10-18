package br.com.w2c.view;

import static br.com.chbandeira.utilitario.Util.isNuloOuVazio;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

import br.com.w2c.controller.business.CalendarioEscolarBO;
import br.com.w2c.exception.NegocioException;
import br.com.w2c.model.domain.CalendarioEscolar;
import br.com.w2c.util.Constantes;
import br.com.w2c.view.exception.AplicacaoFacesException;

/**
 * 
 * @author charlles
 * @since 26/11/2013
 */
@ManagedBean
@SessionScoped
public class CalendarioEscolarMB extends BaseMB {

	private CalendarioEscolar calendarioEscolar;
	private CalendarioEscolar calendarioEscolarSelecionado;
	private boolean alteracao;
	
	private DataModel<CalendarioEscolar> calendarioEscolares;
	
	private CalendarioEscolarBO calendarioEscolarBO = getBean("calendarioEscolarBO");
	
	public CalendarioEscolarMB() {
		iniciarEntidades();
	}
	
	@Override
	protected void iniciarEntidades() {
		calendarioEscolar = new CalendarioEscolar();
		calendarioEscolar.setStatus(2L);
		calendarioEscolar.setRecorrente(Boolean.FALSE);
		calendarioEscolarSelecionado = new CalendarioEscolar();
		calendarioEscolares = new ListDataModel<CalendarioEscolar>();
	}
	
	public void pageCadastro(ActionEvent event) {
		if (!alteracao) {
			iniciarEntidades();
		}
		alteracao = false;
		redirect.redirecionaPaginaJSF(Constantes.PAGE_CADASTRO_CALENDARIO_ESCOLAR);
	}
	
	public void pageConsulta(ActionEvent event) {
		iniciarEntidades();
		redirect.redirecionaPaginaJSF(Constantes.PAGE_CONSULTA_CALENDARIO_ESCOLAR);
	}

	public void salvar(ActionEvent event) throws AplicacaoFacesException {
		try {
			
			if (calendarioEscolar.getId() != null) {
				redirectPageConsulta = true;
			}
			
			calendarioEscolar.setAutomatico(Boolean.FALSE);
			calendarioEscolarBO.salvar(calendarioEscolar);
			mensagemSalvoSucesso();
			iniciarEntidades();
			
			if (redirectPageConsulta) {
				redirectPageConsulta = false;
				redirect.redirecionaPaginaJSF(Constantes.PAGE_CONSULTA_CALENDARIO_ESCOLAR);
			}
			
		} catch (NegocioException e) {
			mensagemErro(e.getMessages());
		} catch (Exception e) {
			throw new AplicacaoFacesException(e);
		}
	}
	
	public void consultar(ActionEvent event) throws AplicacaoFacesException {
		try {
			List<CalendarioEscolar> lista = calendarioEscolarBO.obterListaPorParametrosPesquisa(calendarioEscolar);
			calendarioEscolares = new ListDataModel<CalendarioEscolar>(lista);
		} catch (NegocioException e) {
			mensagemErro(e.getMessages());
		} catch (Exception e) {
			throw new AplicacaoFacesException(e);
		}
	}
	
	public void alterar(ActionEvent event) {
		alteracao = true;
		calendarioEscolar = calendarioEscolares.getRowData();
		pageCadastro(event);
	}
	
	public void excluir(ActionEvent event) throws AplicacaoFacesException {
		try {
			calendarioEscolarBO.excluir(calendarioEscolarSelecionado);
			mensagemExcluidoSucesso();
			consultar(event);
			iniciarEntidades();
		} catch (NegocioException e) {
			mensagemErro(e.getMessages());
		} catch (Exception e) {
			throw new AplicacaoFacesException(e);
		}
	}
	
	public DataModel<CalendarioEscolar> getCalendarioEscolars() throws AplicacaoFacesException {
		if (!isNuloOuVazio(calendarioEscolar) && !isNuloOuVazio(calendarioEscolar.getDescricao())) {
			consultar(null);
		}
		return calendarioEscolares;
	}
	
	/* ===================================
	 * 			GETTERS & SETTERS
	 * =================================== */

	public CalendarioEscolar getCalendarioEscolar() {
		return calendarioEscolar;
	}

	public void setCalendarioEscolar(CalendarioEscolar calendarioEscolar) {
		this.calendarioEscolar = calendarioEscolar;
	}

	public CalendarioEscolar getCalendarioEscolarSelecionado() {
		return calendarioEscolarSelecionado;
	}

	public void setCalendarioEscolarSelecionado(
			CalendarioEscolar calendarioEscolarSelecionado) {
		this.calendarioEscolarSelecionado = calendarioEscolarSelecionado;
	}

	public boolean isAlteracao() {
		return alteracao;
	}

	public void setAlteracao(boolean alteracao) {
		this.alteracao = alteracao;
	}

	public DataModel<CalendarioEscolar> getCalendarioEscolares() {
		return calendarioEscolares;
	}

	public void setCalendarioEscolares(
			DataModel<CalendarioEscolar> calendarioEscolares) {
		this.calendarioEscolares = calendarioEscolares;
	}
	
}
