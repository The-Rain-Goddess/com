package com.rain.app.service.riot.api.endpoints.spectator.dto;

import java.io.Serializable;
import java.util.List;

import com.rain.app.service.riot.api.Dto;

public class CurrentGameParticipant extends Dto implements Serializable {

	private static final long serialVersionUID = -2549733502630146814L;

	private boolean bot;
	private int championId;
	private List<Mastery> masteries;
	private int profileIconId;
	private List<Rune> runes;
	private int spell1Id;
	private int spell2Id;
	private long summonerId;
	private String summonerName;
	private int teamId;

	public int getChampionId() {
		return championId;
	}

	public List<Mastery> getMasteries() {
		return masteries;
	}

	public int getProfileIconId() {
		return profileIconId;
	}

	public List<Rune> getRunes() {
		return runes;
	}

	public int getSpell1Id() {
		return spell1Id;
	}

	public int getSpell2Id() {
		return spell2Id;
	}

	public long getSummonerId() {
		return summonerId;
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
