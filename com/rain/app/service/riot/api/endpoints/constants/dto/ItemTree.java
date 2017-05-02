package com.rain.app.service.riot.api.endpoints.constants.dto;

import java.io.Serializable;
import java.util.List;

import com.rain.app.service.riot.api.Dto;

public class ItemTree extends Dto implements Serializable {

	private static final long serialVersionUID = 7245929613325190245L;

	private String header;
	private List<String> tags;

	public String getHeader() {
		return header;
	}

	public List<String> getTags() {
		return tags;
	}

	@Override
	public String toString() {
		return getHeader();
	}
}
