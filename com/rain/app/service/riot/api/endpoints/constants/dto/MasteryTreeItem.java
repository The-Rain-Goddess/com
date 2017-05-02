package com.rain.app.service.riot.api.endpoints.constants.dto;

import java.io.Serializable;

import com.rain.app.service.riot.api.Dto;

public class MasteryTreeItem extends Dto implements Serializable {

	private static final long serialVersionUID = -1538829926775279621L;

	private int masteryId;
	private String prereq;

	public int getMasteryId() {
		return masteryId;
	}

	public String getPrereq() {
		return prereq;
	}

	@Override
	public String toString() {
		return String.valueOf(getMasteryId());
	}
}
