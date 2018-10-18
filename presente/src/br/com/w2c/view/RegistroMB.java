package br.com.w2c.view;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;

import br.com.w2c.controller.business.ImagemBO;
import br.com.w2c.controller.business.RegistroBO;
import br.com.w2c.exception.NegocioException;
import br.com.w2c.model.domain.Matricula;
import br.com.w2c.model.domain.Registro;
import br.com.w2c.model.enums.TipoRegistro;
import br.com.w2c.model.wrapper.RegistroWrapper;
import br.com.w2c.util.Constantes;
import br.com.w2c.view.exception.AplicacaoFacesException;

/**
 * @author charlles
 * @since 17/09/2013
 */
@ManagedBean
@SessionScoped
public class RegistroMB extends BaseMB {
	
	private Registro registro;
	
	private RegistroBO registroBO = getBean("registroBO");
	
	private ImagemBO imagemBO = getBean("imagemBO");
	
	private String filename;
	private byte[] foto;
	
	public RegistroMB() {
		iniciarEntidades();
		foto = null;
		filename = null;
	}

	private void verificarExisteRegistroNaMesmaDataPorTipo(Registro registro) throws Exception {
		if (registroBO.existeRegistroNaMesmaDataPorTipo(registro)) {
			if (registro.getTipoRegistro().equals(TipoRegistro.ENTRADA)) {
				mensagemAviso(getMessage("MSG008", registro.getMatricula().getMatricula()));
			} else {
				mensagemAviso(getMessage("MSG009", registro.getMatricula().getMatricula()));
			}
			Registro regitroAtual = registroBO.getRegistroDAO().obter(Registro.class, registro.getId());
			registro.setDataUltimaAtualizacao(regitroAtual.getDataUltimaAtualizacao());
			registroBO.atualizarNaoEnviarRepetido(registro);
		}
	}

	protected void iniciarEntidades() {
		registro = new Registro();
		registro.setMatricula(new Matricula());
	}
	
	public void pageEntrada(ActionEvent event) {
		foto = null;
		filename = null;
		iniciarEntidades();
		redirect.redirecionaPaginaJSF(Constantes.PAGE_REGISTRO_ENTRADA);
	}
	
	public void pageSaida(ActionEvent event) {
		foto = null;
		filename = null;
		iniciarEntidades();
		redirect.redirecionaPaginaJSF(Constantes.PAGE_REGISTRO_SAIDA);
	}
	
	private void registrar() throws Exception {
		filename = null;
		foto = null;
		
		RegistroWrapper wrapper = registroBO.salvar(registro);
		mensagemInfo(getMessage("MSG052", wrapper.getNomeAluno(), wrapper.getMatricula(), wrapper.getSerie(), wrapper.getTurma(), wrapper.getDataHoraRegistro()));
		
		filename = imagemBO.getRandomImageName();
		setFoto(wrapper.getFoto());
		imagemBO.carregarFoto(filename, getDiretorioFotos(), getFoto());
		
		verificarExisteRegistroNaMesmaDataPorTipo(registro);
		iniciarEntidades();
	}

	public void registrarEntrada(ActionEvent event) throws AplicacaoFacesException {
		try {
			registro.setTipoRegistro(TipoRegistro.ENTRADA);
			registrar();
		} catch (NegocioException e) {
			mensagemErro(e.getMessages());
		} catch (Exception e) {
			throw new AplicacaoFacesException(e);
		}
	}

	public void registrarSaida(ActionEvent event) throws AplicacaoFacesException {
		try {
			registro.setTipoRegistro(TipoRegistro.SAIDA);
			registrar();
		} catch (NegocioException e) {
			mensagemErro(e.getMessages());
		} catch (Exception e) {
			throw new AplicacaoFacesException(e);
		}
	}
	
	public String getFilename() {
        return filename;
    }
	
	/* ===================================
	 * 			GETTERS & SETTERS
	 * =================================== */

	public Registro getRegistro() {
		return registro;
	}

	public void setRegistro(Registro registro) {
		this.registro = registro;
	}

	public byte[] getFoto() {
		return foto;
	}

	public void setFoto(byte[] foto) {
		this.foto = foto;
	}
}
