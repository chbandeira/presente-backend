package br.com.w2c.jasperreport.util;

/**
 * 
 * @author Charlles
 * @since 25/02/2017
 */
public class Like {
	
	private static final String PERCENT = "%";

	public static String colocarNoInicio(String texto) {
		return new StringBuilder().append(PERCENT).append(texto).toString();
	}
	
	public static String colocarNoFinal(String texto) {
		return new StringBuilder(texto).append(PERCENT).toString();
	}
	
	public static String colocarNoInicioEFinal(String texto) {
		return new StringBuilder().append(PERCENT).append(texto).append(PERCENT).toString();
	}

	public static String semTexto() {
		return PERCENT;
	}
	
}
