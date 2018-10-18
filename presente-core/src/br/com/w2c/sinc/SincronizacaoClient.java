package br.com.w2c.sinc;

import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;

/**
 * Utilizada para acessar serviços
 * @author Charlles
 * @since 19/01/2016
 */
public class SincronizacaoClient {

	/**
	 * 
	 * @return
	 * @throws MalformedURLException
	 */
	public static SincronizacaoService criar() throws MalformedURLException {
		
		URL url = new URL("http://www.oryontec.com.br/presente-online/SincronizacaoService?wsdl");
//		URL url = new URL("http://localhost:8080/presente-online/SincronizacaoService?wsdl");
        QName qname = new QName("http://www.oryontec.com.br", "SincronizacaoService");
        Service ws = Service.create(url, qname);
        SincronizacaoService sinc = ws.getPort(SincronizacaoService.class);
        
        return sinc;
	}
	
}
