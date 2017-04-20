package com.cts.cldapps.domain;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Message {
	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	

}
