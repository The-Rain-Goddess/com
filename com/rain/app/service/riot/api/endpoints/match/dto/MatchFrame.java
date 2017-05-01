package com.rain.app.service.riot.api.endpoints.match.dto;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.rain.app.service.riot.api.Dto;

public class MatchFrame extends Dto implements Serializable {

	private static final long serialVersionUID = 4359409042534560829L;

	private List<MatchEvent> events;
	private Map<Integer, MatchParticipantFrame> participantFrames;
	private long timestamp;

	public List<MatchEvent> getEvents() {
		return events;
	}

	public Map<Integer, MatchParticipantFrame> getParticipantFrames() {
		return participantFrames;
	}

	public long getTimestamp() {
		return timestamp;
	}

	@Override
	public String toString() {
		return String.valueOf(getTimestamp());
	}
}
