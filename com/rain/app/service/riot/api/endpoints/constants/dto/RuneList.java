package com.rain.app.service.riot.api.endpoints.constants.dto;

import java.io.Serializable;
import java.util.Map;

import com.rain.app.service.riot.api.Dto;

public class RuneList extends Dto implements Serializable {

	private static final long serialVersionUID = 1696878942733740445L;

	private BasicData basic;
	private Map<String, Rune> data;
	private String type;
	private String version;

	public BasicData getBasic() {
		return basic;
	}

	public Map<String, Rune> getData() {
		return data;
	}

	public String getType() {
		return type;
	}

	public String getVersion() {
		return version;
	}

	@Override
	public String toString() {
		return getType();
	}
}
