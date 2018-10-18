package br.com.w2c.sinc;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

/**
 * 
 * @author Charlles
 * @since 19/01/2016
 */
@WebService(targetNamespace="http://www.oryontec.com.br", name="Sincronizacao")
public interface SincronizacaoService {

	@WebMethod
	String enviarPacote(@WebParam(name="pacote") String pacote);
	
}
