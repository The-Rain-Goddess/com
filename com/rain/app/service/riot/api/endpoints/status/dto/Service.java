package com.rain.app.service.riot.api.endpoints.status.dto;

import java.io.Serializable;
import java.util.List;

import com.rain.app.service.riot.api.Dto;

public class Service extends Dto implements Serializable {

	private static final long serialVersionUID = -5472099748922109877L;

	private List<Incident> incidents;
	private String name;
	private String slug;
	private String status;

	public List<Incident> getIncidents() {
		return incidents;
	}

	public String getName() {
		return name;
	}

	public String getSlug() {
		return slug;
	}

	public String getStatus() {
		return status;
	}

	@Override
	public String toString() {
		return getName();
	}
}
