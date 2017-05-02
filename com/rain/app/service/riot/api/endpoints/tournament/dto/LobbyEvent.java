package com.rain.app.service.riot.api.endpoints.tournament.dto;

import java.io.Serializable;

import com.rain.app.service.riot.api.Dto;

public class LobbyEvent extends Dto implements Serializable {

	private static final long serialVersionUID = 2914669820313091597L;

	private String eventType;
	private String summonerId;
	private String timestamp;

	public String getEventType() {
		return eventType;
	}

	public String getSummonerId() {
		return summonerId;
	}

	public String getTimestamp() {
		return timestamp;
	}

	@Override
	public String toString() {
		return getEventType();
	}
}
