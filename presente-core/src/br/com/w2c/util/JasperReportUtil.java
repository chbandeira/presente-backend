package br.com.w2c.util;

import java.io.File;
import java.io.OutputStream;
import java.util.Map;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import br.com.chbandeira.utilitario.Util;

/**
 * 
 * @author charlles
 * @since 24/10/2013
 * @version 19/07/2015
 */
public class JasperReportUtil {

	private static JasperReportUtil instance;
	
	private JasperReportUtil() {}
	
	public static JasperReportUtil getInstance() {
		return Util.isNuloOuVazio(instance) ? new JasperReportUtil() : instance;
	}
	
	@Deprecated
	public void exportar(String sourceFileName, Map<String, Object> params, String destFileName) throws JRException {
		JasperPrint print = JasperFillManager.fillReport(sourceFileName, params, HibernateUtil.getConnection());
		File file = new File(destFileName);
		File parentFile = file.getParentFile();
		if (parentFile != null && !parentFile.exists()) {
			parentFile.mkdirs();
		}
		JasperExportManager.exportReportToPdfFile(print, destFileName);
	}

	public void exportarPdf(String sourceFileName, Map<String, Object> parametros, OutputStream outputStream) {
		try {
			JasperPrint jasperPrint = JasperFillManager.fillReport(sourceFileName, parametros, HibernateUtil.getConnection());
			JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);
		} catch (JRException e) {
			throw new RuntimeException(e);
		}
	}

}
