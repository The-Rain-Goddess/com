package com.rain.app.service.riot.api.endpoints.tournament.methods;

import java.util.HashMap;
import java.util.Map;

import com.rain.app.service.riot.api.ApiConfig;
import com.rain.app.service.riot.api.endpoints.tournament.TournamentApiMethod;
import com.rain.app.service.riot.api.endpoints.tournament.constant.PickType;
import com.rain.app.service.riot.api.endpoints.tournament.constant.SpectatorType;
import com.rain.app.service.riot.api.endpoints.tournament.constant.TournamentMap;
import com.rain.app.service.riot.api.request.RequestMethod;
import com.rain.app.service.riot.constant.Platform;

public class UpdateTournamentCode extends TournamentApiMethod {

	public UpdateTournamentCode(ApiConfig config, String tournamentCode, TournamentMap mapType, PickType pickType, SpectatorType spectatorType,
			long... allowedSummonerIds) {
		super(config);
		setMethod(RequestMethod.PUT);
		setReturnType(Void.class);
		setUrlBase(Platform.GLOBAL.getHost() + "/lol/tournament/v3/codes/" + tournamentCode);
		addTournamentApiKeyParameter();

		Map<String, Object> body = new HashMap<String, Object>();
		if (mapType != null) {
			body.put("mapType", mapType);
		}
		if (pickType != null) {
			body.put("pickType", pickType);
		}
		if (spectatorType != null) {
			body.put("spectatorType", spectatorType);
		}
		if (allowedSummonerIds != null && allowedSummonerIds.length > 0) {
			HashMap<String, Object> allowedSummonerIdsMap = new HashMap<String, Object>();
			allowedSummonerIdsMap.put("participants", allowedSummonerIds);
			body.put("allowedSummonerIds", allowedSummonerIdsMap);
		}
		buildJsonBody(body);
	}
}
