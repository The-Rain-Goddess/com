package com.rain.app.service.riot.api.endpoints.stats.dto;

import java.io.Serializable;

import com.rain.app.service.riot.api.Dto;

public class PlayerStatsSummary extends Dto implements Serializable {

	private static final long serialVersionUID = -3584187392263947778L;

	private AggregatedStats aggregatedStats;
	private int losses;
	private long modifyDate;
	private String playerStatSummaryType;
	private int wins;

	public AggregatedStats getAggregatedStats() {
		return aggregatedStats;
	}

	public int getLosses() {
		return losses;
	}

	public long getModifyDate() {
		return modifyDate;
	}

	public String getPlayerStatSummaryType() {
		return playerStatSummaryType;
	}

	public int getWins() {
		return wins;
	}

	@Override
	public String toString() {
		return getPlayerStatSummaryType();
	}
}
