package br.com.w2c.view;

import static br.com.chbandeira.utilitario.Util.isNuloOuVazio;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

import br.com.w2c.controller.business.SerieBO;
import br.com.w2c.view.exception.AplicacaoFacesException;
import br.com.w2c.exception.NegocioException;
import br.com.w2c.model.domain.Serie;
import br.com.w2c.util.Constantes;

/**
 * 
 * @author charlles
 * @since 08/09/2013
 */
@ManagedBean
@SessionScoped
public class SerieMB extends BaseMB {

	private Serie serie;
	private Serie serieSelecionada;
	private boolean alteracao;
	
	private DataModel<Serie> series;
	
	private SerieBO serieBO = getBean("serieBO");
	
	public SerieMB() {
		iniciarEntidades();
	}
	
	@Override
	protected void iniciarEntidades() {
		serie = new Serie();
		serieSelecionada = new Serie();
		series = new ListDataModel<Serie>();
	}
	
	public void pageCadastro(ActionEvent event) {
		if (!alteracao) {
			iniciarEntidades();
		}
		alteracao = false;
		redirect.redirecionaPaginaJSF(Constantes.PAGE_CADASTRO_SERIE);
	}
	
	public void pageConsulta(ActionEvent event) {
		iniciarEntidades();
		redirect.redirecionaPaginaJSF(Constantes.PAGE_CONSULTA_SERIE);
	}

	public void salvar(ActionEvent event) throws AplicacaoFacesException {
		try {
			
			if (serie.getId() != null) {
				redirectPageConsulta = true;
			}
			
			serieBO.salvar(serie);
			mensagemSalvoSucesso();
			iniciarEntidades();
			
			if (redirectPageConsulta) {
				redirectPageConsulta = false;
				redirect.redirecionaPaginaJSF(Constantes.PAGE_CONSULTA_SERIE);
			}
			
		} catch (NegocioException e) {
			mensagemErro(e.getMessages());
		} catch (Exception e) {
			throw new AplicacaoFacesException(e);
		}
	}
	
	public void consultar(ActionEvent event) throws AplicacaoFacesException {
		try {
			List<Serie> lista = serieBO.obterListaPorParametros(serie);
			series = new ListDataModel<Serie>(lista);
		} catch (NegocioException e) {
			mensagemErro(e.getMessages());
		} catch (Exception e) {
			throw new AplicacaoFacesException(e);
		}
	}
	
	public void alterar(ActionEvent event) {
		alteracao = true;
		serie = series.getRowData();
		pageCadastro(event);
	}
	
	public void excluir(ActionEvent event) throws AplicacaoFacesException {
		try {
			serieBO.desativar(serieSelecionada);
			mensagemExcluidoSucesso();
			consultar(event);
			iniciarEntidades();
		} catch (NegocioException e) {
			mensagemErro(e.getMessages());
		} catch (Exception e) {
			throw new AplicacaoFacesException(e);
		}
	}
	
	public DataModel<Serie> getSeries() throws AplicacaoFacesException {
		if (!isNuloOuVazio(serie) && !isNuloOuVazio(serie.getDescricao())) {
			consultar(null);
		}
		return series;
	}
	
	/* ===================================
	 * 			GETTERS & SETTERS
	 * =================================== */
	
	public Serie getSerie() {
		return serie;
	}

	public void setSerie(Serie serie) {
		this.serie = serie;
	}

	public Serie getSerieSelecionada() {
		return serieSelecionada;
	}

	public void setSerieSelecionada(Serie serieSelecionada) {
		this.serieSelecionada = serieSelecionada;
	}

	public boolean isAlteracao() {
		return alteracao;
	}

	public void setAlteracao(boolean alteracao) {
		this.alteracao = alteracao;
	}
	
}
