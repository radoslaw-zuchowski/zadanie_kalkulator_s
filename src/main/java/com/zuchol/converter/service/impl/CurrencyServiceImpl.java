package com.zuchol.converter.service.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.zuchol.converter.service.CurrencyService;


@Service
public class CurrencyServiceImpl implements CurrencyService {

	private static final String URL = "http://api.nbp.pl/api/exchangerates/rates/A/";
	private static final String JSON_SUFIX = "?format=json";

	
	@Override
	public BigDecimal getCourseByCode(String currencyCode) throws IOException, JSONException {
		URL url = createUrl(currencyCode);
		StringBuilder response = getRequest(url);
		Double result = getCourseFromJson(response);
		return new BigDecimal(result);
		
	} 

	private URL createUrl(String currencyCode) throws MalformedURLException {
		String url = URL + currencyCode + JSON_SUFIX;
		return new URL(url);
	}
	
	private StringBuilder getRequest(URL url) throws IOException {
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		InputStream inputStream = con.getInputStream();
		BufferedReader in = new BufferedReader(new InputStreamReader(inputStream));
		String inputLine;
		StringBuilder response = new StringBuilder();
		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();
		return response;
	}
	
	private Double getCourseFromJson(StringBuilder response) throws JSONException {
		JSONObject myResponse = new JSONObject(response.toString());
		JSONArray ratesArray = (JSONArray) myResponse.get("rates");
		JSONObject rates = ratesArray.getJSONObject(0);
		return rates.getDouble("mid");
	}
	
	
}
