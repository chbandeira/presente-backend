package br.com.w2c.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Scope;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

/**
 * 
 * @author charlles
 * @since 14/09/2013
 */
@Scope("singleton")
@Component
public class SpringUtil implements ApplicationContextAware {

	private static ApplicationContext context = new ClassPathXmlApplicationContext();

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		initializeApplicationContext(applicationContext);
	}

	private static void initializeApplicationContext(ApplicationContext applicationContext) {
		context = applicationContext;
	}

	@SuppressWarnings("unchecked")
	public static <T extends Object> T getBean(String idObject) {
		return (T) context.getBean(idObject);
	}

}
