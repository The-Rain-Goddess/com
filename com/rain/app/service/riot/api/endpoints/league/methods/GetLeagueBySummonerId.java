package com.rain.app.service.riot.api.endpoints.league.methods;

import java.util.List;

import com.google.gson.reflect.TypeToken;
import com.rain.app.service.riot.api.ApiConfig;
import com.rain.app.service.riot.api.endpoints.league.LeagueApiMethod;
import com.rain.app.service.riot.api.endpoints.league.dto.LeagueList;
import com.rain.app.service.riot.constant.Platform;

public class GetLeagueBySummonerId extends LeagueApiMethod {

	public GetLeagueBySummonerId(ApiConfig config, Platform platform, long summonerId) {
		super(config);
		setPlatform(platform);
		setReturnType(new TypeToken<List<LeagueList>>() {
		}.getType());
		setUrlBase(platform.getHost() + "/lol/league/v3/leagues/by-summoner/" + summonerId);
		addApiKeyParameter();
	}
}