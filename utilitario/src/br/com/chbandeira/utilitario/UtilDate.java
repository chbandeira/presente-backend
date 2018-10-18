package br.com.chbandeira.utilitario;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * 
 * @author Charlles
 * @since 12/07/2014
 */
public class UtilDate {
	
	public static final String YYYY = "yyyy";
	public static final String MM_YYYY = "MM/yyyy";
	public static final String MM_YYYY_DB = "yyyy-MM";
	public static final String DD_MM_YYYY = "dd/MM/yyyy";
	public static final String DD_MM_YYYY_DB = "yyyy-MM-dd";
	public static final String DD_MM_YYYY_HH_MM = "dd/MM/yyyy HH:mm";
	public static final String DD_MM_YYYY_HH_MM_SS = "dd/MM/yyyy HH:mm:ss";
	public static final String DD_MM_YYYY_HH_MM_SS_SSS = "dd/MM/yyyy HH:mm:ss.SSS";
	public static final String DD_MM_YYYY_EEEE = "dd/MM/yyyy, EEEE";
	
	public static Date getDataAtual() {
		return new Date();
	}
	
	public static Timestamp getDataAtualTimestamp() {
		return new Timestamp(getDataAtual().getTime());
	}
	
	/**
	 * Pattern DD_MM_YYYY
	 * @return
	 */
	public static String getDataAtualFormatada() {
		SimpleDateFormat sdf = new SimpleDateFormat(DD_MM_YYYY);
		return sdf.format(new Date());
	}
	
	public static String getDataAtualFormatada(String pattern) {
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		return sdf.format(new Date());
	}

	/**
	 * Pattern DD_MM_YYYY
	 * @param data
	 * @return
	 */
	public static String getDataFormatada(Date data) {
		SimpleDateFormat sdf = new SimpleDateFormat(DD_MM_YYYY);
		return sdf.format(data);
	}
	
	public static String getDataFormatada(Date data, String pattern) {
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		return sdf.format(data);
	}

	public static Integer getAnoAtual() {
		Date dataAtual = getDataAtual();
		SimpleDateFormat sdf = new SimpleDateFormat(YYYY);
		return Integer.valueOf(sdf.format(dataAtual));
	}
	
	public static Integer getAno(Date data) {
		SimpleDateFormat sdf = new SimpleDateFormat(YYYY);
		return Integer.valueOf(sdf.format(data));
	}
	
	public static Calendar getInicioAnoApenasData() throws ParseException {
		Calendar inicioAno = getApenasData();
		inicioAno.set(Calendar.DAY_OF_MONTH, 1);
		inicioAno.set(Calendar.MONTH, 0);
		inicioAno.set(Calendar.YEAR, getAnoAtual());
		return inicioAno;
	}

	public static Calendar getFimAnoApenasData() throws ParseException {
		Calendar fimAno = getApenasData();
		fimAno.set(Calendar.DAY_OF_MONTH, 31);
		fimAno.set(Calendar.MONTH, 11);
		fimAno.set(Calendar.YEAR, getAnoAtual());
		return fimAno;
	}
	
	public static Calendar getApenasData() throws ParseException {
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat(DD_MM_YYYY);
		Date date = sdf.parse(getDataFormatada(getDataAtual(), DD_MM_YYYY));
		calendar.setTime(date);
		return calendar;
	}
	
	public static boolean isPeriodoMesmoAno(Date periodoInicial, Date periodoFinal) {
		Integer ano = getAno(periodoInicial);
		Integer anoComparar = getAno(periodoFinal);
		
		return ano.compareTo(anoComparar) != 0;
	}

