package com.rain.app.service.riot.api.endpoints.spectator.methods;

import com.rain.app.service.riot.api.ApiConfig;
import com.rain.app.service.riot.api.endpoints.spectator.SpectatorApiMethod;
import com.rain.app.service.riot.api.endpoints.spectator.dto.CurrentGameInfo;
import com.rain.app.service.riot.constant.Platform;

public class GetActiveGameBySummoner extends SpectatorApiMethod {

	public GetActiveGameBySummoner(ApiConfig config, Platform platform, long summonerId) {
		super(config);
		setPlatform(platform);
		setReturnType(CurrentGameInfo.class);
		setUrlBase(platform.getHost() + "/lol/spectator/v3/active-games/by-summoner/" + summonerId);
		addApiKeyParameter();
	}
}
