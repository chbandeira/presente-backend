package br.com.w2c.controller.business;

import static br.com.chbandeira.utilitario.Util.isNuloOuVazio;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import br.com.w2c.exception.AplicacaoException;
import br.com.w2c.exception.NegocioException;
import br.com.w2c.util.ResourceUtil;
import br.com.w2c.util.SpringUtil;

/**
 * 
 * @author charlles
 * @since 07/09/2013
 */
@Component
public class BaseBO<T> {

	protected Logger log = LogManager.getLogger();
	
	private List<String> mensagens = new ArrayList<String>();
	
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
	
	protected String getMessageParametros(String chave, Object... parametros) throws AplicacaoException {
		return ResourceUtil.getMessage(chave, parametros);
	}
	
	protected String getMessageSuport(String chave) throws AplicacaoException{
		return ResourceUtil.getMessageSuport(chave);
	}
	
	protected void addMensagem(String chaveMensagem) throws AplicacaoException {
		mensagens.add(getMessage(chaveMensagem));
	}
	
	protected void addMensagemSuport(String chaveMensagem) throws AplicacaoException {
		mensagens.add(getMessageSuport(chaveMensagem));
	}
	
	protected void addMensagemLabel(String chaveMensagem, String chaveLabel) throws AplicacaoException {
		mensagens.add(getLabel(chaveLabel).concat(": ").concat(getMessage(chaveMensagem)));
	}
	
	/**
	 * Com texto: Preenchimento obrigatório.
	 * @param chaveLabel
	 * @throws AplicacaoException 
	 */
	protected void addMensagemCampo(String chaveLabel) throws AplicacaoException {
		mensagens.add(getLabel(chaveLabel).concat(": ").concat(getMessage("MSG006")));
	}
	
	protected void addMensagemParametros(String chaveMensagem, Object... parametros) throws AplicacaoException {
		mensagens.add(getMessageParametros(chaveMensagem, parametros));
	}

	protected void lancarMensagemSuportePadrao() throws NegocioException, AplicacaoException {
		mensagens.add(getMessageSuport("SUPPORT001"));
		List<String> mensagensTemp = new ArrayList<String>(mensagens);
		limparMensagens();
		NegocioException negocioException = new NegocioException(mensagensTemp);
		log.error(mensagensTemp, negocioException);
		throw negocioException;
	}

	protected void validarMensagens() throws NegocioException {
		if (hasMensagens()) {
			List<String> mensagensTemp = new ArrayList<String>(mensagens);
			limparMensagens();
			NegocioException negocioException = new NegocioException(mensagensTemp);
			log.warn(mensagensTemp);
			throw negocioException;
		}
	}

	protected boolean hasMensagens() {
		return mensagens.size() > 0;
	}
	
	protected void limparMensagens() {
		mensagens = new ArrayList<String>();
	}
	
	/**
	 * @return Retorna o bean da implementação
	 */
	protected <E extends Object> E getBean(String idObject) {
		return SpringUtil.getBean(idObject);
	}
	
	protected List<String> getMensagens() {
		return mensagens;
	}

	protected void setMensagens(List<String> mensagens) {
		this.mensagens = mensagens;
	}

	/**
	 * Faz uma validação na entidade antes de utilizar os getters e setters dela
	 * @param entidade
	 * @throws NegocioException
	 * @throws AplicacaoException 
	 */
	protected void validarEntidade(Object entidade) throws NegocioException, AplicacaoException {
		if (isNuloOuVazio(entidade)) {
			log.error("Objeto nulo ou vazio");
			lancarMensagemSuportePadrao();
		}
	}

	public void validarCamposObrigatorios(T entidade) throws NegocioException, AplicacaoException {
		validarEntidade(entidade);
	}
	
	public void validarCamposParaExclusao(T entidade) throws NegocioException, AplicacaoException {
		validarEntidade(entidade);
	}

	public void validarCamposParaPesquisa(T entidade) throws NegocioException, AplicacaoException {
		validarEntidade(entidade);
	}
	
	public void validarExisteCadastrado(T entidade) throws NegocioException, AplicacaoException {
		
	}
	
	protected void trimString(String... string) {
		if (!isNuloOuVazio(string)) {
			for (String s : string) {
				if (!isNuloOuVazio(s)) {
					s = s.trim();
				}
			}
		}
	}
	
	/**
	 * 
	 * @param objeto
	 * @throws NegocioException lancarMensagemSuportPadrao
	 * @throws AplicacaoException 
	 */
	protected void checkIsNullOrEmpty(Object... objeto) throws NegocioException, AplicacaoException {
		if (isNuloOuVazio(objeto)) {
			lancarMensagemSuportePadrao();
		} else {
			for (Object o : objeto) {
				if (isNuloOuVazio(o)) {
					lancarMensagemSuportePadrao();
				}
			}
		}
	}
	
	protected T validarObjetoUnico(List<T> retorno, String objeto) throws AplicacaoException, NegocioException {
		T entidade = null;
		
		if (retorno.size() > 1) {
			addMensagemParametros("MSG074", objeto);
		}
		else if (retorno.size() == 1) {
			entidade = retorno.get(0);
		}
		validarMensagens();
		
		return entidade;
	}
	
	protected void copiarPropriedades(T destino, T origem) throws AplicacaoException {
		try {
			BeanUtils.copyProperties(destino, origem);
		} catch (IllegalAccessException e) {
			throw new AplicacaoException(e);
		} catch (InvocationTargetException e) {
			throw new AplicacaoException(e);
		}
		
	}
}
