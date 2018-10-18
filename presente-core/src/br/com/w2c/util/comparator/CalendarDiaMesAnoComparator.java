package br.com.w2c.util.comparator;

import java.util.Calendar;
import java.util.Comparator;

/**
 * Compara o dia, mês e ano entre Calendar
 * @author charlles
 * @since 01/12/2013
 */
public class CalendarDiaMesAnoComparator implements Comparator<Calendar> {

	@Override
	public int compare(Calendar calendar1, Calendar calendar2) {
		
		Integer dia1 = calendar1.get(Calendar.DAY_OF_YEAR);
		Integer mes1 = calendar1.get(Calendar.MONTH);
		Integer ano1 = calendar1.get(Calendar.YEAR);
		
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
