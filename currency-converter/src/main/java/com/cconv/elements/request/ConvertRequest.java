package com.cconv.elements.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConvertRequest {
	private Double amount;
	private Double fromCurrency;
	private Double toCurrency;

}