	/**
	 * 
	 * @param data dd/MM/yyyy
	 * @return
	 * @throws ParseException
	 */
	public static Date getStringToDate(String data) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat(DD_MM_YYYY);
		return sdf.parse(data);
	}
	
	public static Date getStringToDate(String data, String pattern) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		return sdf.parse(data);
	}
	
	public static Calendar getDataCalendarAtual() {
		return Calendar.getInstance(new Locale("pt", "BR"));
	}
	
	/**
	 * adaptado de http://javafree.uol.com.br/topic-877386-calculo-de-idade.html
	 * <br>
	 * Calendar c = Calendar.getInstance();  
     * c.set(1986, 5, 25); // mês começa de 0 e não de 1  
     * c.getTime(); = new Date();  
	 * @param dataNasc
	 * @return
	 */
	public static int getIdade(Date dataNasc){  
        Date hoje = new Date();  
        Calendar cal = Calendar.getInstance();  
          
        cal.setTime(hoje);  
        int diaHoje = cal.get(Calendar.DAY_OF_YEAR);  
        int anoHoje = cal.get(Calendar.YEAR);  
          
        cal.setTime(dataNasc);  
        int diaNasc = cal.get(Calendar.DAY_OF_YEAR);  
        int anoNasc = cal.get(Calendar.YEAR);  
          
        int nAno = anoHoje - anoNasc - 1;  
        
        if(diaHoje >= diaNasc) {
            nAno++; // completou aniversario esse ano.
        }
              
        return nAno;  
    }
	
	/**
	 * adaptado de http://javafree.uol.com.br/topic-877386-calculo-de-idade.html
	 * <br>
	 * Calendar c = Calendar.getInstance();  
     * c.set(1986, 5, 25); // mês começa de 0 e não de 1  
     * c.getTime(); = new Date();  
	 * @param calNasc
	 * @return
	 */
	public static int getIdade(Calendar calNasc){  
        Date hoje = new Date();  
        Calendar calHoje = Calendar.getInstance();  
          
        calHoje.setTime(hoje);  
        int diaHoje = calHoje.get(Calendar.DAY_OF_YEAR);  
        int anoHoje = calHoje.get(Calendar.YEAR);  
          
        int diaNasc = calNasc.get(Calendar.DAY_OF_YEAR);  
        int anoNasc = calNasc.get(Calendar.YEAR);  
          
        int nAno = anoHoje - anoNasc - 1;  
        
        if(diaHoje >= diaNasc) {
            nAno++; // completou aniversario esse ano.
        }
              
        return nAno;  
    }
	
	/**
	 * adaptado de http://javafree.uol.com.br/topic-877386-calculo-de-idade.html
	 * <br>
	 * Calendar c = Calendar.getInstance();  
     * c.set(1986, 5, 25); // mês começa de 0 e não de 1  
     * c.getTime(); = new Date();  
	 * @param nasc formato dd/MM/yyyy
	 * @return
	 */
	public static int getIdade(String nasc){  
		Date hoje = new Date();  
        Calendar cal = Calendar.getInstance();  
          
        cal.setTime(hoje);  
        int diaHoje = cal.get(Calendar.DAY_OF_YEAR);  
        int anoHoje = cal.get(Calendar.YEAR);  
          
        int ano = Integer.parseInt(nasc.substring(6, 10));
		int mes = Integer.parseInt(nasc.substring(3, 5)) -1;
		int dia = Integer.parseInt(nasc.substring(0, 2));
		
		cal.set(ano, mes, dia); 
        int diaNasc = cal.get(Calendar.DAY_OF_YEAR);  
        int anoNasc = cal.get(Calendar.YEAR);  
          
        int nAno = anoHoje - anoNasc - 1;  
        
        if(diaHoje >= diaNasc) {
            nAno++; // completou aniversario esse ano.
        }
              
        return nAno;  
    }

	/**
	 * @param data Data a ser alterada o horario
	 * @return Retorna a mesma data preenchido o horario em 23:59:59
	 */
	public static Date obterDataUltimaHora(Date data) {
		Calendar retorno = Calendar.getInstance();
		retorno.setTime(data);
		retorno.set(Calendar.HOUR_OF_DAY, 23);
		retorno.set(Calendar.MINUTE, 59);
		retorno.set(Calendar.SECOND, 59);
		return retorno.getTime();
	}
	
}