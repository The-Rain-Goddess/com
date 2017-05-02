package com.rain.app.service.riot.api.endpoints.constants.dto;

import java.io.Serializable;

import com.rain.app.service.riot.api.Dto;

public class BlockItem extends Dto implements Serializable {

	private static final long serialVersionUID = 716305113317412182L;

	private int count;
	private int id;

	public int getCount() {
		return count;
	}

	public int getId() {
		return id;
	}

	@Override
	public String toString() {
		return getId() + ": " + getCount();
	}
}
