package br.com.chbandeira.utilitario;

import java.text.Normalizer;

/**
 * 
 * @author Charlles
 * @since 12/07/2014
 * @version 1.1
 */
public class UtilFormat {

	public static String somenteNumeros(String telefone) {
		return telefone.replaceAll("\\D", "");
	}
	
	public static String removerAcentos(String string) {
		return Normalizer.normalize(string, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
	}
	
}
