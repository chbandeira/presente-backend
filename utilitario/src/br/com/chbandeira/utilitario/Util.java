package br.com.chbandeira.utilitario;

import java.util.Collection;
import java.util.Date;
import java.util.Map;

/**
 * Classe utilitaria geral
 * @author charlles
 * @version 1.1 - 12/07/2014 
 * @since 27/03/2012
 */
public class Util {
	
	/*
	 * Metódos sobrecarregados para verificar se o objeto é vazio ou nulo
	 * Retorna true caso seja vazio ou nulo, retorna false caso contrário
	 */
	public static boolean isNuloOuVazio(String texto) {
		return texto == null || (texto.trim()).length() == 0;
	}

	public static boolean isNuloOuVazio(Object[] objeto) {
		return objeto == null || objeto.length == 0;
	}

	public static boolean isNuloOuVazio(Object objeto) {
		return objeto == null;
	}

	public static boolean isNuloOuVazio(char caractere) {
		Character character = new Character(caractere);
		return isNuloOuVazio(character.toString());
	}

	public static boolean isNuloOuVazio(Collection<?> lista) {
		return lista == null || lista.isEmpty();
	}

	public static boolean isNuloOuVazio(Map<?, ?> mapa) {
		return mapa == null || mapa.isEmpty();
	}

	public static boolean isNuloOuVazio(Date data) {
		return data == null || (data.toString().trim()).length() == 0;
	}

	public static boolean isNuloOuVazio(Double valor) {
		return valor == null || (valor.toString().trim()).length() == 0;
	}

	public static boolean isNuloOuVazio(Integer valor) {
		return valor == null || (valor.toString().trim()).length() == 0;
	}

	public static boolean isNuloOuVazio(Long valor) {
		return valor == null || (valor.toString().trim()).length() == 0;
	}
	
	public static boolean isNulo(Object objeto) {
		return objeto == null;
	}

	public static boolean isNuloOuZero(Number number) {
		return number == null || number.intValue() == 0;
	}
	
}