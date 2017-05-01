package com.rain.app.service.riot.api.endpoints.match.dto;

import java.io.Serializable;

import com.rain.app.service.riot.api.Dto;

public class MatchParticipantFrame extends Dto implements Serializable {

	private static final long serialVersionUID = -7134736662198942818L;

	private int currentGold;
	private int dominionScore;
	private int jungleMinionsKilled;
	private int level;
	private int minionsKilled;
	private int participantId;
	private MatchPosition position;
	private int teamScore;
	private int totalGold;
	private int xp;

	public int getCurrentGold() {
		return currentGold;
	}

	public int getDominionScore() {
		return dominionScore;
	}

	public int getJungleMinionsKilled() {
		return jungleMinionsKilled;
	}

	public int getLevel() {
		return level;
	}

	public int getMinionsKilled() {
		return minionsKilled;
	}

	public int getParticipantId() {
		return participantId;
	}

	public MatchPosition getPosition() {
		return position;
	}

	public int getTeamScore() {
		return teamScore;
	}

	public int getTotalGold() {
		return totalGold;
	}

	public int getXp() {
		return xp;
	}
}
