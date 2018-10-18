package br.com.w2c.view.validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import br.com.chbandeira.utilitario.Util;
import br.com.chbandeira.utilitario.UtilEmail;
import br.com.w2c.exception.AplicacaoException;
import br.com.w2c.util.ResourceUtil;

/**
 * Validator para Email
 * @author charlles
 * @version 1.1 - 18/10/2013
 * @since 07/04/2012
 */
@FacesValidator(value="emailValidator")
public class EmailValidator implements Validator {

	@Override
	public void validate(FacesContext context, UIComponent component, Object value)	throws ValidatorException {
		
		if (!Util.isNuloOuVazio(value.toString()) && !UtilEmail.isEmailValido(value)) {
			try {
				throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, ResourceUtil.getMessage("MSG036"), null));
			} catch (AplicacaoException e) {
				e.printStackTrace();
			}
		}
	}
}
