package com.rain.app.service.riot.api.endpoints.spectator.dto;

import java.io.Serializable;
import java.util.List;

import com.rain.app.service.riot.api.Dto;

public class FeaturedGames extends Dto implements Serializable {

	private static final long serialVersionUID = -8500784155234313042L;

	private long clientRefreshInterval;
	private List<FeaturedGameInfo> gameList;

	public long getClientRefreshInterval() {
		return clientRefreshInterval;
	}

	public List<FeaturedGameInfo> getGameList() {
		return gameList;
	}
}
