package com.seoi.escapeplus.global.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateTimeUtil {

	public static LocalDate replaceStringWithLocalDate(String time) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		return LocalDate.parse(time, formatter);
	}
}
