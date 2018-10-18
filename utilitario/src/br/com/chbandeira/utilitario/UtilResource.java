package br.com.chbandeira.utilitario;

import java.text.MessageFormat;
import java.util.ResourceBundle;

/**
 * 
 * @author charlles
 * @since 12/07/2014
 */
public class UtilResource {

	public static String getValue(ResourceBundle resourceBundle, String chave) {
		return resourceBundle.getString(chave);
	}
	
	public static String getValue(ResourceBundle resourceBundle, String chave, Object... parametros) {
		return MessageFormat.format(resourceBundle.getString(chave), parametros);
	}

}
