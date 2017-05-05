package com.rain.app.service.riot.api.endpoints.league.methods;

import com.rain.app.service.riot.api.ApiConfig;
import com.rain.app.service.riot.api.endpoints.league.LeagueApiMethod;
import com.rain.app.service.riot.api.endpoints.league.dto.LeagueList;
import com.rain.app.service.riot.constant.Platform;

public class GetMasterLeagueByQueue extends LeagueApiMethod {

	public GetMasterLeagueByQueue(ApiConfig config, Platform platform, String queue) {
		super(config);
		setPlatform(platform);
		setReturnType(LeagueList.class);
		setUrlBase(platform.getHost() + "/lol/league/v3/masterleagues/by-queue/" + queue);
		addApiKeyParameter();
	}
}
