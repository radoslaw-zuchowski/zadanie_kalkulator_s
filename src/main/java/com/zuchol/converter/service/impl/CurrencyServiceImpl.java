package com.zuchol.converter.service.impl;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import com.zuchol.converter.service.CurrencyService;

@Service
public class CurrencyServiceImpl implements CurrencyService {

	@Override
	public BigDecimal getCourseByCode(String currencyCode) {
		
		return new BigDecimal(1);
	}

	
}
