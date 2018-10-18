package br.com.w2c.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Criptografador de textos
 * @author charlles bandeira
 * @version 1.2 - 04/03/2012
 * @since 04/12/2011
 */
public class CriptografadorUtil {	
	
	private Logger log = LogManager.getLogger();
	
	public static final String MD5 = "MD5";
	public static final String SHA_256 = "SHA-256";
	public static final String SHA_512 = "SHA-512";
	private static CriptografadorUtil instance;
	
	private CriptografadorUtil() {};
	
	public static CriptografadorUtil getInstance() {
		return instance == null ? new CriptografadorUtil() : instance;
	}
	
	/**
	 * Criptografa textos.
	 * @param texto Texto a ser criptografada
	 * @param algoritimo Algoritimo do tipo MD5, SHA-512 entre outros
	 * @return Texto criptografada.
	 */
	public String criptografar(String texto, String algoritimo) {
		return criptografarTexto(texto, algoritimo);
	}

	/**
	 * Criptografa textos em MD5.
	 * @param texto Texto a ser criptografada
	 * @return Texto criptografada.
	 */
	public String criptografarMD5(String texto) {
		return criptografarTexto(texto, CriptografadorUtil.MD5);
	}

	/**
	 * Criptografa textos em SHA-256.
	 * @param texto Texto a ser criptografada
	 * @return Texto criptografada.
	 */
	public String criptografarSHA256(String texto) {
		return criptografarTexto(texto, CriptografadorUtil.SHA_256);
	}

	/**
	 * Criptografa textos em SHA-512.
	 * @param texto Texto a ser criptografada
	 * @return Texto criptografada.
	 */
	public String criptografarSHA512(String texto) {
		return criptografarTexto(texto, CriptografadorUtil.SHA_512);
	}
	
	/**
	 * Criptografa textos.
	 * @param texto Texto a ser criptografada
	 * @param algoritimo Algoritimo do tipo MD5, SHA-512 entre outros
	 * @return Texto criptografada.
	 */
	private String criptografarTexto(String texto, String algoritimo) {
		String out = "";
		MessageDigest md;
		try {
			md = MessageDigest.getInstance(algoritimo);
			md.update(texto.getBytes());

			byte[] bs = md.digest();
			for (int i = 0; i < bs.length; i++) {
				byte temp = bs[i];
				String s = Integer.toHexString(temp);
				while (s.length() < 2) {
					s = "0" + s;
				}
				out += s.substring(s.length() - 2);
			}

		} catch (NoSuchAlgorithmException e) {
			log.error("Erro ao tentar criptografar texto.", e);
		}
		return out;
	}
	
	public static void main(String[] args) {
		String sha256 = CriptografadorUtil.getInstance().criptografarSHA256("11111111111");
		System.out.println(sha256);
	}

}