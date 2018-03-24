package com.zuchol.converter.service;

import java.math.BigDecimal;


import org.springframework.stereotype.Service;

public interface CurrencyService {
	
	BigDecimal getCurrencyByCode(String currencyCode);

}
