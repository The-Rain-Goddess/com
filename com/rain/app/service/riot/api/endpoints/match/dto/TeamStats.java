package com.rain.app.service.riot.api.endpoints.match.dto;

import java.io.Serializable;
import java.util.List;

import com.rain.app.service.riot.api.Dto;

public class TeamStats extends Dto implements Serializable {

	private static final long serialVersionUID = -3414402914522684688L;

	private List<TeamBans> bans;
	private int baronKills;
	private int dominionVictoryScore;
	private int dragonKills;
	private boolean firstBaron;
	private boolean firstBlood;
	private boolean firstDragon;
	private boolean firstInhibitor;
	private boolean firstRiftHerald;
	private boolean firstTower;
	private int inhibitorKills;
	private int riftHeraldKills;
	private int teamId;
	private int towerKills;
	private int vilemawKills;
	private boolean win;

	public List<TeamBans> getBans() {
		return bans;
	}

	public int getBaronKills() {
		return baronKills;
	}

	public int getDominionVictoryScore() {
		return dominionVictoryScore;
	}

	public int getDragonKills() {
		return dragonKills;
	}

	public int getInhibitorKills() {
		return inhibitorKills;
	}

	public int getRiftHeraldKills() {
		return riftHeraldKills;
	}

	public int getTeamId() {
		return teamId;
	}

	public int getTowerKills() {
		return towerKills;
	}

	public int getVilemawKills() {
		return vilemawKills;
	}

	public boolean isFirstBaron() {
		return firstBaron;
	}

	public boolean isFirstBlood() {
		return firstBlood;
	}

	public boolean isFirstDragon() {
		return firstDragon;
	}

	public boolean isFirstInhibitor() {
		return firstInhibitor;
	}

	public boolean isFirstRiftHerald() {
		return firstRiftHerald;
	}

	public boolean isFirstTower() {
		return firstTower;
	}

	public boolean isWin() {
		return win;
	}

	@Override
	public String toString() {
		return String.valueOf(getTeamId());
	}
}
