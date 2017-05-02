package com.rain.app.service.riot.api.endpoints.tournament.methods;

import java.util.HashMap;
import java.util.Map;

import com.rain.app.service.riot.api.ApiConfig;
import com.rain.app.service.riot.api.endpoints.tournament.TournamentApiMethod;
import com.rain.app.service.riot.api.request.RequestMethod;
import com.rain.app.service.riot.constant.Platform;

public class CreateTournamentProvider extends TournamentApiMethod {

	public CreateTournamentProvider(ApiConfig config, String region, String callbackUrl) {
		super(config);
		setMethod(RequestMethod.POST);
		setReturnType(Integer.class);
		if (config.getTournamentMockMode()) {
			setUrlBase(Platform.GLOBAL.getHost() + "/lol/tournament-stub/v3/providers");
		} else {
			setUrlBase(Platform.GLOBAL.getHost() + "/lol/tournament/v3/providers");
		}
		addTournamentApiKeyParameter();
		allowMockMode();

		Map<String, Object> body = new HashMap<String, Object>();
		body.put("region", region);
		body.put("url", callbackUrl);
		buildJsonBody(body);
	}

}
