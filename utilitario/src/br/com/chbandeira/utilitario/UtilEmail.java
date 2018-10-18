package br.com.chbandeira.utilitario;

/**
 * 
 * @author Charlles
 * @since 12/07/2014
 */
public class UtilEmail {

	public static boolean isEmailValido(Object value) {
		return value != null && !value.toString().equals("") && value.toString().contains("@");
	}
	
}
