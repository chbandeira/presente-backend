package br.com.w2c.view;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;

import br.com.w2c.util.Constantes;

/**
 * 
 * @author charlles
 * @since 10/05/2014
 */
@ManagedBean
@SessionScoped
public class ManualMB extends BaseMB {

	public void pageCompleto(ActionEvent event) {
		redirect.redirecionaPaginaJSF(Constantes.PAGE_MANUAL_COMPLETO);
	}

}