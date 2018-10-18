package br.com.w2c.view.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.w2c.controller.business.PerfilBO;
import br.com.w2c.exception.AplicacaoException;
import br.com.w2c.model.domain.Perfil;
import br.com.w2c.util.SpringUtil;

/**
 * 
 * @author charlles
 * @since 13/10/2013
 */
@FacesConverter(value="perfilConverter")
public class PerfilConverter implements Converter {

	private PerfilBO perfilBO = SpringUtil.getBean("perfilBO");
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Perfil perfil = null;
		if (value != null && !value.trim().equals("")) {
			try {
				perfil = perfilBO.obterPorNome(value);
			} catch (AplicacaoException e) {
				e.printStackTrace();
			}
		}
		return perfil;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object object) {
		if (object != null && object instanceof Perfil) {
			return ((Perfil)object).getNome();
		}
		return "";
	}

}
