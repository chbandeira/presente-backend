package br.com.w2c.job;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.stereotype.Component;

/**
 * 
 * @author Charlles
 * @since 05/05/2014
 */
@Component
public class JobInit {

	private static Logger log = LogManager.getLogger();

	/**
	 * Agendando para atualizar dados da base
	 */
	static {
		try {
			
			Scheduler scheduler = new StdSchedulerFactory().getScheduler();
			scheduler.start();

//			Jobs jobEmail = enviarEmailJOB();
//			log.info("Registrando envio de EMAIL por JOB");
//			scheduler.scheduleJob(jobEmail.getJobDetail(), jobEmail.getTrigger());
//			log.info("Registrado envio de EMAIL por JOB");
//
//			Jobs jobSms = enviarSmsJOB();
//			log.info("Registrando envio de SMS por JOB");
//			scheduler.scheduleJob(jobSms.getJobDetail(), jobSms.getTrigger());
//			log.info("Registrado envio de SMS por JOB");
			
			registrarSchedulerEnvioSmsEmail(scheduler);
			
			registrarSchedulerSincronizacaoWeb(scheduler);

		} catch (SchedulerException se) {
			log.error(se);
		}
	}

	private static void registrarSchedulerSincronizacaoWeb(Scheduler scheduler) throws SchedulerException {
		Jobs jobSincronizacaoWeb = registrarJobSincronizacaoWeb();
		log.info("Registrando Sincronização Web por JOB");
		scheduler.scheduleJob(jobSincronizacaoWeb.getJobDetail(), jobSincronizacaoWeb.getTrigger());
		log.info("Registrado Sincronização Web por JOB");
	}

	private static void registrarSchedulerEnvioSmsEmail(Scheduler scheduler) throws SchedulerException {
		Jobs jobSmsEmail = registrarJobEnviarSmsEmail();
		log.info("Registrando envio de SMS e EMAIL por JOB");
		scheduler.scheduleJob(jobSmsEmail.getJobDetail(), jobSmsEmail.getTrigger());
		log.info("Registrado envio de SMS e EMAIL por JOB");
	}

	@SuppressWarnings("unused")
	@Deprecated
	private static Jobs enviarSmsJOB() {
		try {

			JobKey jobKey = new JobKey("jobKeyEnviarSmsJOB", "group1");
			JobDetail job = JobBuilder.newJob(EnviarSmsJOB.class)
					.withIdentity(jobKey).build();

			Trigger trigger = TriggerBuilder
					.newTrigger()
					.withIdentity("triggerEnviarSmsJOB", "group1")
					.withSchedule(
							CronScheduleBuilder.cronSchedule("0 0/10 * * * ?"))
					.build();
			
			return new Jobs(job, trigger);

		} catch (Exception e) {
			log.error(e);
		}
		return null;
	}

	@SuppressWarnings("unused")
	@Deprecated
	private static Jobs enviarEmailJOB() {
		try {
			
			JobKey jobKey = new JobKey("jobKeyEnviarEmailJOB", "group1");
			JobDetail job = JobBuilder.newJob(EnviarEmailJOB.class)
					.withIdentity(jobKey).build();

			Trigger trigger = TriggerBuilder
					.newTrigger()
					.withIdentity("triggerEnviarEmailJOB", "group1")
					.withSchedule(
							CronScheduleBuilder.cronSchedule("0 0/6 * * * ?"))
					.build();
			
			return new Jobs(job, trigger);

		} catch (Exception se) {
			log.error(se);
		}
		return null;
	}
	
	private static Jobs registrarJobEnviarSmsEmail() {
		try {
			
			JobKey jobKey = new JobKey("jobKeyEnviarSmsEmailJOB", "group1");
			JobDetail job = JobBuilder.newJob(EnviarSmsEmailJOB.class)
					.withIdentity(jobKey).build();

			Trigger trigger = TriggerBuilder
					.newTrigger()
					.withIdentity("triggerEnviarSmsEmailJOB", "group1")
					.withSchedule(
							CronScheduleBuilder.cronSchedule("0 0/5 * * * ?"))
					.build();
			
			return new Jobs(job, trigger);

		} catch (Exception se) {
			log.error(se);
		}
		return null;
	}

	
	/**
	 * Agendamento Sincronizacao Web
	 * @return
	 */
	private static Jobs registrarJobSincronizacaoWeb() {
		try {
			
			JobKey jobKey = new JobKey("jobKeySincronizacaoWebJOB", "group1");
			JobDetail job = JobBuilder.newJob(SincronizacaoWebJOB.class).withIdentity(jobKey).build();

			Trigger trigger = TriggerBuilder
					.newTrigger()
					.withIdentity("triggerSincronizacaoWebJOB", "group1")
					.withSchedule(CronScheduleBuilder.cronSchedule("0 0/7 * * * ?"))
					.build();
			
			return new Jobs(job, trigger);

		} catch (Exception se) {
			log.error(se);
		}
		return null;
	}
}

class Jobs {
	
	private JobDetail jobDetail;
	private Trigger trigger;

	public Jobs(JobDetail jobDetail, Trigger trigger) {
		this.jobDetail = jobDetail;
		this.trigger = trigger;
	}

	public JobDetail getJobDetail() {
		return jobDetail;
	}

	public void setJobDetail(JobDetail jobDetail) {
		this.jobDetail = jobDetail;
	}

	public Trigger getTrigger() {
		return trigger;
	}

	public void setTrigger(Trigger trigger) {
		this.trigger = trigger;
	}
	
}
