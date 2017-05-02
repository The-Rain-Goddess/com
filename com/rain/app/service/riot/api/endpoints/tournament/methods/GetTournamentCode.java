package com.rain.app.service.riot.api.endpoints.tournament.methods;

import com.rain.app.service.riot.api.ApiConfig;
import com.rain.app.service.riot.api.endpoints.tournament.TournamentApiMethod;
import com.rain.app.service.riot.api.endpoints.tournament.dto.TournamentCode;
import com.rain.app.service.riot.constant.Platform;

public class GetTournamentCode extends TournamentApiMethod {

	public GetTournamentCode(ApiConfig config, String tournamentCode) {
		super(config);
		setReturnType(TournamentCode.class);
		setUrlBase(Platform.GLOBAL.getHost() + "/lol/tournament/v3/codes/" + tournamentCode);
		addTournamentApiKeyParameter();
	}
}
