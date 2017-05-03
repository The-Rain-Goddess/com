package com.rain.app.service.riot.api.endpoints.match.dto;

import java.io.Serializable;
import java.util.List;

import com.rain.app.service.riot.api.Dto;

public class Participant extends Dto implements Serializable {

	private static final long serialVersionUID = -507075680096851928L;

	private int championId;
	private String highestAchievedSeasonTier;
	private List<Mastery> masteries;
	private int participantId;
	private List<Rune> runes;
	private int spell1Id;
	private int spell2Id;
	private ParticipantStats stats;
	private int teamId;
	private ParticipantTimeline timeline;

	public int getChampionId() {
		return championId;
	}

	public String getHighestAchievedSeasonTier() {
		return highestAchievedSeasonTier;
	}

	public List<Mastery> getMasteries() {
		return masteries;
	}

	public int getParticipantId() {
		return participantId;
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

	public ParticipantStats getStats() {
		return stats;
	}

	public int getTeamId() {
		return teamId;
	}

	public ParticipantTimeline getTimeline() {
		return timeline;
	}

	@Override
	public String toString() {
		return String.valueOf(getParticipantId());
	}
}
