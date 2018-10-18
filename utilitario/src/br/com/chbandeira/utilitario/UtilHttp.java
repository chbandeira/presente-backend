package br.com.chbandeira.utilitario;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * 
 * @author Charlles
 * @since 29/05/2014
 */
public class UtilHttp {

	public static InputStream getInputStream(String stringUrl) throws IOException {
		URL url = new URL(stringUrl);
		HttpURLConnection conexao = (HttpURLConnection) url.openConnection();
		conexao.setRequestMethod("GET");
		conexao.setDoInput(true);
		conexao.connect();
		InputStream is = conexao.getInputStream();
		return is;
	}
	
	public static void downloadFile(String url, String destino) throws IOException {
		
		System.out.println("Iniciando download do arquivo " + destino);
		
		URL _url = new URL(url);

		InputStream is = _url.openStream();
		FileOutputStream fos = new FileOutputStream(destino);

		int bytes = 0;

		while ((bytes = is.read()) != -1) {
			fos.write(bytes);
		}

		is.close();
		fos.close();
		
		System.out.println("Finalizando download do arquivo " + destino);
	}
	
}
