package com.cconv.elements.response;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class ConvertResponse extends Response {
	private Double convertedAmount;
	private int usageLimit;

}
