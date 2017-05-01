package com.rain.app.service.riot.api.endpoints.league.dto;

import java.io.Serializable;

import com.rain.app.service.riot.api.Dto;

public class MiniSeries extends Dto implements Serializable {

	private static final long serialVersionUID = -1698803031525933530L;

	private int losses;
	private String progress;
	private int target;
	private int wins;

	public int getLosses() {
		return losses;
	}

	public String getProgress() {
		return progress;
	}

	public int getTarget() {
		return target;
	}

	public int getWins() {
		return wins;
	}

	@Override
	public String toString() {
		return getProgress();
	}
}
