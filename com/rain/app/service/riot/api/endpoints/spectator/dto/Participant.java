package com.rain.app.service.riot.api.endpoints.spectator.dto;

import java.io.Serializable;

import com.rain.app.service.riot.api.Dto;

public class Participant extends Dto implements Serializable {

	private static final long serialVersionUID = 7105307616042600638L;

	private boolean bot;
	private int championId;
	private int profileIconId;
	private int spell1Id;
	private int spell2Id;
	private String summonerName;
	private int teamId;

	public int getChampionId() {
		return championId;
	}

	public int getProfileIconId() {
		return profileIconId;
	}

	public int getSpell1Id() {
		return spell1Id;
	}

	public int getSpell2Id() {
		return spell2Id;
	}

	public String getSummonerName() {
		return summonerName;
	}

	public int getTeamId() {
		return teamId;
	}

	public boolean isBot() {
		return bot;
	}

	@Override
	public String toString() {
		return getSummonerName();
	}
}
