package com.rain.app.service.riot.api.endpoints.league.methods;

import java.util.Set;

import com.google.gson.reflect.TypeToken;
import com.rain.app.service.riot.api.ApiConfig;
import com.rain.app.service.riot.api.endpoints.league.LeagueApiMethod;
import com.rain.app.service.riot.api.endpoints.league.dto.LeaguePosition;
import com.rain.app.service.riot.constant.Platform;

public class GetLeaguePositionsBySummonerId extends LeagueApiMethod {

	public GetLeaguePositionsBySummonerId(ApiConfig config, Platform platform, long summonerId) {
		super(config);
		setPlatform(platform);
		setReturnType(new TypeToken<Set<LeaguePosition>>() {
		}.getType());
		setUrlBase(platform.getHost() + "/lol/league/v3/positions/by-summoner/" + summonerId);
		addApiKeyParameter();
	}
}
