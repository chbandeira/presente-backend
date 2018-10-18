package br.com.w2c.util;

import java.io.File;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.SQLException;
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
	public void exportar(String sourceFileName, Map<String, Object> params, String destFileName) throws JRException, SQLException {
		Connection conn = null;
		try {
			conn = HibernateUtil.getConnection();
			JasperPrint print = JasperFillManager.fillReport(sourceFileName, params, conn);
			File file = new File(destFileName);
			File parentFile = file.getParentFile();
			if (parentFile != null && !parentFile.exists()) {
				parentFile.mkdirs();
			}
			JasperExportManager.exportReportToPdfFile(print, destFileName);
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
	}

	public void exportarPdf(String sourceFileName, Map<String, Object> parametros, OutputStream outputStream) throws SQLException {
		Connection conn = null;
		try {
			conn = HibernateUtil.getConnection();
			JasperPrint jasperPrint = JasperFillManager.fillReport(sourceFileName, parametros, conn);
			JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);
		} catch (JRException e) {
			throw new RuntimeException(e);
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
	}

}
