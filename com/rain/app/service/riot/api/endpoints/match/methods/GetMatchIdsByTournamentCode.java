package com.rain.app.service.riot.api.endpoints.match.methods;

import java.util.List;

import com.google.gson.reflect.TypeToken;
import com.rain.app.service.riot.api.ApiConfig;
import com.rain.app.service.riot.api.endpoints.tournament.TournamentApiMethod;
import com.rain.app.service.riot.constant.Platform;

public class GetMatchIdsByTournamentCode extends TournamentApiMethod {

	public GetMatchIdsByTournamentCode(ApiConfig config, Platform platform, String tournamentCode) {
		super(config);
		setPlatform(platform);
		setReturnType(new TypeToken<List<Long>>() {
		}.getType());
		setUrlBase(platform.getHost() + "/lol/match/v3/matches/by-tournament-code/" + tournamentCode + "/ids");
		addTournamentApiKeyParameter();
		allowMockMode();
	}
}
