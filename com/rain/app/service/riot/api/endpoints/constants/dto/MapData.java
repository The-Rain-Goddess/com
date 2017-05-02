package com.rain.app.service.riot.api.endpoints.constants.dto;

import java.io.Serializable;
import java.util.Map;

import com.rain.app.service.riot.api.Dto;

public class MapData extends Dto implements Serializable {
	private static final long serialVersionUID = 1718454202345877041L;

	private Map<String, MapDetails> data;
	private String type;
	private String version;

	public Map<String, MapDetails> getData() {
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
