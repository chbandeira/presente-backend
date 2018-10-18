package br.com.chbandeira.utilitario;

import org.apache.commons.lang.StringEscapeUtils;

/**
 * 
 * @author Charlles
 * @since 12/07/2014
 */
public class UtilHtml { 

	/**
	 * Ex: mat&amp;eacute;rias ou "mat&eacute;rias = matérias
	 * @return
	 */
	public static String decodeHtmlToString(String caractereEspecialHtml) {
		String string = StringEscapeUtils.unescapeHtml(caractereEspecialHtml);
		string = StringEscapeUtils.unescapeHtml(string);
		return string;
	}
	
	public static String encodeStringToHtml(String texto) {
		String string = StringEscapeUtils.escapeHtml(texto);
		return string;
	}
   
} 
