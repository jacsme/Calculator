package com.cal.response;

import java.util.Stack;

public class CalculatorResponse {
	private Stack<String> result;
	private String status;
	private String message;
	
	public Stack<String> getResult() {
		return result;
	}

	public void setResult(Stack<String> result) {
		this.result = result;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	

}
