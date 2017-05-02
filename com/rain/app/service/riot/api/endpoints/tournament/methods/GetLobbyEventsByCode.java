package com.rain.app.service.riot.api.endpoints.tournament.methods;

import com.rain.app.service.riot.api.ApiConfig;
import com.rain.app.service.riot.api.endpoints.tournament.TournamentApiMethod;
import com.rain.app.service.riot.api.endpoints.tournament.dto.LobbyEventWrapper;
import com.rain.app.service.riot.constant.Platform;

public class GetLobbyEventsByCode extends TournamentApiMethod {

	public GetLobbyEventsByCode(ApiConfig config, String tournamentCode) {
		super(config);
		setReturnType(LobbyEventWrapper.class);
		if (config.getTournamentMockMode()) {
			setUrlBase(Platform.GLOBAL.getHost() + "/lol/tournament-stub/v3/lobby-events/by-code/" + tournamentCode);
		} else {
			setUrlBase(Platform.GLOBAL.getHost() + "/lol/tournament/v3/lobby-events/by-code/" + tournamentCode);
		}
		addTournamentApiKeyParameter();
		allowMockMode();
	}
}
