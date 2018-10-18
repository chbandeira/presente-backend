package br.com.w2c.view.exception;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import br.com.w2c.exception.AplicacaoException;
import br.com.w2c.util.Constantes;
import br.com.w2c.util.ResourceUtil;
import br.com.w2c.view.util.RedirecionaPagina;

/**
 * Classe de exceção para a aplicação em geral.
 * @author charlles
 *
 */
public class AplicacaoFacesException extends AplicacaoException {
	
	private static Logger log = LogManager.getLogger();

	private static final long serialVersionUID = 8140618691651897814L;

	/**
	 * Retorna o erro na página caso ocorra algum problema da aplicação que não tenha sido prevista.
	 * @param mensagem Mensagem a ser mostrada
	 * @throws AplicacaoException 
	 */
	public AplicacaoFacesException(String mensagem) throws AplicacaoException {
		super(mensagem);
		log.error(mensagem);
		String msg = ResourceUtil.getMessageSuport("SUPPORT004");
		FacesContext.getCurrentInstance().addMessage(null, 
	    		new FacesMessage(FacesMessage.SEVERITY_FATAL, msg.concat(mensagem), ""));
	}
	
	/**
	 * Retorna o erro redirecionando para a página caso ocorra algum problema da aplicação 
	 * que não tenha sido prevista.
	 * @param e Exceção ocorrida
	 */
	public AplicacaoFacesException(Throwable e) {
		super(e);
		log.error("Erro na aplicação. ", e);
		RedirecionaPagina.getInstance().redirecionaPaginaJSF(Constantes.PAGE_ERROR_APP);
	}
	
}
