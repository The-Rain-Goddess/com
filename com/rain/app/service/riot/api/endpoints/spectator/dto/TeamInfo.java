package com.rain.app.service.riot.api.endpoints.spectator.dto;

import java.io.Serializable;
import java.util.Map;

import com.rain.app.service.riot.api.Dto;

public class TeamInfo extends Dto implements Serializable {

	private static final long serialVersionUID = -1268634988633731822L;

	private String memberStatus;
	private String name;
	private long secondsUntilEligibleForDeletion;
	private String tag;
	private Map<String, String> teamId;

	public String getFullTeamId() {
		return teamId.get("fullId");
	}

	public String getMemberStatus() {
		return memberStatus;
	}

	public String getName() {
		return name;
	}

	public long getSecondsUntilEligibleForDeletion() {
		return secondsUntilEligibleForDeletion;
	}

	public String getTag() {
		return tag;
	}

	@Override
	public String toString() {
		return getName();
	}
}
