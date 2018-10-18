package br.com.w2c.controller.business;

import static br.com.chbandeira.utilitario.Util.isNuloOuVazio;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.chbandeira.utilitario.UtilDate;
import br.com.w2c.exception.AplicacaoException;
import br.com.w2c.exception.NegocioException;
import br.com.w2c.model.dao.RegistroDAO;
import br.com.w2c.model.domain.Matricula;
import br.com.w2c.model.domain.Registro;
import br.com.w2c.model.domain.Usuario;

/**
 * 
 * @author charlles
 * @since 14/09/2013
 */
@Component
@SuppressWarnings("rawtypes")
public class PresencaBO extends BaseBO {

	@Autowired
	private RegistroDAO registroDAO;
	
	public List<Registro> obterRegistrosPorPeriodo(Registro registro, Date periodoInicial, Date periodoFinal) throws NegocioException, AplicacaoException {
		validarEntidade(registro);
		validarEntidade(registro.getMatricula());
		validarEntidade(registro.getMatricula().getAluno());
		
		List<Registro> registros;
		if (!isNuloOuVazio(periodoInicial) && !isNuloOuVazio(periodoFinal)) {
			registros = registroDAO.obterPorMatriculaEPeriodo(registro.getMatricula(), periodoInicial, periodoFinal);
			
		} else if (!isNuloOuVazio(periodoInicial)) {
			registros = registroDAO.obterPorPeriodoInicial(registro, periodoInicial);
			
		} else if (!isNuloOuVazio(periodoFinal)) {
			registros = registroDAO.obterPorPeriodoFinal(registro, periodoFinal);
			
		} else {
			registros = registroDAO.obterSemPeriodo(registro);
		}
		if (registros == null || registros.isEmpty()) {
			addMensagem("MSG001");
			validarMensagens();
		}
		
		return registros;
	}

	public List<Registro> obterRegistrosPorUsuario(Matricula matricula,
			Date periodoInicial, Date periodoFinal, Usuario usuario) throws NegocioException, AplicacaoException {
		
		validarCamposObrigatorios(matricula);
		
		if (!isNuloOuVazio(periodoFinal)) {
			periodoFinal = UtilDate.obterDataUltimaHora(periodoFinal);
		}
		
		List<Registro> registros = registroDAO.obterRegistrosPorUsuario(matricula, periodoInicial, periodoFinal, usuario);
		
		if (registros == null || registros.isEmpty()) {
			addMensagem("MSG001");
			validarMensagens();
		}
		
		return registros;
	}

	public void validarCamposObrigatorios(Matricula matricula) throws NegocioException, AplicacaoException {
		if (isNuloOuVazio(matricula)) {
			addMensagem("MSG070");
			validarMensagens();
		}
	}

}
