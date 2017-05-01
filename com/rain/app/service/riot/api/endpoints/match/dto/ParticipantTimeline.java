package com.rain.app.service.riot.api.endpoints.match.dto;

import java.io.Serializable;
import java.util.Map;

import com.rain.app.service.riot.api.Dto;

public class ParticipantTimeline extends Dto implements Serializable {

	private static final long serialVersionUID = -6355300896423737331L;

	private Map<String, Double> creepsPerMinDeltas;
	private Map<String, Double> csDiffPerMinDeltas;
	private Map<String, Double> damageTakenDiffPerMinDeltas;
	private Map<String, Double> damageTakenPerMinDeltas;
	private Map<String, Double> goldPerMinDeltas;
	private String lane;
	private int participantId;
	private String role;
	private Map<String, Double> xpDiffPerMinDeltas;
	private Map<String, Double> xpPerMinDeltas;

	public Map<String, Double> getCreepsPerMinDeltas() {
		return creepsPerMinDeltas;
	}

	public Map<String, Double> getCsDiffPerMinDeltas() {
		return csDiffPerMinDeltas;
	}

	public Map<String, Double> getDamageTakenDiffPerMinDeltas() {
		return damageTakenDiffPerMinDeltas;
	}

	public Map<String, Double> getDamageTakenPerMinDeltas() {
		return damageTakenPerMinDeltas;
	}

	public Map<String, Double> getGoldPerMinDeltas() {
		return goldPerMinDeltas;
	}

	public String getLane() {
		return lane;
	}

	public int getParticipantId() {
		return participantId;
	}

	public String getRole() {
		return role;
	}

	public Map<String, Double> getXpDiffPerMinDeltas() {
		return xpDiffPerMinDeltas;
	}

	public Map<String, Double> getXpPerMinDeltas() {
		return xpPerMinDeltas;
	}
}
