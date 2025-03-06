package com.cconv.rest;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.cconv.elements.Constants;
import com.cconv.elements.Currency;
import com.cconv.elements.response.CurrencyListResponse;
import com.cconv.elements.response.CurrencyResponse;

@RestController
public class Service {

	@GetMapping("/getAll")
	public ResponseEntity<CurrencyResponse> getAll() {
		String url = Constants.URL_CURRENCY_API + "currencies/eur.json";
		CurrencyResponse response = new CurrencyResponse();
		RestTemplate rs = new RestTemplate();

		try {
			response = rs.getForObject(url, CurrencyResponse.class);
			response.setStatus(HttpStatus.OK.name());
			response.setMessage(Constants.STATUS_OK);

		} catch (Exception e) {
			e.printStackTrace();
			response.setStatus(Constants.STATUS_KO);
			response.setMessage(e.getMessage());
			new ResponseEntity<>(response, HttpStatus.OK);
		}

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@SuppressWarnings("unchecked")
	@GetMapping("/getCurrencyList")
	public ResponseEntity<CurrencyListResponse> getCurrencyList() {
		String url = Constants.URL_CURRENCY_API + "currencies.json";
		CurrencyListResponse response = new CurrencyListResponse();
		RestTemplate rs = new RestTemplate();

		try {
			Map<String, String> list = rs.getForObject(url, Map.class);
			List<Currency> currencies = new ArrayList<>();
			list.forEach((key, value) -> currencies.add(new Currency(key, value)));

			response.setStatus(Constants.STATUS_OK);
			response.setCurrencyList(currencies);

		} catch (Exception e) {
			e.printStackTrace();
			response.setStatus(Constants.STATUS_KO);
			response.setMessage(e.getMessage());
			new ResponseEntity<>(response, HttpStatus.OK);
		}

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

}
