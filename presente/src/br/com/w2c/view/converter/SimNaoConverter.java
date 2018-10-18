package br.com.w2c.view.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 * 
 * @author charlles
 * @since 28/09/2013
 */
@FacesConverter(value="simNaoConverter")
public class SimNaoConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		return value;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object object) {
		if (object != null && object instanceof Boolean) {
			if ((Boolean) object) {
				return "Sim";
			} else {
				return "N\u00E3o";
			}
		}
		return "";
	}

}
