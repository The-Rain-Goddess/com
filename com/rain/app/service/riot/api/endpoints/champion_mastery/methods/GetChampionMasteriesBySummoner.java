package com.rain.app.service.riot.api.endpoints.champion_mastery.methods;

import java.util.List;

import com.google.gson.reflect.TypeToken;
import com.rain.app.service.riot.api.ApiConfig;
import com.rain.app.service.riot.api.endpoints.champion_mastery.ChampionMasteryApiMethod;
import com.rain.app.service.riot.api.endpoints.champion_mastery.dto.ChampionMastery;
import com.rain.app.service.riot.constant.Platform;

public class GetChampionMasteriesBySummoner extends ChampionMasteryApiMethod {

	public GetChampionMasteriesBySummoner(ApiConfig config, Platform platform, long summonerId) {
		super(config);
		setPlatform(platform);
		setReturnType(new TypeToken<List<ChampionMastery>>() {
		}.getType());
		setUrlBase(platform.getHost() + "/lol/champion-mastery/v3/champion-masteries/by-summoner/" + summonerId);
		addApiKeyParameter();
	}
}
