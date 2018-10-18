package br.com.w2c.controller.business;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import br.com.chbandeira.utilitario.Util;
import br.com.chbandeira.utilitario.UtilFile;
import br.com.w2c.exception.AplicacaoException;

/**
 * 
 * @author charlles
 * @since 19/10/2013
 */
@SuppressWarnings("rawtypes")
@Component
public class LogServidorBO extends BaseBO {
	
	private static Logger log = LogManager.getLogger();

	public InputStream getInputStream(String path) throws AplicacaoException {
		try {
			InputStream inputStream = UtilFile.getInputStream(path);
			if (Util.isNuloOuVazio(inputStream)) {
				inputStream = new FileInputStream(new File("logPresente.log"));
			}
			return inputStream;
		} catch (FileNotFoundException e) {
			log.error(this.getClass().getName(), e);
			throw new AplicacaoException(e);
		}
	}
	
}
