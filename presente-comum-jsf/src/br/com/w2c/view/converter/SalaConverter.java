package br.com.w2c.view.converter;

import static br.com.chbandeira.utilitario.Util.isNuloOuVazio;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.w2c.controller.business.SalaBO;
import br.com.w2c.exception.AplicacaoException;
import br.com.w2c.model.domain.Sala;
import br.com.w2c.util.SpringUtil;

/**
 * 
 * @author charlles
 * @since 20/09/2013
 */
@FacesConverter(value="salaConverter")
public class SalaConverter implements Converter {

	private SalaBO salaBO = SpringUtil.getBean("salaBO");
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Sala sala = null;
		if (value != null && !value.trim().equals("")) {
			try {
				sala = salaBO.obterPorDescricao(value);
			} catch (AplicacaoException e) {
				e.printStackTrace();
			}
			if (isNuloOuVazio(sala) || isNuloOuVazio(sala.getId())) {
				sala = new Sala(value); 
			}
		}
		return sala;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object object) {
		if (object != null && object instanceof Sala) {
			return ((Sala)object).getDescricao();
		}
		return "";
	}

}
