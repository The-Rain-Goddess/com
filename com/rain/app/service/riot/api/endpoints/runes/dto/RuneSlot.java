package com.rain.app.service.riot.api.endpoints.runes.dto;

import java.io.Serializable;

import com.rain.app.service.riot.api.Dto;

public class RuneSlot extends Dto implements Serializable {

	private static final long serialVersionUID = -8204125507572318573L;

	private int runeId;
	private int runeSlotId;

	public int getRuneId() {
		return runeId;
	}

	public int getRuneSlotId() {
		return runeSlotId;
	}

	@Override
	public String toString() {
		return getRuneSlotId() + ": " + getRuneId();
	}
}
