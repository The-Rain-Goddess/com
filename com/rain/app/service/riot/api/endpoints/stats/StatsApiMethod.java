package com.rain.app.service.riot.api.endpoints.stats;

import com.rain.app.service.riot.api.ApiConfig;
import com.rain.app.service.riot.api.ApiMethod;

abstract public class StatsApiMethod extends ApiMethod {

	@Deprecated
	protected StatsApiMethod(ApiConfig config) {
		super(config, "stats");
		requireApiKey();
	}
}
