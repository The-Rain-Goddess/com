package com.rain.app.service.riot.api.endpoints.constants.dto;

import java.io.Serializable;
import java.util.Map;

import com.rain.app.service.riot.api.Dto;

public class MasteryList extends Dto implements Serializable {

	private static final long serialVersionUID = -2255568964283725298L;

	private Map<String, Mastery> data;
	private MasteryTree tree;
	private String type;
	private String version;

	public Map<String, Mastery> getData() {
		return data;
	}

	public MasteryTree getTree() {
		return tree;
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
