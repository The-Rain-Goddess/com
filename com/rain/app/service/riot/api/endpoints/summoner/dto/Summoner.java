package com.rain.app.service.riot.api.endpoints.summoner.dto;

import java.io.Serializable;

import com.rain.app.service.riot.api.Dto;

public class Summoner extends Dto implements Serializable {

	private static final long serialVersionUID = -8213488199644701555L;

	private long accountId;
	private long id;
	private String name;
	private int profileIconId;
	private long revisionDate;
	private int summonerLevel;

	public long getAccountId() {
		return accountId;
	}

	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public int getProfileIconId() {
		return profileIconId;
	}

	public long getRevisionDate() {
		return revisionDate;
	}

	public int getSummonerLevel() {
		return summonerLevel;
	}

	@Override
	public String toString() {
		return getId() + ": " + getName();
	}
}
