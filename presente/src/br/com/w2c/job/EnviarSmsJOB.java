package br.com.w2c.job;

import static br.com.chbandeira.utilitario.Util.isNuloOuVazio;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
//import org.smslib.OutboundMessage;

import br.com.w2c.controller.business.RegistroBO;
import br.com.w2c.model.domain.Registro;
import br.com.w2c.util.Constantes;
import br.com.w2c.util.SpringUtil;
//import br.com.w2c.util.sms.EnviarSMS;
import br.com.w2c.util.sms.HttpPostComteleSMS;

/**
 * 
 * @author Charlles
 * @since 05/05/2014
 */
public class EnviarSmsJOB implements Job {

	private static Logger log = LogManager.getLogger();
	
	private RegistroBO registroBO;
	
	private HttpPostComteleSMS comteleSMS = new HttpPostComteleSMS();
	
	private List<Registro> registros = new ArrayList<Registro>();
	
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		try {
			registroBO = SpringUtil.getBean("registroBO");
		
			Map<Registro, String> map = registroBO.obterSmsNaoEnviado();
			
			if (!isNuloOuVazio(map)) {
				log.info("Iniciando envio de SMS em lote");
				for (Entry<Registro, String> entry : map.entrySet()) {
					String telefoneCelular = entry.getKey().getResponsavelTelefoneCelular();
					if (!isNuloOuVazio(telefoneCelular)) {
						String celular = telefoneCelular.replace("-", "");
						celular = celular.replace("(", "");
						celular = celular.replace(")", "");
						if (celular.length()==10) {
							celular = Constantes.DDI_LOCAL+celular;
						}
						//556199998888
						if (celular.length()==12) {
							try {
								comteleSMS.enviarSMS(entry.getValue(), celular);
								registros.add(entry.getKey());
							} catch (Exception e) {
								log.error("Erro ao enviar SMS para: "+celular, e);
							}
						}
					}
				}
				if (!isNuloOuVazio(registros)) {
					registroBO.salvarEnvioSMS(registros);
				}
				log.info("Finalizando envio de EMAIL em lote");
			}
			
		} catch (Exception e) {
			log.error("Erro ao enviar sms em lote", e);
		}
	}

}
