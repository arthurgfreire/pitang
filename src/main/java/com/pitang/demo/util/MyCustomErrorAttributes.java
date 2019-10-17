package com.pitang.demo.util;

import javax.xml.bind.annotation.XmlRootElement;

@SuppressWarnings("serial")
@XmlRootElement
public class MyCustomErrorAttributes extends RuntimeException {

	private Integer errorCode;
	private String message;
	
	
	
	public MyCustomErrorAttributes(Integer errorCode, String message) {
		super();
		this.errorCode = errorCode;
		this.message = message;
	}
	public Integer getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(Integer errorCode) {
		this.errorCode = errorCode;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}

}
