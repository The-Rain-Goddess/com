package com.rain.app.service.riot.api.endpoints.summoner;

import com.rain.app.service.riot.api.ApiConfig;
import com.rain.app.service.riot.api.ApiMethod;

public abstract class SummonerApiMethod extends ApiMethod {

	protected SummonerApiMethod(ApiConfig config) {
		super(config, "summoner");
		requireApiKey();
	}
}
