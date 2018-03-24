package com.zuchol.converter.service.impl;

import java.math.BigDecimal;
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
		
		BigDecimal monthValue = value.multiply(new BigDecimal(22));
		BigDecimal taxValue = monthValue.multiply(country.getTax());
		
		return monthValue.toString();
	}
	
	
	
	
	
	
}
