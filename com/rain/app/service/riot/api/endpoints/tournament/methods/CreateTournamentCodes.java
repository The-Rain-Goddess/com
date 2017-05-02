package com.rain.app.service.riot.api.endpoints.tournament.methods;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.reflect.TypeToken;
import com.rain.app.service.riot.api.ApiConfig;
import com.rain.app.service.riot.api.UrlParameter;
import com.rain.app.service.riot.api.endpoints.tournament.TournamentApiMethod;
import com.rain.app.service.riot.api.endpoints.tournament.constant.PickType;
import com.rain.app.service.riot.api.endpoints.tournament.constant.SpectatorType;
import com.rain.app.service.riot.api.endpoints.tournament.constant.TournamentMap;
import com.rain.app.service.riot.api.request.RequestMethod;
import com.rain.app.service.riot.constant.Platform;

public class CreateTournamentCodes extends TournamentApiMethod {

	public CreateTournamentCodes(ApiConfig config, int tournamentId, int count, int teamSize, TournamentMap mapType, PickType pickType,
			SpectatorType spectatorType, String metaData, long... allowedSummonerIds) {
		super(config);
		setMethod(RequestMethod.POST);
		setReturnType(new TypeToken<List<String>>() {
		}.getType());
		if (config.getTournamentMockMode()) {
			setUrlBase(Platform.GLOBAL.getHost() + "/lol/tournament-stub/v3/codes");
		} else {
			setUrlBase(Platform.GLOBAL.getHost() + "/lol/tournament/v3/codes");
		}
		add(new UrlParameter("tournamentId", tournamentId));
		add(new UrlParameter("count", count));
		addTournamentApiKeyParameter();
		allowMockMode();

		Map<String, Object> body = new HashMap<String, Object>();
		body.put("teamSize", teamSize);
		body.put("mapType", mapType);
		body.put("pickType", pickType);
		body.put("spectatorType", spectatorType);
		if (metaData != null) {
			body.put("metaData", metaData);
		}
		if (allowedSummonerIds != null && allowedSummonerIds.length > 0) {
			HashMap<String, Object> allowedSummonerIdsMap = new HashMap<String, Object>();
			allowedSummonerIdsMap.put("participants", allowedSummonerIds);
			body.put("allowedSummonerIds", allowedSummonerIdsMap);
		}
		buildJsonBody(body);
	}
}
