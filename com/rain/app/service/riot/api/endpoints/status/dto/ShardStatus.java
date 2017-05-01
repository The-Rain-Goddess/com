package com.rain.app.service.riot.api.endpoints.status.dto;

import java.io.Serializable;
import java.util.List;

import com.google.gson.annotations.SerializedName;
import com.rain.app.service.riot.api.Dto;

public class ShardStatus extends Dto implements Serializable {

	private static final long serialVersionUID = -530404100006610537L;

	private String hostname;
	private List<String> locales;
	private String name;
	@SerializedName(value = "region_tag")
	private String regionTag;
	private List<Service> services;
	private String slug;

	public String getHostname() {
		return hostname;
	}

	public List<String> getLocales() {
		return locales;
	}

	public String getName() {
		return name;
	}

	public String getRegionTag() {
		return regionTag;
	}

	public List<Service> getServices() {
		return services;
	}

	public String getSlug() {
		return slug;
	}

	@Override
	public String toString() {
		return getName();
	}
}
