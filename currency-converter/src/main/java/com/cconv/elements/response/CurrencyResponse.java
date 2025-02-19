package com.cconv.elements.response;

import java.util.Date;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class CurrencyResponse extends Response {
	@JsonProperty("eur")
	private Map<String, Double> currencyList;
	
	@JsonProperty("date")
	private Date date;


}
