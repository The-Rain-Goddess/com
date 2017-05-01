package com.rain.app.service.riot.api.endpoints.match.dto;

import java.io.Serializable;

import com.rain.app.service.riot.api.Dto;

public class MatchReference extends Dto implements Serializable {

	private static final long serialVersionUID = 3975874858170021162L;

	private int champion;
	private long gameId;
	private String lane;
	private String platformId;
	private int queue;
	private String role;
	private int season;
	private long timestamp;

	public int getChampion() {
		return champion;
	}

	public long getGameId() {
		return gameId;
	}

	public String getLane() {
		return lane;
	}

	public String getPlatformId() {
		return platformId;
	}

	public int getQueue() {
		return queue;
	}

	public String getRole() {
		return role;
	}

	public int getSeason() {
		return season;
	}

	public long getTimestamp() {
		return timestamp;
	}

	@Override
	public String toString() {
		return String.valueOf(getGameId());
	}
}
