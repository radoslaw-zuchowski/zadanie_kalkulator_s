package com.zuchol.converter.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zuchol.converter.model.Country;
import com.zuchol.converter.repository.CountryRepository;
import com.zuchol.converter.service.CountryService;
import com.zuchol.converter.service.CurrencyService;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Service
@Transactional
public class CountryServiceImpl implements CountryService {

	public static final BigDecimal ONE_HUNDRED = new BigDecimal(100);
	public static final BigDecimal DAYS_IN_MONTH = new BigDecimal(22);

	
	@Autowired
	private CountryRepository countryRepository;
	@Autowired
	private CurrencyService currencySerive;

	
	@Override
	public Country getCountryById(Long countryId) {
		return countryRepository.getOne(countryId);
	}


	@Override
	public Country saveCountry(Country country) {
		return countryRepository.save(country); 
	}


	@Override
	public void deleteCountry(Long id) {
		countryRepository.deleteById(id);
	}
	
	
	@Override
	public List<Country> getAllCountries() {
		return countryRepository.findAll();
	}


	@Override
	public String getPayment(BigDecimal value, Long countryId) {
		
		Country country = getCountryById(countryId);
		BigDecimal monthValue = value.multiply(DAYS_IN_MONTH);
		BigDecimal taxes = getTaxes(monthValue, country);
		BigDecimal result = monthValue.subtract(taxes);
		BigDecimal recalculatedValue = getRecalculatedValue(country.getCurrencyCode(), result);
		return recalculatedValue.toString();
	}
	
	
	private BigDecimal getTaxes(BigDecimal value, Country country) {
		BigDecimal staticTax = country.getConstantVariable();
		BigDecimal tax = value.multiply(country.getTax().divide(ONE_HUNDRED));
		BigDecimal allTaxes = staticTax.add(tax);
		return value.subtract(allTaxes);
	}
	
	
	private BigDecimal getRecalculatedValue(String currencyCode, BigDecimal value) {
		BigDecimal course = currencySerive.getCourseByCode(currencyCode);
		BigDecimal resultValue = value.multiply(course);
		return resultValue.setScale(2, RoundingMode.HALF_UP);
	}
	
	
}
