package br.com.w2c.view.converter;

import static br.com.chbandeira.utilitario.Util.isNuloOuVazio;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.w2c.controller.business.SerieBO;
import br.com.w2c.exception.AplicacaoException;
import br.com.w2c.model.domain.Serie;
import br.com.w2c.util.SpringUtil;

/**
 * 
 * @author charlles
 * @since 20/09/2013
 */
@FacesConverter(value="serieConverter")
public class SerieConverter implements Converter {

	private SerieBO serieBO = SpringUtil.getBean("serieBO");
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Serie serie = null;
		if (value != null && !value.trim().equals("")) {
			try {
				serie = serieBO.obterPorDescricao(value);
			} catch (AplicacaoException e) {
				e.printStackTrace();
			}
			if (isNuloOuVazio(serie) || isNuloOuVazio(serie.getId())) {
				serie = new Serie(value);
			}
		} else {
			serie = new Serie(value);
		}
		return serie;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object object) {
		if (object != null && object instanceof Serie) {
			return ((Serie)object).getDescricao();
		}
		return "";
	}

}
