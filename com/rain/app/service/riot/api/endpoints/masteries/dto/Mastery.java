package com.rain.app.service.riot.api.endpoints.masteries.dto;

import java.io.Serializable;

import com.rain.app.service.riot.api.Dto;

public class Mastery extends Dto implements Serializable{

	private static final long serialVersionUID = -1053051501742552701L;

	private int id;
	private int rank;

	public int getId() {
		return id;
	}

	public int getRank() {
		return rank;
	}

	@Override
	public String toString() {
		return getId() + ": " + getRank();
	}
}
