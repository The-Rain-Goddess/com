package com.rain.app.service.riot.api.endpoints.runes;

import com.rain.app.service.riot.api.ApiConfig;
import com.rain.app.service.riot.api.ApiMethod;

abstract public class RunesApiMethod extends ApiMethod {

	protected RunesApiMethod(ApiConfig config) {
		super(config, "runes");
		requireApiKey();
	}
}
