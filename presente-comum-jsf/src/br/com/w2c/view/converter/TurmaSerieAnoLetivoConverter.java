package br.com.w2c.view.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.w2c.model.domain.Serie;
import br.com.w2c.model.domain.Turma;
import br.com.w2c.model.enums.Turno;

/**
 * 
 * @author charlles
 * @since 20/05/2015
 */
@FacesConverter(value="turmaSerieAnoLetivoConverter")
public class TurmaSerieAnoLetivoConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Turma turma = null;
		if (value != null && !value.trim().equals("")) {
			String[] strings = value.split(";");
			
			turma = new Turma();
			turma.setId(new Long(strings[0]));
			if (!strings[1].equals("-")) {
				turma.setSerie(new Serie(strings[1]));
			}
			if (!strings[2].equals("-")) {
				turma.setAnoLetivo(new Integer(strings[2]));
			}
			if (!strings[3].equals("-")) {
				turma.setDescricao(strings[3]);
			}
			if (!strings[4].equals("-")) {
				turma.setTurno(Turno.valueOf(strings[4]));
			}
		}
		return turma;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object object) {
		if (object != null && object instanceof Turma) {
			Turma turma = (Turma)object;
			if (turma.getId() != null) {
				return turma.getId().toString()
						.concat(";").concat(turma.getSerie() != null ? turma.getSerie().getDescricao() : "-")
						.concat(";").concat(turma.getAnoLetivo() != null ? turma.getAnoLetivo().toString() : "-")
						.concat(";").concat(turma.getDescricao() != null ? turma.getDescricao() : "-")
						.concat(";").concat(turma.getTurno() != null ? turma.getTurno().name() : "-");
			}
		}
		return "";
	}

}
