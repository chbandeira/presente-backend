package br.com.w2c.view;

import static br.com.chbandeira.utilitario.Util.isNuloOuVazio;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

import br.com.w2c.controller.business.SalaBO;
import br.com.w2c.view.exception.AplicacaoFacesException;
import br.com.w2c.exception.NegocioException;
import br.com.w2c.model.domain.Sala;
import br.com.w2c.util.Constantes;

/**
 * 
 * @author charlles
 * @since 08/09/2013
 */
@ManagedBean
@SessionScoped
public class SalaMB extends BaseMB {

	private Sala sala;
	private Sala salaSelecionada;
	private boolean alteracao;
	
	private DataModel<Sala> salas;
	
	private SalaBO salaBO = getBean("salaBO");
	
	public SalaMB() {
		iniciarEntidades();
	}
	
	@Override
	protected void iniciarEntidades() {
		sala = new Sala();
		salaSelecionada = new Sala();
		salas = new ListDataModel<Sala>();
	}
	
	public void pageCadastro(ActionEvent event) {
		if (!alteracao) {
			iniciarEntidades();
		}
		alteracao = false;
		redirect.redirecionaPaginaJSF(Constantes.PAGE_CADASTRO_SALA);
	}
	
	public void pageConsulta(ActionEvent event) {
		iniciarEntidades();
		redirect.redirecionaPaginaJSF(Constantes.PAGE_CONSULTA_SALA);
	}

	public void salvar(ActionEvent event) throws AplicacaoFacesException {
		try {
			
			if (sala.getId() != null) {
				redirectPageConsulta = true;
			}
			
			salaBO.salvar(sala);
			mensagemSalvoSucesso();
			iniciarEntidades();
			
			if (redirectPageConsulta) {
				redirectPageConsulta = false;
				redirect.redirecionaPaginaJSF(Constantes.PAGE_CONSULTA_SALA);
			}
			
		} catch (NegocioException e) {
			mensagemErro(e.getMessages());
		} catch (Exception e) {
			throw new AplicacaoFacesException(e);
		}
	}
	
	public void consultar(ActionEvent event) throws AplicacaoFacesException {
		try {
			List<Sala> lista = salaBO.obterListaPorParametros(sala);
			salas = new ListDataModel<Sala>(lista);
		} catch (NegocioException e) {
			mensagemErro(e.getMessages());
		} catch (Exception e) {
			throw new AplicacaoFacesException(e);
		}
	}
	
	public void alterar(ActionEvent event) {
		alteracao = true;
		sala = salas.getRowData();
		pageCadastro(event);
	}
	
	public void excluir(ActionEvent event) throws AplicacaoFacesException {
		try {
			salaBO.desativar(salaSelecionada);
			mensagemExcluidoSucesso();
			consultar(event);
			iniciarEntidades();
		} catch (NegocioException e) {
			mensagemErro(e.getMessages());
		} catch (Exception e) {
			throw new AplicacaoFacesException(e);
		}
	}
	
	public DataModel<Sala> getSalas() throws AplicacaoFacesException {
		if (!isNuloOuVazio(sala) && !isNuloOuVazio(sala.getDescricao())) {
			consultar(null);
		}
		return salas;
	}
	
	/* ===================================
	 * 			GETTERS & SETTERS
	 * =================================== */
	
	public Sala getSala() {
		return sala;
	}

	public void setSala(Sala sala) {
		this.sala = sala;
	}

	public Sala getSalaSelecionada() {
		return salaSelecionada;
	}

	public void setSalaSelecionada(Sala salaSelecionada) {
		this.salaSelecionada = salaSelecionada;
	}

	public boolean isAlteracao() {
		return alteracao;
	}

	public void setAlteracao(boolean alteracao) {
		this.alteracao = alteracao;
	}
	
}
