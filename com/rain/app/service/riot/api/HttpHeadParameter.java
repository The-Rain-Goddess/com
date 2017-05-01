package com.rain.app.service.riot.api;

public class HttpHeadParameter implements Cloneable {

	private final String key;
	private final String value;

	public HttpHeadParameter(String key, String value) {
		this.key = key;
		this.value = value;
	}

	@Override
	public HttpHeadParameter clone() {
		return new HttpHeadParameter(key, value);
	}

	public String getKey() {
		return key;
	}

	public String getValue() {
		return value;
	}
}
