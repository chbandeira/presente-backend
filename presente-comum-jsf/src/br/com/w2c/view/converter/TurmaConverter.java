package br.com.w2c.view.converter;

import static br.com.chbandeira.utilitario.Util.isNuloOuVazio;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.w2c.controller.business.TurmaBO;
import br.com.w2c.exception.AplicacaoException;
import br.com.w2c.model.domain.Turma;
import br.com.w2c.util.SpringUtil;

/**
 * 
 * @author charlles
 * @since 20/09/2013
 */
@FacesConverter(value="turmaConverter")
public class TurmaConverter implements Converter {

	private TurmaBO turmaBO = SpringUtil.getBean("turmaBO");
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Turma turma = null;
		if (value != null && !value.trim().equals("")) {
			try {
				turma = turmaBO.obterPorDescricao(value);
			} catch (AplicacaoException e) {
				e.printStackTrace();
			}
			if (isNuloOuVazio(turma) || isNuloOuVazio(turma.getId())) {
				turma = new Turma(value);
			}
		} else {
			turma = new Turma(value);
		}
		return turma;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object object) {
		if (object != null && object instanceof Turma) {
			return ((Turma)object).getDescricao();
		}
		return "";
	}

}
