package com.rain.app.service.riot.api.request;

import java.util.List;
import java.util.Map;

public class RequestResponse {

	private final int code;
	private final String body;
	private final Map<String, List<String>> headerFields;

	/**
	 * Constructs a RequestResponse
	 * 
	 * @param code
	 *            HTTP response code
	 * @param body
	 *            Raw body of the HTTP response
	 * @param headerFields
	 *            HTTP header fields
	 */
	RequestResponse(int code, String body, Map<String, List<String>> headerFields) {
		this.code = code;
		this.body = body;
		this.headerFields = headerFields;
	}

	/**
	 * Returns the HTTP response code from the Riot Api.
	 * 
	 * @return HTTP response code
	 */
	public int getCode() {
		return code;
	}

	/**
	 * Returns the raw HTTP body from the Riot Api.
	 *
	 * @return HTTP body
	 */
	public String getBody() {
		return body;
	}

	/**
	 * Returns the HTTP header fields from the Riot Api.
	 * 
	 * @return HTTP header fields
	 */
	public Map<String, List<String>> getHeaderFields() {
		return headerFields;
	}
}
