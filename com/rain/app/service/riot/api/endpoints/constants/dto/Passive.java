package com.rain.app.service.riot.api.endpoints.constants.dto;

import java.io.Serializable;

import com.rain.app.service.riot.api.Dto;

public class Passive extends Dto implements Serializable {

	private static final long serialVersionUID = 3894157958044991050L;

	private String description;
	private Image image;
	private String name;
	private String sanitizedDescription;

	public String getDescription() {
		return description;
	}

	public Image getImage() {
		return image;
	}

	public String getName() {
		return name;
	}

	public String getSanitizedDescription() {
		return sanitizedDescription;
	}

	@Override
	public String toString() {
		return getName();
	}
}
