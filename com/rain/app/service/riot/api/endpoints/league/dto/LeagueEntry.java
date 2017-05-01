package com.rain.app.service.riot.api.endpoints.league.dto;

import java.io.Serializable;

import com.rain.app.service.riot.api.Dto;

import net.rithms.riot.dto.League.MiniSeries;

public class LeagueEntry extends Dto implements Serializable {

	private static final long serialVersionUID = 3987113536371700279L;

	private String division;
	private boolean isFreshBlood;
	private boolean isHotStreak;
	private boolean isInactive;
	private boolean isVeteran;
	private int leaguePoints;
	private int losses;
	private MiniSeries miniSeries;
	private String playerOrTeamId;
	private String playerOrTeamName;
	private int wins;

	public String getDivision() {
		return division;
	}

	public int getLeaguePoints() {
		return leaguePoints;
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

	public int getWins() {
		return wins;
	}

	public boolean isFreshBlood() {
		return isFreshBlood;
	}

	public boolean isHotStreak() {
		return isHotStreak;
	}

	public boolean isInactive() {
		return isInactive;
	}

	public boolean isVeteran() {
		return isVeteran;
	}

	@Override
	public String toString() {
		return getPlayerOrTeamName();
	}
}
