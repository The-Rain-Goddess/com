package com.rain.app.service.riot.api.endpoints.constants.dto;

import java.io.Serializable;

import com.rain.app.service.riot.api.Dto;

public class ProfileIconDetails extends Dto implements Serializable {

	private static final long serialVersionUID = 3518094705310465723L;

	private long id;
	private Image image;

	public long getId() {
		return id;
	}

	public Image getImage() {
		return image;
	}

	@Override
	public String toString() {
		return String.valueOf(getId());
	}
}
