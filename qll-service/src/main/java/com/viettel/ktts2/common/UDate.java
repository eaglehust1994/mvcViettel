package com.viettel.ktts2.common;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.apache.log4j.Logger;

public class UDate {
	
	static Logger LOGGER=Logger.getLogger(UDate.class);

    public XMLGregorianCalendar parseToXMLGregorianCalendar(java.util.Date obj) throws Exception {
        GregorianCalendar gCalendar = new GregorianCalendar();
        gCalendar.setTime(obj);
        return DatatypeFactory.newInstance().newXMLGregorianCalendar(gCalendar);
    }
    
    public static Date  toNextDate(Date date){
    	Calendar cal=Calendar.getInstance();
    	cal.setTime(date);
    	cal.add(Calendar.DATE, 1);
    	return cal.getTime();
    }
    public static Date toEndOfPreviousDate(Date date){
    	Calendar cal=Calendar.getInstance();
    	cal.setTime(date);    
    	cal.set(Calendar.HOUR, 0);
    	cal.set(Calendar.MINUTE, 0);
    	cal.set(Calendar.SECOND, 0);
    	cal.set(Calendar.MILLISECOND, 0);    	    	    
    	cal.add(Calendar.MILLISECOND, -1);
    	return cal.getTime();
    }
    public static Date toEndOfDate(Date date){
    	Calendar cal=Calendar.getInstance();
    	cal.setTime(date);
    	cal.set(Calendar.HOUR, 0);
    	cal.set(Calendar.MINUTE, 0);
    	cal.set(Calendar.SECOND, 0);
    	cal.set(Calendar.MILLISECOND, 0);    	
    	
    	cal.add(Calendar.DATE, 1);
    	cal.add(Calendar.MILLISECOND, -1);
    	return cal.getTime();
    }

//     private   SimpleDateFormat simple_format = new SimpleDateFormat("dd/MM/yyyy");
    // private  SimpleDateFormat log_date_format = new SimpleDateFormat("yyyy/MM/dd hh24:mm:ss:SSS");
    //static DateTimeFormatter simple_format= DateTimeFormatter.ofPattern("dd/MM/yyyy");
    //static DateTimeFormatter log_date_format= DateTimeFormatter.ofPattern("dd/MM/yyyy");
    public static String toSimpleFormat(Date date) {
        if (date == null) {
            return "";
        }
        SimpleDateFormat simple_format = new SimpleDateFormat("dd/MM/yyyy");

        return simple_format.format(date);
    }
    
    public static String format(Date date, String patern){
         if (date == null) {
            return "";
        }
        SimpleDateFormat simple_format = new SimpleDateFormat(patern);

        return simple_format.format(date);
    }

    public static String toLogDateFormat(Date date) {
        if (date == null) {
            return null;
        }
        SimpleDateFormat log_date_format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss:SSS");
        return log_date_format.format(date);
    }
    public static Date fromLogToDate(String dateString) throws Exception{
        if (dateString == null) {
            return null;
        }
        SimpleDateFormat log_date_format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss:SSS");
      
		return log_date_format.parse(dateString);
		
    }

}
