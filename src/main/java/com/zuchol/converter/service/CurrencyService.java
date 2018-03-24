package com.zuchol.converter.service;

import java.math.BigDecimal;


import org.springframework.stereotype.Service;

public interface CurrencyService {
	
	/**
	 * Get current course by currency code
	 * 
	 * @param currencyCode code of currency
	 * @return course
	 */
	BigDecimal getCourseByCode(String currencyCode);

}
