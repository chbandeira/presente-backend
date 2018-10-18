package br.com.w2c.util.sms;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import org.jfree.util.Log;
import org.springframework.stereotype.Component;

import br.com.w2c.controller.business.ConfiguracaoEscolaBO;
import br.com.w2c.model.domain.ConfiguracaoEscola;
import br.com.w2c.util.SpringUtil;

@Component
public class HttpPostComteleSMS {

	private static final String AUTHORITY = "sms.comtele.com.br";
	//TODO chave comtele
	private static final String CHAVE = "-"/*ChavePrivada.CHAVE*/;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		HttpPostComteleSMS http = new HttpPostComteleSMS();
		
		try{
			http.sendPost("conteudo", "destinatario");
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
	}

	public void enviarSMSThread(final String conteudo, final String destinatario) {
		
		Thread thread = new Thread(new Runnable() {
			
			@Override
			public void run() {
				HttpPostComteleSMS sms = new HttpPostComteleSMS();
				try {
					sms.sendPost(conteudo, destinatario);
				} catch (Exception e) {
					System.err.println("Erro ao enviar sms.");
					e.printStackTrace();
				}
			}
		});
		thread.start();
	}
	
	public void enviarSMS(final String conteudo, final String destinatario) throws Exception {
		HttpPostComteleSMS sms = new HttpPostComteleSMS();
		try {
			int retorno = sms.sendPost(conteudo, destinatario);
			if (retorno != 200) {
				throw new Exception();
			}
		} catch (Exception e) {
			System.err.println("Erro ao enviar sms.");
			e.printStackTrace();
			throw e;
		}
	}
	
	private int sendPost(String conteudo, String destinatario) throws Exception {
		
		ConfiguracaoEscolaBO configuracaoEscolaBO = SpringUtil.getBean("configuracaoEscolaBO");
		ConfiguracaoEscola configuracaoEscola = configuracaoEscolaBO.obterConfiguracaoEscola();
		
		String parameters = "sender=" + configuracaoEscola.getIdentificador() + "&content=" + conteudo + "&receivers=" + destinatario;
		
		Log.info("Identificador: " + configuracaoEscola.getIdentificador());
		
		URI uri = new URI("https", AUTHORITY,
				"/Api/" + CHAVE + "/SendMessage",
				"sender=" + configuracaoEscola.getIdentificador() + "&content=" + conteudo	+ "&receivers=" + destinatario,
				null);

		URL obj = uri.toURL();
		
		HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();
		
		con.setRequestMethod("POST");
		con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
		con.setRequestProperty("Content-Length", Integer.toString(parameters.length()));
		con.setDoInput(true);
		con.setDoOutput(true);
		
		DataOutputStream wr = new DataOutputStream(con.getOutputStream());
		wr.writeBytes(parameters);
		wr.flush();
		wr.close();
		
		int responseCode = con.getResponseCode();
		
		System.out.println("\nEnviando 'POST' para a URL : " + obj.toString());
		System.out.println("Parametros : " + parameters);
		System.out.println("Codigo de Resposta : " + responseCode);
 
		BufferedReader in = new BufferedReader(
		        new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();
 
		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		
		in.close();
 
		// imprime resultado
		System.out.println(response.toString());
		
		return responseCode;
	}
	
}
