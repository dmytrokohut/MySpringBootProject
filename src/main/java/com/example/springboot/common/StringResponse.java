package com.example.springboot.common;

/**
 * This class created especially for response String in JSON-format
 * @author User
 * @version 1.0
 */
public class StringResponse {
	
	private String response;
	
	/**
	 * Constructor that sets a value of response attribute
	 * @param response
	 */
	public StringResponse(String response) {
		this.response = response;
	}
	
	public String getResponse() {
		return response;
	}
	
}
