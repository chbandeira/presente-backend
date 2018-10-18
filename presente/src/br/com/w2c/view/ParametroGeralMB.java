package br.com.w2c.view;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;

import br.com.w2c.controller.business.ParametroGeralBO;
import br.com.w2c.exception.AplicacaoException;
import br.com.w2c.exception.NegocioException;
import br.com.w2c.model.wrapper.ParametroGeralWrapper;
import br.com.w2c.util.Constantes;

/**
 * 
 * @author charlles
 * @since 19/10/2013
 */
@ManagedBean
@SessionScoped
public class ParametroGeralMB extends BaseMB {
	
	private ParametroGeralWrapper wrapper;
	
	private ParametroGeralBO parametroGeralBO = getBean("parametroGeralBO"); 

	public void pageParametroGeral(ActionEvent event) throws AplicacaoException {
		wrapper = parametroGeralBO.obterParametrosConfiguracao();
		redirect.redirecionaPaginaJSF(Constantes.PAGE_PARAMETRO_GERAL);
	}
	
	public void salvar(ActionEvent event) {
		try {
			parametroGeralBO.salvar(wrapper);
			mensagemSalvoSucesso();
		} catch (NegocioException e) {
			mensagemErro(e.getMessages());
		} catch (Exception e) {
			mensagemFatal("Erro na aplicação");
		}
	}

	public ParametroGeralWrapper getWrapper() {
		return wrapper;
	}

	public void setWrapper(ParametroGeralWrapper wrapper) {
		this.wrapper = wrapper;
	}
	
}
