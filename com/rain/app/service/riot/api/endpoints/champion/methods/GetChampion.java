package com.rain.app.service.riot.api.endpoints.champion.methods;

import com.rain.app.service.riot.api.ApiConfig;
import com.rain.app.service.riot.api.endpoints.champion.ChampionApiMethod;
import com.rain.app.service.riot.api.endpoints.champion.dto.Champion;
import com.rain.app.service.riot.constant.Platform;

public class GetChampion extends ChampionApiMethod {

	public GetChampion(ApiConfig config, Platform platform, int id) {
		super(config);
		setPlatform(platform);
		setReturnType(Champion.class);
		setUrlBase(platform.getHost() + "/lol/platform/v3/champions/" + id);
		addApiKeyParameter();
	}
}
