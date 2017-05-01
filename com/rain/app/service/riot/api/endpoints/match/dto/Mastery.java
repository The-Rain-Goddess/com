package com.rain.app.service.riot.api.endpoints.match.dto;

import java.io.Serializable;

import com.rain.app.service.riot.api.Dto;

public class Mastery extends Dto implements Serializable {

private static final long serialVersionUID = 1049623074386811783L;
	
	private int masteryId;
	private int rank;

	public int getMasteryId() {
		return masteryId;
	}
	
	public int getRank() {
		return rank;
	}

	@Override
	public String toString() {
		return getMasteryId() + ": " + getRank();
	}
}
