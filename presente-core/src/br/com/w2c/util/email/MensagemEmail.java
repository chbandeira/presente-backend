package br.com.w2c.util.email;

import br.com.chbandeira.utilitario.UtilDate;
import br.com.w2c.model.domain.Ocorrencia;
import br.com.w2c.model.domain.Usuario;
import br.com.w2c.util.Constantes;

/**
 * Classe de mensagens de email.
 * 
 * @author charlles
 *
 */
public class MensagemEmail {

	/**
	 * Cabecalho padrao do email.
	 * 
	 * @param usuario
	 * @param conteudo
	 * @return
	 */
	private static StringBuilder cabecalho(Usuario usuario, StringBuilder conteudo) {
		conteudo.append("<!DOCTYPE html PUBLIC '-//W3C//DTD HTML 4.01 Transitional//EN'>");
		conteudo.append("<html><head> <title>Presente!</title>");
		conteudo.append("<meta http-equiv='Content-Type' content='text/html; charset=UTF-8'>");
		conteudo.append("</head>");
		conteudo.append("<body> Caro(a) " + usuario.getNome() + ",");
		return conteudo;
	}

	/**
	 * Rodape padrao do email.
	 * 
	 * @param conteudo
	 * @return
	 */
	private static StringBuilder rodape(StringBuilder conteudo) {
		conteudo.append("<br><br>Atenciosamente,");
		conteudo.append("<br><br>Administrador do Sistema.");
		conteudo.append("</body></html>");
		return conteudo;
	}

	/**
	 * Corpo do email de novo usuário.
	 * 
	 * @param usuario
	 * @return
	 */
	public static String msgNovoUsuario(Usuario usuario) {
		StringBuilder conteudo = new StringBuilder();
		conteudo = cabecalho(usuario, conteudo);
		conteudo.append("<br><br>Seu cadastro no <b>Presente!</b> foi realizado com sucesso. ");
		conteudo.append("<br><br>Segue abaixo seus dados para Acessar o Sistema: ");
		conteudo.append("<br><br>Login de acesso: " + usuario.getLogin() + " ");
		conteudo.append("<br>Senha de acesso: " + usuario.getSenha() + " ");
		conteudo.append("<br><br>Obs: Por favor ao Acessar o Sistema, altere sua senha.");
		conteudo = rodape(conteudo);

		return conteudo.toString();
	}

	/**
	 * Corpo do email de recuperacao de senha.
	 * 
	 * @param usuario
	 * @return
	 */
	public static String msgRecuperaSenha(Usuario usuario) {
		StringBuilder conteudo = new StringBuilder();
		conteudo = cabecalho(usuario, conteudo);
		conteudo.append("Sua Solicitação foi realizado com sucesso. ");
		conteudo.append("<br><br>Segue abaixo sua Nova senha para Acessar o Sistema: ");
		conteudo.append("<br><br>Login de acesso: " + usuario.getLogin() + " ");
		conteudo.append("<br>Senha de acesso: " + usuario.getSenha() + " ");
		conteudo.append("<br><br>Obs: Por favor, no próximo Login altere sua senha.");
		conteudo = rodape(conteudo);

		return conteudo.toString();
	}

	/**
	 * Corpo do email de alteracao de senha.
	 * 
	 * @param usuario
	 * @return
	 */
	public static String msgAlterarSenha(Usuario usuario) {
		StringBuilder conteudo = new StringBuilder();
		conteudo = cabecalho(usuario, conteudo);
		conteudo.append("Você alterou sua senha. ");
		conteudo.append("<br><br>Segue abaixo sua nova Senha para Acessar o Sistema: ");
		conteudo.append("<br><br>Login de acesso: " + usuario.getLogin());
		conteudo.append("<br>Nova senha de acesso: " + usuario.getSenha());
		conteudo = rodape(conteudo);

		return conteudo.toString();
	}
	
	public static String msgOcorrenciaEmail(Ocorrencia ocorrencia, String nomeDaEscola) {
		StringBuilder sb = new StringBuilder();
		sb.append("<!DOCTYPE html PUBLIC '-//W3C//DTD HTML 4.01 Transitional//EN'>");
		sb.append("<html><head> <title>Presente!</title>");
		sb.append("<meta http-equiv='Content-Type' content='text/html; charset=UTF-8'>");
		sb.append("</head>");
		sb.append("<table cellspacing=\"4\">");
		sb.append("<tbody>");
		sb.append("<tr>");
		sb.append("	<td><label><b>").append(nomeDaEscola).append("</b></label></td>");
		sb.append("</tr>");
		sb.append("<tr>");
		sb.append("	<td><label><b>Aluno(a):</b> ").append(ocorrencia.getMatricula().getAluno().getNome()).append("</label></td>");
		sb.append("</tr>");
		if (ocorrencia.getMatricula().getSerie() != null) {
			sb.append("<tr>");
			sb.append("	<td><label><b>Série:</b> ").append(ocorrencia.getMatricula().getSerie().getDescricao()).append("</label></td>");
			sb.append("</tr>");
		}
		if (ocorrencia.getMatricula().getTurma() != null) {
			sb.append("<tr>");
			sb.append("	<td><label><b>Turma:</b> ").append(ocorrencia.getMatricula().getTurma().getDescricao()).append("</label></td>");
			sb.append("</tr>");
		}
		if (ocorrencia.getMatricula().getTurno() != null) {
			sb.append("<tr>");
			sb.append("	<td><label><b>Turno:</b> ").append(ocorrencia.getMatricula().getTurno().getDescricao()).append("</label></td>");
			sb.append("</tr>");
		}
		if (ocorrencia.getMatricula().getSala() != null) {
			sb.append("<tr>");
			sb.append("	<td><label><b>Sala:</b> ").append(ocorrencia.getMatricula().getSala().getDescricao()).append("</label></td>");
			sb.append("</tr>");
		}
		sb.append("<tr>");
		sb.append("	<td><label><b>Número da Ocorrência:</b> ").append(ocorrencia.getId()).append("</label></td>");
		sb.append("</tr>");
		sb.append("<tr>");
		String dataFormatada = UtilDate.getDataFormatada(ocorrencia.getData(), Constantes.DD_MM_YYYY);
		sb.append("	<td><label><b>Data da Ocorrência:</b> ").append(dataFormatada).append("</label></td>");
		sb.append("</tr>");
		sb.append("<tr>");
		sb.append("	<td><label><b>Tipo de Ocorrência:</b> ").append(ocorrencia.getTipo().getDescricao()).append("</label></td>");
		sb.append("</tr>");
		if (ocorrencia.getQtdDias() != null && ocorrencia.getQtdDias() > 0) {
			sb.append("<tr>");
			sb.append("	<td><label><b>Quantidade de dias:</b> ").append(ocorrencia.getQtdDias()).append("</label></td>");
			sb.append("</tr>");
		}
		sb.append("<tr>");
		sb.append("	<td><label><b>Responsável pela Ocorrência:</b> ").append(ocorrencia.getUsuario().getNome()).append("</label></td>");
		sb.append("</tr>");
		sb.append("<tr>");
		sb.append("	<td><label><b>Descrição:</b> ").append(ocorrencia.getDescricao()).append("</label></td>");
		sb.append("</tr>");
		sb.append("</tbody>");
		sb.append("</table>");
		sb.append("</html>");
		return sb.toString();
	}
}
