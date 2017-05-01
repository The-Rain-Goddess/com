package com.rain.app.service.riot.api.endpoints.masteries.dto;

import java.io.Serializable;
import java.util.Set;

import com.rain.app.service.riot.api.Dto;

public class MasteryPages extends Dto implements Serializable {

	private static final long serialVersionUID = 2574314573359467443L;

	private Set<MasteryPage> pages;
	private long summonerId;

	public Set<MasteryPage> getPages() {
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
