package br.com.w2c.sinc;

import javax.jws.WebService;

import br.com.w2c.controller.business.SincronizacaoBO;
import br.com.w2c.util.SpringUtil;


/**
 * 
 * @author Charlles
 * @since 20/01/2016
 */
@WebService(portName = "SincronizacaoServicePort", serviceName = "SincronizacaoService", 
	targetNamespace = "http://www.oryontec.com.br", endpointInterface="br.com.w2c.sinc.SincronizacaoService")
public class SincronizacaoServiceImpl implements SincronizacaoService {

	
	@Override
	public String enviarPacote(String pacote) {
		SincronizacaoBO sincronizacaoBO = SpringUtil.getBean("sincronizacaoBO");
		String retorno = sincronizacaoBO.sincronizarWeb(pacote);
		return retorno;
	}

}
