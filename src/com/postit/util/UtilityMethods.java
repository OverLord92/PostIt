package com.postit.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UtilityMethods {

	public static Date convertStringToDate(String dateString) throws ParseException{
		
		dateString = "11-01-2006";
		DateFormat format = new SimpleDateFormat("dd-mm-yyyy");
		Date date = format.parse(dateString);
		
		return date;
	}
	
}
