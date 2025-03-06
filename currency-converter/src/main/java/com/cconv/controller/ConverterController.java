package com.cconv.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.cconv.elements.Currency;
import com.cconv.elements.request.ConvertRequest;
import com.cconv.elements.response.ConvertResponse;
import com.cconv.elements.response.CurrencyListResponse;
import com.cconv.elements.response.CurrencyResponse;
import com.cconv.rest.Service;

@Controller
public class ConverterController {
	Service service = new Service();

	/**
	 * Index endpoint to show the index page
	 *
	 * @param model Spring's view model
	 * @return view name
	 */
	@GetMapping({ "/", "/index" })
	public String index(Model model) {
		loadAttributes(model);
		return "index";
	}
	
	@PostMapping({ "/convert" })
	public String convert(@ModelAttribute ConvertRequest convertRequest, Model model) {
		ConvertResponse resp = new ConvertResponse();				
		CurrencyResponse list =	service.getAll().getBody();
		
		Double normalizedFrom = list.getCurrencyList().get(convertRequest.getFromCurrency() );
		Double normalizedTo = list.getCurrencyList().get(convertRequest.getToCurrency() );
		resp.setConvertedAmount(convert(normalizedFrom, convertRequest.getAmount(), normalizedTo));
		
		loadAttributes(model);
		
		model.addAttribute("convertRequest", convertRequest);
		model.addAttribute("convertResponse",resp);		
		model.addAttribute("date",list.getDate());		
				
		return "index";
	}
	
	/**
	 * Set the static elements of the page
	 *
	 * @param model Spring's view model
	 */
	private void loadAttributes(Model model) {
		model.addAttribute("title", "Currency Converter");
		model.addAttribute("applicationTitle", "Currency Converter");
		model.addAttribute("convertRequest", new ConvertRequest());
		CurrencyListResponse resp = service.getCurrencyList().getBody();
		
		List<Currency> currencyList = resp.getCurrencyList();				
		
		model.addAttribute("base", new Currency("eur", "Euro"));
		model.addAttribute("fromCurrencies", currencyList);
		model.addAttribute("toCurrencies", currencyList);
	}
	
	public double convert(Double normalizedFrom, Double amount, Double to) {
		if(amount == 0)
			return 0;
		Double tmp = amount / normalizedFrom;
		return tmp * to ;
	}

}
