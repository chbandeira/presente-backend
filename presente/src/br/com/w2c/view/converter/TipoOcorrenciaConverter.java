package br.com.w2c.view.converter;

import static br.com.chbandeira.utilitario.Util.isNuloOuVazio;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.w2c.controller.business.TipoOcorrenciaBO;
import br.com.w2c.exception.AplicacaoException;
import br.com.w2c.model.domain.TipoOcorrencia;
import br.com.w2c.util.SpringUtil;

/**
 * 
 * @author charlles
 * @since 19/05/2015
 */
@FacesConverter(value="tipoOcorrenciaConverter")
public class TipoOcorrenciaConverter implements Converter {

	private TipoOcorrenciaBO tipoOcorrenciaBO = SpringUtil.getBean("tipoOcorrenciaBO");
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		TipoOcorrencia tipoOcorrencia = null;
		if (value != null && !value.trim().equals("")) {
			try {
				tipoOcorrencia = tipoOcorrenciaBO.obterPorDescricao(value);
			} catch (AplicacaoException e) {
				e.printStackTrace();
			}
			if (isNuloOuVazio(tipoOcorrencia) || isNuloOuVazio(tipoOcorrencia.getId())) {
				tipoOcorrencia = new TipoOcorrencia(value); 
			}
		}
		return tipoOcorrencia;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object object) {
		if (object != null && object instanceof TipoOcorrencia) {
			return ((TipoOcorrencia)object).getDescricao();
		}
		return "";
	}

}
