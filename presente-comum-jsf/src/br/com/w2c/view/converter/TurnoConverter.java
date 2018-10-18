package br.com.w2c.view.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.w2c.model.enums.Turno;

/**
 * 
 * @author charlles
 * @since 21/09/2013
 */
@FacesConverter(value="turnoConverter")
public class TurnoConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		if (value != null && !value.trim().equals("")) {
			return Turno.valueOf(value);
		}
		return null;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object object) {
		if (object != null && object instanceof Turno) {
			return ((Turno)object).name();
		}
		return "";
	}

}
