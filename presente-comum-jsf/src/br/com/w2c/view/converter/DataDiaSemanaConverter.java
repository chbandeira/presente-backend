package br.com.w2c.view.converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import br.com.w2c.util.Constantes;

/**
 * 
 * @author charlles
 * @since 01/12/2013
 */
@FacesConverter(value="dataDiaSemanaConverter")
public class DataDiaSemanaConverter implements Converter {

	private Logger log = LogManager.getLogger();
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		if (value != null && !value.equals("")) {
			SimpleDateFormat formatter = new SimpleDateFormat(Constantes.DD_MM_YYYY_EEEE);
			try {
				return formatter.parse(value);
			} catch (ParseException e) {
				log.error(this.getClass().getName(), e);
			}
		}
		return null;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object object) {
		if (object != null && object instanceof Date) {
			SimpleDateFormat formatter = new SimpleDateFormat(Constantes.DD_MM_YYYY_EEEE);
			return formatter.format(object);
		}
		return null;
	}

}
