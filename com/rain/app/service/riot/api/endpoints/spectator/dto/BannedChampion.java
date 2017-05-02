package com.rain.app.service.riot.api.endpoints.spectator.dto;

import java.io.Serializable;

import com.rain.app.service.riot.api.Dto;

public class BannedChampion extends Dto implements Serializable {

	private static final long serialVersionUID = -2518840271357794856L;

	private int championId;
	private int pickTurn;
	private int teamId;

	public int getChampionId() {
		return championId;
	}

	public int getPickTurn() {
		return pickTurn;
	}

	public int getTeamId() {
		return teamId;
	}

	@Override
	public String toString() {
		return String.valueOf(getChampionId());
	}
}
