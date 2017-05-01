package com.rain.app.service.riot.api.endpoints.league;

import com.rain.app.service.riot.api.ApiConfig;
import com.rain.app.service.riot.api.ApiMethod;

abstract public class LeagueApiMethod extends ApiMethod {

	protected LeagueApiMethod(ApiConfig config) {
		super(config, "league");
		requireApiKey();
	}
}
