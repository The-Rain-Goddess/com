package com.rain.app.service.riot.api.endpoints.league.dto;

import java.io.Serializable;

import com.rain.app.service.riot.api.Dto;

public class LeaguePosition extends Dto implements Serializable {

	private static final long serialVersionUID = 1753178439019663600L;

	private boolean freshBlood;
	private boolean hotStreak;
	private boolean inactive;
	private String leagueName;
	private int leaguePoints;
	private int losses;
	private MiniSeries miniSeries;
	private String playerOrTeamId;
	private String playerOrTeamName;
	private String queueType;
	private String rank;
	private String tier;
	private boolean veteran;
	private int wins;

	public int getLeaguePoints() {
		return leaguePoints;
	}

	public String getLeagueName() {
		return leagueName;
	}

	public int getLosses() {
		return losses;
	}

	public MiniSeries getMiniSeries() {
		return miniSeries;
	}

	public String getPlayerOrTeamId() {
		return playerOrTeamId;
	}

	public String getPlayerOrTeamName() {
		return playerOrTeamName;
	}

	public String getQueueType() {
		return queueType;
	}

	public String getRank() {
		return rank;
	}

	public String getTier() {
		return tier;
	}

	public int getWins() {
		return wins;
	}

	public boolean isFreshBlood() {
		return freshBlood;
	}

	public boolean isHotStreak() {
		return hotStreak;
	}

	public boolean isInactive() {
		return inactive;
	}

	public boolean isVeteran() {
		return veteran;
	}

	@Override
	public String toString() {
		return getQueueType();
	}
}
