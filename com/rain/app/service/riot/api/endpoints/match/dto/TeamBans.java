package com.rain.app.service.riot.api.endpoints.match.dto;

import java.io.Serializable;

import com.rain.app.service.riot.api.Dto;

public class TeamBans extends Dto implements Serializable {

	private static final long serialVersionUID = 2323180505557888125L;

	private int championId;
	private int pickTurn;

	public int getChampionId() {
		return championId;
	}

	public int getPickTurn() {
		return pickTurn;
	}

	@Override
	public String toString() {
		return String.valueOf(getChampionId());
	}
}
