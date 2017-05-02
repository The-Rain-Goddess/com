package com.rain.app.service.riot.api.endpoints.summoner.methods;

import com.rain.app.service.riot.api.ApiConfig;
import com.rain.app.service.riot.api.endpoints.summoner.SummonerApiMethod;
import com.rain.app.service.riot.api.endpoints.summoner.dto.Summoner;
import com.rain.app.service.riot.constant.Platform;

public class GetSummonerByAccount extends SummonerApiMethod {

	public GetSummonerByAccount(ApiConfig config, Platform platform, long accountId) {
		super(config);
		setPlatform(platform);
		setReturnType(Summoner.class);
		setUrlBase(platform.getHost() + "/lol/summoner/v3/summoners/by-account/" + accountId);
		addApiKeyParameter();
	}
}
