package br.com.chbandeira.utilitario;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Scanner;

/**
 * 
 * @author Charlles
 * @since 29/05/2014
 */
public class UtilFile {

	public static String convertStreamToString(InputStream is) throws IOException {  
		  
        if (is != null) {  
            StringBuilder sb = new StringBuilder();  
            String line;  
  
            try {  
                BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));  
                while ((line = reader.readLine()) != null) {  
                    sb.append(line).append("\n");  
                }  
            } finally {  
                is.close();  
            }  
            return sb.toString();  
        } else {          
            return "";  
        }  
    }
	
	public static InputStream getInputStream(String path) throws FileNotFoundException {
		File file = new File(path);
		if(file.exists()){
			return new FileInputStream(file);
		}
		return null;
	}
	
	public static StringBuilder getStringFromFile(String arquivo) {
		StringBuilder sb = new StringBuilder();
		try { 
			FileReader arq = new FileReader(arquivo); 
			BufferedReader lerArq = new BufferedReader(arq); 
			String linha = lerArq.readLine(); 
			while (linha != null) { 
				sb.append(linha);
				sb.append("\n");
				linha = lerArq.readLine();
			} 
			arq.close(); 
		} catch (IOException e) { 
			System.err.printf("Erro na abertura do arquivo: " + arquivo, e.getMessage()); 
		}
		return sb; 
	}
	
	public static Scanner getScannerFromFile(String arquivo) {
		StringBuilder sb = new StringBuilder();
		try { 
			FileReader arq = new FileReader(arquivo); 
			BufferedReader lerArq = new BufferedReader(arq); 
			String linha = lerArq.readLine(); 
			while (linha != null) { 
				sb.append(linha);
				sb.append("\n");
				linha = lerArq.readLine();
			} 
			arq.close(); 
		} catch (IOException e) { 
			System.err.printf("Erro na abertura do arquivo: " + arquivo, e.getMessage()); 
		}
		return new Scanner(sb.toString()); 
	}
	
	public static Scanner getScannerFromFile(File file) {
		StringBuilder sb = new StringBuilder();
		try { 
			FileReader arq = new FileReader(file); 
			BufferedReader lerArq = new BufferedReader(arq); 
			String linha = lerArq.readLine(); 
			while (linha != null) { 
				sb.append(linha);
				sb.append("\n");
				linha = lerArq.readLine();
			} 
			arq.close(); 
		} catch (IOException e) { 
			System.err.printf("Erro na abertura do arquivo: " + file.getName(), e.getMessage()); 
		}
		return new Scanner(sb.toString()); 
	}
	
	public static BufferedReader getBufferedReader(String arquivo, String charset) throws IOException {
		
		FileInputStream fsr = null;
		InputStreamReader isr = null;
		try { 
			File file = new File(arquivo);
			fsr = new FileInputStream(file);
			isr = new InputStreamReader(fsr, charset);
			BufferedReader lerArq = new BufferedReader(isr); 
			return lerArq;
		} catch (IOException e) { 
			System.err.printf("Erro na abertura do arquivo: " + arquivo, e.getMessage());
		}
		return null;
	}
	
	public static BufferedReader getBufferedReader(File file, String charset) throws IOException {
		
		FileInputStream fsr = null;
		InputStreamReader isr = null;
		try { 
			fsr = new FileInputStream(file);
			isr = new InputStreamReader(fsr, charset);
			BufferedReader lerArq = new BufferedReader(isr); 
			return lerArq;
		} catch (IOException e) { 
			System.err.printf("Erro na abertura do arquivo: " + file.getName(), e.getMessage());
		}
		return null;
	}
}
