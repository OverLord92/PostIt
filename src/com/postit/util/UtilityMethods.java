package com.postit.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.postit.dao.Postit;

public class UtilityMethods {
	
	public static void main (String[] args){
//		System.out.println(getFirstDayOfWeek(new Date(System.currentTimeMillis() - 345600000)));
//		System.out.println(getLastDayOfWeek(new Date(System.currentTimeMillis() - 345600000)));
		
		System.out.println(getFirstDayOfMonth(new Date(System.currentTimeMillis() - 345600000)));
		System.out.println(getLastDayOfMonth(new Date(System.currentTimeMillis() - 345600000)) + "\n");
		
		System.out.println(getFirstDayOfMonth(new Date(System.currentTimeMillis() - 10000000000L)));
		System.out.println(getLastDayOfMonth(new Date(System.currentTimeMillis() - 10000000000L)) + "\n");
		
		System.out.println(getFirstDayOfMonth(new Date(System.currentTimeMillis() + 20000000000L)));
		System.out.println(getLastDayOfMonth(new Date(System.currentTimeMillis() + 20000000000L)));
	}

	public static Date convertStringToDate(String dateString) throws ParseException{
		
		DateFormat format = new SimpleDateFormat("dd-MM-yyyy");
		Date date = format.parse(dateString);
		return date;
	}

	public static List<Postit> filterByDay(List<Postit> allUserPostits, Date date) {
		List<Postit> result = new ArrayList<>();
		Postit currentPostit;
		
		for(int i = 0; i < allUserPostits.size(); i++){
			currentPostit = allUserPostits.get(i);
			
			if(date.equals(currentPostit.getDate())){
				result.add(currentPostit);
			}	
		}
		return result;

	}

	public static List<Postit> filterByDateInterval(List<Postit> allUserPostits, Date firstDayOfWeek,
			Date lastDayOfWeek) {
		List<Postit> result = new ArrayList<>();
		Postit currentPostit;
		Date postitDate;
		
		for(int i = 0; i < allUserPostits.size(); i++){
			currentPostit = allUserPostits.get(i);
			postitDate = currentPostit.getDate();
			System.out.println(postitDate);
			if(postitDate.compareTo(firstDayOfWeek) >=0 && postitDate.compareTo(lastDayOfWeek) <=0) {
				result.add(currentPostit);
			}
		}
		return result;
	}
	
	public static Date getFirstDayOfWeek(Date date) {
		
		Calendar calendar = Calendar.getInstance();
		calendar.setFirstDayOfWeek(Calendar.MONDAY);
		calendar.setTime(date);
		
		while (calendar.get(Calendar.DAY_OF_WEEK) > calendar.getFirstDayOfWeek()) {
		    calendar.add(Calendar.DATE, -1); 
		}
		return calendar.getTime();
	}

	public static Date getLastDayOfWeek(Date date) {
		
		Date firstDayOfWeek = getFirstDayOfWeek(date);
		
		Calendar calendar = Calendar.getInstance();
		calendar.setFirstDayOfWeek(Calendar.MONDAY);
		calendar.setTime(firstDayOfWeek);
		
		calendar.add(Calendar.DATE, + 6);
		
		return calendar.getTime();
	}

	public static Date getFirstDayOfMonth(Date date) {
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		
		while (calendar.get(Calendar.DAY_OF_MONTH) > calendar.getActualMinimum(Calendar.DAY_OF_MONTH)) {
		    calendar.add(Calendar.DATE, -1); 
		}
		return calendar.getTime();
		
	}

	public static Date getLastDayOfMonth(Date date) {

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		
		while (calendar.get(Calendar.DAY_OF_MONTH) < calendar.getActualMaximum(Calendar.DAY_OF_MONTH)) {
		    calendar.add(Calendar.DATE, +1); 
		}
		return calendar.getTime();
	}
	
	

}
