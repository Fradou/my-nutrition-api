package com.fradou.nutrition.mvc.utils.work;

/**
 * Utils class used to generate api error message.
 * 
 * @author AF
 *
 */
public class ApiErrorMessage {

	private int code;
	
	private String message;

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public ApiErrorMessage(int code, String message) {
		this.code = code;
		this.message = message;
	}
}
