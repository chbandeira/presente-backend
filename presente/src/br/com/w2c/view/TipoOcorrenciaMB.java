package br.com.w2c.view;

import static br.com.chbandeira.utilitario.Util.isNuloOuVazio;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

import br.com.w2c.controller.business.TipoOcorrenciaBO;
import br.com.w2c.view.exception.AplicacaoFacesException;
import br.com.w2c.exception.NegocioException;
import br.com.w2c.model.domain.TipoOcorrencia;
import br.com.w2c.util.Constantes;

/**
 * 
 * @author charlles
 * @since 19/05/2015
 */
@ManagedBean
@SessionScoped
public class TipoOcorrenciaMB extends BaseMB {

	private TipoOcorrencia tipoOcorrencia;
	private TipoOcorrencia tipoOcorrenciaSelecionada;
	private boolean alteracao;
	
	private DataModel<TipoOcorrencia> tipoOcorrencias;
	
	private TipoOcorrenciaBO tipoOcorrenciaBO = getBean("tipoOcorrenciaBO");
	
	public TipoOcorrenciaMB() {
		iniciarEntidades();
	}
	
	@Override
	protected void iniciarEntidades() {
		tipoOcorrencia = new TipoOcorrencia();
		tipoOcorrenciaSelecionada = new TipoOcorrencia();
		tipoOcorrencias = new ListDataModel<TipoOcorrencia>();
	}
	
	public void pageCadastro(ActionEvent event) {
		if (!alteracao) {
			iniciarEntidades();
		}
		alteracao = false;
		redirect.redirecionaPaginaJSF(Constantes.PAGE_CADASTRO_TIPO_OCORRENCIA);
	}
	
	public void pageConsulta(ActionEvent event) {
		iniciarEntidades();
		redirect.redirecionaPaginaJSF(Constantes.PAGE_CONSULTA_TIPO_OCORRENCIA);
	}

	public void salvar(ActionEvent event) throws AplicacaoFacesException {
		try {
			
			if (tipoOcorrencia.getId() != null) {
				redirectPageConsulta = true;
			}
			
			tipoOcorrenciaBO.salvar(tipoOcorrencia);
			mensagemSalvoSucesso();
			iniciarEntidades();
			
			if (redirectPageConsulta) {
				redirectPageConsulta = false;
				redirect.redirecionaPaginaJSF(Constantes.PAGE_CONSULTA_TIPO_OCORRENCIA);
			}
			
		} catch (NegocioException e) {
			mensagemErro(e.getMessages());
		} catch (Exception e) {
			throw new AplicacaoFacesException(e);
		}
	}
	
	public void consultar(ActionEvent event) throws AplicacaoFacesException {
		try {
			List<TipoOcorrencia> lista = tipoOcorrenciaBO.obterListaPorParametros(tipoOcorrencia);
			tipoOcorrencias = new ListDataModel<TipoOcorrencia>(lista);
		} catch (NegocioException e) {
			mensagemErro(e.getMessages());
		} catch (Exception e) {
			throw new AplicacaoFacesException(e);
		}
	}
	
	public void alterar(ActionEvent event) {
		alteracao = true;
		tipoOcorrencia = tipoOcorrencias.getRowData();
		pageCadastro(event);
	}
	
	public void excluir(ActionEvent event) throws AplicacaoFacesException {
		try {
			tipoOcorrenciaBO.desativar(tipoOcorrenciaSelecionada);
			mensagemExcluidoSucesso();
			consultar(event);
			iniciarEntidades();
		} catch (NegocioException e) {
			mensagemErro(e.getMessages());
		} catch (Exception e) {
			throw new AplicacaoFacesException(e);
		}
	}
	
	public DataModel<TipoOcorrencia> getTipoOcorrencias() throws AplicacaoFacesException {
		if (!isNuloOuVazio(tipoOcorrencia) && !isNuloOuVazio(tipoOcorrencia.getDescricao())) {
			consultar(null);
		}
		return tipoOcorrencias;
	}
	
	/* ===================================
	 * 			GETTERS & SETTERS
	 * =================================== */
	
	public TipoOcorrencia getTipoOcorrencia() {
		return tipoOcorrencia;
	}

	public void setTipoOcorrencia(TipoOcorrencia tipoOcorrencia) {
		this.tipoOcorrencia = tipoOcorrencia;
	}

	public TipoOcorrencia getTipoOcorrenciaSelecionada() {
		return tipoOcorrenciaSelecionada;
	}

	public void setTipoOcorrenciaSelecionada(TipoOcorrencia tipoOcorrenciaSelecionada) {
		this.tipoOcorrenciaSelecionada = tipoOcorrenciaSelecionada;
	}

	public boolean isAlteracao() {
		return alteracao;
	}

	public void setAlteracao(boolean alteracao) {
		this.alteracao = alteracao;
	}
	
}
