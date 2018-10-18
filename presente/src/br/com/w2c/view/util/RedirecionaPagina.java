package br.com.w2c.view.util;

import java.io.IOException;

import javax.faces.context.FacesContext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Classe que controla o redirecionamentos de páginas JSF
 * 
 * @author charlles
 * @version 2.0 - 07/09/2013
 * @since - 21/02/2012
 */
public class RedirecionaPagina {

	private Logger log = LogManager.getLogger();
	
	private static RedirecionaPagina instance;
	
	private RedirecionaPagina() {}

	/**
	 * @return instancia do objeto
	 */
	public static RedirecionaPagina getInstance() {
		return instance == null ? new RedirecionaPagina() : instance;
	}

	/**
	 * Redireciona para a página JSF desejada
	 * 
	 * @param pagina String com a url da página
	 * @throws IOException
	 */
	public void redirecionaPaginaJSF(String pagina) {
		try {
			String projeto = FacesContext.getCurrentInstance().getExternalContext().getContextName();
			FacesContext.getCurrentInstance().getExternalContext().redirect("/" + projeto + pagina);
		} catch (IOException e) {
			log.error(this.getClass().getName(), e);
		}
	}
	
}
