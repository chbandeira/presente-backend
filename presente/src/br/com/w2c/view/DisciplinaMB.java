package br.com.w2c.view;

import static br.com.chbandeira.utilitario.Util.isNuloOuVazio;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

import br.com.w2c.controller.business.DisciplinaBO;
import br.com.w2c.exception.NegocioException;
import br.com.w2c.model.domain.Disciplina;
import br.com.w2c.util.Constantes;
import br.com.w2c.view.exception.AplicacaoFacesException;

/**
 * 
 * @author charlles
 * @since 05/05/2015
 */
@ManagedBean
@SessionScoped
public class DisciplinaMB extends BaseMB {

	private Disciplina disciplina;
	private Disciplina disciplinaSelecionada;
	private boolean alteracao;
	
	private DataModel<Disciplina> disciplinas;
	
	private DisciplinaBO disciplinaBO = getBean("disciplinaBO");
	
	public DisciplinaMB() {
		iniciarEntidades();
	}
	
	@Override
	protected void iniciarEntidades() {
		disciplina = new Disciplina();
		disciplinaSelecionada = new Disciplina();
		disciplinas = new ListDataModel<Disciplina>();
	}
	
	public void pageCadastro(ActionEvent event) {
		if (!alteracao) {
			iniciarEntidades();
		}
		alteracao = false;
		redirect.redirecionaPaginaJSF(Constantes.PAGE_CADASTRO_DISCIPLINA);
	}
	
	public void pageConsulta(ActionEvent event) {
		iniciarEntidades();
		redirect.redirecionaPaginaJSF(Constantes.PAGE_CONSULTA_DISCIPLINA);
	}

	public void salvar(ActionEvent event) throws AplicacaoFacesException {
		try {
			
			if (disciplina.getId() != null) {
				redirectPageConsulta = true;
			}
			
			disciplinaBO.salvar(disciplina);
			mensagemSalvoSucesso();
			iniciarEntidades();
			
			if (redirectPageConsulta) {
				redirectPageConsulta = false;
				redirect.redirecionaPaginaJSF(Constantes.PAGE_CONSULTA_DISCIPLINA);
			}
			
		} catch (NegocioException e) {
			mensagemErro(e.getMessages());
		} catch (Exception e) {
			throw new AplicacaoFacesException(e);
		}
	}
	
	public void consultar(ActionEvent event) throws AplicacaoFacesException {
		try {
			List<Disciplina> lista = disciplinaBO.obterListaPorParametrosPesquisa(disciplina);
			disciplinas = new ListDataModel<Disciplina>(lista);
		} catch (NegocioException e) {
			mensagemErro(e.getMessages());
		} catch (Exception e) {
			throw new AplicacaoFacesException(e);
		}
	}
	
	public void alterar(ActionEvent event) {
		alteracao = true;
		disciplina = disciplinas.getRowData();
		pageCadastro(event);
	}
	
	public void excluir() throws AplicacaoFacesException {
		try {
			disciplinaBO.desativar(disciplinaSelecionada);
			mensagemExcluidoSucesso();
			consultar(null);
			iniciarEntidades();
		} catch (NegocioException e) {
			mensagemErro(e.getMessages());
		} catch (Exception e) {
			throw new AplicacaoFacesException(e);
		}
	}
	
	public DataModel<Disciplina> getDisciplinas() throws AplicacaoFacesException {
		if (!isNuloOuVazio(disciplina) && !isNuloOuVazio(disciplina.getNome())) {
			consultar(null);
		}
		return disciplinas;
	}

	/* ===================================
	 * 			GETTERS & SETTERS
	 * =================================== */
	
	public Disciplina getDisciplina() {
		return disciplina;
	}

	public void setDisciplina(Disciplina disciplina) {
		this.disciplina = disciplina;
	}

	public Disciplina getDisciplinaSelecionada() {
		return disciplinaSelecionada;
	}

	public void setDisciplinaSelecionada(Disciplina disciplinaSelecionada) {
		this.disciplinaSelecionada = disciplinaSelecionada;
	}
	
}
