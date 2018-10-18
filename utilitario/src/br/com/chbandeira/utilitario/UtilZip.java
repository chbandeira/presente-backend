package br.com.chbandeira.utilitario;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/**
 * 
 * @author Charlles
 * @since 12/07/2014
 */
public class UtilZip {

	@SuppressWarnings("rawtypes")
	public static void unzip(String arquivoZip, String destino) {
		Enumeration entries;
		ZipFile zipFile;
		
		File file = new File(destino);
		if (!file.exists()) {
			file.mkdirs();
			System.out.println("Diretorio " + destino + " criado.");
		}

		if (arquivoZip == null) {
			System.err.println("Sintaxe do programa: Unzip arquivo");
			return;
		}
		try {
			zipFile = new ZipFile(arquivoZip);
			entries = zipFile.entries();
			while (entries.hasMoreElements()) {
				ZipEntry entry = (ZipEntry) entries.nextElement();
				if (entry.isDirectory()) {
					System.err.println("Descompactando diretório: " + entry.getName());
					(new File(entry.getName())).mkdir();
					continue;
				}
				
				System.out.println("Descompactando arquivo:" + entry.getName());
				copyInputStream(
						zipFile.getInputStream(entry),
						new BufferedOutputStream(new FileOutputStream(destino + entry.getName())));
			}
			zipFile.close();
			System.out.println("Arquivos descompactados em: " + destino);
		} catch (IOException ioe) {
			System.err.println("Erro ao descompactar:" + ioe.getMessage());
			return;
		}
	}
	
	private static final void copyInputStream(InputStream in, OutputStream out)
			throws IOException {
		byte[] buffer = new byte[1024];
		int len;
		while ((len = in.read(buffer)) >= 0)
			out.write(buffer, 0, len);
		in.close();
		out.close();
	}
	
}
