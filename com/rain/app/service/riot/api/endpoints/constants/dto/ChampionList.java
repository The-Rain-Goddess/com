package com.rain.app.service.riot.api.endpoints.constants.dto;

import java.io.Serializable;
import java.util.Map;

import com.rain.app.service.riot.api.Dto;

public class ChampionList extends Dto implements Serializable {

	private static final long serialVersionUID = -1332170960767191762L;

	private Map<String, Champion> data;
	private String format;
	private Map<String, String> keys;
	private String type;
	private String version;

	public Map<String, Champion> getData() {
		return data;
	}

	public String getFormat() {
		return format;
	}

	public Map<String, String> getKeys() {
		return keys;
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
