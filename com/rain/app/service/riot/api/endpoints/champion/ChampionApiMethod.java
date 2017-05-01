package com.rain.app.service.riot.api.endpoints.champion;

import com.rain.app.service.riot.api.ApiConfig;
import com.rain.app.service.riot.api.ApiMethod;

abstract public class ChampionApiMethod extends ApiMethod {

	protected ChampionApiMethod(ApiConfig config) {
		super(config, "champion");
		requireApiKey();
	}
}
