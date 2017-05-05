package com.rain.app.service.riot.api.endpoints.league.dto;

import java.io.Serializable;
import java.util.List;

import com.rain.app.service.riot.api.Dto;

public class League extends Dto implements Serializable {

	private static final long serialVersionUID = 1878237445691308532L;

	private List<LeagueEntry> entries;
	private String name;
	private String participantId;
	private String queue;
	private String tier;

	public List<LeagueEntry> getEntries() {
		return entries;
	}

	public String getName() {
		return name;
	}

	public String getParticipantId() {
		return participantId;
	}

	public String getQueue() {
		return queue;
	}

	public String getTier() {
		return tier;
	}

	@Override
	public String toString() {
		return getName();
	}
}
