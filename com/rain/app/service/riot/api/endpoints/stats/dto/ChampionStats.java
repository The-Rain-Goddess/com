package com.rain.app.service.riot.api.endpoints.stats.dto;

import java.io.Serializable;

import com.rain.app.service.riot.api.Dto;

public class ChampionStats extends Dto implements Serializable {

	private static final long serialVersionUID = 5305743875267859091L;

	private int id;
	private AggregatedStats stats;

	public int getId() {
		return id;
	}

	public AggregatedStats getStats() {
		return stats;
	}

	@Override
	public String toString() {
		return String.valueOf(getId());
	}
}
