package br.com.w2c.exception;

import java.util.ArrayList;
import java.util.List;

/**
 * @author charlles
 */
public class NegocioException extends Exception {

	private static final long serialVersionUID = 1L;
	
	private List<String> mensagens = new ArrayList<String>();
	
	public NegocioException(List<String> mensagens) {
		this.mensagens = mensagens;
	}
	
	public List<String> getMessages() {
		return mensagens;
	}

}
