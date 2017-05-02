package com.rain.app.service.riot.api.endpoints.constants.dto;

import java.io.Serializable;
import java.util.Map;

import com.rain.app.service.riot.api.Dto;

public class ProfileIconData extends Dto implements Serializable {

	private static final long serialVersionUID = -8083989551564055132L;

	private Map<String, ProfileIconDetails> data;
	private String type;
	private String version;

	public Map<String, ProfileIconDetails> getData() {
		return data;
	}

	public String getType() {
		return type;
	}

	public String getVersion() {
		return version;
	}
}
