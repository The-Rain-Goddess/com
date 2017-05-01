package com.rain.app.service.riot.api.endpoints.status.dto;

import java.io.Serializable;

import com.google.gson.annotations.SerializedName;
import com.rain.app.service.riot.api.Dto;

public class Translation extends Dto implements Serializable {

	private static final long serialVersionUID = -8923544184113630844L;

	private String content;
	private String locale;
	@SerializedName(value = "updated_at")
	private String updatedAt;

	public String getContent() {
		return content;
	}

	public String getLocale() {
		return locale;
	}

	public String getUpdatedAt() {
		return updatedAt;
	}

	@Override
	public String toString() {
		return getContent();
	}
}
