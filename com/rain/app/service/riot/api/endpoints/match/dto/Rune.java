package com.rain.app.service.riot.api.endpoints.match.dto;

import java.io.Serializable;

import com.rain.app.service.riot.api.Dto;

public class Rune extends Dto implements Serializable {

	private static final long serialVersionUID = -6196166736612993781L;

	private int rank;
	private int runeId;

	public int getRank() {
		return rank;
	}

	public int getRuneId() {
		return runeId;
	}

	@Override
	public String toString() {
		return getRuneId() + ": " + getRank();
	}
}
