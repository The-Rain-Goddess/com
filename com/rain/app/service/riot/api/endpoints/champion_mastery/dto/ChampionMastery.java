package com.rain.app.service.riot.api.endpoints.champion_mastery.dto;

import java.io.Serializable;

import com.rain.app.service.riot.api.Dto;

public class ChampionMastery extends Dto implements Serializable {

	private static final long serialVersionUID = 1980259216579071478L;

	private int championId;
	private int championLevel;
	private int championPoints;
	private long championPointsSinceLastLevel;
	private long championPointsUntilNextLevel;
	private boolean chestGranted;
	private long lastPlayTime;
	private long playerId;
	private int tokensEarned;

	public int getChampionId() {
		return championId;
	}

	public int getChampionLevel() {
		return championLevel;
	}

	public int getChampionPoints() {
		return championPoints;
	}

	public long getChampionPointsSinceLastLevel() {
		return championPointsSinceLastLevel;
	}

	public long getChampionPointsUntilNextLevel() {
		return championPointsUntilNextLevel;
	}

	public long getLastPlayTime() {
		return lastPlayTime;
	}

	public long getPlayerId() {
		return playerId;
	}

	public int getTokensEarned() {
		return tokensEarned;
	}

	public boolean isChestGranted() {
		return chestGranted;
	}

	@Override
	public String toString() {
		return getChampionId() + ": " + getChampionPoints() + " (Level " + getChampionLevel() + ")";
	}
}
