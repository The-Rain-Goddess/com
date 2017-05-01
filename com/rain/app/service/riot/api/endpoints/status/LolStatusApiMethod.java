package com.rain.app.service.riot.api.endpoints.status;

import com.rain.app.service.riot.api.ApiConfig;
import com.rain.app.service.riot.api.ApiMethod;

abstract public class LolStatusApiMethod extends ApiMethod {

	protected LolStatusApiMethod(ApiConfig config) {
		super(config, "lolstatus");
		requireApiKey();
	}

}
