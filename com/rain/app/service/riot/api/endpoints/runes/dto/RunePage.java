package com.rain.app.service.riot.api.endpoints.runes.dto;

import java.io.Serializable;
import java.util.Set;

import com.rain.app.service.riot.api.Dto;

import net.rithms.riot.dto.Summoner.RuneSlot;

public class RunePage extends Dto implements Serializable {

	private static final long serialVersionUID = 97699628656087959L;

	private boolean current;
	private long id;
	private String name;
	private Set<RuneSlot> slots;

	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public Set<RuneSlot> getSlots() {
		return slots;
	}

	public boolean isCurrent() {
		return current;
	}

	@Override
	public String toString() {
		return getId() + ": " + getName();
	}
}
