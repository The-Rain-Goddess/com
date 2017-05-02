package com.rain.app.service.riot.api.endpoints.spectator.dto;

import java.io.Serializable;
import java.util.List;

import com.rain.app.service.riot.api.Dto;

public class FeaturedGameInfo extends Dto implements Serializable {

	private static final long serialVersionUID = -3351906462835367100L;

	private List<BannedChampion> bannedChampions;
	private long gameId;
	private long gameLength;
	private String gameMode;
	private int gameQueueConfigId;
	private long gameStartTime;
	private String gameType;
	private int mapId;
	private Observer observers;
	private List<Participant> participants;
	private String platformId;

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

	public List<Participant> getParticipants() {
		return participants;
	}

	public String getPlatformId() {
		return platformId;
	}

	@Override
	public String toString() {
		return getGameId() + " (" + getGameMode() + ")";
	}
}
