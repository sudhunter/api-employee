package com.mx.aleon.aemployee.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.mx.aleon.aemployee.ex.ApiException;

public class DateMapperUtil {

	private DateMapperUtil() {
	}

	public static Date toDate(String dateString) throws ApiException {
		if(dateString == null) {
			throw new ApiException(-1, "Invalid date format: null");
		}
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		try {
			return formatter.parse(dateString);
		} catch (ParseException e) {
			throw new ApiException(-1, "Invalid date format: " + dateString);
		}
	}

}
