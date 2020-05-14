package com.dms.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.beanutils.Converter;

public class MyConventer implements Converter{

	@Override
	public Object convert(Class clazz, Object value) {
		if(value instanceof Date)
			return value;
		if(value==null)
			return null;
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		try {

			Date date = sdf.parse((String)value);
			System.out.println(date);
			return date;
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
}
