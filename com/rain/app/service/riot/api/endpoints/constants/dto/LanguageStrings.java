package com.rain.app.service.riot.api.endpoints.constants.dto;

import java.io.Serializable;
import java.util.Map;

import com.rain.app.service.riot.api.Dto;

public class LanguageStrings extends Dto implements Serializable {

	private static final long serialVersionUID = -5097401216029512928L;

	private Map<String, String> data;
	private String type;
	private String version;

	public Map<String, String> getData() {
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
