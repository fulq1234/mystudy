package org.hibernate.demo.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Tool {
	/**
	 * 
	 * @param strDate
	 * @param pattern
	 * @return
	 * @throws ParseException
	 */
	public static Date strToDate(String strDate,String pattern) throws ParseException{
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		return sdf.parse(strDate);
	}
}
