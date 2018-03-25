package com.zuchol.converter.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.zuchol.converter.model.Country;


@RunWith(SpringRunner.class)
@SpringBootTest
public class CountryServiceTest {
	private Country country;
	
	@Autowired
    private CountryService service;
	
	
	@After
	public void clearTable() {
		if (this.country != null) {
			service.deleteCountry(this.country);
		}
	}
	
	@Test
    public void countryShouldBeSaved() throws Exception {
		Country countryToSave = new Country();
		countryToSave.setName("Japonia");
		countryToSave.setCurrencyCode("JEN");
		countryToSave.setConstantVariable(new BigDecimal(300));
		countryToSave.setTax(new BigDecimal(15));
		Country savedCountry = service.saveCountry(countryToSave);
		this.country = savedCountry;
		assertThat(savedCountry.getId() != null);
    }
	
	@Test
    public void shouldGetListOfCountries() throws Exception {
		List<Country> countryList = service.getAllCountries();
		assertThat(countryList != null);
    }
	
	
	@Test
    public void shouldNotGetPayment() throws Exception {
		String result = service.getPayment(null, null);
		assertThat(result == null);
		
		result = service.getPayment(null, 1L);
		assertThat(result == null);
		
		result = service.getPayment(new BigDecimal(200), null);
		assertThat(result == null);
		
		result = service.getPayment(new BigDecimal(200), 1L);
		assertThat(result != null);
    }

	

}
