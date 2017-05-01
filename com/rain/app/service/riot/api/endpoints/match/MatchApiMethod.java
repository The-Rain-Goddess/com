package com.rain.app.service.riot.api.endpoints.match;

import com.rain.app.service.riot.api.ApiConfig;
import com.rain.app.service.riot.api.ApiMethod;

public abstract class MatchApiMethod extends ApiMethod {

	protected MatchApiMethod(ApiConfig config) {
		super(config, "match");
		requireApiKey();
	}
}
