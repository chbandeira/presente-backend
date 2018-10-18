package br.com.w2c.view.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.w2c.model.enums.StatusCalendarioEscolar;

/**
 * 
 * @author charlles
 * @since 30/11/2013
 * @see StatusCalendarioEscolar
 */
@FacesConverter(value="simNaoDataAulaConverter")
public class SimNaoDataAulaConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		return value;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object object) {
		if (object != null && object instanceof Long) {
			if ((Long) object == StatusCalendarioEscolar.INCLUIR.getStatus()) {
				return "Sim";
			} else {
				return "Não";
			}
		}
		return "";
	}

}
