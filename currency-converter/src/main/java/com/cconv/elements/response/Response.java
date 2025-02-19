package com.cconv.elements.response;

import lombok.Data;

@Data
public class Response {
	private String status;
	private String message;
	private int statusCode = 200;

}
