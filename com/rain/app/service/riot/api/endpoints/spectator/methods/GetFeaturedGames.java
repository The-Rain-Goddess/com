package com.rain.app.service.riot.api.endpoints.spectator.methods;

import com.rain.app.service.riot.api.ApiConfig;
import com.rain.app.service.riot.api.endpoints.spectator.SpectatorApiMethod;
import com.rain.app.service.riot.api.endpoints.spectator.dto.FeaturedGames;
import com.rain.app.service.riot.constant.Platform;

public class GetFeaturedGames extends SpectatorApiMethod {

	public GetFeaturedGames(ApiConfig config, Platform platform) {
		super(config);
		setPlatform(platform);
		setReturnType(FeaturedGames.class);
		setUrlBase(platform.getHost() + "/lol/spectator/v3/featured-games");
		addApiKeyParameter();
	}
}
