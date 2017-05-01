package com.rain.app.service.riot.api.endpoints.champion_mastery.methods;

import com.rain.app.service.riot.api.ApiConfig;
import com.rain.app.service.riot.api.endpoints.champion_mastery.ChampionMasteryApiMethod;
import com.rain.app.service.riot.constant.Platform;

public class GetChampionMasteryScoresBySummoner extends ChampionMasteryApiMethod {

	public GetChampionMasteryScoresBySummoner(ApiConfig config, Platform platform, long summonerId) {
		super(config);
		setPlatform(platform);
		setReturnType(Integer.class);
		setUrlBase(platform.getHost() + "/lol/champion-mastery/v3/scores/by-summoner/" + summonerId);
		addApiKeyParameter();
	}
}
