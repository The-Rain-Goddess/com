package com.rain.app.service.riot.api.endpoints.constants.dto;

import java.io.Serializable;
import java.util.Map;

import com.rain.app.service.riot.api.Dto;

public class SummonerSpellList extends Dto implements Serializable {

	private static final long serialVersionUID = 3109246145319877891L;

	private Map<String, SummonerSpell> data;
	private String type;
	private String version;

	public Map<String, SummonerSpell> getData() {
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
