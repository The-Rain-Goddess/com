package com.rain.app.service.riot.api.endpoints.champion_mastery.methods;

import com.rain.app.service.riot.api.ApiConfig;
import com.rain.app.service.riot.api.endpoints.champion_mastery.ChampionMasteryApiMethod;
import com.rain.app.service.riot.api.endpoints.champion_mastery.dto.ChampionMastery;
import com.rain.app.service.riot.constant.Platform;

public class GetChampionMasteriesBySummonerByChampion extends ChampionMasteryApiMethod {

	public GetChampionMasteriesBySummonerByChampion(ApiConfig config, Platform platform, long summonerId, int championId) {
		super(config);
		setPlatform(platform);
		setReturnType(ChampionMastery.class);
		setUrlBase(platform.getHost() + "/lol/champion-mastery/v3/champion-masteries/by-summoner/" + summonerId + "/by-champion/" + championId);
		addApiKeyParameter();
	}
}
