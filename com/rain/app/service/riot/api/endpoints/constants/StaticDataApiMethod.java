package com.rain.app.service.riot.api.endpoints.constants;

import com.rain.app.service.riot.api.ApiConfig;
import com.rain.app.service.riot.api.ApiMethod;

abstract public class StaticDataApiMethod extends ApiMethod {

	protected StaticDataApiMethod(ApiConfig config) {
		super(config, "staticdata");
		requireApiKey();
	}
}
