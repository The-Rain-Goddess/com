package com.rain.app.service.riot.api.endpoints.match.dto;

import java.io.Serializable;

import com.rain.app.service.riot.api.Dto;

public class Player extends Dto implements Serializable {

	private static final long serialVersionUID = -4459702825178547603L;

	private long accountId;
	private long currentAccountId;
	private String currentPlatformId;
	private String matchHistoryUri;
	private String platformId;
	private int profileIcon;
	private long summonerId;
	private String summonerName;

	public long getAccountId() {
		return accountId;
	}

	public long getCurrentAccountId() {
		return currentAccountId;
	}

	public String getCurrentPlatformId() {
		return currentPlatformId;
	}

	public String getMatchHistoryUri() {
		return matchHistoryUri;
	}

	public String getPlatformId() {
		return platformId;
	}

	public int getProfileIcon() {
		return profileIcon;
	}

	public long getSummonerId() {
		return summonerId;
	}

	public String getSummonerName() {
		return summonerName;
	}

	@Override
	public String toString() {
		return getSummonerName();
	}
}
