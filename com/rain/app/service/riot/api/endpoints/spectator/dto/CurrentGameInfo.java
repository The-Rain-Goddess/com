package com.rain.app.service.riot.api.endpoints.spectator.dto;

import java.io.Serializable;
import java.util.List;

import com.rain.app.service.riot.api.Dto;



public class CurrentGameInfo extends Dto implements Serializable {

	private static final long serialVersionUID = -6094147804616587912L;

	private List<BannedChampion> bannedChampions;
	private long gameId;
	private long gameLength;
	private String gameMode;
	private int gameQueueConfigId;
	private long gameStartTime;
	private String gameType;
	private int mapId;
	private Observer observers;
	private List<CurrentGameParticipant> participants;
	private String platformId;
	private TeamInfo teamInfoOne;
	private TeamInfo teamInfoTwo;

	public List<BannedChampion> getBannedChampions() {
		return bannedChampions;
	}

	public long getGameId() {
		return gameId;
	}

	public long getGameLength() {
		return gameLength;
	}

	public String getGameMode() {
		return gameMode;
	}

	public int getGameQueueConfigId() {
		return gameQueueConfigId;
	}

	public long getGameStartTime() {
		return gameStartTime;
	}

	public String getGameType() {
		return gameType;
	}

	public int getMapId() {
		return mapId;
	}

	public Observer getObservers() {
		return observers;
	}

	public List<CurrentGameParticipant> getParticipants() {
		return participants;
	}

	public String getPlatformId() {
		return platformId;
	}

	public TeamInfo getTeamInfoOne() {
		return teamInfoOne;
	}

	public TeamInfo getTeamInfoTwo() {
		return teamInfoTwo;
	}
}
