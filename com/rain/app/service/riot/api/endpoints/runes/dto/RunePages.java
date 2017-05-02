package com.rain.app.service.riot.api.endpoints.runes.dto;

import java.io.Serializable;
import java.util.Set;

import com.rain.app.service.riot.api.Dto;

public class RunePages extends Dto implements Serializable {

	private static final long serialVersionUID = 3636621264321429110L;

	private Set<RunePage> pages;
	private long summonerId;

	public Set<RunePage> getPages() {
		return pages;
	}

	public long getSummonerId() {
		return summonerId;
	}

	@Override
	public String toString() {
		return getPages().toString();
	}
}
