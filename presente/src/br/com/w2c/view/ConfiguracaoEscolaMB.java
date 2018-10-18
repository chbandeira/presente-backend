package br.com.w2c.view;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;

import br.com.w2c.controller.business.ConfiguracaoEscolaBO;
import br.com.w2c.exception.AplicacaoException;
import br.com.w2c.exception.NegocioException;
import br.com.w2c.model.domain.ConfiguracaoEscola;
import br.com.w2c.util.Constantes;
import br.com.w2c.view.exception.AplicacaoFacesException;

/**
 * 
 * @author charlles
 * @since 22/10/2013
 */
@ManagedBean
@SessionScoped
public class ConfiguracaoEscolaMB extends BaseMB {
	
	private ConfiguracaoEscola configuracaoEscola;
	
	private ConfiguracaoEscolaBO configuracaoEscolaBO = getBean("configuracaoEscolaBO"); 

	public ConfiguracaoEscolaMB() {
		iniciarEntidades();
	}
	
	protected void iniciarEntidades() {
		configuracaoEscola = new ConfiguracaoEscola();
	}
	
	public void pageConfiguracaoEscola(ActionEvent event) throws AplicacaoException {
		configuracaoEscola = configuracaoEscolaBO.obterConfiguracaoEscola();
		redirect.redirecionaPaginaJSF(Constantes.PAGE_CONFIGURACAO_ESCOLA);
	}
	
	public void salvar(ActionEvent event) throws AplicacaoFacesException {
		try {
			configuracaoEscolaBO.salvar(configuracaoEscola);
			mensagemSalvoSucesso();
		} catch (NegocioException e) {
			mensagemErro(e.getMessages());
		} catch (Exception e) {
			throw new AplicacaoFacesException(e);
		}
	}

	public ConfiguracaoEscola getConfiguracaoEscola() {
		return configuracaoEscola;
	}

	public void setConfiguracaoEscola(ConfiguracaoEscola configuracaoEscola) {
		this.configuracaoEscola = configuracaoEscola;
	}

}
