package com.capgemini.exceptions;

public class ErrorInformation {
	private String url; 
	private String message;

	public ErrorInformation() {}
	
	public ErrorInformation(String url, String message) {
		this.url = url;
		this.message = message;
	}
	
	public String getUrl() {
		return url;
	}
	
	public void setUrl(String url) {
		this.url = url;
	}
	
	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
}