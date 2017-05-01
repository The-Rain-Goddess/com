package com.rain.app.service.riot.api.endpoints.match.dto;

import java.io.Serializable;

import com.rain.app.service.riot.api.Dto;

public class MatchPosition extends Dto implements Serializable {

	private static final long serialVersionUID = -6998248732159256525L;

	private int x;
	private int y;

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	@Override
	public String toString() {
		return "[" + getX() + "," + getY() + "]";
	}
}
