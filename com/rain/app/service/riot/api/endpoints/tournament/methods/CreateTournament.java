package com.rain.app.service.riot.api.endpoints.tournament.methods;

import java.util.HashMap;
import java.util.Map;

import com.rain.app.service.riot.api.ApiConfig;
import com.rain.app.service.riot.api.endpoints.tournament.TournamentApiMethod;
import com.rain.app.service.riot.api.request.RequestMethod;
import com.rain.app.service.riot.constant.Platform;

public class CreateTournament extends TournamentApiMethod {

	public CreateTournament(ApiConfig config, String tournamentName, int providerId) {
		super(config);
		setMethod(RequestMethod.POST);
		setReturnType(Integer.class);
		if (config.getTournamentMockMode()) {
			setUrlBase(Platform.GLOBAL.getHost() + "/lol/tournament-stub/v3/tournaments");
		} else {
			setUrlBase(Platform.GLOBAL.getHost() + "/lol/tournament/v3/tournaments");
		}
		addTournamentApiKeyParameter();
		allowMockMode();

		Map<String, Object> body = new HashMap<String, Object>();
		body.put("name", (tournamentName == null) ? "" : tournamentName);
		body.put("providerId", providerId);
		buildJsonBody(body);
	}
}
