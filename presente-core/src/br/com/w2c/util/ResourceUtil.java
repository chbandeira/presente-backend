package br.com.w2c.util;

import java.text.MessageFormat;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import br.com.w2c.exception.AplicacaoException;

/**
 * 
 * @author charlles
 * @since 08/09/2013
 * @version 21/07/2015
 */
public class ResourceUtil {
	
	private static final Logger log = LogManager.getLogger();
	
	private static final ClassLoader CLASS_LOADER = new ResourceUtil().getClass().getClassLoader();

	private static final String RESOURCE_QUERIES_SQL_XML_PROPERTIES = "queries_sql_xml.properties";
	
	private static final String RESOURCE_LABEL_PT_BR = "label_pt_BR.properties";
	private static final String RESOURCE_MESSAGE_PT_BR = "message_pt_BR.properties";
	private static final String RESOURCE_MESSAGE_SUPPORT = "message_support_pt_BR.properties";
	private static final String RESOURCE_ORM = "orm.properties";
	private static final String RESOURCE_PATH = "path.properties";
	
	/**
	 * Obtém propriedades do label_pt_BR.properties
	 * @param chave Chave a ser obtida o valor
	 * @return ResourceBundle
	 * @throws AplicacaoException 
	 */
	public static String getLabel(String key) throws AplicacaoException {
		return getProperty(key, RESOURCE_LABEL_PT_BR);
	}
	
	/**
	 * Obtém propriedades do message_pt_BR.properties
	 * @param chave Chave a ser obtida o valor
	 * @return ResourceBundle
	 * @throws AplicacaoException 
	 */
	public static String getMessage(String key) throws AplicacaoException {
		return getProperty(key, RESOURCE_MESSAGE_PT_BR);
	}
	
	public static String getMessage(String key, Object... parametros) throws AplicacaoException {
		return MessageFormat.format(getProperty(key, RESOURCE_MESSAGE_PT_BR), parametros);
	}

	public static String getMessageSuport(String key) throws AplicacaoException {
		return getProperty(key, RESOURCE_MESSAGE_SUPPORT);
	}
	
	public static String getPath(String key) throws AplicacaoException {
		return getProperty(key, RESOURCE_PATH);
	}
	
	public static String getQueryORM(String key) throws AplicacaoException {
		return getProperty(key, RESOURCE_ORM);
	}
	
	public static String getQuerySQL(String key) throws AplicacaoException {
		return getPropertyXml(key, RESOURCE_QUERIES_SQL_XML_PROPERTIES);
	}

	private static String getPropertyXml(String key, String resource) throws AplicacaoException {
		try {
			Properties properties = new Properties();
			properties.loadFromXML(CLASS_LOADER.getResourceAsStream(resource));
			return properties.getProperty(key);
		} catch (Exception e) {
			log.error(e);
			throw new AplicacaoException(e);
		}
	}

	private static String getProperty(String key, String resource) throws AplicacaoException {
		try {
			Properties properties = new Properties();
			properties.load(CLASS_LOADER.getResourceAsStream(resource));
			return properties.getProperty(key);
		} catch (Exception e) {
			log.error(e);
			throw new AplicacaoException(e);
		}
	}
	
}
