package com.rain.app.service.riot.api.endpoints.constants.dto;

import java.io.Serializable;

import com.google.gson.annotations.SerializedName;
import com.rain.app.service.riot.api.Dto;

public class Group extends Dto implements Serializable {

	private static final long serialVersionUID = -4691215871693156751L;

	@SerializedName(value = "MaxGroupOwnable")
	private String maxGroupOwnable;
	private String key;

	public String getMaxGroupOwnable() {
		return maxGroupOwnable;
	}

	public String getKey() {
		return key;
	}

	@Override
	public String toString() {
		return getKey();
	}
}
