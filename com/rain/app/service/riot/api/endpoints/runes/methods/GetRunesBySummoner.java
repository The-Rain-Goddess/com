package com.rain.app.service.riot.api.endpoints.runes.methods;

import com.rain.app.service.riot.api.ApiConfig;
import com.rain.app.service.riot.api.endpoints.runes.RunesApiMethod;
import com.rain.app.service.riot.api.endpoints.runes.dto.RunePages;
import com.rain.app.service.riot.constant.Platform;

public class GetRunesBySummoner extends RunesApiMethod {

	public GetRunesBySummoner(ApiConfig config, Platform platform, long summonerId) {
		super(config);
		setPlatform(platform);
		setReturnType(RunePages.class);
		setUrlBase(platform.getHost() + "/lol/platform/v3/runes/by-summoner/" + summonerId);
		addApiKeyParameter();
	}
}
