package com.zuchol.converter.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.zuchol.converter.model.Country;
import com.zuchol.converter.service.CountryService;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class IndexController {
	
	public static final String CONVERTER_PAGE = "converter";
	
	@Autowired
	@Setter
	private CountryService countryService;
	
	@GetMapping("/")
    public String indexGet(Model model) {
		List<Country> countryList = countryService.getAllCountries();
		model.addAttribute("countries", countryList);
		model.addAttribute("requestCountryId", countryList.get(0));
        return CONVERTER_PAGE;
    }
	
	
	@PostMapping("/")
    public String indexPost(Model model
    		, @RequestParam("price") BigDecimal price
    		, @RequestParam("country") Long countryId) {
		
		model.addAttribute("countries", countryService.getAllCountries());
		model.addAttribute("requestCountryId", countryId);
       
		if (price == null) {
			model.addAttribute("error", "Price can't by empty");
			return CONVERTER_PAGE;
		}
		
		String resultValue;
		try {
			resultValue = countryService.getPayment(price, countryId);
		} catch (IOException e) {
			log.error("IOException error: ", e);
			model.addAttribute("error", "Ther are problems with connection to NBP");
			return CONVERTER_PAGE;
		} catch (JSONException e) {
			log.error("JSONException error: ", e);
			model.addAttribute("error", "Ther are problems with converting response from NBP");
			return CONVERTER_PAGE;
		}
		
		model.addAttribute("requestValue", price);
		model.addAttribute("resultValue", resultValue);
		return CONVERTER_PAGE;
    }
	
	
}
