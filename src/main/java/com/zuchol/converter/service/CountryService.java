package com.zuchol.converter.service;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import org.json.JSONException;

import com.zuchol.converter.model.Country;

/**
 * Service interface for Country class
 * 
 */
public interface CountryService {
	
	/**
	 * Get Country by id
	 * 
	 * @param countryId country Id
	 * @return Country
	 */
	Country getCountryById(Long countryId);
	
	/**
	 * Save country
	 * 
	 * @param country country to save
	 * @return saved country
	 */
	Country saveCountry(Country country);
	
	/**
	 * Remove country
	 * 
	 * @param id country id
	 */
	void deleteCountry(Long id);
	
	
	/**
	 * Get all countries
	 * 
	 * @return list of countries
	 */
	List<Country> getAllCountries();
	
	/**
	 * Get monthly net payment
	 * 
	 * @param value of daily rate
	 * @param countryId id of country
	 * @return monthly net payment
	 */
	String getPayment(BigDecimal value, Long countryId) throws IOException, JSONException ;
	
	
}
