package com.rain.app.service.riot.api.endpoints.champion_mastery;

import com.rain.app.service.riot.api.ApiConfig;
import com.rain.app.service.riot.api.ApiMethod;

abstract public class ChampionMasteryApiMethod extends ApiMethod {

	protected ChampionMasteryApiMethod(ApiConfig config) {
		super(config, "championmastery");
		requireApiKey();
	}
}
