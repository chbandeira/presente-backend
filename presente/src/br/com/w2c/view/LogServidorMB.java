package br.com.w2c.view;

import java.io.InputStream;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.w2c.controller.business.LogServidorBO;
import br.com.w2c.util.Constantes;
import br.com.w2c.util.ResourceUtil;
import br.com.w2c.view.exception.AplicacaoFacesException;

/**
 * 
 * @author charlles
 * @since 19/10/2013
 */
@ManagedBean
@SessionScoped
public class LogServidorMB extends BaseMB {

	@Autowired
	private LogServidorBO logServidorBO = getBean("logServidorBO");
	
	public void pageBaixarLog(ActionEvent event) {
		redirect.redirecionaPaginaJSF(Constantes.PAGE_BAIXAR_LOG);
	}
	
	public StreamedContent getFile() throws AplicacaoFacesException {
		StreamedContent file = null;
		InputStream stream = null;
		try {
			String pathLogServidor = ResourceUtil.getPath(Constantes.PATH_LOG_SERVIDOR);
			stream = logServidorBO.getInputStream(pathLogServidor);
			file = new DefaultStreamedContent(stream, "text/plan", pathLogServidor);  
		} catch (Exception e) {
			throw new AplicacaoFacesException(e);
		}
		return file;
	}
	
}
