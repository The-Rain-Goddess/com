package com.rain.app.service.riot.api.endpoints.masteries.dto;

import java.io.Serializable;
import java.util.List;

import com.rain.app.service.riot.api.Dto;

public class MasteryPage extends Dto implements Serializable {

	private static final long serialVersionUID = -8442771692820615671L;

	private boolean current;
	private long id;
	private List<Mastery> masteries;
	private String name;

	public long getId() {
		return id;
	}

	public List<Mastery> getMasteries() {
		return masteries;
	}

	public String getName() {
		return name;
	}

	public boolean isCurrent() {
		return current;
	}

	@Override
	public String toString() {
		return getId() + ": " + getName();
	}
}
