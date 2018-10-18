package br.com.w2c.view.validator;

import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import br.com.chbandeira.utilitario.Util;
import br.com.w2c.exception.AplicacaoException;
import br.com.w2c.util.ResourceUtil;

/**
 * @author charlles
 * @since 19/10/2013
 */
@FacesValidator(value="dataNascimentoValidator")
public class DataNascimentoValidator implements Validator {

	@Override
	public void validate(FacesContext context, UIComponent component, Object value)	throws ValidatorException {
		
		if (!Util.isNuloOuVazio(value)) {
			Date dataAtual = new Date();
			Date dataPreenchida = null;
			if (value instanceof Date) {
				dataPreenchida = (Date) value;
				if (dataPreenchida.after(dataAtual)) {
					try {
						throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, ResourceUtil.getMessage("MSG038"), null));
					} catch (AplicacaoException e) {
						e.printStackTrace();
					}
				}
			} else {
				try {
					throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, ResourceUtil.getMessage("MSG037"), null));
				} catch (AplicacaoException e) {
					e.printStackTrace();
				}				
			}
		}
	}
}
