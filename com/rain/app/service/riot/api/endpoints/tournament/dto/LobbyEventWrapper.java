package com.rain.app.service.riot.api.endpoints.tournament.dto;

import java.io.Serializable;
import java.util.List;

import com.rain.app.service.riot.api.Dto;

public class LobbyEventWrapper extends Dto implements Serializable {

	private static final long serialVersionUID = -7830193432118565589L;

	private List<LobbyEvent> eventList;

	public List<LobbyEvent> getEventList() {
		return eventList;
	}

	@Override
	public String toString() {
		return getEventList().toString();
	}
}
