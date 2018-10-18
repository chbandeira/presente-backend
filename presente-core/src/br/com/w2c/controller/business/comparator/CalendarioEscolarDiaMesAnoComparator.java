package br.com.w2c.controller.business.comparator;

import java.util.Calendar;
import java.util.Comparator;

import br.com.w2c.model.domain.CalendarioEscolar;

/**
 * Compara o dia, mês e ano entre CalendarioEscolar
 * @author charlles
 * @since 01/12/2013
 */
public class CalendarioEscolarDiaMesAnoComparator implements Comparator<CalendarioEscolar> {

	@Override
	public int compare(CalendarioEscolar calendario1, CalendarioEscolar calendario2) {
		
		Calendar calendar1 = Calendar.getInstance();
		calendar1.setTime(calendario1.getData());
		Integer dia1 = calendar1.get(Calendar.DAY_OF_YEAR);
		Integer mes1 = calendar1.get(Calendar.MONTH);
		Integer ano1 = calendar1.get(Calendar.YEAR);
		
		Calendar calendar2 = Calendar.getInstance();
		calendar2.setTime(calendario2.getData());
		Integer dia2 = calendar2.get(Calendar.DAY_OF_YEAR);
		Integer mes2 = calendar2.get(Calendar.MONTH);
		Integer ano2 = calendar2.get(Calendar.YEAR);
		
		if (dia1.compareTo(dia2)==0
				&& mes1.compareTo(mes2)==0
				&& ano1.compareTo(ano2)==0) {
			
			return 0;
			
		} else if ((ano1.compareTo(ano2)>0)
				|| (ano1.compareTo(ano2)==0 && mes1.compareTo(mes2)>0)
				|| (ano1.compareTo(ano2)==0 && mes1.compareTo(mes2)==0 && dia1.compareTo(dia2)>0)) {
			
			return 1;
			
		} else {
			return -1;
		}
	}

}
