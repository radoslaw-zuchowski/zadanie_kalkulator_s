package com.zuchol.converter.service;

import java.io.IOException;
import java.math.BigDecimal;

import org.json.JSONException;

public interface CurrencyService {
	
	/**
	 * Get current course by currency code
	 * 
	 * @param currencyCode code of currency
	 * @return course
	 */
	BigDecimal getCourseByCode(String currencyCode) throws IOException, JSONException ;

}
