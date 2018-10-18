package br.com.chbandeira.utilitario;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 
 * @author Charlles
 * @since 12/07/2014
 */
public class UtilSecurity {

	public static String getSha256(String code) throws NoSuchAlgorithmException {
		MessageDigest md = MessageDigest.getInstance("SHA-256");
		md.update(code.getBytes());
		return new BigInteger(1, md.digest()).toString(16);
	}
	
}
