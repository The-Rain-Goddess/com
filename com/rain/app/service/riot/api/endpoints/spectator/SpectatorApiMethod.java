package com.rain.app.service.riot.api.endpoints.spectator;

import com.rain.app.service.riot.api.ApiConfig;
import com.rain.app.service.riot.api.ApiMethod;

abstract public class SpectatorApiMethod extends ApiMethod {

	protected SpectatorApiMethod(ApiConfig config) {
		super(config, "spectator");
		requireApiKey();
	}
}
