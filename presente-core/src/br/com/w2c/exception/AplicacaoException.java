package br.com.w2c.exception;


/**
 * 
 * @author Charlles
 * @since 20/06/2015
 */
public class AplicacaoException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public AplicacaoException(Throwable e) {
		super(e);
	}

	public AplicacaoException(String mensagem) {
		super(mensagem);
	}

}
