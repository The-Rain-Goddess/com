package com.rain.app.service.riot.api.endpoints.masteries;

import com.rain.app.service.riot.api.ApiConfig;
import com.rain.app.service.riot.api.ApiMethod;

abstract public class MasteriesApiMethod extends ApiMethod {

	protected MasteriesApiMethod(ApiConfig config) {
		super(config, "masteries");
		requireApiKey();
	}
}
