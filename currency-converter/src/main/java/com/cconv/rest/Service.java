package com.cconv.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.cconv.elements.Constants;
import com.cconv.elements.response.Response;

@RestController
public class Service {
	
	@GetMapping("/getAll")
	public ResponseEntity<Response> getAll() {
		String url = Constants.URL_CURRENCY_API;
		Response response = new Response();
		RestTemplate rs = new RestTemplate();		
		
		try {
			response = rs.getForObject(url, Response.class);
			System.out.println(response);
			response.setStatus(Constants.STATUS_OK);
			
		} catch (Exception e) {
			e.printStackTrace();
			response.setStatus(Constants.STATUS_KO);
			response.setMessage(e.getMessage());
			new ResponseEntity<>(response, HttpStatus.OK);
		} 
		
		
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

}
