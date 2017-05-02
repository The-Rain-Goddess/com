package com.rain.app.service.riot.api.endpoints.match.methods;

import com.rain.app.service.riot.api.ApiConfig;
import com.rain.app.service.riot.api.UrlParameter;
import com.rain.app.service.riot.api.endpoints.match.dto.Match;
import com.rain.app.service.riot.api.endpoints.tournament.TournamentApiMethod;
import com.rain.app.service.riot.constant.Platform;

public class GetMatchByMatchIdAndTournamentCode extends TournamentApiMethod {

	public GetMatchByMatchIdAndTournamentCode(ApiConfig config, Platform platform, long matchId, String tournamentCode) {
		super(config);
		setPlatform(platform);
		setReturnType(Match.class);
		setUrlBase(platform.getHost() + "/lol/match/v3/matches/" + matchId + "/by-tournament-code/" + tournamentCode);
		add(new UrlParameter("tournamentCode", tournamentCode));
		addTournamentApiKeyParameter();
		allowMockMode();
	}
}
