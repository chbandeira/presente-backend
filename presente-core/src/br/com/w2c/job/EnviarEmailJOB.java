package br.com.w2c.job;

import static br.com.chbandeira.utilitario.Util.isNuloOuVazio;
import static br.com.chbandeira.utilitario.UtilEmail.isEmailValido;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import br.com.w2c.controller.business.RegistroBO;
import br.com.w2c.model.domain.Registro;
import br.com.w2c.model.wrapper.EmailWrapper;
import br.com.w2c.util.SpringUtil;
import br.com.w2c.util.email.EnviarEmail;

/**
 * 
 * @author Charlles
 * @since 09/05/2014
 */
public class EnviarEmailJOB implements Job {

	private static Logger log = LogManager.getLogger();
	
	private RegistroBO registroBO;
	
	private List<Registro> registros = new ArrayList<Registro>();
	
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		try {
			registroBO = SpringUtil.getBean("registroBO");
		
			Map<Registro, EmailWrapper> map = registroBO.obterEmailNaoEnviado();
			
			if (!isNuloOuVazio(map)) {
				log.info("Iniciando envio de EMAIL em lote");
				for (Entry<Registro, EmailWrapper> entry : map.entrySet()) {
					String email = entry.getValue().getDestinatario();
					if (!isNuloOuVazio(email) && isEmailValido(email)) {
						try {
							EnviarEmail.enviarEmail(entry.getValue());
							registros.add(entry.getKey());
						} catch (Exception e) {
							log.error("Erro ao enviar email para: "+email, e);
						}
					}
				}
				if (!isNuloOuVazio(registros)) {
					registroBO.salvarEnvioEmail(registros);
				}
				log.info("Finalizando envio de EMAIL em lote");
			}
			
		} catch (Exception e) {
			log.error("Erro ao enviar email em lote", e);
		}
	}

}
