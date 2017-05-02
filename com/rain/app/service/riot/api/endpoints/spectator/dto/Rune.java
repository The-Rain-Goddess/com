package com.rain.app.service.riot.api.endpoints.spectator.dto;

import java.io.Serializable;

import com.rain.app.service.riot.api.Dto;

public class Rune extends Dto implements Serializable {

	private static final long serialVersionUID = -5212321615648903989L;

	private int count;
	private int runeId;

	public int getCount() {
		return count;
	}

	public int getRuneId() {
		return runeId;
	}

	@Override
	public String toString() {
		return getRuneId() + ": " + getCount();
	}
}
