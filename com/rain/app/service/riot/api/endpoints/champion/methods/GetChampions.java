package com.rain.app.service.riot.api.endpoints.champion.methods;

import com.rain.app.service.riot.api.ApiConfig;
import com.rain.app.service.riot.api.UrlParameter;
import com.rain.app.service.riot.api.endpoints.champion.ChampionApiMethod;
import com.rain.app.service.riot.api.endpoints.champion.dto.ChampionList;
import com.rain.app.service.riot.constant.Platform;

public class GetChampions extends ChampionApiMethod {

	public GetChampions(ApiConfig config, Platform platform, boolean freeToPlay) {
		super(config);
		setPlatform(platform);
		setReturnType(ChampionList.class);
		setUrlBase(platform.getHost() + "/lol/platform/v3/champions");
		if (freeToPlay) {
			add(new UrlParameter("freeToPlay", freeToPlay));
		}
		addApiKeyParameter();
	}
}
