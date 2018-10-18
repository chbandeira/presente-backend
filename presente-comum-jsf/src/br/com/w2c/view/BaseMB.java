package br.com.w2c.view;

import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import br.com.w2c.exception.AplicacaoException;
import br.com.w2c.model.domain.Usuario;
import br.com.w2c.util.Constantes;
import br.com.w2c.util.ResourceUtil;
import br.com.w2c.util.SpringUtil;
import br.com.w2c.view.util.RedirecionaPagina;

/**
 * Superclasse para classes ManagedBean
 * @author charlles
 * @version 1.0 - 01/04/2012 
 * @since 01/04/2012
 */
public abstract class BaseMB {
	
	protected static final RedirecionaPagina redirect = RedirecionaPagina.getInstance();
	
	protected boolean redirectPageConsulta;
	
	@SuppressWarnings("unused")
	private Usuario usuarioSession;
	
	/**
	 * Mostra na tela mensagem de Erro
	 * @param mensagem
	 */
	protected void mensagemErro(String mensagem) {
		FacesContext.getCurrentInstance().addMessage(null, 
    		new FacesMessage(FacesMessage.SEVERITY_ERROR, mensagem, ""));
	}
	
	/**
	 * Mostra na tela mensagens de Erro
	 * @param mensagem
	 */
	protected void mensagemErro(List<String> mensagens) {
		for (String mensagem : mensagens) {
			FacesContext.getCurrentInstance().addMessage(null, 
					new FacesMessage(FacesMessage.SEVERITY_ERROR, mensagem, ""));
		}
	}
	
	/**
	 * Mostra na tela mensagem de Erro
	 * @param mensagem
	 * @param detalhes
	 */
	protected void mensagemErro(String mensagem, String detalhes) {
		FacesContext.getCurrentInstance().addMessage(null, 
    		new FacesMessage(FacesMessage.SEVERITY_ERROR, mensagem, detalhes));
	}
	
	/**
	 * Mostra na tela mensagem de erro Fatal
	 * @param mensagem
	 */
	protected void mensagemFatal(String mensagem) {
		FacesContext.getCurrentInstance().addMessage(null, 
    		new FacesMessage(FacesMessage.SEVERITY_FATAL, mensagem, ""));
	}
	
	/**
	 * Mostra na tela mensagem de erro Fatal
	 * @param mensagem
	 * @param detalhes
	 */
	protected void mensagemFatal(String mensagem, String detalhes) {
		FacesContext.getCurrentInstance().addMessage(null, 
    		new FacesMessage(FacesMessage.SEVERITY_FATAL, mensagem, detalhes));
	}
	
	/**
	 * Mostra na tela mensagem de Informação
	 * @param mensagem
	 */
	protected void mensagemInfo(String mensagem) {
		FacesContext.getCurrentInstance().addMessage(null, 
    		new FacesMessage(FacesMessage.SEVERITY_INFO, mensagem, ""));
	}
	
	/**
	 * Mostra na tela mensagem de Informação
	 * @param mensagem
	 * @param detalhes
	 */
	protected void mensagemInfo(String mensagem, String detalhes) {
		FacesContext.getCurrentInstance().addMessage(null, 
    		new FacesMessage(FacesMessage.SEVERITY_INFO, mensagem, detalhes));
	}
	
	/**
	 * Mostra na tela mensagem de Aviso
	 * @param mensagem
	 */
	protected void mensagemAviso(String mensagem) {
		FacesContext.getCurrentInstance().addMessage(null, 
    		new FacesMessage(FacesMessage.SEVERITY_WARN, mensagem, ""));
	}
	
	/**
	 * Mostra na tela mensagem de Aviso
	 * @param mensagem
	 * @param detalhes
	 */
	protected void mensagemAviso(String mensagem, String detalhes) {
		FacesContext.getCurrentInstance().addMessage(null, 
    		new FacesMessage(FacesMessage.SEVERITY_WARN, mensagem, detalhes));
	}
	
	protected void mensagemSalvoSucesso() throws AplicacaoException {
		FacesContext.getCurrentInstance().addMessage(null, 
				new FacesMessage(FacesMessage.SEVERITY_INFO, getMessage("MSG004"), ""));
	}
	
	protected void mensagemExcluidoSucesso() throws AplicacaoException {
		FacesContext.getCurrentInstance().addMessage(null, 
				new FacesMessage(FacesMessage.SEVERITY_INFO, getMessage("MSG005"), ""));
	}
	
	/**
	 * Obtém propriedades do label_pt_BR.properties
	 * @param chave Chave a ser obtida o valor
	 * @return ResourceBundle
	 * @throws AplicacaoException 
	 */
	protected String getLabel(String chave) throws AplicacaoException{
		return ResourceUtil.getLabel(chave);
	}
	
	/**
	 * Obtém propriedades do message_pt_BR.properties
	 * @param chave Chave a ser obtida o valor
	 * @return ResourceBundle
	 * @throws AplicacaoException 
	 */
	protected String getMessage(String chave) throws AplicacaoException{
		return ResourceUtil.getMessage(chave);
	}
	
	protected String getMessage(String chave, Object... parametros) throws AplicacaoException{
		return ResourceUtil.getMessage(chave, parametros);
	}
	
	protected void iniciarEntidades() throws AplicacaoException {}
	
	/**
	 * Método para retornar o caminho completo do diretório onde se encontra o arquivo 'pdf'
	 * 
	 * @param diretorio String diretório a ser localizado na aplicação
	 * @return String caminho completo
	 */
	protected String getDiretorioReal(String diretorio) {
		return FacesContext.getCurrentInstance().getExternalContext().getRealPath(diretorio);
	}
	
	protected String getDiretorioRealRelatorios() throws AplicacaoException {
		return getDiretorioReal(ResourceUtil.getPath(Constantes.PATH_JRXML));
	}
	
	protected String getDiretorioFotos() throws AplicacaoException {
		return getDiretorioReal(ResourceUtil.getPath(Constantes.PATH_FOTO));
	}
	
	/**
	 * @return Retorna o bean da implementação
	 */
	protected <E extends Object> E getBean(String idObject) {
		return SpringUtil.getBean(idObject);
	}
	
	protected Usuario getUsuarioSession() {
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		return (Usuario) session.getAttribute(Constantes.SESSION_USUARIO);
	}

	public Map<String, Object> getSession() {
		return FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
	}
	
}