package com.rain.app.service.riot.api.endpoints.match.dto;

import java.io.Serializable;

import com.rain.app.service.riot.api.Dto;

public class ParticipantIdentity extends Dto implements Serializable {

	private static final long serialVersionUID = 7750317217073991764L;

	private int participantId;
	private Player player;

	public int getParticipantId() {
		return participantId;
	}

	public Player getPlayer() {
		return player;
	}

	@Override
	public String toString() {
		return getPlayer().toString();
	}
}
