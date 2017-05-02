package com.rain.app.service.riot.api.endpoints.constants.dto;

import java.io.Serializable;

import com.google.gson.annotations.SerializedName;
import com.rain.app.service.riot.api.Dto;

public class MetaData extends Dto implements Serializable {
	private static final long serialVersionUID = 8983953859724300000L;

	@SerializedName(value = "isRune")
	private boolean rune;
	private String tier;
	private String type;

	public String getTier() {
		return tier;
	}

	public String getType() {
		return type;
	}

	public boolean isRune() {
		return rune;
	}

	@Override
	public String toString() {
		return getTier();
	}
}
