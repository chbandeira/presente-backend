package br.com.w2c.view.converter;

import static br.com.chbandeira.utilitario.Util.isNuloOuVazio;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.w2c.controller.business.MatriculaBO;
import br.com.w2c.model.domain.Matricula;
import br.com.w2c.util.SpringUtil;

/**
 * 
 * @author charlles
 * @since 19/05/2015
 */
@FacesConverter(value="matriculaConverter")
public class MatriculaConverter implements Converter {

	private MatriculaBO matriculaBO = SpringUtil.getBean("matriculaBO");
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Matricula matricula = null;
		if (value != null && !value.trim().equals("")) {
			try {
				matricula = matriculaBO.obterPorId(Long.parseLong(value));
				if (isNuloOuVazio(matricula) || isNuloOuVazio(matricula.getId())) {
					matricula = new Matricula(value); 
				}
			} catch (NumberFormatException e) {
			}
		}
		return matricula;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object object) {
		if (object != null && object instanceof Matricula && ((Matricula)object).getId() != null) {
			return ((Matricula)object).getId().toString();
		}
		return "";
	}

}
