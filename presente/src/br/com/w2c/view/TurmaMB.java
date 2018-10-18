package br.com.w2c.view;

import static br.com.chbandeira.utilitario.Util.isNuloOuVazio;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

import br.com.w2c.controller.business.TurmaBO;
import br.com.w2c.view.exception.AplicacaoFacesException;
import br.com.w2c.exception.NegocioException;
import br.com.w2c.model.domain.Turma;
import br.com.w2c.util.Constantes;

/**
 * 
 * @author charlles
 * @since 08/09/2013
 */
@ManagedBean
@SessionScoped
public class TurmaMB extends BaseMB {

	private Turma turma;
	private Turma turmaSelecionada;
	private boolean alteracao;
	
	private DataModel<Turma> turmas;
	
	private TurmaBO turmaBO = getBean("turmaBO");
	
	public TurmaMB() {
		iniciarEntidades();
	}
	
	@Override
	protected void iniciarEntidades() {
		turma = new Turma();
		turmaSelecionada = new Turma();
		turmas = new ListDataModel<Turma>();
	}
	
	public void pageCadastro(ActionEvent event) {
		if (!alteracao) {
			iniciarEntidades();
		}
		alteracao = false;
		redirect.redirecionaPaginaJSF(Constantes.PAGE_CADASTRO_TURMA);
	}
	
	public void pageConsulta(ActionEvent event) {
		iniciarEntidades();
		redirect.redirecionaPaginaJSF(Constantes.PAGE_CONSULTA_TURMA);
	}

	public void salvar(ActionEvent event) throws AplicacaoFacesException {
		try {
			
			if (turma.getId() != null) {
				redirectPageConsulta = true;
			}
			
			turmaBO.salvar(turma);
			mensagemSalvoSucesso();
			iniciarEntidades();
			
			if (redirectPageConsulta) {
				redirectPageConsulta = false;
				redirect.redirecionaPaginaJSF(Constantes.PAGE_CONSULTA_TURMA);
			}
			
		} catch (NegocioException e) {
			mensagemErro(e.getMessages());
		} catch (Exception e) {
			throw new AplicacaoFacesException(e);
		}
	}
	
	public void consultar(ActionEvent event) throws AplicacaoFacesException {
		try {
			List<Turma> lista = turmaBO.obterListaPorParametros(turma);
			turmas = new ListDataModel<Turma>(lista);
		} catch (NegocioException e) {
			mensagemErro(e.getMessages());
		} catch (Exception e) {
			throw new AplicacaoFacesException(e);
		}
	}
	
	public void alterar(ActionEvent event) {
		alteracao = true;
		turma = turmas.getRowData();
		pageCadastro(event);
	}
	
	public void excluir(ActionEvent event) throws AplicacaoFacesException {
		try {
			turmaBO.desativar(turmaSelecionada);
			mensagemExcluidoSucesso();
			consultar(event);
			iniciarEntidades();
		} catch (NegocioException e) {
			mensagemErro(e.getMessages());
		} catch (Exception e) {
			throw new AplicacaoFacesException(e);
		}
	}
	
	public DataModel<Turma> getTurmas() throws AplicacaoFacesException {
		if (!isNuloOuVazio(turma) && !isNuloOuVazio(turma.getDescricao())) {
			consultar(null);
		}
		return turmas;
	}
	
	/* ===================================
	 * 			GETTERS & SETTERS
	 * =================================== */
	
	public Turma getTurma() {
		return turma;
	}

	public void setTurma(Turma turma) {
		this.turma = turma;
	}

	public Turma getTurmaSelecionada() {
		return turmaSelecionada;
	}

	public void setTurmaSelecionada(Turma turmaSelecionada) {
		this.turmaSelecionada = turmaSelecionada;
	}

	public boolean isAlteracao() {
		return alteracao;
	}

	public void setAlteracao(boolean alteracao) {
		this.alteracao = alteracao;
	}
	
}
