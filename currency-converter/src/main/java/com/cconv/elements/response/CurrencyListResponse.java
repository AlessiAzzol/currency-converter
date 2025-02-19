package com.cconv.elements.response;


import java.util.List;

import com.cconv.elements.Currency;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class CurrencyListResponse extends Response {
	private List<Currency> currencyList;

}
