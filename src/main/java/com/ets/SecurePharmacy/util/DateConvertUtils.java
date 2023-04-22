package com.ets.SecurePharmacy.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Component;
@Component
public class DateConvertUtils {

	private LocalDate localDate;
	private DateTimeFormatter format=DateTimeFormatter.ofPattern("yyyy-MM-dd");
	public LocalDate convertStringToDate(String date) {
		if(date!=null) {
		 localDate = LocalDate.parse(date, format);
		}
		return localDate;
	}

	public String convertDatetoString(LocalDate date) {
		return date.format(format);
	}
}
