package com.rain.app.service.riot.api.endpoints.masteries.methods;

import com.rain.app.service.riot.api.ApiConfig;
import com.rain.app.service.riot.api.endpoints.masteries.MasteriesApiMethod;
import com.rain.app.service.riot.api.endpoints.masteries.dto.MasteryPages;
import com.rain.app.service.riot.constant.Platform;

public class GetMasteriesBySummoner extends MasteriesApiMethod {

	public GetMasteriesBySummoner(ApiConfig config, Platform platform, long summonerId) {
		super(config);
		setPlatform(platform);
		setReturnType(MasteryPages.class);
		setUrlBase(platform.getHost() + "/lol/platform/v3/masteries/by-summoner/" + summonerId);
		addApiKeyParameter();
	}
}
