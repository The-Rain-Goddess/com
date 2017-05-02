package com.rain.app.service.riot.api.endpoints.stats.dto;

import java.io.Serializable;
import java.util.List;

import com.rain.app.service.riot.api.Dto;

public class PlayerStatsSummaryList extends Dto implements Serializable {

	private static final long serialVersionUID = 1005704777989478627L;

	private List<PlayerStatsSummary> playerStatSummaries;
	private long summonerId;

	public List<PlayerStatsSummary> getPlayerStatSummaries() {
		return playerStatSummaries;
	}

	public long getSummonerId() {
		return summonerId;
	}
}
